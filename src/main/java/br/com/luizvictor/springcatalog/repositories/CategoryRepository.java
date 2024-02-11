package br.com.luizvictor.springcatalog.repositories;

import br.com.luizvictor.springcatalog.entities.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByOwner(Long ownerId);
}