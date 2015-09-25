/*
Remove Nth Node From End of List

Given a linked list, remove the nth node from the end of list and return its head.

For example,

   Given linked list: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked list becomes 1->2->3->5.
   
Note:
Given n will always be valid.
Try to do this in one pass.
*/


/**
 * Two pointer problem
 * 
 * The tricky part is indexing.
 * 
 * To remove a node, we need get the prev node of target node.
 * In the original problem, if n = 1, then we will remove tail node. 
 * So actually we will remove the node (n-1) away from tail. And the prev node should be n node away from tail.
 * Thus we use two pointers, the gap between two pointer is just n nodes away. We let the first pointer reach tail, then 
 * the second pointer will be n nodes away from tail, which is exactly the prev node of target node.
 * Since the problem guarantees the input n is valid, we can simply remove target node by assigning prev node's next = prev node's next.next
 * This is the basic idea. Since we may remove head, we use a dummy node to help us find the new head of list in such case.
 * @author hpPlayer
 * @date Sep 24, 2015 10:07:03 PM
 */
public class Remove_Nth_Node_From_End_of_List_p19_sol1 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        
        //use dummy node, in case we will remove head
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode prev = dummy;
        ListNode curr = dummy;
        
        //while curr has not reach tail
        while(curr.next != null){
            //we will start move prev node, when the gap between prev and curr is n
            //so their gap will always be n, if curr reach tail, then prev will be n nodes from tail(inclusive)
            //while the delete node is n-1 nodes away from tail, so the prev node now points to the prev node 
            //of delete node, since "Given n will always be valid", we can safely remove delete node, by skip it,
            //and let prev.next points to prev.next.next
            
            if(n <= 0){
                prev = prev.next;
            }
            
            curr = curr.next;
            n --;
        }
        
        prev.next = prev.next.next;
        
        return dummy.next;
    }
}
