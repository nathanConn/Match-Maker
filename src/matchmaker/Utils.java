package matchmaker;


public class Utils {
	
    public static String rpad(String stringToPad, char aCharacter, int anInt) {
        String newString;
        if (stringToPad.length() >= anInt) return stringToPad;
        newString = stringToPad;
        while (newString.length() < anInt) {
            newString += aCharacter;
        }
        return newString;
    }

}