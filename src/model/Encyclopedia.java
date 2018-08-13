package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


import model.Items.Item;
import model.Items.ItemID;
import model.Items.Shield;
import model.Items.Weapon;
import model.Items.WeaponHands;
import model.Items.Armor;
import model.Items.ArmorClass;
import model.Items.ArmorPiece;
import model.Items.Consumable;
import model.Items.ConsumableId;
import model.character.Character;
import model.character.Inventory;
import model.character.Loadout;
import model.character.RpgClass;
import model.character.Skills;
import model.character.SkillsType;

public class Encyclopedia {

	

	//this list contains the xp necessary to gain a level for each lvl in the game
	private List<Integer> xpToLvlUp;
	//this Map contains every item of the game usable by the character, the string is the name
	private Map<String, Item> allItems;
	
	//this Map contains all the items exclusive to enemies, the string is the name
	private Map<String, Item> enemyItems;
	
	//this Map contains every single enemy of the game, the string is the name
	private Map<String, Enemy> allEnemies;

	
	private Random r;
	
	public  Encyclopedia() {
		this.xpToLvlUp = new ArrayList<Integer>();
		this.allItems = new HashMap<String, Item>();
		this.enemyItems = new HashMap<String, Item>();
		this.allEnemies = new HashMap<String, Enemy>();
		this.r = new Random();
		//
		//Here we add hardcoded objects to each list, one by one.
		//
		
		//XP FOR EACH LEVEL
		
		//Level 1
		xpToLvlUp.add(0);
		//Level 2
		xpToLvlUp.add(100);
		//Level 3
		xpToLvlUp.add(250);
		//Level 4
		xpToLvlUp.add(500);
		//Level 5
		xpToLvlUp.add(1000);
		//Level 6
		xpToLvlUp.add(2000);
		//Level 7
		xpToLvlUp.add(3500);
		//Level 8
		xpToLvlUp.add(5000);
		//Level 9
		xpToLvlUp.add(7000);
		//Level 10
		xpToLvlUp.add(10000);
		//Level 11 (unreachable)
		xpToLvlUp.add(Integer.MAX_VALUE);
		
		//ITEMS
		
		
		//ENEMY WEAPONS
		List<String> successText = new ArrayList<String>();
		List<String> failText = new ArrayList<String>();
		
		successText.add("Its shiv hits you, cutting a serious wound");
		failText.add("The shiv barely scratches your armor");
		
		enemyItems.put("Goblin Shiv", new Weapon("Goblin Shiv", "Small and pointy", 5, 2d, ItemID.ONEHANDED, 8d,2d,0d,0d,WeaponHands.ONEHANDED,1,20d,1.5d,successText,failText));
		
		successText = new ArrayList<String>();
		failText = new ArrayList<String>();
		//ENEMY ARMORS
		
		enemyItems.put("Old Rag", new Armor("Old Rag", "It's stinky", 2, 2, ItemID.ARMOR, 1, 10d, 10d, ArmorPiece.CHEST, ArmorClass.LIGHT));
		
		//CHARACTER WEAPONS
		
		successText.add("You stabbed your opponent!");
		failText.add("You swing your dagger unsuccesfully..");
		allItems.put("Iron Dagger", new Weapon("Iron Dagger", "Once it fell so hard they made a city about it", 10,3d,ItemID.ONEHANDED,6,2,0,0,WeaponHands.ONEHANDED,1,33.3d,3d,successText,failText));
		successText = new ArrayList<String>();
		failText = new ArrayList<String>();
		
		successText.add("You slash a new wound in the foul creature!");
		failText.add("You barely inflict a scratch..");
		allItems.put("Short Iron Sword", new Weapon("Short Iron Sword", "Short but effective", 20,9d,ItemID.ONEHANDED,8,4,0,0,WeaponHands.ONEHANDED,2,20d,2.5d,successText,failText));
		successText = new ArrayList<String>();
		failText = new ArrayList<String>();
		
		successText.add("Your arrow hits the creature between its eyes!");
		failText.add("Your arrow flies past your enemy into a wall..");
		allItems.put("Short Wooden Bow", new Weapon("Short Wooden Bow", "Small and lightweight", 25,5d,ItemID.BOW,12,4,0,0,WeaponHands.TWOHANDED,2,25d,3d,successText,failText));
		successText = new ArrayList<String>();
		failText = new ArrayList<String>();
		
		successText.add("Your magic dart splashes in the monster's face!");
		failText.add("Your wand fizzles and nothing happens, how embarassing");
		allItems.put("Inferior Magic Wand", new Weapon("Inferior Magic Wand", "Capable of a little more than tricks", 40,3d,ItemID.MAGICWAND,12,2,0,0,WeaponHands.ONEHANDED,2,15d,2d,successText,failText));
		successText = new ArrayList<String>();
		failText = new ArrayList<String>();
		
		//CHARACTER SHIELDS
		allItems.put("Wooden Shield", new Shield("Wooden Shield", "It's made of rotten wood", 10, 5, ItemID.SHIELD, WeaponHands.ONEHANDED, 1, 8d, 2d, ArmorClass.LIGHT));
		
		
		//CHARACTER ARMORS
		
		allItems.put("Iron Helmet", new Armor("Iron Helmet", "Just like in Skyrim", 60, 5, ItemID.ARMOR, 2, 15d, 3d, ArmorPiece.HELMET, ArmorClass.HEAVY));
		allItems.put("Iron Armor", new Armor("Iron Armor", "Just so you won't be naked", 125, 30, ItemID.ARMOR, 3, 25d, 10d, ArmorPiece.CHEST, ArmorClass.HEAVY));
		allItems.put("Iron Greaves", new Armor("Iron Greaves", "Sturdy and effective", 40, 15, ItemID.ARMOR, 2, 10d, 5d, ArmorPiece.LEGS, ArmorClass.HEAVY));
		allItems.put("Iron Gauntlets", new Armor("Iron Gauntlets", "Sturdy and effective", 25, 5, ItemID.ARMOR, 2, 10d, 1d, ArmorPiece.GAUNTLETS, ArmorClass.HEAVY));
		allItems.put("Iron Boots", new Armor("Iron Boots", "Sturdy and effective", 25, 6, ItemID.ARMOR, 2, 10d, 2d, ArmorPiece.BOOTS, ArmorClass.HEAVY));
		
		allItems.put("Hide Helmet", new Armor("Hide Helmet", "Made out of a wolf's head", 25, 2, ItemID.ARMOR, 1, 10d, 8d, ArmorPiece.HELMET, ArmorClass.LIGHT));
		allItems.put("Hide Armor", new Armor("Hide Armor", "Makes you feel like belonging in the wilderness", 50, 5, ItemID.ARMOR, 2, 20d, 10d, ArmorPiece.CHEST, ArmorClass.LIGHT));
		allItems.put("Hide Leggings", new Armor("Hide Leggings", "Can't go around naked", 15, 2, ItemID.ARMOR, 1, 5d, 5d, ArmorPiece.LEGS, ArmorClass.LIGHT));
		allItems.put("Hide Bracers", new Armor("Hide Bracers", "Minimal protection for your arms", 10, 1, ItemID.ARMOR, 1, 5d, 5d, ArmorPiece.GAUNTLETS, ArmorClass.LIGHT));
		allItems.put("Hide Boots", new Armor("Hide Boots", "Better than barefeet", 10, 1, ItemID.ARMOR, 1, 5d, 5d, ArmorPiece.BOOTS, ArmorClass.LIGHT));
		
		//CHARACTER MISCELLANEOUS
		
		allItems.put("Goblin Amulet", new Item("Goblin Amulet", "Made out of mysterious bones", 20, 0.5, ItemID.TREASURE, 1));
		
		//CHARACTER CONSUMABLES
		
		allItems.put("Small Health Potion", new Consumable("Small Health Potion", "Restores a small amount of health", 17, 0.5d, ItemID.CONSUMABLE, 1, ConsumableId.POTION, 15));
		
		//ENEMIES
		allEnemies.put("Small Goblin", new Enemy(15d, "Small and green, it watches you from the darkness", "Small Goblin",
				 1, ActorID.GOBLIN, this, 70d,25d,5d,(Weapon)(enemyItems.get("Goblin Shiv")), (Armor)(enemyItems.get("Old Rag")), 20, 20, 2, 10, 10));
		
		
		
	}
	
