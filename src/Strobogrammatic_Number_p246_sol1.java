/*
Strobogrammatic Number

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

For example, the numbers "69", "88", and "818" are all strobogrammatic.
*/

/**
 * Two pointer problem
 * 
 * The problem itself is not hard. The tricky part is how write a fast and beautiful solution
 * 
 * Based on the definition, the given word should be same even if it is upside down.
 * So, we found the first char must be the upside-down version of last char. Similar to the second and last second char, and
 * other pair of chars. Those valid pairs include "00, 11, 88, 69 and 96". We can simply create a HashMap, and place them in 
 * the map. It is trivial, so I did not put them here. We found the valid range is so limited that we even can hard-code them
 * 
 * In sol1, we build a long string contains all pairs above, then we use String.contains() to check the pair
 * 
 * @author hpPlayer
 * @date Oct 14, 2015 11:06:28 PM
 */
public class Strobogrammatic_Number_p246_sol1 {
    public boolean isStrobogrammatic(String num) {
        int left = 0, right = num.length() - 1;
        while(left <= right){
        	//we need to check last char where left == right as well
        	//if left == right, then we must have 11 00 or 88 otherwise return false
        	//for 69 and 96, we can combine them together to 696.
        	//As we never has a composed string.length > 2, we can do that
        	if( !"00 11 88 696".contains(num.charAt(left) + "" + num.charAt(right))) return false;
        	left ++;
        	right --;
        }
        
        return true;
    }
}
