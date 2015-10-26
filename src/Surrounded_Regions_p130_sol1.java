import java.util.*;

/*
Surrounded Regions

Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
*/


/**
 * DFS solution
 * 
 * There are two tricky parts: 1) come up with the idea of doing BFS/DFS on 'O' on boundary 2) using iterative DFS to get rid of
 * stack overflow problem
 * 
 * The main idea is using DFS and BFS start search all nodes connected with 'O' in 4 boundaries. We set them to a different char, say 'D'.
 * After done BFS/DFS search, we just revisit each cell in matrix. We will set 'D' to 'O', and set other 'O' to 'X'.
 * 
 * Remark:
 * 1) Since the solution here is a standard DFS visit on matrix, I did not put much comment here
 * 2) This problem can also be solved by BFS with a minor changes.
 * 3) Besides, this problem can also be solved by union-find. Due to the time limit, I did not study the solution, I will update it later 
 * if I got time
 * 
 * This problem is similar to problem Number of Islands(p200)
 * 
 * @author hpPlayer
 * @date Oct 26, 2015 5:44:05 PM
 */

public class Surrounded_Regions_p130_sol1 {
    private class MyNode{
        int x;
        int y;
        MyNode(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public void solve(char[][] board) {
        if(board.length == 0) return;
        
        Stack<MyNode> stack = new Stack<MyNode>();
        
        for(int i = 0; i < board.length; i++){
            if(board[i][0] == 'O'){
                stack.push(new MyNode(i, 0));
            }
            
            if(board[i][board[0].length - 1] == 'O'){
                stack.push(new MyNode(i, board[0].length - 1));
            }
        }
        
        for(int i = 0; i < board[0].length; i++){
            if(board[0][i] == 'O'){
                stack.push(new MyNode(0, i));
            }
            
            if(board[board.length - 1][i] == 'O'){
                stack.push(new MyNode(board.length - 1, i));
            }
        }        
        
        while(!stack.isEmpty()){
            MyNode curr = stack.pop();
            board[curr.x][curr.y] = 'D';
            
            int[] xOffset = {0, 0, 1, -1};
            int[] yOffset = {1, -1, 0, 0};
            
            for(int i = 0; i < 4; i++){
                int newX = curr.x + xOffset[i];
                int newY = curr.y + yOffset[i];
                
                if(newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length && board[newX][newY] == 'O'){
                    stack.push(new MyNode(newX, newY));
                }
            }            
            
        }
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }else if(board[i][j] == 'D'){
                    board[i][j] = 'O';
                }
            }
        }
        
    }
}
