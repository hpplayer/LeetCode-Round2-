/*
Best Time to Buy and Sell Stock IV

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
*/

/**
 * DP + Observation
 * 
 * The tricky part is to find the pruning technique and come up with the idea of DP
 * 
 * This solution is very similar to the solution in Best Time to Buy and Sell StockIII (p123), but we need to generalize it to k transaction
 * cases, also we need preprocessing for extreme large k cases.
 * 
 * First of all, let's discuss why we say len/2 transaction can already retrieve the max profit? Sure, we can have multiple transactions more
 * than len/2 times, but if look carefully, there will only at most len/2 transactions that will gain profit. For the number exceed the len/2,
 * we can either combine it to one of a transaction in len/2 transactions or they will let us lose profit. Ex: 1 2 3 4, in this case, we can 
 * have 3 transactions but they can be merged into one transaction with same profit. Ex: 1 3 2 1, in this example, we only have 1 transaction
 * that can earn profit. Think about the hill. the transaction that makes profit is like the uphill. For an array of n points, how many uphills
 * can we make? at most n/2 times. so if given k > len(prices), we will treat it as Best_Time_to_Buy_and_Sell_Stock_II_p122_sol1, and sum up 
 * all positive profits.
 * 
 * The remaining part is similar to solution in p123. We create a dp table that has 2 columns. The first column stores the max Buy value,
 * the second column stores the max buyAndSell value. For each cell(except row 0), all buy value will include the max buyAndSell value from
 * previous row. We also need to store negative prices to keep consistency and make it is easier for use to calculate the max buyAndSell value.
 * Finally, we just return the buyAndSell value in the last row, and it will give the max profit we can make 
 * 
 * Remark:
 * 1) remember to initialize default buy value to be Integer.MIN_VALUE because we store the reverse of (prices), which may be negative!!!!!!
 * @author hpPlayer
 * @date Oct 24, 2015 3:50:09 PM
 */
public class Best_Time_to_Buy_and_Sell_Stock_IV_p188_sol1 {
	public static void main(String[] args){
		int[] prices = {1,3,4,5,6,7};
		System.out.println(maxProfit(1, prices));
	}
    public static int maxProfit(int k, int[] prices) {
        //boundary case, no transaction made
        if(k == 0) return 0;
        
        //pruning, deal with the case that k is exremely large
        //for len l, there AT MOST will have l/2 meaningful transaction that will gain profit
        //think about a hill. we at most can make l/2 uphills
        //so any transaction number > l/2 will not gain more profit. Their profits all converage at l/2 transactions
        if(k >= prices.length/2){
            int result = 0;
            for(int i = 1; i < prices.length; i++){
                if(prices[i] > prices[i-1]){
                    result += prices[i] - prices[i-1];
                }
            }
            
            return result;
        }
        
        //general case, create a dp matrix with width 2. First cell holds buy value second cell holds sell value
        int[][] dp = new int[k][2];
        
        //we will store negative prices, so set all initial value of buy to be negative
        for(int i = 0; i < dp.length; i++){
            dp[i][0] = Integer.MIN_VALUE;    
        }
        
        for(int i = 0; i < prices.length; i++){
            //boundary case, oneBuy and oneBuyOneSell from current cell
            dp[0][0] = Math.max(dp[0][0], -prices[i]);
            dp[0][1] = Math.max(dp[0][1], prices[i] + dp[0][0]);
            for(int j = 1; j < dp.length; j++){
                //general case, where each buy value will include maxProfit made by buyAndSell value from prev transaction
                dp[j][0] = Math.max(dp[j][0], dp[j-1][1] - prices[i]);
                dp[j][1] = Math.max(dp[j][1], dp[j][0] + prices[i]);
            }
        }
        
        //the last buyAndSell value will include the max profits we can make 
        return dp[k-1][1];
    }
}
