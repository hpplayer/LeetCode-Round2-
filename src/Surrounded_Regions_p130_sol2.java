/**
 * Union find solution
 * 
 * The tricky part is to get understand with union-find algorithm,  and also get the idea of searching from 'O' on boundary
 * 
 * This solution does not work faster than BFS or DFS solution, but it provides another approach.
 * We firstly preprocess the data and mark all "O"s on boundary. Then we use union-find algorithm to connect all neighbor 'O'.
 * If we found the union part has boundary "O", then we will mark this union part. After we done this to all 'O's on board, we will
 * begin preprocess data. For each 'O', we will find its root and check if the union part with this root has boundary "O". If yes,
 * then we will skip it, otherwise we will reset it to "X"
 * 
 * Remark:
 * We need scan the board for three times. 1) preprocess, initialize unionSet[] and search boundary 'O" 2) find-and-union, and check if
 * union part has boundary "O" 3) postprocess, reset "O" to "X" if the union-part where this "O" at does not have boundary "O"
 * 
 * So the time complexity is O(mn * mn), where we use O(mn) to union and there are totally O(mn) cells.
 * 
 * Problem Graph Valid Tree (p130) can also be solved by union find, check it out!
 * 
 * @author hpPlayer
 * @date Oct 30, 2015 2:50:03 PM
 */
public class Surrounded_Regions_p130_sol2 {
	public static void main(String[] args){
		char[][] board = { {'X', 'X', 'O', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'X', 'X'} };
		new Surrounded_Regions_p130_sol2().solve(board);
		
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	
	
    public void solve(char[][] board) {
        //boundary case
        if(board.length == 0 || board[0].length == 0) return;
        
        int m = board.length, n = board[0].length;
        
        int[] roots = new int[m*n];
        boolean[] hasEdgeO = new boolean[m*n];
        
        //data preprocessing
        for(int i = 0; i < m*n; i++){
            roots[i] = i;
            //get x and y
            int x = i/n, y = i%n;
            //get boundary "O"
            if (board[x][y] == 'O' && (x == 0 || x == m -1 || y == 0 || y == n - 1)){
                hasEdgeO[i] = true;
            }
        }
        
        //union find, union cells, and check if union part has EdgeO
        for(int i = 0; i < m*n; i++){
            int x = i/n, y = i%n;
            //we only look at 'O's
            if(board[x][y] == 'O'){
                //look left and top cells, if they are 'O', then we can add curr cell to an existing union part
                
                //1) add curr cell to existing union part (set roots[curr] = root of union part) is ok
                //2) set existing union part's root to curr cell (set roots[root] = curr cell) is also ok
                //as long as we can union curr cell with union part, both operations are ok
                
            	//each time we union two parts, what we really do is locating the root of each union part, then merge root together
            	//so don't worry about the direction, either direction can give one root, then we can use this root to merge with ohter part
                
                //top cell, add curr cell to top union part if we have one
                //top cell has index i - n, i.e. it is exactly one row width (n) before curr cell
                if(x - 1 >= 0 && board[x-1][y] == 'O') union(i, i-n, roots, hasEdgeO);
                
                //left cell, add curr cell (or top union part if we have union curr with top part) to left union part if we have one
                //left cell has index i-1
                if(y - 1 >= 0 && board[x][y-1] == 'O') union(i, i-1, roots, hasEdgeO);
                
                /*reverse way, also works
              	if(y - 1 >= 0 && board[x][y-1] == 'O') union(i-1, i, roots, hasEdgeO);
                */
            }
        }
        
        
        //data postprocessing, for each 'O' cell find its root, and use the root to check if we have EdgeO in this union part
        
        for(int i = 0; i < m*n; i++){
            int x = i/n, y = i%n;
            if(board[x][y] == 'O'){
                int root = findRoot(i, roots);
                //reset 'O' to 'X', if its union part does not EdgeO
                if(!hasEdgeO[root]){
                    board[x][y] = 'X';
                }
            }
        }
    }
    
    public void union(int a, int b, int[] roots, boolean[] hasEdgeO){
        //find root of part a and part b
        int root1 = findRoot(a, roots); 
        int root2 = findRoot(b, roots);
        
        //check if union part1 or union part2 has boundary O
        boolean EdgeO = hasEdgeO[root1] || hasEdgeO[root2];
        
        //union two parts together
        roots[root1] = root2;
        //update whether new union part has EdgeO
        hasEdgeO[root2] = EdgeO;
    }
    
    public int findRoot(int x, int[] roots){
        if(roots[x] == x) return x;
        //find the root of current union part
        roots[x] = findRoot(roots[x], roots);
        return roots[x];
    }
}
