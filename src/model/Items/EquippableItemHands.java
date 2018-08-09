package model.Items;

import java.util.Optional;

public class EquippableItemHands extends EquippableItem {

	private WeaponHands handsNumber;
	
	public EquippableItemHands(String name, String description, int value, double weigth, ItemID id, WeaponHands handsNumber, int level) {
		super(name, description, value, weigth, id, level);
		this.handsNumber = handsNumber;
	}

	
	
	public WeaponHands getHandsNumber() {
		return handsNumber;
	}



	@Override
	public Optional<EquippableItem> Equip() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEquipped() {
		// TODO Auto-generated method stub
		return false;
	}

}
