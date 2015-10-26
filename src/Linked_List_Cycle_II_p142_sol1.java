/*
Linked List Cycle II

Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Note: Do not modify the linked list.

Follow up:
Can you solve it without using extra space?
*/

/**
 * Math + LinkedList problem
 * 
 * The tricky part is to find the way we can get the entry point of cycle
 * 
 * Assume the distance from head to entry point is L1 
 * Assume the distance from entry point to the meeting point in cycle is L2
 * Assume the length of cycle is C, and fast pointer has looped the cycle n times before meeting slow pointer
 * Then we will have :
 * 
 * 2 * (L1 + l2) = L1 + l2 + n * C
 * L1 + l2 = n * C 
 * L1 = (C - L2) + (n-1) * C
 * 
 * We found the distance from head to entry point equals the distance from meeting point to entry point
 * 
 * So our basic idea is firstly using fast and slow pointer to scan the list. If they meet, we know there must be a loop, otherwise
 * return null. After they meet, we reset slow pointer to head, and let fast pointer moves 1 step each time. The next meeting point
 * will be entry point of cycle
 *    
 * 
 * @author hpPlayer
 * @date Oct 26, 2015 4:50:10 PM
 */
public class Linked_List_Cycle_II_p142_sol1 {
    public ListNode detectCycle(ListNode head) {
        //boundary check
        if(head == null) return null;
        
        ListNode slow = head;
        ListNode fast = head;
        
        //move fast pointer 2x times than slow pointer
        //if there is a cycle, they will meet
        //otherwise, fast will reach tail node(null), then we just return null
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            
            if(slow == fast){
                //if they meet, then we know there is a cycle
                
                //we reset slow to head, and let fast pointer moves one step each time
                slow = head;
              //the entry point of cycle will be their next meeting point based on the equation we get above
                while(slow != fast){
                    slow = slow.next;
                    fast = fast.next;
                }
                
                return slow;
            }
        }
        
        return null;
    }
}
