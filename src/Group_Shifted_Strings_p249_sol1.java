import java.util.*;

/*
Group Shifted Strings

Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd".
 We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
Return:

[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
Note: For the return value, each inner list's elements must follow the lexicographic order.
*/	

/**
 * HashTable problem
 * 
 * The tricky part is to find the correct way to generate the hash key
 * 
 * we can have several forms of shifts. But same shift always have same difference in char gap. What we need to do is to find the way
 * to generate key based on the char gap. The nature way is to calculate the difference then put together. But there may be several
 * boundary cases. Like 1) we don't want to mix the char gaps, so we will put a delimiter '#' between each char gap 2) we should allow
 * the gap to be as large as 26. As we may have a -> z and b -> a. Then the difference between them is 25 and -1 respectively.
 * So we just add 26 to the negative number to make it become positive number, like we add 26 to "a" to mark it as a value in second round 
 * 
 * The basic idea is to build a key to each input String, the key is the shift pattern. So we will put all strings share a same pattern
 * to the same list. Finally we just sort each list, then add to our result
 * 
 * Remark:
 * It is very similar to problem Encode and Decode Strings p271, where we also need to generate an encoded string with number
 * @author hpPlayer
 * @date Oct 14, 2015 8:15:39 PM
 */
public class Group_Shifted_Strings_p249_sol1 {
	public static void main(String[] args){
		String[] strings = {"ba", "cb"};
		for (List<String> temp : new Group_Shifted_Strings_p249_sol1().groupStrings(strings)){
			System.out.println(temp);
		}
	}
	
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> result = new ArrayList<List<String>>();
        HashMap<String, List<String>> hs = new HashMap<String, List<String>>();
        
        for(String s : strings){
            //generate key for each string, add it to related list
            String key = getKey(s);
            if(!hs.containsKey(key)){
                hs.put(key, new ArrayList<String>());
            }    
            hs.get(key).add(s);
        }
        
        //add list to result list
        for(String s : hs.keySet()){
            //we have to sort the list first 
            List<String> temp = hs.get(s);
            Collections.sort(temp);
            result.add(temp);
        }
        
        return result;
    }
    
    public String getKey(String s){
        StringBuilder sb = new StringBuilder();
        //the key is the gap between each char
        for(int i = 0; i+1 < s.length(); i++){
            int diff = s.charAt(i+1) - s.charAt(i);
            //if the gap is 26, then we will form a loop, i.e. b + 26 = a
            //so we add 26 to "a" to indicate it is a value in second round
            if(diff < 0) diff += 26;
            
            //to differeneiate each gap, we use a # here
            sb.append(diff + "#");
        }
        
        return sb.toString();
    }
}
