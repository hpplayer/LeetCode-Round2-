import java.util.*;
/**
 * Similar solution as sol1, but here I use stack to make the program iterative.
 * 
 * Remark:
 * 1. To avoid the mix of count[] from different Node, I used Arrays.copyOf() function to copy the array
 * 2. DONT BE CONFUSED WITH COUNT AND NODE.COUNT, THEY ARE DIFFERENT!!!!!!!!!!
 * 
 * @author hpPlayer
 * @date Oct 3, 2015 5:04:42 PM
 */

public class Palindrome_Permutation_II_p267_sol2 {
	public static void main(String[] args){
		String s = "aabbcc";
		System.out.println(new Palindrome_Permutation_II_p267_sol2().generatePalindromes(s));		
	}
    public class MyNode{
        String temp;
        int[] count;
        public MyNode(String temp, int[] count){
            this.temp = new String(temp);
            this.count = Arrays.copyOf(count, 256);
        }
    }
    public List<String> generatePalindromes(String s) {
        int[] count = new int[256];
        for(char c : s.toCharArray()) count[c]++;
        
        boolean odd = false;
        int odd_char = 0;
        for(int i = 0; i < 256; i++){
            if((count[i]&1) == 1){
                if(odd) return new ArrayList<String>();
                odd = true;
                odd_char = i;
            }
        }
        
        String temp = "";
        if(odd){
            temp += (char) odd_char;
            count[odd_char] --;
        }
        
        Stack<MyNode> stack = new Stack<MyNode>();
        stack.push(new MyNode(temp, count));
        List<String> result = new ArrayList<String>();
        while(!stack.isEmpty()){
            MyNode node = stack.pop();
            if(node.temp.length() == s.length()){
                result.add(node.temp);
                continue;
            }
            
            for(int i = 0; i < 256; i++){
                if(node.count[i] == 0) continue;//skip char with 0 count
                node.count[i] -= 2;
                char c = (char) i;
                stack.push(new MyNode(c + node.temp + c, node.count));
                node.count[i] += 2;//don't forget add 2 back, so next char will not be affected
            }
        }
        
        return result;
    }
}
