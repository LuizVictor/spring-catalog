package br.com.luizvictor.anotai.controllers;

import br.com.luizvictor.anotai.entities.product.Product;
import br.com.luizvictor.anotai.entities.product.ProductDto;
import br.com.luizvictor.anotai.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

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
}
