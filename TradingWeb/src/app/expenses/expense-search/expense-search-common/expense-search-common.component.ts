import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

import { Subscription } from 'rxjs/internal/Subscription';

import { ExpenseService } from '../../../services/expense.service';
import { SharedService } from '../../../shared/shared.service';

import * as models from '../../../models/index';
import { Utility } from '../../../utilities/global-utility';

@Component({
  selector: 'app-expense-search-common',
  templateUrl: './expense-search-common.component.html',
  styleUrls: ['./expense-search-common.component.scss']
})
export class ExpenseSearchCommonComponent implements OnInit {

  selectedTab: string = 'cm';
  showSearchResult: boolean = true;
  expenses: any;
  searchForm: FormGroup;
  expenseTags: any;
  beforeExpenseDeleteSub: Subscription

  constructor(private fb : FormBuilder, private route: ActivatedRoute,
              private sharedService: SharedService, private expenseService: ExpenseService) { }

  ngOnInit() {
    this.selectedTab = this.route.snapshot.data['searchType'] || 'cm';
    this.creatForm();
    this.initSearch();
    this.beforeExpenseDeleteSub = this.expenseService.beforeDelete$.subscribe(exp => {
      this.onExpenseDelete(exp);
    });    
  }

  ngOnDestroy() {
    if(this.beforeExpenseDeleteSub){
      this.beforeExpenseDeleteSub.unsubscribe();
    }
  }

  private initSearch() {
    this.showHideSearchResult(false);
    if(this.selectedTab != 'cs'){
      this.searchExpenses(this.getExpSearchModel());
    } else {
      this.onSeachSubmit();
    }
  }

  private getExpSearchModel() {    
    let today = new Date();
    let searchModel = {startDate: '', endDate: today.toISOString().slice(0, 10)};
    if(this.selectedTab == 'cm') {
        searchModel.startDate = today.getFullYear() + '-' + String(today.getMonth() + 1).padStart(2, '0') + '-01';      
    } else if(this.selectedTab == '3m') {
      searchModel.startDate = this.findStartDate(today, 2);
    } else if(this.selectedTab == '6m') {
      searchModel.startDate = this.findStartDate(today, 5);
    } else if(this.selectedTab == 'ytd') {
      searchModel.startDate = today.getFullYear() + '-01-01';
    }
    return searchModel;
  }

  private searchExpenses(payload: any) {    
    this.expenseService.searchExpense(payload).subscribe(res => {
      if(res.statusCode == 200){
        this.expenses = res.data;        
        this.showHideSearchResult(true);
      } else {
        this.expenses = [];
        this.sharedService.showErrorMsg(res.message);
        }
    });

    this.expenseService.expenseSummaryByTag(payload).subscribe(res => {
      if(res.statusCode == 200){
        this.expenseTags = res.data;
      } else {
        this.expenseTags = [];
        this.sharedService.showErrorMsg(res.message);
      }
    });
  }

  private showHideSearchResult(flag: boolean) {
    this.showSearchResult = flag;
  }

  private findStartDate(today, pastMonths) {
    let startDate = '';
    let currMonth = today.getMonth();
    if(currMonth < pastMonths){
      startDate = (today.getFullYear()-1) + '-' + String(12 - (pastMonths - currMonth) + 1).padStart(2, '0') + '-01';
    } else {
      startDate = today.getFullYear() + '-' + String(currMonth - pastMonths + 1).padStart(2, '0') + '-01';
    }
    return startDate;
  }

  private creatForm() : void {
    this.searchForm = this.fb.group({
      startDate: ['', Validators.required],
        endDate: ['', Validators.required]
      });
  }

  onSeachSubmit() {
    if(!this.isSeachFormValid){
      return;
    }
    const payload =  Utility.copyObject(this.searchForm.value);
    payload.startDate = Utility.getDateFromDP(payload.startDate);
    payload.endDate = Utility.getDateFromDP(payload.endDate);
    this.searchExpenses(payload);
  }

  get isSeachFormValid() : boolean {
    return this.searchForm.valid;
  }

  onExpenseDelete(expense) {
    this.expenseService.deleteExpense(expense.expenseId).subscribe(res => {
      if(res.statusCode == 200){
        this.sharedService.showErrorMsg('Expense deleted successfully');
        this.initSearch();
      } else {
        this.sharedService.showErrorMsg(res.message);
      }
    });
  }
}
