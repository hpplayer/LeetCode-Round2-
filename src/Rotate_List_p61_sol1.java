/**
 * I would call this problem cyclic LinkedList problem
 * 
 * The difficult part is to come up with idea of creating a cycle and realize the range of k
 * 
 * We could solve this problem by splitting the list, then appending first half to the tail of first half. But we really don't need to do that
 * When see the title rotate, we can link it with a cycle. We found the result list can actually be produced by creating a cyclic list, then
 * cut in a different spot. To find the cut point, we have to know the size of list, then do calculation with k. We don't know what k would be.
 * If k is larger than size, then it means we will rotate more than one loop, which is redundant. So after we get size, we use mod operation, to
 * get the real k, then the cut pointer is just between index size- k and size - k - 1. So we can just set the next pointer of node at
 * size - k - 1 to be null to cut the loop, then return size - k as the new head
 *  
 * @author hpPlayer
 * @date Sep 24, 2015 12:18:13 AM
 */
public class Rotate_List_p61_sol1 {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null) return head;
        
        int size = 1;//since our loop will break at tail node, so we count from 1
        
        ListNode curr = head;
        //found tail node
        while(curr.next != null){
            curr = curr.next;
            size ++;
        }
        
        k = k%size;//get real k is k > size
        
        curr.next = head;//form a cycle, so we can create a new list easily
        
        //move to the last node of first part
        //move size - k steps reach newhead, like move 3 steps from 1, we get 4 
        for(int i = 0; i < size - k - 1; i++){
            head = head.next;
        }
        
        ListNode newHead = head.next;
        head.next = null;
        
        return newHead;
    }
}
