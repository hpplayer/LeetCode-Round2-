import java.util.*;

/**
 * Iterative version of sol1.
 * I just translate recursive sol1 to iterative solution.
 * 
 * To use the same algorithm I have to use two extra deque to contain left and right parts. For each iteration, we will append 
 * one char after the left part and one char before the right part. We also use a parameter k to track the number of iteration we have
 * we won't append 0 around the string in the first iteration. If we got 1 char remaining, it means we reach the mid, we can only 
 * add 0, 1, 8. If we got 0 char remaining, it still means we reach the mid but the input string is even length, we just combine
 * left and right part together
 * 
 * @author hpPlayer
 * @date Oct 15, 2015 12:03:58 AM
 */
public class Strobogrammatic_Number_II_p247_sol2 {
	public static void main(String[] args){
		System.out.println(new Strobogrammatic_Number_II_p247_sol2().findStrobogrammatic(4));
	}
    public List<String> findStrobogrammatic(int n) {
        Deque<String> left = new LinkedList<String>();
        left.add("");
        Deque<String> right = new LinkedList<String>();
        right.add("");
        
        List<String> result = new ArrayList<String>();
        int k = n;
        
        //include all possible Strobogrammatic Number
        HashMap<Character, Character> hs = new HashMap<Character, Character>(){
            {put('1', '1');  put('6', '9');  put('9', '6');  put('8', '8'); put('0', '0');}
        };
        while(k >= 0){
            if( k == 1){
                //single mid case(odd length), we can only have 0, 1, 8 in the mid
                while(!left.isEmpty()){
                    String l = left.pollLast();
                    String r = right.pollLast();
                    result.add(l + 0 + r);
                    result.add(l + 1 + r);
                    result.add(l + 8 + r);                   
                }
            }else if(k == 0){
                while(!left.isEmpty()){
                    //0 mid case (even length), just combine left and right to result
                    result.add( left.pollLast() + right.pollLast());
                }
            }else{
                //general case, we need to append Strobogrammatic Numbers from two sides to mid
                int size = left.size();
                //we will append each char in hs to left and right part
                for(int i = 0; i < size; i++){
                    String l = left.pollLast();
                    String r = right.pollLast(); 
                    for(char key : hs.keySet()){
                        if(key == '0' && k == n) continue;//don't append 0 in the first iteration
                        left.offerFirst(l + key); //add new char after left part
                        right.offerFirst(hs.get(key) + r);//add new char before right part
                    }
                }
            }
            k -= 2;
        }
        
        return result;
    }
}
