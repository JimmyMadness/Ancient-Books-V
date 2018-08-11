package model.Items;

public class Consumable extends Item{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2007173647821713317L;
	
	private ConsumableId consumableId;
	private double effect;
	
	public Consumable(String name, String description, int value, double weight, ItemID id, int level, ConsumableId consumableId, double effect) {
		super(name, description, value, weight, id, level);
		this.consumableId = consumableId;
		this.effect = effect;
	}

	public ConsumableId getConsumableId() {
		return consumableId;
	}

	public double getEffect() {
		return effect;
	}

	
	
}
