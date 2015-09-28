/*
Reverse Nodes in k-Group

Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
*/

/**
 * Linked List problem., this is recursive solution
 * 
 * The idea is to be clear with the logic, we need get the updated next node from next recursion and check if there is enough nodes in current group
 * 
 * This is the recursive approach, each recursion will reverse nodes in a group.
 * We don't need to care about the node before group, since we have backtrack
 * But we do need to care about the node after group, since we may need to reverse the next groups, and we don't know what will be updated node.
 * So before we reverse our group, we firstly call the next recursion on the node after group, and get the updated next node
 * This updated next node is supposed to be append after the the first node in current group due to reverse, so we set prev = updated next node
 * and set curr = head, then we can reverse the list as general list reverse problem. After we done that we just return the new updated head 
 * node in current group (the old last Node) to the last recursion
 * 
 * @author hpPlayer
 * @date Sep 27, 2015 8:02:34 PM
 */
public class Reverse_Nodes_in_k_Group_p25_sol1 {
	public static void main(String[] args){
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
		ListNode c = new ListNode(3);
		a.next = b;
		b.next = c;
		ListNode curr = new Reverse_Nodes_in_k_Group_p25_sol1().reverseKGroup(a, 2);
		while(curr != null){
			System.out.println(curr.val + " ");
			curr = curr.next;
		}
	}
    public ListNode reverseKGroup(ListNode head, int k) {
        //if we don't need reverse the group
        if(k <= 1) return head;
        
        ListNode curr = head;
        //look for the k+ 1 nodes, i.e. the first node in next group
        for(int i = 0; i < k; i++){
            if(curr == null) return head;//if we don't have k nodes in group, we return it without change
            curr =curr.next;
        }
        
        //look for the updated first node in next group
        ListNode newNext = reverseKGroup(curr, k);
        
        ListNode prev = newNext;//append this curr node to head node, so head node becomes the last node in current group
        curr = head;
        
        //reverse the node
        for(int i = 0; i < k; i++){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        return prev;//prev is the last node in current group, which is the new first node in updated group
    }
}
