/*
Integer to Roman

Given an integer, convert it to a roman numeral.

Input is guaranteed to be within the range from 1 to 3999.
*/

/**
 * Math problem.
 * 
 * The tricky part: 1) must has basic understanding of Roman Integer, like what is M(1000), C(100).. 2)observations
 * 
 * Let's start this problem with some examples:
 * 1 in Roman integer is I
 * 2 in Roman integer is II
 * 3 in Roman integer is III
 * 
 * 5 in Roman integer is V
 * 6 in Roman integer is VI
 * 7 in Roman integer is VII
 * 8 in Roman integer is VIII
 * 10 in Roman integer is X
 * 
 * In above example, we observe that the first digit in Roman integer always start with a number same or less than input integer.
 * The second digit is a number same or less than the remainder if we subtract first digit from input integer. We will do this until we reach input
 * But using this observation alone will not cover all integers,
 * 
 * we are still missing integer 4, 9
 * 4 in Roman integer is IV
 * 9 in Roman integer is IX
 * 
 * 14 in Roman integer is XIV
 * 19 in Roman integer is XIX
 * 
 * In above example, we observe that 4 and 9 are special cases, but fortunately if we hard code them in the program, following numbers will still
 * follow observation 1:
 * 14 = 10 + 4
 * 19 = 10 + 9
 * 
 * Combine them together, we can conclude our program. Firstly we build a map that links Integer Digit with Roman Digit.
 * Then we look up from large values to small values and find the value that is same or less than input integer.
 * To be convenient, we keep a pointer in map, each time we will look at one index, then move to next index.
 * We use / operator to get the number of value in this index we need, so next loop can avoid the number in this index, and move to next index
 * 
 * @author hpPlayer
 * @date Sep 27, 2015 10:21:01 PM
 */
public class Integer_to_Roman_p12_sol1 {
	//hard core all 4s 9s in input, so remain integer number will follow our observation 1
	//4s include 4, 40, 400, 9s include 9, 90, 900
	private static final int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

	private static final String[] symbols = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

	public String intToRoman(int num) {
		StringBuilder sb = new StringBuilder();

		int i = 0;
		while (num != 0) {
			int times = num / values[i];
			for (int j = 0; j < times; j++) {
				sb.append(symbols[i]);
			}

			num -= values[i] * times;
			i++;
		}

		return sb.toString();
	}
}
