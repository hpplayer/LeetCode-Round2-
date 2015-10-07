import java.util.*;

/*
Peeking Iterator

Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().

Here is an example. Assume that the iterator is initialized to the beginning of the list: [1, 2, 3].

Call next() gets you 1, the first element in the list.

Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.

You call next() the final time and it returns 3, the last element. Calling hasNext() after that should return false.

Hint:

Think of "looking ahead". You want to cache the next element.

Is one variable sufficient? Why or why not?

Test your design with call order of peek() before next() vs next() before peek().

For a clean implementation, check out Google's guava library source code.
(https://github.com/google/guava/blob/703ef758b8621cfbab16814f01ddcc5324bdea33/guava-gwt/src-super/com/google/common/collect/super/com/google/common/collect/Iterators.java#L1125)

Follow up: How would you extend your design to be generic and work with all types, not just integer?
*/

/**
 * Design problem + Cache
 * 
 * To get the next element from iterator, we can only call next() on iterator. So, what should we do to the next element after it popped out?
 * The solution is using a cache to store it. So if we used peek() then call next(), we will still get the next element in order. To make
 * code more formal, we use other variables like boolean hasPeeked to indicate whether we have values stored in cache "peekedElement".
 * We also declare all those variables as private. Each time, if we call peek() and we don't have values stored in cache, then we will
 * call next(), and store next element to cache, otherwise we will just return it. Each time, if we call next(), we will firstly check
 * if we have values in cache, if not, we just call next() as normal, otherwise, we will return values in cache and clear the cache.
 * Now the iterator.hasNext() will be false only when the original hasNext() return false AND we don't have values in cache
 * 
 * Remark:
 * All those code style comes from the google implementation of peekingIterator
 * 
 * @author hpPlayer
 * @date Oct 6, 2015 1:16:05 AM
 */

//Java Iterator interface reference:
//https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
public class Peeking_Iterator_p284_sol1 implements Iterator<Integer> {
    private Iterator<Integer> iterator;//create an iterator object
    private Integer peekedElement;//peeked element
    private boolean hasPeeked;//if we got any peeked element
    
    
	public Peeking_Iterator_p284_sol1(Iterator<Integer> iterator) {
	    // initialize any member here.
	    this.iterator = iterator;
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        if(!hasPeeked){
            //if we have not got any peeked element yet, then we need poll next element
            //and save it to peekedElement. Otherwise we can directly return stored element
            hasPeeked = true;
            peekedElement = iterator.next();
        }
        
        return peekedElement;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
	    if(!hasPeeked){
	        //if we have not got any peeked element, then we can behave like general iterator
	        return iterator.next();
	    }
	    
	    //otherwise we will return peeked element, and reinitialize related variables
	    hasPeeked = false;
	    Integer result = peekedElement;
	    peekedElement = null;
	    
	    return result;
	}

	@Override
	public boolean hasNext() {
	    //we got empty iterator only when we don't have peeked element && iterator is empty as well
	    
	    return hasPeeked || iterator.hasNext();
	}
}
