package com.yazilimciAkademisi.marketplace.dto.mapper;

import com.yazilimciAkademisi.marketplace.dto.request.StoreRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.StoreResponseDTO;
import com.yazilimciAkademisi.marketplace.entity.Store;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StoreMapper {

    public Store toEntity(StoreRequestDTO dto) {
        Store store = new Store();
        store.setStoreName(dto.getStoreName());
        store.setDescription(dto.getDescription());
        store.setAddress(dto.getAddress());
        return store;
    }

    public StoreResponseDTO toResponseDTO(Store store) {
        StoreResponseDTO dto = new StoreResponseDTO();
        dto.setId(store.getId());
        dto.setStoreName(store.getStoreName());
        dto.setDescription(store.getDescription());
        dto.setContactInfo(store.getContactInfo());
        dto.setAddress(store.getAddress());
        dto.setCreatedAt(store.getCreatedAt());
        dto.setUpdatedAt(store.getUpdatedAt());
        return dto;
    }

    public List<StoreResponseDTO> toStoreResponseDTOList(List<Store> storeList) {
        return storeList.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
