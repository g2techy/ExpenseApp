import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Subject } from 'rxjs/internal/Subject';

export interface ConfirmModel {
  message: string,
  onYes: () => void,
  onNo: () => void
}

@Injectable({
  providedIn: 'root'
})
export class ConfirmService {

  private subject = new Subject<ConfirmModel>();

  constructor() { }

  confirm(data: ConfirmModel) {
    this.subject.next(
        { message: data.message,
          onNo : () => {
            if(data.onNo){
              data.onNo();
            }            
          },
          onYes : () => {
            data.onYes();
          }
        }
    );
  }

  get confirm$() : Observable<ConfirmModel> {
    return this.subject.asObservable();
  }

}
