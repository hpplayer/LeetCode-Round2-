import java.util.*;


public class Alien_Dictionary_p269_sol1 {
	public static void main(String[] args){
		System.out.println(new Alien_Dictionary_p269_sol1().alienOrder(new String[]{"ab","ac", "db", "dc", "eb", "ec"}));
	}
    public String alienOrder(String[] words) {
        HashMap<Character, Integer> degrees = new HashMap<Character, Integer>();
        HashMap<Character, Set<Character>> hs = new HashMap<Character, Set<Character>>();
        
        String prev = "";
       // hs.put(words[0].charAt(0), new ArrayList<Character>());

        for(String word : words){
        	for(int i = 0; i < word.length(); i++){
        		if(!degrees.containsKey(word.charAt(i))) degrees.put(word.charAt(i), 0);
        	}
            for(int i = 0; i < Math.min(prev.length(), word.length()); i++){
                char a = prev.charAt(i), b = word.charAt(i);
                if(a != b){
                    if(!hs.containsKey(a)) hs.put(a, new HashSet<Character>());
                    //if(!degrees.containsKey(a)) degrees.put(b, 0);
                    hs.get(a).add(b);
                    //if(!degrees.containsKey(b)) degrees.put(b, 1);
                    degrees.put(b, degrees.get(b) + 1);
                    break;
                }
            }
            
            prev = word;
        }
        
        Queue<Character> que = new LinkedList<Character>();
        
        for(Character c : degrees.keySet()){
        	
            if(degrees.get(c) == 0){
            	que.offer(c);
            }
        }
        System.out.println(degrees);
        System.out.println(hs);
        int count = 0;
        StringBuilder sb = new StringBuilder();
        while(!que.isEmpty()){
            Character c = que.poll();
            sb.append(c);
            count ++;
            if(!hs.containsKey(c)) continue;
            for(Character temp : hs.get(c)){
                if( degrees.get(temp) -1 == 0){
                    que.offer(temp);
                }
                
                degrees.put(temp, degrees.get(temp) -1);
            }
            

        }
       // System.out.println(sb.toString());
        return count == degrees.keySet().size()? sb.toString() : "";
    }
}
