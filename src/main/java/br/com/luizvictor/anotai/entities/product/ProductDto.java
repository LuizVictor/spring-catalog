package br.com.luizvictor.anotai.entities.product;

public record ProductDto(String title, Long owner, Long category, Integer price, String description) {
}
