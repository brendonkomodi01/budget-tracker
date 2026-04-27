import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../../service/category';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.html',
  standalone: false,
  styleUrl: './category-list.css'
})
export class CategoryList implements OnInit {

  categories: any[] = [];

  constructor(private categoryService: CategoryService) {}

  ngOnInit(): void {
    this.categoryService.findAll().subscribe({
      next: (data) => {
        this.categories = data;
      },
      error: (err) => {
        console.error(err);
      }
    });
  }

  deleteCategory(id: number): void {
    this.categoryService.delete(id).subscribe({
      next: () => {
        this.categories = this.categories.filter(category => category.id !== id);
      },
      error: (err) => {
        console.error(err);
      }
    });
  }
}
