import java.util.*;
/**
 * Math problem.
 * 
 * The difficulty is dealing with boundary case and how we find recurring decimals.
 * 
 * To deal with overflow problem, we redefine two longs and use them in calculation
 * The recurring decimal is from recurring remainders, and we need to insert "(", ")" around recurring parts.
 * So we need a HashMap to map recurring remainders and indexes before inserting the decimal from this remainder.
 * Thus if we found a remainder already exists in Map, we will stop calculation and insert "(" before it, the index of insertion
 * is stored in Map, and insert ")" to the end of string, then return result
 *  
 * @author hpPlayer
 * @date Sep 22, 2015 5:26:53 PM
 */
public class Fraction_to_Recurring_Decimal_p166_sol1 {
	public static void main(String[] args) {
		System.out.println(3* 2 % 2 );
		int num = 1, denom = 3;
		System.out.println(new Fraction_to_Recurring_Decimal_p166_sol1().fractionToDecimal(num, denom));
	}

    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder sb = new StringBuilder();
        
        long newNum = numerator, newDenom = denominator;
        if(newNum * newDenom < 0){
            sb.append("-");
            newNum = Math.abs(newNum);
            newDenom = Math.abs(newDenom);
        }
        
        sb.append(newNum/newDenom);
        long remainder = newNum%newDenom;
        
        //if result is integer
        if(remainder == 0) return sb.toString();
        //if it is decimal
        sb.append(".");
        
        //key is remainder, value is index before inserting the result corresponding to the remainder
        HashMap<Long, Integer> hs = new HashMap<Long, Integer>();
        while(!hs.containsKey(remainder)){
            //record index before insert new value to result
            hs.put(remainder, sb.length());
            //10 * remainder as we did for math
            remainder *= 10;
            
            sb.append(remainder/newDenom);
            remainder %= newDenom;
            //if remainder becomes 0, it means we are done with calculation, just return result
            if(remainder == 0) return sb.toString();
        }   
        
        //found the index before this repeat remainder and insert "("
        sb.insert(hs.get(remainder), "(").append(")");
        
        return sb.toString();
    }
}
