import java.util.*;

/**
 * Deq version
 * 
 * This solution is very similar to sol1, but we use some tricks to make code cleaner.
 * 1) use deq, so we can push backward and build string forward
 * 2) use string.split("/+") so that we can automatically handle "//" case
 * 
 * @author hpPlayer
 * @date Oct 6, 2015 9:20:29 PM
 */
public class Simplify_Path_p71_sol2 {
    public String simplifyPath(String path) {
        //split string based on 1+ "/";
        String[] paths = path.split("/+");
        //use deq, so we can push to the back when scanning path while poll from front when building path
        Deque<String> deq = new LinkedList<String>();
        
        for(String str : paths){
            
            //for empty string and ".", we can simply skip them, as they will be removed in simplifed version
            if(str.length() == 0 || str.equals(".")) continue;
            
            if(str.equals("..")){
                //go to parent directory
                //remove current dir from deq, since we may have boundary case like "/../", where we don't 
                //have dir in deq, we need check before pollLast()
                if(!deq.isEmpty()) deq.pollLast();
            }else{
                //all other strings are just dir name
                deq.offerLast(str);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        
        //build the simplifd path forward
        while(!deq.isEmpty()){
            sb.append("/").append(deq.pollFirst());    
        }
        
        //if we got nothing in deq, that means we are in root dir, we simply return "/", otherwise we 
        //will return simplifed path
        return sb.length() == 0? "/" : sb.toString(); 
    }
}
