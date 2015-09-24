/**
 * I would call this solution a two-pointer approach
 * 
 * The difficulty is to observe that we can start from mid when search palindrome
 * 
 * In brute force approach, we would stop at each index, treat it as start point then search palindrome from 1 - n, the validation of palindrome
 * also costs O(n), so the running time is O(n^3), which is slow.
 * This approach, instead of treat each index as start point, it actually treat each palindrome as mid point, then try to extend the palindrome
 * from mid. So we combine checking palindrome and extend len together, which make the program run faster, and the running time is O(n^2). There 
 * are two kinds of mid points, x or xx (the mid point can also be treated as the gap between xx). So we will try to extend palindrome from two 
 * possible mid points. Then update result accordingly.
 * 
 * Remark:
 * Always remember we have two kinds of mid points in palindrome. If we miss double char mid point, then we may miss the result. Like: baac,
 * if we only consider single char point, then we will never found palindrome aa if extend from mid.
 * 
 * Sol1 is two pointer solution
 * Sol2 provides a DP solution
 * 
 * @author hpPlayer
 * @date Sep 23, 2015 10:37:35 PM
 */
public class Longest_Palindromic_Substring_p5_sol1 {
    public String longestPalindrome(String s) {
        String result = "";
        for(int i = 0; i < s.length(); i++){
            //instead of treat each index as start point and check each possible len of palindrome
            //we start from mid and would stop if certain len of extend substring already not palindrome
            String s1 = extendPalin(i, i, s);//single mid point
            String s2 = extendPalin(i, i+1, s);//double mid points
            String temp = s1.length() > s2.length()? s1 : s2;
            if(temp.length() > result.length()) result = temp;
        }
        
        return result;
    }
    
    public String extendPalin(int start, int end, String s){
        //extend the palindrome
        while(start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)){
            start --;
            end ++;
        }
        //we should exclude start and end as they are not match after the loop
        return s.substring(start + 1, end);
    }
}
