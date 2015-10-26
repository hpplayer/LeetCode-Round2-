/*
Insertion Sort List

Sort a linked list using insertion sort.
*/

/**
 * LinkedList problem
 * 
 * The tricky part is to know the insertion sort and be clear with the logic
 * 
 * The insertion sort is a naive sorting algorithm. For each new input, we just scan from the head to find the proper spot to insert it.
 * Here since we don't know what the final first node will be, we will use a dummy node to connect with the first node. We use two pointers to search
 * for the insertion spot. Curr pointer points to the new input ListNode, prev pointer points to ListNode we already sorted. We will move prev pointer
 * to the spot where the next node is larger than new input node and curr node is smaller than the new input node, or prev.next is null, i.e. we have to
 * insert new node in the tail. After get such spot, we just insert next node into here, then reset prev pointer to head to be ready for next insertion.
 * 
 * Remark:
 * 1) Time complexity is O(n^2) and space complexity is O(1)
 * 
 * @author hpPlayer
 * @date Oct 26, 2015 2:37:01 AM
 */
public class Insertion_Sort_List_p147_sol1 {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        ListNode curr = head;
        
        while(curr != null){
            //back up next node 
            ListNode next = curr.next;
            
            while(prev.next != null && prev.next.val < curr.val){
                //search for the spot that the next node is larger than curr.val and prev.val is < curr.val
                //Notice: Although we always start with dummy node, we never compare dummy node with cur Node
                prev = prev.next;
            }
            
            //find the spot, insert the node
            curr.next = prev.next;
            prev.next = curr;
            
            //reset prev to dummy, so next insertion will still start from head
            prev = dummy;
            
            //insertion is done, move to next node
            curr = next;
        }
        
        return dummy.next;
    }
}
