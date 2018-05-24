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
	double zhjjmin;
	double xdmin;
	double ydmin;
	public Paiban(List<Integer> sizeList,int type,	Map<String,Object> map){
		this.sizeList=sizeList;
		this.type=type;
		this.map=map;
    	this.zhjj=Double.parseDouble(map.get("zhjj").toString());
    	this.xd=Double.parseDouble(map.get("xd").toString());
    	this.yd=Double.parseDouble(map.get("yd").toString());
    	this.zhjjmin=Double.parseDouble(map.get("zhjjmin").toString());
    	this.xdmin=Double.parseDouble(map.get("xdmin").toString());
    	this.ydmin=Double.parseDouble(map.get("ydmin").toString());
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
		if (isBronzing==1&isConvex==1&isPvc==1&isUv==1) {
			return true;
		}else {
			return false;
		}
	}
	public int rowLength(int n,double w) {
		return (int)(n*w+xd+6+(n-1)*zhjj);
		
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
    	
    	double t=Double.parseDouble(map.get("t").toString());
    	double g=Double.parseDouble(map.get("g").toString());
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
      double s = (2 * lh + 2 * wh + g) * ht + 2 * wh * (wh + t) + 2 * (wh + t) * lh;//实际纸盒所占面积
      System.out.println("实际单个纸盒的面积为："+s);
    	double L1 = 2 * (wh + t) + ht;//是与X边平行的纸盒所占长度
    	double W1 = 2 * (lh + wh) + g;//是与Y边平行的纸盒所占长度
        //1。先做预判断面积比例 ，成纸面积不得大于1m*1m
        int k=(int) (1000000/s);
        PaibanResult p=new PaibanResult();
        System.out.println("预判断："+k);
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
    			double L2 = 2 * ht + 2 * (wh + t) +  zhjj;//2个可以嵌套成矩形的，完整矩形的X方向的长
    			double L4 = wh + t;//恰好可以连续嵌套类型的一端的凸出部分
    			p=getPaibanResult(isComplex,s, L2, L4, W1);
    		}
    		else if (type==3) {
			
    			p=getPaibanResult(isComplex,s, L1, 0, W1);//平铺
    			
    		}	
	       // System.out.print("最大利用率"+p.getP());
        	result.put("X", p.getX());
        	result.put("Y", p.getY());
        	result.put("N", p.getN());
        	result.put("M", p.getM());
        	result.put("T", p.getT());
        	result.put("G", p.getG());
	        DecimalFormat f=new DecimalFormat("#######0.00");
	        result.put("P", f.format(p.getP()*100));
	        return result;
        }else{
        	//直接平铺
        	type=3;
        	p=getPaibanResult(isComplex,s, L1, 0, W1);
        	result.put("X", p.getX());
        	result.put("Y", p.getY());
        	result.put("N", p.getN());
        	result.put("M", p.getM());
        	result.put("T", p.getT());
        	result.put("G", p.getG());
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
    	double lh=Double.parseDouble(map.get("boxLength").toString());
    	double wh=Double.parseDouble(map.get("boxWidth").toString());
    	double ht=Double.parseDouble(map.get("boxHeight").toString());
    	double tmin=Double.parseDouble(map.get("tmin").toString());
    	double tmax=Double.parseDouble(map.get("tmax").toString());
    	double gmin=Double.parseDouble(map.get("gmin").toString());
    	double gmax=Double.parseDouble(map.get("gmax").toString());
    	double t1=Double.parseDouble(map.get("t").toString());
    	double g1=Double.parseDouble(map.get("g").toString());
    	double boxl=l;
    	double boxlw=w;
    	double boxextra=extra;
    	double t=0;
    	double g=0;
    
		PaibanResult r=new PaibanResult();
		int length=sizeList.size();
		int[] y=new int[length];
		int[] m=new int[length];//X方向的个数
		int[] n=new int[length];//Y方向的个数
		int[] f=new int[length];
		double[] p=new double[length];
		for (int i = 0; i < length; i++)
			
		{
			t=t1;
			g=g1;
		
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
	        l=boxl;
	        w=boxlw;
	        extra=boxextra;
			Map<String, Object> intmap=new HashMap<>();
			intmap=changeTorG(sizeList.get(i), isComplex,l, extra, w);
    		m[i]=Integer.parseInt(intmap.get("m").toString());
    		n[i]=Integer.parseInt(intmap.get("n").toString());
    		y[i]=rowLength(n[i], w);
    		if (type==1||type==3) {
    			if ((sizeList.get(i) - yd*2 - (m[i] - 1) * zhjj - m[i] * l )<=extra){
    			
    				//调整g,t等
					while ((sizeList.get(i) - yd*2 - (m[i] - 1) * zhjj - m[i] * l )<=extra) {
						boolean tflag=false;
						boolean gflag=false;
						if (t>tmin&t<tmax) {
							t--;
							tflag=true;
						}
						if (g>gmin&g<gmax) {
							g--;
							gflag=true;				
						}
						if (t==tmin&g==gmin) {
							if (m[i]>=1) {
								m[i]--;
		    					
							}							
						}
						if (tflag||gflag) {
							
							double W1 = 2 * (lh + wh) + g;//是与Y边平行的纸盒所占长度
							double L3 = ht + wh + t;//中间部分
			    			double L4 = wh + t;//恰好可以连续嵌套类型的一端的凸出部分
			    			double L1=2*(wh+t)+ht;
			    			if (type==1) {
			    				intmap=changeTorG(sizeList.get(i), isComplex, L3, L4, W1);
			    				l=L3;
			    				extra=L4;
							}else if (type==3) {
								intmap=changeTorG(sizeList.get(i), isComplex, L1, 0, W1);
								l=L1;
								extra=0;
							}
				    		m[i]=Integer.parseInt(intmap.get("m").toString());
				    		n[i]=Integer.parseInt(intmap.get("n").toString());
				    		y[i]=rowLength(n[i], W1);
				    		tflag=false;
				    		gflag=false;	
				    		
						}
						f[i] = m[i]*n[i];
					}
			}else {
				
				f[i] = m[i]*n[i];
			}
    			
    		}else if (type == 2)
			{
				if ((sizeList.get(i) - yd*2 - (m[i] - 1) * zhjj -zhjj- m[i] * l )<=(2*extra+ht)&& 
						(sizeList.get(i) - yd*2 - (m[i] - 1) * zhjj -zhjj- m[i] * l )>= extra)
				{
					m[i] = m[i] * 2 ;
					f[i] = m[i] * n[i];
				}
				else if ((sizeList.get(i) - yd*2 - (m[i] - 1) * zhjj -zhjj- m[i] * l )> (2*extra+ht)) {
					m[i] = m[i] * 2+1;
					f[i] = m[i] * n[i];
				
				}else if ((sizeList.get(i) - yd*2 - (m[i] - 1) * zhjj -zhjj- m[i] * l )<extra) {
					//调整g,t等
					while ((sizeList.get(i) - yd*2 - (m[i] - 1) * zhjj -zhjj- m[i] * l )<extra) {
						boolean tflag=false;
						boolean gflag=false;
						if (t>tmin&t<tmax) {
							t--;
							tflag=true;
						}
						else if (g>gmin&g<gmax) {
							g--;
							gflag=true;				
						}
						else if (t==tmin&g==gmin) {
							if (m[i]>=1) {
								m[i]--;
		    					
							}
							
						}
						if (tflag||gflag) {
							
							double W1 = 2 * (lh + wh) + g;//是与Y边平行的纸盒所占长度
							double L2 = 2 * ht + 2 * (wh + t) +  zhjj;//2个可以嵌套成矩形的，完整矩形的X方向的长
			    			double L4 = wh + t;//恰好可以连续嵌套类型的一端的凸出部分
			    			intmap=changeTorG(sizeList.get(i), isComplex, L2, L4, W1);
				    		m[i]=Integer.parseInt(intmap.get("m").toString());
				    		n[i]=Integer.parseInt(intmap.get("n").toString());
				    		y[i]=rowLength(n[i], W1);
				    		tflag=false;
				    		gflag=false;
				    		l=L2;
				    		extra=L4;
				    		
						}
						f[i] = m[i]*n[i];
					    
					}
					
				}
				
			}
			
			if (m[i]<1) {
				n[i]=0;
			}
		System.out.println("x边："+sizeList.get(i)
		+"   Y边的长："+y[i]+"   x方向个数："
				+m[i]+"   y方向个数："+n[i]+"   t:"+t+
				"  g:"+g+"  纸盒间距："+zhjj+"  xd:"+xd+"  yd:"+yd);
		s = (2 * lh + 2 * wh + g) * ht + 2 * wh * (wh + t) + 2 * (wh + t) * lh;//实际纸盒所占面积
			p[i] = (f[i] * s) / (sizeList.get(i) * y[i]);//利用率	

//    			p[i] = (f[i] * s) / (sizeList.get(i) * (n[i]*w+zhjj*n[i]+11));//利用率	
		System.out.println("利用率："+p[i]);
		
		}
		int j = 0;
		j = Largest(p, length);//利用率最大值
		r.setX(sizeList.get(j));
		r.setY((int)(n[j] * w + zhjj * n[j] + 11));
		r.setM(m[j]);
		r.setN(n[j]);
		r.setP(p[j]);
		r.setT((int) t);
		r.setG((int)g);
		System.out.println("x边："+sizeList.get(j)
		+"   Y边的长："+y[j]+"   x方向个数："
				+m[j]+"    y方向个数："+n[j]);
		System.out.println("最大利用率："+p[j]);
		return r;
    		
   }	
   public Map<String, Object> changeTorG(int x,Boolean isComplex, double l, double extra, double w) {

	   int n=1;
	   int m=0;
		//初始化y[i]=一列的长度，后期根据排版效果进行修改
		int y = rowLength(n, w);
		int ymax = 1000000 / x;
		if (ymax >= 1000)
		{
			ymax = 1000;
		}
			 //单个纸盒长度约等于290,排一列
		if (Math.abs(y -290) <10){   
		    m = (int) ((x- extra -2*yd+zhjj) / (l + zhjj));
		}else {
			//面积<1平米
			if (y>290&y<ymax) {
				if (y>700) {
					m = (int) ((x- extra -2*yd+zhjj) / (l + zhjj));
				}else{	
						if (isComplex) {
							m = (int) ((x- extra -2*yd+zhjj) / (l + zhjj));
						}else {
							while (y<700) {
							m = (int) ((x- extra -2*yd+zhjj) / (l + zhjj));	        	 
							n++;
   	    				y = rowLength(n, w);
						}
							n--;
							y=rowLength(n, w);				
					}
				}				
			}else {
				while (y<290) {
					m = (int) ((x- extra -2*yd+zhjj) / (l + zhjj));        	    		
   				n++;
   				y = rowLength(n, w);		
				}
				
			}
		}
		Map<String,Object> intmap =new  HashMap<String, Object>();
		intmap.put("m", m);
		intmap.put("n", n);
		intmap.put("y", y);
		return intmap;
}
    
	public static int Largest(double list[], int length)
	{
		int i;
		int j = 0;
		double max;
		max = list[0];
		for (i = 1;i<length;i++)
		{
			if (list[i]>max&list[i]<1)
			{
				max = list[i];
				j = i;
			}
	
		}
		return j;
	}
}
