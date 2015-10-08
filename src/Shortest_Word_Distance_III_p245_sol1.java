/*
Shortest Word Distance III

This is a follow up of Shortest Word Distance (p243). The only difference is now word1 could be the same as word2.

Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

word1 and word2 may be the same and they represent two individual words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = ¡°makes¡±, word2 = ¡°coding¡±, return 1.
Given word1 = "makes", word2 = "makes", return 3.

Note:
You may assume word1 and word2 are both in the list.
*/

/**
 * Array problem
 * 
 * This problem is not tricky, we can solve it use intuitive solution, i.e. treat same words or different words as two cases.
 * In different words case, we update index1 and index2 as we did in Shortest_Word_Distance_p243_sol1
 * In same words case, we update index1 and index2 sequentially, we use index1 to record the previous appearance of word
 * and use index2 to record the current appearance of word
 * 
 * @author hpPlayer
 * @date Oct 8, 2015 2:36:20 AM
 */
public class Shortest_Word_Distance_III_p245_sol1 {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int result = Integer.MAX_VALUE;
        int index1 = -1, index2 = -1;
        boolean sameWord = word1.equals(word2); 
        
        for(int i = 0; i < words.length; i++){
            if(words[i].equals(word1)){
                if(sameWord){
                    //sameWord case, we update index sequentially, here we use index1 to record previous index
                    index1 = index2;
                    index2 = i;
                }else{
                     //otherwise we update index1 as we do in Shortest_Word_Distance_p243_sol1
                     index1 = i;
                }
            }else if(words[i].equals(word2)){
                 //use else if to avoid check same index twice
                 index2 = i;
            }
            
            //we will update result after we found both words
            if(index1 != -1 && index2 != -1) result = Math.min(result, Math.abs(index1 - index2));
        }
        
        return result;
    }
}
