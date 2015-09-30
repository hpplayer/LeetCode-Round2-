/**
 * Iterative solution
 * 
 * The tricky part is to remove all nodes that are duplicates
 * 
 * Unlike problem Remove_Duplicates_from_Sorted_List(p83), here we need to remove curr node as well if it is duplicate.
 * So it means if the head is duplicate, then we need remove it, thus we need a dummy head.
 * We will have three pointers, prev, curr, next. We will move prev only after we make sure current node is not duplicate.
 * So it seems we need an if-else statement that can tell us when to move prev pointer. In the case that current node is not duplicate,
 * we can simply move prev and check next node. In the case that current node is duplicate, then we will scan the array and search the 
 * first non-duplicate node after that, then we will append this node after prev node, and check it in next loop. So the basic idea is 
 * moving prev only when we found a non-duplicate node, otherwise we will only move curr and next pointer to search next non-duplicate node
 * 
 * 
 * @author hpPlayer
 * @date Sep 30, 2015 1:35:06 AM
 */
public class Remove_Duplicates_from_Sorted_List_II_p82_sol2 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);//since we may remove head as well,  we need dummy node
        ListNode prev = dummy;
        prev.next = head;
        ListNode curr = head;
        
        while(curr != null){
            //since we will use next.val below, we should check if next is null
            if(curr.next == null) return dummy.next;
            
            if(curr.val != curr.next.val){
                //curr is non-duplicate
                prev = curr;//move prev forward
                curr = curr.next;//check next node in next loop
            }else{
                //curr is duplicate, we need skip all duplicates
                ListNode next = curr.next;
                while(next != null && curr.val == next.val){
                    next = next.next;
                }
                //remove curr
                prev.next = next;
                curr = next;//check next node in next loop
            }
        }
        
        return dummy.next;
    }
}
