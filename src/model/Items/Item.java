package model.Items;

import java.io.Serializable;

public class Item implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5258295990252560900L;
	protected String name;
	protected String description;
	protected int value;
	protected double weight;
	protected ItemID id;
	protected int level;
	
	public Item(String name, String description, int value, double weight, ItemID id, int level) {
		this.name = name;
		this.description = description;
		this.value = value;
		this.weight = weight;
		this.id = id;
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getValue() {
		return value;
	}

	public double getWeight() {
		return weight;
	}

	public ItemID getId() {
		return id;
	}

	public int getLevel() {
		return level;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	
	
}
