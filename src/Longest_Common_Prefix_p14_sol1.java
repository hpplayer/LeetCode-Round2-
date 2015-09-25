/**
 * String problem. I would say this problem requires some observations
 * 
 * This problem can be attacked by several ways, the most efficient way should return the Longest Common Prefix once we found it.
 * Since Longest Common Prefix is a very strict requirement, it requires all cells in array have such Longest Common Prefix. So before
 * we insert each new char into the result prefix, we have to check all cells in array to make sure each cell has this char. 
 * We need a sample cell to start with check
 * To be convenient, we pick the first cell. The length of our final prefix will not exceed this first string. We just check each char in
 * the first string, if some later cells does not have this char(different char or meets the tail), we will report current prefix, otherwise 
 * we will insert this char after we checked all cells 
 * 
 * Time is O(n*k), where k is the length of first string
 * @author hpPlayer
 * @date Sep 24, 2015 9:27:38 PM
 */
public class Longest_Common_Prefix_p14_sol1 {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0) return "";
        
        for(int i = 0; i < strs[0].length(); i++){
            char c = strs[0].charAt(i);
            for(String str : strs){
                if(i >= str.length() || str.charAt(i) != c){
                    return strs[0].substring(0, i);//exclude index i
                }
            }
        }
        
        return strs[0];
    }
}
