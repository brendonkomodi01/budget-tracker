import { Component, OnInit } from '@angular/core';
import { BalanceService } from '../../service/balance';

@Component({
  selector: 'app-balance',
  templateUrl: './balance.html',
  standalone: false
})
export class BalanceComponent implements OnInit {

  currentBalance: number = 0;
  newBalance: number = 0;
  successMessage: string = '';

  constructor(private balanceService: BalanceService) {}

  ngOnInit(): void {
    this.balanceService.getBalance().subscribe({
      next: (data) => {
        this.currentBalance = data.amount;
        this.newBalance = data.amount;
      },
      error: (err) => console.error(err)
    });
  }

  save(): void {
    this.balanceService.setBalance(this.newBalance).subscribe({
      next: (data) => {
        this.currentBalance = data.amount;
        this.successMessage = 'Balance updated successfully!';
        setTimeout(() => this.successMessage = '', 3000);
      },
      error: (err) => console.error(err)
    });
  }
}
