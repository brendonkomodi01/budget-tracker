package hu.brendonkomodi.budgettracker.controller;

import hu.brendonkomodi.budgettracker.dto.incoming.CategoryCreateCommand;
import hu.brendonkomodi.budgettracker.dto.outgoing.CategoryInfo;
import hu.brendonkomodi.budgettracker.service.CategoryService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryInfo createCategory(@Valid @RequestBody CategoryCreateCommand command) {
        return categoryService.createCategory(command);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryInfo> findAllCategories() {
        return categoryService.findAllCategories();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}