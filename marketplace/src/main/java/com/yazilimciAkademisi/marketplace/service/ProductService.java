package com.yazilimciAkademisi.marketplace.service;

import com.yazilimciAkademisi.marketplace.dto.mapper.ProductMapper;
import com.yazilimciAkademisi.marketplace.dto.request.ProductRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.ProductResponseDTO;
import com.yazilimciAkademisi.marketplace.entity.Brand;
import com.yazilimciAkademisi.marketplace.entity.Category;
import com.yazilimciAkademisi.marketplace.entity.Product;
import com.yazilimciAkademisi.marketplace.entity.Store;
import com.yazilimciAkademisi.marketplace.repository.BrandRepository;
import com.yazilimciAkademisi.marketplace.repository.CategoryRepository;
import com.yazilimciAkademisi.marketplace.repository.ProductRepository;
import com.yazilimciAkademisi.marketplace.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final StoreRepository storeRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper,
                          CategoryRepository categoryRepository, BrandRepository brandRepository,
                          StoreRepository storeRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.storeRepository = storeRepository;
    }

    public List<ProductResponseDTO> getAllProductDTOs() {
        return productMapper.toProductResponseDTOList(getAllProducts());
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<ProductResponseDTO> getProductResponseDTOById(Integer id) {
        Optional<Product> productOptional = getProductById(id);
        if (productOptional.isPresent()) {
            ProductResponseDTO dto = productMapper.toResponseDTO(productOptional.get());
            return Optional.of(dto);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    public ProductResponseDTO saveProduct(ProductRequestDTO productRequestDTO) {
        // Check if Category exists
        Optional<Category> categoryOptional = categoryRepository.findById(productRequestDTO.getCategoryId());
        if (categoryOptional.isEmpty()) {
            throw new IllegalArgumentException("Category with ID " + productRequestDTO.getCategoryId() + " does not exist.");
        }

        // Check if Brand exists
        Optional<Brand> brandOptional = brandRepository.findById(productRequestDTO.getBrandId());
        if (brandOptional.isEmpty()) {
            throw new IllegalArgumentException("Brand with ID " + productRequestDTO.getBrandId() + " does not exist.");
        }

        // Check if Store exists
        Optional<Store> storeOptional = storeRepository.findById(productRequestDTO.getStoreId());
        if (storeOptional.isEmpty()) {
            throw new IllegalArgumentException("Store with ID " + productRequestDTO.getStoreId() + " does not exist.");
        }

        // Create Product entity
        Product product = productMapper.toEntity(productRequestDTO);

        // Set Category, Brand, Store
        product.setCategory(categoryOptional.get());
        product.setBrand(brandOptional.get());
        product.setStore(storeOptional.get());

        // Save Product
        Product savedProduct = productRepository.save(product);
        return productMapper.toResponseDTO(savedProduct);
    }

    public ProductResponseDTO updateProduct(Integer id, ProductRequestDTO productRequestDTO) {
        // Check if Category exists
        Optional<Category> categoryOptional = categoryRepository.findById(productRequestDTO.getCategoryId());
        if (categoryOptional.isEmpty()) {
            throw new IllegalArgumentException("Category with ID " + productRequestDTO.getCategoryId() + " does not exist.");
        }

        // Check if Brand exists
        Optional<Brand> brandOptional = brandRepository.findById(productRequestDTO.getBrandId());
        if (brandOptional.isEmpty()) {
            throw new IllegalArgumentException("Brand with ID " + productRequestDTO.getBrandId() + " does not exist.");
        }

        // Find existing Product
        Optional<Product> existingProductOptional = getProductById(id);
        if (existingProductOptional.isEmpty()) {
            throw new IllegalArgumentException("Product with ID " + id + " does not exist.");
        }

        Product existingProduct = existingProductOptional.get();

        // TODO: Add control for update or add UpdateRequestDTO
        // Update only given parameters
        // Update Product entity
        existingProduct.setProductCode(productRequestDTO.getProductCode());
        existingProduct.setName(productRequestDTO.getName());
        existingProduct.setDescription(productRequestDTO.getDescription());
        existingProduct.setPrice(productRequestDTO.getPrice());
        existingProduct.setStockQuantity(productRequestDTO.getStockQuantity());
        existingProduct.setCategory(categoryOptional.get());
        existingProduct.setBrand(brandOptional.get());

        // Save updated Product
        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.toResponseDTO(updatedProduct);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
}
