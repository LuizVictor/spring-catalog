package br.com.luizvictor.anotai.services;

import br.com.luizvictor.anotai.entities.category.Category;
import br.com.luizvictor.anotai.entities.category.CategoryDto;
import br.com.luizvictor.anotai.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

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
}
