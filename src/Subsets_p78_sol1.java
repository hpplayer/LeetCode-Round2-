import java.util.*;

/**
 * There are at least 3 ways to solve this problem.
 * Sol1 is recursive solution. Sol2 is iterative solution. Sol3 is bit manipulation solution.
 * For me, I like Sol2 since it is very clear and simple, but both sol1 and sol3 provide great approach as well, so need to 
 * pay attention to them all.
 * 
 * It has been a long time that I didn't practice such problem, I need more practices.
 * The problem requires us to enumerate all subsets, so we will scan array and use each element as the start element to 
 * do recursive search. Our next search will only start after the caller's index, so we wouldn't have duplicates. All subsequent
 * recursive search will add all subsets start with initial caller. During the backtrack, we will list all subsets start with same
 * caller but with different subsequence elements like [1,2], [1,3].
 * 
 * @author hpPlayer
 * @date Sep 20, 2015 1:07:31 AM
 */

public class Subsets_p78_sol1 {
	public static void main(String[] args){
		int[] nums = {1,2,3};//{1,2,3,4,5,6,7,8,10,0}
		for(List<Integer> list : new Subsets_p78_sol1().subsets(nums)){
			System.out.println(list);
		}
	}
    public List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        result.add(new ArrayList<Integer>());
        
        DFS(new ArrayList<Integer>(), result, 0, nums);
        
        return result;
    }
    
    public void DFS(List<Integer> temp, List<List<Integer>> result, int index, int[] nums){
        for(int i = index; i< nums.length; i++){
            temp.add(nums[index]);
            result.add(new ArrayList<Integer>(temp));
            DFS(temp,  result, index + 1, nums);//search all subsets start with nums[index]
            temp.remove(temp.size()-1);//lets next num become the start of subsets
        }
    }
}
