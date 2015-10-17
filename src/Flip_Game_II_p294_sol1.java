import java.util.*;

/*
Flip Game II

You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -,
you and your friend take turns to flip two consecutive "++" into "--".
The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.

For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".

Follow up:
Derive your algorithm's runtime complexity.
*/

/**
 * Backtracking problem
 * 
 * An interesting game problem. The tricky part is to realize that the winning condition for player1 is player2 must lose.
 * 
 * The recursion itself is not tricky, we just try all possible ways to flip the input string, and check if we can guarantee to get
 * win by any move. If we can't then we will return false indicating current input will make us lose for sure. For the other player,
 * if current string make us lose for sure, then his move that produces current string can guarantee him to win this game. In the 
 * backtracking, we will return such information until we reach the top recursion. So if we found any move in top recursion can make
 * the other player lose for sure, then we will return true, otherwise return false. 
 * 
 * Since string has same effects to all players, we can use a HashMap to mark whether a string can give a guaranteed win or lose.
 * If current string has any chance that could let us flip two bits and get guaranteed victory, we will mark current string as win_string
 * If current string even after we tried all ways still cannot give us a guaranteed win, we will mark current string as lose_string
 * 
 * 
 * Remark:
 * 1. A dp way can reduce the time complexity further, but it requires understanding of game theory and not easy to understand. To save time, I
 * will not put dp solution here.
 * 2. It is hard to translate it to iterative solution (except for the dp solution above), if later I got time, I will try to do it.
 * 3. Memoization does help us save a lot of time during backtracking, remember to use it !!!!!!!!!
 * 4. For the time complexity, it is still uncertain, but it may be O(n!!) (double factorial). Since each time we will remove 2 spots
 * and make time like n * (n-2) * (n-4)
 * @author hpPlayer
 * @date Oct 16, 2015 9:49:29 PM
 */

public class Flip_Game_II_p294_sol1 {
	public static void main(String[] args){
		System.out.println(new Flip_Game_II_p294_sol1().canWin("+++++"));
	}
    public boolean canWin(String s) {
        return canWin(s, new HashMap<String, Boolean>());
    }
    
    public boolean canWin(String s, HashMap<String, Boolean> result){
        //for each play, we have to consider all possible playing ways,
        //so no boundary check here, but we can have a hashSet to record string whose format will result in a lose
        if(result.containsKey(s)){
            return result.get(s);
        }
        
        for(int i = 1; i < s.length(); i++){
            //try all possible places to flip the bit
            if(s.charAt(i-1) == '+' && s.charAt(i) == '+'){
                String newStr = s.substring(0, i-1) + "--" + s.substring(i+1);
                //Our opponent will take next step, if he can win next step by any chance, then current move
                //cannot guarantee our victory so we have to continue search
                //but if he is guaranteed to lose next step, then we will win by moving this step,
                //So if next step is false for opponent, we will return true
                //same rule applies to us as well. 
                if ( !canWin(newStr, result)){
                    result.put(s, true);
                    return true;
                } 
            } 
        }
        
        //try all moves but still can't win the game by any chance, return false
        //and add current string to deathStr
        result.put(s, false);
        return false;       
    }
}
