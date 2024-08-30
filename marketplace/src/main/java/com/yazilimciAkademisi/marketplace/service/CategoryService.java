package com.yazilimciAkademisi.marketplace.service;

import com.yazilimciAkademisi.marketplace.dto.CategoryDTO;
import com.yazilimciAkademisi.marketplace.entity.Category;
import com.yazilimciAkademisi.marketplace.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Integer id) {
        return categoryRepository.findById(id);
    }

    public Category saveCategory(CategoryDTO categoryDTO) {
        Category parentCategory = null;
        if (categoryDTO.getParentCategoryId() != null) {
            parentCategory = categoryRepository.findById(categoryDTO.getParentCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid parent category ID"));
        }

        Category category = categoryDTO.toEntity(parentCategory);

        if (!categoryDTO.getSubCategories().isEmpty()) {
            Set<Category> subCategories = categoryDTO.getSubCategories().stream()
                    .map(dto -> dto.toEntity(category))
                    .collect(Collectors.toSet());
            category.setSubCategories(subCategories);
        }

        return categoryRepository.save(category);
    }

    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }

}
