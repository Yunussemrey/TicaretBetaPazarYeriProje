package com.yazilimciAkademisi.marketplace.dto;

import com.yazilimciAkademisi.marketplace.entity.Product;

import java.math.BigDecimal;

public class ProductDTO {
    private Integer id;
    private String productCode;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private Integer categoryId;
    private Integer brandId;

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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Product toEntity() {
        Product newProduct = new Product();
        newProduct.setName(this.getName());
        newProduct.setProductCode(this.getProductCode());
        newProduct.setDescription(this.getDescription());
        newProduct.setPrice(this.getPrice());
        newProduct.setStockQuantity(this.getStockQuantity());
        return newProduct;
    }

    public static ProductDTO fromEntity(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setProductCode(product.getProductCode());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());
        if (product.getBrand() != null) {
            dto.setBrandId(product.getBrand().getId());
        }
        if (product.getCategory() != null) {
            dto.setCategoryId(product.getCategory().getId());
        }
        return dto;
    }
}
