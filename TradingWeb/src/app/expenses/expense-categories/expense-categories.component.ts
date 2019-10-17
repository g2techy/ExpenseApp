import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';

import { SharedService } from '../../shared/shared.service';

import { Utility } from '../../utilities/global-utility';

@Component({
  selector: 'app-expense-categories',
  templateUrl: './expense-categories.component.html',
  styleUrls: ['./expense-categories.component.scss']
})
export class ExpenseCategoriesComponent implements OnInit, OnChanges {

  @Input() expenses: any;
  expenseCategories: any;
  selectedExpenses: any;
  totalExpense: number = 0;

  constructor(private sharedService: SharedService) { }

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges) {
    if(changes){
      if(changes['expenses']){
        this.expenseCategories = Utility.Expense.getExpenseCategories(this.expenses);
        this.totalExpense = Utility.Expense.getTotalExpense(this.expenseCategories);
        this.sharedService.closeAllModals();
      }
    }
  }

  showExpenses(modalWin, categoryId) {
    this.selectedExpenses = this.expenses.filter(e => e.categoryId == categoryId);
    this.sharedService.showModal(modalWin);
  }
  
  
}
