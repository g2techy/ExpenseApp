import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-expense-render-category',
  templateUrl: './expense-render-category.component.html',
  styleUrls: ['./expense-render-category.component.scss']
})
export class ExpenseRenderCategoryComponent implements OnInit, OnChanges {

  @Input() expenseCategory: any;
  categoryShortName: string;
  categoryName: string;

  private categoryIconMap = {
    Activa: 'motorcycle',
    Bills: 'file-contract',
    Education: 'book',
    EMI: 'info',
    Entertainment: 'film',
    Flat: 'home',
    'Food & Drinks': 'food',
    Groceries: 'shopping-basket',
    Health: 'medkit',
    Investment: 'investment',
    Medical: 'medkit',
    Office: 'briefcase',
    Other: 'random',
    Rent: 'rent',
    Shopping: 'shopping-cart',
    Transfer: 'transfer',
    Travel: 'plane',
  };

  constructor() { }

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges) {
    if(changes){
      if(changes['expenseCategory']){
        this.categoryShortName = this.expenseCategory? this.expenseCategory.substr(0, 2) : 'Uk';
        this.categoryName = this.expenseCategory;
      }
    }
  }

  getCategoryIcon(categoryName: any) {
    return this.categoryIconMap[categoryName] ? this.categoryIconMap[categoryName] : categoryName.substr(0, 2);  
  }

}
