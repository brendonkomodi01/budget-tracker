import { Component, OnInit } from '@angular/core';
import { ExpenseService } from '../../service/expense';

@Component({
  selector: 'app-monthly-expenses',
  templateUrl: './monthly-expenses.html',
  standalone: false,
  styleUrl: './monthly-expenses.css'
})
export class MonthlyExpenses implements OnInit {

  monthlyExpenses: any[] = [];

  constructor(private expenseService: ExpenseService) {}

  ngOnInit(): void {
    this.expenseService.getMonthlyExpenses().subscribe({
      next: (data) => {
        this.monthlyExpenses = data;
      },
      error: (err) => {
        console.error(err);
      }
    });
  }

  getMonthName(month: number): string {
    const months = ['January', 'February', 'March', 'April', 'May', 'June',
      'July', 'August', 'September', 'October', 'November', 'December'];
    return months[month - 1];
  }
}
