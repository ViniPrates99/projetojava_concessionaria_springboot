package com.vini.garagem.service;

import com.vini.garagem.domain.Brand;
import com.vini.garagem.repo.BrandRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BrandService {

    private final BrandRepository repo;

    public BrandService(BrandRepository repo) {
        this.repo = repo;
    }

    public Brand create(Brand b) {
        return repo.save(b);
    }

    public List<Brand> list() {
        return repo.findAll();
    }

    // Cria a Marca caso não exista
    public Brand getOrCreate(String nome) {
        return repo.findByNomeIgnoreCase(nome)
                .orElseGet(() -> repo.save(new Brand(nome, null)));
    }
}
