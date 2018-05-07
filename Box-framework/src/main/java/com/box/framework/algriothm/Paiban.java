package com.box.framework.algriothm;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import org.springframework.stereotype.Component;


public class Paiban {
	
	/**
     * 经过计算获取纸张的长、宽、横向个数，纵向个数
     *
     * @param wh
     * @param lh
     * @param ht
     * @return
     */
    public Map<String, Object> getResult( double lh, double wh,double ht,int type) {
        double t = (float) (0.11 * wh);
        double g = (float) (0.15 * wh);
        Map<String, Object> map = new HashMap<String, Object>();//结果集
        if(t<10){
        	t=10;
        }else if(t>20){
        		t=20;
        }
        if(g<10){
        	g=10;
        }else if(g>20){
        		g=20;
        }
   //     System.out.println("常数："+t+"常数："+g);
        double s = (2 * lh + 2 * wh + g) * ht + 2 * wh * (wh + t) + 2 * (wh + t) * lh;//实际纸盒所占面积
  //      System.out.println("实际单个纸盒的面积为："+s);
    	double L1 = 2 * (wh + t) + ht;//是与X边平行的长
    	double W1 = 2 * (lh + wh) + g;//是与Y边平行的长
        //1。先做预判断面积比例 ，成纸的面积不得大于1m*1m
        int k=(int) (1000000/s);
        PaibanResult p=new PaibanResult();
 //       System.out.println("预判断："+k);
        if(k>=3){
        	 //2。再详细计算横向和纵向数目 ,分类处理
        	if (type == 1)
    		{
    			double L3 = ht + wh + t;//中间部分
    			double L4 = wh + t;//恰好可以连续嵌套类型的一端的凸出部分
    			p=getPaibanResult(type,s, L3, L4, W1);
    		}
    		else if (type == 2)
    		{
    			double L2 = 2 * ht + 3 * (wh + t) + 5;//2个可以嵌套成矩形的，完整矩形的X方向的长
    			p=getPaibanResult(type,s, L2, L1, W1);
    		}
    		else
    		{
    			p=getPaibanResult(type,s, L1, 0, W1);
    			
    		}	
	       // System.out.print("最大利用率"+p.getP());
	        map.put("X", p.getX());
	        map.put("Y", p.getY());
	        map.put("N", p.getN());
	        map.put("M", p.getM());
	        DecimalFormat f=new DecimalFormat("#######0.00");
	        map.put("P", f.format(p.getP()*100));
	        return map;
        }else{
        	//直接平铺
        	p=getPaibanResult(3,s, L1, 0, W1);
  	        map.put("X", p.getX());
  	        map.put("Y", p.getY());
  	        map.put("N", p.getN());
  	        map.put("M", p.getM());
  	        DecimalFormat f=new DecimalFormat("#######0.00");
	        map.put("P", f.format(p.getP()*100));
        	return map;
        }
    }
    /**
     * 
     * @param type：包装盒类型
     * @param s:单个纸盒的面积
     * @param l：单个纸盒与X方向平行的长度
     * @param extra:拼接突出的部分
     * @param w：单个纸盒与Y方向平行的长度
     * @return
     */
    public PaibanResult getPaibanResult(int type, double s, double l, double extra, double w){	
    		PaibanResult r=new PaibanResult();
    		int x[] = { 546, 597, 660, 787, 889 };
    		int[] y=new int[5];
    		int[] m=new int[5];//X方向的个数
    		int[] n=new int[5];//Y方向的个数
    		int[] f=new int[5];
    		double[] p=new double[5];
    		for (int i = 0; i < 5; i++)
    		{
    			y[i] = 1000000 / x[i];
    			if (y[i] >= 1000)
    			{
    				y[i] = 1000;
    			}
//    			System.out.println("y的长度："+y[i]);
    			if (type == 1)
    			{
    				m[i] = (int) ((x[i] - extra -7) / (l + 5));
    				n[i] = (int) ((y[i] - 11) / (w + 5));
    			}
    			else
    			{  //type=2，3统一处理
    				m[i] = (int) ((x[i] - 7) / (l + 5));
    				n[i] = (int) ((y[i] - 11) / (w + 5));
    			}	
    			if (type == 2)
    			{
    				if ((x[i] - 6 - (m[i] - 1) * 5 - 5 - m[i] * l < l) && (x[i] - 6 - (m[i] - 1) * 5 - 5 - m[i] * l >= extra))
    				{
    					m[i] = m[i] * 2 + 1;
    					f[i] = m[i] * n[i];
    				}
    				else
    				{
    					m[i] = m[i] * 2;
    					f[i] = m[i] * n[i];
    				}
    			}
    			else
    			{    //type=1，3统一处理
    				f[i] = m[i] * n[i];
    			}
//    			System.out.println("x方向个数："+m[i]);
//    			System.out.println("y方向个数："+n[i]);
//    			System.out.println("Y边的长："+(n[i]*w+5*n[i]+11));
    			p[i] = (f[i] * s) / (x[i] * (n[i]*w+5*n[i]+11));//利用率	
//    			System.out.println("利用率："+p[i]);
    		}
    		int j = 0;
    		j = Largest(p, 5);//利用率最大值
    		r.setX(x[j]);
    		r.setY((int)(n[j] * w + 5 * n[j] + 11));
    		r.setM(m[j]);
    		r.setN(n[j]);
    		r.setP(p[j]);
    		return r;

    	}	
    
	public static int Largest(double list[], int length)
	{
		int i;
		int j = 0;
		double max;
		max = list[0];
		for (i = 1;i<length;i++)
		{
			if (list[i]>max)
			{
				max = list[i];
				j = i;
			}
	
		}
		return j;
	}
}
