/*
Shortest Word Distance

Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = ¡°coding¡±, word2 = ¡°practice¡±, return 3.
Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
*/

/**
 * Array problem
 * 
 * The intuitive solution is to record all indexes that word1 and word2 appear, then calculate the difference between each pair,
 * then found the one with min Value. But this is not necessary, since if now we found word1 at index1 and word2 at index2, say
 * index1 is before index2, then the next word1 with a shorter distance must be a in later index, so we can simply discard index1
 * as we no longer need it. Same rule applies to index2. So we just need to use two variables to search the short distance
 * 
 * @author hpPlayer
 * @date Oct 8, 2015 1:20:41 AM
 */
public class Shortest_Word_Distance_p243_sol1 {
    public int shortestDistance(String[] words, String word1, String word2) {
        //set the inital value to -1, so we know where we found the first pair
        int index1 = -1, index2 = -1;
        //we won't have a distance longer than words.length - 1
        //assume word1 in index 0, word2 in index len -1, then the max difference in this array
        //will be len - 1
        int result = words.length;
        
        for(int i = 0; i < words.length; i++){
            if(words[i].equals(word1)){
                index1 = i;
            }
            
            if(words[i].equals(word2)){
                index2 = i;
            }  
            
            if(index1 != -1 && index2 != -1){
                //since we don't know which one is more recently updated, we have to use abs to get postive value
                result = Math.min(result, Math.abs(index1- index2));                
            }

        }
        
        return result;
    }
}
