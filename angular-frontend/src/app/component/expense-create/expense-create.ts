import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../../service/category';
import { ExpenseService } from '../../service/expense';

@Component({
  selector: 'app-expense-create',
  templateUrl: './expense-create.html',
  standalone: false,
  styleUrl: './expense-create.css'
})
export class ExpenseCreate implements OnInit {

  amount: number | null = null;
  expenseDate: string = '';
  categoryId: number | null = null;
  description: string = '';
  categories: any[] = [];
  successMessage: string = '';
  fieldErrors: any = {};

  constructor(
    private categoryService: CategoryService,
    private expenseService: ExpenseService
  ) {
  }

  ngOnInit(): void {
    this.categoryService.findAll().subscribe({
      next: (data) => {
        this.categories = data;
      },
      error: (err) => {
        console.error(err);
      }
    });
  }

  onSubmit(): void {
    this.fieldErrors = {};
    this.successMessage = '';

    const expense = {
      amount: this.amount,
      expenseDate: this.expenseDate,
      categoryId: this.categoryId,
      description: this.description
    };

    this.expenseService.create(expense).subscribe({
      next: () => {
        this.successMessage = 'Expense successfully saved!';
        this.fieldErrors = {};
        this.amount = null;
        this.expenseDate = '';
        this.categoryId = null;
        this.description = '';
      },
      error: (err) => {
        this.successMessage = '';
        if (err.error && err.error.fieldErrors) {
          err.error.fieldErrors.forEach((e: any) => {
            this.fieldErrors[e.field] = e.message;
          });
        }
      }
    });
  }
}
