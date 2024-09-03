package com.yazilimciAkademisi.marketplace.service;

import com.yazilimciAkademisi.marketplace.dto.mapper.BrandMapper;
import com.yazilimciAkademisi.marketplace.dto.request.BrandRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.BrandResponseDTO;
import com.yazilimciAkademisi.marketplace.entity.Brand;
import com.yazilimciAkademisi.marketplace.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Autowired
    public BrandService(BrandRepository brandRepository, BrandMapper brandMapper) {
        this.brandRepository = brandRepository;
        this.brandMapper = brandMapper;
    }

    public List<BrandResponseDTO> getAllBrandDTOs() {
        return brandMapper.toBrandResponseDTOList(getAllBrands());
    }

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Optional<BrandResponseDTO> getBrandResponseDTOById(Integer id) {
        Optional<Brand> brandOptional = getBrandById(id);
        if (brandOptional.isPresent()) {
            BrandResponseDTO dto = brandMapper.toResponseDTO(brandOptional.get());
            return Optional.of(dto);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Brand> getBrandById(Integer id) {
        return brandRepository.findById(id);
    }

    public BrandResponseDTO saveBrand(BrandRequestDTO brandRequestDTO) {
        Brand brand = brandMapper.toEntity(brandRequestDTO);
        Brand savedBrand = brandRepository.save(brand);
        return brandMapper.toResponseDTO(savedBrand);
    }

    public void deleteBrand(Integer id) {
        if (!brandRepository.existsById(id)) {
            throw new IllegalArgumentException("Brand with ID " + id + " does not exist.");
        }
        brandRepository.deleteById(id);
    }
}
