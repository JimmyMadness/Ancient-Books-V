package model;

public class Characteristics {
	private int str;
	private int dex;
	private int con;
	private int intl;
	private int cha;
	private int lck;
	
	public Characteristics(int str, int dex, int con, int intl, int cha, int lck) {
		this.str = str;
		this.dex = dex;
		this.con = con;
		this.intl = intl;
		this.cha = cha;
		this.lck = lck;
	}

	public int getStr() {
		return str;
	}

	public void setStr(int str) {
		this.str = str;
	}

	public int getDex() {
		return dex;
	}

	public void setDex(int dex) {
		this.dex = dex;
	}

	public int getCon() {
		return con;
	}

	public void setCon(int con) {
		this.con = con;
	}

	public int getIntl() {
		return intl;
	}

	public void setIntl(int intl) {
		this.intl = intl;
	}

	public int getCha() {
		return cha;
	}

	public void setCha(int cha) {
		this.cha = cha;
	}

	public int getLck() {
		return lck;
	}

	public void setLck(int lck) {
		this.lck = lck;
	}

	
	public double getCarryWeigth() {
		return 200 + str;
	}

	public double getMaxHp(int level) {
		return 80+(con*0.5)+(level-1)*(con/20+2.5);
	}
	
	public int getLevelUpSkillPoints() {
		return 5 + intl/20;
	}
	
	
}
