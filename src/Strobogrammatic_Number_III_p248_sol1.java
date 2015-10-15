/*
Strobogrammatic Number III

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

For example,
Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.

Note:
Because the range might be a large number, the low and high numbers are represented as string.
*/

/**
 * I couldn't find any good solution towards this problem.
 * The approach here is to use same technique in Strobogrammatic_Number_II_p247_sol1.
 * We will generate all valid Strobogrammatic Number from len(low) to len(high), and count the valid number
 * 
 * This time we don't need to output the string, we just need to let count ++ if current string is in range
 * Notice: string.compareTo() compares the string based on lexicological order.
 * It means if we have input a = 60, b = 100, it will still think a > b since in lexicological order, 6 is after 1.
 * So it's only meaningful to compare string with same length. 
 * Therefore in sol1, we will think in reverse order, instead of including all numbers in range, now we will filter out
 * all strings not in range. To do that, we just need to make sure current length is SAME with low or high boundary,
 * then do the comparison. If current string is too small or too large, we will filter it out. Otherwise we let count ++
 * 
 * Since the algorithm is almost same with Strobogrammatic Number II, I will not include the iterative solution.
 * If there is any better solution coming, I will update it later
 * 
 * @author hpPlayer
 * @date Oct 15, 2015 1:02:01 AM
 */

public class Strobogrammatic_Number_III_p248_sol1 {
	public static void main(String[] args){
		//System.out.println("69".compareTo("100"));
		System.out.println(new Strobogrammatic_Number_III_p248_sol1().strobogrammaticInRange("50", "100"));
	}
    public int strobogrammaticInRange(String low, String high) {
        count = 0;
        //do loop on length
        for(int i = low.length(); i <= high.length(); i++){
            n = i;//update n to avoid add heading and trailing 0s
            DFS("", "", low, high, i);
        }
        
        return count;
    }
    
    int count;
    int n;
    
    public void DFS(String left, String right, String low, String high, int k){
        if (k == 0){
            String s = left + right;
            boolean l = s.length() == low.length() && s.compareTo(low) < 0;
            boolean r = s.length() == high.length() && s.compareTo(high) > 0;
            
            //if failed at any check, skip this number
            if(!l && !r) count ++;
            return;
        }
        
        if (k == 1){
            int[] map = {0, 1, 8};
            for(int i = 0; i < 3; i++){
                String s = left + map[i] + right;
                boolean l = s.length() == low.length() && s.compareTo(low) < 0;
                boolean r = s.length() == high.length() && s.compareTo(high) > 0;
                
                //if failed at any check, skip this number
                if(!l && !r) count ++;               
            }
            return;
        }        
        
        
        if(k != n) DFS(left + 0, 0 + right, low, high, k-2);//we use 2 chars update k
        
        DFS(left + 1, 1 + right, low, high, k-2);
        DFS(left + 6, 9 + right, low, high, k-2);
        DFS(left + 9, 6 + right, low, high, k-2);
        DFS(left + 8, 8 + right, low, high, k-2);
    }
}
