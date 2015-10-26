/*
Intersection of Two Linked Lists

Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 ¡ú a2
                   ¨K
                     c1 ¡ú c2 ¡ú c3
                   ¨J            
B:     b1 ¡ú b2 ¡ú b3
begin to intersect at node c1.


Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.
*/

/**
 * LinkedList problem
 * 
 * The tricky part is to solve the problem without using len counter or difference counter
 * 
 * Actually we don't care about the "value" of difference, we just want to make sure two pointers reach the intersection node at the same time.
 * We can use two iterations to do that. In the first iteration, we will reset the pointer of one linkedlist to the head of another linkedlist
 * after it reaches the tail node. In the second iteration, we will move two pointers until they points to the same node. Our operations in first
 * iteration will help us counteract the difference. So if two linkedlist intersects, the meeting point in second iteration must be the intersection point.
 * If the two linked lists have no intersection at all, then the meeting pointer in second iteration must be the tail node of both lists, which is null
 * 
 * @author hpPlayer
 * @date Oct 26, 2015 1:07:27 PM
 */
public class Intersection_of_Two_Linked_Lists_p160_sol1 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //boundary check
        if(headA == null || headB == null) return null;
        
        ListNode a = headA;
        ListNode b = headB;
        
        //if a & b have different len, then we will stop the loop after second iteration
        while( a != b){
        	//for the end of first iteration, we just reset the pointer to the head of another linkedlist
            a = a == null? headB : a.next;
            b = b == null? headA : b.next;    
        }
        
        return a;
    }
}
