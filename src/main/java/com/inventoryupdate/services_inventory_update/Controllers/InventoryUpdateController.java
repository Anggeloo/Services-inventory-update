package com.inventoryupdate.services_inventory_update.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventoryupdate.services_inventory_update.Models.ApiResponse;
import com.inventoryupdate.services_inventory_update.Models.Inventory;
import com.inventoryupdate.services_inventory_update.Services.InventoryUpdateServices;

@RestController
@RequestMapping("/inventory/update")
public class InventoryUpdateController {
    @Autowired
    private InventoryUpdateServices _service;

    @PutMapping
    public ApiResponse<Inventory> updateInventory(@RequestBody Inventory inventory) {
        ApiResponse<Inventory> response = _service.updateInventory(inventory);
        return response;
    }
}
