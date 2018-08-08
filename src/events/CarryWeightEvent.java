package events;

import java.util.EventObject;

public class CarryWeightEvent extends EventObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8035475217462322428L;
	
	private double carryWeight;

	public CarryWeightEvent(Object arg0, double carryWeight) {
		super(arg0);
		this.carryWeight = carryWeight;
	}
	
	public double getCarryWeight() {
		return carryWeight;
	}

}
