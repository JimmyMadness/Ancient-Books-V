package model.character;

import java.util.*;

import events.CarryWeightEvent;
import events.CarryWeightListener;
import model.Items.*;


public class Inventory {

	private Loadout loadout;
	private List<Item> items;

	private CarryWeightListener weightListener;
	
	public Inventory() {
		this.loadout = new Loadout();
		this.items = new ArrayList<Item>();
	}
		
	public Loadout getLoadout() {
		return loadout;
	}
	
	public void setLoadout(Loadout loadout) {
		this.loadout = loadout;
		
	}

	
	public List<Item> getItems() {
		return items;
	}

	
	
	public void setWeigthListener(CarryWeightListener weightListener) {
		this.weightListener = weightListener;
	}

	public void add(Item i) {
		this.items.add(i);
		weightListener.onCarryWeight(new CarryWeightEvent(this, getTotalWeight()));
		
	}
	
	//unequipping gets directly called on the loadout
	
	public void equip(EquippableItemHands e) {
		List<EquippableItemHands> unequippedItems = loadout.equip(e);
		//i unwrap what i got back
		for(EquippableItemHands u : unequippedItems)
			items.add(u);
	}
	public void equip(Armor e) {
		Optional<Armor> unequippedArmor = loadout.equip(e);
		if (unequippedArmor.isPresent())
			this.items.add(unequippedArmor.get());
	}
	
	//this has to be called when items are about to be added to the player's inventory, to check if they can carry them
	//items get added that can surpass the weight limit when you buy and when you finish a battle
	public double getTotalWeight() {
		int result = 0;
		for (Item i : items)
			result += i.getWeight();
		result += loadout.getTotalWeight();
		return result;
	}




}
