import java.util.Stack;
/**
 * Iterative version of sol1, in case we need provide an iterative solution.
 * In recursive version, we have a helper() that can receive a Node and a new word, so we can start a new search there.
 * To achieve the same effect, here we use a inner class Node, which contains a node and the start index of new word.
 * Other details are very similar to recursive solution
 * 
 * @author hpPlayer
 * @date Sep 19, 2015 2:08:32 AM
 */

public class Add_and_Search_Word_Data_structure_design_p211_sol2 {
	public class WordDictionary {
	    TrieNode root = new TrieNode();
	    
	    public class TrieNode{
	        boolean isWord;
	        TrieNode children[] = new TrieNode[26];
	    }
	    
	    public class Node{
	        int index;
	        TrieNode node;
	        public Node(TrieNode node, int index){
	            this.index = index;
	            this.node = node;
	        }
	    }
	    
	    // Adds a word into the data structure.
	    public void addWord(String word) {
	        TrieNode curr = root;
	        for(int i = 0; i < word.length(); i++){
	            char c = word.charAt(i);
	            if(curr.children[c - 'a'] == null) curr.children[c - 'a'] = new TrieNode();
	            curr = curr.children[c - 'a'];
	        }
	        curr.isWord = true;
	    }

	    // Returns if the word is in the data structure. A word could
	    // contain the dot character '.' to represent any one letter.
	    public boolean search(String word) {
	        Stack<Node> stack = new Stack<Node>();
	        Node node = new Node(root, -1);
	        stack.push(node);
	        
	        while(!stack.isEmpty()){
	            Node temp = stack.pop();
	            
	            if(temp.index == word.length() -1 && temp.node.isWord){
	                return true;
	            }
	            
	            //add new node to stack only if new index < word.length
	            if(temp.index + 1< word.length()){
	                char c = word.charAt(temp.index + 1);
	                if(c == '.'){
	                    for(TrieNode child : temp.node.children){
	                        if(child != null){
	                            Node newNode = new Node(child, temp.index + 1);
	                            stack.push(newNode);
	                        }
	                    }
	                }else{
	                    if(temp.node.children[c - 'a'] != null){
	                        stack.push(new Node(temp.node.children[c - 'a'], temp.index + 1));
	                    }
	                }
	            }
	            
	        }
	        
	        return false;
	        
	    }
	}

	// Your WordDictionary object will be instantiated and called as such:
	// WordDictionary wordDictionary = new WordDictionary();
	// wordDictionary.addWord("word");
	// wordDictionary.search("pattern");
}
