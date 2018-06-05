package com.box.boxmanage.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PaibanTypeL {
	
	List<BoxClassification> boxClassificationList=new ArrayList<BoxClassification>();
	Map<String,Object> map=new HashMap<String,Object>();
	BoxType boxType=new BoxType();
	double w;
	double t;
	public PaibanTypeL(List<BoxClassification> boxClassificationList,BoxType boxType,Map<String,Object> map){
		this.boxClassificationList=boxClassificationList;
		this.map=map;
    	this.boxType=boxType;
    	this.w=Double.parseDouble(map.get("w").toString());
    	this.t=Double.parseDouble(map.get("t").toString());
	}
	
	/**
	 * 计算x边一个盒子的长度
	 * getResult:(这里用一句话描述这个方法的作用).
	 *
	 * @author luowen
	 * @return
	 * @since JDK 1.8
	 */
    public Map<String, Object> getResult() {
    	double Lgai=0;
    	double Ldi=0;
    	int gai=0;//1.前开直插、后开直插2.吊口3.平粘
    	int xijie=0;//1.正常 0.非正常
    	int lock=0;//1.安全扣2.安全扣+指甲扣3.指甲扣
    	boolean zhicha=false;
    	for (int i = 0; i < boxClassificationList.size(); i++) {
			if (boxType.getDetail1().equals(boxClassificationList.get(i).getId())) {
				if (boxClassificationList.get(i).getName().equals("前开直插")||
						boxClassificationList.get(i).equals("后开直插")) {
					Lgai=w+t;
					Ldi=0;
					gai=1;
					zhicha=false;
				}else if (boxClassificationList.get(i).getName().equals("吊口")) {
					Lgai=2*w+t;
					Ldi=0;
					gai=2;
				}else if (boxClassificationList.get(i).getName().equals("平粘")) {
					Lgai=w;
					Ldi=0;
					gai=3;
				}
			}
			if (boxType.getDetail3().equals(boxClassificationList.get(i).getId())) {
				if (boxClassificationList.get(i).getName().equals("三角孔")||
						boxClassificationList.get(i).getName().equals("圆孔")||
						boxClassificationList.get(i).getName().equals("飞机孔")) {
					lock=0;
					Lgai=2*w+t;
					Ldi=0;
				}else if (boxClassificationList.get(i).getName().equals("安全扣")){
					lock=1;
					Lgai=w+t;
					Ldi=0;
				}else if(boxClassificationList.get(i).getName().equals("双安全扣")){
					lock=2;
					Lgai=w+t;
					Ldi=0;
				}
				else if(boxClassificationList.get(i).getName().equals("安全扣+指甲扣")){
					lock=3;
					Lgai=w+t;
					Ldi=0;
				}
				else if(boxClassificationList.get(i).getName().equals("指甲扣")) {
					lock=4;
					Lgai=w+t;
					Ldi=0;
				}else if (boxClassificationList.get(i).getName().equals("撕拉线")) {
					lock=0;
					Lgai=w;
					Ldi=0;
				}else if (boxClassificationList.get(i).getName().equals("正常")) {
					lock=0;
					xijie=1;
					Ldi=0;
					switch (gai) {
					case 1:
						break;
					case 2:
						Lgai=0.5*w;
						break;
					case 3:
						break;
					default:
						break;
					}
				}
			}
			if (boxType.getDetail2().equals(boxClassificationList.get(i).getId())) {
				if (boxClassificationList.get(i).getName().equals("前开直插")||
						boxClassificationList.get(i).getName().equals("后开直插")) {
					zhicha=true;
					Ldi=w+t;
				}else if (boxClassificationList.get(i).getName().equals("平粘")) {
					if (gai==2&&xijie==1) {
						Ldi=0.5*w;
					}else {
						Ldi=w+t;
					}
					
				}else if (boxClassificationList.get(i).getName().equals("自动锁底")) {
					if (gai==3&&xijie==1) {
						Lgai=w;
					}else{
						Ldi=0.5*w+15;
					}
					
				}else if (boxClassificationList.get(i).getName().equals("锁底")) {
					if (gai==3&&xijie==1) {
						Lgai=w*0.5;
					}else{
						Ldi=2*w/3;
					}
				}
			}
		}
    	map.put("gai", Lgai);
    	map.put("di", Ldi);
    	map.put("lock", lock);
    	map.put("zhicha", zhicha);
    	
		return map;
    
    }
 
}
