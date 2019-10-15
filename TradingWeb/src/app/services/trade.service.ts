import { Injectable, Injector } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { map } from 'rxjs/operators';

import { environment } from '../../environments/environment';
import * as models from '../models/index';

@Injectable()
export class TradeService {

  private apiUrl : string = '';

  constructor(private httpClient : HttpClient) {
    this.apiUrl = environment.apiBasePath + 'trades/';
  }

  private getTrades(): Observable<models.TypeValueModel[]> {
    return this.httpClient.get<models.TypeValueModel[]>(this.apiUrl)
      .pipe(map(res => res));
  }

  saveTrade(trade : any): Observable<models.ApiResponse> {
    return this.httpClient.post<models.ApiResponse>(this.apiUrl + 'save', trade)
      .pipe(map(res => res));
  }

  getTradeDetails(tradeId: number) {
    return this.httpClient.get<models.ApiResponse>(this.apiUrl + tradeId)
      .pipe(map(res => res));
  }
  
}
