package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import model.Items.Item;
import model.character.Inventory;
import model.character.Loadout;
import model.character.RpgClass;
import model.character.Skills;
import model.character.SkillsType;

public class Encyclopedia {

	//every level of the character allows more expensive items in the shop
	private List<Integer> totalGoldValuesOfShop;
	//this list contains the xp necessary to gain a level for each lvl in the game
	private List<Integer> xpToLvlUp;
	//this Set contains every single item of the game
	private Set<Item> allItems;
	//this Set contains every single enemy of the game
	private Set<Enemy> allEnemies;
	
	private Random r;
	
	public  Encyclopedia() {
		this.totalGoldValuesOfShop = new ArrayList<Integer>();
		this.xpToLvlUp = new ArrayList<Integer>();
		this.allItems = new HashSet<Item>();
		this.allEnemies = new HashSet<Enemy>();
		this.r = new Random();
		//
		//Here we add hardcoded objects to each list, one by one.
		//
	}
	
	public Inventory getStartingInventory(RpgClass rpgClass) {
		Inventory startingInventory = null;
		if (rpgClass == RpgClass.WARRIOR) {
			//hard coded starting inventory
		}
		if (rpgClass == RpgClass.MAGE) {
			//hard coded starting inventory
		}
		if (rpgClass == RpgClass.ROGUE) {
			//hard coded starting inventory
		}
		
		return startingInventory;
	}

	public Loadout getStartingLoadout(RpgClass rpgClass) {
		Loadout startingLoadout = null;
		if (rpgClass == RpgClass.WARRIOR) {
			//hard coded starting loadout
		}
		if (rpgClass == RpgClass.MAGE) {
			//hard coded starting loadout
		}
		if (rpgClass == RpgClass.ROGUE) {
			//hard coded starting loadout
		}
		
		return startingLoadout;
	}

	public Skills getStartingSkills(Characteristics characteristics, SkillsType[] primarySkills, SkillsType[] secondarySkills) {
		int oneHanded = 0;
		int twoHanded = 0;
		int archery = 0;
		int block = 0;
		int dodge = 0;
		int lightArmor = 0;
		int heavyArmor = 0;
		int criticalStrike = 0;
		int magicPower = 0;
		int haggling = 0;
		int dungeoneering = 0;
		
		for (SkillsType t : primarySkills)
			if (t == SkillsType.ONEHANDED)
				oneHanded +=5;
		for (SkillsType t : primarySkills)
			if (t == SkillsType.TWOHANDED)
				twoHanded +=5;
		for (SkillsType t : primarySkills)
			if (t == SkillsType.ARCHERY)
				archery +=5;
		for (SkillsType t : primarySkills)
			if (t == SkillsType.BLOCK)
				block +=5;
		for (SkillsType t : primarySkills)
			if (t == SkillsType.DODGE)
				dodge +=5;
		for (SkillsType t : primarySkills)
			if (t == SkillsType.LIGHTARMOR)
				lightArmor +=5;
		for (SkillsType t : primarySkills)
			if (t == SkillsType.HEAVYARMOR)
				heavyArmor +=5;
		for (SkillsType t : primarySkills)
			if (t == SkillsType.CRITICALSTRIKE)
				criticalStrike +=5;
		for (SkillsType t : primarySkills)
			if (t == SkillsType.MAGICPOWER)
				magicPower +=5;
		for (SkillsType t : primarySkills)
			if (t == SkillsType.HAGGLING)
				haggling +=5;
		for (SkillsType t : primarySkills)
			if (t == SkillsType.DUNGEONEERING)
				dungeoneering +=5;
		
		oneHanded += 2 + (int)(characteristics.getStr()*0.2) + (int)(characteristics.getLck()/20);
		twoHanded += 2 + (int)(characteristics.getStr()*0.2) + (int)(characteristics.getLck()/20);
		archery += 2 + (int)(characteristics.getDex()*0.2) + (int)(characteristics.getLck()/20);
		block += 2 + (int)(characteristics.getStr()*0.2) + (int)(characteristics.getLck()/20);
		dodge += 2 + (int)(characteristics.getDex()*0.2) + (int)(characteristics.getLck()/20);
		lightArmor += 2 + (int)(characteristics.getCon()*0.2) + (int)(characteristics.getLck()/20);
		heavyArmor += 2 + (int)(characteristics.getCon()*0.2) + (int)(characteristics.getLck()/20);
		criticalStrike += 2 + (int)(characteristics.getDex()*0.2) + (int)(characteristics.getLck()/20);
		magicPower += 2 + (int)(characteristics.getIntl()*0.2) + (int)(characteristics.getLck()/20);
		haggling += 2 + (int)(characteristics.getCha()*0.2) + (int)(characteristics.getLck()/20);
		dungeoneering = 2 + (int)(characteristics.getIntl()*0.2) + (int)(characteristics.getLck()/20);
		
		return new Skills(primarySkills, secondarySkills, oneHanded, twoHanded, archery, block, dodge, lightArmor, heavyArmor, criticalStrike, magicPower, haggling, dungeoneering);
	}

	
	//in the following methods the character is necessary to provide Luck and other relevant characteristics/skills
	public List<Item> getLoot(Enemy e, Character c){
		// TODO
		return null;
	}
	
	public List<Item> getShopList(Character c){
		//TODO
		return null;
	}
	
	public Enemy getEnemy(Character c) {
		//TODO
		return null;
	}
}
