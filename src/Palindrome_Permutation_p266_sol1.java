/*
Palindrome Permutation

Given a string, determine if a permutation of the string could form a palindrome.

For example,
"code" -> False, "aab" -> True, "carerac" -> True.

Hint:

Consider the palindromes of odd vs even length. What difference do you notice?
Count the frequency of each character.
If each character occurs even number of times, then it must be a palindrome. How about character which occurs odd number of times?
*/		

/**
 * Counting solution
 * 
 * There are may "raw" way to solve the problem. The tricky part is how to write a beautiful solution with less code but be more efficient
 * 
 * If the problem allow us to cover all permutations of a string, then we just need to count the occurrence of each char. For a valid palindrome
 * we would at most have 1 char that has odd occurrences. In sol1, we use a variable "odd" to count the number of chars we have odd occurrences.
 * We build a 256 length table to record the occurrences of each char. Each time after we update the occurrences of char, we will check if it
 * is odd or even. If it is odd, we will + 1 to the variable "odd", otherwise, we will - 1 to the variable "odd". So finally, if a char appears
 * even times, then it will contribute 0 to variable "odd". Otherwise, the char appears odd times will contribute 1 to variable "odd".
 * So we just need to check "odd", return true if it is < 2, else return false;
 * 
 * Remark:
 * In sol1, we use several techniques:
 * 1) use & 1 to check the odd or even. Even number won't have 1 is last bit, and odd number will have 1 in last bit
 * 2) use "odd" to specifically count the num of odd occurrence, so we don't need to scan the count[] to count the total occurrences of each char
 * 3) we use char c directly in indexing, i.e. casting the char as an int
 * 
 * @author hpPlayer
 * @date Oct 3, 2015 2:26:49 PM
 */
public class Palindrome_Permutation_p266_sol1 {
    public boolean canPermutePalindrome(String s) {
        int odd = 0;//count how many odd counts do we have
        int[] count = new int[256];//count table
        
        for(char c : s.toCharArray()){
            //an odd number & 1 will get 1, we use this operation to count odd
        	//Notice: update "odd" after we updated the occurence
            odd += (++count[c] & 1) == 1? 1 : -1;//for even number, we will first + 1 odd, then -1
        }
        
        //if a char has even occurences, then it won't contribute to "odd", 0 contribution
        //if a char has odd occurences, then it it will contribute 1 to "odd", 1 contribution
        //so by looking at "odd", we will know how many chars of odd occurences do we have
        return odd < 2;
    }
}
