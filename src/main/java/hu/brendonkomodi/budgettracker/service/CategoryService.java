package hu.brendonkomodi.budgettracker.service;

import hu.brendonkomodi.budgettracker.domain.AppUser;
import hu.brendonkomodi.budgettracker.domain.Category;
import hu.brendonkomodi.budgettracker.dto.incoming.CategoryCreateCommand;
import hu.brendonkomodi.budgettracker.dto.outgoing.CategoryInfo;
import hu.brendonkomodi.budgettracker.repository.AppUserRepository;
import hu.brendonkomodi.budgettracker.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final AppUserRepository appUserRepository;
    private final ModelMapper modelMapper;

    public CategoryService(CategoryRepository categoryRepository, AppUserRepository appUserRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.appUserRepository = appUserRepository;
        this.modelMapper = modelMapper;
    }

    private AppUser getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return appUserRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public CategoryInfo createCategory(CategoryCreateCommand command) {
        Category category = modelMapper.map(command, Category.class);
        category.setUser(getCurrentUser());
        Category saved = categoryRepository.save(category);
        log.info("Category is created");
        return modelMapper.map(saved, CategoryInfo.class);
    }

    public List<CategoryInfo> findAllCategories() {
        log.info("Category list page is requested");
        AppUser user = getCurrentUser();
        return categoryRepository.findAllByUserOrderByNameAsc(user)
                .stream()
                .map(category -> modelMapper.map(category, CategoryInfo.class))
                .collect(Collectors.toList());
    }

    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + id));
        categoryRepository.delete(category);
        log.info("Category is deleted");
    }
}