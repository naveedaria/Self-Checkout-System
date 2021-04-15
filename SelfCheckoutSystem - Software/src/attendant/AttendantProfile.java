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
	
	@Override
	public boolean equals(Object o) {
		
		if(o instanceof AttendantProfile) {
			
			if(this.username.equals(((AttendantProfile)o).username) && this.password.equals(((AttendantProfile)o).password)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
}
