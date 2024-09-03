package com.yazilimciAkademisi.marketplace.dto.mapper;

import com.yazilimciAkademisi.marketplace.dto.request.CategoryRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.CategoryResponseDTO;
import com.yazilimciAkademisi.marketplace.entity.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public Category toEntity(CategoryRequestDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }

    public CategoryResponseDTO toResponseDTO(Category category) {
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setParentCategoryId(category.getParentCategory() != null ? category.getParentCategory().getId() : null);

        dto.setSubCategories(category.getSubCategories().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toSet()));
        return dto;
    }

    public List<CategoryResponseDTO> toCategoryResponseDTOList(List<Category> categoryList) {
        return categoryList.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
