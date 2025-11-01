package com.vini.garagem.web;

import com.vini.garagem.domain.Brand;
import com.vini.garagem.domain.Model;
import com.vini.garagem.service.BrandService;
import com.vini.garagem.service.ModelService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/models")
public class ModelController {
    private final ModelService modelService;
    private final BrandService brandService;

    public ModelController(ModelService modelService, BrandService brandService) {
        this.modelService = modelService; this.brandService = brandService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Model create(@RequestParam String brandName,
                        @RequestParam String nome,
                        @RequestParam(required = false) String categoria) {
        Brand b = brandService.getOrCreate(brandName);
        return modelService.create(new Model(nome, categoria, b));
    }

    @GetMapping
    public List<Model> list() { return modelService.list(); }
}
