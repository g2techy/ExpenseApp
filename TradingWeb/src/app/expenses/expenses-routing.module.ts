import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ExpensesComponent } from './expenses/expenses.component';
import { ExpenseListComponent } from './expense-list/expense-list.component';
import { ExpenseAddComponent } from './expense-add/expense-add.component';
import { ExpenseSearchComponent } from './expense-search/expense-search.component';

const routes : Routes = [
  { path : '', 
    component : ExpensesComponent,
    children: [
      { 
        path: '', 
        component: ExpenseSearchComponent 
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
