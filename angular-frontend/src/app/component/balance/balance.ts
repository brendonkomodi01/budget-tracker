import { Component, OnInit } from '@angular/core';
import { BalanceService } from '../../service/balance';

@Component({
  selector: 'app-balance',
  templateUrl: './balance.html',
  standalone: false
})
export class BalanceComponent implements OnInit {

  balances: any[] = [];
  selectedYear: number = new Date().getFullYear();
  selectedMonth: number = new Date().getMonth() + 1;
  newAmount: number | null = null;
  successMessage: string = '';

  years: number[] = [2023, 2024, 2025, 2026];
  months: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];

  constructor(private balanceService: BalanceService) {}

  ngOnInit(): void {
    this.loadBalances();
  }

  loadBalances(): void {
    this.balanceService.getBalance().subscribe({
      next: (data) => this.balances = data,
      error: (err) => console.error(err)
    });
  }

  save(): void {
    this.balanceService.setBalance(this.selectedYear, this.selectedMonth, this.newAmount ?? 0).subscribe({
      next: () => {
        this.successMessage = 'Balance saved successfully!';
        this.loadBalances();
        setTimeout(() => this.successMessage = '', 3000);
      },
      error: (err) => console.error(err)
    });
  }
}
