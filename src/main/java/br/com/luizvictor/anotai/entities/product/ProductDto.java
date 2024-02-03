package br.com.luizvictor.anotai.entities.product;

import java.math.BigDecimal;

public record ProductDto(String title, Long owner, Long category, Integer price, String description) {
}
