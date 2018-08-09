package model.character;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Items.*;

public class Loadout {
	private Optional<Armor> helmet;
	private Optional<Armor> chest;
	private Optional<Armor> legs;
	private Optional<Armor> gauntlets;
	private Optional<Armor> boots;
	//if the equipped item is two-Handed we only equip it in one hand and keep the other free but locked
	private Optional<EquippableItemHands> rightHand;
	private Optional<EquippableItemHands> leftHand;
	
	private boolean twoHandedPresent = false;
	
	
	public Loadout() {
		this.helmet = Optional.empty();
		this.chest = Optional.empty();
		this.gauntlets = Optional.empty();
		this.boots = Optional.empty();
		this.rightHand = Optional.empty();
		this.leftHand = Optional.empty();
	}

	public Optional<Armor> getHelmet() {
		return helmet;
	}

	public Optional<Armor> getChest() {
		return chest;
	}

	public Optional<Armor> getLegs() {
		return legs;
	}

	public Optional<Armor> getGauntlets() {
		return gauntlets;
	}

	public Optional<Armor> getBoots() {
		return boots;
	}

	public Optional<EquippableItemHands> getRightHand() {
		return rightHand;
	}

	public Optional<EquippableItemHands> getLeftHand() {
		return leftHand;
	}

	public boolean isTwoHandedPresent() {
		return twoHandedPresent;
	}


