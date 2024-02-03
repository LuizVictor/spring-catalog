package br.com.luizvictor.anotai.controllers;

import br.com.luizvictor.anotai.entities.category.Category;
import br.com.luizvictor.anotai.entities.category.CategoryDto;
import br.com.luizvictor.anotai.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
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

    @GetMapping
    public ResponseEntity<List<Category>> list() {
        List<Category> categories = service.list();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        try {
            Category category = service.findById(id);
            return ResponseEntity.ok(category);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@RequestBody CategoryDto data, @PathVariable Long id) {
        try {
            Category category = service.update(data, id);
            return ResponseEntity.ok(category);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
