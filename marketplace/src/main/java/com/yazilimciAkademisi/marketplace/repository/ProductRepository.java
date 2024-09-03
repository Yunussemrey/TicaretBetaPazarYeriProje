package com.yazilimciAkademisi.marketplace.repository;

import com.yazilimciAkademisi.marketplace.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
