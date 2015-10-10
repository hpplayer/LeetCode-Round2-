import java.util.*;

/**
 *  String problem
 *  
 *  Sol2 mainly use the length of old string to help us find the indexing in encoded string.
 *  
 *  We use a delimiter to append before old string, then insert the len before the delimiter, so that form of new string would be:
 *  "len(str)" + '#' + str
 *  In decode process, we will always jump from a new string to next new string. We will use indexOf() to search for the first 
 *  appearance of our delimiter. Thus we even wouldn't touch the old string part. We just need the length of old string and 
 *  index of delimiter, then we can find our old string! We put delimiter between len and old string, so we would not mix these
 *  two parts. We will repeat this process for each new string until we reach the end of encoded string
 * 
 * @author hpPlayer
 * @date Oct 10, 2015 1:26:56 AM
 */
public class Encode_and_Decode_Strings_p271_sol2 {


    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        
        for(String str : strs){
            //we define the new string as "len(str)" + '#' + str
            //we append it ahead of str, so we will always firstly find our delimiter not '#' in str
            //we append it after len, so we will not mix len with str
            //we can also insert deliminter as string not char, here we just choose char for simplicity
            sb.append(str.length()).append('#').append(str);
        }
        
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> result = new ArrayList<String>();
        
        //i will always stop at the first index of each new string
        int i = 0;
        
        while( i < s.length()){
            //we use indexOf to find the first '#' in our new string, which as we defined above, must be 
            //the delimiter that we added
            
            //indexOf(), as quoted from Java Doc:
            //Returns the index within this string of the first occurrence of the specified character, starting
            //the search at the specified index.   
            
            int index = s.indexOf('#', i);    
            
            //size if the subpart before index and after i
            int size = Integer.valueOf(s.substring(i, index));
            
            //the old string is the subpart after index with len size
            //use example s = "#ab", now index is 0, we need get ab, the index would be substring(0+1, 0 + 1 + 2)
            result.add( s.substring(index + 1, index + 1 + size));
            
            //we don't want know how length is the part for size
            //we just jump from '#' + size + 1 to the start index of next new string
            i = index + 1 + size;
        }
        
        return result;
    }
}

//Your Codec object will be instantiated and called as such:
//Codec codec = new Codec();
//codec.decode(codec.encode(strs));