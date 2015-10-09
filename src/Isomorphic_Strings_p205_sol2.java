/**
 * HashMap solution
 * 
 * The main idea is similar to sol1, but here the value stored in hashMap is index.
 * For a pair of chars, they should appear at same index in t and s. We just need to record the first appearance index, as if they appear
 * at same time later, they will still get same value, but if one of them matches a third char, then third char must has a different 
 * first appearance index and we will catch it, then return false immediately
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
            //we use "lazy" strategy here. We just need to set map value to be the index of first appearance
            //If char in s and t always appear in same index, then their map value
            //must be same. If one of them appear in different index, then we should found immediately, as the
            //third char must correspond to a different first appearance index, which is not same with char in pair
            
            if(map1[s.charAt(i)] == 0) map1[s.charAt(i)] = i + 1;
            if(map2[t.charAt(i)] == 0) map2[t.charAt(i)] = i + 1;
            
            //if current char in s and t do not appear in same index before, then they are an unmatched pair, return false
            if(map1[s.charAt(i)] !=  map2[t.charAt(i)]) return false;
        }
        
        return true;
    }
}
