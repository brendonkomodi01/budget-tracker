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

  allExpenses: any[] = [];
  summaryList: any[] = [];
  availableYears: number[] = [];
  availableMonths: number[] = [];
  selectedYear: number | null = null;
  selectedMonth: number | null = null;
  chart: any;

  @ViewChild('myChart') myChart!: ElementRef;

  constructor(private expenseService: ExpenseService) {}

  ngOnInit(): void {
    this.expenseService.findAll().subscribe({
      next: (data) => {
        this.allExpenses = data;
        this.availableYears = [...new Set(data.map((e: any) => new Date(e.expenseDate).getFullYear()))].sort();
        this.applyFilter();
      },
      error: (err) => console.error(err)
    });
  }

  ngAfterViewInit(): void {}

  onYearChange(): void {
    this.selectedMonth = null;
    if (this.selectedYear) {
      this.availableMonths = [...new Set(
        this.allExpenses
          .filter((e: any) => new Date(e.expenseDate).getFullYear() === this.selectedYear)
          .map((e: any) => new Date(e.expenseDate).getMonth() + 1)
      )].sort((a, b) => a - b);
    } else {
      this.availableMonths = [];
    }
    this.applyFilter();
  }

  onMonthChange(): void {
    this.applyFilter();
  }

  applyFilter(): void {
    let filtered = this.allExpenses;
    if (this.selectedYear) {
      filtered = filtered.filter((e: any) => new Date(e.expenseDate).getFullYear() === this.selectedYear);
    }
    if (this.selectedMonth) {
      filtered = filtered.filter((e: any) => new Date(e.expenseDate).getMonth() + 1 === this.selectedMonth);
    }
    const summaryMap: { [key: string]: number } = {};
    filtered.forEach((e: any) => {
      summaryMap[e.categoryName] = (summaryMap[e.categoryName] || 0) + e.amount;
    });
    this.summaryList = Object.entries(summaryMap).map(([categoryName, sumAmount]) => ({ categoryName, sumAmount }));
    setTimeout(() => this.createChart(), 100);
  }

  createChart(): void {
    if (this.chart) {
      this.chart.destroy();
    }
    this.chart = new Chart(this.myChart.nativeElement, {
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
