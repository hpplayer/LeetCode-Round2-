import java.util.*;
/**
 * LinkedHashMap
 * 
 * This is like a cheat solution, we have built-in LinkedHashMap in java, which actually can be easily used as the structure for LRU
 * It even has a constructor to allow map automatically change based on access frequency!
 * 
 * LinkedHashMap preserve the insertion order. It has a built-in function removeEldestEntry() that can remove least used entry from map
 * if we reach the limit. The only thing needs to be noticed is that, by default, we have to remove and put key again to make it in the 
 * front of list. So each time we get a stored key, we will remove key and re-put it in the map. Each time if we set a stored key, will
 * also remove key and re-put it in the map
 * 
 * @author hpPlayer
 * @date Sep 30, 2015 9:25:56 PM
 */

public class LRU_Cache_p146_sol2{
    LinkedHashMap<Integer, Integer> hs;
    
    public LRU_Cache_p146_sol2(int capacity) {
        hs = new LinkedHashMap<Integer, Integer>(){
            //built in function that will be checked before insert any new element
            //if size has reach capacity, hs will remove the last entry
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> e){
                return this.size() > capacity;
            }
        };
    }
    
    public void putFront(int key, int val){
        //remove key from tail
        hs.remove(key);
        //insert key into head
        hs.put(key,val);
    }
    
    public int get(int key) {
        if(!hs.containsKey(key)){
            return -1;
        }else{
            //put key in front
            int val = hs.get(key);
            putFront(key, val);
            return val;
        }
    }
    
    public void set(int key, int value) {
        //remove from tail
        if(hs.containsKey(key)){
            hs.remove(key);
        }
        
        //put front
        hs.put(key, value);
    }
}
