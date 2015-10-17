import java.util.*;

/*
Word Search II 

* 
* Given a 2D board and a list of words from the dictionary, find all words in the board.
* 
* Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" 
* cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
* 
* For example,
*   Given words = ["oath","pea","eat","rain"] and board = 
*   
*   [
*     ['o','a','a','n'],
*     ['e','t','a','e'],
*     ['i','h','k','r'],
*     ['i','f','l','v']
*   ]
*   
* Return ["eat","oath"].
* 
* Note:
* You may assume that all inputs are consist of lowercase letters a-z.
* 
* click to show hint.
* 
* You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?
* 
* If the current candidate does not exist in all words' prefix, you could stop backtracking immediately. 
* What kind of data structure could answer such query efficiently? Does a hash table work? Why or why not? 
* How about a Trie? If you would like to learn how to implement a basic trie, please work on this problem: 
* Implement Trie (Prefix Tree) first (p208).
*/



/**
 * Trie + backtracking problem
 * 
 * The tricky part is to come up with the idea of using Trie and be careful with the corner case
 * 
 * In this problem, the given input is a list of words, if we backtrack the board without Trie, then we will check each word list every time we found a char,
 * it is definitely not what we want. So we build a Trie, the trie can tell us whether we have current char or current prefix in the word list. For this problem
 * Trie is perfect!
 * 
 * Remark:
 * 
 * 1) corner case 1, we don't want to add duplicate word into result list. Instead of using a set, we use simply reset the isEnd variable in TrieNode so,this
 * word no longer exist in our Trie
 * 2) After we found a prefix that is a word, we shouldn't stop searching further since this word may still be a part of prefix of another word
 * 3) This problem can also be solved by iteration, which can be done with Stack and inner class. But we have to create/deep copy a boolean map to avoid
 * searching visited chars, so it is very slow nor space-wise. But at least we can still do that in iterative way
 * 
 * @author hpPlayer
 * @date Oct 16, 2015 4:40:37 PM
 */

public class Word_Search_II_p212_sol1 {
    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for(String word : words){
            trie.addWord(word);
        }    
        List<String> result = new ArrayList<String>();
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(trie.hasPrefix(board[i][j] + "")){
                    char c = board[i][j];
                    board[i][j] = '*';
                    DFS(board, i, j, trie, c + "", result);
                    board[i][j] = c;
                }
            }
        }
        
        return result;
    }
    
    public void DFS(char[][] board, int x, int y, Trie trie, String temp, List<String> result){
        if(trie.hasWord(temp)){
            result.add(temp);
            //we can't stop here since current word may be a part of prefix of a longer word
            //return;
        }
        
        int[] xOffset = {0, 0, 1, -1};
        int[] yOffset = {1, -1, 0, 0};
        
        for(int i = 0; i < 4; i++){
            int newX = x + xOffset[i];
            int newY = y + yOffset[i];
            if(newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length && board[newX][newY] != '*'){
                if(trie.hasPrefix(temp + board[newX][newY])){
                    char c = board[newX][newY];
                    board[newX][newY] = '*';
                    DFS(board, newX, newY, trie, temp + c, result);
                    board[newX][newY] = c;
                }
            }
            
        }
    }
    
    private class TrieNode{
        boolean isEnd = false;
        HashMap<Character, TrieNode> hs;
        private TrieNode(){
            isEnd = false;
            hs = new HashMap<Character, TrieNode>();
        }
    }
    
    private class Trie{
        TrieNode root;
        
        private Trie(){
            root = new TrieNode();
        }
        
        private void addWord(String s){
            TrieNode curr = root;
            for(int i = 0; i < s.length(); i++){
                char c = s.charAt(i);
                if(!curr.hs.containsKey(c)){
                    curr.hs.put(c, new TrieNode());
                }
                    curr = curr.hs.get(c);                
            }
            curr.isEnd = true;
        }
        
        private boolean hasPrefix(String s){
            TrieNode curr = root;
            for(int i = 0; i < s.length(); i++){
                char c = s.charAt(i);
                if(!curr.hs.containsKey(c)) return false;
                curr = curr.hs.get(c);
            }
            return true;
        }
        
         private boolean hasWord(String s){
            TrieNode curr = root;
            for(int i = 0; i < s.length(); i++){
                char c = s.charAt(i);
                if(!curr.hs.containsKey(c)) return false;
                curr = curr.hs.get(c);
            }
            
            boolean result = curr.isEnd;
            curr.isEnd = false;
            return result;
        }       
        
    }
}
