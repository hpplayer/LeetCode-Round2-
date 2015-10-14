import java.util.*;

/*
Flatten 2D Vector

Implement an iterator to flatten a 2d vector.

For example,
Given 2d vector =

[
  [1,2],
  [3],
  [4,5,6]
]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].

Hint:

How many variables do you need to keep track?
1. Two variables is all you need. Try with x and y.
2. Beware of empty rows. It could be the first few rows.
3. To write correct code, think about the invariant to maintain. What is it?
4. The invariant is x and y must always point to a valid point in the 2d vector.
    Should you maintain your invariant ahead of time or right when you need it?
5. Not sure? Think about how you would implement hasNext(). Which is more complex?
6. Common logic in two different places should be refactored into a common method.

Follow up:
As an added challenge, try to code it using only iterators in C++ or iterators in Java.
*/

/**
 * Design problem
 * 
 * The tricky part is to be clear with the indexes and boundary case
 * 
 * To get a value in matrix, we need row and col. So two parameters are all we need to retrieve value in vec2d
 * Since OJ always call hasNext() before next(), we are guaranteed row in next() is a valid row. we just use col to get the 
 * value in that row, then move col to next index. If we found col reaches end, then we reset col and move row pointer.
 * In hasNext(), we will firstly skip all invalid row, then check if we still have valid row left. However, if we found we 
 * even don't have valid vec2d, then we directly return false. So there is that.
 * 
 * Sol1 is the raw solution with two parameters
 * Sol2 is using the iterator solution, which is even simpler
 * 
 * @author hpPlayer
 * @date Oct 14, 2015 2:00:59 PM
 */

public class Flatten_2D_Vector_p251_sol1 {
    int row;//get row
    int col;//get col
    List<List<Integer>> vec2d;//matrix

    public Flatten_2D_Vector_p251_sol1(List<List<Integer>> vec2d) {
        row = 0;
        col = 0;
        this.vec2d = vec2d;
    }

    public int next() {
        //since OJ will call hasNext() before next, if we are calling next(), then we are guaranteed
        //to have a valid row left
        
        //we always makes col valid, so we can directly return value at [row, col]
        int result = vec2d.get(row).get(col);
        //move pointer
        col++;
        //if pointer reachs end, then change row
        if(col == vec2d.get(row).size()){
            col = 0;
            row ++;
        }
        
        return result;
    }

    public boolean hasNext() {
        //hasNext means we still have a valid row.
        //valid row should not be null nor empty
        
        //firstly check the matrix
        if(vec2d == null || vec2d.isEmpty()) return false;
        
        //then skip invalid row
        while( row < vec2d.size() && (vec2d.get(row) == null || vec2d.get(row).isEmpty()) ){
            row ++;
        }
        
        //check if we still have valid row left
        return row < vec2d.size();
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */