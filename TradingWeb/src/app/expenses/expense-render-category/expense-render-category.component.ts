import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-expense-render-category',
  templateUrl: './expense-render-category.component.html',
  styleUrls: ['./expense-render-category.component.scss']
})
export class ExpenseRenderCategoryComponent implements OnInit, OnChanges {

  @Input() expenseCategory: any;
  categoryShortName: string;

  constructor() { }

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges) {
    if(changes){
      if(changes['expenseCategory']){
        this.categoryShortName = this.expenseCategory? this.expenseCategory.substr(0, 2) : 'Uk';
      }
    }
  }

}
