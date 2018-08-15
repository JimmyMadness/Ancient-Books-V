package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import model.character.Character;
import model.Items.Item;
import model.Items.ShopEntry;

import java.util.List;

public class Shop {
	
	private Map<Item, Integer> items;
	private double priceModifier;
	private int avaiableGold;
	private List<ShopEntry> shopEntries;
	
	public Shop() {
		priceModifier = 1;
		items = new HashMap<Item, Integer>();
		avaiableGold=0;
		shopEntries = new ArrayList<ShopEntry>();
	}
	
	public void setShop(Map<Item, Integer> items) {
		this.items = items;
	}
	
	public void setShopEntries() {
		shopEntries = new ArrayList<ShopEntry>();
		for(Item i : items.keySet()) {
			shopEntries.add(new ShopEntry(i.getName(), items.get(i), (int) Math.round(i.getValue()*priceModifier)));
		}
	}
	
	public List<ShopEntry> getShopEntries(){
		return shopEntries;
	}

	public double getPriceModifier() {
		return priceModifier;
	}

	public void setPriceModifier(Character c) {
		this.priceModifier = 3.3-(1.3* ((c.getSkills().getHaggling()*3 + c.getCharacteristics().getCha()*2)/5)/100);
	}
	
	public int getAvaiableGold() {
		return avaiableGold;
	}

	
	//remember to refresh avaiable gold only if the character had at least one fight after last visit
	public void setAvaiableGold(Character c) {
		Random r = new Random();
		this.avaiableGold = (r.nextInt(100)+50)*c.lvl+200;
	}

	public Map<Item, Integer> getItems() {
		return items;
	}

	// check with canBuy before calling this
	public void buy(Item i, Character c) {
		if (items.containsKey(i))
			items.replace(i,items.get(i)+1);
		else
			items.put(i, 1);
		int transactionGold = (int)Math.round((i.getValue()/priceModifier));
		avaiableGold -= transactionGold;
		c.setGold(c.getGold()+transactionGold);
		c.getInventory().remove(i);
		setShopEntries();
		
	}
	
	
	//check with canSell before calling this
	public void sell(Item i, Character c) {
		items.replace(i, items.get(i)-1);
		if (items.get(i) == 0)
			items.remove(i);
		int transactionGold = (int)Math.round((i.getValue()*priceModifier));
		avaiableGold += transactionGold;
		c.setGold(c.getGold()-transactionGold);
		c.getInventory().add(i);
		setShopEntries();
	}
	
	public boolean canSell(Item i, Character c) {
		int transactionGold = (int)Math.round((i.getValue()*priceModifier));
		return c.canAddToInventory(i) && (c.getGold()>=transactionGold);
	}
	
	public boolean canBuy(Item i) {
		int transactionGold = (int)Math.round((i.getValue()/priceModifier));
		return transactionGold < avaiableGold;
	}
	
}
