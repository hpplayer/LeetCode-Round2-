/**
 * DP problem.
 * we can create a matrix, each value is the edge of maximal square can be built end at this cell.
 * Then, we fill each cell in matrix and record the maximum edge, then return the area accordingly.
 * 
 * The difficulty part is to find out what does the value in matrix represent: It represents the edge of maximal square can be built end at this cell
 * And how we update it: if matrix value is 0, then set it to 0, if it is 1, then we need to update it based on the previous information. 
 * 
 * if the corresponding value in matrix is 0, then we just set dp[i][j] to 0
 * if the corresponding value in matrix is 1, then we know we may extend previous matrix we found, but we need three previous information:
 * 1) [i-1][j-1] tells us all information end at (i-1, j-1) so far
 * 2) [i-1][j] tells us the information at col j, if we got any zero in this column j, we will find it
 * 3) [i][j-1] tells us the information at row i, if we got any zero in this row i, we will find it
 * 
 * Our current dp[i][j] will based on the min value of 1)-3) + 1.
 * 
 * Since we just need those three previous information to update current cell, we can use an array + an extra variable to replace matrix.
 * Extra variable record left-top value, array[j-1] tells us left value, array[j] tells us top value.
 * 
 * 
 * @author hpPlayer
 * @date Sep 21, 2015 3:17:18 PM
 */


public class Maximal_Square_p221_sol1 {
	public int maximalSquare(char[][] matrix) {
		if (matrix.length == 0)
			return 0;
		int[] dp = new int[matrix[0].length];

		int prev = 0;// record top-left value
		int result = 0; // record the edge of square
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < dp.length; j++) {
				if (j == 0) {
					prev = dp[0];
					dp[0] = matrix[i][0] == '0' ? 0 : 1;
					result = Math.max(result, dp[0]);
				} else {
					if (matrix[i][j] == '0') {
						dp[j] = 0;
						prev = dp[j];
					} else {// at least we have a square of 1
						int temp = dp[j];// record its value, later it will be used as prev
						dp[j] = Math.min(dp[j - 1], Math.min(dp[j], prev)) + 1;
						result = Math.max(result, dp[j]);
						prev = temp;
					}
				}
			}
		}
		return result * result;
	}
}
