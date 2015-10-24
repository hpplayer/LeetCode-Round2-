import java.util.*;

/**
 * HashMap problem
 * 
 * This solution is similar to sol1, but now I convert it to iterative version so it will reduce the load on stack.
 * 
 * The basic idea is using a HashMap to store the copied RandomListNode. We are doing copy while scanning the list. If we found we do not have 
 * a copy of random/next node, but original node does has such node, then we will make a copy, store it to hashmap and attach it to current copy
 * node
 * 
 * Remark:
 * Remember to copy the node only when current original node does have such node. In other words don't make a copy of null node at put it to hashMap
 * 
 * 
 * @author hpPlayer
 * @date Oct 24, 2015 12:18:01 AM
 */

public class Copy_List_with_Random_Pointer_p138_sol2 {
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) return null;
        
        //hs map stores the relation of original and copy pair
        HashMap<RandomListNode, RandomListNode> hs = new HashMap<RandomListNode, RandomListNode>();
        
        RandomListNode node = new RandomListNode(head.label);
        hs.put(head, node);
        
        //scan the linkedlist with curr pointer
        //the pointer in copy list is node as we declared above
        RandomListNode curr = head;
        
        while(curr != null){
            if(curr.next != null){
                //copy curr.next if it has one
                if(!hs.containsKey(curr.next)){
                    hs.put(curr.next, new RandomListNode(curr.next.label) );
                }
                
                node.next = hs.get(curr.next);
            }
            
            if(curr.random != null){
                //copy curr.random if it has one
                if(!hs.containsKey(curr.random)){
                    hs.put(curr.random, new RandomListNode(curr.random.label) );
                }
                
                node.random = hs.get(curr.random);
            }            
            
            //done with current Node move pointer in both lists forward
            curr = curr.next;
            node = node.next;
        }
        
        
        return hs.get(head);
    }
}
