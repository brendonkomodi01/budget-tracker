import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CategoryService } from '../../service/category';

@Component({
  selector: 'app-category-create',
  templateUrl: './category-create.html',
  standalone: false,
  styleUrl: './category-create.css'
})
export class CategoryCreate {

  name: string = '';
  errorMessage: string = '';

  constructor(private categoryService: CategoryService, private router: Router) {}

  onSubmit(): void {
    if (!this.name.trim()) {
      this.errorMessage = 'Category name is mandatory';
      return;
    }
    this.categoryService.create({ name: this.name }).subscribe({
      next: () => {
        this.router.navigate(['/category-list']);
      },
      error: (err) => {
        if (err.error && err.error.fieldErrors) {
          this.errorMessage = err.error.fieldErrors[0].message;
        }
      }
    });
  }
}
