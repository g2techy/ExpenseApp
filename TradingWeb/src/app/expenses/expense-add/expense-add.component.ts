import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { TypeValueService } from '../../services/type-value.service';
import { ExpenseService } from '../../services/expense.service';
import { SharedService } from '../../shared/shared.service';

import { ExpenseTagListComponent } from '../expense-tag-list/expense-tag-list.component';

import * as models from '../../models/index';
import { Utility } from '../../utilities/global-utility';

@Component({
  selector: 'app-expense-add',
  templateUrl: './expense-add.component.html',
  styleUrls: ['./expense-add.component.scss']
})
export class ExpenseAddComponent implements OnInit {

  expenseId: number = 0;
  expenseDetailsModel : any;
  expenseForm: FormGroup;
  expenseCategoties: models.TypeValueModel[] = [];
  fundSources: models.TypeValueModel[] = [];
  expenseTags: models.TypeValueModel[] = [];
  headerText: string = 'Add New Expense';

  @ViewChild(ExpenseTagListComponent, null) tagListComponent : ExpenseTagListComponent;

  constructor(private fb : FormBuilder, private router: Router, private route : ActivatedRoute,
              private sharedService: SharedService, private typeValueService: TypeValueService, private expenseService: ExpenseService) { }

  ngOnInit() {
    this.creatForm();
    this.getExpenseCategories();
    this.getExpenseFundSources();
    this.getExpenseTags();
    this.route.params.subscribe(params => {
      this.expenseId = params['id'];
      if(this.expenseId && this.expenseId > 0){
        this.headerText = "Update Expense";
        this.getExpenseDetails();
      }
    });
  }

  private creatForm() : void {
    this.expenseForm = this.fb.group({
      expenseId: ['0'],
      expenseName: ['', Validators.required],
      expenseDate: ['', Validators.required],
      expenseAmount: ['', Validators.required],
      categoryId: ['', Validators.required],     
      isExpense: ['true'],
      expenseDesc: [''],
      fundSourceId: ['', Validators.required]
    });
  }

  get isFormValid() : boolean {
    return this.expenseForm.valid;
  }

  private getExpenseCategories() {
    this.typeValueService.getTypeValues(models.TypeIDEnum.expenseCategory).subscribe(data => {
      this.expenseCategoties = data;
    });
  }

  private getExpenseFundSources() {
    this.typeValueService.getTypeValues(models.TypeIDEnum.expenseFundSource).subscribe(data => {
      this.fundSources = data;
    });
  }

  private getExpenseTags() {
    this.typeValueService.getTypeValues(models.TypeIDEnum.expenseTag).subscribe(data => {
      this.expenseTags = data;
    });
  }

  onClear() {
    if(this.expenseId && this.expenseId > 0){
      this.setExpenseDetails()
    } else {
      this.expenseForm.reset();
    }    
  }

  onSubmit() {
    if(!this.isFormValid) {
      return;
    }

    let payLoad = Utility.copyObject(this.expenseForm.value);
    payLoad.expenseDate = Utility.getDateFromDP(payLoad.expenseDate);

    let selTags = this.tagListComponent.getSelectedTags();
    payLoad.expenseTags = [];
    if(selTags && selTags.length > 0){
      selTags.forEach(item => {
        payLoad.expenseTags.push({expenseTagId: 0,  expenseId: payLoad.expenseId, tagId: item.typeValueId});
      });
    }

    this.saveExpense(payLoad);
  }

  private saveExpense(expense: any) {
    this.expenseService.saveExpense(expense).subscribe(res => {
      if(res.statusCode == 200){
        this.sharedService.showSuccessMsg('Expense details saved successfully');
        this.navigateToExpenses();
      } else {
        this.sharedService.showErrorMsg(res.message);
      }
    });
  }

  navigateToExpenses() {
    this.router.navigate(['/expenses']);
  }

  private getExpenseDetails() {
    this.expenseService.getExpenseDetails(this.expenseId).subscribe(res => {
      if(res.statusCode == 200){
        this.expenseDetailsModel = res.data;
        this.setExpenseDetails();     
      } else {
        this.sharedService.showErrorMsg(res.message);
      }
    });
  }

  private setExpenseDetails() {
    this.expenseForm.patchValue(this.expenseDetailsModel);
    this.expenseForm.controls['expenseDate'].setValue(Utility.getDatePickerDateFromDate(this.expenseDetailsModel.expenseDate));
    this.setSelectedTags();
  }

  private setSelectedTags() {
    if(this.expenseDetailsModel) {
      this.tagListComponent.selectedTags = this.expenseDetailsModel.expenseTags;
      this.tagListComponent.setSelectedTags();
    }
  }

}
