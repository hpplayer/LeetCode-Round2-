/*
Reorder List

Given a singly linked list L: L0¡úL1¡ú¡­¡úLn-1¡úLn,
reorder it to: L0¡úLn¡úL1¡úLn-1¡úL2¡úLn-2¡ú¡­

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.
*/


/**
 * LinkedList problem
 * 
 * The tricky part is the zig-zag combination part. We need to observe that the reordered list always end with the node in second List.
 * In other words, our zig-zag combination is done once we are done with first list. Ex: odd case: 1 23 => 1 32, even case: 12 34 => 1 4 2 3
 * 
 * There may be many ways to do the zig-zag combination, but I think below code is the most logic clear one
 * 
 * The basic idea is simple. We firstly use slow-fast pointer to find the second half of list. Then we reverse the second half. Then
 * use zig-zag combination to merge two lists
 * 
 * Remark:
 * 1) To make the logic clear, I track the node before second half list, so I can clearly split the list into two lists
 * 2) Our zig-zag combination when ends when pointer in first list reach tail node(null). Since we always move pointer in first list first,
 * we need a mid check before looking at the second list
 * 3) Don't forget the boundary case, that we don't have node or have only one node!!!!!!!!!!
 * 
 * @author hpPlayer
 * @date Oct 26, 2015 1:45:34 PM
 */
public class Reorder_List_p143_sol1 {
	public static void main(String[] args){
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
		ListNode c = new ListNode(3);
		//a.next = b;
		//b.next = c;
		new Reorder_List_p143_sol1().reorderList(a);
		ListNode curr = a;
		while(curr != null){
			System.out.println(curr.val);
			curr = curr.next;
		}
	}
    public void reorderList(ListNode head) {
        //boundary check
        if(head == null || head.next == null) return;
        
        //use fast and slow pointer to locate the second list
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = slow;
        
        while(fast != null && fast.next != null){
        	prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        //split first and second list
        prev.next = null;
        
        //reverse second list
        ListNode pointerB = reverse(slow);
        ListNode pointerA = head;
        
        //zig-zag combination until we are done with list 1
        while(pointerA != null){
            //zig
            ListNode next = pointerA.next;
            pointerA.next = pointerB;
            pointerA = next;
            
            //check pointer in first list again, if we are done with list1, then we are done with the zig-zag
            if(next == null) return;
            
            //zag
            next = pointerB.next;
            pointerB.next = pointerA;//we let poinerB points to the next node in list1 which is pointed by pointerA now.
            pointerB = next;
        }
        
    }
    
    public ListNode reverse(ListNode head){
        //general way to reverse a Linkedlist
        ListNode prev = null;
        ListNode curr = head;
        
        while(curr != null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        return prev;
    }
}
