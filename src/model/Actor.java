package model;


public abstract class Actor {
		protected Characteristics characteristics;

	
		protected double maxHP;
		protected double currentHP;
		
		protected String descritpion;
		protected String name;
		
		protected int lvl;
		protected ActorID id;
		protected Encyclopedia encyclopedia;
		

		
		public Actor(Characteristics characteristics, double maxHP, String descritpion, String name, int lvl, ActorID id, Encyclopedia encyclopedia) {
			super();
			this.characteristics = characteristics;
			this.maxHP = maxHP;
			this.currentHP = maxHP;
			this.descritpion = descritpion;
			this.name = name;
			this.lvl = lvl;
			this.id = id;
		}
		
		
		
		public Characteristics getCharacteristics() {
			return characteristics;
		}

		public double getMaxHP() {
			return maxHP;
		}

		public double getCurrentHP() {
			return currentHP;
		}
		
		public void setCurrentHP(double currentHP) {
			this.currentHP = currentHP;
		}

		public String getDescritpion() {
			return descritpion;
		}

		public String getName() {
			return name;
		}

		public int getLvl() {
			return lvl;
		}

		public ActorID getId() {
			return id;
		}

		public abstract Attack attack(Actor target);
		public abstract Defense defend(Attack a);
		public abstract boolean isDead();
		
}
