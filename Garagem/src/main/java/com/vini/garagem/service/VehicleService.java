package com.vini.garagem.service;

import com.vini.garagem.domain.*;
import com.vini.garagem.repo.VehicleRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository repo;

    public VehicleService(VehicleRepository repo) {
        this.repo = repo;
    }

    // Cria um veículo
    public Vehicle create(Vehicle v) {
        return repo.save(v);
    }

    // Busca um veículo pelo ID
    public Vehicle get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado!"));
    }

    // Remove um veículo
    public void delete(Long id) {
        repo.deleteById(id);
    }

    // Atualiza um veículo existente
    public Vehicle update(Long id, Vehicle v) {
        Vehicle db = get(id);
        db.setBrand(v.getBrand());
        db.setModel(v.getModel());
        db.setAno(v.getAno());
        db.setCor(v.getCor());
        db.setPreco(v.getPreco());
        db.setQuilometragem(v.getQuilometragem());
        db.setStatus(v.getStatus());
        return repo.save(db);
    }

    // Filtro de busca (marca, modelo, ano, preço, status, etc.)
    public List<Vehicle> search(String brand, String model, Integer ano,
                                Double precoMin, Double precoMax, VehicleStatus status) {

        Specification<Vehicle> spec = (root, query, cb) -> {
            Predicate p = cb.conjunction();

            if (brand != null && !brand.isBlank())
                p = cb.and(p, cb.equal(cb.lower(root.get("brand").get("nome")), brand.toLowerCase()));

            if (model != null && !model.isBlank())
                p = cb.and(p, cb.equal(cb.lower(root.get("model").get("nome")), model.toLowerCase()));

            if (ano != null)
                p = cb.and(p, cb.equal(root.get("ano"), ano));

            if (precoMin != null)
                p = cb.and(p, cb.ge(root.get("preco"), precoMin));

            if (precoMax != null)
                p = cb.and(p, cb.le(root.get("preco"), precoMax));

            if (status != null)
                p = cb.and(p, cb.equal(root.get("status"), status));

            return p;
        };

        return repo.findAll(spec);
    }
}
