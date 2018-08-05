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
		

		
		public Actor(int str, int dex, int con, int intl, int cha, int lck,
				double maxHP, String descritpion, String name, int lvl, ActorID id, Encyclopedia encyclopedia) {
			super();
			this.characteristics.SetCharacteristics(str, dex, con, intl, cha, lck);
			this.maxHP = maxHP;
			this.currentHP = maxHP;
			this.descritpion = descritpion;
			this.name = name;
			this.lvl = lvl;
			this.id = id;
		}
		
		
		
		public int getStr() {
			return characteristics.getStr();
		}

		public int getDex() {
			return characteristics.getDex();
		}

		public int getCon() {
			return characteristics.getCon();
		}

		public int getIntl() {
			return characteristics.getIntl();
		}

		public int getCha() {
			return characteristics.getCha();
		}

		public int getLck() {
			return characteristics.getLck();
		}

		public double getMaxHP() {
			return maxHP;
		}

		public double getCurrentHP() {
			return currentHP;
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

		public abstract void attack(Actor target);
		public abstract void defend(Actor attacker);
		
}
