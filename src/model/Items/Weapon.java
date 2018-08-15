package model.Items;

import java.util.List;

import java.util.Random;

public class Weapon extends EquippableItemHands implements HasDamage{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5248000014319821995L;
	private double maxPhysicalDamage;
	private double minPhysicalDamage;
	private double maxMagicalDamage;
	private double minMagicalDamage;
	//critChance between 0 and 100
	private double critChance;
	private double critMultiplier;
	private List<String> successText;
	private List<String> failText;
	
	public Weapon(String name, String description, int value, double weigth, ItemID id,
			double maxPhysicalDamage, double minPhysicalDamage, double maxMagicalDamage,
			double minMagicalDamage, WeaponHands handsNumber, int level , double critChance, double critMultiplier, List<String> successText, List<String> failText) {
		super(name, description, value, weigth, id, handsNumber, level);
		this.maxPhysicalDamage = maxPhysicalDamage;
		this.minPhysicalDamage = minPhysicalDamage;
		this.maxMagicalDamage = maxMagicalDamage;
		this.minMagicalDamage = minMagicalDamage;
		this.critChance = critChance;
		this.critMultiplier = critMultiplier;
		this.successText = successText;
		this.failText = failText;
	}
	
	
	public double getMaxPhysicalDamage() {
		return maxPhysicalDamage;
	}

	public double getMinPhysicalDamage() {
		return minPhysicalDamage;
	}
	
	public double getMaxMagicalDamage() {
		return maxMagicalDamage;
	}


	public double getMinMagicalDamage() {
		return minMagicalDamage;
	}


	public double getCritChance() {
		return critChance;
	}

	public double getCritMultiplier() {
		return critMultiplier;
	}
	
	// it doesn't take into account the critical strike chance and multiplier
	@Override
	public double getPhysicalDamage() {
		Random r = new Random();
		double result;
		result = minPhysicalDamage + (maxPhysicalDamage-minPhysicalDamage) * r.nextDouble();
		return result;
	}
	
	// it doesn't take into account the critical strike chance and multiplier
	@Override
	public double getMagicalDamage() {
		Random r = new Random();
		double result;
		result = minMagicalDamage + (maxMagicalDamage-minMagicalDamage) * r.nextDouble();
		return result;
	}
	
	//returns one of the possible messages
	public String getSuccessText() {
		Random r = new Random();
		//successText.size excluded
		Integer textIndex = r.nextInt(successText.size());
		return successText.get(textIndex);
	}
	
	public String getFailText() {
		Random r = new Random();
		Integer textIndex = r.nextInt(failText.size());
		return failText.get(textIndex);
	}
	


	@Override
	public String itemToString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name + System.lineSeparator());
		sb.append("Physical Dmg: " + minPhysicalDamage + "-" + maxPhysicalDamage + System.lineSeparator());
		sb.append("Magical Dmg: " + minMagicalDamage + "-" + maxMagicalDamage);
		
		return sb.toString();
	}







}
