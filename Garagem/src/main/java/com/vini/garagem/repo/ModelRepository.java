package com.vini.garagem.repo;

import com.vini.garagem.domain.Brand;
import com.vini.garagem.domain.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Long> {
    List<Model> findByBrand(Brand brand);
    List<Model> findByNomeIgnoreCase(String nome);
}
