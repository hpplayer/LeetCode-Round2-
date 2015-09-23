/**
 * Binary search + divide-and-conquer
 * 
 * The difficulty is to define the next search range and why.
 * We firstly pick the mid column, and search for the first value > target, say its index is i and index of mid col is mid.
 * Then we have matrix[i][mid] > target while matrix[i-1][mid] < target. Now we can split the matrix into four parts. Among them, top-left one ends at 
 * matrix[i][mid-1], bottom-right one starts at matrix[i][mid]. We can safely eliminate these two parts, since we know all cells in top-left will
 * be smaller than target while all cells in bottom-right will be larger than target. Thus we can just search the remaining two part: top-right and 
 * bottom-left parts for our target. When we are searching value in mid col, if the target is in mid col, then we should find it. But if we did not 
 * find target in mid col, then we can safely further shrink our next search range by skipping mid col.
 * 
 * The time complexity analysis for this algorithm is hard, but it provides a very beautiful way to solve this problem with binary-search and divide-and-conquer
 * 
 * This problem could also be solved by stepwise algorithm, which starts from top-right or bottom-left, the time complexity is O(2n). But it is 
 * trivial, so I do not list it here
 * @author hpPlayer
 * @date Sep 22, 2015 3:22:25 PM
 */
public class Search_a_2D_Matrix_II_p240_sol1 {
    public boolean searchMatrix(int[][] matrix, int target) {
        return searchByPart(0, matrix.length -1, 0, matrix[0].length -1, matrix, target);
    }
    
    public boolean searchByPart(int top, int bottom, int left, int right, int[][] matrix, int target){
        //out of boundary
        if(top > bottom || left > right) return false;
        
        //we are looking for the mid col
        int mid = left + (right - left)/2;
        int row = binarySearch(top, bottom, mid, target, matrix);
        //if we found target
        if(row < matrix.length && matrix[row][mid] == target) return true;
        
        //1)matrix[row][mid] is the first value > target in mid col, so cols on the right of mid on the same row will not be 
        //our target, thus we can just search right top part by skip current row. 
        //but we don't know if target is in the left of mid on the same row, so when search bottom-left part we can't skip current row
        //2)we didn't find target in mid, thus we can skip mid col
        return searchByPart(top, row-1, mid + 1, right, matrix, target) || searchByPart(row, bottom, left, mid-1, matrix, target);
    }
    
    public int binarySearch(int top, int bottom, int col, int target, int[][] matrix){
        while(top <= bottom){
            int mid = top + (bottom - top)/2;
            if(matrix[mid][col] == target){
                return mid;
            }else if(matrix[mid][col] > target){//too large
                bottom = mid - 1;
            }else{
                top = mid + 1;
            }
        }
         return top;//return the x value of matrix[x][y] > target
    }
}
