/**
 * Recursively solution
 * 
 * Each recursion, we swap 2 nodes.
 * In the first part, we recursively visit following list, then during backtrack each next recursion will return a new next node
 * We back up next = curr.next. Then we attach the new next node to curr node, then let next.next = curr, then return next as
 * new next node to upper layer recursion.
 * 
 * 
 * @author hpPlayer
 * @date Sep 27, 2015 12:19:09 AM
 */
public class Swap_Nodes_in_Pairs_p24_sol2 {
    public ListNode swapPairs(ListNode head) {
        //we don't have enough pair
        if(head == null || head.next == null) return head;
        
        ListNode next = head.next;
        
        //recursively visit following nodes, to get the new next node
        ListNode newNext = swapPairs(next.next);
        
        head.next = newNext;
        next.next = head;
        
        return next;
    }
}
