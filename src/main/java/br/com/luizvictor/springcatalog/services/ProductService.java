package br.com.luizvictor.springcatalog.services;

import br.com.luizvictor.springcatalog.entities.category.Category;
import br.com.luizvictor.springcatalog.entities.product.Product;
import br.com.luizvictor.springcatalog.entities.product.ProductDto;
import br.com.luizvictor.springcatalog.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductService(ProductRepository productRepository, CategoryService categoryRepository) {
        this.productRepository = productRepository;
        this.categoryService = categoryRepository;
    }

    public Product save(ProductDto data) {
        Category category = categoryService.findById(data.category());

        Product product = new Product(
                data.title(),

                data.owner(),
                category,
                BigDecimal.valueOf(data.price()),
                data.description()
        );

        return productRepository.save(product);
    }

    public List<Product> list() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Product not found")
        );
    }

    public Product update(ProductDto data, Long id) {
        Product product = findById(id);

        product.update(data);
        return productRepository.save(product);
    }

    public void delete(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
    }
}
