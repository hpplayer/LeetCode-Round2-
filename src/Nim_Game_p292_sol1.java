/*
Nim Game

You are playing the following Nim Game with your friend: There is a heap of stones on the table, each time one of you take turns to remove 1 to 3 stones. The one who removes the last stone will be the winner. You will take the first turn to remove the stones.

Both of you are very clever and have optimal strategies for the game. Write a function to determine whether you can win the game given the number of stones in the heap.

For example, if there are 4 stones in the heap, then you will never win the game: no matter 1, 2, or 3 stones you remove, the last stone will always be removed by your friend.

Hint:

If there are 5 stones in the heap, could you figure out a way to remove the stones such that you will always be the winner?
*/

/**
 * Math problem
 * 
 * The solution to this problem is not hard, but I found it is very interesting, so I put it here
 * 
 * For 4 stones, it will be a dead number. Whoever first get 4 stones will be doomed.
 * So if we have 5 stones, 6 stones, 7 stones, and we are the first person to pick stone, we can simply pick stones to leave 4 stones
 * for the opponent.
 * However, if we got 8 stones, then the opponent can take stones to make only 4 stones for us.
 * We pick 1, they pick 3, then we got 4
 * We pick 2, they pick 2, then we got 4
 * We pick 3, they pick 1, then we got 4
 * So 8 is also a dead number.
 * 
 * Same rule will be applied to all number later, which is the multiple of 4.
 * 
 * So the solution is very simple. If the given num mod 4 is 0, then we lose, otherwise we win
 * 
 * @author hpPlayer
 * @date Oct 13, 2015 1:48:43 PM
 */
public class Nim_Game_p292_sol1 {
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}
