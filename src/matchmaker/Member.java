package matchmaker;

import java.util.Base64;
import java.util.Comparator;

public class Member{
	private String userId;
	private String password;
	private String email;
	private int age;
	private String gender;
	private int reading;
	private int eating;
	private int sporting;
	private int traveling;
	private int music;
	
	public Member(){
		
	}
	
	public Member(String userId) {
		this.userId = userId;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getEating() {
		return eating;
	}
	public void setEating(int eating) {
		this.eating = eating;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getMusic() {
		return music;
	}
	public void setMusic(int music) {
		this.music = music;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getReading() {
		return reading;
	}
	public void setReading(int reading) {
		this.reading = reading;
	}
	public int getSporting() {
		return sporting;
	}
	public void setSporting(int sporting) {
		this.sporting = sporting;
	}
	public int getTraveling() {
		return traveling;
	}
	public void setTraveling(int traveling) {
		this.traveling = traveling;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	// This algorithm for this match score is totally experimental.  
	// The logic here is that if the two members are 
	// (1) of the opposite gender 
	// (2) close in age and
	// (3) share similar personalities
	// then they are a good match.
	// The smaller the differences between the two, the better.
	// Therefore, the smaller the match score, the better match they are.
	// However a negative score means no match.
	public double getMatchScore(Member other) {
		double score = -1.0;
		if (getGender().equalsIgnoreCase(other.getGender())) {
			score = -1.0; //no match for the same gender
		} else if (getUserId().equalsIgnoreCase(other.getUserId())) {
			score = -1.0; //no match for oneself
		} else {
			double ageDiff = Math.abs(this.getAge() - other.getAge());
			double readingDiff = Math.abs(this.getReading() - other.getReading());
			double eatingDiff = Math.abs(this.getEating() - other.getEating());
			double sportingDiff = Math.abs(this.getSporting() - other.getSporting());
			double travelingDiff = Math.abs(this.getTraveling() - other.getTraveling());
			double musicDiff = Math.abs(this.getMusic() - other.getMusic());
			
			double personalityDiff = ageDiff + readingDiff + eatingDiff + sportingDiff + travelingDiff + musicDiff;
			
			score = ageDiff * 0.4 + personalityDiff * 0.6; // personality has a bigger weight
		}
		return score;
	}


}

