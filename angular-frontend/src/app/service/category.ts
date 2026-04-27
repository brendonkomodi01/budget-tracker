import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private apiUrl = 'https://budget-tracker-production-2b8e.up.railway.app/api/categories';

  constructor(private http: HttpClient) {}

  findAll(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  create(category: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, category);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
