import java.util.*;
/*
Largest Number

Given a list of non negative integers, arrange them such that they form the largest number.

For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

Note: The result may be very large, so you need to return a string instead of an integer.
*/	
		
/**
 * Sorting problem
 * 
 * The tricky part is to come up with the idea of sorting the array lexicographically
 * 
 * To build the largest number, we must put the largest digit in front. So it is easy to come up with the idea of sorting the array based on lexicographic
 * order. We want to put the larger number in front, in other words, sorting the array with descending order. 
 * But this only applies to the pair that has same length. If we have two inputs that have different length, shall we still sort it lexicographically?
 * The solution is not necessary. Ex: a: 121 b: 12, if sort them on lexicographic order, then we will get 12112, but the largest number should be 12121
 * Ex: a: 123 b : 12, if sort them on lexicographic order, then we will get 12312, and it is the largest number. So for two numbers that do not have same length,
 * we have to build two possible result strings, compare them, then return the larger one. Below code applies this rule in Comparator(), same length
 * then compare the lexicographic order, not same length, build two possible strings, then return the larger one.
 * 
 * Below code also applies some tricks: 
 * 1) we convert the int[] to String[], so it will be convenient for us to concatenate and build result string
 * 2) in boundary case that all numbers are 0, we can't build a string like "0000", we just need to look at the very first string in sorted array,
 * if it is "0", then we know the largest digit in array is "0", so we can just return "0"
 * 
 * @author hpPlayer
 * @date Oct 25, 2015 2:26:33 PM
 */
public class Largest_Number_p179_sol1 {
    public String largestNumber(int[] nums) {
        //boundary check
        if(nums.length == 0) return "";
        
        String[] strs = new String[nums.length];
        for(int i = 0; i < nums.length; i++) strs[i] = String.valueOf(nums[i]);
        
        Arrays.sort(strs, new Comparator<String>(){
            public int compare(String a, String b){
                if(a.length() == b.length()){
                    // if same length we want sort the string lexicographically with descending order
                	//we want sort the array with descending order, so use b.compareTo(a)
                    return b.compareTo(a);
                }else{
                    //not same length, then we can't easily say which one is larger, so we will merge them then compare
                    //We compare the number that we put b first or put a first
                    return (b + a).compareTo(a + b);
                }
            }    
        });
        
        //if the array is start with 0, which means the largest digit is 0, then we return 0 directly
        if(strs[0].equals("0")) return "0";
        
        //otherwise, we attach numbers to stringBuilder to build the result
        StringBuilder sb = new StringBuilder();
        
        for(String str : strs){
            sb.append(str);
        }
        
        return sb.toString();
    }
}
