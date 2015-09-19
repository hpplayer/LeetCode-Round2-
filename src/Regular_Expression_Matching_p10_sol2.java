/**
 * Solution2: Backtrack solution. 
 * Like sol1, each time our operation will depends on pattern.
 * If pattern's length == 0, then we check if we also reach the tail of string
 * If pattern's length == 1, or our next char is not "*", then we compare current char, and recursive call on following seq
 * if needed.
 * If our next char is "*", and our current pair of char match, then we can recursively check each occurrence of using *,
 * one can use 0 time ,1 time, ....
 * If our next char is "*", and our current pair does not match, then we can only treat preceding element and * as zero
 * occurrence and search following sequence
 * 
 * Recursively search each occurrence of using * is a kind of brute force, so this solution is slow
 * @author hpPlayer
 * @date Sep 18, 2015 10:28:14 PM
 */
public class Regular_Expression_Matching_p10_sol2 {
	public static void main(String[] args){
		System.out.println(new Regular_Expression_Matching_p10_sol2().isMatch("aa", "a*"));
	}
	
    public boolean isMatch(String s, String p) {
        //we will look at pattern p, so we will check p.length()
        
        //if p.length == 0, then return s.length
        if(p.length() == 0) return s.length() == 0;
        
        
        //if p.length == 1 or we don't have * after it
        if(p.length() == 1 || p.charAt(1) != '*'){
            if(s.length() == 0 || (p.charAt(0) != s.charAt(0) && p.charAt(0) != '.')){
                return false;
            }else{
                return isMatch(s.substring(1), p.substring(1));
            }
        }
        
        //if p.length >= 2 and we have * after it
        //each loop, we will use an element provided by *, firstly start with 0 element
        while(s.length() > 0 && ((s.charAt(0) == p.charAt(0)) || p.charAt(0) == '.')){
            if(isMatch(s, p.substring(2))){//check current pair
                return true;
            }
            s = s.substring(1);//use one element provided by *
            
          //if we have matched all chars in s and p is end with *,
          //then we can simply return true
            if (s.length() == 0 && p.length() ==2) return true;
        }
        
        //we will reach here when p end with *, but * cannot help, then we can only treat preceding element and * as 
        //zero occurence and search following sequence
        return isMatch(s, p.substring(2));
    }
}
