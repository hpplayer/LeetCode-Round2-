import java.util.*;

/*
Repeated DNA Sequences

All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA,
it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

For example,

Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].

*/

/**
 * HashMap + bit manipulation
 * 
 * The tricky part is to avoid MLE. 
 * 
 * To solve this problem, we need remember two things. 1) Replace each char by 2 bit int, so we can finally reduce a string to an integer.
 * Originally each char takes 2 bytes and now each char takes 2 bits. So it is a great compression 2) don't add a sequence more than once.
 * One sequence may appear many times, we just need to add into result in its second appearance.
 * 
 * It seems now Oj has decreased the memory limit a lot, my previous AC solution was not accepted anymore. But I don't think such strict
 * limit is practical
 * 
 * To reduce the memory requirement, now I will repeatedly use a char array to query 2 bits representation of each char instead of an helper
 * function in my old solution. Also we will generate the code on fly to further reduce unnecessary space. Besides, I found declare HashSet
 * as Set will further reduce unnecessary space, though I don't think it is a practical change. Anyway below is the newest AC solution, which
 * uses similar algorithm as my old solution, but made some changes to make it pass current OJ
 * 
 * Remark:
 * Here, to avoid duplicate insertion, we will scan result list each time before we add a string. It will cost more time, but save space. So it is
 * a trade off between time and space
 * 
 * @author hpPlayer
 * @date Oct 18, 2015 4:44:19 PM
 */

public class Repeated_DNA_Sequences_p187_sol1 {
    public List<String> findRepeatedDnaSequences(String s) {
        Set<Integer> hs = new HashSet<Integer>();
        List<String> result = new ArrayList<String>();
        
        //get code query table
        int[] codes = new int[26];
        
        //assign values, "-'A'" is to help get correct index with 'A' based
        codes['A' - 'A'] = 0;
        codes['T' - 'A'] = 1;
        codes['C' - 'A'] = 2;
        codes['G' - 'A'] = 3;
        
        for(int i =0 ; i + 10 <= s.length(); i++){
            int code = 0;
            //get code of current sequence
            //get char on s directly, to avoid extra space of declearing an extra substring
            for(int j = i; j < i + 10; j++){
            	//left shift code to append 2 new bits after generated code
                code <<= 2;
                //set 2 new bits with current char
                code |= codes[s.charAt(j) - 'A'];
            }
            
            if(!hs.contains(code)){
                //first time see this sequence, add to hs
                hs.add(code);
            }else{
                //to avoid insert duplicate sequence to result, we will check each string in result
                //Although we can use an extra HashSet to record all previously added sequence,
                //we will not do that due to space saving
                if(!result.contains(s.substring(i, i + 10))){
                    result.add(s.substring(i, i + 10));
                }
            }
        }
        
        return result;
    }
}
