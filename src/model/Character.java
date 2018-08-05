package model;

import javafx.scene.image.Image;

public class Character extends Actor {
	private Sex sex;
	private int age;
	private Image characterPicture;
	private RpgClass rpgClass;
	
	private int xp;
	private int gold;
	
	private Inventory inventory;
	private Loadout loadout;	
	private Skills skills;
	
	private double carryWeigth;
	private double totalCarryWeigth;

	public Character(int str, int dex, int con, int intl, int cha, int lck, double maxHP, String descritpion,
			String name, int lvl, Encyclopedia encyclopedia, ActorID id, Sex sex, int age, Image characterPicture,
			RpgClass rpgClass) {
		super(str, dex, con, intl, cha, lck, maxHP, descritpion, name, lvl, id, encyclopedia);
		this.sex = sex;
		this.age = age;
		this.characterPicture = characterPicture;
		this.rpgClass = rpgClass;
		this.xp =0;
		this.gold = 100;
		this.inventory = encyclopedia.getStartingInventory(rpgClass);
		this.loadout = encyclopedia.getStartingLoadout(rpgClass);
		this.skills = encyclopedia.calculateStartingSkills(characteristics);
		this.carryWeigth = inventory.getTotalWeigth() + loadout.getTotalWeigth();
		this.totalCarryWeigth = characteristics.getCarryWeigth();
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

}
