package hu.progmasters.angularspringexamretakebudget.service;

import hu.progmasters.angularspringexamretakebudget.domain.Category;
import hu.progmasters.angularspringexamretakebudget.dto.incoming.CategoryCreateCommand;
import hu.progmasters.angularspringexamretakebudget.dto.outgoing.CategoryInfo;
import hu.progmasters.angularspringexamretakebudget.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    public CategoryInfo createCategory(CategoryCreateCommand command) {
        Category category = modelMapper.map(command, Category.class);
        Category saved = categoryRepository.save(category);
        log.info("Category is created");
        return modelMapper.map(saved, CategoryInfo.class);
    }

    public List<CategoryInfo> findAllCategories() {
        log.info("Category list page is requested");
        return categoryRepository.findAllByOrderByNameAsc()
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