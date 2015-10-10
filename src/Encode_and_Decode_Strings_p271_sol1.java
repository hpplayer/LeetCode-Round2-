import java.util.*;

/*
Encode and Decode Strings

Design an algorithm to encode a list of strings to a string.
The encoded string is then sent over the network and is decoded back to the original list of strings.

Machine 1 (sender) has the function:

string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}
Machine 2 (receiver) has the function:
vector<string> decode(string s) {
  //... your code
  return strs;
}
So Machine 1 does:

string encoded_string = encode(strs);
and Machine 2 does:

vector<string> strs2 = decode(encoded_string);
strs2 in Machine 2 should be the same as strs in Machine 1.

Implement the encode and decode methods.

Note:
The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized enough to work on any possible characters.
Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.

*/

/**
 * String problem
 * 
 * The tricky part of this problem is to be familiar with several operations on string and we also need to be careful about empty string case.
 * 
 * Sol1 provides a way that add a delimiter and replace original char that may be incorrectly treated as delimiter. The required operation on
 * string here include replace(), split(). To make our delimiter unique, we will make it as the form of "aba". The b will be our key.
 * We firstly replace all "b" in string with "bb", so there will be no single "b" left. Then we append our delimiter after each string.
 * For decode, we will split the input string based on "aba", since b is exclusive for delimiter, we can easily split the string into 
 * original form. But the tricky part, if we only have empty string in original input, then our default split() will not work on that.
 * It will recognize "" that has non-empty string after it. To cover such extreme case, we need input an extra parameter in split(),
 * so the split will include such case. But this split() will also include the extra empty string after the last delimiter. Since 
 * we will append delimiter after the word, above split() will produce an extra "" in tail. We need be careful about that and don't 
 * add it into our final result
 *  
 * Sol1 provides a way to encode/decode string with string.replace() and string.split(xxx, -1)
 * Sol2 provides a way to encode/decode string with string.indexOf(xxx, i)
 * 
 * Overall, sol1 is more tricky and time-consuming (maybe due to the split() and replace())
 * so sol2 is more recommended, sol1 here can be used as a practice for string operation
 * 
 * @author hpPlayer
 * @date Oct 10, 2015 12:14:42 AM
 */
public class Encode_and_Decode_Strings_p271_sol1 {


    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        
        for(String str : strs){
            //our delimiter will be in the form of aba, we need use "a" to round "b" so that we make sure
            //the single b is unique and can be used as the delimiter
            
            //we firstly replace the core char ("b") in string, so that single b will be unique for delimiter
            //we choose to replace "b" by "bb", so that we won't mix the replaced one with other chars in string
            //ex, if we replace "b" by "bc", then we may mix it with "bc" that exist in original input
            //then we insert out delimiter. 
            
            //To make the code exactly same with above algorithm, I will use "aba" as delimiter
            //but we can also free to use other chars in delimiter as long as it matches the form of "aba"
            sb.append( str.replace("b", "bb") ).append("aba");
        }
        
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> result = new ArrayList<String>();
        
        //here we use "-1" in split(), since we want cover "" (empty string) as well. 
        //if we use default split(), then empty string will not be recognized if there is no valid string after them
        //Quote from Java Doc:
        //"If n is non-positive then the pattern will be applied as many times as possible and the array can have any length" (-1 will keep trailing empty strings while 0 will remove trailing empty strings)
        
        String[] strs = s.split("aba", -1);
        
        //However, if we cover all empty strings, then we will also include a fake empty string that must appeared
        //there after the last delimiter. Fortunately, we know them ahead, so our scan the string[] will not cover
        //the last empty string in last cell
        
        for(int i = 0; i < strs.length-1; i++){
            //don't forget recover the core char from "bb" to "b"
            result.add(strs[i].replace("bb", "b"));
        }
        
        return result;
    }
}

//Your Codec object will be instantiated and called as such:
//Codec codec = new Codec();
//codec.decode(codec.encode(strs));