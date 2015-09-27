/**
 * Brainstorming problem
 * 
 * The tricky part is thinking backward instead forward
 * 
 * We can just check char backward, firstly skip all spaces, then check then length of last word
 * Simple approach, but need to think about visiting string backward
 * 
 * @author hpPlayer
 * @date Sep 26, 2015 4:27:04 PM
 */
public class Length_of_Last_Word_p58_sol1 {
    public int lengthOfLastWord(String s) {
        //pointer in string s
        int i = s.length()-1;
        
        //skip all trailing spaces
        while(i >= 0 && s.charAt(i) == ' '){
            i --;
        }
        
        int len = 0;
        //detect the length of last word
        while(i >= 0 && s.charAt(i) != ' '){
            len ++;
            i --;
        }
        
        return len;
    }
}
