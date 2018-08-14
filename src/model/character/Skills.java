package model.character;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Skills implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6610947169817052636L;
	private SkillsType primarySkills[];
	private SkillsType secondarySkills[];
	private SkillsType miscellaneousSkills[]
;	private int oneHanded;
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
	
	public Skills(SkillsType[] primarySkills, SkillsType[] secondarySkills, int oneHanded, int twoHanded, int archery,
			int block, int dodge, int lightArmor, int heavyArmor, int criticalStrike, int magicPower, int haggling,
			int dungeoneering) {
		super();
		this.primarySkills = primarySkills;
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
		Set<SkillsType> setPrimary = new HashSet<SkillsType>(Arrays.asList(primarySkills));
		Set<SkillsType> setSecondary = new HashSet<SkillsType>(Arrays.asList(secondarySkills));
		Set<SkillsType> setTotal = new HashSet<SkillsType>(Arrays.asList(SkillsType.values()));
		setTotal.remove(SkillsType.NONE);
		setTotal.removeAll(setPrimary);
		setTotal.removeAll(setSecondary);
		this.miscellaneousSkills = new SkillsType[setTotal.size()]; 
		setTotal.toArray(this.miscellaneousSkills);
		
	
	}

	public SkillsType[] getPrimarySkills() {
		return primarySkills;
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
	
	
	public SkillsType[] getMiscellaneousSkills() {
		return miscellaneousSkills;
	}
	
	//i from 1-3 -->primary
	//from 4-6 -->secondary
	//7-11 -->miscellaneous
	public int getSkill(int i) {
		SkillsType skill = SkillsType.NONE;
		int result = -1;
		if (i<=3) 
			skill = primarySkills[i-1];
		if (i>3 && i<=6)	
			skill = secondarySkills[i-4];
		if (i>6 && i<=11)
			skill = miscellaneousSkills[i-7];
		switch (skill) {
		case ONEHANDED: result=oneHanded;
		break;
		case TWOHANDED: result=twoHanded;
		break;
		case ARCHERY: result=archery;
		break;
		case BLOCK: result=block;
		break;
		case DODGE: result=dodge;
		break;
		case LIGHTARMOR: result=lightArmor;
		break;
		case HEAVYARMOR: result=heavyArmor;
		break;
		case CRITICALSTRIKE: result=criticalStrike;
		break; 
		case MAGICPOWER: result=magicPower;
		break;
		case HAGGLING: result=haggling;
		break;
		case DUNGEONEERING: result=dungeoneering;
		break;
		case NONE: result=-1;
		break;
		}
		return result;
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
		case NONE:
			break;
		default:
			break;	
		}
		return result;
	}	
}
