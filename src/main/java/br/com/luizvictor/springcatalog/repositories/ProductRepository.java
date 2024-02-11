package br.com.luizvictor.springcatalog.repositories;

import br.com.luizvictor.springcatalog.entities.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}