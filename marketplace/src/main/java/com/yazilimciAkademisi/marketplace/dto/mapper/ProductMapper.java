package com.yazilimciAkademisi.marketplace.dto.mapper;

import com.yazilimciAkademisi.marketplace.dto.request.ProductRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.ProductResponseDTO;
import com.yazilimciAkademisi.marketplace.entity.Product;
import com.yazilimciAkademisi.marketplace.entity.Store;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    private final CategoryMapper categoryMapper;
    private final BrandMapper brandMapper;
    private final StoreMapper storeMapper;

    public ProductMapper(CategoryMapper categoryMapper, BrandMapper brandMapper, StoreMapper storeMapper) {
        this.categoryMapper = categoryMapper;
        this.brandMapper = brandMapper;
        this.storeMapper = storeMapper;
    }

    public Product toEntity(ProductRequestDTO dto) {
        Product product = new Product();
        product.setProductCode(dto.getProductCode());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());
        return product;
    }

    public ProductResponseDTO toResponseDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setProductCode(product.getProductCode());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());
        dto.setCategory(categoryMapper.toResponseDTO(product.getCategory()));
        dto.setBrand(brandMapper.toResponseDTO(product.getBrand()));
        dto.setStore(storeMapper.toResponseDTO(product.getStore()));
        return dto;
    }

    public List<ProductResponseDTO> toProductResponseDTOList(List<Product> productList) {
        return productList.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

}
