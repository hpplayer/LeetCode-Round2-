/**
 * Backtracking problem.
 * 
 * The difficulty here is to come up with the idea of using pointers to indicate the index that we can go back if match failed
 * 
 * We call it backtracking because we will try to match * with chars in s, if match failed, we will go back and start a new matching
 * We keep two pointers, one tells us where we can go back in p, another one tells where we can go back in s.
 * if current chars match, then we simply let two pointers continue
 * if current char in P is *, then we will record the indexes and start trial. We will firstly try to use 0 *, i.e. we match 
 * next char in p with current char in s.
 * if current pair does not match, we will check if we can go back, if no, return false immediately. Otherwise, we go back
 * and use an extra *,  which is reflected by matching next char in p with next char in s, if failed then next char in p with
 * next next char in S. i.e. we can use * to match chars in s, and let next char in p to match following chars in s
 * Repeat above step, until pointer in S reach end
 * 
 * Finally, we will deal with the trailing *.
 * 
 * After skip all trailing *, we will compare index of pointer in P with p.length, if same return true, otherwise return false
 * 
 * 
 * @author hpPlayer
 * @date Sep 26, 2015 11:04:39 PM
 */
public class Wildcard_Matching_p44_sol2 {
    public boolean isMatch(String s, String p) {
        int indexInS = 0;//pointer in S
        int indexInP = 0;//pointer in P
        
        int backupS = -1;
        int backupP = -1;
        
        //Because our loop is based on pointer in s, we have to check the boundary of pointer in p inside the loop
        while(indexInS < s.length()){
            if(indexInP < p.length() && (p.charAt(indexInP) == '?' || p.charAt(indexInP) == s.charAt(indexInS))){
                 //found match, let pointer move on
                indexInS ++;
                indexInP ++;
            }else if(indexInP < p.length() && p.charAt(indexInP) == '*'){
                backupS = indexInS;
                //if we need use backupS, then our next match pair will be next char in P vs next char in S
                //we will try to use an extra * match with char in s in each trial
                backupP = indexInP + 1;
                
                 //first case, we don't use *, so we let next char in p match current char in s
                indexInP ++;
            }else{
                //if no backup
                if(backupP == -1){
                    return false;
                }
                
                //like I said above, if match fail, the next pair will be using one * match "current" char in S
                //so we will match backupP + 1 with backupS + 1
                //for used '*', we will treat it same with matched char, and skip them
                //since our match starts with 0 used of *, 1 use of *, index of matched char in s should be ascending
                
                indexInS = ++backupS;
                indexInP= backupP;
            }
        }
        
        //skip trailing *
        while(indexInP < p.length() && p.charAt(indexInP) == '*') indexInP++;
        
        return indexInP == p.length();
    }
}
