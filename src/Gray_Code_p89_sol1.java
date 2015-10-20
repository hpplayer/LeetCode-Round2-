import java.util.*;

/*
Gray Code

The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code.
A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
*/

/**
 * Observation problem
 * 
 * The tricky part is to observe that gray codes actually follow a pattern.
 * 
 * First of all, we list some gray code:
 * 0 0 0 
 * 0 0 1
 * 
 * 0 1 1
 * 0 1 0
 * 
 * 1 1 0
 * 1 1 1 
 * 1 0 1
 * 1 0 0
 * 
 * We found each seg part above can be got from read previous result backward, then add 1 in front
 * 
 * So we just use a list, add 0 to it as initial case, then loops it for n times.
 * In each loop, we will record the size of previous result, and read each previous result backward, then set 1 in front use (x|<<1) operation
 * 
 * Remark:
 * We can easily convert this solution to recursive version. But it is trivial, so I did not list it here. During the conversion, be careful about 
 * left shift operation, we need to shift leftward by n - 1 times, not n. Like what we did it iterative version. we loop from 0 to n-1, each time we 
 * left shift by n-1 as well.
 * 
 * @author hpPlayer
 * @date Oct 20, 2015 12:48:37 PM
 */
public class Gray_Code_p89_sol1 {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new LinkedList<Integer>();
        result.add(0);
        
        for(int i = 0; i < n; i++){
            int size = result.size();
            for(int j = size - 1; j >= 0; j--){
                result.add(result.get(j) | (1 << i));
            }
        }
        
        return result;
    }
}
