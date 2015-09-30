/*
Remove Duplicates from Sorted List II

Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.
*/

/**
 * LinkedList problem w/ recursive solution
 * 
 * The tricky part is to deal with current node if it needs to be removed
 * 
 * In problem Remove_Duplicates_from_Sorted_List(p83), if found duplicates, we can simply remove nodes w/t constraints
 * But here if we remove duplicates in recursions, then in the last recursion where we have only one duplicate node left
 * we will never know whether it needs to be removed. So the idea in this version is changed, we will firstly scan all duplicates
 * in one recursion and remove them all if necessary then pass a "clean" node, which is at least node same with our removed duplicates
 * to next recursion and further check. If we found next and current nodes are not same, then at least current node should be 
 * included in final list, but we don't know if next node is clean, so we will pass next node to next recursion for further check
 * and append returned nodes to current node
 * 
 * Sol1 is recursive solution
 * Sol2 is iterative solution
 * 
 * @author hpPlayer
 * @date Sep 30, 2015 12:53:58 AM
 */
		
public class Remove_Duplicates_from_Sorted_List_II_p82_sol1 {
    public ListNode deleteDuplicates(ListNode head) {
        //boundary case:
        //1) differed from previous verison, now we may have null input if we have removed all elments before it
        //2) Since we still want to compare next.val, we will try to return non-null node if possible
        if(head == null || head.next == null) return head;
        
        ListNode next = head.next;
        
        if(head.val == next.val){
            //if current head is duplicate, we will remove all duplicates and return a non-duplicate value in
            //later list
            while(next != null && head.val == next.val){
                next = next.next;
            }
            
            //check next node in next recursion
            return deleteDuplicates(next);
        }else{
            //at least now we guarantee head is not duplicate, but we don't know if next is duplicate,
            //so we will check next node in next recursion 
            
            head.next = deleteDuplicates(next);
            return head;
        }
        
    }
}
