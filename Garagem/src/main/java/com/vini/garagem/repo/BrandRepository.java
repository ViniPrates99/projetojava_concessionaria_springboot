package com.vini.garagem.repo;

import com.vini.garagem.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findByNomeIgnoreCase(String nome);
}
