package br.com.luizvictor.springcatalog.controllers;

import br.com.luizvictor.springcatalog.entities.product.Product;
import br.com.luizvictor.springcatalog.entities.product.ProductDto;
import br.com.luizvictor.springcatalog.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody ProductDto data) {
        try {
            Product product = service.save(data);
            return ResponseEntity.created(URI.create("/api/products/" + product.getId())).body(product);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> list() {
        List<Product> products = service.list();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        try {
            Product product = service.findById(id);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
     }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@RequestBody ProductDto data, @PathVariable Long id) {
        try {
            Product product = service.update(data, id);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
