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
		// TODO Auto-generated method stub
		return null;
	}

	public Loadout getStartingLoadout(RpgClass rpgClass) {
		// TODO Auto-generated method stub
		return null;
	}

	public Skills getStartingSkills(Characteristics characteristics, SkillsType[] primarySkills, SkillsType[] secondarySkills) {
		// TODO Auto-generated method stub
		return null;
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
