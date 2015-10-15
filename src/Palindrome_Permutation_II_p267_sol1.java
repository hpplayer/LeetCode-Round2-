import java.util.*;
/*

Palindrome Permutation II

Given a string s, return all the palindromic permutations (without duplicates) of it.
Return an empty list if no palindromic permutation could be form.

For example:

Given s = "aabb", return ["abba", "baab"].

Given s = "abc", return [].

Hint:

If a palindromic permutation exists, we just need to generate the first half of the string.
To generate all distinct permutations of a (half of) string, use a similar approach from: Permutations II (p47) or Next Permutation (p31).

*/

/**
 * String + backtracking solution
 * 
 * This solution can be divided into two parts 1) build occurrence table 2) generate palindromes
 * The basic idea is firstly building the occurrence table that is similar (not same) to problem Palindrome Permutation (p266)
 * Then we can use the table to check if we can build palindrome strings. For odd length of palindrome, we will find the char 
 * with odd occurrences, put it in mid, then do recursion. Each recursion will append one char in front and int tail. So we
 * will reduce 2 from the occurrences of this char. Then we will do the recursion, until our string has length of given s.
 * 
 * For duplicates, each cell represents a unique char, and each recursion we will visit the unique char once, so we won't 
 * worry about duplicates
 * 
 * Remark:
 * This problem can be perfectly solved by recursion, but in case iteration is needed, see sol2
 * 
 * Sol1 and sol2 use the same idea.
 * Sol1 is recursion
 * Sol2 is iteration, due to operations on stack, the speed is not as fast as recursion
 * 
 * If more smart iterative solution come up, I will update it accordingly
 * 
 * This problem is similar to problem Strobogrammatic Number II (p247) where we will build the string from two sides, and attach two
 * chars on two sides in each recursion.
 * 
 * @author hpPlayer
 * @date Oct 3, 2015 4:25:10 PM
 */
public class Palindrome_Permutation_II_p267_sol1 {
	public static void main(String[] args){
		int[] ori = {1,2};
		int[] newArray = Arrays.copyOf(ori, 2);
		ori[0] = 3;
		System.out.println(Arrays.toString(ori));
		System.out.println(Arrays.toString(newArray));
		
		String s = "abababab";
		System.out.println(new Palindrome_Permutation_II_p267_sol1().generatePalindromes(s));
	}
    int len = 0;//len of s, so our backtracking know where to stop
    public List<String> generatePalindromes(String s) {
        len = s.length();//set target palin length
        int[] count = new int[256];//occurence table
        for(char c : s.toCharArray()) count[c] ++;//increase the occurence
        
        boolean odd = false;//indicate whether we have found a char with odd occurences
        int odd_char = 0;//indicate which char has odd occurences
        
        //we have to go through entire array to find the num of chars with odd occurences
        for(int i = 0; i < 256; i++){
            if((count[i]&1) == 1){
                //if found a char with odd occurences
                if(odd) return new ArrayList<String>();//if we have two chars with odd occurences, return directly
                odd = true;
                odd_char = i;
            }
        }
        
        String temp = "";//like temp list in permutation problem
        if(odd){
            //if we got a char with odd occurences, then we manually add it in mid
            temp += (char) odd_char;
            count[odd_char] --;//decrease the count
        }
        
        List<String> result = new ArrayList<String>();
        DFS(result, temp, count);
        
        return result;
    }
    
    public void DFS(List<String> result, String temp, int[] count){
        if(temp.length() == len){
            //boundary case
            result.add(temp);
            return;
        }
        
        //since each index is a unique char and we - 2 during each visit, we would'have duplicates in result
        for(int i = 0; i < 256; i++){
            if(count[i] == 0) continue;//skip char that we have 0 count
            count[i] -= 2;//in each recursion, the num in cell must be multiplies of 2, so we can safly -2
            char c = (char) i;
            DFS(result,  c + temp + c, count);//next recursion
            count[i] += 2;//reset
        }
    }
}
