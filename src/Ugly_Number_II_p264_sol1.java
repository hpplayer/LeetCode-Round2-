/*
Ugly Number II

Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

Note that 1 is typically treated as an ugly number.

Hint:

1) The naive approach is to call isUgly for every number until you reach the nth one. Most numbers are not ugly.
Try to focus your effort on generating only the ugly ones.
2) An ugly number must be multiplied by either 2, 3, or 5 from a smaller ugly number.
3) The key is how to maintain the order of the ugly numbers. Try a similar approach of merging from three sorted lists: L1, L2, and L3.
4) Assume you have Uk, the kth ugly number. Then Uk+1 must be Min(L1 * 2, L2 * 3, L3 * 5).

*/

/**
 * dp solution
 * 
 * The tricky part is to come up with the idea of using pointers to track the used ugly number for each prime
 * 
 * The basic idea is not hard. We have three lists, each list for one prime factor. To pick the next ugly number,
 * we will pick the smallest head number on three lists. After removing this head number, we will generate the 
 * next smallest number in this list. How? The prime factor is fixed, and we don't want generate past ugly number,
 * so we will keep a pointer in the series of ugly number. The pointer will points to the next smallest and unused 
 * ugly number in the ugly series. To be more specific, we use three pointers in ugly series to indicate which one 
 * has been used in those lists. We also have three numbers that represent the head numbers of those lists. Our loop
 * will search the next ugly number, which is the smallest number among three head numbers. For list that contains 
 * the head number, we will generate its next head number based on the prime factor and the unused smallest number ugly
 * number. Notice: we may have multiple head numbers have same min value. For such case, we will remove them all, and 
 * generate new numbers for all of them.
 * 
 * Time complexity is O(n)
 * 
 * Sol1 is dp approach
 * Sol2 is minHeap approach
 * 
 * Sol2 is slow and causing overflow problem, so sol1 is better
 * 
 * @author hpPlayer
 * @date Sep 30, 2015 3:30:53 PM
 */
public class Ugly_Number_II_p264_sol1 {
    public int nthUglyNumber(int n) {
        if(n <= 0) return 0;//boundary case
        int[] dp = new int[n];//dp table
        dp[0] = 1;//inital case
        
        int num2 = 2, num3 = 3, num5 = 5;//current min value in 3 lists
        int index2 = 1, index3 = 1, index5 = 1;//index of current min candidate in table
    
        for(int i = 1; i < n; i++){
            //next ugly number is the smallest number
            int next = Math.min(num2, Math.min(num3, num5));
            dp[i] = next;
            
            //pop min number, and generate a new min number
            if(next == num2){
                num2 =  2 * dp[index2++];
            }

            if(next == num3){
                num3 =  3 * dp[index3++];
            }
            
            if(next == num5){
                num5 =  5 * dp[index5++];
            }            
        }    
        
        return dp[n-1];
    }
}
