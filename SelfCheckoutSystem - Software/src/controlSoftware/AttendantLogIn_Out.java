package controlSoftware;

public class AttendantLogIn_Out {

	private AttendantProfileDatabase profiles;
	private String username, password;
	
	public boolean logIn(String username, String password) {
		AttendantProfile profile = new AttendantProfile(username, password);
		if (profiles.lookForProfile(profile) == true) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean logOut(String username, String password) {
		AttendantProfile profile = new AttendantProfile(username, password);
		if(profiles.lookForProfile(profile) == true) {
			return true;
		}else {
		return false;
		}
	}
	
	public void loadAttendantProfileDatabase(AttendantProfileDatabase profiles) {
		this.profiles = profiles;
	}
	
}
