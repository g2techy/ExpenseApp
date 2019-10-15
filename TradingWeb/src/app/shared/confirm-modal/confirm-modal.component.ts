import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ConfirmService, ConfirmModel } from "../../services/confirm.service";
import { Subscription } from 'rxjs/internal/Subscription';

@Component({
  selector: 'app-confirm-modal',
  templateUrl: './confirm-modal.component.html',
  styleUrls: ['./confirm-modal.component.scss']
})
export class ConfirmModalComponent implements OnInit {

  @Input() confirmMessage: string;
  @ViewChild('modelWin', null) confirmWin: any;
  confirmSubscription: Subscription;
  currConfirmModel: ConfirmModel;


  constructor(private modalService: NgbModal, private activeModal : NgbActiveModal,
              private confirmService: ConfirmService) { 
              
  }

  ngOnInit() {
    this.confirmSubscription = this.confirmService.confirm$.subscribe(data => {      
      if(data){
        this.currConfirmModel = data;
        this.confirmMessage = data.message;
        this.activeModal = this.modalService.open(this.confirmWin, { size: 'lg' });
      }
    });
  }

  onYes() {
    if(this.currConfirmModel){
      this.currConfirmModel.onYes();
    }
    this.closeModal();
  }

  onNo() {
    if(this.currConfirmModel && this.currConfirmModel.onNo){
      this.currConfirmModel.onNo();
    }
    this.closeModal();
  }

  closeModal() {
    if(this.activeModal){
      this.activeModal.close();
    }    
  }

}
