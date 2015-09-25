/*

Best Time to Buy and Sell Stock II

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like
(ie, buy one and sell one share of the stock multiple times).
However, you may not engage in multiple transactions at the same time
(ie, you must sell the stock before you buy again).

*/

/**
 * Greedy problem
 * 
 * The tricky part is how to understand the problem correctly
 * 
 * The solution here is greedy sum the positive profit, how can we do that?
 * Say we have a b c, if b > a and c > b, then our profit would be b - a + c - b = c - a
 * How can we involve b twice as problem states that "you must sell the stock before you buy again" Well, that's allowable, since 
 * our profit is consecutive. For the consecutive positive profits, we can treat it as composed of several small profits, or just a 
 * big transaction that buy at the lowest price then sell at highest price. thus we can ignore b, even we may treat it as doing buy-and-sell
 * at the same day on same stock.
 * 
 * In the other hand, If we have a b c, if a < b, b > c, then of course we only buy at a, as b is peak among a, b, c, we want buy 
 * at lowest price than sell at highest price b, 
 * 
 * @author hpPlayer
 * @date Sep 25, 2015 1:44:15 AM
 */
public class Best_Time_to_Buy_and_Sell_Stock_II_p122_sol1 {
    public int maxProfit(int[] prices) {
        int result = 0;
        
        for(int i = 1; i < prices.length; i++){
            int profit = prices[i] - prices[i-1];
            
            result += profit > 0 ? profit : 0; 
        }
        
        return result;
    }
}
