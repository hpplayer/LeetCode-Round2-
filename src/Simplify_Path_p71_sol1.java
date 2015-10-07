import java.util.*;

/*
Simplify Path 

Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"

click to show corner cases.

Corner Cases:
Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".
*/	

/**
 * 
 * String + stack problem
 * 
 * The tricky part is to know the background information before doing it. My old notes have a perfect explanation, I will paste it below
 * 
 * First let my clarify what are those symbols in path
 * We only have 3 path signs "/", "." and ".."
 * All other signs are just filename like "...", "ab.."
 * Of course, we may have some duplicate like "////", just treat them like "/"
 * 
 * Path signs:
 * 1) "/" is the director separator, so the last "/" usually can be omit, because we have not assigned a new directory after it,
 *  and thus we don't need separator. Example "cd /a/" can be simplified to "cd /a"
 * 2) "." means stay in current directory, thus "/a/./././././" will still stay in path "/a" or namely "/a/"
 * If we say "./././././" it will still stay in current path
 * 3) ".." means parent path, so if we assign a directory before the "..", the ".." will lead us to go back to that directory
 * 
 * This solution just use a stack to store valid directory name between "/". If the string between two "/" is ".", then we simply 
 * continue, if the string between "/" is "..", then we will pop the top dir name from stack. Note: we may have corner case like 
 * "/../", i.e. return to an non-existing parent dir, for such case, we simply let it continue. Other strings between "/" are just
 * dir name, though some of them may look strange like "ab..", "....", but they are all valid dir name
 * 
 * After we use above algorithm to store all valid dir name in stack, the next step is simply poping strings from stack and create 
 * result backward
 * 
 * Sol1 is stack solution, with intuitive solution
 * Sol2 is deque solution, with the use of split()
 * 
 * For this problem deque is more suitable, since we need push backward and build string forward.
 * So sol2 is more recommended
 * 
 * @author hpPlayer
 * @date Oct 6, 2015 8:56:00 PM
 */
public class Simplify_Path_p71_sol1 {
	   public String simplifyPath(String path) {
	        //stack will only contain valid directory names
	        Stack<String> stack = new Stack<String>();
	        
	        //our loop will stop at each directory splitter "/"
	        for(int i = 0; i < path.length(); i++){
	            //create directory name between two "/"
	            StringBuilder sb = new StringBuilder();
	            while(i + 1 < path.length() && path.charAt(i+1) != '/'){
	                //first i will always point to /, so we need move pointer first, to find the first char
	                i++;
	                sb.append(path.charAt(i));
	            }
	            
	            //if there is no chars between //, then we simply continue
	            if(sb.length()==0) continue;
	            
	            String temp = sb.toString();
	            if(temp.equals("..")){
	                //we need return to previous dir, but we may have boundary case like "/../", where we don't have 
	                //dir names in stack, so we need check stack first, otherwise we pop the top dir name in stack
	                if(!stack.isEmpty()) stack.pop();
	            }else if(temp.equals(".")){
	                //stay in current dir, so we simply continue
	                continue;
	            }else{
	                //all other strings are just dir name, we simply push to stack
	                stack.push(temp);
	            }
	        }
	        
	        StringBuilder sb = new StringBuilder();
	        //we will build result path backward (due to stack structure)
	        while(!stack.isEmpty()){
	            sb.insert(0, "/" + stack.pop());
	        }
	        
	        //if our stack is empty before above while loop, that means now we are in root
	        //so we simply return "/""
	        return sb.length()==0? "/" : sb.toString();
	    }
}
