import java.util.*;
/*
Walls and Gates

You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate
is less than 2147483647.

Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

For example, given the 2D grid:
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
 */

/**
 * A classic BFS solution
 * 
 * It is easy to come up with a BFS solution, but where should apply the BFS, on the room or gate? The answer is gate. If we do BFS on each room,
 * then there will a lot of redundant visit since when visit the next room, we come without useful information, so we may visit a same room many times.
 * However if we do BFS on gate, then we will save a lot of time. Because now we start from gate, at this time if we visit the next room , and this
 * is the first time to visit this room a, then we know current path is the shortest path from a gate to room a. Next time if we see the room a
 * again, we will simply skip it without looking it twice.
 * 
 * So to find the shortest path from each room to gate, we need to think backward, i.e. starts from each gate to search for the room that is close
 * to it. Do we need to do a complete BFS on each gate, the update the path on each room repeatedly? not necessary, if we firstly push all gates
 * to the queue, and we search layer by layer, then the we will find the shortest path from gate to room at the first time we see this room, that's
 * the magic of BFS! So we just search the neighbors of current cell in the BFS, then update the path to those unvisited room, we will guarantee
 * the path will be shortest, and we will never update it again. So in this algorithm, we firstly scan the matrix and push all gates to the queue,
 * then we simply update each reachable room once, the time complexity should be O(2mn)
 * 
 * Remark:
 * In this problem, the gate is marked with 0, so the shortest path from gate to each room is just how many layers we need to reach this room.
 * In other words, the value in final matrix is just the number of layers we need to reach this room in BFS
 * 
 * @author hpPlayer
 * @date Oct 8, 2015 9:02:26 PM
 */

public class Walls_and_Gates_p286_sol1 {
	public static void main(String[] args){
		Walls_and_Gates_p286_sol1 sol = new Walls_and_Gates_p286_sol1();
		Pair pair = sol.new Pair(1, 1);
		System.out.println(pair.x);
	}
    /*
    private inner class can only accessed by outer class
    
    Quoted from Oracle:
    
    Otherwise, if the member or constructor is declared private, then access is permitted if and only if it occurs
    within the body of the top level class (¡ì7.6) that encloses the declaration of the member or constructor.
    
    both class and constructor declared public, I can access it in global
    class private (or both private), I can't even see this inner class in outer class
    class public constructor private, I can see this inner class from outer class, but I can't create an object for it in outer class
    
    */
	private class Pair{
        int x;
        int y;
        private Pair(int x, int y){
        	this.x = x;
        	this.y = y;
        }
    }
    
    public void wallsAndGates(int[][] rooms) {
        if(rooms.length == 0) return;//boundary case
        
        Queue<Pair> que = new LinkedList<Pair>();
        
        for(int i = 0; i < rooms.length; i++){
            for(int j = 0; j < rooms[0].length; j++){
                //to make each visit carrying with information, we will do BFS on gate
                if(rooms[i][j] == 0){
                    que.offer(new Pair(i, j));
                }
            }
        }
        
        int[] xOffset = {0, 0, 1, -1};
        int[] yOffset = {1, -1, 0, 0};
        
        //we will offer each room to que only once
        while(!que.isEmpty()){
            Pair curr = que.poll();
            
            //visit the neighbors of curr cell
            for(int i = 0; i < 4; i++){
                int newX = curr.x + xOffset[i];
                int newY = curr.y + yOffset[i];
                
                //check boundary and make sure this is the first time we visit this neighbor
                //Note: once we update the cell, the value in it will be the min value, i.e. the 
                //shortest path from a gate to this cell
                //Accordingly, if we have not seen this cell before, then its value will be the max
                //as defined in problem as int_max
                if(newX >= 0 && newX < rooms.length && newY >= 0 && newY < rooms[0].length && rooms[newX][newY]
                > rooms[curr.x][curr.y] + 1){
                    //one step from curr cell, update path accordingly
                    rooms[newX][newY] = rooms[curr.x][curr.y] + 1;
                    
                    //put this cell in que to look for my rooms
                    que.offer(new Pair(newX, newY));
                }
            }
        }
    }
}
