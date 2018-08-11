package model;


public abstract class Actor {

	
		protected double maxHP;
		protected double currentHP;
		
		protected String descritpion;
		protected String name;
		
		protected int lvl;
		protected ActorID id;
		protected Encyclopedia encyclopedia;
		

		
		public Actor(double maxHP, String descritpion, String name, int lvl, ActorID id, Encyclopedia encyclopedia) {
			super();
			this.maxHP = maxHP;
			this.currentHP = maxHP;
			this.descritpion = descritpion;
			this.name = name;
			this.lvl = lvl;
			this.id = id;
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


		public abstract boolean isDead();
		
}
