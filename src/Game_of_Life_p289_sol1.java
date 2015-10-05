/*
Game of Life

According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton
devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0).
Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules
(taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state.

Follow up: 
Could you solve it in-place? Remember that the board needs to be updated at the same time:
You cannot update some cells first and then use their updated values to update other cells.

In this question, we represent the board using a 2D array. In principle, the board is infinite,
which would cause problems when the active area encroaches the border of the array. How would you address these problems?
*/		
		

/**
 * 
 * Brainstorming problem
 * 
 * The tricky part is to come up with the idea of using special value for cells in transitive state
 * 
 * We can easily check the neighbors around a cell, like we did for other matrix problems (like Number of Islands p200)
 * But to how to deal with the value?
 * For those cells that don't need change status, we can just leave it
 * For those cells that need change status, we need do something to them, so later we can change them accordingly while 
 * does not affect later visiting. To do those in place, here we need at least two big loops, the first loop update those
 * transitive value accordingly, while the second loop will reset them to 0 or 1
 * 
 * The idea used in sol1 is using 2 and 3. For those cells that live now but die later, will be reassigned to 2.
 * For those cells that dead now but live later, will be reassigned to 3
 * for cells live in next round, now we have two values 3 1
 * for cells dead in next round, now we have two values 2 0
 * So, we just need to use %2 in second loop, then we can easily reset value to 0 or 1
 * 
 * Each time we check the 8 neighbors of current cell, and if the value in neighbor is 1 or 2, we will let count ++
 * After got the count, if we found current cell will change status in next round, then we will assign it to 3 or 2 accordingly
 * 
 * Sol1 uses two variables 2 and 3 to mark transitive cells
 * Sol2 uses bit manipulation, i.e. bit at 1 to mark status in next round
 * 
 * Sol1 and sol2 have little difference, both are good solution
 * 
 * @author hpPlayer
 * @date Oct 4, 2015 6:34:40 PM
 */
public class Game_of_Life_p289_sol1 {
    public void gameOfLife(int[][] board) {
        //8 directions
        int[] xOffset = {0, 0, 1, -1, 1, 1, -1, -1};
        int[] yOffset = {1, -1, 0, 0, 1, -1, 1, -1};
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                int count = 0;//for each cell, we will count its neighbors
                for(int z = 0; z < 8; z++){//8 directions
                    int newX = i + xOffset[z];
                    int newY = j + yOffset[z];
                    if(newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length){
                        //boundary check
                        //1 is cell that keeps live next round, 2 is cell live now but dead next round
                        //both of them are live cell now
                        if(board[newX][newY] == 1 || board[newX][newY] == 2){
                            count ++;
                        }
                    }
                }
                
                //we only care about cells that needs change value
                //first is the dead cell that will live next round
                if(count == 3 && board[i][j] == 0) board[i][j] = 3;
                //second is the live cell that will die next round
                if((board[i][j] == 1) && (count > 3 || count < 2)) board[i][j] = 2;
            }
        }
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                //reset value for those cells in trasition state
                //3 will be set to 1 while 2 will be set to 0
                board[i][j] %= 2;
            }
        }
    }
}
