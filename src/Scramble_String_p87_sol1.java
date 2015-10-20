/*
Scramble String

Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
We say that "rgtae" is a scrambled string of "great".

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
*/

/**
 * 
 * DFS + pruning!
 * 
 * This approach is very similar to the brute-force way, but we use pruning to stop early and save a lot of time
 * The tricky part is to come up with the pruning, also be careful about indexing when splitting string!
 * 
 * First of all, lets clarify the problem. We can treat the input string as a tree. we are able to swap nodes inside the tree.
 * But we can only swap nodes in same tree. So Scramble String does not equal to Permutation String
 * Therefore if we correctly split the input, the left and right parts should have same char appearance as their corresponding parts
 * in scramble string. We can use this property to quick identify if two inputs are possible scramble pair. This is the MOST IMPORTANT
 * key in this solution. We can reduce time a lot based on this pruning
 * 
 * The basic idea of this solution is to use recursive way try all possible split spot to find the corresponding part. As discussed above
 * corresponding part at least should have same length and same appearance of chars. If we correctly split the strings, we can finally
 * find each pair of scramble part. 
 * 
 * To be more specific, in each recursion, we will firstly do pre-check to make sure two inputs are possible scramble pair. Then we will
 * try all possible split spot (or we can say left part length) on the inputs to find match part. Left part length can range from 1 to 
 * len - 1. We can have two cases in match searching. 1) they are exactly match, no swap is needed 2) if 1) is not the case, we will 
 * look one string forward and look another string backward, like gr and rg to see if they match. If 2) also failed, we now current inputs
 * are not correspnding part, we will return false.
 * 
 * 
 * Remark:
 * 1) By understanding the algorithm used in this solution, we can easily understand the dp approach.
 * 
 * Sol1 is recursion with pruning
 * Sol2 is 3D-dp solution. 
 * Time complexity for both sol should be O(n^4) but with pruning, the real result of sol1 should be much faster
 * 
 * Sol2 will return the result in the last moment while sol1 will return the result when we found match, so sol1 is faster
 * Also Sol2 is not so straightforward as sol1
 * So sol1 is recommended
 * 
 * @author hpPlayer
 * @date Oct 19, 2015 11:39:52 PM
 */
public class Scramble_String_p87_sol1 {
    public boolean isScramble(String s1, String s2) {
        
        //boundary case check
        if(s1.length() != s2.length()) return false;
        if(s1.equals(s2)) return true;
        
        //they key of this solution is this pruning, if we don't have same appearance of chars, we will return false
        
        int[] chars = new int[256];
        for(int i = 0; i < s1.length(); i++){
            chars[s1.charAt(i)] ++;
            chars[s2.charAt(i)] --;
        }
        
        for(int i = 0; i < 256; i++){
            if(chars[i] != 0) return false;
        }
        
        //now we have make sure s1 and s2 at least have same appearance of chars
        //let's try all possible split spot to match them!
        
        int len = s1.length();
        //we will expand the left part and move the split spot rightward until we have only one char in right part
        for(int i = 1; i < len; i++){
            String s1_left = s1.substring(0, i), s1_right = s1.substring(i);
            String s2_left = s2.substring(0, i), s2_right = s2.substring(i);
            
            //case 1, no swap at all
            if( isScramble(s1_left, s2_left) && isScramble(s1_right,s2_right) ) return true;
            
            //case 2 swap case. In such case, we will look at s2 backward
            //we will readjust the split spot on s2, to make the length match
            s2_right = s2.substring(len - i);
            s2_left = s2.substring(0, len - i);
            if( isScramble(s1_left, s2_right) && isScramble(s1_right,s2_left) ) return true;
        }
        
        //we have tried all possible split spot, still no luck, return false
        
        return false;
    }
}
