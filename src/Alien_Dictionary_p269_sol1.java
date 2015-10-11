import java.util.*;

/*
 
Alien Dictionary

There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you.
You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language.
Derive the order of letters in this language.

For example,
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".

Note:
1. You may assume all letters are in lowercase.
2. If the order is invalid, return an empty string.
3. There may be multiple valid order of letters, return any one of them is fine.
		
*/	

/**
 * BFS / topological solution
 * 
 * This problem is similar to problem p210 Course Schedule II, where we need to visit the graph based on topological order, while
 * filling the result array.
 * 
 * The tricky part is to find the problem actually can be split into two parts: 1) create prerequisites table 2) topological search
 *  
 * Firstly we need to create the prerequisites table. The order in same word is not important, we can freely have abc or cba.
 * But the order between different words is very important. We care about the first different word after common part like abc and acb
 * so in above example, we know b must appear before c, thus we will have the prerequisites that b decides c or c depends on b.
 * In BFS, we build the string forward, so we use "decides" edge. We will do this to all words in dict
 * 
 * Secondly we will do topological search. We will start with nodes without incoming edge. We put them in queue, then remove all
 * their outgoing edges and make a series new nodes without incoming edge, then add those new nodes into queue. We will do this 
 * until we visit all nodes (if there is no cycle, we will not put nodes in cycle into queue). Since we visit nodes following 
 * topological order, the result should exactly follow the order too.
 * 
 * Sol1 provides iterative BFS solution
 * Sol2 provides recursive DFS solution
 * 
 * For this problem, I think sol1 is more recommended due to its cleanness and intuitive logic.
 * But sol2 also has its own advantage, we will find cycle during the DFS and return immediately. No need to wait until then end to report
 * 
 * @author hpPlayer
 * @date Oct 11, 2015 3:45:47 PM
 */
public class Alien_Dictionary_p269_sol1 {
	public static void main(String[] args){
		System.out.println(new Alien_Dictionary_p269_sol1().alienOrder(new String[]{"ab","ac", "db", "dc", "eb", "ec"}));
	}
    public String alienOrder(String[] words) {
        //**first part, create prerequisites table**
        
        //create the degree table, monitoring how many incoming edges each node has
        //since we don't how many chars in total, we have to use HashTable that can change size
        HashMap<Character, Integer> degrees = new HashMap<Character, Integer>();
        //in BFS, we will create result forward, so we need use "decides" relation
        //To avoid add same relationship multiple times, we use HashSet to store nodes
        //Ex: za zb ca cb, both shows relationship a decides b, we don't want have 2 b after a in relations
        HashMap<Character, Set<Character>> hs = new HashMap<Character, Set<Character>>();
        
        String prev = "";//prev word
        //fill prerequisites table and degree table
        for(String word : words){
            //we put degree table here to include each char in dict
            for(char c : word.toCharArray()){
                if(!degrees.containsKey(c)) degrees.put(c, 0);
            }    
            //then search for the the first char after common part
            for(int i = 0; i < Math.min(prev.length(), word.length()); i++){
                char a = prev.charAt(i), b = word.charAt(i);
                if(a != b){
                    //we use "decides" relation, so a is key while b is a value in related value set
                    //we may not necessary to have prerequisites for each char, so we put it here and 
                    //no need to include all chars in dict, if some words do not have dependency
                    if(!hs.containsKey(a)) hs.put(a, new HashSet<Character>());
                    hs.get(a).add(b);
                    //then we update incoming edge table (degrees table)
                    degrees.put(b, degrees.get(b) + 1);
                
                    break;//we only care about first char, so break now
                }
            }
            prev = word;//update prev word
        }
        
        //**second part, use BFS to topologically visit the graph **
        Queue<Character> que = new LinkedList<Character>();
        
        for(Character c : degrees.keySet()){
            //add first series of nodes that do not have incoming edges
            if(degrees.get(c) == 0){
                que.offer(c);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        while(!que.isEmpty()){
            Character curr = que.poll();
            sb.append(curr);
            
            //since we may not necessary include all nodes in prerequisites table, we need do boundary check first
            if(!hs.containsKey(curr)) continue;
            //remove outgoing edges from c, add new nodes if all their incoming edges have been removed
            for(Character c : hs.get(curr)){
                degrees.put(c, degrees.get(c) - 1);
                if(degrees.get(c) == 0){
                    que.offer(c);
                }
            }
        }
        
        //check the result length with supposed length from keySize()
        //if not same, then there must be some nodes in cycle and did not included to our queue
        return sb.length() == degrees.size()? sb.toString() : "";
    }
}
