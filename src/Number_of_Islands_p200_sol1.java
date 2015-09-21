import java.util.*;
/**
 * DFS/BFS problem.
 * 
 * The solution here use an iterative BFS solution.
 * To skip visited cell, we can have two approaches. 1) we change the original input matrix, so we won't need extra data structure
 * 2) we use a boolean map
 * Since this problem does not give specific requirement, I prefer to make change on original matrix to save spaces.
 * 
 * Our target is marking an island's all cells to be '0'. To do that we can do BFS, i.e. push each node's '1' neighbor into the queue
 * then mark current node as '0' or visited. Here I use fillMap() and an inner class to record fill cells on islands. Then in our
 * main program we just need to find remaining islands in the grid to start a new fillMap(). Each time we found a new island, we
 * will let count ++
 * 
 * It is easy to write a recursive DFS solution, so I will not list it here.
 * 
 * @author hpPlayer
 * @date Sep 20, 2015 5:38:16 PM
 */

public class Number_of_Islands_p200_sol1 {
	public static void main(String[] args){
		char[][] grid = {{'1', '1'}};
		System.out.println(new Number_of_Islands_p200_sol1().numIslands(grid));
	}
    public int numIslands(char[][] grid) {
        if(grid.length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        
        //boolean visited[][] = new boolean[m+1][n+1];
        
        int count = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j< n; j++){
                if(grid[i][j] == '1'){
                	grid[i][j] = '0';
                    count++;
                    fillMap(i, j, grid);
                }
            }
        }
        
        return count;
    }
    
    public class pair{
        int x;
        int y;
        public pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public void fillMap(int x, int y, char[][] grid){
        int[] xOffset = {1, -1, 0, 0};
        int[] yOffset = {0, 0, 1, -1};
        
        Queue<pair> que = new LinkedList<pair>();
        pair start = new pair(x, y);// visited start with 1, while grid start with 0
        
        que.offer(start);
        
        while(!que.isEmpty()){
            int size = que.size(); 
            for(int i = 0; i < size; i++){
                pair curr = que.poll();
                for(int j = 0; j < xOffset.length; j++){
                    int newX = curr.x + xOffset[j];
                    int newY = curr.y + yOffset[j];
                    if(newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY] == '1'){
                    	grid[newX][newY] = '0';
                        que.offer(new pair(newX, newY));
                    }
                }
            }
        }
        
    }
}
