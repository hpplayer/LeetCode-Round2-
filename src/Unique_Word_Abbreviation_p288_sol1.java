import java.util.*;
/*

Unique Word Abbreviation

An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:

a) it                      --> it    (no abbreviation)

     1
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
d) l|ocalizatio|n          --> l10n
Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary.
A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

Example: 
Given dictionary = [ "deer", "door", "cake", "card" ]

isUnique("dear") -> false
isUnique("cart") -> true
isUnique("cane") -> false
isUnique("make") -> true

*/

/**
 * HashMap solution
 * 
 * It is easy to come up with the idea of using hashMap. The trick part is to decide what is the key and value in HashMap. An abbr
 * can respond to several words, while one word just has one abbr, so the key would be abbr and the value wold be words.
 * A word is defined as not duplicate if we don't have its abbr key in Map or the abbr in map is produced exactly by this input word.
 * 
 * So when building the HashMap, if we got a abbr key twice from two diff words in dict, then this abbr will be duplicate.
 * It is not necessary to store a set of words that produces same abbr, instead, we simply store a maker like empty string or null.
 * Then we check the input word's abbr key in Hashmap to if the value would match it. If they can match, then it will be the same word,
 * otherwise we will return false. If we got empty string or Null, definitely we couldn't match them, so we will return false.
 * If we even don't have such abbr key in Map, its definitely unique, just return true
 * 
 * Remark:
 * 1. we use an extra function here to produce the key, so we can avoid duplicate code
 * 2. we use String.valueOf() to convert int to string in the mid, otherwise we may get a sum of char code
 * 
 * @author hpPlayer
 * @date Oct 8, 2015 11:09:08 PM
 */
public class Unique_Word_Abbreviation_p288_sol1 {
	public static void main(String[] args){
		String[] dict = {"ab", "b"};
		Unique_Word_Abbreviation_p288_sol1 sol = new Unique_Word_Abbreviation_p288_sol1(dict);
		System.out.println(sol.isUnique("ac"));
	}
    private final HashMap<String, String> hs;
    
    public Unique_Word_Abbreviation_p288_sol1(String[] dictionary) {
        hs = new HashMap<String, String>();
        
        for(String word : dictionary){
            String key = produceKey(word);
            //found duplicate key, put a marker "" here indicating it is duplicate
            if(hs.containsKey(key)){
                hs.put(key, "");
            }else{
                //otherwise put a word that produces this word
                hs.put(key, word);
            }
        }
    }

    public boolean isUnique(String word) {
        String key = produceKey(word);
        //System.out.println(key);
        //if we don't have such key in dict, or we have such key in dict but produced by same word
        //Note: if we use null as key, then we need to use equals() on word instead of hs.get(key), since null does not have equals() function
        return !hs.containsKey(key) || hs.get(key).equals(word);
    }
    
    public String produceKey(String word){
        //if word length < 2, the key is it selft
        if(word.length() <= 2) return word;
        
        return word.charAt(0) + String.valueOf(word.length() - 2) + word.charAt(word.length() -1);
    }
}
