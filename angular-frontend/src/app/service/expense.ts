import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ExpenseService {

  private apiUrl = 'https://budget-tracker-production-2b8e.up.railway.app/api/expenses';

  constructor(private http: HttpClient) {}

  findAll(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  create(expense: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, expense);
  }

  getSummary(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/summary`);
  }

  getMonthlyExpenses(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/monthly`);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  update(id: number, expense: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${id}`, expense);
  }
}
