import java.util.*;
/**
 * Stack problem.
 * 
 * Here is the solution using built-in stack library
 * We use two stacks, one stack behave as normal stack while the other stack only contains the min value so far.
 * To save space, we will only save local min value into the min Stack. If current value is a duplicate of min value in 
 * min Stack, we will push it too. So later if it is poped out, we still have another min value in stack
 * 
 * This solution is trivial, but given by official pdf.
 * @author hpPlayer
 * @date Sep 22, 2015 6:08:00 PM
 */

public class Min_Stack_p155_sol1 {
	public static void main(String[] args){
		Min_Stack_p155_sol1 test = new Min_Stack_p155_sol1();
		test.push(1);
		test.push(2);
		test.push(1);
		System.out.println(test.getMin());
		test.pop();
		System.out.println(test.getMin());
		test.pop();
		System.out.println(test.getMin());
	}
    Stack<Integer> stack = new Stack<Integer>();
    Stack<Integer> minStack = new Stack<Integer>();
    
    public void push(int x) {
        stack.push(x);
        if(minStack.isEmpty() || x <= minStack.peek()) minStack.push(x);
    }

    public void pop() {
        if(stack.pop().equals(minStack.peek())) minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
