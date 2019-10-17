import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';

import { ExpenseService } from '../../services/expense.service';
import { SharedService } from '../../shared/shared.service';

import { Utility } from '../../utilities/global-utility';

@Component({
  selector: 'app-expense-fund-sources',
  templateUrl: './expense-fund-sources.component.html',
  styleUrls: ['./expense-fund-sources.component.scss']
})
export class ExpenseFundSourcesComponent implements OnInit {

  @Input() expenses: any;
  fundSources: any;
  selectedExpenses: any;

  constructor(private sharedService: SharedService, private expenseService: ExpenseService) { }

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges) {
    if(changes){
      if(changes['expenses']){
        this.fundSources = Utility.Expense.getFundSources(this.expenses);
        this.sharedService.closeAllModals();
      }
    }
  }

  showExpenses(modalWin, fundSourceId) {
    this.selectedExpenses = this.expenses.filter(e => e.fundSourceId == fundSourceId);
    this.sharedService.showModal(modalWin);   
  }
  
}
