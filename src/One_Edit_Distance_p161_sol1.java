/*
One Edit Distance

Given two strings S and T, determine if they are both one edit distance apart.
*/

/**
 * Two pointer + string problem
 * 
 * The tricky part is to get the idea of reduce 3 operations to 2 operations and be clear about the two cases 1) same length 2) different length
 * 
 * First of all, the problem requires two string have exactly one edit distance. So if we got two strings have same length, then they must
 * have exactly one pair of different chars, while if we get two strings with different length, then after we insert a virtual char in the shorter
 * string, we must have same remaining substrings.
 * 
 * In sol1, we use two pointers to keep track of char in two strings. If we found an unmatched case, then we can either use replace if we have
 * same length of input strings of we can use insert where we got different input length. We require after such change, there is no more unmatched
 * cases in the following comparison. The scan will end when we reach the end of shorter length if we have one. We will return false in the loop
 * if we found there are more than one mismatch.
 * 
 * After scan is done, we will have two cases (1 mismatch and 0 mismatch):
 * 1) we have found 1 mismatch in the string. If we have same length inputs, then 1 mismatch is the
 * total match we have, if we have different length inputs, since we require they differed exactly by 1, by adding one extra char, we could
 * still match the rest substrings, so the total mismatch is still 1
 * 
 * 2) we found 0 mismatch in the string. It may because input s and t are exactly same inputs or may because s is the prefix of t. In this problem
 * we only allow case 2 to be valid. So in 0 mismatch, we will return true if we found s is prefix of t
 * 
 * Remark:
 * we can either use two pointers to manually scan rest array, or we can use built-in string's equal() to let Java help us scan rest array.
 * sol1  is the manual version
 * sol2  is the auto-scan version
 * 
 * Sol1 and sol2 are almost same, but sol2 use built-in api, so it is shorter
 * @author hpPlayer
 * @date Oct 17, 2015 11:43:51 PM
 */

public class One_Edit_Distance_p161_sol1 {
	public static void main(String[] args){
		System.out.println( new One_Edit_Distance_p161_sol1().isOneEditDistance("ab", "acd"));
	}
    public boolean isOneEditDistance(String s, String t) {
        //manual compare version
    	
        int m = s.length();
        int n = t.length();
        
        //to avoid the deletion operation, we will always assume s is the smaller one, if not we swap the order of
        //input to make them valid
        if(m > n) return isOneEditDistance(t, s);
        
        //if longer string(t) is longer than 1 unit than shorter string(s), then we return false directly
        if(n - m > 1) return false;
        
        //if we are comparing the string manually, we need a counter to record how many mismatch we have had
        int count = 0;
        
        //i is pointer in s while j is pointer in t
        for(int i = 0, j = 0; i < m; i++, j++){
            if(s.charAt(i) != t.charAt(j)){
                //if they have different length, we will insert a "virtual" char in s,
                //and let same char in s to compare next char in t
                if(n != m) i --;//keep i stay still
                count ++;//record one mismatch
            }
            
            //if we got 2 mismatches, return false
            if(count >= 2) return false;
        }
        
        //we have finished the scan of shorter string
        //we require count = 1 for general case (we have mismatch inside s), but we may have count = 0 in boundary case
        //In boundary case, where s matches prefix of t, we require n and m differed exactly by 1
        return count == 1 || n - m == 1;
    }
}
