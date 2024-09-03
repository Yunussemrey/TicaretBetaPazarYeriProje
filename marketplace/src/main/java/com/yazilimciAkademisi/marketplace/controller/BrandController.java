package com.yazilimciAkademisi.marketplace.controller;

import com.yazilimciAkademisi.marketplace.dto.request.BrandRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.BrandResponseDTO;
import com.yazilimciAkademisi.marketplace.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public ResponseEntity<List<BrandResponseDTO>> getAllBrands() {
        List<BrandResponseDTO> brands = brandService.getAllBrandDTOs();
        return ResponseEntity.ok(brands);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponseDTO> getBrandById(@PathVariable Integer id) {
        Optional<BrandResponseDTO> brandDTO = brandService.getBrandResponseDTOById(id);
        return brandDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BrandResponseDTO> createBrand(@RequestBody BrandRequestDTO brandRequestDTO) {
        BrandResponseDTO brandDTO = brandService.saveBrand(brandRequestDTO);
        return ResponseEntity.status(201).body(brandDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandResponseDTO> updateBrand(@PathVariable Integer id, @RequestBody BrandRequestDTO brandRequestDTO) {
        if (brandService.getBrandById(id).isPresent()) {
            BrandResponseDTO updatedBrandDTO = brandService.saveBrand(brandRequestDTO);
            return ResponseEntity.ok(updatedBrandDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Integer id) {
        try {
            brandService.deleteBrand(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
