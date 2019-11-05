import { Injectable, Injector } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { Subject } from 'rxjs/internal/Subject';
import { map } from 'rxjs/operators';

import { environment } from '../../environments/environment';
import * as models from '../models/index';


@Injectable()
export class ExpenseService {

  private apiUrl: string = '';

  lastSearchModel: any;

  constructor(private httpClient: HttpClient) {
    this.apiUrl = environment.apiBasePath + 'expenses/';
  }

  saveExpense(expense: any): Observable<models.ApiResponse> {
    return this.httpClient.post<models.ApiResponse>(this.apiUrl + 'save', expense)
      .pipe(map(res => res));
  }

  getExpenseDetails(expenseId: number) {
    return this.httpClient.get<models.ApiResponse>(this.apiUrl + expenseId)
      .pipe(map(res => res));
  }

  searchExpense(payload: any): Observable<models.ApiResponse> {
    this.lastSearchModel = payload;
    return this.httpClient.post<models.ApiResponse>(this.apiUrl + 'search', payload)
      .pipe(map(res => res));
  }

  deleteExpense(expenseId: number): Observable<models.ApiResponse> {
    return this.httpClient.post<models.ApiResponse>(this.apiUrl + expenseId + '/delete', null)
      .pipe(map(res => res));
  }

  expenseSummaryByTag(payload: any): Observable<models.ApiResponse> {
    return this.httpClient.post<models.ApiResponse>(this.apiUrl + 'summary-by-tag', payload)
      .pipe(map(res => res));
  }

  searchExpensesByTag(payload: any): Observable<models.ApiResponse> {
    return this.httpClient.post<models.ApiResponse>(this.apiUrl + 'find-by-tag', payload)
      .pipe(map(res => res));
  }

  private _beforeDelete$: Subject<any> = new Subject<any>();
  beforeDeleteExpense(expense: any) {
    this._beforeDelete$.next(expense);
  }
  get beforeDelete$() {
    return this._beforeDelete$;
  }

  private _afterDelete$: Subject<any> = new Subject<any>();
  afterDeleteExpense(expense: any) {
    this._afterDelete$.next(expense);
  }
  get afterDelete$() {
    return this._afterDelete$;
  }

  downloadTemplate() {
    return this.httpClient.get(this.apiUrl + 'download-template', { responseType: 'blob' });
  }

  uploadTemplate(payload: any) {
    return this.httpClient.post(this.apiUrl + 'upload', payload, { responseType: 'blob' });
  }

}