import java.util.*;

/*
Strobogrammatic Number II

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

For example,
Given n = 2, return ["11","69","88","96"].

Hint:

Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.
*/

/**
 * The tricky part is to handle corner case like 0. Also be careful about odd length and even length.
 * 
 * We will append 2 char (a pair) in each recursion. If we got even length, our recursion will end with 0 chars remaining
 * If we got odd length, our recursion will end with 1 char remaining. For 1 char, we can only choose from 0, 1, 8.
 * To handle 0 case properly, here we use a counter k to mark which loop we are at. The counter represents how many chars left
 * that we need to append. If k== n, i.e. the initial case, we could not append 0 around string, otherwise we will have an invalid
 * number starts with 0, so we skip 0 in such case. Otherwise we will treat 0 as other Strobogrammatic num. 
 * 
 * Here we build the string from two sides. We append new char after left part and before right part, so be careful with the order.
 * 
 * Remark:
 * 
 * This problem is similar to problem Palindrome Permutation II (p267), where we split the string into three parts, mid, left and right
 * parts. Each recursion, we will append a new char around the mid strings to form a new Palindrome, meanwhile reduce the count of char 
 * by 2.
 * 
 * Sol1 is recursive solution
 * Sol2 is iterative solution with same algorithm. But sol2 is slower than sol1, maybe because the operation on deque is expensive
 * 
 * @author hpPlayer
 * @date Oct 14, 2015 11:32:06 PM
 */
public class Strobogrammatic_Number_II_p247_sol1 {
	public static void main(String[] args){
		System.out.println(new Strobogrammatic_Number_II_p247_sol1().findStrobogrammatic(3));
	}
    public List<String> findStrobogrammatic(int n) {
        this.n = n;//use n to avoid append 0 in the first recursion
        List<String> result = new ArrayList<String>();
        DFS(result, "", "", n);
        return result;
    }
    
    int n;
    public void DFS(List<String> list, String left, String right, int k){
        //we try to fill the list from two-side and converage at mid
        if(k == 0){
            //we have even length, so the remaining length is 0
            list.add(left + right);
            return;
        }
        
        if(k == 1){
            //we have odd length, so we need add a single mid, which could only be 0, 1, 8 
            list.add(left + 0 + right);
            list.add(left + 1 + right);
            list.add(left + 8 + right);
        
            return;
        }
        
        //otherwise we will append char pair in left and right part
        
        //0 is boundary case, we couldn't add 0 at the right and left bound, which would make num be 0...0
        //and we don't have a valid num has len > 1, and starts with 0
        if(k != n) DFS(list, left + 0, 0 + right, k-2);
        
        //for other pairs, we can add it freely
        
        DFS(list, left + 1, 1 + right, k - 2);
        DFS(list, left + 6, 9 + right, k - 2);
        DFS(list, left + 9, 6 + right, k - 2);
        DFS(list, left + 8, 8 + right, k - 2);
    }
}
