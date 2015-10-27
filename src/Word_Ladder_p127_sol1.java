import java.util.*;
/*
Word Ladder

Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord,
such that:

Only one letter can be changed at a time
Each intermediate word must exist in the word list
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
*/

/**
 * Bidirectional BFS
 * 
 * The tricky part is to find BFS is much better than DFS in this problem since we want stop searching as long as we find the shortest path. Besides,
 * we need come up several ways to speed up the program. Finally, we also want to a concise and beautiful code
 * 
 * The first optimization we used to speed up the program is using BFS not DFS. BFS is very useful in finding the shortest path problem. Unlike DFS, where
 * we finish all paths before we know the shortest one. In BFS, as long as we find a valid path, it must be the shortest path.
 * 
 * The second optimization we used to speed up the program is using bidirectional BFS. Here each level of BFS means a set of variants that comes from
 * start or end word. We may have a lot of variant from one direction while having a very little variant from another direction. So if we always work
 * on the one with smaller size then it will be faster
 * 
 * The third optimization we used is using Set. We use set to check if one of the variant can be connected with the variant from another direction
 * 
 * The forth optimization we used is building variant of a word by changing char in it and check if it is in wordList, it will be much faster than 
 * searching inside the wordList to find a word that has only one char differed from the target word (since the wordList maybe very long)
 * 
 * We also use a recursive BFS here, so the code can be short and clean. If we are required to convert it to iterative way, we just need to remove words
 * from a set, then fill the set with variant we newly created, we iteratively repeat this until we find a match or one set is empty.
 * 
 * 
 * Remark:
 * 1) Don't forget the ending condition for BFS, which is either one of the set becomes empty!!!!!!!!!!!
 * 2) the num of steps = the num of word in final list
 * 3) We always discard variants in current set before doing next BFS, is it possible that we will miss some matches? No, it is not possible, if we 
 * can find a match from prev level with another part, then we should report it at that time, not now or later.  
 * 4) We will remove all words in forward/backward set from set (dict) before doing the BFS, so we won't add same words into forward/backward more
 * than once. Important!!!!!!!!!!!!!
 * 
 * @author hpPlayer
 * @date Oct 26, 2015 8:26:05 PM
 */

public class Word_Ladder_p127_sol1 {
	public static void main(String[] args){
		String begin = "hit";
		String end = "cog";
		
		Set<String> set = new HashSet<String>();
		set.add("hot");
		set.add("dot");
		set.add("dog");
		set.add("lot");
		set.add("log");
		
		Word_Ladder_p127_sol1 sol = new Word_Ladder_p127_sol1();
		System.out.println( sol.ladderLength(begin, end, set));
	}
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        Set<String> forward = new HashSet<String>();
        Set<String> backward = new HashSet<String>();
        
        //initialize the sets
        forward.add(beginWord);
        backward.add(endWord);
        
        return BFS(forward, backward, wordList, 2);
    }
    
    //since we always do BFS on smaller set, forward set may not necessary the one with forward direction
    public int BFS(Set<String> forward, Set<String> backward, Set<String> set, int level){
        //boundary case, if one of the forward and backward set is empty
        if(forward.isEmpty() || backward.isEmpty()) return 0;
        
        //we will do BFS on smaller set, so the searching time will be faster
        //here we assume size of forward set is smaller, if not we will swap forward and backward
        if(forward.size() > backward.size()) return BFS(backward, forward, set, level);
        
        //We will remove all words in set from forward/backward set, so we won't add duplicate word into forward/backward more than once
        set.removeAll(forward);
        set.removeAll(backward);
        
        //new set for forward
        Set<String> hs = new HashSet<String>();
        
        for(String str : forward){
            for(int i = 0; i < str.length(); i++){
            	
            	//searching for each possible intermediate word or we called variant above
                 for(char c = 'a'; c <= 'z'; c++){
                    String newStr = str.substring(0, i) + c + str.substring(i + 1);
                    
                    //if newStr is in backward set, then we found the shortest path, return it
                    if(backward.contains(newStr)){
                        return level;
                    }
                    
                    //if newStr is in not in backward set, then we add it to hs to prepare next set
                    if(set.contains(newStr)){
                        //if newStr is a valid intermediate word in set
                        hs.add(newStr);
                    }
                }               
            }
        }
       
        
        //begin next BFS search, our bidirectional BFS will always start with smaller set, so it is not 
        //necessary to start set1 then set2
        return BFS(hs, backward, set, level + 1);
    }
}
