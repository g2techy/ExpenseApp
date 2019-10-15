import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';

import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

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
  totalExpense: number = 0;
  selectedExpenses: any;

  constructor(private modalService: NgbModal, private activeModal: NgbActiveModal,
              private sharedService: SharedService, private expenseService: ExpenseService) { }

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges) {
    if(changes){
      if(changes['expenses']){
        this.fundSources = Utility.getFundSources(this.expenses);
        this.totalExpense = this.fundSources.reduce((a, b) => a + b.expenseAmount, 0);
        this.closeModal();
      }
    }
  }

  showExpenses(modalWin, fundSourceId) {
    this.selectedExpenses = this.expenses.filter(e => e.fundSourceId == fundSourceId);
    this.activeModal = this.modalService.open(modalWin, { size: 'xl' });   
  }

  closeModal() {
    this.activeModal.close();
  }

}
