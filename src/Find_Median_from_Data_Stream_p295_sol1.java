import java.util.*;
/**
 * PriorityQueue + observation
 * 
 * The code is simple but it is hard to get the logic. We need to balance the number in two queues, and the logic is not easy to understand
 * 
 * Basically we will use two ques  to hold numbers, one max que holds this first half of numbers and one min que holds the second half of numbers
 * So the top number in two ques will be target numbers for medians.
 * 
 * For each new input, we first it into our max que. then we poll the max one from max que to min que. So this is like we sort the whole previous
 * inputs. But If we keep doing this, all numbers will be stored in min que, so we still need to return number back to max que. When shall we 
 * return number? only when we get odd number of inputs. So in even inputs, two ques hold same number of inputs and in odd inputs, max ques will
 * hold one more input. 
 * 
 * Remark:
 * 1. In this solution we use a trick to implement max que. We just negate the inputs, instead of write a new comparator()
 * 2. Since we negate the input to implement max que, we have to be careful when using number in max que!!!!!!!!!
 * 
 * @author hpPlayer
 * @date Oct 24, 2015 8:44:50 PM
 */

public class Find_Median_from_Data_Stream_p295_sol1 {
	public static void main(String[] args){
		Find_Median_from_Data_Stream_p295_sol1 sol = new Find_Median_from_Data_Stream_p295_sol1();
		sol.addNum(1);
		sol.addNum(2);
		System.out.println(sol.findMedian());
		sol.addNum(3);
		System.out.println(sol.findMedian());
	}
	
    //max que is used to hold first half of sorted nums
    private PriorityQueue<Integer> max = new PriorityQueue<Integer>();
    //min que is used to hold second half of sorted nums
    private PriorityQueue<Integer> min = new PriorityQueue<Integer>();
    
    // Adds a number into the data structure.
    public void addNum(int num) {
        //first add to first half que
        max.offer(-num);   
        
        //then add the max number in first half to second half, then we poll the smallest number from second half
        //this will make numbers in two ques are balanced
        min.offer(-max.poll());
        
        //we only poll number from min and add it to max when we have odd inputs
        //so we can keep both que hold numbers
        if(min.size() > max.size()){
            max.offer(-min.poll());
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        if(min.size() != max.size()){
            //odd inputs, we poll the extra number from min que to max que
            //so the top of max que is the median
            return -max.peek();
        }else{
            //even case, tops of min and max are median, get the average
            return (min.peek() - max.peek())/2.0;
        }
    }
}

//Your MedianFinder object will be instantiated and called as such:
//MedianFinder mf = new MedianFinder();
//mf.addNum(1);
//mf.findMedian();