
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
public class GrowUpValue {
/**
 * 爱奇艺累计有2000万会员，为了提高会员权益以及减少运营成本，我们需要对用户分等级差异化运营，为此我们制定了用户成长体系，按成长值（最小为0）划分为不同等级。成长值计算公式为：
 会员成长值＝每天成长值＋任务成长值
 现在我们输入一组数据，表示用户的成长值计算规则，比如某个用户的每天成长值规则1 1 5 10，第一列1表示每日成长值规则，第二列1表示该条规则的生效开始时间，第三列5表示该条规则的生效截至时间，第四列10表示该条规则的每天成长值，则用户初始值为0，第1天到第5天，每天成长值10点，则第5天成长值为50；另外任务成长值规则，比如2 3 4，第一列2表示该规则为任务成长值，第二列3表示第三天做任务，第三列4表示该天做任务得到成长值4。现在输入一组数据，每行一条成长规则，每日成长规则生效时间重合时以成长数值最大的为准，每日成长值是每天0点更新，任务成长值是0点以后，要求计算成长值规则对应最后一天成长值。
 输入
 输入数据有多行，第一列为1时，该行会有4个数值，第一列为2时，该列会有3个数值
 输出
 对于每个测试实例，初始成长值都为0，计算成长值规则最后一天的用户成长值。


 样例输入
1 1 5 10
 2 3 4
 1 4 6 －5
样例输出
49
 */
	 public static void main(String[] arg) {
	        Scanner scan = new Scanner(System.in);
	        int[] arr = new int[1002];
	        int[] tasks = new int[1002];
	        int lastDay = 0;
	        while (scan.hasNext()) {
	            String input = scan.nextLine();
	            String[] strs = input.split(" ");
	            if (strs.length == 4) {
	                int start = Integer.parseInt(strs[1]);
	                int end = Integer.parseInt(strs[2]);
	                int value = Integer.parseInt(strs[3]);
	                for (int i = start; i <= end; i++) {
	                    if (arr[i] != 0) {
	                        arr[i] = arr[i] > value ? arr[i] : value;
	                    }else {
	                        arr[i] = value;
	                    }
	                }
	                lastDay = lastDay > end ? lastDay : end;
	            }else {
	                int time = Integer.parseInt(strs[1]);
	                int value = Integer.parseInt(strs[2]);
	                tasks[time] = value;
	                lastDay = lastDay > time ? lastDay : time;
	            }
	        }
	        System.out.println(solve(arr,tasks,lastDay));
	        scan.close();
	    }

	    private static long solve(int[] arr, int[] tasks,int lastDay) {
	        long ans = 0;
	        for (int i = 0; i <= lastDay; i++) {
	            ans += arr[i];
	            ans += tasks[i];
	        }
	        return ans;
	    }


}

