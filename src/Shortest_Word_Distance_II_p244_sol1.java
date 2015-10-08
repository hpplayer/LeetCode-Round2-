import java.util.*;

/*
Shortest Word Distance II

This is a follow up of Shortest Word Distance (p243). The only difference is now you are given the list of words and your method will be called repeatedly many times with different parameters. How would you optimize it?

Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = ¡°coding¡±, word2 = ¡°practice¡±, return 3.
Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
*/

/**
 * HashMap problem
 * 
 * The intuitive solution is not hard. In the constructor we build a map and put all word's indexes into the map. Then we pull the indexes
 * for input word1 and word2, calculate the difference, then return the min distance.
 * The tricky part is how to speed up the process to found the min distance.
 * 
 * As discussed in problem Shortest Word Distance (p243), if index1 is smaller than index2, than the shorter distance must comes from
 * a later index3 compared with index2, so there is no need to keep index1. Similarly, here we actually don't need to use a nested
 * loop to calculate the distance of different combinations. We can simply discard the smaller indexes to find the shortest distance.
 * Therefore the time complexity will drop from O(m*n) to O(m + n).
 * 
 * Sol1 below provides a such solution. Word. The constructor has the intuitive idea to create the HashMap, but in shortest(), we use
 * a merge-sort liked approach to move pointers in two lists wisely. 
 * 
 * 
 * @author hpPlayer
 * @date Oct 8, 2015 1:52:24 AM
 */
public class Shortest_Word_Distance_II_p244_sol1 {
	    //private object can only accessed by class
	    //final object cannot be modified once initialized
	    private final HashMap<String, List<Integer>> hs;
	    
	    public Shortest_Word_Distance_II_p244_sol1(String[] words) {
	        hs = new HashMap<String, List<Integer>>();
	        //add each word and its indexes into hashMap
	        for(int i = 0; i < words.length; i++){
	            if(!hs.containsKey(words[i])){
	                hs.put(words[i], new ArrayList<Integer>());
	            }
	            
	            hs.get(words[i]).add(i);
	        }

	    }

	    public int shortest(String word1, String word2) {
	        //the tricky part is how to wisely search for the shortest distance
	        //since for each pair of index, the shorter distance will only come from
	        //updating at least one index, so there is no need to keep the index which has a smaller index
	        List<Integer> list1 = hs.get(word1);
	        List<Integer> list2 = hs.get(word2);
	        
	        //i is pointer in list1, j is pointer in list2
	        int i = 0, j = 0;
	        
	        int result = Integer.MAX_VALUE;
	        
	        //we will still consider all indexes, but not all combinations of indexes
	        //Therefore the time complexity reduced from O(mn) (nested loops) to O(m + n) (move pointer wisely)
	        while(i < list1.size() && j < list2.size()){
	            result = Math.min(result,  Math.abs(list1.get(i) - list2.get(j)) );
	            
	            if(list1.get(i) < list2.get(j)){
	                //if index in list1 is smaller, then we will discard it, as we can never find a 
	                //shorter distance contains this index
	                i ++;
	            }else{
	                j++;
	            }
	        }
	        
	        return result;
	    }

	// Your WordDistance object will be instantiated and called as such:
	// WordDistance wordDistance = new WordDistance(words);
	// wordDistance.shortest("word1", "word2");
	// wordDistance.shortest("anotherWord1", "anotherWord2");
}
