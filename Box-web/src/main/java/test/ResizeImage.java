
 /**
 * Project Name:Box-web
 * File Name:ResizeImage.java
 * Package Name:test
 * Date:2018年5月15日下午10:23:37
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

/**
 * Project Name:Box-web
 * File Name:ResizeImage.java
 * Package Name:test
 * Date:2018年5月15日下午10:23:37
 * Copyright (c) 2018,Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
 */
 

package test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * ClassName:ResizeImage
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2018年5月15日 下午10:23:37
 * @author   luowen
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */

public class ResizeImage {
	 public static void main(String[] args) throws IOException{  
	        ResizeImage ri=new ResizeImage();  
	        ri.start("E:/2.jpg");  
	        ri.save("E:/2.jpg");  
	    }  
	    BufferedImage src;  
	    int x1,y1,x2,y2;//左上角，右下角  
	      
	    void start(String fn) throws IOException{  
	        src=ImageIO.read(new File(fn));  
	        this.deal();  
	    }  
	    void deal(){  
	        int w=src.getWidth();  
	        int h=src.getHeight();  
	        x1=w;y1=h;x2=0;y2=0;  
	        for(int i=0;i<w;i++){  
	            for(int j=0;j<h;j++){  
	                int rgb=src.getRGB(i, j);  
	                if(rgb!=0xffffff){//不透明  
	                    if(i<x1) x1=i;  
	                    if(j<y1) y1=j;  
	                    if(i>x2) x2=i;  
	                    if(j>y2) y2=j;  
	                }  
	            }  
	        }  
	        System.out.println(x1+" "+y1+" "+x2+" "+y2);  
	    }  
	    void save(String fn) throws IOException{  
	        BufferedImage out=src.getSubimage(x1, y1, x2-x1+1, y2-y1+1);  
	        ImageIO.write(out, "png", new File(fn));  
	    }  
}

