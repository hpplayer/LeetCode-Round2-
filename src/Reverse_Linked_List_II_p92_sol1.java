/*
Reverse Linked List II

Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ¡Ü m ¡Ü n ¡Ü length of list.
*/


/**
 * LinkedList problem
 * 
 * The intuitive solution that split the input list into three parts, then reverse the mid part is not acceptable, since we need
 * two pass, one pass to find the mid part, another pass to reverse it. If mid part has length of whole list, then it is exactly
 * two-pass solution which fails meeting the requirement
 * 
 * The correct solution is building reversed part on fly, so we don't need to firstly find it then reverse it.
 * How to build it on fly? We just create a sublist that represents the reverse list. For each new incoming node, we will place 
 * it in the head of sublist. Finally, we just need to attach this sublist in to correct position of input lists then we are done.
 * To correctly place sublist in position, we need know the nodes before and after it. So we use "prev" to indicate the node before 
 * reversed part, and use "curr" to find the first node after reversed part. After our sublist is built, we just connect it with 
 * those two nodes, then we are done!
 * 
 * @author hpPlayer
 * @date Oct 5, 2015 3:36:56 PM
 */
public class Reverse_Linked_List_II_p92_sol1 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        //since we may chagne head Node, we need a dummy to track the new head
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        //head and taill node for reverse part
        ListNode subHead = null;
        ListNode subTail = null;
        
        //curr and prev are normal nodes that we used to scan the list
        ListNode curr = head;
        ListNode prev = dummy;
        
        for(int i = 1; i <= n; i++){
            ListNode next = curr.next;//in case we need to reverse nodes, we need mark the next node ahead
            if(i < m){
               prev = curr;//update prev Node, prev should be the node before reversed part 
            }else if(i == m){
                //now we found the first node in reverse part, update subHead and subTail accordingly
                subHead = curr;
                subTail = curr;
            }else{
                //now we are in the reverse part, we always insert the new node in front to make it reversed
                //so it looks like we are frequently update the subHead
                curr.next = subHead;
                subHead = curr;
            }
            
            curr = next;//visit next node 
        }
        
        //now we have done the part until reverse part, what we need to do now is to place it correctly in the list
        //make its head connected to first part
        prev.next = subHead;
        //make its tail connected to third part
        subTail.next = curr;
        
        return dummy.next;//return head
    }
}
