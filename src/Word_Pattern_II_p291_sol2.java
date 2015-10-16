import java.util.*;
/**
 * Exactly same algorithm with sol1 but using iterative way (inner class + stack)
 * To save space, I used pointer in inner class instead of string, so we don't require much space on string pool
 *  
 * This solution is neither time-wisely nor space-wisely, not recommended
 * 
 * @author hpPlayer
 * @date Oct 15, 2015 2:46:27 PM
 */

public class Word_Pattern_II_p291_sol2 {
	public static void main(String[] args){
		String a = "abab";
		String b = "redblueredblue";
		
		System.out.println(new Word_Pattern_II_p291_sol2().wordPatternMatch(a, b) );
	}
    public class MyNode{
        int p;
        int s;
        HashMap<Character, String> hs ;
        HashSet<String> visited;
        
        public MyNode(int p, int s, HashMap<Character, String> hs,  HashSet<String> visited){
            this.p = p;
            this.s = s;
            this.hs = hs;
            this.visited = visited;
        }
    }
    public boolean wordPatternMatch(String pattern, String str) {
        Stack<MyNode> stack = new Stack<MyNode>();
        stack.push( new MyNode(0, 0, new HashMap<Character, String>(), new HashSet<String>() )  );
        
        while( ! stack.isEmpty()){
            MyNode node = stack.pop();
            if(pattern.length() == node.p && str.length() == node.s){
                return true;
            }
            
            if(pattern.length() == node.p || str.length() == node.s){
               continue;
            }
            
            char c = pattern.charAt(node.p);
            if(node.hs.containsKey(c)){
                if(str.substring(node.s).startsWith(node.hs.get(c))){
                    stack.push( new MyNode(node.p+1,  node.s+node.hs.get(c).length(), node.hs, node.visited));
                }
            }else{
                for(int i = node.s + 1; i <= str.length(); i++){
                    String s = str.substring(node.s, i);
                    if(node.visited.contains(s)) continue;
                    HashMap<Character, String> newHs = new HashMap<Character, String>(node.hs);
                    HashSet<String> newVisited = new HashSet<String>(node.visited);
                    newHs.put(c, s);
                    newVisited.add(s);
                    MyNode newNode = new MyNode(node.p + 1, i, newHs, newVisited  );
                    stack.push(newNode);
                }
            }
        }
        
        return false;
    }
}
