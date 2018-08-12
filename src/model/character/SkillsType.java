package model.character;

public enum SkillsType {
	NONE("None"),
	ONEHANDED("One Handed"),
	TWOHANDED("Two Handed"),
	ARCHERY("Archery"),
	BLOCK("Block"),
	DODGE("Dodge"),
	CRITICALSTRIKE("Critical Strike"),
	MAGICPOWER("Magic Power"),
	LIGHTARMOR("Light Armor"),
	HEAVYARMOR("Heavy Armor"),
	HAGGLING("Haggling"),
	DUNGEONEERING("Dungeoneering");
	
	private final String name;
	
	private SkillsType(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
