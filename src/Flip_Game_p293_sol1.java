import java.util.*;
/*
Flip Game

You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -,
 you and your friend take turns to flip two consecutive "++" into "--".
  The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to compute all possible states of the string after one valid move.

For example, given s = "++++", after one move, it may become one of the following states:

[
  "--++",
  "+--+",
  "++--"
]
If there is no valid move, return an empty list [].
*/	

/**
 * New problem. Have not found any suggestive post. Simply scan the array and pick two consecutive "++", replace it, then continue
 * 
 * @author hpPlayer
 * @date Oct 14, 2015 11:01:20 PM
 */
public class Flip_Game_p293_sol1 {
	public static void main(String[] args){
		System.out.println( new Flip_Game_p293_sol1().generatePossibleNextMoves("++++"));
	}
    public List<String> generatePossibleNextMoves(String s) {
        List<String> result = new ArrayList<String>();
        for(int i = 0; i < s.length() - 1; i++){
            if(s.charAt(i) == '+' && s.charAt(i+1) == '+'){
                result.add(s.substring(0, i) + '-' + '-' + s.substring(i+2));
            }
        }
        return result;
    }
}
