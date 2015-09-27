/**
 * LinkedList problem
 * 
 * The tricky part is to be clear with the logic, it is better to draw the logic on paper to avoid confusion
 * 
 * We will swap in pairs, so we need to make sure we at least have two nodes (curr!= null && curr.next != null)
 * We use prev and temp to record the node before and after the pair, so the swapped nodes can be correctly inserted 
 * into the linkedlist. Then use dummy node to find the new head
 * 
 * Sol1 is iterative solution using prev and curr pointers
 * Sol2 is recursive solution
 * @author hpPlayer
 * @date Sep 27, 2015 12:13:44 AM
 */
public class Swap_Nodes_in_Pairs_p24_sol1 {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode prev = dummy;
        ListNode curr = head;
        
        while(curr!= null && curr.next != null){
            ListNode next = curr.next;
            ListNode temp = next.next;
            
            prev.next = next;
            next.next = curr;
            curr.next = temp;
            
            prev = curr;
            curr = temp;
        }
        
        return dummy.next;
    }
}
