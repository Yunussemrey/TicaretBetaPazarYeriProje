package com.yazilimciAkademisi.marketplace.service;

import com.yazilimciAkademisi.marketplace.dto.ProductDTO;
import com.yazilimciAkademisi.marketplace.entity.Brand;
import com.yazilimciAkademisi.marketplace.entity.Category;
import com.yazilimciAkademisi.marketplace.entity.Product;
import com.yazilimciAkademisi.marketplace.repository.BrandRepository;
import com.yazilimciAkademisi.marketplace.repository.CategoryRepository;
import com.yazilimciAkademisi.marketplace.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, BrandRepository brandRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
    }

    public List<ProductDTO> getAllProductDTOs() {
        return productRepository.findAll().stream()
                .map(ProductDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<ProductDTO> getProductDTOById(Integer id) {
        return productRepository.findById(id)
                .map(ProductDTO::fromEntity);
    }

    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    public ProductDTO saveProduct(ProductDTO productDTO) {

        Product product = productDTO.toEntity();

        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID: " + productDTO.getCategoryId()));
        Brand brand = brandRepository.findById(productDTO.getBrandId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid brand ID: " + productDTO.getBrandId()));

        product.setCategory(category);
        product.setBrand(brand);
        Product savedProduct = productRepository.save(product);
        return ProductDTO.fromEntity(savedProduct);
    }

    public ProductDTO updateProduct(ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(productDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));
        existingProduct.setProductCode(productDTO.getProductCode());
        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setStockQuantity(productDTO.getStockQuantity());
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID: " + productDTO.getCategoryId()));
        Brand brand = brandRepository.findById(productDTO.getBrandId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid brand ID: " + productDTO.getBrandId()));
        existingProduct.setCategory(category);
        existingProduct.setBrand(brand);
        Product updatedProduct = productRepository.save(existingProduct);
        return ProductDTO.fromEntity(updatedProduct);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
}
