/*
Add Digits

Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

For example:

Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

Follow up:
Could you do it without any loop/recursion in O(1) runtime?

Hint:

A naive implementation of the above process is trivial. Could you come up with other methods?
What are all the possible results?
How do they occur, periodically or randomly?
You may find this Wikipedia article useful. (https://en.wikipedia.org/wiki/Digital_root)
*/


/**
 * Math problem.
 * 
 * The tricky part is to observe the magic of 9
 * 
 * In mathematics, the result of this problem is called digit roots, lets list some numbers:
 * 1, 10, 19 all have digit root of 1
 * 2, 11, 20 all have digit root of 2
 * ...
 * 9, 18, 27 all have digit root of 9
 * 
 * So actually we found it is a 9-hex problem. 1-9 will give result of 1-9, 10-18, will give result of 1-9, 19-27 will give result of 9
 * Thus we need to mod input number by 9, if the mod is 0, then return 9, otherwise return the mod result
 * But, we got an boundary case that num =0 which also give the mod of 9, for such case, we will handle it in the beginning
 * 
 * @author hpPlayer
 * @date Sep 26, 2015 1:00:31 AM
 */
public class Add_Digits_p258_sol1 {
    public int addDigits(int num) {
        if(num == 0) return 0;
        if(num%9 == 0) return 9;
        return num%9;
    }
}
