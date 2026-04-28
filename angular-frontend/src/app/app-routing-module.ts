import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CategoryCreate } from './component/category-create/category-create';
import { CategoryList } from './component/category-list/category-list';
import { ExpenseCreate } from './component/expense-create/expense-create';
import { ExpenseList } from './component/expense-list/expense-list';
import { Summary } from './component/summary/summary';
import { MonthlyExpenses } from './component/monthly-expenses/monthly-expenses';
import { LoginComponent } from './component/login/login';
import { AuthGuard } from './service/auth-guard';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'category-create', component: CategoryCreate, canActivate: [AuthGuard] },
  { path: 'category-list', component: CategoryList, canActivate: [AuthGuard] },
  { path: 'expense-create', component: ExpenseCreate, canActivate: [AuthGuard] },
  { path: 'expense-list', component: ExpenseList, canActivate: [AuthGuard] },
  { path: 'summary', component: Summary, canActivate: [AuthGuard] },
  { path: 'monthly-expenses', component: MonthlyExpenses, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
