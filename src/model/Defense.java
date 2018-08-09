package model;

public class Defense {
	
	private double damageReceived;
	private boolean killed;
	public Defense(double damageReceived, boolean killed) {
		super();
		this.damageReceived = damageReceived;
		this.killed = killed;
	}
	public double getDamageReceived() {
		return damageReceived;
	}
	public boolean isKilled() {
		return killed;
	}
	
	
}
