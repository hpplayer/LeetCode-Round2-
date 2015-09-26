/*
Candy

There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?
*/

/**
 * Peak problem
 * 
 * First of all, don't be afraid of the lengthy code below, the logic is not so complicated and I just added bunch of comments
 * The difficulty is to observe that the descending part is the key. In two pass solution, we have to go through the array two times
 * since we don't know the right part of each cell in one pass. This solution catches this point and use a variable "desLen" to let
 * us can find the right part of each cell and calculate the value with math equation.
 * 
 * Because of descending rank, we can't safely say current number is the final candy, we may have a very long descending tail, then
 * our current number will actually increase a lot because of the descending tail. So our strategy is to track the descending tail.
 * If our current sequence is ascending or flat, we just increase the candy accordingly, and we always record current peak
 * But if current sequence is descending, then we will begin track it. then after descending is ended, we will look back and increase
 * all previous related candies accordingly. Assume the tail of descending part needs candy 1, we look back and add 1 more candy to 
 * the previous cell(like a backward ascending peak), finally we will get a new peak. Then we compare it with record peak, and add candies
 * to record peak if new peak is higher.
 * 
 * Since we always calculate descending part in next ascending part, after the loop is done. We should manually check if the last part
 * is descending, if it is, then we will manually calculate it.
 * 
 * 
 * The problem can also be solved by two-pass algorithm, but it is trivial, so I did not list it here
 * @author hpPlayer
 * @date Sep 25, 2015 4:43:33 PM
 */
public class Candy_p135_sol1 {
    public int candy(int[] ratings) {
        
        if(ratings.length == 0) return 0;//we don't have child
        
        int desLen = 0;//descending length
        int peak = 1;//inital peak = 1
        int result = 1;//result = 0
        
        for(int i = 1; i < ratings.length; i++){
            if(ratings[i] < ratings[i-1]){
                //descending part
                desLen ++;
            }else{
                //if we got descending part ahead, then we need to finish and calculate it first
                if(desLen > 0){
                    
                    //desLen start from 1, so current desLen is the required candy in last cell
                    result += (1 + desLen) * desLen/2;
                    
                    //we got a new peak, if previous peak is lower than new peak, then update it  
                    if(desLen + 1 > peak){
                        result += desLen + 1 - peak;
                    }
                    
                    desLen = 0;
                    peak = 1;//the last cell contains candy 1, and it will be the start of next part
                }
                
                //update prev if needed
                peak = ratings[i] == ratings[i-1]? 1 : peak + 1;
                
                result += peak;
            }
        }
        
        //we always finish and calucalte descending part in next ascending part, but if the last part
        //of input array is descending, then we have to manually calculate it
        
        if(desLen > 0){
            
            //desLen start from 1, so current desLen is the required candy in last cell
            result += (1 + desLen) * desLen/2;
            
            //we got a new peak, if previous peak is lower than new peak, then update it  
            if(desLen + 1 > peak){
                result += desLen + 1 - peak;
            }
        }        
        
        return result;
    }
}
