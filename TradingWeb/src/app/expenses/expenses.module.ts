import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from "@angular/forms";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { InrCurrencyPipe } from '../pipes/inr-currency.pipe';

import { ExpensesRoutingModule } from './expenses-routing.module';
import { ExpensesComponent } from './expenses/expenses.component';
import { ExpenseListComponent } from './expense-list/expense-list.component';
import { ExpenseAddComponent } from './expense-add/expense-add.component';
import { ExpenseSearchComponent } from './expense-search/expense-search.component';
import { ExpenseCategoriesComponent } from './expense-categories/expense-categories.component';
import { ExpenseTagListComponent } from './expense-tag-list/expense-tag-list.component';
import { ExpenseRenderTagsComponent } from './expense-render-tags/expense-render-tags.component';
import { ExpenseRenderCategoryComponent } from './expense-render-category/expense-render-category.component';
import { ExpenseTagsComponent } from './expense-tags/expense-tags.component';
import { ExpenseFundSourcesComponent } from './expense-fund-sources/expense-fund-sources.component';

@NgModule({
  declarations: [InrCurrencyPipe, ExpensesComponent, ExpenseListComponent, ExpenseAddComponent, ExpenseSearchComponent, ExpenseCategoriesComponent, ExpenseTagListComponent, ExpenseRenderTagsComponent, ExpenseRenderCategoryComponent, ExpenseTagsComponent, ExpenseFundSourcesComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    NgbModule,
    ExpensesRoutingModule
  ]
})
export class ExpensesModule { }
