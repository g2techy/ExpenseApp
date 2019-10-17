import { Injectable, TemplateRef } from '@angular/core';
import { Subject } from 'rxjs/internal/Subject';

import { NgbModal, NgbModalOptions } from '@ng-bootstrap/ng-bootstrap';

import { AlertService } from '../services/alert.service';
import { ConfirmService } from '../services/confirm.service';

@Injectable({
  providedIn: 'root'
})
export class SharedService {

  public toggleSidebar$: Subject<any> = new Subject();

  constructor(private alertService : AlertService, private confirmService: ConfirmService, private modalService: NgbModal ) { }

  toggleSidebar(){
    this.toggleSidebar$.next(null);
  }

  showSuccessMsg(message: string) {
    this.alertService.success(message, true);
  }

  showErrorMsg(message: string) {
    this.alertService.error(message, true);
  }

  showConfirmMsg(message: string, onYes?: ()=> void, onNo?: () => void) {
    this.confirmService.confirm({message: message, onYes: onYes, onNo: onNo});
  }

  showModal(content: string | TemplateRef<any> | any, config?: NgbModalOptions) {
    this.modalService.open(content, config || { size: 'xl' });
  }

  closeAllModals() {
    this.modalService.dismissAll();
  }
  
}
