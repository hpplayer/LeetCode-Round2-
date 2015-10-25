/*
Best Time to Buy and Sell Stock III


Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
*/

/**
 * DP solution
 * 
 * The tricky part is to come up with the idea and be clear about the logic, also come up with the tricky to reverse the sign of buy value
 *
 * First of all, we need to realize that local maximum profit may not be global maximum profit. In other words, if we made two transactions
 * too early, then we may miss the global maximum profit
 * ex:
 * 1 4 3 7 2 9
 * when looking at range 1-7
 * In this case, if we do two transactions, then we will get such profit: (4-1) + (7-3) = 7
 * In this case, if we do one transaction, then we will get such profit: 7-1 = 6
 * Then we made decide to make two transactions.
 * However, if we look at a global view, then do one transaction will be a better choice, since we will get profit: 6 + 7 = 13 
 * If we already decide to split the range 1-7, then whichever transaction we pick in this range will not get a global maximum profit
 * 
 * Each transaction itself can be a part of two transaction that make maximum profit, or two transactions in itself may already compose
 * the way to make maximum profit. so we need two sets of variables to cover both cases. oneBuy and oneBuyOneSell is used to cover the 
 * case that current transaction is part of final two transactions. twoBuy and twoBuyTwoSell is used to cover the case that two transactions
 * inside current transaction can make global profit. We need to pay special attention to twoBuy. twoBuy means we will buy the stock after
 * we have completed the first stock, so we need to include the profit made by first transaction into twoBuy, otherwise it can still be
 * treated as oneBuy. By including the profit made by first transaction into twoBuy, the value of twoBuyTwoSell of course will cover the max
 * profit made by first transaction. So in the end, we just need to return the value of twoBuyTwoSell, and it will be our solution
 * 
 * 
 * Remark:
 * 1) No matter how the input array is given to us, make two transactions can always make equal or higher profit than one transaction.
 *  Think about example 1, 6, 5, 10
 * 2) remember to reverse the sign of prices[i] to make it negative !!!!!!!!!!!!
 * 3) the follow up of this problem can be found in Best_Time_to_Buy_and_Sell_Stock_IV (p188)
 * 4) In this series we allow multiple transactions on same day. But we must sell before we can buy it again in same day
 * @author hpPlayer
 * @date Oct 24, 2015 2:39:58 PM
 */
public class Best_Time_to_Buy_and_Sell_Stock_III_p123_sol1 {
    public int maxProfit(int[] prices) {
        //for each transcation, the profit may be made by looking at the transcation as a whole (then assoicated with a later transaction)
        //or split it into two parts, so we need two sets of variables to record these two cases
        
        //we will reverse buy price to let it < 0, so the calculation will be convenient
        //But more importantly, when calculating twoBuy value, where we need consider the profit made from oneBuyOneSell, by reversing the
        //sign of buy price, we can make oneBuyOneSell and buy price has same trend, i.e. we want both of them to be maximum to make an
        //optimized twoBuy
        int oneBuy = Integer.MIN_VALUE;
        int oneBuyOneSell = 0;
        int twoBuy = Integer.MIN_VALUE;;
        int twoBuyTwoSell = 0;
        
        for(int i = 0; i < prices.length; i++){
            //include the case that a transaction is treatead as a whole and complete transaction
            oneBuy = Math.max(oneBuy, -prices[i]);
            oneBuyOneSell = Math.max(oneBuyOneSell, prices[i] + oneBuy);
            //include the case that a transaction is split into two parts, current cell is the split spot
            //we will include the maximum profit we can made from first part into the count.
            //so it is convenient that we make prices[i] to be negative in calculation, because now we want both of them to be maximum
            twoBuy = Math.max(twoBuy, oneBuyOneSell - prices[i]);
            twoBuyTwoSell = Math.max(twoBuyTwoSell, twoBuy + prices[i]);
        }
        
        //because we have include the maximum profit can be made from one transaction in twoBuy, therefore twoBuyTwoSell must
        //include the global max profit we can made from any way we can provide
        return twoBuyTwoSell;
    }
}
