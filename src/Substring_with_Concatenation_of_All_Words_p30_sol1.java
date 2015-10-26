import java.util.*;

/*
Substring with Concatenation of All Words

You are given a string, s, and a list of words, words, that are all of the same length.
Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

For example, given:
s: "barfoothefoobarman"
words: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).
*/

/**
 * 
 * Sliding window problem, and it is very similar to problem Minimum_Window_Substring_p76_sol1 (p76)
 * 
 * sol1 is lengthy but not hard to understand. The difficulty is to come up with the idea of using HashMap to count the 
 * Occurrences of words and using left and right pointer to control the window size.
 * 
 * Basic idea:
 * 1) we use two pointers to control the window. If newly incoming word does not belong to dict, then our window will stop extend
 * and we will move the whole window skip over this word. 
 * 2) we use two hashMaps to count the expected occurrences of word and real occurrences of word. If newly incoming word belongs
 * to dict, then our window will include this new word, and increase the count accordingly. But if we found the real occurrences
 * exceed the expected occurrences, then we will have to move the left pointer to skip words until we skip one occurrence of this
 * word
 * 3) we use a global variable called "count" to track how many valid words we have included in our result. If the count = len(dict)
 * then we will add left pointer to the result
 * 
 * Remark:
 * There are three cases that we need left pointer
 * 1) the new word does not belong to dict, then we have to move left pointer skip over this new word
 * 2) the new word makes the occurrences of a word exceeds its expected occurrences, then we will move left pointer until we skip
 * one occurrence of this word
 * 3) we have reached the ideal size of win, after inserting left pointer into the result, we will have to move left pointer skip
 * one word to start the search for new window
 * 
 * Time complexity is O(len(word) * len(s) / Len(word)) = O(len(s))
 * 
 * This problem is similar to problem:
 *  Longest_Substring_Without_Repeating_Characters_p3_sol1,
 *  Minimum_Window_Substring_p76_sol1
 *  Longest_Substring_with_At_Most_Two_Distinct_Characters_p159_sol1
 *  
 * @author hpPlayer
 * @date Sep 29, 2015 2:46:14 PM
 */

public class Substring_with_Concatenation_of_All_Words_p30_sol1 {
	public static void main(String[] args){
		String a = "a";
		String b = "a";
		String c = new String("a");
		System.out.println(a == c);
	}
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<Integer>();
        if(words.length == 0) return result;
        
        //get each word length
        int len = words[0].length();
        HashMap<String, Integer> expected = new HashMap<String, Integer>();
        
        //put each word and its occurence in map
        //Notice: when we create a new string, JVM will firstly search for the string pool, so in general case
        //string a = "a" and string b = "a" will point to a same object, thus we can safely use string as a key
        //here
        for(String word : words){
            if(!expected.containsKey(word)){
                expected.put(word, 1);
            }else{
                expected.put(word, expected.get(word) + 1);
            }
        }
        
        //i is the start point of our win, since later win size will always increase by len
        //we have to mannually adjust the start point from 0 to len - 1 to try all possibilities
        for(int i = 0; i < len; i++){
            int left = i;//curr win left bound
            int count = 0;//count of word we had have in words
            HashMap<String, Integer> visited = new HashMap<String, Integer>();
            
            //right is the right bound of win. To be more specificly, right here is the start index of last
            //word in current window
            for(int right = left; right + len <= s.length(); right += len){
                String word = s.substring(right, right + len);//get the last word in current win
                if(!expected.containsKey(word)){
                    //if current word is not a word in dict, we will move our win skip over current word, i.e.
                    //reset every parameters and start a new window after current word
                    count = 0;
                    visited.clear();
                    left = right + len;
                    continue;
                }
                
                //if current word is in dict, then we add it into our count map
                if(!visited.containsKey(word)){
                    visited.put(word, 1);
                }else{
                    visited.put(word, visited.get(word) + 1);
                }                
                count ++;//add count
                
                //if count in map is larger than expected, then we will move win skip the last occur of this word
                while(visited.get(word) > expected.get(word)){
                    String temp = s.substring(left, left + len);//get the word that will be removed
                    visited.put(temp, visited.get(temp) -1);//remove from count
                    count --;//decrease total count
                    left += len;//move len steps away
                }
                
                //after all above operations, if we still has a perfect window, then we add it into the result
                //then we manually move left bound len steps away to search for next perfect window
                if(count == words.length){
                    result.add(left);
                    
                    String temp = s.substring(left, left + len);//get the word that will be removed
                    visited.put(temp, visited.get(temp) -1);//remove from count
                    count --;//decrease total count
                    left += len;//move len steps away
                }
                
            } 
            
        }
        
        return result;
    }
}
