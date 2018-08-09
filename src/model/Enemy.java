package model;

import java.util.Random;

import model.Items.Armor;
import model.Items.ItemID;
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

	@Override
	public boolean isDead() {
		return getCurrentHP()<0;
	}

	@Override
	public Attack attack(Actor target) {
		Character c = (Character)target;
		double hitChance = 0;
		if (weapon.getPhysicalDamage() > weapon.getMagicalDamage())
//			hitChance = skill - (e.getArmor().getPhysicalArmorRate()/10) + ((characteristics.getDex()-e.getCharacteristics().getDex())/10) +
//			(skills.getCriticalStrike()/10) -15;
//		else
//			hitChance = skill - (e.getArmor().getMagicalArmorRate()/10) + ((characteristics.getDex()-e.getCharacteristics().getDex())/10)+
//			(skills.getCriticalStrike()/10) -15;
		
		if (hitChance > 95) 
			hitChance = 95;
		
		Random r = new Random();
		boolean hitSuccess = (r.nextDouble()*100) < hitChance;
		if (!hitSuccess)
			return new Attack(false, 0, 0 ,weapon.getFailText());
		else{
			double critChance = weapon.getCritChance() + characteristics.getLck()/4 + skills.getCriticalStrike()/4;
			boolean critSuccess = (r.nextDouble()*100) < critChance;
			double physicalDamage = (weapon.getPhysicalDamage() + weapon.getPhysicalDamage() * skill/100);
			double magicalDamage = (weapon.getMagicalDamage() + weapon.getMagicalDamage() * skill/100);
			if (critSuccess){
				physicalDamage = physicalDamage * weapon.getCritMultiplier();
				magicalDamage = magicalDamage * weapon.getCritMultiplier();
			}
			//inflicted damage is returned by the defender
			return new Attack(true, physicalDamage, magicalDamage, weapon.getSuccessText());
			
		}
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