	public Inventory getStartingInventory(RpgClass rpgClass) {
		Inventory startingInventory = new Inventory();
		if (rpgClass == RpgClass.WARRIOR) {
			startingInventory.add(new Item("Lucky Amulet", "A symbol of your new adventure", 100, 0.5, ItemID.TREASURE, 1));
			startingInventory.add(allItems.get("Small Health Potion"));
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
		Loadout startingLoadout = new Loadout();
		if (rpgClass == RpgClass.WARRIOR) {
			startingLoadout.equip((Armor)allItems.get("Iron Helmet"));
			startingLoadout.equip((Armor)allItems.get("Hide Boots"));
			startingLoadout.equip((Armor)allItems.get("Hide Armor"));
			startingLoadout.equip((Weapon)allItems.get("Short Iron Sword"));
			startingLoadout.equip((Shield)allItems.get("Wooden Shield"));
			
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

	
	public List<Integer> getXpToLevelUp(){
		return xpToLvlUp;
	}
	
	//in the following methods the character is necessary to provide Luck and other relevant characteristics/skills
	
	public List<Item> getLoot(Enemy e, Character c){

		List<Item> result = new ArrayList<Item>();
		
		//noted that the highChance is actually useless, since all 3 percentages add up to 100 we actually need only the first 2
		//we are using all 3 only to increase the lowChance in case of a Luck < 50

		//with a luck of 0 or 100 the percentageChance is 33, with luck 50 is 0
		double percentageChange = Math.abs(c.getCharacteristics().getLck()-50)/1.5151515151; 
		double diff;
		double adjustedLowChance;
		double adjustedHighChance;
		if (c.getCharacteristics().getLck()>50) {
			//diff is how much the lowRewardChance changes in absolute value, this amount will be added to the highRewardChance
			diff = e.getLowRewardChance() - e.getLowRewardChance()*(100-percentageChange)/100;
			adjustedLowChance = e.getLowRewardChance()*(100-percentageChange)/100;
			adjustedHighChance = e.getHighRewardChance() + diff;
		}
		else {
			diff = e.getHighRewardChance() - e.getHighRewardChance()*(100-percentageChange)/100;
			adjustedHighChance = e.getHighRewardChance()*(100-percentageChange)/100;
			adjustedLowChance = e.getLowRewardChance() + diff;
		}

		//dungeoneering slightly shiftes some percentage from medium to high, 10% at max
		//this means that the medium chance should never be less than 10%
		double adjustedMediumChance = e.getMediumRewardChance()-(c.getSkills().getDungeoneering()/100);
		adjustedHighChance = adjustedHighChance + (c.getSkills().getDungeoneering()/100);

		//the amount of rolls is 70% of the times 1, 20% of the times 2 and 10% of the times 3
		int amountOfRolls;
		double rollForRolls = r.nextDouble()*100;
		if(rollForRolls < 70)
			amountOfRolls =1;
		else
			if (rollForRolls <90)
				amountOfRolls = 2;
			else 
				amountOfRolls = 3;
		for (int i =0 ; i<amountOfRolls; i++) {
			double rollForLoot = r.nextDouble()*100;
			
			//remember that for r.nextInt the amount we put between brackets is excluded
			if (rollForLoot < adjustedLowChance) {
				//the item will be of a level that is between the character level (included) and 5 levels lower
				result.add(getItem(c.getLvl()-r.nextInt(6)));
			}else {
				if (rollForLoot < adjustedLowChance + adjustedMediumChance) {
					//the item will be of the same level of the character or -1
					result.add(getItem(c.getLvl()-r.nextInt(2)));
				}else {
					//the item will be between character level+1 and character level+3
					result.add(getItem(c.getLvl()+1+r.nextInt(3)));
				}		
			}
		}
		return result;
	}

	public Map<Item, Integer> getShopList(Character c){
		
		Map<Item, Integer> result = new HashMap<Item, Integer>();
		//100 gold for each level plus a bonus up to 20% based on haggling
		int maxValueOfShop = c.getLvl()*100 * ( 1 + c.getSkills().getHaggling()/500);
		
		//at chosen levels (5, 10 and 20) the value of the shop doubles
		if (c.getLvl() >= 5)
			maxValueOfShop *= 2;
		if (c.getLvl() >= 10)
			maxValueOfShop *= 2;
		if (c.getLvl() >= 20)
			maxValueOfShop *= 2;
		
		int currentValueOfShop = 0;
		Item toAdd;

		while(currentValueOfShop<maxValueOfShop){
			//in the shop all items are between -2 nd +2 of the character Level
			int	itemLevel = (r.nextInt(5) -2) + c.getLvl();
			int quantity;
			toAdd = getItem(itemLevel);
			//consumables can have a quantity between 1 and 10, other items have quantity of 1
			if (toAdd.getId() == ItemID.CONSUMABLE)
				quantity = 1 + r.nextInt(10);
			else 
				quantity = 1;
			currentValueOfShop += toAdd.getValue()*quantity;
			
			//this control is needed in case the same item gets rolled more than once
			if (result.containsKey(toAdd))
				result.replace(toAdd,result.get(toAdd)+quantity);
			else
				result.put(toAdd, quantity);
		}
		return result;
	}
	
	public Enemy getEnemy(Character c) {
		//dungeoneering skill heavily skews the enemy level
		//the maximum level for the enemies should be the same as the character, and a skill of 100 dungeoneering shoul guarantee that all enemies
		//are lower level than the character. This way the player won't miss out on any cool enemy just for having high dungeoneering
		//it will simply be easier to find lower level enemies.
		//100 in dungeoneering means that enemy level will be less or equal character level (-5 ,0)
		//0 in dungeoneering means that enemy level will be more or equal character level (0, +5)
		int enemyLevel = c.getLvl() + (int)Math.round((r.nextDouble()*5)- c.getSkills().getDungeoneering()/20);
		if (enemyLevel<1)
			enemyLevel = 1;
		if (enemyLevel>20)
			enemyLevel = 20;
		List<Enemy> enemies = new ArrayList<Enemy>(allEnemies.values());
		List<Enemy> enemiesWithCorrectLevel = new ArrayList<Enemy>();
		//using streams and filters would be better
		//the maximum level of the enemies should be the same as the character, so that having 100 dungeoneering won't prevent the character from finding cool enemies
		for (Enemy e : enemies) {
			if (e.getLvl() == enemyLevel)
				enemiesWithCorrectLevel.add(e);
		}
		int rollForItem = r.nextInt(enemiesWithCorrectLevel.size());
		
		return enemiesWithCorrectLevel.get(rollForItem);
	}
	
	public Item getItem(int level) {
		if (level<1)
			level = 1;
		List<Item> items = new ArrayList<Item>(allItems.values());
		List<Item> itemsWithCorrectLevel = new ArrayList<Item>();
		//using streams and filters would be better
		for (Item i : items) {
			if (i.getLevel() == level)
				itemsWithCorrectLevel.add(i);
		}
		//maybe there could be at least one level that has no items, but is shouldn't happen for more than 2 consecutive levels
		//remember that the highest level item in the game should be the highest level of the character +3
		if (itemsWithCorrectLevel.isEmpty())
			for (Item i : items) {
				if (i.getLevel() == level-1)
					itemsWithCorrectLevel.add(i);
			}
		//the last number is escluded, if the list has  10 items we will get a number between 0 and 9
		//this means that we can pick the item that is at index between 0 and 9
		//all items of the same level have equal chance of getting picked.
		int rollForItem = r.nextInt(itemsWithCorrectLevel.size());
	
		return itemsWithCorrectLevel.get(rollForItem);
	}
	
}
