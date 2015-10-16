import java.util.*;

/*
Word Pattern II


Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

Examples:
pattern = "abab", str = "redblueredblue" should return true.
pattern = "aaaa", str = "asdasdasdasd" should return true.
pattern = "aabb", str = "xyzabcxzyabc" should return false.
Notes:
You may assume both pattern and str contains only lowercase letters.
*/

/**
 * Backtracking problem
 * 
 * The tricky part is to write a clean code and remember to compare char-string match in two directions
 * 
 * In the given condition, we don't how can we match a char in pattern with a substring in string, so we have to use brute-force like 
 * solution, i.e. backtracking technique to try all possible matches. We have to use HashMap to record the matches we have used. Since 
 * the matches are one-to-one relations, we have to use two HashMaps. Our recursion is based on the pattern, because we know each char
 * in a pattern should related with a match. Therefore, there will be two cases 1) we have not used current char in pattern yet 2) we
 * have used current char in previous match. In case 1), we will try all possible substring in s to match this char. To avoid incorrectly
 * use a previous used substring, we used our second hashMap or which could be reduced to hashSet to check if current substring has been
 * previously used. In case 2) we check if current start substring in string is matched with this c as record in first hashMap, if so
 * let match continue
 * 
 * Remark:
 * 1) when checking a char that has been previously used, we can use built-in function s.startsWith() to check the prefix
 * 
 * 
 * Sol1 provides a recursive solution based on backtracking
 * Sol2 provides an iterative solution, which uses classic way to build iterative solution (using inner class + stack). But due to the 
 * operation on copy HashMap and HashSet, the run time is 406ms compare with 110ms of recursion. Anyway, it is an iterative solution.
 * 
 * @author hpPlayer
 * @date Oct 15, 2015 2:44:18 PM
 */
public class Word_Pattern_II_p291_sol1 {
	public static void main(String[] args){
		String a = "gh";
		String b = "i";
		
		System.out.println(new Word_Pattern_II_p291_sol1().wordPatternMatch(a, b) );
	}
    public boolean wordPatternMatch(String pattern, String str) {
        return DFS(pattern, str, new HashMap<Character, String>(), new HashSet<String>());
    }
    
    public boolean DFS(String pattern, String str, HashMap<Character, String> hs, HashSet<String> used){
        if(pattern.length() == 0 && str.length() == 0){
            //we reach the end of pattern and str, this is a valid match, return true
            return true;
        }
        
        if(pattern.length() == 0 || str.length() == 0){
            //we only reach the end of one input, invalid match, return false
            return false;
        }
        
        //for pattern, we will look it char by char
        char c = pattern.charAt(0);
        
        if(hs.containsKey(c)){
            //if we already use c to match string, then current start string must match this string
            //otherwise we return false
            
            //we use string.startsWith() to check such string
            if(!str.startsWith(hs.get(c))) return false;
            
            //start string is matched, continue match
            return DFS( pattern.substring(1), str.substring( hs.get(c).length()), hs, used  );
        }else{
            //an unvisited char, we will try to match it with different substring in str
            for(int i = 1; i <= str.length(); i++){
                //since we used a char in pattern, we must use a substring in str as well.
                //so starts  with i = 1, which will gives us a substring with len 1.
                //current char can also match the rest part of str as well
                //ex: pattern ababc, str: dogcatdogcatpeople, in such case, c should match people, i.e. the rest part
                
                String s = str.substring(0, i);
                
                //current string has been used by a previous match, we couldn't use it
                if(used.contains(s)) continue;
                
                //use current char to match current string
                hs.put(c, s);
                used.add(s);
                if(DFS(pattern.substring(1), str.substring(i), hs, used)) return true;
                
                //current match is invalid, try next match
                //reset environment to ensure a clean environment for next match
                hs.remove(c);
                used.remove(s);
            }
            
                    
            //we tried all matches, still no luck, return false
            return false;
        }

    }
}
