package matchmaker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

public class MenuHelper {
	public static Properties properties = new Properties();;
	private static final String RESOURCE_DIR = "resources";
	private static String RESOURCE_FILE = "menus_en.properties"; // Default to English
	
	// Initialize the properties based on Desktop system Locale
	static {
		Locale currentLocale = Locale.getDefault();
		if (currentLocale.getLanguage() != null) {
			String systemLanguage = currentLocale.getLanguage();
			if (systemLanguage.equals(new Locale("es").getLanguage())) {
				RESOURCE_FILE = "menus_es.properties";
			} else if (systemLanguage.equals(new Locale("fr").getLanguage())) {
				RESOURCE_FILE = "menus_fr.properties";
			}
		} 
		
		// Default to English
		File f = new File(RESOURCE_DIR + File.separator + RESOURCE_FILE);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(f);
			properties.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null)
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			fis = null;
		}
		
	}
	
	public static void printHeading() {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("*****************************");
		System.out.println("**  " + properties.getProperty("welcome") + "  **");
		System.out.println("*****************************");
	}
	
	public static void printExitScreen() {
		System.out.println("**************************************");
		System.out.println("**  "+ properties.getProperty("thanks")+"  **");
		System.out.println("**  "+ properties.getProperty("come_back")+"  **");
		System.out.println("**************************************");
	}
	
	public static void printLoginMenu() {
		printHeading();
		System.out.println();
		System.out.println();
		System.out.println(properties.getProperty("login_menu"));
	}
	
	public static void printMainMenu() {
		printHeading();
		System.out.println();
		System.out.println();
		System.out.println("1. " + properties.getProperty("update_profile"));
		System.out.println();
		System.out.println("2. " + properties.getProperty("view_all_members"));
		System.out.println();
		System.out.println("3. " + properties.getProperty("view_best_match"));
		System.out.println();
		System.out.println("0. " + properties.getProperty("exit"));
	}
	
	public static void printHomeMenu() {
		printHeading();
		System.out.println();
		System.out.println();
		System.out.println("1. " + properties.getProperty("login"));
		System.out.println();
		System.out.println("2. " + properties.getProperty("join"));
		System.out.println();
		System.out.println("0. " + properties.getProperty("exit"));	
	}

}