import java.util.*;


public class inputNumOutputList {
	public static void main(String[] args){
		System.out.println(generateC("12"));
	}
	public static List<String> generateC(String num){
		HashMap<Integer, List<Character>> hs = new HashMap<Integer, List<Character>>();
		int count = 0;
		for(int i = 1; i <= 9; i ++){
			hs.put(i, new ArrayList<Character>());
			List<Character> list = hs.get(i);
			for(int j = 0; j < 3 && count < 26; j++){
				list.add( (char) ('A' + count) );
				count ++;
			}
		}
		
		List<String> result = new ArrayList<String>();
		
		DFS(result, num, "", hs);
		
		return result;
	}
	
	public static void DFS(List<String> result, String num, String temp, HashMap<Integer, List<Character>> hs){
		if(num.length() == 0){
			result.add(temp);
			return;
		}
		
		for(Character c : hs.get(num.charAt(0) - '0')){
			DFS(result, num.substring(1), temp + c, hs);
		}
	}
}
