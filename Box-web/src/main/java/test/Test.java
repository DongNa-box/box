
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

public class Test {
	/** 输入一个数组，计算以多少步使每个数数值一样，输出步数，如果怎么移都不能一样输出-1 **/
	 /** **/
	    static int solution(int[] machines) {
	int result=0,sum=0;
	       for(int i=0;i<machines.length;i++){
	sum=sum+machines[i];
	}
	if(machines.length!=0){
	if((sum%machines.length)==0){

	int a=sum/machines.length;
	int min=0;
	     for(int i=0;i<machines.length;i++){
	    	 if (i<machines.length&i!=0) {
	    		 if(machines[i]<machines[i-1]){
	    				min=machines[i];
	    				}
			}
	
	}result=Math.abs(min-a);
	}else{
	result=-1;
	}
	}else{
	result=0;
	}

	return result;

	    }

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
	        
	        res = solution(machines);
	        System.out.println(String.valueOf(res));    

	    }
	}



