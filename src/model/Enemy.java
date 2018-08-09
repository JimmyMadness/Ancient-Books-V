package model;

import java.util.Random;

import model.Items.Armor;
import model.Items.Weapon;

public class Enemy extends Actor {

	private double lowRewardChance;
	private double mediumRewardChance;
	private double highRewardChance;
	
	//weapons and armor for enemies are custom made to give them damage and defense for combat
	private Weapon weapon;
	private Armor armor;
	
	private int minGold;
	private int maxGold;
	
	private int xpReward;
	

	
	public Enemy(Characteristics characteristics, double maxHP, String descritpion, String name, int lvl, ActorID id,
			Encyclopedia encyclopedia, double lowRewardChance, double mediumRewardChance, double highRewardChance,
			Weapon weapon, Armor armor, int minGold, int maxGold, int xpReward) {
		super(characteristics, maxHP, descritpion, name, lvl, id, encyclopedia);
		this.lowRewardChance = lowRewardChance;
		this.mediumRewardChance = mediumRewardChance;
		this.highRewardChance = highRewardChance;
		this.weapon = weapon;
		this.armor = armor;
		this.minGold = minGold;
		this.maxGold = maxGold;
		this.xpReward = xpReward;
	}
	
	public double getLowRewardChance() {
		return lowRewardChance;
	}

	public double getMediumRewardChance() {
		return mediumRewardChance;
	}

	public double getHighRewardChance() {
		return highRewardChance;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public Armor getArmor() {
		return armor;
	}

	public int getGoldReward() {
		Random r = new Random();
		return minGold + r.nextInt(maxGold -minGold);
	}


	public int getXpReward() {
		return xpReward;
	}

	public boolean isDead() {
		return getCurrentHP()<0;
	}

	@Override
	public Attack attack(Actor target) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Defense defend(Attack a) {
		double totalDamage = 0;
		totalDamage += a.getPhysicalDamage() * (100/(100+ armor.getPhysicalArmorRate()));
		totalDamage += a.getMagicalDamage() * (100/(100+ armor.getMagicalArmorRate()));
		currentHP -= totalDamage;		
		return new Defense(totalDamage, isDead());
	}




}
