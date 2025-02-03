package com.inventoryupdate.services_inventory_update.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.inventoryupdate.services_inventory_update.Models.Inventory;

public interface InventoryUpdateRepository extends JpaRepository<Inventory, Integer>{
    Inventory findByInventoryCode(String inventoryCode);
}
