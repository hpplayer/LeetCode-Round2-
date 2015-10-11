import java.util.*;

/**
 * DFS / topological solution
 * 
 * Sol2 also needs to split the problem into two parts. 1) create prerequisites table 2) topological search
 * Here we use inner class to do the topological search. Each inner class contains its prerequisites.
 * So we just needs a node list that relates each char to its corresponding Node
 * 
 * We firstly use similar idea to fill the prerequisites table which is in each node.
 * Then we visit the each node to do the DFS topological visit. We will compose the string backward, so the root node will be 
 * in front. Assume current node b has prerequisites a, and decides c. after the topological sort, we can put a before b, while
 * still have c after b. So by doing DFS, we can put previous node in correct order while won't affect later nodes that depends on
 * current node
 * 
 * Remark:
 * 1. Here we split the prerequisites table into small table specific to each node. So the Node list is very important and necessary
 * otherwise we don't know where to start our DFS search, or we may not include all nodes
 * 2. To make each node append chars to same string, we will use stringBuilder
 * 3. Here we still use "cleanNode" to skip each visited clean Node to avoid duplicate path
 * 4. Since we don't the total num of char, we have to use HashMap to record each char, instead of a fixed length char
 * 
 * @author hpPlayer
 * @date Oct 11, 2015 4:48:34 PM
 */
public class Alien_Dictionary_p269_sol2 {
	public static void main(String[] args){
		System.out.println(new Alien_Dictionary_p269_sol2().alienOrder(new String[]{"wrt","wrf","er","ett","rftt"}));
	}
    private class Node{
        char c;
        boolean cleanNode;//if we have visited this node before and it is clean
        boolean visited;//if we have visited this node before in current search
        //we don't want add same relations twice, so we use set, otherwise, if we got case like zb zc, ab ac
        //we will add two c nodes after b
        Set<Node> nodes;
        
        private Node(char c){
            cleanNode = false;
            visited = false;
            nodes = new HashSet<Node>();
            this.c = c;
        }
        
        private void add(Node node){
            nodes.add(node);
        }
        
        private boolean hasCycle(StringBuilder sb){
            //we need put the root node first. To be convenient we want start DFS from any node, not the node
            //without prerequisites or with most prerequisites. So we will build the string backward.
            //We will start build the string after we reach the bottom (the root node). Therefore, in DFS
            //we should use "depends on" relation, where we put root node to the bottom
            
            //cleanNode check first, so we can set and reset "visited" freely
            if(cleanNode) return false;
            if(visited) return true;
            visited = true;
            
            for(Node node : nodes){
                if(node.hasCycle(sb)) return true;
            }
            
            cleanNode = true;
            visited = false;
            sb.append(c);
            return false;
        }
    }
    
    public String alienOrder(String[] words) {
        //** part one, create prerequisites table **
        
    	//since now we split the prerequisites table into small table specific to each Node,
    	//we only need a big Node table, which relates each char with its corresponding Node
    	//With this table, we can include all chars while still holds the prerequisites
        HashMap<Character, Node> nodes = new HashMap<Character, Node>();
        
        String prev = "";
        
        for(String word : words){
            for(char c : word.toCharArray()){
                //for node list, it should include all chars in dict
                if(!nodes.containsKey(c)) nodes.put(c, new Node(c));
            }
            
            for(int i = 0; i < Math.min(prev.length(), word.length()); i++){
                char a = prev.charAt(i), b = word.charAt(i);
                if(a != b){
                    //In DFS we want visit the root node last, so we should use "depend on" relations
                    //Now a should before b, i.e. b depends on a, so we insert a into b's prerequisites
                    nodes.get(b).add(nodes.get(a));
                    break;
                }
            }
            
            prev = word;//update prev
        }
        
        //** part two, use DFS to topologically visit the graph **
        
        StringBuilder sb = new StringBuilder();
        
        for(Character c : nodes.keySet()){
            if(nodes.get(c).hasCycle(sb)){
                //if we got cycle, return empty string instead
                return "";
            }    
        }
        
        return sb.toString();
    }
}
