package model.character;

import events.CarryWeightEvent;
import events.CarryWeightListener;
import javafx.scene.image.Image;
import model.Actor;
import model.ActorID;
import model.Characteristics;
import model.Encyclopedia;

public class Character extends Actor implements CarryWeightListener {
	private Sex sex;
	private int age;
	private Image characterPicture;
	private RpgClass rpgClass;
	
	private int xp;
	private int gold;
	
	private Inventory inventory;
	private Loadout loadout;	
	private Skills skills;
	
	private double carryWeight;
	private double totalCarryWeight;

	public static Character createNewCharacter(Characteristics characteristics, String descritpion,
			String name, ActorID id, Encyclopedia encyclopedia,  Sex sex, int age, Image characterPicture,
			RpgClass rpgClass, SkillsType[] primarySkills, SkillsType[] secondarySkills) {
		
		double maxHp = characteristics.getMaxHp();
		int level = 1;
		int xp = 0;
		int gold = 100;
		Inventory inventory = encyclopedia.getStartingInventory(rpgClass);
		Loadout loadout = encyclopedia.getStartingLoadout(rpgClass);
		Skills skills = encyclopedia.getStartingSkills(characteristics, primarySkills, secondarySkills);
		double carryWeight = inventory.getTotalWeight();
		double totalCarryWeight = characteristics.getCarryWeigth();
		
		return new Character(characteristics, maxHp, descritpion, name, level, id, encyclopedia,  sex, age, characterPicture, rpgClass,
				xp, gold, inventory, loadout, skills, carryWeight ,totalCarryWeight);
	
	}
	
	public static Character loadCharacter(Characteristics characteristics, double maxHP, String descritpion,
			String name, int lvl, ActorID id,Encyclopedia encyclopedia, Sex sex, int age, Image characterPicture,
			RpgClass rpgClass, int xp, int gold, Inventory inventory, Loadout loadout, Skills skills, double carryWeight, double totalCarryWeight) {
		return new Character(characteristics,  maxHP, descritpion, name, lvl, id, encyclopedia,  sex,  age,  characterPicture,
			 rpgClass,  xp,  gold,  inventory,  loadout,  skills,  carryWeight,  totalCarryWeight);
	}
	
	private Character(Characteristics characteristics, double maxHP, String descritpion,
			String name, int lvl, ActorID id,Encyclopedia encyclopedia, Sex sex, int age, Image characterPicture,
			RpgClass rpgClass, int xp, int gold, Inventory inventory, Loadout loadout, Skills skills, double carryWeight, double totalCarryWeight) {
		super(characteristics, maxHP, descritpion, name, lvl, id, encyclopedia);
		this.sex = sex;
		this.age = age;
		this.characterPicture = characterPicture;
		this.rpgClass = rpgClass;
		this.xp = xp;
		this.gold = gold;
		this.inventory = inventory;
		this.loadout = loadout;
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

	public Loadout getLoadout() {
		return loadout;
	}

	public Skills getSkills() {
		return skills;
	}

	@Override
	public void attack(Actor target) {
		// TODO Auto-generated method stub

	}

	@Override
	public void defend(Actor attacker) {
		// TODO Auto-generated method stub

	}



	@Override
	public void onCarryWeight(CarryWeightEvent event) {
		this.carryWeight = event.getCarryWeight();
		
	}

}
