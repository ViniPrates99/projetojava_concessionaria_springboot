package com.vini.garagem.web;

import com.vini.garagem.domain.Brand;
import com.vini.garagem.domain.Model;
import com.vini.garagem.domain.Vehicle;
import com.vini.garagem.domain.VehicleStatus;
import com.vini.garagem.service.BrandService;
import com.vini.garagem.service.ModelService;
import com.vini.garagem.service.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ui/vehicles")
public class VehiclePageController {

    private final VehicleService vehicleService;
    private final BrandService brandService;
    private final ModelService modelService;

    public VehiclePageController(VehicleService vehicleService, BrandService brandService, ModelService modelService) {
        this.vehicleService = vehicleService;
        this.brandService = brandService;
        this.modelService = modelService;
    }

    // LISTA + FILTRO
    @GetMapping
    public String list(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer ano,
            @RequestParam(required = false) Double precoMin,
            @RequestParam(required = false) Double precoMax,
            @RequestParam(required = false) VehicleStatus status,
            org.springframework.ui.Model view
    ) {
        List<Vehicle> vehicles = vehicleService.search(brand, model, ano, precoMin, precoMax, status);
        view.addAttribute("vehicles", vehicles);
        view.addAttribute("selectedStatus", status);
        return "vehicles";
    }

    // FORM NOVO
    @GetMapping("/new")
    public String form(org.springframework.ui.Model view) {
        view.addAttribute("form", new VehicleForm());
        return "vehicle-form";
    }

    // CRIAR
    @PostMapping
    public String create(@ModelAttribute("form") VehicleForm f) {
        Brand b = brandService.getOrCreate(f.getBrandName());
        Model md = modelService.list().stream()
                .filter(x -> x.getNome().equalsIgnoreCase(f.getModelName()) && x.getBrand().getId().equals(b.getId()))
                .findFirst()
                .orElseGet(() -> modelService.create(new Model(f.getModelName(), f.getCategoria(), b)));

        Vehicle v = new Vehicle(b, md, f.getAno(), f.getCor(), f.getPreco(), f.getQuilometragem(), f.getStatus());
        vehicleService.create(v);
        return "redirect:/ui/vehicles";
    }

    // ---------- EDITAR ----------
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, org.springframework.ui.Model view) {
        Vehicle v = vehicleService.get(id);
        VehicleForm f = new VehicleForm();
        f.setId(v.getId());
        f.setBrandName(v.getBrand().getNome());
        f.setModelName(v.getModel().getNome());
        f.setCategoria(v.getModel().getCategoria());
        f.setAno(v.getAno());
        f.setCor(v.getCor());
        f.setPreco(v.getPreco());
        f.setQuilometragem(v.getQuilometragem());
        f.setStatus(v.getStatus());
        view.addAttribute("form", f);
        return "vehicle-form";
    }

    // ---------- ATUALIZAR ----------
    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("form") VehicleForm f) {
        Brand b = brandService.getOrCreate(f.getBrandName());
        Model md = modelService.list().stream()
                .filter(x -> x.getNome().equalsIgnoreCase(f.getModelName()) && x.getBrand().getId().equals(b.getId()))
                .findFirst()
                .orElseGet(() -> modelService.create(new Model(f.getModelName(), f.getCategoria(), b)));

        Vehicle body = new Vehicle(b, md, f.getAno(), f.getCor(), f.getPreco(), f.getQuilometragem(), f.getStatus());
        vehicleService.update(id, body);
        return "redirect:/ui/vehicles";
    }

    // ---------- EXCLUIR ----------
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        vehicleService.delete(id);
        return "redirect:/ui/vehicles";
    }


    public static class VehicleForm {
        private Long id;
        private String brandName;
        private String modelName;
        private String categoria;
        private Integer ano;
        private String cor;
        private Double preco;
        private Double quilometragem;
        private VehicleStatus status = VehicleStatus.DISPONIVEL;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getBrandName() { return brandName; }
        public void setBrandName(String brandName) { this.brandName = brandName; }
        public String getModelName() { return modelName; }
        public void setModelName(String modelName) { this.modelName = modelName; }
        public String getCategoria() { return categoria; }
        public void setCategoria(String categoria) { this.categoria = categoria; }
        public Integer getAno() { return ano; }
        public void setAno(Integer ano) { this.ano = ano; }
        public String getCor() { return cor; }
        public void setCor(String cor) { this.cor = cor; }
        public Double getPreco() { return preco; }
        public void setPreco(Double preco) { this.preco = preco; }
        public Double getQuilometragem() { return quilometragem; }
        public void setQuilometragem(Double quilometragem) { this.quilometragem = quilometragem; }
        public VehicleStatus getStatus() { return status; }
        public void setStatus(VehicleStatus status) { this.status = status; }
    }
}
