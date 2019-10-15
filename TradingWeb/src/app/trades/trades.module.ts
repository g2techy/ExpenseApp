import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from "@angular/forms";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { TradesRoutingModule } from './trades-routing.module';

import { TradesComponent } from './trades/trades.component';
import { TradeListComponent } from './trade-list/trade-list.component';
import { TradeAddComponent } from './trade-add/trade-add.component';
import { TradeSearchComponent } from './trade-search/trade-search.component';

import { SharedModule } from "../shared/shared.module";

@NgModule({
  declarations: [TradesComponent, TradeListComponent, TradeAddComponent, TradeSearchComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    NgbModule,
    TradesRoutingModule,
    SharedModule
  ]
})
export class TradesModule { }
