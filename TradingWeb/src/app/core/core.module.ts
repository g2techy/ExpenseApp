import { NgModule, ModuleWithProviders, Optional, SkipSelf } from '@angular/core';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';

import { TypeValueService } from '../services/type-value.service';
import { StockService } from '../services/stock.service';
import { TradeService } from '../services/trade.service';
import { AlertService } from '../services/alert.service';
import { ConfirmService } from '../services/confirm.service';
import { ExpenseService } from '../services/expense.service';

@NgModule({
  imports : [ HttpClientModule ]
})
export class CoreModule { 

  constructor(@Optional() @SkipSelf() parentModule: CoreModule){
    if (parentModule) {
      throw new Error('CoreModule is already loaded. Import it in the AppModule only');
    }
  }

  static forRoot () : ModuleWithProviders {
    return {
      ngModule: CoreModule,
      providers: [ AlertService, ConfirmService, TypeValueService, StockService, TradeService, ExpenseService ]
    }
  }
}
