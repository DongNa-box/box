
 /**
 * Project Name:Box-framework
 * File Name:FileUtil.java
 * Package Name:com.box.framework.utils
 * Date:2018年4月10日下午4:42:27
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.framework.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;

/**
 * ClassName:FileUtil
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2018年4月10日 下午4:42:27
 * @author   cheng
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class FileUtil {
	
	 private static final int BUFFER_SIZE = 10 * 1024;
//	 private static final String path="mobile"+File.separator+"files"+File.separator+"BoxType"+File.separator;

	 /**
	  * 上传图片
	  * upload:(这里用一句话描述这个方法的作用).
	  *
	  * @author cheng
	  * @param inputStream 文件
	  * @param fileName 文件名称
	  * @param id 纸盒id
	  * @param detail 存储的位置名称
	  * @since JDK 1.8
	  */
     public static void upload(InputStream inputStream,String fileName,String path){
    	  //将图片资源进行存储
         //存储路径设置：/根目录/files/BoxType/id号/dime/图片
//    	  String path1=path+id+File.separator+detail;
//		  System.out.println("path1-->"+path1);
//		  String path2=File.separator+"home"+File.separator+"ubuntu"+File.separator+"tomcat"+File.separator+path1;
//		  System.out.println("path2-->"+path2);
//		  String path3="E:"+File.separator+path;
		  File file = new File(path);
		  if (!file.exists()) {
			file.mkdirs();
		  }
		  File[] listFiles = file.listFiles();
		  if(listFiles.length > 0){
		   //文件夹下有文件，删除文件
			delAllFile(path); //删除完里面所有内容
		  }
		File imageFile = new File(path+ File.separator+ fileName);
		try {
			copyFile(inputStream, imageFile);
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

     }

     /**
      * 删除指定文件夹下所有文件
      * delAllFile:(这里用一句话描述这个方法的作用).
      *
      * @author cheng
      * @param 文件夹完整绝对路径
      * @return
      * @since JDK 1.8
      */
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				// delFolder(path + "/" + tempList[i]);//再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}
	/**
	 * 上传文件处理存放
	 */
	private static void copyFile(InputStream inputStream, File dst) throws Exception {

		try {
			InputStream in = null;
			OutputStream out = null;

			try {
				in = new BufferedInputStream(inputStream,
						BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	/**
	 * 平面展开图处理
	 * @param url
	 * @param id
	 * @param pla
	 * @param finalUrl
	 * @return
	 */
	 public static byte[] transferAlpha(String url,String finalUrl) {
  	   ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
  	   File file = new File(url);
  	   InputStream is;
  	   try {
  	    is = new FileInputStream(file);
  	  //如果是MultipartFile类型，那么自身也有转换成流的方法：is = file.getInputStream();
  	    BufferedImage bi=ImageIO.read(is);
  	    Image image=(Image)bi;
  	    ImageIcon imageIcon = new ImageIcon(image);
  	    BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(),
  	      BufferedImage.TYPE_4BYTE_ABGR);
  	    Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();
  	    g2D.drawImage(imageIcon.getImage(), 0, 0, imageIcon.getImageObserver());
  	    int alpha = 0;
  	    for (int j1 = bufferedImage.getMinY(); j1 < bufferedImage
  	      .getHeight(); j1++) {
  	     for (int j2 = bufferedImage.getMinX(); j2 < bufferedImage
  	       .getWidth(); j2++) {
  	      int rgb = bufferedImage.getRGB(j2, j1);
  	    
  	      int R =(rgb & 0xff0000 ) >> 16 ;
  	      int G= (rgb & 0xff00 ) >> 8 ;
  	      int B= (rgb & 0xff );
  	      if(((255-R)<30) && ((255-G)<30) && ((255-B)<30)){
  	       rgb = ((alpha + 1) << 24) | (rgb & 0x00ffffff);
  	      }

  	      bufferedImage.setRGB(j2, j1, rgb);

  	     }
  	    }

  	    g2D.drawImage(bufferedImage, 0, 0, imageIcon.getImageObserver());
  	   // String path3="E:"+File.separator+"apache-tomcat-8.5.23"+File.separator+finalUrl;
		//System.out.println("-->"+path3);	
  	    //String path2=File.separator+"home"+File.separator+"ubuntu"+File.separator+"tomcat"+File.separator+finalUrl;
		File file1 = new File(finalUrl);
		if (!file1.exists()) {
			file.mkdirs();
		}
		ImageIO.write(bufferedImage, "png", file1);

  	   } catch (Exception e) {
  	       e.printStackTrace();
  	   }finally{
  	   
  	   }
  	   return byteArrayOutputStream.toByteArray();
  	}
	 
	  /**
	     * <p>Title: thumbnailImage</p>
	     * <p>Description: 根据图片路径生成缩略图 </p>
	     * @param imagePath    原图片路径
	     * @param w            缩略图宽
	     * @param h            缩略图高
	     * @param prevfix    生成缩略图的前缀
	     * @param force        是否强制按照宽高生成缩略图(如果为false，则生成最佳比例缩略图)
	     */
	    public static void thumbnailImage(File srcImg, File output, int w, int h){
	        if(srcImg.exists()){
	            try {
	                // ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif]
	                String types = Arrays.toString(ImageIO.getReaderFormatNames()).replace("]", ",");
	                String suffix = null;
	                // 获取图片后缀
	                if(srcImg.getName().indexOf(".") > -1) {
	                    suffix = srcImg.getName().substring(srcImg.getName().lastIndexOf(".") + 1);
	                }// 类型和图片后缀全部小写，然后判断后缀是否合法
	                if(suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase()+",") < 0){
	                    //log.error("Sorry, the image suffix is illegal. the standard image suffix is {}." + types);
	                    return ;
	                }
	                //log.debug("target image's size, width:{}, height:{}.",w,h);
	                Image img = ImageIO.read(srcImg);
	                // 根据原图与要求的缩略图比例，找到最合适的缩略图比例
	
	                    int width = img.getWidth(null);
	                    int height = img.getHeight(null);
	                    if((width*1.0)/w < (height*1.0)/h){
	                        if(width > w){
	                            h = Integer.parseInt(new java.text.DecimalFormat("0").format(height * w/(width*1.0)));
	                            //log.debug("change image's height, width:{}, height:{}.",w,h);
	                        }
	                    } else {
	                        if(height > h){
	                            w = Integer.parseInt(new java.text.DecimalFormat("0").format(width * h/(height*1.0)));
	                            //log.debug("change image's width, width:{}, height:{}.",w,h);
	                        }
	                    }
	   
	                BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	                Graphics g = bi.getGraphics();
	                g.drawImage(img, 0, 0, w, h, Color.white, null);
	                g.dispose();
	                // 将图片保存在原目录并加上前缀
	                ImageIO.write(bi, suffix, output);
	            } catch (IOException e) {
	              // log.error("generate thumbnail image failed.",e);
	            }
	        }else{
	            //log.warn("the src image is not exist.");
	        }
	    }
        /**
         * 文件路径
         * getSize:(这里用一句话描述这个方法的作用).
         *
         * @author cheng
         * @param url
         * @return
         * @since JDK 1.8
         */
	    public static int[] getSize(String url){
	    	int[] result=new int[4];
	        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	     	File file = new File(url);
	     	InputStream is;
	     	try {
	     	 is = new FileInputStream(file);
	      	 BufferedImage bi=ImageIO.read(is);
	   	    Image image=(Image)bi;
	   	    ImageIcon imageIcon = new ImageIcon(image);
	   	    System.out.println(imageIcon.getIconWidth()+"-->"+imageIcon.getIconHeight());
	   	    BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(),
	   	      BufferedImage.TYPE_4BYTE_ABGR);
	   	    Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();
	   	    g2D.drawImage(imageIcon.getImage(), 0, 0, imageIcon.getImageObserver());
	   	    int alpha = 0;
//	   	    System.out.println(bufferedImage.getMinY());
//	   	    System.out.println(bufferedImage.getMinX());
//	   	    System.out.println(bufferedImage.getHeight());
//	   	    System.out.println(bufferedImage.getWidth());
	   	    int[] x=new int[bufferedImage.getWidth()];
	   	    int j=0;
	   	    int[] y=new int[bufferedImage.getHeight()];
	   	    int k=0;
	   	    int[] center=new int[bufferedImage.getWidth()];
	    	    int n=0;
	   	    for (int j1 =0; j1 <bufferedImage.getHeight(); j1++) {
	   	    	 int count=0;
	   	     for (int j2 =0; j2 <bufferedImage.getWidth(); j2++) {
	   	      int rgb = bufferedImage.getRGB(j2, j1);
	   	       if(rgb!=-1){
	   	    	   count++;
	   	    	   if(count==1){
	   	    		  x[j]=j2;
	   	    		  y[k]=j1;
	   	    		  j++;
	   	    		  k++;
	   	    	   }
	   	       }
	   	     }  
	   	   }
	   	    //获取y的第一个值是起始点的纵坐标
	   	    int beginY=y[0];
	   	    int endY=y[k-1];
	   	    int height=endY-beginY;//图片的高度
	   	    System.out.println("Y的开始"+beginY+","+endY+","+height);
	   	    //获取x的最小值是起始点的横坐标
	   	    int min=x[0];
	   	    for(int i=0;i<j;i++){
	   	    	if(x[i]<min){
	   	    		min=x[i];
	   	    	}
	   	    }
	   	    int beginX=min;
	   	    System.out.println(min);
	   	    //找到第一个点的横坐标的值为min值
	   	    int key=0;
	   	    for (int j1 =0; j1 <bufferedImage.getHeight(); j1++) {
	   	    	 int count=0;
	   	        int rgb = bufferedImage.getRGB(min, j1);
	   	         if(rgb!=-1){
	   	        	 count++;
	   	        	 if(count==1){
	   	        		 key=j1;
	   	        		 break;
	   	        	 }
	   	         }
	   	    }
	   	   // System.out.println("key:"+key);
	   	    for (int j1 =key; j1 <key+1; j1++) {
	    	       for (int j2 =min; j2 <bufferedImage.getWidth(); j2++) {
	    	        int rgb = bufferedImage.getRGB(j2, j1);
	    	         if(rgb!=-1){
	    	        	 center[n]=j2;
	    	        	 n++;
	    	         }
	    	        }
	       	   }
	   	    //center数组中的最后一个值是宽度的最大值
	   	    int width=center[n-1]-min;
	   	    System.out.println("width:"+width);
	   	    result[0]=beginX;
	   	    result[1]=beginY;
	   	    result[2]=width;
	   	    result[3]=height;
	   	    return result;
	     	   }catch (Exception e) {
	  	       e.printStackTrace();
	  	   }finally{
	  	   
	  	   }
			return result;
	    }
	    /**
	     * <p>Title: cutImage</p>
	     * <p>Description:  根据原图与裁切size截取局部图片</p>
	     * @param srcImg    源图片
	     * @param output    图片输出流
	     * @param rect      需要截取部分的坐标和大小
	     */
	    public static void cutImage(File srcImg, File output,Rectangle rect){
	        if(srcImg.exists()){
	            java.io.FileInputStream fis = null;
	            ImageInputStream iis = null;
	            try {
	                fis = new FileInputStream(srcImg);
	                // ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif]
	                String types = Arrays.toString(ImageIO.getReaderFormatNames()).replace("]", ",");
	                String suffix = null;
	                // 获取图片后缀
	                if(srcImg.getName().indexOf(".") > -1) {
	                    suffix = srcImg.getName().substring(srcImg.getName().lastIndexOf(".") + 1);
	                }// 类型和图片后缀全部小写，然后判断后缀是否合法
	                if(suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase()+",") < 0){
	                    //log.error("Sorry, the image suffix is illegal. the standard image suffix is {}." + types);
	                    return ;
	                }
	                // 将FileInputStream 转换为ImageInputStream
	                iis = ImageIO.createImageInputStream(fis);
	                // 根据图片类型获取该种类型的ImageReader
	                ImageReader reader = ImageIO.getImageReadersBySuffix(suffix).next();
	                reader.setInput(iis,true);
	                ImageReadParam param = reader.getDefaultReadParam();
	                param.setSourceRegion(rect);
	                BufferedImage bi = reader.read(0, param);
	                ImageIO.write(bi, suffix, output);
	            } catch (FileNotFoundException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            } finally {
	                try {
	                    if(fis != null) fis.close();
	                    if(iis != null) iis.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }else {
	            //log.warn("the src image is not exist.");
	        }
	    }
}

