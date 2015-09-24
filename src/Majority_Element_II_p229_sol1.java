import java.util.*;

/**
 * Voting algorithm
 * 
 * The difficulty is checking corner cases, there are several corner cases like initial value of candidate can also be treated as candidate, then if
 * we have another candidate has same value with it, we actually have two candidates with same value.
 * 
 * This algorithm can be split into two parts: 1) find majority 2) check occurrences
 * 
 * The problem states that we would have elements that appear more than n/3 times, that means we would not have 3 of such elements, we at most would 
 * have 2 of them. So here our majority would be 2 or 1. If we split the array into three segments, assume elements each segment has one value. So,
 * those three elements would appear exactly n/3 times, but now we define majority of them to appear more than n/3 times, so only two of them could be
 * majority by using the elements in the third segment.
 * 
 * We firstly scan the array, and update majority. Be notice about the candidate, we don't want
 * one element would be assigned to different candidates. If we found an element that is not belong to candidate1 and candiate2, then we would rather 
 * decrease the vote1 and vote2 at the same time, otherwise it would be unfair. 
 * 
 * In our next step, we need to check the occurrences of candidates, since if we only have two values in array, we would always have two candidates.
 * But one of them may appear less than n/3 times. So this step is equal important. Then we only return the candidate that appears more than n/3 times
 * as defined by the problem
 * 
 * @author hpPlayer
 * @date Sep 23, 2015 9:22:00 PM
 */

public class Majority_Element_II_p229_sol1 {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        
        int candidate1 = 0;//the initial value of candidate1/2 is not important, we only care about vote
        int candidate2 = 0;
        
        int vote1 = 0;
        int vote2 = 0;
        
        for(int num : nums){
            if(vote1 == 0){
                candidate1 = num;
                vote1 ++;
            }else if(num != candidate1 && vote2 == 0){//we don't want candidate 1&2 be the same person, so need check 
                candidate2 = num;
                vote2 ++;
            }else if(num == candidate1){
                vote1 ++;
            }else if(num == candidate2){
                vote2 ++;
            }else{//not belong to any of the candidate
                vote1 --;
                vote2 --;
            }
        }
        
        //if array only contains two values, then we must have vote1 and vote2, thus we need scan array one more time to ensure two candidates are majority
        vote1 = 0;
        vote2 = 0;
        int filter = nums.length / 3;
        
        for(int num : nums){
            if(num == candidate1){
                vote1 ++;
            }else if(num == candidate2){//important, our initial candidate2 value may has been assigned to candadite 1, in this case we only want to let candidate's vote ++, so we need else if 
                vote2 ++;
            }
        }
        
        if (vote1 > filter) result.add(candidate1);
        if (vote2 > filter) result.add(candidate2);
        
        return result;
    }
}
