import { Injectable, Injector } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { map } from 'rxjs/operators';

import { environment } from '../../environments/environment';
import * as models from '../models/index';

@Injectable()
export class StockService {

  private apiUrl : string = '';

  constructor(private httpClient : HttpClient) {
    this.apiUrl = environment.apiBasePath + '/trades/stocks/';
  }

  public getStocks() : Observable<models.Stock[]> {
    return this.httpClient.get<models.ApiResponse>(this.apiUrl + 'active')
    .pipe(map(res => {
      if(res.statusCode == 200) {
        return res.data;
      }
      return null;
    }));
  }

  public searchStocks(term: string) : Observable<models.Stock[]> {
    return this.httpClient.get<models.ApiResponse>(this.apiUrl + 'search/' + term)
    .pipe(map(res => {
      if(res.statusCode == 200) {
        return res.data;
      }
      return null;
    }));
  }
  
}
