package br.com.luizvictor.anotai.services;

import br.com.luizvictor.anotai.entities.category.Category;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class CatalogService {
    private final CategoryService service;
    private final RabbitTemplate rabbitTemplate;
    private final Logger logger = LoggerFactory.getLogger(CatalogService.class);

    public CatalogService(CategoryService service, RabbitTemplate rabbitTemplate) {
        this.service = service;
        this.rabbitTemplate = rabbitTemplate;
    }

    public String create(Long ownerId) {
        List<Category> categories = service.findByOwnerId(ownerId);
        JSONObject json = createCatalogJson(ownerId, categories);

        rabbitTemplate.convertAndSend("", "q.catalog-generation", json.toString());
        logger.info("The catalog for the owner {} is being created", ownerId);
        return "Catalog is being generated";
    }

    @RabbitListener(queues = "q.catalog-generation")
    public void saveCatalogFile(JSONObject json) {
        logger.info("Catalog event received: {}", json);
        Integer ownerId = (Integer) json.get("owner");

        try {
            Path path =  Paths.get("uploads/" + ownerId + "-catalog.json");
            Files.write(path, json.toString().getBytes());
            logger.info("Catalog saved on: {}", path);
        } catch (IOException e) {
            logger.error("Error generating catalog: {}", e.getMessage());
        }
    }

    private JSONObject createCatalogJson(Long ownerId, List<Category> categories) {
        JSONObject json = new JSONObject();
        json.put("owner", ownerId);

        JSONArray array = new JSONArray();
        for (Category category : categories) {
            array.put(category.toJson());
        }

        json.put("catalog", array);

        return json;
    }
}
