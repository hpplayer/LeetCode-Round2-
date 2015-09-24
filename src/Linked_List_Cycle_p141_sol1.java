/**
 * Two pointer problem.
 * 
 * I put it here for memorization. We firstly let two pointer start running, then compare them after they move, so we will
 * not stuck on the initial case !
 * 
 * @author hpPlayer
 * @date Sep 23, 2015 6:16:40 PM
 */
public class Linked_List_Cycle_p141_sol1 {
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;//slow pointer has to move, since the start of loop may not just begin at index 0 
            if(fast == slow) return true;
        }
        return false;
    }
}
