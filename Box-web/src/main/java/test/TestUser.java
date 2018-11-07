
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
public class TestUser {

	/** 输入一个数组，计算以多少步使每个数数值一样，输出步数，如果怎么移都不能一样输出-1 **/
	 /** **/
	  static int solution(int[] machines) {
			int res=0;
			      int[] a=machines;
			if (equwl(a)) {
				if (a[0]==a[1]&&a[1]==a[2]) {
					return 0;
				}else{
					if (cha(machines)==0) {
						return 0;
					}else if (cha(machines)/2==0) {
						return cha(machines)/2;
					}else if (cha(machines)/2!=0) {
						return cha(machines)/2+1;
					}
				}
			}else {
				int b=a[0]+a[1]+a[2];
				return 0;
			}
			return res;
				
			}
	static boolean equwl(int[] machines) {
		int x,y,z;
		x=machines[0];y=machines[1];z=machines[2];
		if (x==y||y==z||x==z) {
			return true;
		}
		return false;
		
	}
	static int max(int[] machines) {
		int x,y,z;
		x=machines[0];y=machines[1];z=machines[2];
		if (x>y&x>z) {
			return 0;
		}else if (y>z&y>x) {
			return 1;
		}else if (z>x&z>y) {
			return 2;
		}return 3;
		
	}
	static int cha(int[] machines) {
		 int[] a=machines;
		int b = Math.max(Math.abs(a[0]-a[1]),Math.abs(a[0]-a[2]));
		int c = Math.max(Math.abs(a[1]-a[2]), b);
		return c;
		
	}
	    public static void main(String[] args){
	        Scanner in = new Scanner(System.in);
	        int res,i=0;
	        int[] machines = new int[3];
	        while (i<machines.length && in.hasNextInt())
		      { machines[i] = in.nextInt();

		          i++;
		      }
	     
	        res = solution(machines);
	        System.out.println(String.valueOf(res));    

	    }
}

