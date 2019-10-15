import { Component, OnInit, ViewChild } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AlertService } from "../../services/alert.service";

@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.scss']
})
export class AlertComponent implements OnInit {

  @ViewChild('alertWin', null) confirmWin: any;
  messageModel : any;
  message: string;

  constructor(private modalService: NgbModal, private alertService : AlertService) { }

  ngOnInit() {
    this.alertService.getMessage().subscribe(message => { 
        if(message){
          this.messageModel = message;
          this.message = this.messageModel.message;
          this.modalService.open(this.confirmWin, { size: 'lg' });
        }        
      }
    );
  }

}
