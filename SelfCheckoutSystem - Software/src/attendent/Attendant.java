package attendent;

import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.Item;

import controlSoftware.ControlSoftware;

public class Attendant {
	
	String attendantID;
	
	public Attendant(String attendantID) {
		this.attendantID = attendantID;
	}
	
	//rename 
	public void removeItem(ControlSoftware cs, BarcodedItem item, int quantity) {
		cs.shoppingCart.removeFromShoppingCart(item, quantity);
	}

}
