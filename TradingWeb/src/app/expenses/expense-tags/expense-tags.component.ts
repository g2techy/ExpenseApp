import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';

import { ExpenseService } from '../../services/expense.service';
import { SharedService } from '../../shared/shared.service';

import { Utility } from '../../utilities/global-utility';

@Component({
  selector: 'app-expense-tags',
  templateUrl: './expense-tags.component.html',
  styleUrls: ['./expense-tags.component.scss']
})
export class ExpenseTagsComponent implements OnInit, OnChanges {

  @Input() expenseTags: any;
  selectedExpenses: any;
  
  constructor(private sharedService: SharedService, private expenseService: ExpenseService) { }

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges) {
    if(changes){
      if(changes['expenseTags']){
        this.sharedService.closeAllModals();
      }
    }
  }

  showExpenses(modalWin, tagId) {
    let lastSearchModel = Utility.copyObject(this.expenseService.lastSearchModel);
    lastSearchModel.tagId = tagId;
    this.expenseService.searchExpensesByTag(lastSearchModel).subscribe(res => {
      this.selectedExpenses = [];
      if(res.statusCode == 200){
        this.selectedExpenses = res.data;
        this.sharedService.showModal(modalWin);
      } else {
        this.sharedService.showErrorMsg(res.message);
      }
    });   
  }

}
