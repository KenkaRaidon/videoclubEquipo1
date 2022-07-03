package uabc.videoclubs.services;

import uabc.videoclubs.entities.Inventory;

public interface IInventoryService {
	
	public Inventory findByInventoryId(Integer inventoryId);
}
