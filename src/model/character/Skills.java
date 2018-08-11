package model.character;

import java.io.Serializable;

public class Skills implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6610947169817052636L;
	private SkillsType primarySKills[];
	private SkillsType secondarySkills[];
	private int oneHanded;
	private int twoHanded;
	private int archery;
	private int block;
	private int dodge;
	private int lightArmor;
	private int heavyArmor;
	private int criticalStrike;
	private int magicPower;
	private int haggling;
	private int dungeoneering;
	
	public Skills(SkillsType[] primarySKills, SkillsType[] secondarySkills, int oneHanded, int twoHanded, int archery,
			int block, int dodge, int lightArmor, int heavyArmor, int criticalStrike, int magicPower, int haggling,
			int dungeoneering) {
		super();
		this.primarySKills = primarySKills;
		this.secondarySkills = secondarySkills;
		this.oneHanded = oneHanded;
		this.twoHanded = twoHanded;
		this.archery = archery;
		this.block = block;
		this.dodge = dodge;
		this.lightArmor = lightArmor;
		this.heavyArmor = heavyArmor;
		this.criticalStrike = criticalStrike;
		this.magicPower = magicPower;
		this.haggling = haggling;
		this.dungeoneering = dungeoneering;
	}

	public SkillsType[] getPrimarySKills() {
		return primarySKills;
	}

	public SkillsType[] getSecondarySkills() {
		return secondarySkills;
	}

	public int getOneHanded() {
		return oneHanded;
	}

	public int getTwoHanded() {
		return twoHanded;
	}

	public int getArchery() {
		return archery;
	}

	public int getBlock() {
		return block;
	}

	public int getDodge() {
		return dodge;
	}

	public int getLightArmor() {
		return lightArmor;
	}

	public int getHeavyArmor() {
		return heavyArmor;
	}

	public int getCriticalStrike() {
		return criticalStrike;
	}

	public int getMagicPower() {
		return magicPower;
	}

	public int getHaggling() {
		return haggling;
	}

	public int getDungeoneering() {
		return dungeoneering;
	}
	
	
	//the amount of avaiable skillpoints removed gets calculated from the caller of this method
	public boolean increase(SkillsType s) {
		boolean result = false;
		switch(s) {
		case ONEHANDED:
			if (this.oneHanded <100) {
				oneHanded ++;
				result = true;
			}
			break;
		case TWOHANDED:
			if (this.twoHanded <100) {
				twoHanded++;
				result = true;
			}
			break;
		case ARCHERY:
			if (this.archery <100) {
				archery++;
				result = true;
			}
			break;	
		case BLOCK:
			if (this.block <100) {
				block++;
				result = true;
			}
			break;		
		case DODGE:
			if (this.dodge <100) {
				dodge++;
				result = true;
			}
			break;		

		case LIGHTARMOR:
			if (this.lightArmor <100) {
				lightArmor++;
				result = true;
			}
			break;	
		case HEAVYARMOR:
			if (this.heavyArmor <100) {
				heavyArmor++;
				result = true;
			}
			break;	
		case CRITICALSTRIKE:
			if (this.criticalStrike <100) {
				criticalStrike++;
				result = true;
			}
			break;	
		case MAGICPOWER:
			if (this.magicPower <100) {
				magicPower++;
				result = true;
			}
			break;	
		case HAGGLING:
			if (this.haggling <100) {
				haggling++;
				result = true;
			}
			break;	
		case DUNGEONEERING:
			if (this.dungeoneering <100) {
				dungeoneering++;
				result = true;
			}
			break;	
		}
		return result;
	}	
}
