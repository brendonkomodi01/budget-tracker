import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BalanceService {

  private apiUrl = 'https://budget-tracker-production-2b8e.up.railway.app/api/balance';

  constructor(private http: HttpClient) {}

  getBalance(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }

  setBalance(amount: number): Observable<any> {
    return this.http.put<any>(this.apiUrl, { amount });
  }
}
