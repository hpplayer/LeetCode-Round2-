/**
 * Brain-teaser problem.
 * 
 * We need to find the 1-based index that we will have least n citations for n papers, and we want find the index to be as large as possible.
 * So our final solutions will be the boundary case that even increase it by 1, we will include papers that does not have enough citations.
 * 
 * The difficulty part is to come up with the idea of flipping the axis. The raw input array is unsorted, we want create a new array, and assigning
 * values to the new array so the new array will be sorted.
 * 
 * The new array should have range from 0 to n, which is correspondent to the range of H-index. If we don't have any paper meet requirement, then
 * the H-index = 0, if every paper meet requirement since our index is 1 based, then the H-index will be n. As discussed above, we will flip the axis
 * and assign papers to citations. For citations that are larger than n, we will assign them to the n-th cell. Then we will scan our array and find 
 * the first index that has more papers than its index, then  the index will be (i-1) + 1, (i-1) to convert index, + 1 to move index backward by 1
 * (we need the last index that papers[i] <= i)
 * 
 *  
 * @author hpPlayer
 * @date Sep 21, 2015 9:20:27 PM
 */
public class H_Index_p274_sol1 {
    public int hIndex(int[] citations) {
        int n = citations.length;
        
        //our H-index range from 0 to n (both are inclusive), 0 means none meet the requirment,
        //n means each paper meets the requirments. Note: H-index is 1 based, but can be 0 also
        int papers[] = new int[n+1];
        
        for(int i =0; i < n; i++){
            if(citations[i] >= n){//paper has citations >= n can all be treated as n
                papers[n]++;
            }else{
                papers[citations[i]]++;
            }
        }
        
        if(papers[n] >= n) return n;//if even n citations has already include enough papers, we will return n
        
        //we scan array from tail to end, so we can accumulate high citation's papers to low citation's papers
        for(int i = n -1; i >= 0; i--){
            papers[i] += papers[i+1];
            //if we found the first citations that has more papers than its index, then its index i if converted 
            //to n-exclusive based index, the index will be (i-1) + 1, (i-1) to convert index, + 1 to 
            //move index backward by 1 (we need the last index that papers[i] <= i)
            if(papers[i] >= i) return i;
        }
        
        return -1;
    }
}
