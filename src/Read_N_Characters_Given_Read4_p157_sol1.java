/*
 *
Read N Characters Given Read4

The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function will only be called once for each test case.
*
*/

/**
 * 
 * String problem
 * 
 * This problem is very interesting, there are two tricky parts 1) get understand with the description 2) Boundary case
 * 
 * First of all, let's get understand with the problem. Now we are provided with an API read4(char[]), this API can read chars from a file
 * into the char[] passed as argument. However, this API will only read 4 chars each time. When reach the end of file, it will read the num
 * of remaining chars, which may < 4. So it returns a number with how many chars it actually read, the number range from 0 -> 4. The problem
 * asks us to implement another API which can read N chars each time. This new API should behave similar to read4(), but it must be implemented 
 * based on read4().
 * 
 * The, lets discuss about the solution. The basic idea is simply use read4() to read file, but to read N chars in a file,
 * there will be two boundary cases. Case 1: we reach the end of file. In such case, read4() may return a number < 4. If we found that
 * we know we reach the end of file, then we should stop. Case 2: we have read n chars. In such case, we use a variable to hold
 * how many chars we have read. If it reaches n, we will stop read immediately. Since read4() will only write 4 chars each time,
 * we will create an extra char[] with len 4. We will reuse this char[]. When we copy the chars in extra[] to our real dest buffer,
 * we need be careful about the boundary. We cannot read more than n chars, also we cannot read not more than remaining chars in file
 * 
 * Remark:
 * we have official solution in Handbook
 * 
 * @author hpPlayer
 * @date Oct 21, 2015 8:33:55 PM
 */
public class Read_N_Characters_Given_Read4_p157_sol1 {
	public static void main(String[] args){
		char[] buf = {' '};
		System.out.println(new Read_N_Characters_Given_Read4_p157_sol1().read(buf, 1));
	}
	
	/* The read4 API is defined in the parent class Reader4.
    int read4(char[] buf); */
	
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        //a buffer that we used to reac chars from read4()
        char[] buf2 = new char[4];
        //there are two cases that our read will end
        //case 1: we reach the end of file, where read4() returns a number < 4
        //case 2: we reach n chars in current read
        //case 1 is indicated by  boolean variable EOF(end of file)
        //case 2 is indicated by int variable charRead
        boolean EOF = false;
        int charRead = 0;
        
        while(!EOF && charRead < n){
            int size = read4(buf2);
            //we reach of file
            if(size < 4) EOF = true;
            //decide how many chars we can write in current loop
            //the number is decided by case 1 and case 2, whichever is met 
            int realLen = Math.min(size, n-charRead);
            
            //update value in buf
            for(int i = 0; i < realLen; i++){
                buf[charRead + i] = buf2[i];
            }
            
            //update variable charRead
            charRead += realLen;
        }
        
        return charRead;
    }
    
    public int read4(char[] buf){
    	return 1;
    }
}
