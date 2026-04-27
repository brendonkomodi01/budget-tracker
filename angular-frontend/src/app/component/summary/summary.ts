import { Component, OnInit, AfterViewInit, ElementRef, ViewChild } from '@angular/core';
import { ExpenseService } from '../../service/expense';
import { Chart, registerables } from 'chart.js';

Chart.register(...registerables);

@Component({
  selector: 'app-summary',
  templateUrl: './summary.html',
  standalone: false,
  styleUrl: './summary.css'
})
export class Summary implements OnInit, AfterViewInit {

  summaryList: any[] = [];

  @ViewChild('myChart') myChart!: ElementRef;

  constructor(private expenseService: ExpenseService) {}

  ngOnInit(): void {
    this.expenseService.getSummary().subscribe({
      next: (data) => {
        this.summaryList = data;
        setTimeout(() => this.createChart(), 100);
      },
      error: (err) => {
        console.error(err);
      }
    });
  }

  ngAfterViewInit(): void {}

  createChart(): void {
    new Chart(this.myChart.nativeElement, {
      type: 'pie',
      data: {
        labels: this.summaryList.map(item => item.categoryName),
        datasets: [{
          data: this.summaryList.map(item => item.sumAmount),
          backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0', '#9966FF']
        }]
      }
    });
  }
}
