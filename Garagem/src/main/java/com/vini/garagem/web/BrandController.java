package com.vini.garagem.web;

import com.vini.garagem.domain.Brand;
import com.vini.garagem.service.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {
    private final BrandService service;
    public BrandController(BrandService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Brand create(@RequestBody Brand b) { return service.create(b); }

    @GetMapping
    public List<Brand> list() { return service.list(); }
}
