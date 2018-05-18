
 /**
 * Project Name:Box-framework
 * File Name:MergeImage.java
 * Package Name:com.box.framework.algriothm
 * Date:2018年5月17日下午5:58:33
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.framework.algriothm;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * ClassName:MergeImage
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2018年5月17日 下午5:58:33
 * @author   luowen
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class MergeImage {
	Map<String,Object> map=new HashMap<String,Object>();
	int type;//1对插2头对头3平铺
	int zhjj;//纸盒间距
	int xd;//x边距离
	int yd;//y边距离
	int x;//x边
	int y;//y边
	int m;//x个数
	int n;//y个数
	int l;//张开图长
	int w;//展开图宽
	int extra;//移位
	String file;//文件地址
	String targetFile;//生成文件地址
	
	public MergeImage(Map<String, Object> map) {
		super();
		this.map = map;
		this.type = Integer.parseInt(map.get("type").toString());
		this.zhjj = Integer.parseInt(map.get("zhjj").toString());
		this.xd = Integer.parseInt(map.get("xd").toString());
		this.yd = Integer.parseInt(map.get("yd").toString());
		this.x = Integer.parseInt(map.get("x").toString());
		this.y = Integer.parseInt(map.get("y").toString());
		this.m = Integer.parseInt(map.get("m").toString());
		this.n = Integer.parseInt(map.get("n").toString());
		this.l = Integer.parseInt(map.get("l").toString());
		this.w = Integer.parseInt(map.get("w").toString());
		this.extra = Integer.parseInt(map.get("extra").toString());
		this.file = map.get("file").toString();
		this.targetFile = map.get("targetFile").toString();
	}
	/**
	 * 合成图像
	 * mergeImage:(这里用一句话描述这个方法的作用).
	 *
	 * @author luowen
	 * @param files
	 * @param low
	 * @param column
	 * @param targetFile
	 * @since JDK 1.8
	 */
	  public void mergeImage() {  
		    
	    	File src = new File(file);  
	        BufferedImage images = null;
	        int[] ImageArrays = null;
			try {
				images = ImageIO.read(src);
				 int width = images.getWidth();  
			     int height = images.getHeight();  
			        ImageArrays = new int[width * height];  
			        ImageArrays = images.getRGB(0, 0, width, height, ImageArrays, 0, width);
			} catch (IOException e1) {
				
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
			}
			int ls=ChangeRgb(l);
			int ws=ChangeRgb(w);
			int xs=ChangeRgb(x);
			int ys=ChangeRgb(y);
			int r1=ls/images.getWidth();
			int r2=ws/images.getHeight();
			
	        double width1=2*ChangeRgb(xd) + m * images.getWidth()*r1+(m - 1) * ChangeRgb(zhjj);
	        double height1= ChangeRgb(yd)*2 + n * images.getHeight()*r2 + (n - 1) * ChangeRgb(zhjj);
	        int rate=(int) (500*500/width1*height1);
	        
	        
	        int lg=ChangeRgb(ls)*rate;
			int wg=ChangeRgb(ws)*rate;
			int xg=ChangeRgb(xs)*rate;
			int yg=ChangeRgb(ys)*rate;
			int xdg=ChangeRgb(xd)*rate;
			int ydg=ChangeRgb(yd)*rate;
			int zhjjg=ChangeRgb(zhjj)*rate;
			int extrag=ChangeRgb(extra)*rate;
	        int width_i=ydg;
	        int height_i=xdg;
	        // 生成新图片  
	        try {  
	            BufferedImage ImageNew = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
	            if (type==1) {
	            	for (int i = 0; i < m; i++) {
						for (int j = 0; j < n; j++) {
							ImageNew.setRGB(width_i, height_i, images.getWidth()*rate, images.getHeight()*rate, ImageArrays, 0,  
									images.getWidth()*rate); 
							System.out.println(width_i+"____"+height_i);
							width_i +=(images.getWidth()*rate+zhjjg);
						}
						width_i=0;
						height_i+=(images.getHeight()*rate+zhjjg-extrag);
					}
				}else if (type==2) {
					for (int i = 0; i < m; i++) {
						for (int j = 0; j < n; j++) {
							ImageNew.setRGB(width_i, height_i, images.getWidth()*rate, images.getHeight()*rate, ImageArrays, 0,  
									images.getWidth()*rate); 
							System.out.println(width_i+"____"+height_i);
							width_i +=(images.getWidth()*rate+zhjjg);
						}
						width_i=0;
						height_i+=(images.getHeight()*rate+zhjjg-extrag);
					}
				}else if (type==3) {
					for (int i = 0; i < m; i++) {
						for (int j = 0; j < n; j++) {
							ImageNew.setRGB(width_i, height_i, images.getWidth()*rate, images.getHeight()*rate, ImageArrays, 0,  
									images.getWidth()*rate); 
							System.out.println(width_i+"____"+height_i);
							width_i +=(images.getWidth()*rate+zhjjg);
						}
						width_i=0;
						height_i+=(images.getHeight()*rate+zhjjg);
					}
				}
	          
	          
	            //输出想要的图片  
	            ImageIO.write(ImageNew, targetFile.split("\\.")[1], new File(targetFile));  
	  
	        } catch (Exception e) {  
	            throw new RuntimeException(e);  
	        }  
				
	    }
	  
	    public int ChangeRgb(int value) {
			return (int) (value/25.4*300);
		}
	    /**
	     * 旋转
	     * 
	     * @param degree
	     *            旋转角度
	     * @throws Exception
	     */
	    public void spin(String openUrl, String saveUrl, String suffix,int degree) throws Exception {
	        int swidth = 0; // 旋转后的宽度
	        int sheight = 0; // 旋转后的高度
	        int x; // 原点横坐标
	        int y; // 原点纵坐标

	        File file = new File(openUrl);
	        if (!file.isFile()) {
	            throw new Exception("ImageDeal>>>" + file + " 不是一个图片文件!");
	        }
	        BufferedImage bi = ImageIO.read(file); // 读取该图片
	        // 处理角度--确定旋转弧度
	        degree = degree % 360;
	        if (degree < 0)
	            degree = 360 + degree;// 将角度转换到0-360度之间
	        double theta = Math.toRadians(degree);// 将角度转为弧度

	        // 确定旋转后的宽和高
	        if (degree == 180 || degree == 0 || degree == 360) {
	            swidth = bi.getWidth();
	            sheight = bi.getHeight();
	        } else if (degree == 90 || degree == 270) {
	            sheight = bi.getWidth();
	            swidth = bi.getHeight();
	        } else {
	            swidth = (int) (Math.sqrt(bi.getWidth() * bi.getWidth()
	                    + bi.getHeight() * bi.getHeight()));
	            sheight = (int) (Math.sqrt(bi.getWidth() * bi.getWidth()
	                    + bi.getHeight() * bi.getHeight()));
	        }

	        x = (swidth / 2) - (bi.getWidth() / 2);// 确定原点坐标
	        y = (sheight / 2) - (bi.getHeight() / 2);

	        BufferedImage spinImage = new BufferedImage(swidth, sheight,
	                bi.getType());
	        // 设置图片背景颜色
	        Graphics2D gs = (Graphics2D) spinImage.getGraphics();
	        gs.setColor(Color.white);
	        gs.fillRect(0, 0, swidth, sheight);// 以给定颜色绘制旋转后图片的背景

	        AffineTransform at = new AffineTransform();
	        at.rotate(theta, swidth / 2, sheight / 2);// 旋转图象
	        at.translate(x, y);
	        AffineTransformOp op = new AffineTransformOp(at,
	                AffineTransformOp.TYPE_BICUBIC);
	        spinImage = op.filter(bi, spinImage);
	        File sf = new File(saveUrl);
	        ImageIO.write(spinImage, suffix, sf); // 保存图片

	    }
	    public static void main(String[] args) throws Exception {
	    	
	    	Map<String,Object> map=new HashMap<String,Object>();
	    	map.put("type", 1);
	    	map.put("zhjj",5);//纸盒间距
	    	map.put("xd",16);//x边距离
	    	map.put("yd",6);//y边距离
	    	map.put("x",889);//x边
	    	map.put("y",521);//y边
	    	map.put("m",2);//x个数
	    	map.put("n",6);//y个数
	    	map.put("l",210);//张开图长
	    	map.put("w",255);//展开图宽
	    	map.put("extra",1);//移位
	    	map.put("file","E:/1.jpg");//文件地址
	    	map.put("targetFile","E:/21.jpg");//生成文件地址
	    	
	    }
}

