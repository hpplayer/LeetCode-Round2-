import java.util.*;

/**
 * Design + iterator problem
 * 
 * Similar to sol1, we need keep two iterators. One will scan the rows, one will scan the columns.
 * Iterator will help us move the pointer, so we only need to care about the boundary cases.
 * If row iterator (we call x here) has next, then we will jump over all invalid rows (null or empty row).
 * If row iterator does not have next, it may either means we are in last row (we may still have col iterator here)
 * or it means our matrix is empty at all ( we even can't initialize our iterator), so we need do final check to see
 * if our col iterator has been initialized
 * 
 * @author hpPlayer
 * @date Oct 14, 2015 2:23:48 PM
 */
public class Flatten_2D_Vector_p251_sol2 {
    Iterator<List<Integer>> x;
    Iterator<Integer> y;
    public Flatten_2D_Vector_p251_sol2(List<List<Integer>> vec2d) {
    	//now we assume vec2d will always be non-null, otherwise, we even can't initialize our x iterator
        x = vec2d.iterator();
        //we not necessary have values in vec2d, so we have to check before we initialize iterator y 
    }

    public int next() {
        return y.next();
    }

    public boolean hasNext() {
        //Note: we may have !hasNext() in x, but it may means we are still in current row
        //so we can't return false if !x.hasNext()
        if(x == null) return false;
        //skip invalid iterator
        while( x.hasNext() && (y == null || !y.hasNext())){
            y = x.next().iterator();
        }
        
        //return if we have a valid iterator (if we have values in it) 
        //we may have an empty x, therefore y will never be initialized, we need to check if y == null first
        return y != null && y.hasNext();
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */