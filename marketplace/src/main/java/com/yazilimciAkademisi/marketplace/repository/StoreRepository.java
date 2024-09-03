package com.yazilimciAkademisi.marketplace.repository;

import com.yazilimciAkademisi.marketplace.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {
}
