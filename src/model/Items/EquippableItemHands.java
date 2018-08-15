package model.Items;



public abstract class EquippableItemHands extends EquippableItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -275302738851348007L;
	private WeaponHands handsNumber;
	
	public EquippableItemHands(String name, String description, int value, double weigth, ItemID id, WeaponHands handsNumber, int level) {
		super(name, description, value, weigth, id, level);
		this.handsNumber = handsNumber;
	}

	
	
	public WeaponHands getHandsNumber() {
		return handsNumber;
	}






	public abstract String itemToString();

}
