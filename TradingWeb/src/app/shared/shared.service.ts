import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/internal/Subject';

import { AlertService } from '../services/alert.service';
import { ConfirmService } from '../services/confirm.service';

@Injectable({
  providedIn: 'root'
})
export class SharedService {

  public toggleSidebar$: Subject<any> = new Subject();

  constructor(private alertService : AlertService, private confirmService: ConfirmService ) { }

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
  
}
