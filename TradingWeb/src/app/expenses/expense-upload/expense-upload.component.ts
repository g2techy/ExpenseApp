import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { ExpenseService } from '../../services/expense.service';
import { SharedService } from '../../shared/shared.service';

import * as models from '../../models/index';
import { Utility } from '../../utilities/global-utility';

@Component({
  selector: 'app-expense-upload',
  templateUrl: './expense-upload.component.html',
  styleUrls: ['./expense-upload.component.scss']
})
export class ExpenseUploadComponent implements OnInit {

  uploadForm: FormGroup;
  constructor(private fb : FormBuilder,
              private sharedService: SharedService, private expenseService: ExpenseService) { }

  ngOnInit() {
    this.creatForm();
  }

  private creatForm() : void {
    this.uploadForm = this.fb.group({
      file: ['', Validators.required]
    });
  }

  onDownload() {
    this.expenseService.downloadTemplate().subscribe(res => {
      Utility.downloadExcelFile(res, 'expense_upload_template.xlsx');
    });
  }

  onSubmit() {
    if(!this.isFormValid){
      return;
    }

    

  }

  get isFormValid() : boolean {
    return this.uploadForm.valid;
  }

}
