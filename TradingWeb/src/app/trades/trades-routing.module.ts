import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { TradesComponent } from './trades/trades.component';
import { TradeListComponent } from './trade-list/trade-list.component';
import { TradeAddComponent } from './trade-add/trade-add.component';

const routes : Routes = [
  { path : '', 
    component : TradesComponent,
    children: [
      { 
        path: '', 
        component: TradeListComponent 
      },
      { 
        path: 'add', 
        component: TradeAddComponent 
      },
      { 
        path: 'edit/:id', 
        component: TradeAddComponent 
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
export class TradesRoutingModule { }
