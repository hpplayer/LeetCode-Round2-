import java.util.*;

/**
 * Iterative solution of sol1.
 * 
 * We still use inner class and stack to replace the recursion
 * Since each inner node only contains input s and temp string, it would not be space-consuming.
 * So it is still meaningful to write this iteraitve solution
 * 
 * @author hpPlayer
 * @date Oct 17, 2015 9:41:52 PM
 */

public class Restore_IP_Addresses_p93_sol2 {
	public static void main(String[] args){
		String s = "1111";
		System.out.println(new Restore_IP_Addresses_p93_sol2().restoreIpAddresses(s));
	}
    public class MyNode{
        int level;
        String s;
        String temp;
        public MyNode(int level, String s, String temp){
            this.level = level;
            this.s = s;
            this.temp = temp;
        }
    }
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<String>();
        Stack<MyNode> stack = new Stack<MyNode>();
        MyNode root = new MyNode(0, s, "");
        
        stack.push(root);
        
        while( !stack.isEmpty()){
            MyNode curr = stack.pop();
            
            if(curr.level == 4){
                if(curr.s.length() == 0){
                    result.add(curr.temp);
                }
                continue;
            }
            
            for(int i= 1; i <= 3 && i <= curr.s.length(); i++){
                String newStr = curr.s.substring(0, i);
                if(curr.s.charAt(0) == '0' && i > 1) break;
                if(isValid(newStr)){
                    if(curr.level != 0){
                    	
                        newStr = curr.temp + "." + newStr;
                    }

                    stack.push(new MyNode(curr.level + 1, curr.s.substring(i), newStr));
                }
            }
            
        }
        
        
        return result;
    }
    
    public boolean isValid(String s){
        if(s.length() ==0 || s.length() > 3){
            return false;
        }
        
        int val = Integer.valueOf(s);
        return val < 256;
    }
}
