package com.yazilimciAkademisi.marketplace.dto.response;

import java.util.Set;

public class CategoryResponseDTO {
    private Integer id;
    private String name;
    private Integer parentCategoryId;
    private Set<CategoryResponseDTO> subCategories;

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

    public Set<CategoryResponseDTO> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Set<CategoryResponseDTO> subCategories) {
        this.subCategories = subCategories;
    }

}
