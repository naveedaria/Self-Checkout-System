package controlSoftware;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class AttendantLogIn_OutTest {

	private AttendantProfileDatabase profiles;
	private AttendantLogIn_Out inOut;
	
	@Before
	public void initialize_structure() {
		profiles = new AttendantProfileDatabase();
		inOut = new AttendantLogIn_Out();
	}
	
	@Test
	public void LogInTest() {
		AttendantProfile profile1 = new AttendantProfile("attendant1", "password1");
		profiles.addProfile(profile1);
		inOut.loadAttendantProfileDatabase(profiles);

		assertFalse(inOut.logIn("attendant1", "password1"));
		
	}
	
	@Test
	public void LogOutTest() {
		
		AttendantProfile profile2 = new AttendantProfile("attendant2", "password1");
		profiles.addProfile(profile2);
		inOut.loadAttendantProfileDatabase(profiles);
		
		assertFalse(inOut.logOut("attendant2", "password1"));
		
	}
}
