package com.vini.garagem.web;

import com.vini.garagem.domain.*;
import com.vini.garagem.service.BrandService;
import com.vini.garagem.service.ModelService;
import com.vini.garagem.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;
    private final BrandService brandService;
    private final ModelService modelService;

    public VehicleController(VehicleService vehicleService, BrandService brandService, ModelService modelService) {
        this.vehicleService = vehicleService; this.brandService = brandService; this.modelService = modelService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vehicle create(@RequestParam String brandName,
                          @RequestParam String modelName,
                          @RequestParam int ano,
                          @RequestParam String cor,
                          @RequestParam double preco,
                          @RequestParam double quilometragem,
                          @RequestParam(defaultValue = "DISPONIVEL") VehicleStatus status) {

        Brand b = brandService.getOrCreate(brandName);
        Model m = modelService.list().stream()
                .filter(mm -> mm.getNome().equalsIgnoreCase(modelName) && mm.getBrand().getId().equals(b.getId()))
                .findFirst()
                .orElseGet(() -> modelService.create(new Model(modelName, null, b)));

        return vehicleService.create(new Vehicle(b, m, ano, cor, preco, quilometragem, status));
    }

    @GetMapping("/{id}")
    public Vehicle get(@PathVariable Long id) { return vehicleService.get(id); }

    @GetMapping
    public List<Vehicle> search(@RequestParam(required = false) String brand,
                                @RequestParam(required = false) String model,
                                @RequestParam(required = false) Integer ano,
                                @RequestParam(required = false) Double precoMin,
                                @RequestParam(required = false) Double precoMax,
                                @RequestParam(required = false) VehicleStatus status) {
        return vehicleService.search(brand, model, ano, precoMin, precoMax, status);
    }

    @PutMapping("/{id}")
    public Vehicle update(@PathVariable Long id, @RequestBody Vehicle body) {
        return vehicleService.update(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { vehicleService.delete(id); }
}
