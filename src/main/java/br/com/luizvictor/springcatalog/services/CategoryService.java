package br.com.luizvictor.springcatalog.services;

import br.com.luizvictor.springcatalog.entities.category.Category;
import br.com.luizvictor.springcatalog.entities.category.CategoryDto;
import br.com.luizvictor.springcatalog.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category save(CategoryDto data) {
       Category category = new Category(data);
       return repository.save(category);
    }

    public List<Category> list() {
        return repository.findAll();
    }

    public Category findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found"));
    }

    public Category update(CategoryDto data, Long id) {
        Category category = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found"));
        category.update(data);
        return repository.save(category);
    }

    public void delete(Long id) {
        Category category = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found"));
        repository.delete(category);
    }

    public List<Category> findByOwnerId(Long ownerId) {
        List<Category> categories =  repository.findByOwner(ownerId);
        if (categories.isEmpty()) {
            throw new EntityNotFoundException("Category not found");
        }

        return categories;
    }
}
