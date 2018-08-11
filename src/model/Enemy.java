package model;

import java.util.Random;
import model.character.Character;
import model.Items.Armor;
import model.Items.Weapon;

public class Enemy extends Actor {

	//numbers between 0 and 100
	
	private double lowRewardChance;
	//mediumRewardChance should never be less than 10%
	private double mediumRewardChance;
	private double highRewardChance;
	
	//weapons and armor for enemies are custom made to give them damage and defense for combat
	private Weapon weapon;
	private Armor armor;
	
	//adding a skill stat to determine hit chance
	private int skill;
	
	//replacing characteristics. Adding evasion to use in place of dex to determine how hard is to hit for the character
	private int agility;
	
	private int minGold;
	private int maxGold;
	
	private int xpReward;
	

	
	
	public Enemy(double maxHP, String descritpion, String name, int lvl, ActorID id,
			Encyclopedia encyclopedia, double lowRewardChance, double mediumRewardChance, double highRewardChance,
			Weapon weapon, Armor armor,int skill, int agility, int minGold, int maxGold, int xpReward) {
		super(maxHP, descritpion, name, lvl, id, encyclopedia);
		this.lowRewardChance = lowRewardChance;
		this.mediumRewardChance = mediumRewardChance;
		this.highRewardChance = highRewardChance;
		this.weapon = weapon;
		this.armor = armor;
		this.skill = skill;
		this.agility = agility;
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
	
	public int getSkill() {
		return skill;
	}

	public int getAgility() {
		return agility;
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

	
	public Attack attack(Character target) {
		double hitChance = 0;
		if (weapon.getPhysicalDamage() > weapon.getMagicalDamage())
			hitChance = skill - (target.getAdjustedPhysicalArmorRate()/10) + ((agility- target.getCharacteristics().getDex())/10) - 10;
		else
			hitChance = skill - (target.getAdjustedMagicalArmorRate()/10) + ((agility- target.getCharacteristics().getDex())/10) - 10;
//			(skills.getCriticalStrike()/10) -15;
		
		if (hitChance > 95) 
			hitChance = 95;
		
		Random r = new Random();
		boolean hitSuccess = (r.nextDouble()*100) < hitChance;
		if (!hitSuccess)
			return new Attack(false, 0, 0 ,weapon.getFailText());
		
		//if the hit should be  a success then the character can try to roll for dodge and block, at skill 100 they both have a chance of 66%
		hitSuccess = hitSuccess && (r.nextDouble()*100 > target.getSkills().getBlock()*66);
		if (!hitSuccess)
			return new Attack(false, 0, 0 ,"You successfully blocked the attack");
		
		hitSuccess = hitSuccess && (r.nextDouble()*100 > target.getSkills().getDodge()*66);
		if (!hitSuccess)
			return new Attack(false, 0, 0 ,"You succesfully dodged the attack");
		
		else {
			double critChance = weapon.getCritChance() + 5;
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


	public Defense defend(Attack a) {
		double totalDamage = 0;
		totalDamage += a.getPhysicalDamage() * (100/(100+ armor.getPhysicalArmorRate()));
		totalDamage += a.getMagicalDamage() * (100/(100+ armor.getMagicalArmorRate()));
		currentHP -= totalDamage;		
		return new Defense(totalDamage, isDead());
	}




}
