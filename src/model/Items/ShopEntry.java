package model.Items;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ShopEntry {
	private final SimpleStringProperty itemName;
    private final SimpleIntegerProperty itemQuantity;
    private final SimpleIntegerProperty	itemPrice;
    
    public ShopEntry(String itemName, Integer itemQuantity, Integer itemPrice) {
    	this.itemName = new SimpleStringProperty(itemName);
    	this.itemQuantity = new SimpleIntegerProperty(itemQuantity);
    	this.itemPrice = new SimpleIntegerProperty(itemPrice);
    }

	public SimpleStringProperty getItemName() {
		return itemName;
	}

	public SimpleIntegerProperty getItemQuantity() {
		return itemQuantity;
	}

	public SimpleIntegerProperty getItemPrice() {
		return itemPrice;
	}

    
    
    
}
