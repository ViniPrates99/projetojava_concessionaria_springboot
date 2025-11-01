package com.vini.garagem.service;

import com.vini.garagem.domain.Brand;
import com.vini.garagem.domain.Model;
import com.vini.garagem.repo.ModelRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ModelService {

    private final ModelRepository repo;

    public ModelService(ModelRepository repo) {
        this.repo = repo;
    }

    // Cria um novo modelo
    public Model create(Model m) {
        return repo.save(m);
    }

    // Lista todos os modelos
    public List<Model> list() {
        return repo.findAll();
    }

    // Busca modelos de uma marca específica
    public List<Model> byBrand(Brand b) {
        return repo.findByBrand(b);
    }
}
