/**
 * The tricky part is to solve it with constant space
 * 
 * @author hpPlayer
 * @date Oct 17, 2015 1:53:46 AM
 */
public class Populating_Next_Right_Pointers_in_Each_Node_p116_sol1 {
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        
        TreeLinkNode prev = root;
        TreeLinkNode curr = null;
        
        while(prev.left != null){
            curr = prev;
            while(curr != null){
                curr.left.next = curr.right;
                if(curr.next != null) curr.right.next = curr.next.left;
                curr = curr.next;
            }
            
            prev = prev.left;
        }
    }
}
