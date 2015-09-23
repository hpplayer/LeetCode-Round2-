/**
 * Implementation of stack
 * 
 * In this solution, in stead of using two stacks, we combine min and input value into one object.
 * I create an inner class Node which contains both values. Also we create it as LinkedList structure, so we can avoid the use of
 * built-in stack library. Each time we insert a new Node, we will compare its value with the min value in previous node to decide 
 * what the min value in current node. Be careful with the corner case, if the use want to pop or query an empty stack, then 
 * we should notice that, as here I will return int_max
 * 
 * @author hpPlayer
 * @date Sep 22, 2015 6:30:04 PM
 */
public class Min_Stack_p155_sol2 {
    public class Node{
        int val;
        int min;
        Node next;
        public Node(int val, int min){
            this.val = val;
            this.min = min;
        }
    }
    
    Node prev = null;//record the top node in stack

    public void push(int x) {
        if(prev == null){
            prev = new Node(x, x);
        }else{
            //we always insert new Node in front
            Node newNode = new Node(x, Math.min(x, prev.min));
            newNode.next = prev;
            prev = newNode;
        }
    }

    public void pop() {
        if(prev != null) prev = prev.next;
    }

    public int top() {
        if(prev == null) return Integer.MIN_VALUE;
        return prev.val;
    }

    public int getMin() {
        if(prev == null) return Integer.MIN_VALUE;
        return prev.min;       
    }
}
