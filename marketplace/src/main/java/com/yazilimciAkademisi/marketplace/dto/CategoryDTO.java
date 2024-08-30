package com.yazilimciAkademisi.marketplace.dto;

import com.yazilimciAkademisi.marketplace.entity.Category;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CategoryDTO {
    private Integer id;
    private String name;
    private Integer parentCategoryId;
    private Set<CategoryDTO> subCategories = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Integer parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public Set<CategoryDTO> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Set<CategoryDTO> subCategories) {
        this.subCategories = subCategories;
    }

    public Category toEntity(Category parentCategory) {
        Category category = new Category();
        category.setId(this.id);
        category.setName(this.name);
        category.setParentCategory(parentCategory);
        return category;
    }

    public static CategoryDTO fromEntity(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setParentCategoryId(category.getParentCategory() != null ? category.getParentCategory().getId() : null);

        Set<CategoryDTO> subCategoryDTOs = category.getSubCategories().stream()
                .map(CategoryDTO::fromEntity)
                .collect(Collectors.toSet());
        dto.setSubCategories(subCategoryDTOs);

        return dto;
    }
}
