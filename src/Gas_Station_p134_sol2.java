/**
 * Observation solution
 * 
 * This solution mainly depend on the observation. We found that if index j is the first index we can't reach from start index, then
 * all indexes between start and j cannot reach j as well. But our task is to travel around the circuit once, so we must reach j during the trip.
 * So if we want to reach index j, we have to start from j + 1. We keep doing this until we reach the last index that we can't reach. If we can travel
 * around the circuit once, then the index after it must has enough gas to fill all gaps we found before. So this solution is similar to sol1, where
 * we are searching for the maxGap
 * 
 * @author hpPlayer
 * @date Oct 26, 2015 4:08:00 PM
 */
public class Gas_Station_p134_sol2 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int remainGas = 0;//track the accumulative gas in tank, here it only used to check if we are able to traveral a cycle
        int tank = 0;// the real gas in tank. Here we always look at valid trip, so tank should >= 0
        int start = 0;
        
        for(int i = 0; i < gas.length; i++){
            tank += gas[i] - cost[i];
            remainGas += gas[i] - cost[i];
            
            if(tank < 0){
                //if we find a gap that we cannot pass now, we cannot reach index i from any index among start and i
                //in order to reach index i, we have to at least start from index i + 1
                start = i + 1;
                tank = 0;//reset tank
            }
        }
        
        return remainGas <0 ? - 1 : start;
    }
}
