import java.util.*;
/*
Zigzag Iterator

Given two 1d vectors, implement an iterator to return their elements alternately.

For example, given two 1d vectors:

v1 = [1, 2]
v2 = [3, 4, 5, 6]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].

Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

Clarification for the follow up question - Update (2015-09-18):
The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you,
replace "Zigzag" with "Cyclic". For example, given the following input:

[1,2,3]
[4,5,6,7]
[8,9]
It should return [1,4,8,2,5,9,3,6,7].

*/

/**
 * 
 * Iterator problem
 * 
 * 2 list version can be easily solved by using 2 pointers in two lists and a flag to indicate which list should we dig in.
 * Or we can solve it use built-in iterator. Sol1 is the code that use intuitive solution, I put it here as I like it.
 * But the problem this solution is specific to 2 input lists. We can hardly extend it to k lists problem. Sol2 uses built-in
 * iterator API, which can be applied to k input lists with small modification. 
 * 
 * Sol1 provides a intuitive solution working on 2 lists with 5 parameters
 * Sol2 provides a lazy solution working on k lists but with built-in iterator API
 * Anyway, sol2 is also a good example to practice using iterator
 * 
 * @author hpPlayer
 * @date Oct 9, 2015 12:26:50 AM
 */

public class Zigzag_Iterator_p281_sol1 {

    List<Integer> list1, list2;
    int i, j;
    boolean inList1;//whether should we look at list1?
    
    public Zigzag_Iterator_p281_sol1(List<Integer> v1, List<Integer> v2) {
        list1 = v1;
        list2 = v2;
        i = 0;
        j = 0;
        inList1 = true;
    }

    public int next() {
        //if we need look at List1 and we have elements left in list1
        if(i < list1.size() && inList1){
            if(j != list2.size()) inList1 = false;//if list2 is not empty, next turn will be list2
            return list1.get(i++);
        }else{
            //otherwise we have to look at list2
            if(i != list1.size()) inList1 = true;//if list1 is not empty, next turn will be list1
            return list2.get(j++);
        }

    }

    public boolean hasNext() {
        //as long as one of indexes has not reach end
        return i != list1.size() || j != list2.size();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */