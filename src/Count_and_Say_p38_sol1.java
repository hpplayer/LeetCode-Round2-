/**
 * String problem.
 * 
 * I would say this problem is actually a brute-force problem without trick
 * We build string for each ith sequence, then pass it to next recursion for further count-and-say
 * 
 * @author hpPlayer
 * @date Sep 20, 2015 11:22:17 PM
 */
public class Count_and_Say_p38_sol1 {
	public static void main(String[] args){
		System.out.println(new Count_and_Say_p38_sol1().countAndSay(100));
	}
    public String countAndSay(int n) {
        return Helper(n, "1");
    }
    
    public String Helper(int n, String s){
        if(n == 1) return s;//found our target
        
        StringBuilder sb = new StringBuilder();
        
        int count = 1;
        char prev = s.charAt(0);
        for(int i = 1; i < s.length(); i++){
            if(s.charAt(i) == prev){
                count ++;
            }else{
                //append prev with its count
                sb.append(count);
                sb.append(prev);
                count = 1;//reset count
            }
            
            prev = s.charAt(i);
        }
        
        //insert last pair
        sb.append(count);
        sb.append(prev);
        //System.out.println(sb.toString());
        return Helper(n-1, sb.toString());
    }
}
