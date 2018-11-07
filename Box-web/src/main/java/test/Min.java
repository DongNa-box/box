
 /**
 * Project Name:Box-web
 * File Name:GrowUpValue.java
 * Package Name:test
 * Date:2018年4月15日下午2:38:19
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ClassName:GrowUpValue
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2018年4月15日 下午2:38:19
 * @author   luowen
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class Min {
/**
 * 
输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
8 4
4 5 1 6 2 7 3 8
输出
1 2 3 4

 */
	 public static ArrayList<Integer>GetLeastNumbers_Solution(int[] input, int k) {
	        ArrayList<Integer> array = new ArrayList<Integer>();
	        if(input==null||input.length==0||k<=0||k>input.length){
	            return array;
	        }
	        for(int i=k/2-1;i>=0;i--){
	            buildMaxHeapSort(input,i,k);
	        }
	        for(int j=k;j<input.length;j++){
	            if(input[j]<input[0]){
	                swap(input,0,j);
	                buildMaxHeapSort(input,0,k);
	            }
	        }
	        for(int i=k-1;i>=0;i--){
	            array.add(input[i]);
	        }
	        return array;
	    }

	    public static void buildMaxHeapSort(int[] input,int i,int k){
	        int leftchild=2*i;
	        int rightchild=2*i+1;
	        int larget=i;
	        if(leftchild<k&&input[i]<input[leftchild]){
	            larget=leftchild;
	        }
	        if(rightchild<k&&input[larget]<input[rightchild]){
	            larget=rightchild;
	        }
	        if(larget!=i){
	            swap(input,i,larget);
	            buildMaxHeapSort(input,larget,k);
	        }
	    }

	    public static void swap(int[] input,int a,int b){
	        int temp=input[a];
	        input[a]=input[b];
	        input[b]=temp;
	    }
	    public static void main(String[] arg) {
	    	Scanner input = new Scanner(System.in);
	      int[] a = new int[2];
	     int i =0;
	     System.out.print("a:");
	      while (i<a.length && input.hasNextInt())
	      {
	          a[i] = input.nextInt();
	        
	          i++;
	      }
	      System.out.print("\nb:");
	      int[] b = new int[a[0]];
//	      String[] machinesTmp =  input.nextLine().trim().split(" ");
	    /*  for(int j = 0; j < machinesTmp.length; j++) {
	    	  System.out.println(machinesTmp[j]);
	        }
	        int[] b = new int[machinesTmp.length];*/
	      
	        
	      /*for(int j = 0; j < b.length; j++) {
	            b[j] = Integer.parseInt(machinesTmp[j]);
	        }*/
	      while (i<b.length && input.hasNextInt())
	      {
	         b[i] = input.nextInt();
	         i++;
	      }
	 
	      System.out.print("b长度："+b.length);
	      ArrayList<Integer> array = new ArrayList<Integer>();
	      array=GetLeastNumbers_Solution(b,a[1]);
	      for (int j = 0; j < array.size(); j++) {
	    	  System.out.println(array.get(j));
		}
	    }
	  
}

