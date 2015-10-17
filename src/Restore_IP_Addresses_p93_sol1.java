import java.util.*;

/**
 * 
 * Remark:
 * This problem is similar to problem decode ways (p91)
 * 
 * @author hpPlayer
 * @date Oct 17, 2015 1:43:10 AM
 */
public class Restore_IP_Addresses_p93_sol1 {
	public static void main(String[] args){
		String s = "0000";
		System.out.println(new Restore_IP_Addresses_p93_sol1().restoreIpAddresses(s));
	}
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<String>();
        DFS(result, "", s, 0);
        return result;
    }
    
    public void DFS(List<String> result, String temp, String s, int seg){ 
        if(s.length() == 0 && seg == 4){
            result.add(temp);
            return;
        }
        if(seg > 3) return;
        
        for(int i = 1; i <= 3 && i <= s.length(); i++ ){
            String curr = s.substring(0, i);
            if(s.charAt(0) == '0' && i > 1) return;
            if(validIP(curr)){  
                if(seg > 0) curr = temp + '.' + curr;
                if(seg == 0) curr = temp + curr;
                //System.out.println(curr);
                DFS(result, curr, s.substring(i), seg + 1);
            }
           
        }
    }
    public boolean validIP(String s){
        if(s.length() == 0 || s.length() > 3) return false;
        int val = Integer.valueOf(s);
        return val < 256;
    }
}
