
 /**
 * Project Name:boxCI-core
 * File Name:TestUser.java
 * Package Name:com.box.uums.test
 * Date:2017年5月4日下午3:01:02
 * Copyright (c) 2017, Wuhan box Technology co.,LTD. All Rights Reserved.
 *
*/

package test;

import java.util.Scanner;


/**
 * ClassName:TestUser
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2017年5月4日 下午3:01:02
 * @author   Jay
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class TestUser2 {

	    public static void main(String[] args){
	        Scanner in = new Scanner(System.in);
	        int res=0,i=0;
	        int[] a = new int[13];
	        while (i<a.length && in.hasNextInt())
		      { a[i] = in.nextInt();

		          i++;
		      }
	        int b=in.nextInt();
	        int[] c= new int[b];
	        i=0;
	        while (i<c.length && in.hasNextInt())
		      { c[i] = in.nextInt();

		          i++;
		      }
	      
	        System.out.println(String.valueOf(res)); 
	     
	        for (int j = 0; j < a.length; j++) {
	        	for (int j2 = 0; j2 < c.length; j2++) {
	        		if (a[i]==c[j2]) {
						
					}
				}
				
			}
	    }
}

