import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DashboardRoutingModule } from "./dashboard-routing.module";

import { DashboardComponent } from './dashboard/dashboard.component';
import { DashboardWidgetsComponent } from './dashboard-widgets/dashboard-widgets.component';

@NgModule({
  declarations: [ DashboardComponent, DashboardWidgetsComponent ],
  imports: [
    CommonModule,
    DashboardRoutingModule
  ]
})
export class DashboardModule { }
