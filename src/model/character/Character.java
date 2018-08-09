package model.character;

import java.util.Random;

import events.CarryWeightEvent;
import events.CarryWeightListener;
import javafx.scene.image.Image;
import model.Actor;
import model.ActorID;
import model.Attack;
import model.Characteristics;
import model.Defense;
import model.Encyclopedia;
import model.Enemy;
import model.Items.ItemID;
import model.Items.Weapon;

public class Character extends Actor implements CarryWeightListener {
	private Sex sex;
	private int age;
	private Image characterPicture;
	private RpgClass rpgClass;
	
	private int xp;
	private int gold;
	
	private Inventory inventory;
	private Skills skills;
	
	private double carryWeight;
	private double totalCarryWeight;

	public static Character createNewCharacter(Characteristics characteristics, String descritpion,
			String name, ActorID id, Encyclopedia encyclopedia,  Sex sex, int age, Image characterPicture,
			RpgClass rpgClass, SkillsType[] primarySkills, SkillsType[] secondarySkills) {
		
		int level = 1;
		double maxHp = characteristics.getMaxHp(level);
		int xp = 0;
		int gold = 100;
		Inventory inventory = encyclopedia.getStartingInventory(rpgClass);
		inventory.setLoadout(encyclopedia.getStartingLoadout(rpgClass)) ;
		Skills skills = encyclopedia.getStartingSkills(characteristics, primarySkills, secondarySkills);
		double carryWeight = inventory.getTotalWeight();
		double totalCarryWeight = characteristics.getCarryWeigth();
		
		return new Character(characteristics, maxHp, descritpion, name, level, id, encyclopedia,  sex, age, characterPicture, rpgClass,
				xp, gold, inventory, skills, carryWeight ,totalCarryWeight);
	
	}
	
	public static Character loadCharacter(Characteristics characteristics, double maxHP, String descritpion,
			String name, int lvl, ActorID id,Encyclopedia encyclopedia, Sex sex, int age, Image characterPicture,
			RpgClass rpgClass, int xp, int gold, Inventory inventory, Skills skills, double carryWeight, double totalCarryWeight) {
		return new Character(characteristics,  maxHP, descritpion, name, lvl, id, encyclopedia,  sex,  age,  characterPicture,
			 rpgClass,  xp,  gold,  inventory,  skills,  carryWeight,  totalCarryWeight);
	}
	
	private Character(Characteristics characteristics, double maxHP, String descritpion,
			String name, int lvl, ActorID id,Encyclopedia encyclopedia, Sex sex, int age, Image characterPicture,
			RpgClass rpgClass, int xp, int gold, Inventory inventory, Skills skills, double carryWeight, double totalCarryWeight) {
		super(characteristics, maxHP, descritpion, name, lvl, id, encyclopedia);
		this.sex = sex;
		this.age = age;
		this.characterPicture = characterPicture;
		this.rpgClass = rpgClass;
		this.xp = xp;
		this.gold = gold;
		this.inventory = inventory;
		this.skills = skills;
		this.carryWeight = carryWeight;
		this.totalCarryWeight = totalCarryWeight;
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

	public Image getCharacterPicture() {
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
		this.carryWeight = event.getCarryWeight();
		
	}

	

	@Override
	public Attack attack(Actor target) {
		Enemy e = (Enemy)target;
		double physicalDamage = 0;
		double magicalDamage= 0;
		boolean success = false;
		String text = "";
		for(Weapon w : inventory.getLoadout().getWeapons()) {
			Attack a = weaponAttack(e, w);
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
			hitChance = skill - (e.getArmor().getPhysicalArmorRate()/10) + ((characteristics.getDex()-e.getCharacteristics().getDex())/10) +
			(skills.getCriticalStrike()/10) -15;
		else
			hitChance = skill - (e.getArmor().getMagicalArmorRate()/10) + ((characteristics.getDex()-e.getCharacteristics().getDex())/10)+
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
	
	@Override
	public Defense defend(Attack a) {
		// TODO Auto-generated method stub
		return null;
	}


}
