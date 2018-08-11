package model.Items;

import java.util.Optional;

public abstract class EquippableItem extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4671967993388918290L;
	public EquippableItem(String name, String description, int value, double weigth, ItemID id, int level) {
		super(name, description, value, weigth, id, level);
		
	}
	
	
	// maybe?, more probably the item will be equipped from the inventory class to its internal loadout class, to indicate it as equipped
	public abstract Optional<EquippableItem> Equip();
	public abstract boolean isEquipped();	
	

}
