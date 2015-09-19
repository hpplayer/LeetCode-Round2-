/**
 * A tire problem. Differed from original Trie problem, here we need modify the search() so we can search word with "." quickly.
 * We will still scan all children when meet ".", but when we found a non-null child, we will start new search with this child and reaming word,
 * instead of creating a new word and start search over from root. 
 * Some boundary cases that needs attention:
 * 1) Each time we found "." we will start new search on remaining word, but we may already reach the end of word, in such case, we will check
 * current Node to see if it is an end Node. Similarly, if we reach the end of Trie, we will check if we have reach the end of word as well.
 * 2) If none of child will return true search value, then we can safely say this "." does not help use find the target word, then we can simply
 * return false
 * 
 * @author hpPlayer
 * @date Sep 19, 2015 1:21:13 AM
 */
public class Add_and_Search_Word_Data_structure_design_p211_sol1 {
	public class WordDictionary {
	    TrieNode root = new TrieNode();
	    public class TrieNode{
	        boolean isWord;
	        TrieNode children[] = new TrieNode[26];
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
	        return DFS(root, word);
	    }
	    
	    //To speed up search, we must have a helper() that can do search on it
	    
	    public boolean DFS(TrieNode head, String word){
	        //if we have reach the end of word
	        if(word.length() == 0 && head != null) return head.isWord;
	        
	        //if we have not reach the end of word, but TrieNode already null
	        if(word.length() == 0 || head == null) return false;
	        
	        TrieNode curr = head;//start with current input, otherwise we are not using the adv of DFS(node, word)
	        for(int i = 0; i < word.length(); i++){
	            char c = word.charAt(i);
	            if(c == '.'){
	                for(TrieNode node : curr.children){
	                    if(node != null && DFS(node, word.substring(i+1))) return true; 
	                }
	                return false;//if none of curr.child can retrieve word, then return false immediately
	            }else{
	                if(curr.children[c - 'a'] == null) return false;
	                curr = curr.children[c - 'a'];
	            }
	        }
	        
	        return curr.isWord;
	    }
	}
	
	// Your WordDictionary object will be instantiated and called as such:
	// WordDictionary wordDictionary = new WordDictionary();
	// wordDictionary.addWord("word");
	// wordDictionary.search("pattern");
}
