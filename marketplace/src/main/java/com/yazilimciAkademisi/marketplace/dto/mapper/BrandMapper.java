package com.yazilimciAkademisi.marketplace.dto.mapper;

import com.yazilimciAkademisi.marketplace.dto.request.BrandRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.BrandResponseDTO;
import com.yazilimciAkademisi.marketplace.entity.Brand;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BrandMapper {

    public Brand toEntity(BrandRequestDTO dto) {
        Brand brand = new Brand();
        brand.setName(dto.getName());
        return brand;
    }

    public BrandResponseDTO toResponseDTO(Brand brand) {
        BrandResponseDTO dto = new BrandResponseDTO();
        dto.setId(brand.getId());
        dto.setName(brand.getName());
        return dto;
    }

    public List<BrandResponseDTO> toBrandResponseDTOList(List<Brand> brandList) {
        return brandList.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
