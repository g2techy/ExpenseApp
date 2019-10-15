import { Injectable, Injector } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { map } from 'rxjs/operators';

import { environment } from '../../environments/environment';
import * as models from '../models/index';

@Injectable()
export class TypeValueService {

  private apiUrl : string = '';

  constructor(private httpClient : HttpClient) {
    this.apiUrl = environment.apiBasePath + 'type-values/';
  }

  getTypeValues(typeID: number) : Observable<models.TypeValueModel[]> {
    return this.httpClient.get<models.ApiResponse>(`${this.apiUrl}${typeID}`)
      .pipe(map(res => {
        if(res.statusCode == 200) {
          return res.data;
        }
        return null;
      }));
  }

  getTradeTypes() : Observable<models.TypeValueModel[]> {
    return this.getTypeValues(models.TypeIDEnum.tradeType);
  }

  getExchanges() : Observable<models.TypeValueModel[]> {
    return this.getTypeValues(models.TypeIDEnum.exchangeType);
  }

}
