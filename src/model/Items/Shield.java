package model.Items;

public class Shield extends EquippableItemHands implements HasArmor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6430557023464093514L;
	private double physicalArmorRate;
	private double magicalArmorRate;
	private ArmorClass armorClass;
	
	public Shield(String name, String description, int value, double weigth, ItemID id, WeaponHands handsNumber,
			int level, double physicalArmorRate, double magicalArmorRate, ArmorClass armorClass) {
		super(name, description, value, weigth, id, handsNumber, level);
		this.physicalArmorRate = physicalArmorRate;
		this.magicalArmorRate = magicalArmorRate;
		this.armorClass = armorClass;
	}

	@Override
	public double getPhysicalArmorRate() {
		return physicalArmorRate;
	}

	@Override
	public double getMagicalArmorRate() {
		return magicalArmorRate;
	}

	@Override
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
