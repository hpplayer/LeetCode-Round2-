/*
Compare Version Numbers

Compare two version numbers version1 and version2.
If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

Here is an example of version numbers ordering:

0.1 < 1.1 < 1.2 < 13.37
*/

/**
 * Pure string problem
 * 
 * The tricky part is to realize that "." cannot be directly used in split, as we it is a special char in regex. So we need use "\\" to skip
 * Also we need be careful about the boundary case like 2 vs 2.0
 * 
 * The main idea is simple, we split two version number based on ".", then we compare each segment. The problem is when shall we stop?
 * Stop at the shorter string end or longer string end? The solution is stopping at longer string, so we can cover boundary like "1" and "1.0".
 * Therefore for the shorter string, we will provide 0 after it ends.
 * 
 * @author hpPlayer
 * @date Oct 26, 2015 6:31:00 PM
 */

public class Compare_Version_Numbers_p165_sol1 {
	public static void main(String[] args){
		String v1 = "123.4560.123";
		String v2 = "0.1";
		
		System.out.println( new Compare_Version_Numbers_p165_sol1().compareVersion(v1, v2)  );
	}
    public int compareVersion(String version1, String version2) {
        //since "." is a special char in regular expression, we need to skip it.
        //to skip a char, we use "\", but we need to skip this "\" as well, so we use double "\\" 
        String[] s1 = version1.split("\\.");
        String[] s2 = version2.split("\\.");
        
        //to cover boundary case like "1" vs "1.0", we will provide supplement 0s to the shorter string after it end
        for(int i = 0; i < Math.max(s1.length, s2.length); i++){
            int a = i >= s1.length? 0 : Integer.valueOf(s1[i]);
            int b = i >= s2.length? 0 : Integer.valueOf(s2[i]);
            
            //equal case, just continue
            if(a == b) continue;
            
            //otherwise we have found a larger version
            return a > b? 1 : -1;
        }
        
        //all chars have been compared, all equal, so we return 0
        return 0;
    }
}
