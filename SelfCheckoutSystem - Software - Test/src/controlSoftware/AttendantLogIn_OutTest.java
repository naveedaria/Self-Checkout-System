package controlSoftware;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.lsmr.selfcheckout.devices.SimulationException;

import attendant.AttendantLogIn_Out;
import attendant.AttendantProfile;
import attendant.AttendantProfileDatabase;

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

		assertTrue(inOut.logIn("attendant1", "password1"));
		
	}
	
	@Test
	public void LogOutTest() {
		
		AttendantProfile profile2 = new AttendantProfile("attendant2", "password1");
		profiles.addProfile(profile2);
		inOut.loadAttendantProfileDatabase(profiles);
		
		assertTrue(inOut.logOut("attendant2", "password1"));
		
	}
	
	@Test(expected = SimulationException.class) 
	public void usernameNullTest() {
		String username = null;
		String password = "12344";
		
		AttendantProfile profile3 = new AttendantProfile(username, password);
	}
	@Test(expected = SimulationException.class) 
	public void passwordNullTest() {
		String username = "Bob";
		String password = null;
		
		AttendantProfile profile3 = new AttendantProfile(username, password);
	}
	
	@Test
	public void removeProfileTest() {
		AttendantProfile profile2 = new AttendantProfile("Bob", "hunter2");
		profiles.addProfile(profile2);
		profiles.removeProfile(profile2);
		
		assertEquals(false, profiles.lookForProfile(profile2));
		
	}
}
