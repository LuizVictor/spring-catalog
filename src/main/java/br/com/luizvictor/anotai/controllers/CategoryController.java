package br.com.luizvictor.anotai.controllers;

import br.com.luizvictor.anotai.entities.category.Category;
import br.com.luizvictor.anotai.entities.category.CategoryDto;
import br.com.luizvictor.anotai.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Category> save(@RequestBody CategoryDto data) {
        Category category = service.save(data);
        return ResponseEntity.created(URI.create("/api/category/" + category.getId())).body(category);
    }
}
