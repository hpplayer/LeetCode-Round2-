import java.util.HashMap;

/**
 * 
 * This problem is very similar to problem Roman to Integer (p13). We both need to convert a string to a number. So we need a HashMap
 * to tell us which char is correspond to which value. We scan the string from left to right, if we still have loop left, we will increase
 * the previous result by 26.
 * 
 * Remark:
 * when build the HashMap, remember to use casting correctly (double () ) , otherwise JAVA will treat input as char code
 * @author hpPlayer
 * @date Oct 20, 2015 2:18:31 PM
 */

public class Excel_Sheet_Column_Number_p171_sol1 {
	public static void main(String[] args){
		System.out.println(titleToNumber("BA"));
	}
    public static int titleToNumber(String s) {
        //create the Hash Table
        HashMap<Character, Integer> hs = new HashMap<Character, Integer>();
        for(int i = 0; i < 26; i++){
            hs.put( (char) ('A' + i), i+1);
        }
        
        int result = 0;
        //build result
        for(int i = 0; i <s.length(); i++){
            //if we still have number left in s, we will update previous result by * 26
            result *= 26;
            result += hs.get(s.charAt(i));
        }
        
        return result;
    }
}
