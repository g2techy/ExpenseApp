import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-trade-search',
  templateUrl: './trade-search.component.html',
  styleUrls: ['./trade-search.component.scss']
})
export class TradeSearchComponent implements OnInit {

  searchForm : FormGroup;

  constructor(private fb : FormBuilder) { }

  ngOnInit() {
    this.creatForm();
  }

  private creatForm() : void {
    this.searchForm = this.fb.group({
      stockCode: [''],
      startDate: [''],
      endDate: ['']
    });
  }

  onClear() {
    this.searchForm.reset();
  }

  onSubmit() {

  }
}
