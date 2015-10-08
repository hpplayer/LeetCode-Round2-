import java.util.Stack;
/*
 * Maximal Rectangle 
 * 
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
 */

/**
 * Stack solution
 * 
 * This problem can be attacked in at least two ways. Sol1 provides a stack way while Sol2 provides a pure DP way
 * 
 * In sol1, we can use the code from Largest Rectangle in Histogram (p84) to finish the main task.
 * This problem can be split into two parts. 
 * In part1, we will record the max bar ended at each row, so for each row we will have a mini-version of problem Largest Rectangle in Histogram 
 * In part2, we use the function from Largest Rectangle in Histogram to calculate the max rectangle ended at each row, we just mark
 * the max one, and return it as solution
 * 
 * The time complexity of this solution is O(2mn), because we will scan the matrix 2 times
 * 
 * Sol1 uses stack approach. The time complexity is O(2mn), and it is easier to come up with
 * Sol2 uses pure DP approach, The time complexity is O(mn), and it is not easy to come up with
 * 
 * @author hpPlayer
 * @date Oct 7, 2015 11:35:24 PM
 */

public class Maximal_Rectangle_p85_sol1 {
    public int maximalRectangle(char[][] matrix) {
        //In this solution, we record the bar height end at each row, then use the same technique in 
        //Largest Rectangle in Histogram to solve it
        
        //boundary case
        if(matrix.length == 0) return 0;
        
        int m = matrix.length, n = matrix[0].length;
        
        int[][] helper = new int[m][n];
        
        //we will first handle the first row as boundary case
        for(int i = 0; i < n; i++){
            if(matrix[0][i] == '1') helper[0][i] = 1;
        }
        
        //then we will scan the rest matrix and assign each cell the height of bar end here
        for(int i = 1; i < m; i++){
            for(int j = 0; j < n; j++){
                //if current cell in matrix is 1, then we know we at least have a bar of height 1
                //ended at this cell, we will also look up the cell above it to see if we have higher bar above
                if(matrix[i][j] == '1') helper[i][j] = helper[i-1][j] + 1;
            }
        }
        
        //then we just use the function in Largest Rectangle in Histogram to find the max rectangle in matrix
        int result = 0;
        for(int[] row : helper){
            result = Math.max(result, LargestArea(row));
        }
        
        return result;
    }
    
    //same function as used in Largest Rectangle in Histogram
    public int LargestArea(int[] row){
        int result = 0;
        Stack<Integer> stack = new Stack<Integer>();
        int size = row.length;
        
        //include a dummy tail to cover all indexes
        for(int i = 0; i <= size; i++){
            //the dummy tail has height of 0
            int newBar = i == size? 0 : row[i];
            //we will keep bar height in the stack following undescending order
            //so that we can calculate the area after find the right edge
            if(stack.isEmpty() || newBar >= row[stack.peek()]){
                stack.push(i);
            }else{
                //after find the right edge, we will pop all bars that have higher height
                //since their rectangle will stop here
                while(!stack.isEmpty() && newBar < row[stack.peek()]){
                    //since the real left edge may hide in some taller bars,
                    //the bar in the index just indicates the height of rectangle and it may 
                    //not be the real left edge
                    int index = stack.pop();
                    
                    //stack.peek() is the bar just before the left edge, and it may be far away
                    //from the index we pop above. So bar at index i is the bar just after the right 
                    //edge and the bar at peek() is the bar just before the left edge. By using these
                    //two bars, we can calculate the width
                    //if we got the rectangle that starts from index 0, then it wouldn't have the bar before
                    //left edge, for such case, we simply calculate the width by assiging i
                    //ex: i = 2, while the rectangle from 0 to 1, the width is 2 
                    result = Math.max(result, row[index] * (stack.isEmpty()? i : i - 1 - stack.peek()));
                }
                
                //now we can push i to stack and still keep the non-descending property
                stack.push(i);
            }
        }
        
        return result;
    }
}
