import java.util.*;
/*
Word Ladder II

Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord,
such that:

Only one letter can be changed at a time
Each intermediate word must exist in the word list
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
Note:
All words have the same length.
All words contain only lowercase alphabetic characters.
*/

/**
 * Bidirectional BFS + backtracking problem
 * 
 * The main algorithm is same with problem Word Ladder (p126). But now we need a HashMap to record the node with its parent or child node,
 * so we can build the path with backtracking
 * 
 * We have one start word and one end word, but there maybe many nodes between these two nodes. This is like a node has several edges.
 * Here, we don't need to create a Node, we just use a HashMap to record all nodes with its children or parents, whichever you want. We 
 * just want the direction keep the same, either forward or backward. Not all children will be a part of shortest path, so when we build
 * the path with backtracking, if we find current node does not has child Node (no such key in hash), then it means it is not a part of 
 * shortest path, we just return back
 * 
 * The remaining part is just a standard bidirectional BFS that we used on p126, so I will not discuss it here
 * 
 * Remark:
 * 1) Build string with charArray is faster than substring!
 * 2) Again, don't forget to remove words from set that comes from forward/backward set, since we don't want to add a same word more than once!
 * @author hpPlayer
 * @date Oct 26, 2015 11:23:22 PM
 */

public class Word_Ladder_II_p126_sol1 {	
	public static void main(String[] args){
		String begin = "hit";
		String end = "cog";
		
		Set<String> set = new HashSet<String>();
		set.add("hot");
		set.add("dot");
		set.add("dog");
		set.add("lot");
		set.add("log");

		Word_Ladder_II_p126_sol1 sol = new Word_Ladder_II_p126_sol1();
		System.out.println( sol.findLadders(begin, end, set));
	}
	
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        connected = false;
        Set<String> forward = new HashSet<String>();
        Set<String> backward = new HashSet<String>();
        forward.add(beginWord);
        backward.add(endWord);
        
        Map<String, List<String>> hs = new HashMap<String, List<String>>();
        
        
        //do BFS, update hs
        BFS(forward, backward, wordList, false, hs);

        List<List<String>> result = new ArrayList<List<String>>();
        
        //not connected
        if(!connected) return result;
        
        List<String> temp = new ArrayList<String>();
        //each path must starts with beginWord, so we add it here
        temp.add(beginWord);
        
        buildPath(result, temp, beginWord, endWord, hs);
        
        return result;
        
    }
    
    //flag whether there is a path from start to tail
    boolean connected;
    
    public void BFS(Set<String> forward, Set<String> backward, Set<String> set, boolean swap, Map<String, List<String>> hs){

        //boundary check
        if(forward.isEmpty() || backward.isEmpty()) return;
        
        //we will do BFS on smaller set. Here we assume forward is smaller, if not, swap forward with backward
        if(forward.size() > backward.size()){
            BFS(backward, forward, set, !swap, hs);
            return;
        }
        
        //new set for forward set, and it will include all new variants of words in forward set
        Set<String> newSet = new HashSet<String>();
        
        
        //we don't want add word in forward/backward twice, so we will firstly remove words in set from these two sets 
        set.removeAll(forward);
        set.removeAll(backward);
        
        for(String str : forward){
            //create variant for each word in forward
            //using charArray is faster than substring in creating new string
            for(int i = 0; i < str.length(); i++){
            	char[] temp = str.toCharArray();
                for(char c = 'a'; c <= 'z'; c++){
                    temp[i] = c;
                    
                    //create a new variant newStr
                    String newStr = new String(temp);
                    
                    //In this solution, we will record the forward path, i.e. the path from start to tail
                    //so the key in HashMap is parent Node, value is lists of children Node
                    //since we are doing Bidirectional BFS, we have to add key-val pair to hashMap based on 
                    //which direction we are currently at.
                    String key = !swap? str : newStr;
                    String val = !swap? newStr : str;
                    
                    List<String> list = hs.containsKey(key)? hs.get(key) : new ArrayList<String>();
                    
                    if(backward.contains(newStr)){
                        //if backward also contains this newStr, then we have connected two parts
                        //in other words, we have found the shortest path
                        connected = true;//update flag to indicate we have found path
                        //add current pair
                        list.add(val);
                        hs.put(key, list);   
                    }
                    if(!connected && set.contains(newStr)){
                        //we will expand newSet only when we have not found the shortest path
                        //if we can still expand current newSet, then we will add current pair to Map,
                        //though they may not be a part of final path (when we build path, we will ignore those
                        //invalid pair)
                        newSet.add(newStr);
                        
                        list.add(val);
                        hs.put(key, list);
                    }
                }
            }
        }
        
        if(!connected){
            //we will do next BFS only when we have not found the shortest path
            BFS(newSet, backward, set, swap, hs);
        }

    }
    
    public void buildPath(List<List<String>> result, List<String> temp, String start, String end, Map<String, List<String>> hs){
        if(start.equals(end)){
            //find a path
            result.add(new ArrayList<String>(temp));
            return;
        }
        
        //not necessary each child of parent is a part of shortest path.
        //if we found current node does not have children (not a key in hs), then we just return indicating it is not a part of shortest path
        if(!hs.containsKey(start)) return; 
        
        for(String str : hs.get(start)){
            temp.add(str);
            buildPath(result, temp, str, end, hs);
            temp.remove(temp.size()-1);
        }
        
    }
    
}
