import java.util.*;

/**
 * Stack problem
 * 
 * I put this solution here since it will only costs O(n) from pop() and peek(), we don't always need to keep all numbers in one stack.
 * Push: If stack1 is empty, then we are safe to insert new int into stack1, even if we have ints in stack2, since we only care about firstly inserted ints,
 * only when we don't have old data in stack2, then we will have too look up new data in stack1
 * Pop: we want all ints are put in stack2 so the top data will be the firstly insert data, then we just pop this data out
 * Peek: Similar to pop, but we don't want to pop this data out
 * 
 * @author hpPlayer
 * @date Sep 22, 2015 10:19:08 PM
 */

public class Implement_Queue_using_Stacks_p232_sol1 {
    
    Stack<Integer> input = new Stack<Integer>();
    Stack<Integer> output = new Stack<Integer>();
    
    // Push element x to the back of queue.
    public void push(int x) {
        input.push(x);//even input is empty, we are still putting x in front, which is what we want
    }

    // Removes the element from in front of queue.
    public void pop() {
        peek();
        output.pop();
    }

    // Get the front element.
    public int peek() {
        if(output.isEmpty()){
           while(!input.isEmpty()) output.push(input.pop()); 
        }
        return output.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return input.isEmpty() && output.isEmpty();
    }
}
