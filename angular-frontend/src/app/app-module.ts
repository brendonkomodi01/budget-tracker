import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { Navbar } from './component/navbar/navbar';
import { CategoryCreate } from './component/category-create/category-create';
import { CategoryList } from './component/category-list/category-list';
import { ExpenseCreate } from './component/expense-create/expense-create';
import { ExpenseList } from './component/expense-list/expense-list';
import { Summary } from './component/summary/summary';
import { MonthlyExpenses } from './component/monthly-expenses/monthly-expenses';
import { LoginComponent } from './component/login/login';
import { AuthInterceptor } from './service/auth-interceptor';
import { BalanceComponent } from './component/balance/balance';

@NgModule({
  declarations: [
    App,
    Navbar,
    CategoryCreate,
    CategoryList,
    ExpenseCreate,
    ExpenseList,
    Summary,
    MonthlyExpenses,
    LoginComponent,
    BalanceComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ],
  bootstrap: [App]
})
export class AppModule { }
