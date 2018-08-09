package model.Items;

public abstract class Item {
	protected String name;
	protected String description;
	protected int value;
	protected double weigth;
	protected ItemID id;
	protected int level;
	
	public Item(String name, String description, int value, double weigth, ItemID id, int level) {
		this.name = name;
		this.description = description;
		this.value = value;
		this.weigth = weigth;
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
		return weigth;
	}

	public ItemID getId() {
		return id;
	}
	
	
	
}
