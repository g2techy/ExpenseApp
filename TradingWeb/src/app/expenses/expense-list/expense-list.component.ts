import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';

import { ExpenseService } from '../../services/expense.service';
import { SharedService } from '../../shared/shared.service';

@Component({
  selector: 'app-expense-list',
  templateUrl: './expense-list.component.html',
  styleUrls: ['./expense-list.component.scss']
})
export class ExpenseListComponent implements OnInit, OnChanges {

  @Input() expenses: any;
  totalExpense: number = 0;
  
  constructor(private sharedService: SharedService, private expenseService: ExpenseService) { 
    
  }

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges) {
    if(changes) {
      if(changes['expenses'] && changes['expenses'].currentValue){
        this.totalExpense = this.expenses.reduce((a, b) => a + b.expenseAmount, 0);
      }
    }
  }

  onDelete(expense: any) {
    this.sharedService.showConfirmMsg(`Are you sure you want to delete '${expense.expenseName}' expense?`, () => {
      this.expenseService.beforeDeleteExpense(expense);
    });
  }
  
}
