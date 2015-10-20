/**
 * DP solution
 * 
 * This is the only 3D dp problen on leetCode, the tricky part is indexing and get clear with the logic.
 * But it wouldn't be hard if you get understanding with sol1
 * 
 * value in dp[i][j][k] means substring in input1 starts from i and substring in input2 from j are scramble pair, and their len
 * is k. So in this dp table we record all substring pairs with different start index and different length.
 * 
 * The basic idea is to starts from substring pair with len 1, then expand to len 2 case, len3 cases...
 * for each substring pair, the technique we used to check if they are scramble pair is same with sol1. We tried each possible 
 * split spot and considered two cases 1) swap or 2) non-swap. Finally we just need to check the cell dp[0][0][len-1], we can
 * know if two input strings are scramble pair
 * 
 * Remark:
 * 1) boolean dp table + outer loop is for len, it is very similar to problem Longest Palindromic Substring (p5)
 * 2) In this solution we build dp table with extra col or row, so the length is 0 based. Be careful about the indexing. 
 * if k and p are both 0 based string, then k-p will get the 1 based len difference, so we need -1 to convert it to 0 based if needed
 * Of if we need use 1 based index, we should + 1 to convert it as well. 
 * 
 * @author hpPlayer
 * @date Oct 20, 2015 12:46:25 AM
 */
public class Scramble_String_p87_sol2 {
    public boolean isScramble(String s1, String s2) {
        if(s1.length() != s2.length()) return false;
        if(s1.equals(s2)) return true;
        
        int len = s1.length();
        //to save space, we only create the table with s1.length, so our following length should all be 0 based!
        boolean[][][] dp = new boolean[len][len][len];
        
        //boundary case, start the substring with len 1
        for(int i = 0; i < len; i++){
            for(int j = 0; j < len; j++){
                if(s1.charAt(i) == s2.charAt(j)) dp[i][j][0] = true;
            }
        }
        
        //let's expand the string with longer length
        for(int k = 1; k < len; k++){
            //nested loop is to fill dp value for all substring with len k
            //i and j are the start of substring at s1 and s2 respectively
            for(int i = 0; i + k < len; i++){
                for(int j = 0; j + k < len; j++){
                    //try all possible split spots to see if we can make current two subtring scramble
                    for(int p = 0; p < k; p++){
                        //still two cases as sol1: 1) no swap is needed 2) swap case
                        //since length i 0 based now, we need add 1 back to convert to 1 based len
                        //or minus 1 to convert to 0 based len (k-p will make the offset 1 disappear, so -1 to add offset back)
                        //for j + k -p, offset is disappeared, so j + (k-p) can directly jump to target index
                        if((dp[i][j][p]&&dp[i+p+1][j+p+1][k-p-1]) || (dp[i][j+k-p][p] && dp[i+p+1][j][k-p-1])){
                            //any case is true, we can make current two inputs become scramble pair
                            dp[i][j][k] = true;
                            break;
                        }
                
                    }
                    
                }
            }
        }
        
        return dp[0][0][len-1];
    }
}
