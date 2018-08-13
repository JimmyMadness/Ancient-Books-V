package model.character;

public enum RpgClass {
	WARRIOR("Warrior"),
	MAGE("Mage"),
	ROGUE("Rogue");
	
	
private final String name;
	
	private RpgClass(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
