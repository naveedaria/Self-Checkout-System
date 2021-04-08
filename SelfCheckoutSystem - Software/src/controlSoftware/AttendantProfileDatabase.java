package controlSoftware;

import java.util.ArrayList;

public class AttendantProfileDatabase {
	
	private ArrayList<AttendantProfile> profiles;
	
	public AttendantProfileDatabase() {

		profiles = new ArrayList<AttendantProfile>();
	}
	
	public void addProfile(AttendantProfile newProfile) {
		if(profiles.contains(newProfile) == false) {
			profiles.add(newProfile);
		} else {
			System.out.println("You are trying to add a duplicate profile to the database. please try again.");
		}
		
	}
	
	public void removeProfile(AttendantProfile profileToBeRemoved) {
		
		if(profiles.contains(profileToBeRemoved) == true) {
			profiles.remove(profileToBeRemoved);
		} else {
			System.out.println("You are trying to remove a profile that is not stored in the database.");
		}
		
	}
	
	public boolean lookForProfile(AttendantProfile searchProfile) {

		if (profiles.contains(searchProfile)) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
