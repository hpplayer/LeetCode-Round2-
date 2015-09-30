/*
Remove Duplicates from Sorted List

Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.
*/

/**
 * LinkedList problem + recursive solution
 * 
 * The problem can be solved by iteration (use two while loop, inner loop jumps all consecutive duplicate node, while outer loop scan array)
 * I put recursive solution here because it let us think list as reverse way.
 * 
 * In iterative solution, we will delete next nodes if they are duplicates
 * But, in recursive solution, we visit the list backward, so we will delete current node instead. The returned next node should be a list
 * without duplicates, so we don't want throw it away and that's why we remove current node instead of next node if there exists duplicates
 * 
 * @author hpPlayer
 * @date Sep 30, 2015 12:23:07 AM
 */
public class Remove_Duplicates_from_Sorted_List_p83_sol1 {
    public ListNode deleteDuplicates(ListNode head) {
        //1)head == null is boundary case, like input head is null, just return it
        //2)we put head.next == null here, because we want to sure the return node is not null and has value
        if(head == null || head.next == null) return head;
        
        //return next node
        ListNode next = deleteDuplicates(head.next);
        
        //next.val = head.val, since we are visiting backward, so we need remove head node instead of next
        if(next.val == head.val){
            head = next;
        }else{
            head.next = next;
        }
        
        return head;
    }
}
