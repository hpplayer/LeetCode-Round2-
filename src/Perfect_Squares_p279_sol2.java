import java.util.*;

/**
 * BFS solution
 * 
 * The difficulty is to observe sol1 is actually an implication of BFS.
 * 
 * Similar as sol1, we want find target with least perfect squares. So we search the neighbors of previously visited number, neighbors means 
 * they are just one perfect square away from visited number. If we found the target we just return the number of steps away from start num 0.
 * Since one number may be composed of several combinations, while we just want to visit it once because the neighbors of one int may be many,
 * we don't want to re-process them. So we use an boolean array to mark if an integer has been visited before.
 * 
 * 
 * @author hpPlayer
 * @date Sep 22, 2015 4:06:47 PM
 */

public class Perfect_Squares_p279_sol2 {
    public int numSquares(int n) {
        if( n== 0) return 0;
        
        Queue<Integer> que = new LinkedList<Integer>();
        que.offer(0);
        
        boolean visited[] = new boolean[n];
        int level = 0;
        
        while(!que.isEmpty()){
            int size = que.size();
            level++;
            for(int i = 0; i < size; i++){
                int curr = que.poll();
                for(int j = 1; curr + j*j <= n; j++){
                    if(curr + j*j == n) return level;
                    if(!visited[curr+j*j]){
                        que.offer(curr + j*j);
                        visited[curr + j*j] = true;
                    }
                }
            }
        }
        
        return -1;
    }
}