	//if this returns true then the inventory needs to add the item e
	public boolean remove(Armor e) {
		if (e.getArmorPiece() == ArmorPiece.HELMET) {
			if (this.getHelmet().isPresent()) {
				//i use equals cause the item that has to be removed is the exact one that is equipped
				//and the caller of this method is supposed to know exactly which istance is in the loadout
				if (e.equals(getHelmet().get())){
					this.helmet = Optional.empty();
					return true;
				}
			}
		}
		if (e.getArmorPiece() == ArmorPiece.CHEST) {
			if (this.getChest().isPresent()) {
				if (e.equals(getChest().get())){
					this.chest = Optional.empty();
					return true;
				}
			}
		}
		if (e.getArmorPiece() == ArmorPiece.GAUNTLETS) {
			if (this.getGauntlets().isPresent()) {
				if (e.equals(getGauntlets().get())){
					this.gauntlets = Optional.empty();
					return true;
				}
			}
		}
		if (e.getArmorPiece() == ArmorPiece.LEGS) {
			if (this.getLegs().isPresent()) {
				if (e.equals(getLegs().get())){
					this.legs = Optional.empty();
					return true;
				}
			}
		}
		if (e.getArmorPiece() == ArmorPiece.BOOTS) {
			if (this.getBoots().isPresent()) {
				if (e.equals(getBoots().get())){
					this.boots = Optional.empty();
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean remove(EquippableItemHands e) {
		if (e instanceof Weapon) {
			if (this.getRightHand().isPresent()) {
				if (e.equals(getRightHand().get())){
					this.rightHand = Optional.empty();
					twoHandedPresent = false;
					return true;
				}
			}
		}
		if (e instanceof Weapon) {
			if (this.getLeftHand().isPresent()) {
				if (e.equals(getLeftHand().get())){
					this.leftHand = Optional.empty();
					twoHandedPresent = false;
					return true;
				}
			}
		}
		
		return false;
	}

	//this method returns the previously equipped item if any (hence the optional)
	//this method is always succesful, every piee of armor is equippable. What is optional is the return value
	public Optional<Armor> equip(Armor e){
		
		Optional<Armor> previousItem = Optional.empty();
		
		if (e.getArmorPiece() == ArmorPiece.HELMET) {
			previousItem = getHelmet();
			this.helmet = Optional.of(e);
		}	
		if (e.getArmorPiece() == ArmorPiece.CHEST) {
			previousItem = getChest();
			this.chest = Optional.of(e);
		}
		if (e.getArmorPiece() == ArmorPiece.GAUNTLETS) {
			previousItem = getGauntlets();
			this.gauntlets = Optional.of(e);
		}
		if (e.getArmorPiece() == ArmorPiece.LEGS) {
			previousItem = getLegs();
			this.legs = Optional.of(e);
		}	
		if (e.getArmorPiece() == ArmorPiece.BOOTS) {
			previousItem = getBoots();
			this.boots = Optional.of(e);
		}
			
		return previousItem;
	}
	
	//in case of equipping something in the hands slot things are different
	//this because the character could be holding two separate one handed items
	//and in that case we are supposed to return them both, for this reason we created a class
	//called HandLoadout capable of holding 0-2 items in it, it has a method that allows the inventory
	//to add all the unequipped items, between 0-2. The inventory that calls the equip method has to save the return value
	//and then unwrap it to get the items back in it.
	public List<EquippableItemHands> equip(EquippableItemHands e){
		List<EquippableItemHands> result = new ArrayList<EquippableItemHands>();
		if ((e.getHandsNumber() == WeaponHands.TWOHANDED)) {
			this.twoHandedPresent = true;
			if (rightHand.isPresent())
				result.add(rightHand.get());
			if (leftHand.isPresent())
				result.add(leftHand.get());
			rightHand = Optional.of(e);
			leftHand = Optional.empty();
		}
		else {
			twoHandedPresent = false;
			if (!rightHand.isPresent())
				rightHand = Optional.of(e);
			else
				if(!leftHand.isPresent())
					leftHand = Optional.of(e);
				else {
					result.add(rightHand.get());
					rightHand = Optional.of(e);
				}
					
		}
		return result;
		
	}
	
	public List<Weapon> getWeapons() {
		List<Weapon> result = new ArrayList<Weapon>();
		if (rightHand.isPresent())
			if (rightHand.get() instanceof Weapon)
				result.add((Weapon)rightHand.get());
		if (leftHand.isPresent())
			if (leftHand.get() instanceof Weapon)
				result.add((Weapon)leftHand.get());
		return result;
		
	}
	
	
	public double getTotalPhysicalArmorRate() {
		double result = 0;
		if (helmet.isPresent())
			result += helmet.get().getPhysicalArmorRate();
		if (chest.isPresent())
			result += chest.get().getPhysicalArmorRate();
		if (gauntlets.isPresent())
			result += gauntlets.get().getPhysicalArmorRate();
		if (legs.isPresent())
			result += legs.get().getPhysicalArmorRate();
		if (boots.isPresent())
			result += boots.get().getPhysicalArmorRate();
		if (rightHand.isPresent())
			if (rightHand.get()instanceof HasArmor)
				result += ((HasArmor)rightHand.get()).getPhysicalArmorRate();
		if (leftHand.isPresent())
			if (leftHand.get()instanceof HasArmor)
				result += ((HasArmor)leftHand.get()).getPhysicalArmorRate();
		return result;
	}
	
	public double getTotalMagicalArmorRate() {
		double result = 0;
		if (helmet.isPresent())
			result += helmet.get().getMagicalArmorRate();
		if (chest.isPresent())
			result += chest.get().getMagicalArmorRate();
		if (gauntlets.isPresent())
			result += gauntlets.get().getMagicalArmorRate();
		if (legs.isPresent())
			result += legs.get().getMagicalArmorRate();
		if (boots.isPresent())
			result += boots.get().getMagicalArmorRate();
		if (rightHand.isPresent())
			if (rightHand.get()instanceof HasArmor)
				result += ((HasArmor)rightHand.get()).getMagicalArmorRate();
		if (leftHand.isPresent())
			if (leftHand.get()instanceof HasArmor)
				result += ((HasArmor)leftHand.get()).getMagicalArmorRate();
		return result;
	}
	
	public double getTotalWeight() {
		int result = 0;
		if (helmet.isPresent())
			result += helmet.get().getWeight();
		if (chest.isPresent())
			result += chest.get().getWeight();
		if (gauntlets.isPresent())
			result += gauntlets.get().getWeight();
		if (legs.isPresent())
			result += legs.get().getWeight();
		if (boots.isPresent())
			result += boots.get().getWeight();
		if (rightHand.isPresent())
			result += rightHand.get().getWeight();
		if (leftHand.isPresent())
			result += leftHand.get().getWeight();
		return result;
	}

}
