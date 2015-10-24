
/*
Convert Sorted List to Binary Search Tree

Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
*/

/**
 * Bottom-up tree building + divide-and-conquer solution
 * 
 * The tricky part is to come up with the idea of divide-and-conquer idea. In linkedList, it is not easy to get the index of a node. So if we still
 * use two pointer solution in problem Convert Sorted Array to Binary Search Tree, then we have to scan the list several times, which would be inefficient.
 * The idea used in this solution is just moving pointer during tree-built, obviously in such case, we need build the tree bottom-up way
 * 
 * Here we just need to modify the two pointer solution a little bit to avoid the duplicate scans of list. In array problem, we firstly calculate 
 * mid index, then get the value in target index and build the TreeNode accordingly. Here we just build the left part first. So the pointer in linkedlist
 * will automatically move to mid index during the process of building left tree, 
 * 
 * 
 * Remark:
 * 1) Don't forget boundary check in case input head == null!!!!!!
 * 2) How to convert this recursive solution to iterative solution? We can build a BST tree based on the total nodes. Because we know how many nodes
 * the tree would have and we also require build a 
 * @author hpPlayer
 * @date Oct 23, 2015 8:28:51 PM
 */

public class Convert_Sorted_List_to_Binary_Search_Tree_p109_sol1 {
    public TreeNode sortedListToBST(ListNode head) {
        //boundary check 
        if(head == null) return null;
        
        //we need get the len of list so we can always split in half and therefore build a height balanced tree
        int len = 0;
        ListNode temp = head;
        curr = head;
        while(temp.next != null){
            temp = temp.next;
            len ++;
        }
        
        
        return buildTree(0, len);
    }
    
    //pointer in LinkedList
    ListNode curr;
    
    public TreeNode buildTree(int start, int end){
        if(start > end){
            //boundary case, invalid range, return null
            return null;
        }
        
        //to build a balanced tree, we always need to split current range into half and take the mid as root
        int mid = start + (end - start)/2;
        
        //firstly build the left tree, so the pointer in linkedList will stop automatically at mid index, after done left subtree
        TreeNode left = buildTree(start, mid - 1);
        
        TreeNode root = new TreeNode(curr.val);
        //we have used current listNode, let pointer move 
        curr = curr.next;
        
        TreeNode right = buildTree(mid + 1, end);
        
        root.left = left;
        root.right = right;
        
        
        return root;
    }
}
