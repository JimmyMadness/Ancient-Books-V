package model;

public class Attack {

	private double physicalDamage;
	private double magicalDamage;
	private String text;
	private boolean success;
	public Attack(boolean success,double physicalDamage, double magicalDamage, String text) {
		this.success = success;
		this.physicalDamage = physicalDamage;
		this.magicalDamage = magicalDamage;
		this.text = text;
	}
	public boolean isSucceded() {
		return success;
	}
	
	public double getPhysicalDamage() {
		return physicalDamage;
	}
	public double getMagicalDamage() {
		return magicalDamage;
	}
	public String getText() {
		return text;
	}
	
	
}
