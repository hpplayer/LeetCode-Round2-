import java.util.*;
/*
Permutation Sequence

The set [1,2,3,бн,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note: Given n will be between 1 and 9 inclusive.
*/

/**
 * Math solution and I need more practices for this problem.
 * 
 * There are mainly two tricky parts 1) get correct number in each digit 2) indexing and boundary case
 * 
 * We observe, for each permutation of length n, after we fixed the first digit, then we totally got (n-1)! permutations 
 * start with this fixed digit. So, we can use k/(n-1)! to get the number of first digit. Then use k%(n-1)! to get the index
 * in permutations start with this digit. To handle indexing properly, we will convert k to k - 1. Also, after we used a 
 * number in permutation, we have to left shift all numbers after it. Example: if we fixed first number to be 3, and we have 
 * 4 digits, then 3th number in this sequence should be 3124 (assume we only have number 1 - 9 and don't allow duplicates).
 * In this solution, left shift is represented by removing cell at target index.
 * 
 * @author hpPlayer
 * @date Oct 5, 2015 10:26:23 PM
 */

public class Permutation_Sequence_p60_sol1 {
	public static void main(String[] args){
		System.out.println(new Permutation_Sequence_p60_sol1().getPermutation(3, 2));
	}
    public String getPermutation(int n, int k) {
        //create the first permutation with len n
        List<Integer> seq = new LinkedList<Integer>();
        for(int i = 1; i <= n; i++) seq.add(i);
        
        //get 0 to n-1 len's factorial, so after first digit is fixed, we know how many numbers it carry
        //we will include 0 len as well, so we will cover single digit case, i.e. olny one number it carry
        int[] dp = new int[n];
        dp[0] = 1;// 0! =1, which means single number carry one number
        for(int i = 1; i < dp.length; i++) dp[i] = dp[i-1] *i;
        
        //we need convert k to 0 based so we can get the correct number in seq
        //ex: n = 1, k = 1, seq contains [1], and we need get first number in seq, which is 0
        //so k must be k -1, IMPORTANT !!!!!
        k --;
            
        StringBuilder sb = new StringBuilder();
        //get number for each digit in final permutation
        for(int i = n; i >= 1; i--){
            //after first digit is fixed, say it is 1 we got (n-1)! numbers with it
            //so to get kth number, we need k/(n-1)! number in first digit
            int index = k / dp[i-1];
            k %= dp[i-1];//update k to remaining index
            
            //insert digit forward
            sb.append(seq.get(index));
            
            //since we will not have duplicates in permutation, we wil remove curretn digit out
            //and this is also correct for math, since after remove i, i + 1 becomes the smallest 
            //number after i - 1, so if later we need the ith number again, it should use th new ith
            //number which is old (i+1)th
            seq.remove(index);
        }
        
        return sb.toString();
    }
}
