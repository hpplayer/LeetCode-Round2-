/**
 * Bottom-up Merge sort.
 * We start scan the loop with the scope of 2, then 4, then 8. Each time we will compare a pair of such scope.
 * Each loop will sort the list within scope. To successfully sort the list, we need three variables.
 * "tail" which will locate the last Node of previous sequence. "left" locates the start Node of first scope, "right" locates the start Node 
 * of second scope. Here we use split() to scan those scopes, then use merge() to merge those two scopes, and search the the LastNode of current two
 * scopes, so we can update "tail" and repeat those steps
 * 
 * @author hpPlayer
 * @date Sep 17, 2015 8:25:12 PM
 */
public class Sort_List_p148_sol1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode a = new ListNode(3);
		ListNode b = new ListNode(4);
		ListNode c = new ListNode(1);
		a.next = b;
		b.next = c;
		
		ListNode curr = new Sort_List_p148_sol1().sortList(a);
		while(curr != null){
			System.out.println(curr.val);
			curr = curr.next;
		}
	}
	
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        int len = 0;
        ListNode curr = head;
        while(curr != null){
            curr = curr.next;
            len ++;
        }
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode left, right, tail;
        for(int i = 1; i < len; i *= 2){//each loop goes 2 times larger
            curr = dummy.next;
            tail = dummy;
            while(curr != null){
                left = curr;//start of first seq
                right = split(left, i);//start of second seq
                curr = split(right, i);//start of next seq
                tail = merge(left, right, tail);//return last Node
            }
        }
        
        return dummy.next;
    }
    
    public ListNode merge(ListNode left, ListNode right, ListNode tail){
        ListNode curr = tail;
        while(left != null && right != null){
            if(left.val > right.val){
                curr.next = right;
                right = right.next;
                curr = curr.next;
            }else{
                curr.next = left;
                left = left.next;
                curr = curr.next;
            }
        }
        
        curr.next = (left == null ? right : left);
        
        while(curr.next != null){
            curr = curr.next;   
        }
        
        return curr;//return the last Node of current merged list
    }
    public ListNode split(ListNode head, int len){
        //look for last Node of current sequence
        for(int i = 1; i < len && head != null; i++){
            head = head.next;
        }
        //if null
        if(head == null) return null;
        ListNode second = head.next;//start Node of next sequence
        head.next = null;//cut the link between current sequence and next sequence, so we can reorder freely
        return second;
        
    }
}
