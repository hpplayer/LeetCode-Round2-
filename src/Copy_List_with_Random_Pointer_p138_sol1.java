import java.util.*;

/*
Copy List with Random Pointer

A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.
*/

/**
 * HashMap problem
 * 
 * The tricky part is to get rid of stack overflow problem. To solve that, it is better to use iterative solution in sol2
 * 
 * The basic idea is simple, we just scan the list and use a Hashmap to store nodes that we have made a copy. If we have made a copy, then 
 * just attach the copy node to proper position. If we have not made a copy, then we will make a copy and put it in HashMap.
 * Sol1 is recursive solution, there are two ways we made a copy. First one is scanning the whole list, so each node will have a copy in HashMap
 * then we assign them to current node during backtracking. Second one is made a copy of next/random node before visiting next node. Both of them
 * can be an AC solution. But I would not recommend recursive solution for this problem as we put too much load on stack, and it can easily cost
 * stack overflow problem
 * 
 * Remark:
 * 1) recursion may get stack overflow problem when the list is long. There are several ways to reduce the load on stack: a) change
 * from hashMap to Map b) declare hs as a global variable instead of argument, etc. Or we can use iterative way instead.
 * 2) This problem is similar to problem Clone Graph (p133), where we also need a HashMap to store the pair of original node and 
 * cloned node
 * 3) since next/random node can be null, we need check if we have a such node before make a copy !!!!!!!!!!!!!!
 * 
 * Sol1 provides a recursive solution that uses a HashMap to store the pair of original and copy node
 * Sol2 provides an iterative solution that uses similar algorithm, but it reduces the load on stack. So sol2 is recommended
 * 
 * @author hpPlayer
 * @date Oct 23, 2015 11:39:03 PM
 */

public class Copy_List_with_Random_Pointer_p138_sol1 {
    public RandomListNode copyRandomList(RandomListNode head) {
    	if(head == null) return null;
        return DFS(head, new HashMap<RandomListNode, RandomListNode>());
    }
    
    public RandomListNode DFS(RandomListNode head, HashMap<RandomListNode, RandomListNode> hs){
        if(!hs.containsKey(head)){
            hs.put(head, new RandomListNode(head.label));
        }
        
        RandomListNode curr = hs.get(head);
        
        if(!hs.containsKey(head.random)){
            hs.put(head.random, new RandomListNode(head.random.label));
        }
        
        curr.random = hs.get(head.random);
        
        curr.next = DFS(head.next, hs);
        
        return curr;
    }
}
