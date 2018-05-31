
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

import com.box.framework.utils.FileUtil;

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
public class MergePicture {
	Map<String,Object> map=new HashMap<String,Object>();
	int type;//1对插2头对头3平铺
	int m;//x个数
	int n;//y个数
	String file;//文件地址
	String targetFile;//生成文件地址
	
	public MergePicture(Map<String, Object> map) {
		super();
		this.map = map;
		this.type = Integer.parseInt(map.get("type").toString());
		this.m = Integer.parseInt(map.get("m").toString());
		this.n = Integer.parseInt(map.get("n").toString());
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
	  public boolean mergeImage() {  
		   boolean flag=false;
	    	File src = new File(file);  
	        BufferedImage images = null;
	        try {
				images = ImageIO.read(src);
			} catch (IOException e2) {
				
				// TODO Auto-generated catch block
				e2.printStackTrace();
				
			}
			 BufferedImage images1 = null;
		        
		        
		        int[] ImageArrays = null;
				try {
					
					images1 = ImageIO.read(src);
					 int wi = images1.getWidth();  
				     int he = images1.getHeight();  
				        ImageArrays = new int[wi * he];  
				        ImageArrays = images1.getRGB(0, 0, wi, he, ImageArrays, 0, wi);
				} catch (IOException e1) {
					
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}
		        int width_i=0;
		        int height_i=0;
		        // 生成新图片  
		        try {  
		            BufferedImage ImageNew = new BufferedImage(images1.getWidth()*n, images1.getHeight()*m, BufferedImage.TYPE_4BYTE_ABGR);
		            if (type==1) {
		            	for (int i = 0; i < m; i++) {
							for (int j = 0; j < n; j++) {
								ImageNew.setRGB(width_i, height_i, images1.getWidth(), images1.getHeight(), ImageArrays, 0,  
										images1.getWidth()); 
								System.out.println(width_i+"____"+height_i);
								width_i +=images1.getWidth();
							}
							width_i=0;
							height_i+=images1.getHeight()-25;
						}
					}else if (type==2) {
//						for (int i = 0; i < m; i++) {
//							for (int j = 0; j < n; j++) {
//								ImageNew.setRGB(width_i, height_i, images.getWidth(), images.getHeight(), ImageArrays, 0,  
//										images.getWidth()); 
//								System.out.println(width_i+"____"+height_i);
//								width_i +=images.getWidth();
//							}
//							width_i=0;
//							height_i+=(images.getHeight()*rate2+zhjjg-extrag);
//						}
					}else if (type==3) {
					for (int i = 0; i < m; i++) {
						for (int j = 0; j < n; j++) {
							ImageNew.setRGB(width_i, height_i, images1.getWidth(), images1.getHeight(), ImageArrays, 0,  
									images1.getWidth()); 
							System.out.println(width_i+"____"+height_i);
							width_i +=images1.getWidth();
						}
						width_i=0;
						height_i+=images1.getHeight();
					}
					}
		            //输出想要的图片  
		            File target = new File(targetFile);
		        	 if(!target.exists()){
		        		    //先得到文件的上级目录，并创建上级目录，在创建文件
		        		 target.getParentFile().mkdirs();
		        		    try {
		        		        //创建文件
		        		    	target.createNewFile();
		        		    } catch (IOException e) {
		        		        e.printStackTrace();
		        		    }
		        		}
		            ImageIO.write(ImageNew, targetFile.split("\\.")[1], target);  
		          
		            //FileUtil.transferAlpha(targetFile);
					flag=true;
					return flag;
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
	  
}

