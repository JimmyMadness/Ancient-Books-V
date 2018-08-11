package model.character;

import java.io.Serializable;
import java.util.Random;

import events.CarryWeightEvent;
import events.CarryWeightListener;
import model.Actor;
import model.ActorID;
import model.Attack;
import model.Characteristics;
import model.Defense;
import model.Encyclopedia;
import model.Enemy;
import model.Items.Item;
import model.Items.ItemID;
import model.Items.Weapon;

public class Character extends Actor implements CarryWeightListener, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4528962735382593726L;
	
	private Characteristics characteristics;
	private Sex sex;
	private int age;
	private String characterPicture;
	private RpgClass rpgClass;
	
	private int xp;
	private int gold;
	
	private Inventory inventory;
	private Skills skills;
	

	public static Character createNewCharacter(Characteristics characteristics, String descritpion,
			String name, ActorID id, Encyclopedia encyclopedia,  Sex sex, int age, String characterPicture,
			RpgClass rpgClass, SkillsType[] primarySkills, SkillsType[] secondarySkills) {
		
		int level = 1;
		double maxHp = characteristics.getMaxHp(level);
		int xp = 0;
		int gold = 100;
		Inventory inventory = encyclopedia.getStartingInventory(rpgClass);
		inventory.setLoadout(encyclopedia.getStartingLoadout(rpgClass)) ;
		Skills skills = encyclopedia.getStartingSkills(characteristics, primarySkills, secondarySkills);
		
		return new Character(characteristics, maxHp, descritpion, name, level, id, encyclopedia,  sex, age, characterPicture, rpgClass,
				xp, gold, inventory, skills);
	
	}
	
	public static Character loadCharacter(Characteristics characteristics, double maxHP, String descritpion,
			String name, int lvl, ActorID id,Encyclopedia encyclopedia, Sex sex, int age, String characterPicture,
			RpgClass rpgClass, int xp, int gold, Inventory inventory, Skills skills, double carryWeight, double totalCarryWeight) {
		return new Character(characteristics,  maxHP, descritpion, name, lvl, id, encyclopedia,  sex,  age,  characterPicture,
			 rpgClass,  xp,  gold,  inventory,  skills);
	}
	
	private Character(Characteristics characteristics, double maxHP, String descritpion,
			String name, int lvl, ActorID id,Encyclopedia encyclopedia, Sex sex, int age, String characterPicture,
			RpgClass rpgClass, int xp, int gold, Inventory inventory, Skills skills) {
		super(maxHP, descritpion, name, lvl, id, encyclopedia);
		this.characteristics = characteristics;
		this.sex = sex;
		this.age = age;
		this.characterPicture = characterPicture;
		this.rpgClass = rpgClass;
		this.xp = xp;
		this.gold = gold;
		this.inventory = inventory;
		this.skills = skills;

	}
	


	public Characteristics getCharacteristics() {
		return characteristics;
	}


	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public Sex getSex() {
		return sex;
	}

	public int getAge() {
		return age;
	}

	public String getCharacterPicture() {
		return characterPicture;
	}

	public RpgClass getRpgClass() {
		return rpgClass;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public Skills getSkills() {
		return skills;
	}

 

	@Override
	public void onCarryWeight(CarryWeightEvent event) {
	//	this.carryWeight = event.getCarryWeight();
		//TODO change the listener to the controller, so that it can choose what to do
	}

	
	@Override
	public boolean isDead() {
		return currentHP <0;
	}
	
	public boolean canAddToInventory(Item i) {
		return (inventory.getTotalWeight()+ i.getWeight())< characteristics.getCarryWeight();
	}
	
	public boolean doILevelUp(){
		return xp > encyclopedia.getXpToLevelUp().get(this.lvl);
	}
	
	public Attack attack(Enemy target) {
		double physicalDamage = 0;
		double magicalDamage= 0;
		boolean success = false;
		String text = "";
		for(Weapon w : inventory.getLoadout().getWeapons()) {
			Attack a = weaponAttack(target, w);
			physicalDamage += a.getPhysicalDamage();
			magicalDamage += a.getMagicalDamage();
			success = success || a.isSucceded();
			if (a.isSucceded()) text = a.getText();
		}
		return new Attack(success, physicalDamage, magicalDamage, text);	
	}

	public Attack weaponAttack(Enemy e, Weapon weapon) {
		double hitChance = 0;
		int skill = 0;
		if (weapon.getId() == ItemID.ONEHANDED) 		
			skill = skills.getOneHanded();
		if (weapon.getId() == ItemID.TWOHANDED) 		
			skill = skills.getTwoHanded();
		if (weapon.getId() == ItemID.BOW) 		
			skill = skills.getArchery();
		if (weapon.getId() == ItemID.MAGICWAND) 		
			skill = skills.getMagicPower();
		
		if (weapon.getPhysicalDamage() > weapon.getMagicalDamage())
			hitChance = skill - (e.getArmor().getPhysicalArmorRate()/10) + ((characteristics.getDex()-e.getAgility())/10) +
			(skills.getCriticalStrike()/10) -15;
		else
			hitChance = skill - (e.getArmor().getMagicalArmorRate()/10) + ((characteristics.getDex()-e.getAgility())/10)+
			(skills.getCriticalStrike()/10) -15;
		
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
	
	public Defense defend(Attack a) {
		double totalDamage = 0;
		totalDamage += a.getPhysicalDamage() * (100/(100+ getAdjustedPhysicalArmorRate()));
		totalDamage += a.getMagicalDamage() * (100/(100+ getAdjustedMagicalArmorRate()));
		currentHP -= totalDamage;		
		return new Defense(totalDamage, isDead());
	}

	//from 50% to 150% based on skill
	public double getAdjustedPhysicalArmorRate() {
		double light = inventory.getLoadout().getTotalLightPhysicalArmorRate();
		double heavy = inventory.getLoadout().getTotalHeavyPhysicalArmorRate();
		
		light = light + (skills.getLightArmor()-50)* light/100;
		heavy = heavy + (skills.getHeavyArmor()-50) * heavy/100;
		return light + heavy;
	}
	
	//from 50% to 150% based on skill
	public double getAdjustedMagicalArmorRate() {
		double light = inventory.getLoadout().getTotalLightMagicalArmorRate();
		double heavy = inventory.getLoadout().getTotalHeavyMagicalArmorRate();
		
		light = light + (skills.getLightArmor()-50)* light/100;
		heavy = heavy + (skills.getHeavyArmor()-50) * heavy/100;
		return light + heavy;
	}

}
