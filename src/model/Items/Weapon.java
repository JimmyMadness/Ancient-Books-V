package model.Items;

import java.util.Optional;
import java.util.Random;

public class Weapon extends EquippableItemHands implements HasDamage{
	private double maxPhysicalDamage;
	private double minPhysicalDamage;
	private double maxMagicalDamage;
	private double minMagicalDamage;
	private double critChance;
	private double critMultiplier;
	
	public Weapon(String name, String description, int value, double weigth, ItemID id,
			double maxPhysicalDamage, double minPhysicalDamage, double maxMagicalDamage,
			double minMagicalDamage, WeaponHands handsNumber, double critChance, double critMultiplier) {
		super(name, description, value, weigth, id, handsNumber);
		this.maxPhysicalDamage = maxPhysicalDamage;
		this.minPhysicalDamage = minPhysicalDamage;
		this.maxMagicalDamage = maxMagicalDamage;
		this.minMagicalDamage = minMagicalDamage;
		this.critChance = critChance;
		this.critMultiplier = critMultiplier;
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
	
	@Override
	public double getPhysicalDamage() {
		Random r = new Random();
		double result;
		result = minPhysicalDamage + (maxPhysicalDamage-minPhysicalDamage) * r.nextDouble();
		if (r.nextInt()< critChance)
			result = result*critMultiplier;
		return result;
	}
	
	@Override
	public double getMagicalDamage() {
		Random r = new Random();
		double result;
		result = minMagicalDamage + (maxMagicalDamage-minMagicalDamage) * r.nextDouble();
		if (r.nextInt()< critChance)
			result = result*critMultiplier;
		return result;
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
