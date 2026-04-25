import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CategoryCreate } from './component/category-create/category-create';
import { CategoryList } from './component/category-list/category-list';
import { ExpenseCreate } from './component/expense-create/expense-create';
import { ExpenseList } from './component/expense-list/expense-list';
import { Summary } from './component/summary/summary';

const routes: Routes = [
  { path: '', redirectTo: '/category-list', pathMatch: 'full' },
  { path: 'category-create', component: CategoryCreate },
  { path: 'category-list', component: CategoryList },
  { path: 'expense-create', component: ExpenseCreate },
  { path: 'expense-list', component: ExpenseList },
  { path: 'summary', component: Summary }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
