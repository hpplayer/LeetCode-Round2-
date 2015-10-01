import java.util.*;

/*
LRU Cache

Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
 it should invalidate the least recently used item before inserting a new item.
*/

/**
 * DoubleLinkedList + HashMap
 * 
 * The tricky part is to handle the boundary case, the solution is to add the dummy head and tail node
 * 
 * To build an efficient and useful LRU, we need two things: 1) the order of use frequency 2) the location each node is
 * Based on the frequency, we can add and remove nodes as LRU, with the location, we can easily change the linkedList
 * To achieve these, here we use a HashMap + DoubleLinkedList. The HashMap will help us locate the node in constant time,
 * and associate the key with Node. DoubleLinkedList can help us maintain the use frequency, we always insert the most recently
 * used key in front and put the least used key in back. Since we will frequently change the head and tail, it is better to 
 * use dummy nodes to help us avoid boundary check.
 * 
 * So based on above analysis, we will use a HashMap to link key with nodes, and use LinkedList to keep the order. If we use 
 * or insert some nodes, we will put it after the dummy head. If we have reached the size, then we will remove the node before 
 * dummy tail.
 * 
 * Remark:
 * When we are removing nodes due to oversize, we should remove the key from Map as well. 
 * 
 * Sol1 is using DoubleLinkedList + HashMap
 * Sol2 is using built-in LinkedHashMap
 * @author hpPlayer
 * @date Sep 30, 2015 8:42:10 PM
 */
public class LRU_Cache_p146_sol1 {
    public class Node{
        //we use doublely linked list to speed the operations
        int key;
        int val;
        Node next;
        Node prev;
        
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
        
    }
    //key is the input key,value is the Node
    HashMap<Integer, Node> hs = new HashMap<Integer, Node>();
    //In this solution, we always keep the most recently used node after head
    //and accordingly, the least used node before tail
    //we will keep head and tail as dummies to avoid boundary check
    Node head = new Node(-1, -1);
    Node tail = new Node(-1, -1);
    int limit = 0;
    int size  = 0;
    
    public LRU_Cache_p146_sol1(int capacity) {
        limit = capacity;
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if(!hs.containsKey(key)){
            return -1;
        }else{
            Node temp = hs.get(key);
            //remove this node and put it in front
            removeNode(temp);
            swapToFront(temp);
            //update value
            return temp.val;           
        }
    }
    
    public void swapToFront(Node node){
        //insert node between Head and current next node of head
        head.next.prev = node;
        node.next = head.next;
        node.prev = head;
        head.next = node;
    }
    
    public void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    public void set(int key, int value) {
        
        
        if(!hs.containsKey(key)){
            //we firstly insert a node, then decide whether we have reached the size limit
            Node temp = new Node(key, value);
            swapToFront(temp);
            hs.put(key, temp);
            size ++;
            
            //if size > limit, then we need remove the least used node, which is the node before tail
            if(size > limit){
                //!!!! remember remove the key from HashMap as well, since this time we are really removing
                //a node from the list
                hs.remove(tail.prev.key);
            	removeNode(tail.prev);
            }
                        
        }else{
            //we need to update a node
            Node temp = hs.get(key);
            //remove this node and put it in front
            removeNode(temp);
            swapToFront(temp);
            //update value
            temp.val = value;
        }
      
    }

}
