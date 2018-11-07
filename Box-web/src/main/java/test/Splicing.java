
 /**
 * Project Name:Box-web
 * File Name:ExcelTest.java
 * Package Name:test
 * Date:2018年4月23日上午11:09:42
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;

import com.box.framework.algriothm.MergePicture;
import com.box.framework.utils.FileUtil;
import com.box.framework.utils.PropertiesUtil;


import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * ClassName:ExcelTest
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2018年4月23日 上午11:09:42
 * @author   luowen
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class Splicing {
	
	
	public static void main(String[] args) throws Exception {
		//输入图片地址
		//String imgs="E:/1.jpg";
		//调用方法生成图片
		//Splicing.mergeImage(imgs,2,2,"E:/5.jpg");
		//Splicing.createPicTwo2(2,2);
//		InputStream in = new FileInputStream("E:/5.jpg");//图片路径
//        BufferedImage image = ImageIO.read(new File("E:/5.jpg"));
//        Graphics g = image.getGraphics();
//        g.setColor(Color.RED);//画笔颜色
//        g.drawRect(0, 0, image.getWidth(), image.getHeight());//矩形框(原点x坐标，原点y坐标，矩形的长，矩形的宽)
//        //g.dispose();
//        FileOutputStream out = new FileOutputStream("E:/6.jpg");//输出图片的地址
//        ImageIO.write(image, "jpeg", out);
		//旋转
//		 String openUrl="E:/1.jpg"; // 原始图片打开路径
//		    String saveUrl="E:/qq.jpg"; // 新图保存路径
//		    
//		    String suffix="jpg"; // 新图类型 只支持gif,jpg,png
//		    Splicing.spin(openUrl, saveUrl, suffix, 180);
		
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
    	map.put("extra",75);//移位
    	map.put("file","E:/1.jpg");//文件地址
    	map.put("targetFile","E:/2.jpg");//生成文件地址
    	//Splicing.mergeImage(map);
    	MergePicture mergePicture = new MergePicture(map);
		
		if (mergePicture.mergeImage()) {
			System.out.println(true);
		}else{
			System.out.println(false);
		}
    	/*//Splicing.zoomImage("E:/1.jpg","E:/1.jpg",50,50); 
    	Image ima=ImageIO.read(new File("E:/1.jpg"));  
        BufferedImage bufIma=new BufferedImage(ima.getWidth(null),ima.getHeight(null),BufferedImage.TYPE_INT_BGR);  
          
        //这里是关键部分  
        Graphics2D g=bufIma.createGraphics();  
        bufIma = g.getDeviceConfiguration().createCompatibleImage(ima.getWidth(null), ima.getHeight(null), Transparency.TRANSLUCENT);  
        g = bufIma.createGraphics();  
          
        g.drawImage(ima, 0, 0, null);   
        ImageIO.write(bufIma, "jpg", new File("E:/3.jpg"));  */
	    }
	
   
	  public static void mergeImage(Map<String,Object> map) {  
		  int type = Integer.parseInt(map.get("type").toString());
			int zhjj = Integer.parseInt(map.get("zhjj").toString());
			int xd = Integer.parseInt(map.get("xd").toString());
			int yd = Integer.parseInt(map.get("yd").toString());
			int x = Integer.parseInt(map.get("x").toString());
			int y = Integer.parseInt(map.get("y").toString());
			int m = Integer.parseInt(map.get("m").toString());
			int n = Integer.parseInt(map.get("n").toString());
			int l = Integer.parseInt(map.get("l").toString());
			int w = Integer.parseInt(map.get("w").toString());
			int extra = Integer.parseInt(map.get("extra").toString());
			String file = map.get("file").toString();
			String targetFile = map.get("targetFile").toString();
	    	File src = new File(file);  
	        BufferedImage images = null;
	        try {
				images = ImageIO.read(src);
			} catch (IOException e2) {
				
				// TODO Auto-generated catch block
				e2.printStackTrace();
				
			}
			
			float boxlrgb=ChangeRgb(l);
			float boxwrgb=ChangeRgb(w);
			float xdrgb=ChangeRgb(xd);
			float ydrgb=ChangeRgb(yd);
			float zhjjrgb=ChangeRgb(zhjj);
			float extrargb=ChangeRgb(extra);
			
//	        
	        float r1=(float) (boxlrgb*1.0/images.getWidth());
	        float r2= (float) (boxwrgb*1.0/images.getHeight());
	        int width=2000;int height=2000;
	        int width1=(int) (2*xdrgb + m * images.getWidth()*r1+(m - 1) * zhjjrgb);
	        int height1= (int) (ydrgb + n * images.getHeight()*r2 + (n - 1) * zhjjrgb);
	        double rate1= width*1.0/width1;
	        double rate2=height*1.0/height1;
	        //缩小后长宽
	        int w1=(int) ((int)images.getWidth()*rate1);
	        int h1=(int) ((int)images.getWidth()*rate2);
//	        Image Itemp = images.getScaledInstance(w1, h1, images.SCALE_SMOOTH);//设置缩放目标图片模板
//	        AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(rate1, rate2), null);
//	        Itemp = ato.filter(images, null);
//	        try {
//	        	 ImageIO.write((BufferedImage) Itemp,file.substring(file.lastIndexOf(".")+1), src); //写入缩减后的图片
//			} catch (IOException e1) {
//				
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//				
//			} //写入缩减后的图片
//	        //File src = new File(file);  
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
	        System.out.println(images.getWidth()+"--"+images.getHeight());
	        System.out.println(images1.getWidth()+"--"+images1.getHeight());
	        // 生成新图片  
	        try {  
	            BufferedImage ImageNew = new BufferedImage(images1.getWidth()*n, images1.getHeight()*m, BufferedImage.TYPE_4BYTE_ABGR);
	            //这里是关键部分  
//	            Graphics2D g=images1.createGraphics();  
//	            ImageNew = g.getDeviceConfiguration().createCompatibleImage(images1.getWidth(null), images1.getHeight(null), Transparency.TRANSLUCENT);  
//	            g = ImageNew.createGraphics();  
//	              
//	            g.drawImage(images1, 0, 0, null);  
	            if (type==1) {
	            	for (int i = 0; i < m; i++) {
						for (int j = 0; j < n; j++) {
							ImageNew.setRGB(width_i, height_i, images1.getWidth(), images1.getHeight(), ImageArrays, 0,  
									images1.getWidth()); 
							System.out.println(width_i+"____"+height_i);
							width_i +=images1.getWidth();
						}
						width_i=0;
						height_i+=images1.getHeight()-30;
					}
				}else if (type==2) {
//					for (int i = 0; i < m; i++) {
//						for (int j = 0; j < n; j++) {
//							ImageNew.setRGB(width_i, height_i, images.getWidth(), images.getHeight(), ImageArrays, 0,  
//									images.getWidth()); 
//							System.out.println(width_i+"____"+height_i);
//							width_i +=images.getWidth();
//						}
//						width_i=0;
//						height_i+=(images.getHeight()*rate2+zhjjg-extrag);
//					}
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
	            ImageIO.write(ImageNew, targetFile.split("\\.")[1], new File(targetFile));  
				FileUtil.transferAlpha(targetFile); 
	        } catch (Exception e) {  
	            throw new RuntimeException(e);  
	        } 
				
	    }
    public static float ChangeRgb(int value) {
		return (float) (value/25.4*300);
	}
    /**
     * 旋转
     * 
     * @param degree
     *            旋转角度
     * @throws Exception
     */
    public static void spin(String openUrl, String saveUrl, String suffix,int degree) {
        int swidth = 0; // 旋转后的宽度
        int sheight = 0; // 旋转后的高度
        int x; // 原点横坐标
        int y; // 原点纵坐标

        File file = new File(openUrl);
//        if (!file.isFile()) {
//            throw new Exception("ImageDeal>>>" + file + " 不是一个图片文件!");
//        }
        BufferedImage bi;
		try {
			bi = ImageIO.read(file);
		
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
        //gs.setColor(Color.white);
        gs.fillRect(0, 0, swidth, sheight);// 以给定颜色绘制旋转后图片的背景

        AffineTransform at = new AffineTransform();
        at.rotate(theta, swidth / 2, sheight / 2);// 旋转图象
        at.translate(x, y);
        AffineTransformOp op = new AffineTransformOp(at,
                AffineTransformOp.TYPE_BICUBIC);
        spinImage = op.filter(bi, spinImage);
        File sf = new File(saveUrl);
        ImageIO.write(spinImage, suffix, sf); // 保存图片
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} // 读取该图片

    }/*
     * 图片缩放,w，h为缩放的目标宽度和高度
     * src为源文件目录，dest为缩放后保存目录
     */
    public static void zoomImage(String src,String dest,int w,int h) throws Exception {
        
        double wr=0,hr=0;
        File srcFile = new File(src);
        File destFile = new File(dest);

        BufferedImage bufImg = ImageIO.read(srcFile); //读取图片
        Image Itemp = bufImg.getScaledInstance(w, h, bufImg.SCALE_SMOOTH);//设置缩放目标图片模板
        
        wr=w*1.0/bufImg.getWidth();     //获取缩放比例
        hr=h*1.0 / bufImg.getHeight();

        AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
        Itemp = ato.filter(bufImg, null);
        try {
            ImageIO.write((BufferedImage) Itemp,dest.substring(dest.lastIndexOf(".")+1), destFile); //写入缩减后的图片
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    /**
    * 对图片进行强制放大或缩小
    * 
    * @param originalImage
    *            原始图片
    * @return
    */
   public static BufferedImage zoomInImage(BufferedImage originalImage, int width, int height) {
      BufferedImage newImage = new BufferedImage(width, height, originalImage.getType());

      Graphics g = newImage.getGraphics();
      g.drawImage(originalImage, 0, 0, width, height, null);
      g.dispose();
      return newImage;
   }
}

