import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';

import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

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

  constructor(private modalService: NgbModal, private activeModal: NgbActiveModal) { }

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges) {
    if(changes){
      if(changes['expenses']){
        this.expenseCategories = Utility.getExpenseCategories(this.expenses);
        this.totalExpense = this.expenseCategories.reduce((a, b) => a + b.expenseAmount, 0);
        this.closeModal();
      }
    }
  }

  showExpenses(modalWin, categoryId) {
    this.selectedExpenses = this.expenses.filter(e => e.categoryId == categoryId);
    this.activeModal = this.modalService.open(modalWin, { size: 'xl' });
  }

  closeModal() {
    this.activeModal.close();
  }
  
}
