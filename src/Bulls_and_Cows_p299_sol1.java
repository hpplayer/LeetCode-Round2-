/*
Bulls and Cows

You are playing the following Bulls and Cows game with your friend: You write a 4-digit secret number and ask your friend to guess it,
each time your friend guesses a number, you give a hint, the hint tells your friend how many digits are in the correct positions (called "bulls")
and how many digits are in the wrong positions (called "cows"), your friend will use those hints to find out the secret number.

For example:

Secret number:  1807
Friend's guess: 7810
Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)
According to Wikipedia: "Bulls and Cows (also known as Cows and Bulls or Pigs and Bulls or Bulls and Cleots) is an old code-breaking mind or paper
and pencil game for two or more players, predating the similar commercially marketed board game Mastermind.
The numerical version of the game is usually played with 4 digits, but can also be played with 3 or any other number of digits."

Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows,
in the above example, your function should return 1A3B.

You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.
*/

/**
 * HashTable problem
 * 
 * The tricky part is to count the number of chars that in incorrect position, we use counterB to count the number of such pairs
 * 
 * In this solution, we use a count[] to count the appearance of each digit
 * digit from secret will increase the cell value
 * digit from guess will decrease the cell value
 * 
 * say now we are looking at digit a in secret and digit b in guess
 * if we found count[a] < 0, then it means we can pair a prev char in guess with digit a, so counterB ++
 * if we found count[b] > 0, then it means we can pair a prev char in secret with digit b, so counterB ++ 
 * 
 * So the basic idea is that we update count[] based on digits from secret and guess, and if we found above cases, we will increase counterB.
 * Of course, if we found curr a and curr b can pair, then we found a perfect pair, we will increase counterA accordingly
 * 
 * @author hpPlayer
 * @date Oct 30, 2015 5:35:09 PM
 */


public class Bulls_and_Cows_p299_sol1 {
    public String getHint(String secret, String guess) {
        //table records the appearance of a digit
        //digit from secret will increase the counter
        //digit from guess will decrease the counter
        int[] count = new int[10];
        
        int counterA = 0, counterB = 0;
        
        for(int i = 0; i < secret.length(); i++){
            int a = secret.charAt(i) - '0', b = guess.charAt(i) - '0';
            if( a == b){
                //accurate match (same digit with same position)
                counterA ++;
            }else{
                
                //if prev part of guess has curr digit in secret
                //then we found a pair that has same digit with different position
                if(count[a] < 0) counterB ++;
                
                //if prev part of secret has curr digit in guess
                //then we found a pair that has same digit with different position                
                if(count[b] > 0) counterB ++;
                
                count[a] ++;
                count[b] --;
            }
        }
        
        return counterA + "A" + counterB + "B";
    }
}
