package model;
import java.io.Serializable;
import java.time.LocalDateTime;
import model.character.Character;

public class Save implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4637244786790527746L;
	private String saveName;
	private Character character;
	private Location location;
	private int totalGoldGained;
	private int enemiesDefeated;
	private LocalDateTime timeAdventureStart;
	private LocalDateTime timeOfSave;
	
	
	
	public Save(String saveName, Character character, Location location, int totalGoldGained, int enemiesDefeated,
			LocalDateTime timeAdventureStart, LocalDateTime timeOfSave) {
		super();
		this.saveName = saveName;
		this.character = character;
		this.location = location;
		this.totalGoldGained = totalGoldGained;
		this.enemiesDefeated = enemiesDefeated;
		this.timeAdventureStart = timeAdventureStart;
		this.timeOfSave = timeOfSave;
	}
	
	public String getSaveName() {
		return saveName;
	}
	
	public Character getCharacter() {
		return character;
	}
	public Location getLocation() {
		return location;
	}
	public int getTotalGoldGained() {
		return totalGoldGained;
	}
	public int getEnemiesDefeated() {
		return enemiesDefeated;
	}
	public LocalDateTime getTimeAdventureStart() {
		return timeAdventureStart;
	}
	public LocalDateTime getTimeOfSave() {
		return timeOfSave;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	
	
	public void setCharacter(Character character) {
		this.character = character;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public void setTotalGoldGained(int totalGoldGained) {
		this.totalGoldGained = totalGoldGained;
	}

	public void setEnemiesDefeated(int enemiesDefeated) {
		this.enemiesDefeated = enemiesDefeated;
	}

	public void setTimeOfSave(LocalDateTime timeOfSave) {
		this.timeOfSave = timeOfSave;
	}
	
	
	
}
