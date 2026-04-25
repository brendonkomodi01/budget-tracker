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
}
