import java.util.*;

/**
 * Iterator problem
 * 
 * Here we use built-in iterator API and the Deque. We observed that the problem actually asks to output the value column by column.
 * After we reach the bottom, our next output should be from top again. This reminds me of Deque structure where we can pop from first then push
 * to the end. We can easily extend this solution to k lists case, where we just need to push k iterators to the deq. We will use the 
 * hasNext() and next() from built in iterator API. We only push iterator that has next to the deq. At some points if all iterators report
 * no next value, then we know we reach the end.
 * 
 * 
 * Remark:
 * 1) If we are not allowed to use built in API, then I think it is not hard to implement an iterator on a list by myself, where we just 
 * need to have a constructor, a next() and a hasNext()
 * 2) I searched solution discussion and didn't see a good solution. Most solutions uses built-in iterator + a queue to scan k lists. So
 * at this moment I will still use this solution to solve k lists case
 * 
 * @author hpPlayer
 * @date Oct 9, 2015 12:55:08 AM
 */
public class Zigzag_Iterator_p281_sol2 {
    Deque<Iterator<Integer>> iterators;
    
    public Zigzag_Iterator_p281_sol2(List<Integer> v1, List<Integer> v2) {
        iterators = new LinkedList<Iterator<Integer>>();
        //we only push iterators that has next to the deq i.e. we still have elments left in this list
        if(!v1.isEmpty()) iterators.offerLast(v1.iterator());
        if(!v2.isEmpty()) iterators.offerLast(v2.iterator());
    }

    public int next() {
        //get current iterator
        Iterator<Integer> curr = iterators.pollFirst();
        //since we only push iteartor with next into the poll, we guarantee we got next 
        int result = curr.next();
        //if current iterator still has next, we will push it to the tail and let it wait for nexy cycle
        if(curr.hasNext()) iterators.offerLast(curr);
        
        return result;
    }

    public boolean hasNext() {
        //we will return false if our deq as empty, i.e. we have output all elemnts in all lists
        return !iterators.isEmpty();
    }
	/**
	 * Your ZigzagIterator object will be instantiated and called as such:
	 * ZigzagIterator i = new ZigzagIterator(v1, v2);
	 * while (i.hasNext()) v[f()] = i.next();
	 */
}
