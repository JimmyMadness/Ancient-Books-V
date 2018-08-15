package model.Items;



public abstract class EquippableItem extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4671967993388918290L;
	public EquippableItem(String name, String description, int value, double weigth, ItemID id, int level) {
		super(name, description, value, weigth, id, level);
		
	}
	
	
	@Override
	public String toString() {
		return itemToString();
	}
	
	public abstract String itemToString();
	

}
