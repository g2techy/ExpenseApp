import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';

import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

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
  totalExpense: number = 0;
  selectedExpenses: any;
  
  constructor(private modalService: NgbModal, private activeModal: NgbActiveModal,
              private sharedService: SharedService, private expenseService: ExpenseService) { }

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges) {
    if(changes){
      if(changes['expenseTags']){
        this.totalExpense = this.expenseTags.reduce((a, b) => a + b.expenseAmount, 0);
        this.closeModal();
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
        this.activeModal = this.modalService.open(modalWin, { size: 'xl' });
      } else {
        this.sharedService.showErrorMsg(res.message);
      }
    });
   
  }

  closeModal() {
    this.activeModal.close();
  }

}
