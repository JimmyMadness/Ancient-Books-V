package model.character;

import java.util.ArrayList;
import java.util.Optional;

import model.Items.*;

public class HandsLoadout {
	private ArrayList<Optional<EquippableItemHands>> items;
	
	public HandsLoadout() {
		items = new ArrayList<Optional<EquippableItemHands>>();
	}
	
	
	
	private ArrayList<Optional<EquippableItemHands>> getItems() {
		return items;
	}



	//the caller will make sure to not call this more than twice
	public void add(Optional<EquippableItemHands> e) {
		items.add(e);
	}

	public static void unWrap(Inventory i, HandsLoadout l) {
		for (Optional<EquippableItemHands> e : l.getItems()) {
			if (e.isPresent())
				i.add(e.get());
		}
			
	}
	
}
