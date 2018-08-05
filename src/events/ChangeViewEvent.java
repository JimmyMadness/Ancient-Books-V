package events;

import java.util.EventObject;

import application.Views;

public class ChangeViewEvent extends EventObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4911573521539941725L;
	
	private Views view;

	public ChangeViewEvent(Object arg0, Views view) {
		super(arg0);
		this.view = view;
	}
	
	public Views getView() {
		return view;
	}

}
