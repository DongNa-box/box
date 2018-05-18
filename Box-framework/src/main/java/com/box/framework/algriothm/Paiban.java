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
	
	List<Integer> sizeList=new ArrayList<Integer>();
	int type;
	Map<String,Object> map=new HashMap<String,Object>();
	double zhjj;
	double xd;
	double yd;
	public Paiban(List<Integer> sizeList,int type,	Map<String,Object> map){
		this.sizeList=sizeList;
		this.type=type;
		this.map=map;
    	this.zhjj=Double.parseDouble(map.get("zhjj").toString());
    	this.xd=Double.parseDouble(map.get("xd").toString());
    	this.yd=Double.parseDouble(map.get("yd").toString());
	}
	/**
	 * 判断是否工艺复杂
	 * isComplex:(这里用一句话描述这个方法的作用).
	 *
	 * @author luowen
	 * @param isBronzing
	 * @param isConvex
	 * @param isPvc
	 * @param isUv
	 * @return
	 * @since JDK 1.8
	 */
	public boolean isComplex(int isBronzing,int isConvex,int isPvc,int isUv) {
		if (isBronzing==0&isConvex==0&isPvc==0&isUv==0) {
			return false;
		}else {
			return true;
		}
	}
	public int rowLength(int n,double w) {
		return (int)(n*w+yd*2+(n-1)*zhjj);
	}
	/**
     * 经过计算获取纸张的长、宽、横向个数，纵向个数
     *
     * @param wh
     * @param lh
     * @param ht
     * @return
     */
    public Map<String, Object> getResult() {
    	double lh=Double.parseDouble(map.get("boxLength").toString());
    	double wh=Double.parseDouble(map.get("boxWidth").toString());
    	double ht=Double.parseDouble(map.get("boxHeight").toString());
    	double tmin=Double.parseDouble(map.get("tmin").toString());
    	double tmax=Double.parseDouble(map.get("tmax").toString());
    	double gmin=Double.parseDouble(map.get("gmin").toString());
    	double gmax=Double.parseDouble(map.get("gmax").toString());
    	int isBronzing = Integer.parseInt(map.get("isBronzing").toString());
    	int isConvex = Integer.parseInt(map.get("isConvex").toString());
    	int isPvc = Integer.parseInt(map.get("isPvc").toString());
    	int isUv = Integer.parseInt(map.get("isUv").toString());
    	
        double t = (float) (0.11 * wh);
        double g = (float) (0.15 * wh);
        Map<String, Object> result = new HashMap<String, Object>();//结果集
        if(t<tmin){
        	t=10;
        }else if(t>tmax){
        		t=20;
        }
        if(g<gmin){
        	g=10;
        }else if(g>gmax){
        		g=20;
        }
   //     System.out.println("常数："+t+"常数："+g);
//        double s = (2*(lh+wh)+g)*(2*(wh+t)+ht);
      double s = (2 * lh + 2 * wh + g) * ht + 2 * wh * (wh + t) + 2 * (wh + t) * lh;//实际纸盒所占面积
  //      System.out.println("实际单个纸盒的面积为："+s);
    	double L1 = 2 * (wh + t) + ht;//是与X边平行的纸盒所占长度
    	double W1 = 2 * (lh + wh) + g;//是与Y边平行的纸盒所占长度
        //1。先做预判断面积比例 ，成纸的面积不得大于1m*1m
        int k=(int) (1000000/s);
        PaibanResult p=new PaibanResult();
 //       System.out.println("预判断："+k);
        //判断纸盒复杂程度
        boolean isComplex=isComplex(isBronzing, isConvex, isPvc, isUv);
        //计算纸张数量
        if(k>=3){
        	 //2。再详细计算横向和纵向数目 ,分类处理
        	if (type == 1)
    		{
    			double L3 = ht + wh + t;//中间部分
    			double L4 = wh + t;//恰好可以连续嵌套类型的一端的凸出部分
    			p=getPaibanResult(isComplex,s, L3, L4, W1);
    		}
    		else if (type == 2)
    		{
    			double L2 = 2 * ht + 3 * (wh + t) +  zhjj;//2个可以嵌套成矩形的，完整矩形的X方向的长
    			p=getPaibanResult(isComplex,s, L2, L1, W1);
    		}
    		else if (type==3||isComplex) {
			
    			p=getPaibanResult(isComplex,s, L1, 0, W1);//平铺
    			
    		}	
	       // System.out.print("最大利用率"+p.getP());
        	result.put("X", p.getX());
        	result.put("Y", p.getY());
        	result.put("N", p.getN());
        	result.put("M", p.getM());
	        DecimalFormat f=new DecimalFormat("#######0.00");
	        result.put("P", f.format(p.getP()*100));
	        return result;
        }else{
        	//直接平铺
        	p=getPaibanResult(isComplex,s, L1, 0, W1);
        	result.put("X", p.getX());
        	result.put("Y", p.getY());
        	result.put("N", p.getN());
        	result.put("M", p.getM());
  	        DecimalFormat f=new DecimalFormat("#######0.00");
  	        result.put("P", f.format(p.getP()*100));
        	return result;
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
    public PaibanResult getPaibanResult(Boolean isComplex, double s, double l, double extra, double w){	
    		PaibanResult r=new PaibanResult();
    		int length=sizeList.size();
    		int[] y=new int[length];
    		int[] m=new int[length];//X方向的个数
    		int[] n=new int[length];//Y方向的个数
    		int[] f=new int[length];
    		double[] p=new double[length];
    		double b = 290;
    		double eps = 1E-13;
    		double ymax=0;
    		for (int i = 0; i < length; i++)
    			
    		{	//默认排版排一列
    			n[i]=1;
    			//初始化y[i]=一列的长度，后期根据排版效果进行修改
    			y[i] = rowLength(n[i], w);
    			ymax = 1000000 / sizeList.get(i);
    			if (ymax >= 1000)
    			{
    				ymax = 1000;
    			}
//    			System.out.println("y的长度："+y[i]);
    			
    			if (type == 1)
    			{
    				 //单个纸盒长度约等于290,排一列
    	    		if ((y[i] - b) / y[i] <= eps&&isComplex) {
    	    		    System.out.println((y[i] - b) / y[i] );
    	    		    m[i] = (int) ((sizeList.get(i)- extra -2*yd+zhjj) / (l + zhjj));
        				//n[i] = (int) ((y[i] - xd+zhjj) / (w + zhjj));
    	    		    
    	    		}else {
    	    			//面积<1平米
    					if (y[i]>290&y[i]<700&y[i]<ymax) {
    						m[i] = (int) ((sizeList.get(i)- extra -2*yd+zhjj) / (l + zhjj));
    	    				//n[i] = (int) ((y[i] - xd+zhjj) / (w + zhjj));
    					}else {
    						while (y[i]<290) {
    							m[i] = (int) ((sizeList.get(i)- extra -2*yd+zhjj) / (l + zhjj));
        	    				//n[i] = (int) ((y[i] - xd+zhjj) / (w + zhjj));
        	    				n[i]++;
        	    				y[i] = rowLength(n[i], w);
								
							}
    					}
    				}
    	    		
//    				m[i] = (int) ((sizeList.get(i)- extra -2*yd+zhjj) / (l + zhjj));
//    				n[i] = (int) ((y[i] - xd+zhjj) / (w + zhjj));
    			}
    			else
    			{  
    				 //单个纸盒长度约等于290,排一列
    	    		if ((y[i] - b) / y[i] <= eps&&isComplex) {
    	    		    System.out.println((y[i] - b) / y[i] );
    	    		    m[i] = (int) ((sizeList.get(i)-2*yd+zhjj) / (l + zhjj));
        				//n[i] = 1;
    	    		    
    	    		}else {
    	    			//面积<1平米
    					if (y[i]>290) {
    						
    						m[i] = (int) ((sizeList.get(i) -2*yd+zhjj) / (l + zhjj));
    	    				//n[i] = (int) ((y[i] - xd+zhjj) / (w + zhjj));
    					}else {
    						while (y[i]>290&y[i]<700&y[i]<ymax) {
    							m[i] = (int) ((sizeList.get(i) -2*yd+zhjj) / (l + zhjj));
        	    				//n[i] = (int) ((y[i] - xd+zhjj) / (w + zhjj));
        	    				n[i]++;
        	    				y[i] = rowLength(n[i], w);
								
							}
    					
    					}
    				}
    	    		
    				//type=2，3统一处理
//    				m[i] = (int) ((sizeList.get(i) -2*yd+zhjj) / (l + zhjj));
//    				n[i] = (int) ((y[i] - xd+zhjj) / (w + zhjj));
    			}	
    			if (type == 2)
    			{
    				if ((sizeList.get(i) - yd - (m[i] - 1) * zhjj - zhjj - m[i] * l < l) && (sizeList.get(i) - yd - (m[i] - 1) * zhjj - zhjj- m[i] * l >= extra))
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
    			p[i] = (f[i] * s) / (sizeList.get(i) * y[i]);//利用率	

//    			p[i] = (f[i] * s) / (sizeList.get(i) * (n[i]*w+zhjj*n[i]+11));//利用率	
//    			System.out.println("利用率："+p[i]);
    		}
    		int j = 0;
    		j = Largest(p, length);//利用率最大值
    		r.setX(sizeList.get(j));
    		r.setY((int)(n[j] * w + zhjj * n[j] + 11));
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
