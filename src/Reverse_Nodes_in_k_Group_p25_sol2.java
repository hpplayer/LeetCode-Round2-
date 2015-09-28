/**
 * Iterative solution
 * 
 * The idea is very similar to recursive solution. We will reverse nodes in each group.
 * As talked before, we need to know two nodes before reversing each group: the last node before group and the first node after group.
 * Since this time we attack the problem in iterative way, we can easily get the node before group, which now we call "prev"
 * We will try to reverse current k groups, then append the updated head in current group to the "prev" from last group.
 * For the node after group, this time, we won't do it recursively, we just append the original first node after group to the 
 * newly updated tail node. Then we will update "prev" to this new updated tail node, and reverse next group, from there, we will get
 * the updated first node in next group, then append it to the updated "prev". Then do it iteratively until we have scanned all nodes.
 * 
 * 
 * @author hpPlayer
 * @date Sep 27, 2015 8:23:01 PM
 */
public class Reverse_Nodes_in_k_Group_p25_sol2 {
	public static void main(String[] args){
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
		ListNode c = new ListNode(3);
		a.next = b;
		b.next = c;
		ListNode curr = new Reverse_Nodes_in_k_Group_p25_sol2().reverseKGroup(a, 2);
		while(curr != null){
			System.out.println(curr.val + " ");
			curr = curr.next;
		}
	}
	
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        //prev is the last node before each k group
        ListNode prev = dummy;
        while(prev != null){
            prev.next = reverseInGroup(prev.next, k);
            //update prev to the last node in current group, which would be the last node before next group
            //Each prev node is k nodes away   
            for(int i = 0; i < k && prev != null; i++){
                prev = prev.next;
            }
        }
        
        
        return dummy.next;
    }
    
    public ListNode reverseInGroup(ListNode head, int k){
        ListNode NextNode = head;
        //get the first node after current group
        for(int i = 0; i < k; i++){
            //not enough k nodes
            if(NextNode == null) return head;
            NextNode = NextNode.next;
        }
        
        ListNode prev = NextNode;
        ListNode curr = head;
        for(int i = 0; i < k; i++){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        return prev;
    }
}
