package com.box.framework.algriothm;
import java.util.Comparator;
import java.util.Map;

public class ValueComaprable implements Comparator<Integer>{
	
	Map<Integer,Double> base;
	public ValueComaprable(Map<Integer,Double> base){
		this.base = base;
	}
	
	public int compare(Integer o1, Integer o2) {
		if(base.get(o1)>= base.get(o2))
			return -1;
		else
			return 1;
	}

}
