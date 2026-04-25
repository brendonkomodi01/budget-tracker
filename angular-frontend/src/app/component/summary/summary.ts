import { Component, OnInit } from '@angular/core';
import { ExpenseService } from '../../service/expense';

@Component({
  selector: 'app-summary',
  templateUrl: './summary.html',
  standalone: false,
  styleUrl: './summary.css'
})
export class Summary implements OnInit {

  summaryList: any[] = [];

  constructor(private expenseService: ExpenseService) {}

  ngOnInit(): void {
    this.expenseService.getSummary().subscribe({
      next: (data) => {
        this.summaryList = data;
      },
      error: (err) => {
        console.error(err);
      }
    });
  }
}
