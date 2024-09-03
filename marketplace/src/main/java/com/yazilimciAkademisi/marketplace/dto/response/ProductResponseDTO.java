package com.yazilimciAkademisi.marketplace.dto.response;

import java.math.BigDecimal;

public class ProductResponseDTO {

    private Integer id;
    private String productCode;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private CategoryResponseDTO category;
    private BrandResponseDTO brand;
    private StoreResponseDTO store;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public CategoryResponseDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryResponseDTO category) {
        this.category = category;
    }

    public BrandResponseDTO getBrand() {
        return brand;
    }

    public void setBrand(BrandResponseDTO brand) {
        this.brand = brand;
    }

    public StoreResponseDTO getStore() {
        return store;
    }

    public void setStore(StoreResponseDTO store) {
        this.store = store;
    }
}
