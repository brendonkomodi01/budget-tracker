import { Component, OnInit } from '@angular/core';
import { ExpenseService } from '../../service/expense';

@Component({
  selector: 'app-expense-list',
  templateUrl: './expense-list.html',
  standalone: false,
  styleUrl: './expense-list.css'
})
export class ExpenseList implements OnInit {

  expenses: any[] = [];

  constructor(private expenseService: ExpenseService) {}

  ngOnInit(): void {
    this.expenseService.findAll().subscribe({
      next: (data) => {
        this.expenses = data;
      },
      error: (err) => {
        console.error(err);
      }
    });
  }
}
