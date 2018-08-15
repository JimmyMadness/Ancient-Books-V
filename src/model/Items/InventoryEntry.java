package model.Items;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class InventoryEntry {
	private final SimpleStringProperty itemName;
    private final SimpleStringProperty	itemDescription;
    private final SimpleIntegerProperty itemQuantity;

    
    public InventoryEntry(String itemName, String itemDescription, Integer itemQuantity) {
    	this.itemName = new SimpleStringProperty(itemName);
    	this.itemDescription = new SimpleStringProperty(itemDescription);
    	this.itemQuantity = new SimpleIntegerProperty(itemQuantity);

    }

	public SimpleStringProperty getItemName() {
		return itemName;
	}

	public SimpleIntegerProperty getItemQuantity() {
		return itemQuantity;
	}

	public SimpleStringProperty getItemDescription() {
		return itemDescription;
	}

}
