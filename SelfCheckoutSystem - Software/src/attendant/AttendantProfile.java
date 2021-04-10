package attendant;

import org.lsmr.selfcheckout.devices.SimulationException;

public final class AttendantProfile {

	private final String username;
	private final String password;
	
	public AttendantProfile(String username, String password) {
		
		if(username == null) 
			throw new SimulationException(new NullPointerException("username is null"));
		
		if(password == null) 
			throw new SimulationException(new NullPointerException("password is null"));
		
		this.username = username;
		this.password = password;
		
	}
}
