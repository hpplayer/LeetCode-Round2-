import java.util.*;

/*
Max Points on a Line

Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
*/

/**
 * Math problem
 * 
 * The tricky part is to get the idea of fixing one point then scanning remaining points, get the idea of using GCD to converge slopes,
 * get the idea of using hashMap to store lines with same slope and be cautious about duplicates which has 0 GCD and infinite slope,
 * so this is a hard problem with many tricky parts
 * 
 * The basic idea is to scan the array, and treat each point as a start point, then scan the rest array to build lines and find 
 * max num of points in each line. Since we have fixed the start point, if another points have same slope, then we can say those
 * points are in the same line. But if the slope may not necessarily an integer, so we need to use some ways to represent the slope.
 * We can use double or float, but it will cause problem in precision (say we have two lines that differed a very tiny unit). So it
 * is better to represent the slope in a more convenient way. In this solution we will use delta_x and delta_y to represent the slope.
 * But in this way, we may get different dimensions of delta_x and delta_y, so we have to calculate the GCD of slope, and convert them
 * to basic number. For example, start point is (0, 0), and we have two points (2, 4) and (4, 8), the delta_x and delta_y for (2,4)
 * is 2 and 4 , the for second point is 4 and 8, but after conversion with GCD, we will get 1, 2 for both points.
 * 
 * So based on above algorithm, we can find the points in same line, then we use a HashMap to hold all lines with current start point,
 * and find the line with maximum on it. But here comes a boundary case that we may have duplicate points with start point. In such case
 * GCD will be 0 and we can't do the conversion. So we have to use an extra variable to count the num of duplicates and avoid the 
 * conversion.
 * 
 * Remark:
 * 1) The time complexity is O(n^2) and the space complexity is O(n!)
 * 2) The way we used to get GCD is called Euclidean algorithm. The Euclidean algorithm is based on the principle that the greatest common divisor
 * of two numbers does not change if the larger number is replaced by its difference(mod) with the smaller number
 * 3) when get the GCD, remember the end condition is b == 0, not b > 0, otherwise it will not work for non-positive inputs
 * 4) inner loop should start with i + 1 to avoid duplicate calculations
 * 5) be careful about duplicate points, otherwise we will get "/0" error
 *  
 * @author hpPlayer
 * @date Oct 25, 2015 4:42:46 PM
 */

public class Max_Points_on_a_Line_p149_sol1 {
	public static void main(String[] args){	     
		Point a = new Point(3, 1);
		Point b = new Point(12, 3);
		Point c = new Point(3, 1);
		Point d = new Point(-6, -1);
		Point[] points = {a, b, c, d};
		
		Max_Points_on_a_Line_p149_sol1 sol = new Max_Points_on_a_Line_p149_sol1();
		System.out.println( sol.maxPoints(points));
	}
    public int maxPoints(Point[] points) {
        int n = points.length;
        int result = 0;
        
        for(int i = 0; i < n; i++){
            //we only look at points behind to avoid duplicate calculation
            
            int duplicates = 0;//record the number of duplicate points
            int maxCount = 0;//record the max count of points in a line
            
            //key is slope, value is count num
            //we will create a new HashMap for each new start point
            HashMap<String, Integer> hs = new HashMap<String, Integer>();
            
            for(int j = i + 1; j < n; j++){
                if(points[i].x == points[j].x && points[i].y == points[j].y){
                    //duplicate points, increase count and skip it
                    //otherwise we will get 0 GCD
                    duplicates ++;
                    continue;
                }
                
                //get diff 
                int delta_x = points[i].x - points[j].x;
                int delta_y = points[i].y - points[j].y;
                
                //get gcd of diff, so we can make slopes converage 
                int gcd = GCD(delta_x, delta_y);
                
                //use delta x and delta y as key for slope instead of floating/double
                //so we won't worry about the precision problm
                String s = delta_x/gcd + "#" + delta_y/gcd;
                if(!hs.containsKey(s)){
                    hs.put(s, 0);
                }
                
                //add one more point for this line
                int newVal = hs.get(s) + 1;
                hs.put(s, newVal);
                maxCount = Math.max(maxCount, newVal);
            }
            
            //for lines start with points[i], the max points is duplicates + 1(itself) + maxCount
            result = Math.max(maxCount + duplicates + 1, result);
        }
        
        return result;
    }
    
    private int GCD(int a, int b){
        //Euclidean algorithm
        while(b != 0){
            //loop while b !=0 (we may have negative inputs, so set b > 0 is not allowed)
            int temp = b;
            b = a%b;//shrink b until we get 0, then a will be the GCD
            a = temp;
        }
        return a;
    }
}
