import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DashboardComponent } from './dashboard/dashboard.component';
import { DashboardWidgetsComponent } from './dashboard-widgets/dashboard-widgets.component';

const routes : Routes = [
  { path: '', 
    component: DashboardComponent,
    children: [
      {
        path: '',
        component: DashboardWidgetsComponent
      }
    ]
  },
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)  
  ],
  exports: [ RouterModule ]
})
export class DashboardRoutingModule { }
