package com.inventoryupdate.services_inventory_update.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.inventoryupdate.services_inventory_update.Models.ApiResponse;
import com.inventoryupdate.services_inventory_update.Models.Inventory;
import com.inventoryupdate.services_inventory_update.Repository.InventoryUpdateRepository;

@Service
public class InventoryUpdateServices {
    private final RestTemplate restTemplate;
    private final InventoryUpdateRepository _repository;

    // El RestTemplate es inyectado a través del constructor
    @Autowired
    public InventoryUpdateServices(RestTemplate restTemplate, InventoryUpdateRepository repository) {
        this.restTemplate = restTemplate; // Se inicializa en el constructor
        this._repository = repository;
    }

    public ApiResponse<Inventory> updateInventory(Inventory inventory) {
        boolean exists = checkProductExists(inventory.getProductCode());
        if (!exists) {
            return new ApiResponse<Inventory>("error", null, "The product code does not exist in the system.");
        }
    
        Inventory existingInventory = _repository.findByInventoryCode(inventory.getInventoryCode());
    
        if (existingInventory == null) {
            return new ApiResponse<Inventory>("error", null, "The inventory with the provided code does not exist.");
        }
        
        existingInventory.setProductCode(inventory.getProductCode());
        existingInventory.setAvailableQuantity(inventory.getAvailableQuantity());
        inventory.setUpdatedAt(LocalDateTime.now());
        _repository.save(existingInventory);

        return new ApiResponse<Inventory>("success", existingInventory, "Inventory updated successfully.");
    }    

    private boolean checkProductExists(String codice) {
        //String url = "http://localhost:3000/products/byCodiceProducto?codice=" + codice;
        String url = "http://app_producto_search:3000/products/byCodiceProducto?codice=" + codice;
        try {
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            String status = (String) response.get("status");
            if ("success".equals(status)) {
                List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");
                return !data.isEmpty();  
            } else {
                return false;  
            }
        } catch (Exception e) {
            e.printStackTrace();  
            return false; 
        }
    }
}
