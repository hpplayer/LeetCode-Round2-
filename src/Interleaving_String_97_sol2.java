import java.util.*;

/**
 * Backtracking + Memorization
 * 
 * Like DP, we try to find a valid way to match all chars in s1 and s2 with s3. In dp, if the way false, we will set the matrix value to false
 * Here, we will use backtracking, and try another match. To avoid duplicate matches, now we use memorization technique. The boundary case if
 * we found all strings are empty, then it means matches have been completed. Otherwise if s3 is empty, while either s1 or s2 is not, we shall
 * return false. 
 * 
 * @author hpPlayer
 * @date Oct 22, 2015 4:44:59 PM
 */
public class Interleaving_String_97_sol2 {
    //add memorization to avoid duplicate matches
    private Set<String> deathGroup = new HashSet<String>();
    public boolean isInterleave(String s1, String s2, String s3) {
        String group = s1 + '#' + s2 + '#' + s3;
        if(deathGroup.contains(group)) return false;
        
        //if length does not match, then return false
        //it also covers the boundary case len(s3) == 0. In such case, if one of the s1 and s2 is not empty
        //we will return false as well
        if(s1.length() + s2.length() != s3.length()) return false;
        
        //all strings are empty, we have finished the match, return true
        if(s1.length() == 0 && s2.length() == 0 && s3.length() == 0) return true;
        
        //if first char in s1 and s3 match, then use this match, and check rest
        if(s1.length() != 0 && s1.charAt(0) == s3.charAt(0)){
            if(isInterleave(s1.substring(1), s2, s3.substring(1))) return true;
        }
 
        //if first char in s2 and s3 match, then use this match, and check rest
        if(s2.length() != 0 && s2.charAt(0) == s3.charAt(0)){
            if(isInterleave(s1, s2.substring(1), s3.substring(1))) return true;
        }    
        
        deathGroup.add(group);
        
        return false;
    }
}
