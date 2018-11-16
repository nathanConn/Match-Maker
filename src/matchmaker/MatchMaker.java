package matchmaker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MatchMaker {
	
	private ArrayList<Member> members;
	private Member currentMember;
	private Scanner keyboard = new Scanner(System.in);
	
	public MatchMaker() {
		members = new ArrayList<Member>();
		currentMember = null;
	}

	public void start() {
		loadData();
		homeMenuHandler();
	}
	
	private void logout() {
		saveData();
		MenuHelper.printExitScreen();
	}
	
	private void loadData() {
		BufferedReader inputStream = null;
		
		try {
			inputStream = 
	            new BufferedReader(new FileReader(AppConstants.DATA_FILE));
			
			String line = inputStream.readLine( );
	           while (line != null) {
	        	   String[] data = line.split("\t");
	        	   Member m = new Member();
	        	   m.setUserId(data[0]);
	        	   m.setPassword(data[1]);
	        	   m.setEmail(data[2]);
	        	   m.setAge(Integer.parseInt(data[3]));
	        	   m.setGender(data[4]);
	        	   m.setReading(Integer.parseInt(data[5]));
	        	   m.setEating(Integer.parseInt(data[6]));
	        	   m.setSporting(Integer.parseInt(data[7]));
	        	   m.setTraveling(Integer.parseInt(data[8]));
	        	   m.setMusic(Integer.parseInt(data[9]));
	        	   members.add(m);
	        	   line = inputStream.readLine( );
	           }
			
			inputStream.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + AppConstants.DATA_FILE);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO Exception");
			e.printStackTrace();
		} finally {
			//Close the input and output stream.
			//Otherwise the next time this program may not run properly.
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void saveData() {
		if (members != null && members.size() != 0) {
			PrintWriter outputStream = null;
			try {
				outputStream = 
	                 new PrintWriter(new FileOutputStream(AppConstants.DATA_FILE));
				
				for (int i = 0; i < members.size(); i++) {
					Member m = members.get(i);
					outputStream.println(
						m.getUserId() + "\t" +
						m.getPassword() + "\t" +
						m.getEmail() + "\t" +
						m.getAge() + "\t" +
						m.getGender() + "\t" +
						m.getReading() + "\t" +
						m.getEating() + "\t" +
						m.getSporting() + "\t" +
						m.getTraveling() + "\t" +
						m.getMusic());
				}
				outputStream.close();
			} catch (FileNotFoundException e) {
				System.out.println("File not found: " + AppConstants.DATA_FILE);
				e.printStackTrace();
			} finally {
				//Close the input and output stream.
				//Otherwise the next time this program may not run properly.
				if (outputStream != null) {
					outputStream.close();
				}
			}
		}
	}
	
	private void login() {
		MenuHelper.printLoginMenu();
		System.out.println("Enter username: ");
		String username = keyboard.nextLine();
		System.out.println("Enter password: ");
		String password = Base64.getEncoder().encodeToString(keyboard.nextLine().getBytes());
		for (int i = 0; i < members.size(); i++) {
			Member m = members.get(i);
			if (m.getUserId().equals(username) 
					&& m.getPassword().equals(password)) {
				currentMember = m;
				break;
			}
		}
		
		if (currentMember == null) {
			System.out.println("Invalid username/password!");
			login();
		} else {
			mainMenuHandler();
		}
		
		
	}
	
	private void homeMenuHandler() {
		MenuHelper.printHomeMenu();
		System.out.println("Please select a choice: ");
		String choice = keyboard.nextLine();
		char c = choice.charAt(0); //only take the first character
		switch (c) {
			case '1': login();
				break;
			case '2': register();
				break;
			case '0': logout();
				break;
			default: System.out.println("Invalid choice!");
					 homeMenuHandler();
		}
		
}
	
	private void mainMenuHandler() {
			MenuHelper.printMainMenu();
			System.out.println("Please select a choice: ");
			String choice = keyboard.nextLine();
			char c = choice.charAt(0); //only take the first character
			switch (c) {
				case '1': updateProfile();
					break;
				case '2': viewMembers();
					break;
				case '3': viewBestMatch();
					break;
				case '0': logout();
					break;
				default: System.out.println("Invalid choice!");
						 mainMenuHandler();
			}
			
	}
	
	private void register() {
		System.out.println("Please fill the follwoing info: ");
		System.out.println("--------------------------------");
		System.out.println("User ID: (max 10 characters) ");
		String userId = keyboard.nextLine();
		if (userExists(userId)) {
			System.out.println("User ID already exists.  Please choose another one.");
			register();
		} else {		
			System.out.println("Password: (max 10 characters) ");
			String password = Base64.getEncoder().encodeToString(keyboard.nextLine().getBytes());
			System.out.println("Email address: ");
			String email = keyboard.nextLine();
			System.out.println("Age: ");
			String ageStr = keyboard.nextLine();
			int age = Integer.parseInt(ageStr);
			System.out.println("Gender: ('F' or 'M') ");
			String gender = keyboard.nextLine();
			System.out.println();
			System.out.println("Tell us about your personality.");
			System.out.println("Between 0 (least) to 10 (most), give a score to the following: ");
			System.out.println("-------------------------------------------------------------");
			System.out.println("Love to read: ");
			int read = Integer.parseInt(keyboard.nextLine());
			System.out.println("Love to eat: ");
			int eat = Integer.parseInt(keyboard.nextLine());
			System.out.println("Love to do sport: ");
			int sport = Integer.parseInt(keyboard.nextLine());
			System.out.println("Love to travel: ");
			int travel = Integer.parseInt(keyboard.nextLine());
			System.out.println("Love music: ");
			int music = Integer.parseInt(keyboard.nextLine());
			
			
			
			Member m = new Member();
			m.setUserId(userId);
			m.setPassword(password);
			m.setAge(age);
			m.setEmail(email);
			m.setGender(gender);
			m.setEating(eat);
			m.setReading(read);
			m.setSporting(sport);
			m.setTraveling(travel);
			m.setMusic(music);
			
			members.add(m);
			saveData();
			System.out.println("Congratulations! You are a member of the Match Maker community.");
			login();
		}
	}
	
	private void updateProfile() {
		System.out.println("Password: (max 8 characters) ");
		String password = Base64.getEncoder().encodeToString(keyboard.nextLine().getBytes());
		System.out.println("Email address: ");
		String email = keyboard.nextLine();
		System.out.println("Age: ");
		String ageStr = keyboard.nextLine();
		int age = Integer.parseInt(ageStr);
		System.out.println("Gender: ('F' or 'M') ");
		String gender = keyboard.nextLine();
		System.out.println();
		System.out.println("Tell us about your personality.");
		System.out.println("Between 0 (least) to 10 (most), give a score to the following: ");
		System.out.println("-------------------------------------------------------------");
		System.out.println("Love to read: ");
		int read = Integer.parseInt(keyboard.nextLine());
		System.out.println("Love to eat: ");
		int eat = Integer.parseInt(keyboard.nextLine());
		System.out.println("Love to do sport: ");
		int sport = Integer.parseInt(keyboard.nextLine());
		System.out.println("Love to travel: ");
		int travel = Integer.parseInt(keyboard.nextLine());
		System.out.println("Love music: ");
		int music = Integer.parseInt(keyboard.nextLine());

		currentMember.setPassword(password);
		currentMember.setAge(age);
		currentMember.setEmail(email);
		currentMember.setGender(gender);
		currentMember.setEating(eat);
		currentMember.setReading(read);
		currentMember.setSporting(sport);
		currentMember.setTraveling(travel);
		currentMember.setMusic(music);
		
		System.out.println("Thank you!  Your profile has been updated.");
		mainMenuHandler();
	}
	
	private void viewMembers() {
		System.out.println("****************************************     Member Profiles     ******************************************");
		System.out.println();
		System.out.println("Username        Email                                    Age Gender Reading Eating Sporting Traveling Music");
		System.out.println("=============== ======================================== === ====== ======= ====== ======== ========= =====");
		
		for (int i = 0; i < members.size(); i++) {
			Member m = members.get(i);
			System.out.println(Utils.rpad(m.getUserId(), ' ', 15) + " " +
					Utils.rpad(m.getEmail(), ' ', 40) + " " + 
					Utils.rpad(Integer.toString(m.getAge()), ' ', 3) + " " + 
					Utils.rpad(m.getGender(), ' ', 6) + " " +
					Utils.rpad(Integer.toString(m.getReading()), ' ', 7) + " " +
					Utils.rpad(Integer.toString(m.getEating()), ' ', 6) + " " +
					Utils.rpad(Integer.toString(m.getSporting()), ' ', 8) + " " +
					Utils.rpad(Integer.toString(m.getTraveling()), ' ', 9) + " " + 
					Utils.rpad(Integer.toString(m.getMusic()), ' ', 5));
		}
		mainMenuHandler();
	}
	
	private void viewBestMatch() {
		Member m = getBestMatch();
		//System.out.println("*****Best match profile displays here******");
		if (m != null) {
			System.out.println("Congratulations! Below is your best match profile:");
			System.out.println("Match score: " + m.getMatchScore(currentMember));
			
			System.out.println("****************************************     Member Profiles     ******************************************");
			System.out.println();
			System.out.println("Username        Email                                    Age Gender Reading Eating Sporting Traveling Music");
			System.out.println("=============== ======================================== === ====== ======= ====== ======== ========= =====");
			
			System.out.println(Utils.rpad(m.getUserId(), ' ', 15) + " " +
					Utils.rpad(m.getEmail(), ' ', 40) + " " + 
					Utils.rpad(Integer.toString(m.getAge()), ' ', 3) + " " + 
					Utils.rpad(m.getGender(), ' ', 6) + " " +
					Utils.rpad(Integer.toString(m.getReading()), ' ', 7) + " " +
					Utils.rpad(Integer.toString(m.getEating()), ' ', 6) + " " +
					Utils.rpad(Integer.toString(m.getSporting()), ' ', 8) + " " +
					Utils.rpad(Integer.toString(m.getTraveling()), ' ', 9) + " " + 
					Utils.rpad(Integer.toString(m.getMusic()), ' ', 5));
		} else {
			System.out.println("Sorry no match for you at this moment!");
		}
		mainMenuHandler();
	}
	
	//This method will return a Member object or NULL value.  NULL means no match.
	private Member getBestMatch() {
		Member bestMatch = null;
		double bestMatchScore = 999999; // The smaller the better. Negative means no match. 999999 is an unrealistically large number
		for (int i = 0; i < members.size(); i++) {
			//TODO:  Please insert your code here.
				Member curMatch = members.get(i);
				double curScore = currentMember.getMatchScore(members.get(i));
				if (curScore < bestMatchScore && curScore > -1) {
				bestMatch = curMatch;
				bestMatchScore = curScore;
				}	
		}
		return bestMatch;
	}
	
	//To be used for top 3 matches array
	private Member getWorstMatch() {
		Member worstMatch = null;
		double worstMatchScore = 1; // The smaller the better. Negative means no match. 999999 is an unrealistically large number
		for (int i = 0; i < members.size(); i++) {
				Member curMatch = members.get(i);
				double curScore = currentMember.getMatchScore(members.get(i));
				if (curScore > worstMatchScore && curScore > -1) {
				worstMatch = curMatch;
				worstMatchScore = curScore;
				}	
		}
		return worstMatch;
	}
	
	//Sorted list of the top 3 mathces
	private Member[] getBest3() {
		Member[] lowest = new Member[3];
		Member m = getWorstMatch();
		Arrays.fill(lowest, m);
		
		for (Member n: members) {
			if(n.getMatchScore(currentMember) < m.getMatchScore(currentMember)) {
				lowest[2] = n;
				Arrays.sort(lowest);
			}
		}
		return lowest;
	}
	
	private boolean userExists(String userId) {
		boolean answer = false;
		
		for (int i = 0; i < members.size(); i++) {
			Member m = members.get(i);
			if (m.getUserId().equals(userId)) {
				answer = true;
				break;
			}
		}
		
		return answer;
	}

}