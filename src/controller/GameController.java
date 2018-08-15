package controller;

import model.character.Character;
import model.character.RpgClass;
import model.character.Sex;
import model.character.SkillsType;
import persistance.GameLoader;
import persistance.GameSaver;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import exceptions.LoadException;
import model.*;
import model.Items.InventoryEntry;
import model.Items.Item;
import model.Items.ShopEntry;

public class GameController {

	private Character character;
	private Encyclopedia encyclopedia;
	private Save save;
	private String currentSavePath;
	private GameSaver gameSaver;
	private GameLoader gameLoader;
	private Shop villageShop;
	
	public GameController() {
		this.encyclopedia = new Encyclopedia();
		this.gameSaver = new GameSaver();
		this.gameLoader = new GameLoader();
		this.villageShop = new Shop();
		
		//be careful, until we load a game (or make a new save file) the controller won't have a character and a save
		this.character = null;
		this.save = null;
		this.currentSavePath = "";
	}
	
	
	public void newGame(String savePath, String saveName) {
		this.save = new Save(saveName, null, Location.VILLAGE, 0, 0, LocalDateTime.now(), LocalDateTime.now());
		this.currentSavePath = savePath;
		save();
	}
	
	public void setStartingCharacter(int str, int dex, int con, int intl, int cha, int lck, String name, Sex sex, int age,
			String characterPicture, RpgClass rpgClass, SkillsType[] primarySkills, SkillsType[] secondarySkills) {
		
		this.character = Character.createNewCharacter(new Characteristics(str, dex, con, intl, cha, lck), "", name, ActorID.PLAYER,
				this.encyclopedia, sex, age, characterPicture, rpgClass, primarySkills, secondarySkills);
		this.save.setCharacter(this.character);
	}
	
	public void save() {
		save.setTimeOfSave(LocalDateTime.now());
		gameSaver.save(save, currentSavePath);
	}
	
	public boolean preLoad(String savePath) {
		Save toLoad;
		try {
		toLoad = gameLoader.load(savePath);
		}
		catch (LoadException e){
			return false;
		}
		if (toLoad == null)
			return false;
		//if you press new game but don't complete the character creation then the file will be created but with a null character
		//this file is unable to be used and shouldn't be considered
		if (toLoad.getCharacter() == null)
				return false;
		return true;	
	}
	
	public Save load(String savePath) throws LoadException {
		return gameLoader.load(savePath);
		//this.currentSavePath = savePath;
		//this.character = save.getCharacter();
		
	}
	public void load(String savePath, boolean setAsCurrent) throws LoadException {
		
		this.save = gameLoader.load(savePath);
		this.currentSavePath = savePath;
		this.character = save.getCharacter();
		
	}
	
	public Character getCharacter() {
		return character;
	}

	public Encyclopedia getEncyclopedia() {
		return encyclopedia;
	}
	
	public Shop getShop() {
		return villageShop;
	}
	
	
	public void shopRefresh() {
		villageShop.setShop(encyclopedia.getShopList(character));
		villageShop.setPriceModifier(character);
		villageShop.setAvaiableGold(character);
		villageShop.setShopEntries();
	}
		
	public List<ShopEntry> getChInventoryForShop(){
		List <ShopEntry> result = new ArrayList<ShopEntry>();
		for(Item i : character.getInventory().getItems()) {
			result.add(new ShopEntry(i.getName(),
					Collections.frequency(character.getInventory().getItems(), i),
					(int) Math.round(i.getValue()/villageShop.getPriceModifier())));
		}
		return result;			
		
	}
	
	public List<InventoryEntry> getInventoryForTable(){
		List <InventoryEntry> result = new ArrayList<InventoryEntry>();
		for(Item i : character.getInventory().getItems()) {
			result.add(new InventoryEntry(i.getName(),
					i.getDescription(),
					Collections.frequency(character.getInventory().getItems(), i)
					));
		}
		return result;	
	}
	
}
