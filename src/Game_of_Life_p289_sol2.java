/**
 * Since status 0, 1 only use bit at index 0, we can use bit at index 1 to store the status in next round
 * main part of sol2 is very similar to sol1, but now we don't care about the transitive cells. We will set all cells 
 * that live next round have 1 in bit index 1, while set all cells that die next round have 0 in bit index 1
 * 
 * @author hpPlayer
 * @date Oct 4, 2015 6:54:52 PM
 */
public class Game_of_Life_p289_sol2 {
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
                        if((board[newX][newY] & 1) == 1){//check live status now, i.e. get bit at index 0
                            count ++;
                        }
                    }
                }
                //we will set bit at index 1 to 1 in two cases 1) count == 3 or 2) count == 2 && board[i][j] = 1
                if((count == 3) || (count == 2) && board[i][j] == 1) board[i][j] |= 2;//set bit index 1 to be 1
            }
        }
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
            	//rightshift 1 bit to get next status
                board[i][j] >>= 1;
            }
        }
    }
}
