import java.util.*;

/**
 * More explanation will be updated tomorrow
 * 
 * @author hpPlayer
 * @date Oct 23, 2015 1:13:34 AM
 */

public class Best_Meeting_Point_p296_sol1 {
    public int minTotalDistance(int[][] grid) {
        List<Integer> rows = new ArrayList<Integer>();
        List<Integer> cols = new ArrayList<Integer>();
        
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        
        Collections.sort(cols);
        
        return getLen(rows) + getLen(cols);
    }
    
    public int getLen(List<Integer> list){
        int start = 0 ;
        int end = list.size() -1;
        int result = 0;
        
        while(start < end){
            result += list.get(end--) - list.get(start++);
        }
        
        return result;
    }
}
