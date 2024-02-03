package br.com.luizvictor.anotai.entities.product;

import br.com.luizvictor.anotai.entities.category.Category;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Long owner;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private BigDecimal price;
    private String description;

    public Product(String title, Long owner, Category category, BigDecimal price, String description) {
        this.title = title;
        this.owner = owner;
        this.category = category;
        this.price = price;
        this.description = description;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Long getOwner() {
        return owner;
    }

    public Category getCategory() {
        return category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
