package br.com.luizvictor.anotai.repositories;

import br.com.luizvictor.anotai.entities.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}