package br.com.luizvictor.anotai.services;

import br.com.luizvictor.anotai.entities.category.Category;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class CatalogService {
    private final CategoryService service;

    public CatalogService(CategoryService service) {
        this.service = service;
    }

    public String create(Long ownerId) {
        List<Category> categories = service.findByOwnerId(ownerId);

        JSONObject json = createCatalogJson(ownerId, categories);
        saveCatalogFile(ownerId, json);

        return "Catalog is being generated";
    }

    private void saveCatalogFile(Long ownerId, JSONObject json) {
        try {
            Files.write(Paths.get("uploads/" + ownerId + "-catalog.json"), json.toString().getBytes());
        } catch (IOException e) {
            System.out.println(e.getMessage());
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
