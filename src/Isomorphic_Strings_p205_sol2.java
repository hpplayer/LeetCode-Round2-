/**
 * HashMap solution
 * 
 * The main idea is similar to sol1, but here the value stored in hashMap is index.
 * For a pair of chars, they should appear at same index in t and s. If not, one of the char must be used by another pair already
 * 
 * In sol2, to make index 0 differed from initial array value 0, we simply let each index assignment + 1, then we are good to go!
 * 
 * @author hpPlayer
 * @date Oct 6, 2015 11:27:34 PM
 */
public class Isomorphic_Strings_p205_sol2 {
    public boolean isIsomorphic(String s, String t) {
        //pre check to make sure they have same length
        if(s.length() != t.length()) return false;
        
        int[] map1 = new int[256];
        int[] map2 = new int[256];
        
        for(int i = 0; i < s.length(); i++){
        	//if current char in s and t do not appear in same index before, then they are an unmatched pair, return false
            if(map1[s.charAt(i)] != map2[t.charAt(i)]) return false;
            
            //otherwise assign same index to two chars in pair
            map1[s.charAt(i)] = map2[t.charAt(i)] = i + 1; //+1 to make difference between appearance at index 0 and initial value 0
        }
        
        return true;
    }
}
