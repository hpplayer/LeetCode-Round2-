import java.util.Arrays;

/*
 * 
Read N Characters Given Read4 II - Call multiple times

The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function may be called multiple times.
*/


/**
 * String + observation problem
 * 
 * The most tricky part is to be clear with logic and be careful with the corner case. Now we have to update the output buffer
 * based on two extra variables, so things become more complicated.
 * 
 * The main part of this solution is similar to problem Read N Characters Given Read4(p157), but now we need to connect several reads together.
 * Obviously, we need global variables to hold the global status of read. 
 * Each time we still get a buffer of size 4 updated from read4(). But due to boundary case, we may stop before we read the whole
 * chars in buffer. We may have an extra boundary case inside this boundary case. Our last read of the file maybe incomplete, say
 * there is only 3 chars in left, so we have 3 chars in buffer. But now we stop early at first char. So next read should have 
 * read range between 1 and 3. So in addition to buffer, we needs two extra variables to tell us where to start next read
 * and how many chars are left in buffer. So in this solution, we have the code to update these two variables and we also have 
 * code to read chars based on these two variables. More details can be found below
 *  
 * Remark:
 * we have official solution in Handbook
 * 
 * @author hpPlayer
 * @date Oct 21, 2015 10:20:28 PM
 */

public class Read_N_Characters_Given_Read4_II_Call_multiple_times_p158_sol1 {
	public static void main(String[] args){
		char[] buf = new char[100];
		System.out.println(new Read_N_Characters_Given_Read4_II_Call_multiple_times_p158_sol1().read(buf, 1));
		System.out.println(Arrays.toString(buf));
	}
	/* The read4 API is defined in the parent class Reader4.
    int read4(char[] buf); */
	
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    //we may still have chars left in buf2
    //so this time, we will make buf2 become global variable
    //we also keep two pointers to mark where we stop last time in buf2
    //and how many chars left in buf2
    char[] buf2 = new char[4];
    int buf2Start = 0;
    int buf2Size = 0;
    
    public int read(char[] buf, int n) {
        //we still keep EOF and charRead inside read()
        //so we can stop when reach boundary case
        int charRead = 0;        
        boolean EOF = false;
        
        //stop when reach EOF or charRead == n
        while(!EOF && charRead < n){
            //firstly we need check if we have unread chars left from last read
        	//~~~get how many chars we can get from input
            int size = buf2Size == 0? read4(buf2) : buf2Size;
            
            //then we check if we reach EOF
            //In case we have reached EOF but we still have chars in buf2, we still cannot stop the loop
            //so we treat such case as non-EOF case
            if(buf2Size == 0 && size < 4) EOF = true;
            
            //check two boundary cases, put restriction on update if necessary
            //~~~get how many chars can output 
            int realLength = Math.min(size, n - charRead);
            
            //we update buffer based on buf2Start so we can cover general cases and corner case where we
            //need to continue read chars from last stop spot
            for(int i = buf2Start; i < buf2Start + realLength; i++){
            	//we can overwrite buf with different readN
            	//In same read, we have to write on new cell
                buf[charRead + i - buf2Start] = buf2[i];
            }
            
            //we use %4 update the stop spot, when stop at index 0,4 then next buf2Start should be 0
            //if stop at 1, 2, 3, then next buf2Start should be 1,2,3 respectively
            buf2Start = (buf2Start + realLength)%4;
            //the chars left in buf2 is the expected update size - real update size
            buf2Size = size - realLength;
            
            //update charRead accordingly
            charRead += realLength;
        }
        
        return charRead;
    }
    
    public int read4(char[] buf){
    	buf[0] = 'a';
    	return Math.min(4, 1);
    }
}
