package br.com.luizvictor.springcatalog.entities.product;

public record ProductDto(String title, Long owner, Long category, Integer price, String description) {
}
