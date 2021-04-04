package controlSoftware;

import java.math.BigDecimal;
import java.util.Calendar;

public class PaymentByGiftcard {
	BigDecimal balance; 
	boolean activeStatus = true;
	//status field - null or active 
	//suppose no expiry 
	
	
	//don't need database for now - just use amount on card 
	//plan to tap giftcard 
	
	//either we overload the payment by card methods
	//extend the payment by card class 
	//create separate methods based on payment by card methods (parallel) - PICKED 
	
	
	public PaymentByGiftcard() {
		//selfcheckout station parameter
		//card company is COOP 
		//register card reader 
		//for card itself, need cardnumber, amount
		
		
		
	}
	
	//other parameters will be null - pass in cardnumber, amount 
	public void detectCard(String type, String number) {
		
	}
	 
	public boolean tapToRedeem(BigDecimal amount) {
		boolean cardSuccessfullyUsed=true; 
		
		//Use Cases: card balance is greater than amount (left over amount on card)
		//Use Case: card balance is less than amount (set status to null once amount used) 
		//Use Case: is card is null - return a false boolean to say card wasn't used 
		
		
		return cardSuccessfullyUsed;
		
	}
	
	
	
	//QUESTION: new card reader for each instance of paymentbycard/membership class?? 
	
	

}