package com.yazilimciAkademisi.marketplace.service;

import com.yazilimciAkademisi.marketplace.dto.mapper.CategoryMapper;
import com.yazilimciAkademisi.marketplace.dto.request.CategoryRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.CategoryResponseDTO;
import com.yazilimciAkademisi.marketplace.entity.Category;
import com.yazilimciAkademisi.marketplace.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryResponseDTO> getAllCategoryDTOs() {
        return categoryMapper.toCategoryResponseDTOList(getAllCategories());
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<CategoryResponseDTO> getCategoryResponseDTOById(Integer id) {
        Optional<Category> categoryOptional = getCategoryById(id);
        if (categoryOptional.isPresent()) {
            CategoryResponseDTO dto = categoryMapper.toResponseDTO(categoryOptional.get());
            return Optional.of(dto);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Category> getCategoryById(Integer id) {
        return categoryRepository.findById(id);
    }

    public CategoryResponseDTO saveCategory(CategoryRequestDTO categoryRequestDTO) {
        Category category = categoryMapper.toEntity(categoryRequestDTO);
        Category updatedCategory = updateParentCategory(category, categoryRequestDTO.getParentCategoryId());
        Category savedCategory = categoryRepository.save(updatedCategory);
        return categoryMapper.toResponseDTO(savedCategory);
    }

    // Update Category
    public CategoryResponseDTO updateCategory(Integer id, CategoryRequestDTO categoryRequestDTO) {
        Optional<Category> existingCategoryOptional = getCategoryById(id);
        if (existingCategoryOptional.isPresent()) {
            Category existingCategory = existingCategoryOptional.get();
            // Update the existing category properties
            existingCategory.setName(categoryRequestDTO.getName());
            // Update parent category
            Category updatedCategory = updateParentCategory(existingCategory, categoryRequestDTO.getParentCategoryId());
            categoryRepository.save(updatedCategory);
            return categoryMapper.toResponseDTO(updatedCategory);
        } else {
            throw new IllegalArgumentException("Category with ID " + id + " does not exist.");
        }
    }

    public void deleteCategory(Integer id) {
        if (!categoryRepository.existsById(id)) {
            throw new IllegalArgumentException("Category with ID " + id + " does not exist.");
        }
        categoryRepository.deleteById(id);
    }

    // Update Parent Category
    private Category updateParentCategory(Category category,Integer newParentCategoryId) {
        // Category cannot be its own parent
        if (newParentCategoryId != null && newParentCategoryId.equals(category.getId())) {
            throw new IllegalArgumentException("A category cannot be its own parent.");
        }
        // Remove from the old parent category's subcategories
        if (category.getParentCategory() != null) {
            Category oldParentCategory = category.getParentCategory();
            oldParentCategory.getSubCategories().remove(category);
            categoryRepository.save(oldParentCategory);
        }
        // Update new parent category
        if (newParentCategoryId != null) {
            Optional<Category> newParentCategoryOptional = getCategoryById(newParentCategoryId);
            if (newParentCategoryOptional.isPresent()) {
                Category newParentCategory = newParentCategoryOptional.get();
                category.setParentCategory(newParentCategory);
                newParentCategory.getSubCategories().add(category);
                categoryRepository.save(newParentCategory);
            } else {
                category.setParentCategory(null);
            }
        } else {
            category.setParentCategory(null);
        }
        return category;
    }

}
