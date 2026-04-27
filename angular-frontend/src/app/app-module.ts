import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { Navbar } from './component/navbar/navbar';
import { CategoryCreate } from './component/category-create/category-create';
import { CategoryList } from './component/category-list/category-list';
import { ExpenseCreate } from './component/expense-create/expense-create';
import { ExpenseList } from './component/expense-list/expense-list';
import { Summary } from './component/summary/summary';
import { MonthlyExpenses } from './component/monthly-expenses/monthly-expenses';

@NgModule({
  declarations: [
    App,
    Navbar,
    CategoryCreate,
    CategoryList,
    ExpenseCreate,
    ExpenseList,
    Summary,
    MonthlyExpenses
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [App]
})
export class AppModule { }
