import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, of} from 'rxjs';
import { catchError, debounceTime, distinctUntilChanged, map, tap, switchMap } from 'rxjs/operators';

import { TypeValueService } from '../../services/type-value.service';
import { StockService } from '../../services/stock.service';
import { TradeService } from '../../services/trade.service';
import { SharedService } from '../../shared/shared.service';

import * as models from '../../models/index';
import { Utility } from '../../utilities/global-utility';

@Component({
  selector: 'app-trade-add',
  templateUrl: './trade-add.component.html',
  styleUrls: ['./trade-add.component.scss']
})
export class TradeAddComponent implements OnInit {

  tradeForm: FormGroup;
  tradeTypeList: models.TypeValueModel[];
  exchangeList: models.TypeValueModel[];
  searching = false;
  searchFailed = false;
  tradeId: number = 0;
  tradeDetailsModel: any;

  formatter = (stock: models.Stock) => stock.stockCode;
  inputFormatter = (stock: models.Stock) => stock.stockCode;

  search = (text$: Observable<string>) =>
    text$.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      tap(() => this.searching = true),
      switchMap(term =>
        this.stockService.searchStocks(term).pipe(
          tap(() => this.searchFailed = false),
          catchError(() => {
            this.searchFailed = true;
            return of([]);
          }))
      ),
      tap(() => this.searching = false)
    );

  constructor(private fb : FormBuilder,  private router: Router, private route : ActivatedRoute,
              private typeValueService: TypeValueService, private stockService: StockService,
              private tradeService: TradeService, private sharedService: SharedService) { }
  
  ngOnInit() {
    this.creatForm();
    this.getTradeTypes();
    this.getExchanges();
    this.route.params.subscribe(params => {
      this.tradeId = params['id'];
      if(this.tradeId && this.tradeId > 0){
        this.getTradeDetails();
      }
    });
  }
  
  private getTradeTypes() {
    this.typeValueService.getTradeTypes().subscribe(data => {
      this.tradeTypeList = data;
    });    
  }

  private getExchanges() {
    this.typeValueService.getExchanges().subscribe(data => {
      this.exchangeList = data;
    });    
  }

  private getTradeDetails() {
    this.tradeService.getTradeDetails(this.tradeId).subscribe(res => {
      if(res.statusCode == 200){
        this.tradeDetailsModel = res.data;
        this.setTradeDetails();     
      } else {
        this.sharedService.showErrorMsg(res.message);
      }
    });
  }

  private setTradeDetails() {
    this.tradeForm.patchValue(this.tradeDetailsModel);
    this.tradeForm.controls['tradeDate'].setValue(Utility.getDatePickerDateFromDate(this.tradeDetailsModel.tradeDate));
    this.tradeForm.controls['stockCode'].setValue(this.tradeDetailsModel.stockName);
  }

  private creatForm() : void {
    this.tradeForm = this.fb.group({
      tradeId: ['0'],
      tradeTypeId: ['', Validators.required],
      stockId: [''],
      stockCode: ['', Validators.required],
      exchangeId: ['', Validators.required],     
      quantity: ['', Validators.required],
      stockPrice: ['', Validators.required],
      brokerage: ['', Validators.required],
      gst: ['', Validators.required],
      tradeDate: ['', Validators.required]
    });
  }

  get isFormValid() : boolean {
    return this.tradeForm.valid;
  }

  onSubmit() {
    if(!this.isFormValid) {
      return;
    }

    let payLoad = this.tradeForm.value;
    console.log(`payload:`, payLoad);
    payLoad.tradeDate = Utility.getDateFromDP(payLoad.tradeDate);

    this.saveTrade(payLoad);
  }

  onClear() {
    this.tradeForm.reset();
    if(this.tradeDetailsModel){
      this.setTradeDetails();
    }
  }

  onStockSelected(selectedItem : any) {
    this.tradeForm.controls['stockId'].setValue(selectedItem.item.stockId);    
  }

  private saveTrade(trade: any) {
    console.log('payload', trade);
    this.tradeService.saveTrade(trade).subscribe(res => {
      if(res.statusCode == 200){
        this.sharedService.showSuccessMsg('Trade details saved successfully');
        this.navigateToTrades();
      } else {
        this.sharedService.showErrorMsg(res.message);
      }
    });
  }

  navigateToTrades() {
    this.router.navigate(['/trades']);
  }
  
}
