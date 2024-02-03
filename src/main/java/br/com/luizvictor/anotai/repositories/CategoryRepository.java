package br.com.luizvictor.anotai.repositories;

import br.com.luizvictor.anotai.entities.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}