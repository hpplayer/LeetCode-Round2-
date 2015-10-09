import java.util.*;

/*
Word Pattern

Given a pattern and a string str, find if str follows the same pattern.

Examples:
pattern = "abba", str = "dog cat cat dog" should return true.
pattern = "abba", str = "dog cat cat fish" should return false.
pattern = "aaaa", str = "dog cat cat dog" should return false.
pattern = "abba", str = "dog dog dog dog" should return false.
Notes:
patterncontains only lowercase alphabetical letters, and str contains words separated by a single space.
Each word in str contains only lowercase alphabetical letters.

Both pattern and str do not have leading or trailing spaces.
Each letter in pattern must map to a word with length that is at least 1.
*/

/**
 * HashMap solution
 * 
 * This problem is very similar to problem Isomorphic Strings (p205).
 * 
 * The difference is in p205 we only need to compare chars and now we need to compare char with String.
 * So we have to use HashMap. We need a one-to-one relationship, so we need at least two HashMap to record the relationship
 * forward and backward. Unlike p205, where we have initial value for each char, now we don't have default value in HashMap.
 * So we firstly check if HashMap contains such key, if no, we add it, then we compare the values, i.e. the indexes of first appearance
 * of key char or key string. Not same, return false, otherwise, we continue. We actually just want to make sure we 
 * always see the keys in pair at same time, in other words, we want to see the value from two maps are same. So actually we don't need
 * to frequently update the last appearance index, since if appear at the same time, we will increment both indexes, if not, we will
 * find it and report it immediately, since the third word must return a different index. So here, I did not update the map if 
 * we encounter the same pair more than once. We just need to make sure values are same in the map. It will save time to update the map.
 * 
 * 
 * Remark:
 * 1. To be convenient, we firstly split the input str based on " ". Ask interviewer if we are allowed to use that
 * 2. We store interger in HashMap, so remember use equals() to compare them instead of "==" !!!!!!!!!!!!!
 * 3. I have updated p205 with the similar "lazy" update strategy
 * 
 * @author hpPlayer
 * @date Oct 8, 2015 2:49:51 PM
 */
public class Word_Pattern_p290_sol1 {
	public static void main(String[] args){
		String pattern = "aba";
		String str = "cat dog zoo";
		
		System.out.println(new Word_Pattern_p290_sol1().wordPattern(pattern, str));
	}
	
    public boolean wordPattern(String pattern, String str) {
        //split the str based on " ", as described by the problem, it will be guaranteed to get word by word
        String[] strs= str.split(" ");
        
        //if not same length, we will return false
        if(strs.length != pattern.length()) return false;
        
        //create two HashMap to build one-to-one relationship
        //key is char/string, value is index of FIRST appearance
        HashMap<Character, Integer> map1 = new HashMap<Character, Integer>();
        HashMap<String, Integer> map2 = new HashMap<String, Integer>();
        
        for(int i = 0; i < pattern.length(); i++){
            char c = pattern.charAt(i);
            //since now we don't have default value in map, we will firstly set the first appearance index.
            // If one of them has already been seen before, then our hashMap will have difference
            //first appearance indexes for this pair.
            if(!map1.containsKey(c)){
                map1.put(c, i);
            }
            
            if(!map2.containsKey(strs[i])){
                map2.put(strs[i], i);
            }            
            
            //if first appearance index is not same, we will return false
            //NOTE: we save Integer as value in map, and Integer is object, so we can't use "==" to compare
            //we need !.equals()
            if(!map1.get(c).equals(map2.get(strs[i]))) return false;
            
        }
        
        return true;
    }
}
