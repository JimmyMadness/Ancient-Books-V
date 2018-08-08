package model;

public class Enemy extends Actor {



	public Enemy(Characteristics characteristics, double maxHP, String descritpion, String name, int lvl, ActorID id,
			Encyclopedia encyclopedia) {
		super(characteristics, maxHP, descritpion, name, lvl, id, encyclopedia);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attack(Actor target) {
		// TODO Auto-generated method stub

	}

	@Override
	public void defend(Actor attacker) {
		// TODO Auto-generated method stub

	}

}
