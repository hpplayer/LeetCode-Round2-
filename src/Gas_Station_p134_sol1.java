/*
Gas Station

There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1).
You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note:
The solution is guaranteed to be unique.
*/

/**
 * Greedy problem
 * 
 * The tricky part is observe that the unique solution can be related with maxGap
 * 
 * If there is a unique start point, then it must be the index after maxGap (the accumulative difference of gas and cost). In other 
 * words, only when we start fill the tank after the maxGap, can we overcome the maxGap.
 * 
 * If we can start from another index, then it either means the maxGap is small and we can start from anywhere or it means it is 
 * not a maxGap yet. So our solution here is to use greedy thought to find the maxGap, and the index after it.
 * 
 * Of course even we start from the index after maxGap, we may still not overcome the maxGap, so before we return the result, we 
 * need to check if remaining gas in tank is >= 0
 * 
 * Sol1 provides a solution that is more like greedy solution, where we are searching for the maxGap
 * Sol2 provides a solution that mainly based on observation but actually still follows the greedy solution.
 * 
 * Sol1 is more recommended since it is a more formal greedy solution
 * 
 * @author hpPlayer
 * @date Oct 26, 2015 3:57:09 PM
 */
public class Gas_Station_p134_sol1 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int maxGap = 0;//the max accumulative difference between gas and cost
        int remainGas = 0;//track the real-time remaining gas in tank, which can also reflect the accumulative gap
        int start = 0;
        
        for(int i = 0; i < gas.length; i++){
            remainGas += gas[i] - cost[i];
            
            if(remainGas < maxGap){
                //find a larger gap, update maxGap accordingly
                maxGap = remainGas;
                start = i + 1;
            }
        }
        
        //if remaining gas is < 0, it means the maxGap is not filled, so we return -1
        //otherwise we return start index
        return remainGas < 0? -1 : start;
    }
}
