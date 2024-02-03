package br.com.luizvictor.anotai.entities.category;

import br.com.luizvictor.anotai.entities.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Long owner;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Product> products;

    public Category(CategoryDto data) {
        this.title = data.title();
        this.description = data.description();
        this.owner = data.owner();
    }

    public Category() {

    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Long getOwner() {
        return owner;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void update(CategoryDto data) {
        this.title = data.title();
        this.description = data.description();
        this.owner = data.owner();
    }
}
