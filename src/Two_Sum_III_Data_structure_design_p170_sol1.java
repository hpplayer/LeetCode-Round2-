import java.util.HashMap;

/*
Two Sum III - Data structure design

Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

For example,
add(1); add(3); add(5);
find(4) -> true
find(7) -> false
*/

/**
 * Hash Table problem
 * 
 * The tricky part is to be careful with boundary case (duplicates and 0) and how to balance the time on find() and add()
 * 
 * There are several ways to solve this problem. If find() calls significantly exceeds add(), then for each input, we will 
 * add all its 2 sum result to a container, so the find() will cost O(1), but we need O(n^2) space and O(n) add()
 * In general case, we can just use HashTable to stores all input number. But to cover duplicates and 0, we will store the number of
 * appearance as well. So if we found the diff + input n = target, we check if this input appears more than once, if yes, return true
 * else continue search
 * 
 * Remark
 * 1) In solution below, add() costs O(1), find()costs O(n), space complexity is O(n)
 * 
 * @author hpPlayer
 * @date Oct 17, 2015 12:43:17 AM
 */
public class Two_Sum_III_Data_structure_design_p170_sol1 {
    public static void main(String[] args){
    	Two_Sum_III_Data_structure_design_p170_sol1 sol = new Two_Sum_III_Data_structure_design_p170_sol1();
    	sol.add(0);
    	//sol.add(0);
    	sol.add(-1);
    	sol.add(+1);
    	System.out.println(sol.find(0));
    }
    //to cover duplicate and 0 case, we have to use HashMap to count the number of appearance of one number
    HashMap<Integer, Integer> hs = new HashMap<Integer, Integer>();
    // Add the number to an internal data structure.
	public void add(int number) {
	    //add() costs O(1)
	    if(!hs.containsKey(number)){
	        //first time
	        hs.put(number, 1);
	    }else{
	        //appear more than once
	        hs.put(number, hs.get(number) + 1);
	    }
	}

    // Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) {
	    //find() costs O(n)
	    for(int key : hs.keySet()){
	        int diff = value - key;
	        if(hs.containsKey(diff)){
	            if(diff == key){
	                //duplicate case
	                if(hs.get(key) > 1) return true;
	            }else{
	                //general case, contains two number that can sum up to value
	                return true;
	            }
	        }
	    }
	    
	    return false;
	}
}
