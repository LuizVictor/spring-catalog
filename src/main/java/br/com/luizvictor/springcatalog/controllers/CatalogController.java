package br.com.luizvictor.springcatalog.controllers;

import br.com.luizvictor.springcatalog.services.CatalogService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/catalogs")
public class CatalogController {
    private final CatalogService service;

    public CatalogController(CatalogService service) {
        this.service = service;
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> crate(@PathVariable Long id) {
        try {
            String category = service.create(id);
            return ResponseEntity.created(URI.create("/api/catalog/" + id)).body(category);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
