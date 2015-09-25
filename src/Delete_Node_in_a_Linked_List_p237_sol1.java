/**
 * Linkedlist problem
 * 
 * The tricky part is to come up with the idea of just changing one node.
 * 
 * We don't need to change all nodes after delete node, actually, we can use a smart trick to do that.
 * We just let delete node become the same with its next node, that means assign the value and next pointer from next node.
 * Then we are done. So instead of deleting given node, we actually make delete node become its next node, then delete its next node.
 * By doing that, we found the prev node and current node, so we can delete node as usual.
 * 
 * @author hpPlayer
 * @date Sep 24, 2015 12:45:13 PM
 */
public class Delete_Node_in_a_Linked_List_p237_sol1 {
    public void deleteNode(ListNode node) {
        //we were told the given node will not be tail node 
        if(node.next == null) return;
        
        //we make delete node become next node, then delete next node
        node.val = node.next.val;
        node.next = node.next.next;
    
    }
}
