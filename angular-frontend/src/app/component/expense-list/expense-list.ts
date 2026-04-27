import { Component, OnInit } from '@angular/core';
import { ExpenseService } from '../../service/expense';
import { CategoryService } from '../../service/category';

@Component({
  selector: 'app-expense-list',
  templateUrl: './expense-list.html',
  standalone: false,
  styleUrl: './expense-list.css'
})
export class ExpenseList implements OnInit {

  expenses: any[] = [];
  categories: any[] = [];
  totalAmount: number = 0;
  selectedExpense: any = null;

  constructor(
    private expenseService: ExpenseService,
    private categoryService: CategoryService
  ) {}

  ngOnInit(): void {
    this.loadExpenses();
    this.categoryService.findAll().subscribe({
      next: (data) => {
        this.categories = data;
      }
    });
  }

  loadExpenses(): void {
    this.expenseService.findAll().subscribe({
      next: (data) => {
        this.expenses = data;
        this.totalAmount = data.reduce((sum, expense) => sum + expense.amount, 0);
      },
      error: (err) => {
        console.error(err);
      }
    });
  }

  deleteExpense(id: number): void {
    this.expenseService.delete(id).subscribe({
      next: () => {
        this.expenses = this.expenses.filter(expense => expense.id !== id);
        this.totalAmount = this.expenses.reduce((sum, expense) => sum + expense.amount, 0);
      },
      error: (err) => {
        console.error(err);
      }
    });
  }

  editExpense(expense: any): void {
    this.selectedExpense = { ...expense, categoryId: expense.categoryId };
  }

  updateExpense(): void {
    this.expenseService.update(this.selectedExpense.id, {
      amount: this.selectedExpense.amount,
      expenseDate: this.selectedExpense.expenseDate,
      categoryId: this.selectedExpense.categoryId,
      description: this.selectedExpense.description
    }).subscribe({
      next: () => {
        this.selectedExpense = null;
        this.loadExpenses();
      },
      error: (err) => {
        console.error(err);
      }
    });
  }

  cancelEdit(): void {
    this.selectedExpense = null;
  }

  selectedCategoryFilter: string = '';

  get filteredExpenses(): any[] {
    if (!this.selectedCategoryFilter) {
      return this.expenses;
    }
    return this.expenses.filter(expense => expense.categoryName === this.selectedCategoryFilter);
  }
}
