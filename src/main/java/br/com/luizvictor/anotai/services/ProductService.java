package br.com.luizvictor.anotai.services;

import br.com.luizvictor.anotai.entities.category.Category;
import br.com.luizvictor.anotai.entities.product.Product;
import br.com.luizvictor.anotai.entities.product.ProductDto;
import br.com.luizvictor.anotai.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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
}
