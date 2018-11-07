
 /**
 * Project Name:PdoneCI-web
 * File Name:Test.java
 * Package Name:com.pdone.uums.test
 * Date:2017年5月16日下午1:31:42
 * Copyright (c) 2017, Wuhan Pdone Technology co.,LTD. All Rights Reserved.
 *
*/

package test;


/**
 * ClassName:Test
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2017年5月16日 下午1:31:42
 * @author   Jay
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class ali {
	
	   
	    public static void main(String[] args){
	        Scanner in = new Scanner(System.in);
	         
	        int res,i=0;
	        String[] machinesTmp =  in.nextLine().trim().split(",");
	        int[] machines = new int[machinesTmp.length];
	        while (i<machines.length && in.hasNextInt())
		      { machines[i] = in.nextInt();
		           
		        
		          i++;
		      }
	       /* for(int i = 0; i < machines.length; i++) {
	            machines[i] = Integer.parseInt(machinesTmp[i]);
	        }*/
	        
	       // res = solution(machines);
	        //System.out.println(String.valueOf(res));    

	    }
	}



