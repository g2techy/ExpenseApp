import { NgModule } from '@angular/core';
import { CommonModule, TitleCasePipe } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from "@angular/router";

import { NgbModule, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { HeaderComponent } from './layout/header/header.component';
import { SidebarComponent } from './layout/sidebar/sidebar.component';
import { FooterComponent } from './layout/footer/footer.component';
import { BreadCrumbComponent } from './bread-crumb/bread-crumb.component';
import { AlertComponent } from './alert/alert.component';
import { ConfirmModalComponent } from './confirm-modal/confirm-modal.component';

@NgModule({
  declarations: [ HeaderComponent, SidebarComponent, FooterComponent, BreadCrumbComponent, AlertComponent, ConfirmModalComponent ],
  imports: [
    CommonModule,
    HttpClientModule,
    RouterModule,
    NgbModule
  ],
  exports: [ NgbModule, HeaderComponent, SidebarComponent, FooterComponent, BreadCrumbComponent, AlertComponent, ConfirmModalComponent ],
  providers: [ NgbActiveModal ]
})
export class SharedModule { }
