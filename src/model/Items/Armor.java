package model.Items;



public class Armor extends EquippableItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5610378217068334433L;
	private double physicalArmorRate;
	private double magicalArmorRate;
	private ArmorPiece armorPiece;
	private ArmorClass armorClass;
	
	public Armor(String name, String description, int value, double weigth, ItemID id,int level, double physicalArmorRate, double magicalArmorRate, ArmorPiece armorPiece, ArmorClass armorClass) {
		super(name, description, value, weigth, id, level);
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
	
	public ArmorClass getArmorClass() {
		return armorClass;
	}


	@Override
	public String itemToString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name + System.lineSeparator());
		sb.append("Physical Armor: " + physicalArmorRate + System.lineSeparator());
		sb.append("Magic Resist: " + magicalArmorRate);
		
		return sb.toString();
	}



}
