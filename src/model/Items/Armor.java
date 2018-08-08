package model.Items;

import java.util.Optional;

public class Armor extends EquippableItem {

	private double physicalArmorRate;
	private double magicalArmorRate;
	private ArmorPiece armorPiece;
	private ArmorClass armorClass;
	
	public Armor(String name, String description, int value, double weigth, ItemID id, double physicalArmorRate, double magicalArmorRate, ArmorPiece armorPiece, ArmorClass armorClass) {
		super(name, description, value, weigth, id);
		this.physicalArmorRate = physicalArmorRate;
		this.magicalArmorRate = magicalArmorRate;
		this.armorPiece = armorPiece;
		this.armorClass = armorClass;
	}
	
	
	public double getPhysicalArmorRate() {
		return physicalArmorRate;
	}

	public double getMagicalArmorRate() {
		return magicalArmorRate;
	}

	public ArmorPiece getArmorPiece() {
		return armorPiece;
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
