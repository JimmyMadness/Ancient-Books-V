package model.character;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Items.*;

public class Loadout implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6412865762321678854L;
	
	///
	///TODO: just found out the correct usage of optional, by design it is not recommended to store data
	///i will have to refactor this whole class to be able to work with null objects (with a neutral behavior)
	///instead of optionals
	///
	private Armor helmet;
	private Armor chest;
	private Armor legs;
	private Armor gauntlets;
	private Armor boots;
	//if the equipped item is two-Handed we only equip it in one hand and keep the other free but locked
	private EquippableItemHands rightHand;
	private EquippableItemHands leftHand;
	
	private boolean twoHandedPresent = false;
	
	
	public Loadout() {
		this.helmet = null;
		this.chest = null;
		this.gauntlets = null;
		this.boots = null;
		this.rightHand = null;
		this.leftHand = null;
	}

	public Optional<Armor> getHelmet() {
		return Optional.ofNullable(helmet);
	}

	public Optional<Armor> getChest() {
		return Optional.ofNullable(chest);
	}

	public Optional<Armor> getLegs() {
		return Optional.ofNullable(legs);
	}

	public Optional<Armor> getGauntlets() {
		return Optional.ofNullable(gauntlets);
	}

	public Optional<Armor> getBoots() {
		return Optional.ofNullable(boots);
	}

	public Optional<EquippableItemHands> getRightHand() {
		return Optional.ofNullable(rightHand);
	}

	public Optional<EquippableItemHands> getLeftHand() {
		return Optional.ofNullable(leftHand);
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
					this.helmet = null;
					return true;
				}
			}
		}
		if (e.getArmorPiece() == ArmorPiece.CHEST) {
			if (this.getChest().isPresent()) {
				if (e.equals(getChest().get())){
					this.chest = null;
					return true;
				}
			}
		}
		if (e.getArmorPiece() == ArmorPiece.GAUNTLETS) {
			if (this.getGauntlets().isPresent()) {
				if (e.equals(getGauntlets().get())){
					this.gauntlets = null;
					return true;
				}
			}
		}
		if (e.getArmorPiece() == ArmorPiece.LEGS) {
			if (this.getLegs().isPresent()) {
				if (e.equals(getLegs().get())){
					this.legs = null;
					return true;
				}
			}
		}
		if (e.getArmorPiece() == ArmorPiece.BOOTS) {
			if (this.getBoots().isPresent()) {
				if (e.equals(getBoots().get())){
					this.boots = null;
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
					this.rightHand = null;
					twoHandedPresent = false;
					return true;
				}
			}
		}
		if (e instanceof Weapon) {
			if (this.getLeftHand().isPresent()) {
				if (e.equals(getLeftHand().get())){
					this.leftHand = null;
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
			this.helmet = e;
		}	
		if (e.getArmorPiece() == ArmorPiece.CHEST) {
			previousItem = getChest();
			this.chest = e;
		}
		if (e.getArmorPiece() == ArmorPiece.GAUNTLETS) {
			previousItem = getGauntlets();
			this.gauntlets = e;
		}
		if (e.getArmorPiece() == ArmorPiece.LEGS) {
			previousItem = getLegs();
			this.legs = e;
		}	
		if (e.getArmorPiece() == ArmorPiece.BOOTS) {
			previousItem = getBoots();
			this.boots = e;
		}
			
		return previousItem;
	}
	
	//in case of equipping something in the hands slot things are different
	//this because the character could be holding two separate one handed items
	//and in that case we are supposed to return them both, for this reason we return a list
	//and then unwrap it to get the items back.
	public List<EquippableItemHands> equip(EquippableItemHands e){
		List<EquippableItemHands> result = new ArrayList<EquippableItemHands>();
		if ((e.getHandsNumber() == WeaponHands.TWOHANDED)) {
			this.twoHandedPresent = true;
			if (getRightHand().isPresent())
				result.add(rightHand);
			if (getLeftHand().isPresent())
				result.add(leftHand);
			rightHand = e;
			leftHand = null;
		}
		else {
			twoHandedPresent = false;
			if (!getRightHand().isPresent())
				rightHand = e;
			else
				if(!getLeftHand().isPresent())
					leftHand = e;
				else {
					result.add(rightHand);
					rightHand = e;
				}
					
		}
		return result;
		
	}
	
	public List<Weapon> getWeapons() {
		List<Weapon> result = new ArrayList<Weapon>();
		if (getRightHand().isPresent())
			if (rightHand instanceof Weapon)
				result.add((Weapon)rightHand);
		if (getLeftHand().isPresent())
			if (leftHand instanceof Weapon)
				result.add((Weapon)leftHand);
		return result;
		
	}
	
	
	public double getTotalLightPhysicalArmorRate() {
		double result = 0;
		if (getHelmet().isPresent())
			if (helmet.getArmorClass() == ArmorClass.LIGHT)
				result += helmet.getPhysicalArmorRate();
		if (getChest().isPresent())
			if (chest.getArmorClass() == ArmorClass.LIGHT)
				result += chest.getPhysicalArmorRate();
		if (getGauntlets().isPresent())
			if (gauntlets.getArmorClass() == ArmorClass.LIGHT)
				result += gauntlets.getPhysicalArmorRate();
		if (getLegs().isPresent())
			if (legs.getArmorClass() == ArmorClass.LIGHT)
				result += legs.getPhysicalArmorRate();
		if (getBoots().isPresent())
			if (boots.getArmorClass() == ArmorClass.LIGHT)
				result += boots.getPhysicalArmorRate();
		if (getRightHand().isPresent())
			if (rightHand instanceof HasArmor)
				if (((HasArmor) rightHand).getArmorClass() == ArmorClass.LIGHT)
					result += ((HasArmor)rightHand).getPhysicalArmorRate();
		if (getLeftHand().isPresent())
			if (leftHand instanceof HasArmor)
				if (((HasArmor)leftHand).getArmorClass() == ArmorClass.LIGHT)
					result += ((HasArmor)leftHand).getPhysicalArmorRate();
		return result;
	}
	
	public double getTotalHeavyPhysicalArmorRate() {
		double result = 0;
		if (getHelmet().isPresent())
			if (helmet.getArmorClass() == ArmorClass.HEAVY)
				result += helmet.getPhysicalArmorRate();
		if (getChest().isPresent())
			if (chest.getArmorClass() == ArmorClass.HEAVY)
				result += chest.getPhysicalArmorRate();
		if (getGauntlets().isPresent())
			if (gauntlets.getArmorClass() == ArmorClass.HEAVY)
				result += gauntlets.getPhysicalArmorRate();
		if (getLegs().isPresent())
			if (legs.getArmorClass() == ArmorClass.HEAVY)
				result += legs.getPhysicalArmorRate();
		if (getBoots().isPresent())
			if (boots.getArmorClass() == ArmorClass.HEAVY)
				result += boots.getPhysicalArmorRate();
		if (getRightHand().isPresent())
			if (rightHand instanceof HasArmor)
				if (((HasArmor) rightHand).getArmorClass() == ArmorClass.HEAVY)
					result += ((HasArmor)rightHand).getPhysicalArmorRate();
		if (getLeftHand().isPresent())
			if (leftHand instanceof HasArmor)
				if (((HasArmor)leftHand).getArmorClass() == ArmorClass.HEAVY)
					result += ((HasArmor)leftHand).getPhysicalArmorRate();
		return result;
	}
	
	
	public double getTotalLightMagicalArmorRate() {
		double result = 0;
		if (getHelmet().isPresent())
			if (helmet.getArmorClass() == ArmorClass.LIGHT)
				result += helmet.getMagicalArmorRate();
		if (getChest().isPresent())
			if (chest.getArmorClass() == ArmorClass.LIGHT)
				result += chest.getMagicalArmorRate();
		if (getGauntlets().isPresent())
			if (gauntlets.getArmorClass() == ArmorClass.LIGHT)
				result += gauntlets.getMagicalArmorRate();
		if (getLegs().isPresent())
			if (legs.getArmorClass() == ArmorClass.LIGHT)
				result += legs.getMagicalArmorRate();
		if (getBoots().isPresent())
			if (boots.getArmorClass() == ArmorClass.LIGHT)
				result += boots.getMagicalArmorRate();
		if (getRightHand().isPresent())
			if (rightHand instanceof HasArmor)
				if (((HasArmor) rightHand).getArmorClass() == ArmorClass.LIGHT)
					result += ((HasArmor)rightHand).getMagicalArmorRate();
		if (getLeftHand().isPresent())
			if (leftHand instanceof HasArmor)
				if (((HasArmor)leftHand).getArmorClass() == ArmorClass.LIGHT)
					result += ((HasArmor)leftHand).getMagicalArmorRate();
		return result;
	}
	
	public double getTotalHeavyMagicalArmorRate() {
		double result = 0;
		if (getHelmet().isPresent())
			if (helmet.getArmorClass() == ArmorClass.HEAVY)
				result += helmet.getMagicalArmorRate();
		if (getChest().isPresent())
			if (chest.getArmorClass() == ArmorClass.HEAVY)
				result += chest.getMagicalArmorRate();
		if (getGauntlets().isPresent())
			if (gauntlets.getArmorClass() == ArmorClass.HEAVY)
				result += gauntlets.getMagicalArmorRate();
		if (getLegs().isPresent())
			if (legs.getArmorClass() == ArmorClass.HEAVY)
				result += legs.getMagicalArmorRate();
		if (getBoots().isPresent())
			if (boots.getArmorClass() == ArmorClass.HEAVY)
				result += boots.getMagicalArmorRate();
		if (getRightHand().isPresent())
			if (rightHand instanceof HasArmor)
				if (((HasArmor) rightHand).getArmorClass() == ArmorClass.HEAVY)
					result += ((HasArmor)rightHand).getMagicalArmorRate();
		if (getLeftHand().isPresent())
			if (leftHand instanceof HasArmor)
				if (((HasArmor)leftHand).getArmorClass() == ArmorClass.HEAVY)
					result += ((HasArmor)leftHand).getMagicalArmorRate();
		return result;
	}
	
	public double getTotalWeight() {
		int result = 0;
		if (getHelmet().isPresent())
			result += helmet.getWeight();
		if (getChest().isPresent())
			result += chest.getWeight();
		if (getGauntlets().isPresent())
			result += gauntlets.getWeight();
		if (getLegs().isPresent())
			result += legs.getWeight();
		if (getBoots().isPresent())
			result += boots.getWeight();
		if (getRightHand().isPresent())
			result += rightHand.getWeight();
		if (getLeftHand().isPresent())
			result += leftHand.getWeight();
		return result;
	}

}
