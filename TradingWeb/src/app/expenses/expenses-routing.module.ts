import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ExpensesComponent } from './expenses/expenses.component';
import { ExpenseListComponent } from './expense-list/expense-list.component';
import { ExpenseAddComponent } from './expense-add/expense-add.component';
import { ExpenseSearchComponent } from './expense-search/expense-search.component';
import { ExpenseSearchCommonComponent } from './expense-search/expense-search-common/expense-search-common.component';

const routes : Routes = [
  { path : '', 
    component : ExpensesComponent,
    children: [
      { 
        path: '', 
        component: ExpenseSearchComponent,
        children: [
          { path: '', redirectTo: 'recent', pathMatch : 'full' },
          { 
            path: 'recent',
            component: ExpenseSearchCommonComponent,
            data: {searchType: 'cm'}
          },
          { 
            path: 'last-3-months',
            component: ExpenseSearchCommonComponent,
            data: {searchType: '3m'}
          },
          { 
            path: 'last-6-months',
            component: ExpenseSearchCommonComponent,
            data: {searchType: '6m'}
          },
          { 
            path: 'ytd',
            component: ExpenseSearchCommonComponent,
            data: {searchType: 'ytd'}
          },
          { 
            path: 'search',
            component: ExpenseSearchCommonComponent,
            data: {searchType: 'cs'}
          }
        ] 
      },
      { 
        path: 'add', 
        component: ExpenseAddComponent 
      },
      { 
        path: 'edit/:name/:id', 
        component: ExpenseAddComponent 
      }
    ]
  },
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)  
  ],
  exports: [ RouterModule ]
})
export class ExpensesRoutingModule { }
