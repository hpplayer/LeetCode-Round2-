/**
 * Divide-and-Conquer, or we call it bottom-up merge sort
 * 
 * Each time we merge two list from lists, so after each loop, we only have half of lists remaining.
 * Thus we can finish merge K lists in Klog(K) time. Basically, each time we will merge a list in the beginning and a list in the end,
 * and put the merged list in the beginning, then move to next pair
 * 
 * In the first, I try to merge list one by one, like firstly merge list 1 and 2, then merge the merged list of 1 and 2 with 3,
 * However, this approach does not behave like merge sort where we always try to merge the list with similar size. In my approach,
 * my algorithm is more like brute force, like we will insert a new Node into previous sorted list, the worst time will be O(n^2)
 * However, merge sort will always try to merge two lists with similar size. So the time complexity is always O(nlogn)
 * 
 * This problem can also be solved by using a min-heap, and the solution is trivial, so I do no list it here.
 * 
 * @author hpPlayer
 * @date Sep 19, 2015 2:12:18 PM
 */
public class Merge_k_Sorted_Lists_p23_sol1 {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        int end = lists.length-1;
        
        //end > 0 means we have at least 2 elements in list, so we can merge them together
        while(end > 0){
            int start = 0;
            while(end > start){
                //we will always update list's front part, and the last merged final list will be in lists[0]
                lists[start] = merge2Lists(lists[start], lists[end]);
                start ++;
                end --;
            }
        }
        
        return lists[0];
    }
    
    public ListNode merge2Lists(ListNode a, ListNode b){
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        while(a != null && b != null){
            if(a.val < b.val){
                prev.next = a;
                prev = a;
                a = a.next;
            }else{
                prev.next = b;
                prev = b;
                b = b.next;
            }
        }
        
        if( a != null) prev.next = a;
        if( b != null) prev.next = b;
        
        return dummy.next;
    }
}
