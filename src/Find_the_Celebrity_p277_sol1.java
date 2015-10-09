/*
Find the Celebrity

Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity.
 The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.

Now you want to find out who the celebrity is or verify that there is not one.
The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B.
You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n),
your function should minimize the number of calls to knows.

Note: There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party.
If there is no celebrity, return -1.

*/

/**
 * Array problem.
 * 
 * The solution here is kind of similar to problem Bitwise AND of Numbers Range p201. Because in both problems, our conditions to get the
 * result is very strict, in p201, if we got any 0 in current bit, then our result will get 0. Here if any people doesn't know the celebrity
 * or the celebrity knows anyone, then we will return -1.
 * 
 * Basically we need to scans to find the real celebrity. The first scan is "loose" while the second scan is "strict". Ideally, we should 
 * use "strict" check in first check as well, but the problem asks us to reduce the call to knows(). So we just use one call in first scan.
 * 
 * We firstly scan the array to pick a potential candidate. Since the requirement is very strict, we can easily filter out all people that
 * does meets the requirement. The problem asks us to minimize the number of calls to knows. So we just use one condition to filter
 * out general people. How could this check make sure us can pick the celebrity? Assume we got a fake celebrity that is very similar to 
 * real celebrity and also locate ahead of celebrity, but itself knows celebrity. Then we will hold this fake celebrity until we reach 
 * the real celebrity, since it knows the celebrity we will replace it by real celebrity. Or we assume there is a fake celebrity that does
 * not know anyone, then under this assumption, we even don't have real celebrity at all. If fake celebrity is behind real celebrity, then 
 * our real celebrity will never be replaced by fake one, since it meets all requirements. 
 * 
 * After first scan, we will finally pick one candidate, and it maybe a fake celebrity or a real one, we don't know.
 * So we need second scan to validate it. This time, we have to use very strict condition to validate
 * 
 * Remark:
 * This problem can also be solved by stack. But it costs more time, as we need O(N) to push all elements to stack, O(n) time 
 * to find the potential candidate, then O(n) time to validate the candidate. But the stack solution can be found in attached problem file
 * 
 * @author hpPlayer
 * @date Oct 9, 2015 2:04:24 AM
 */
public class Find_the_Celebrity_p277_sol1 {
    public int findCelebrity(int n) {
        //small proble, big knowledge
        
        //we start with choosing 0 as candidate
        int candidate = 0;
        
        //we will scan the whole array to validate and pick the "real" potential candidate
        for(int i = 1; i < n; i++){
            if(knows(candidate, i)){
                //if potential candidate knows anyone in party, then it will be replaced
                //we use a "lazy" check here, since if we got a real celebrity, then it must 
                //meets all requirement, its very strict. 
                //However, if we got a fake celebrity and there existing a real celebrity,
                //then the fake celebrity will must be replaced later since it must know the real celebrity and
                //violates the check condition. Otherwise there is no real celebrity in the party
                candidate = i;
            }
        }
        
                    
        //Now we got a candidate in the party, it may be real one or fake one. In any case, it must
        //be our last hope for the real celebrity, all discard candidate must failed our check condition above
        //Now We will check the last candidate with strictest condition
            
        for (int i = 0; i < n; i++){
            //skip itself
            if(i == candidate) continue;
            //use strictest rule to validate the candidate. If our last hope fails in any 
            //condition, we will directly return -1, as we don't have any hope left
            if(knows(candidate, i) || !knows(i, candidate)){
                return -1;
            }
        }           
        
        //if it pass all checks, then it must be our Celebrity!
        return candidate;
    }
    
    //dummy funtion, not true
    public boolean knows(int i, int j){
    	return true;
    }
}
