
 /**
 * Project Name:Box-web
 * File Name:CropImage.java
 * Package Name:test
 * Date:2018年5月15日下午10:33:17
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

package test;

import java.awt.Color;  
import java.awt.Graphics;  
import java.awt.Image;  
import java.awt.MediaTracker;  
import java.awt.image.BufferedImage;  
import java.awt.image.MemoryImageSource;  
import java.awt.image.PixelGrabber;  
import java.io.File;  
import java.io.IOException;  
  
import javax.imageio.ImageIO;  
import javax.swing.JFrame;  
import javax.swing.JPanel;  
import javax.swing.border.LineBorder;  

/**
 * ClassName:CropImage
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2018年5月15日 下午10:33:17
 * @author   luowen
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class CropImage extends JFrame {
	  private static final long serialVersionUID = -2656049481667364613L;  
	  
	    public static void main( String[] args )  
	    {  
	        CropImage test = new CropImage();  
	        test.setVisible( true );  
	    }  
	      
	    private MediaTracker tracker;  
	    private PixelGrabber maskPg;  
	    private PixelGrabber sourcePg;  
	    private Image backgroundImage;  
	    private Image maskImage;    //蒙版图片  
	    private Image sourceImage;  //源图片  
	    private Image resultImage;  
	    private BackgroundPanel backPanel;  
	    private RocketPanel rocketPanel;  
	      
	    public CropImage()  
	    {  
	        super();  
	          
	        setBounds( 100, 100, 500, 500 );  
	        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );  
	          
	        loadImage();  
	        setResultImage( sourceImage, maskImage );  
	          
	        backPanel = new BackgroundPanel();  
	        backPanel.setLayout( null );  
	          
	        rocketPanel = new RocketPanel();  
	        rocketPanel.setBorder( new LineBorder( Color.WHITE ) );  
	          
	        setContentPane( backPanel );  
	        rocketPanel.setBounds( 200, 200, 42, 42 );  
	        backPanel.add( rocketPanel );  
	    }  
	      
	    /**  
	     * 加载图片  
	     */  
	    public void loadImage()  
	    {  
	        tracker = new MediaTracker( this );  
	          
	        try   
	        {  
	            backgroundImage = ImageIO.read( new File( "E:/3.jpg" ) );  
	            tracker.addImage( backgroundImage, 0 );  
	              
	            maskImage = ImageIO.read( new File( "E:/1.jpg" ) );  
	            tracker.addImage( maskImage, 1 );  
	              
	            sourceImage = ImageIO.read( new File( "E:/2.jpg" ) );   
	            tracker.addImage( sourceImage, 1 );  
	        }   
	        catch (IOException e)   
	        {  
	            e.printStackTrace();  
	        }  
	          
	        try   
	        {  
	            tracker.waitForID( 0 );  
	            tracker.waitForID( 1 );  
	        }   
	        catch (InterruptedException e)   
	        {  
	            e.printStackTrace();  
	        }  
	    }  
	      
	    /**  
	     * 获取新图片  
	     * @param source  
	     * @param mask  
	     */  
	    public void setResultImage( Image source, Image mask )  
	    {  
	        int mw = mask.getWidth( this );  
	        int mh = mask.getHeight( this );  
	        int[] mPixs = new int[ mw * mh ];   //蒙版图片像素  
	        int sw = source.getWidth( this );  
	        int sh = source.getHeight( this );  
	        int[] sPixs = new int[ sw * sh ];   //源图片像素  
	          
	          
	        maskPg = new PixelGrabber( mask, 0, 0, mw, mh, mPixs, 0, mw );  
	        sourcePg = new PixelGrabber( source, 0, 0, sw, sh, sPixs, 0, sw );  
	          
	        try   
	        {  
	            //获取像素  
	            maskPg.grabPixels();  
	            sourcePg.grabPixels();  
	        }   
	        catch (InterruptedException e)   
	        {  
	            System.err.println("interrupted waiting for pixels!");  
	            return;  
	        }  
	          
	        //结果图片的像素  
	        int[] rPixels = new int[ sw * sh ];  
	          
	        for ( int i = 0; i < rPixels.length; i++ )  
	        {  
	            //将源图片的像素赋给结果图片的像素  
	            rPixels[i] = sPixs[i];  
	              
	            int mPixelRgb = mPixs[i] & 0x00ffffff;  
	              
	            //判断背景像素的rgb是否小于0x00808080  
	            if ( mPixelRgb < 0x00808080 )   
	            {  
	                //背景透明处理  
	                rPixels[i] = rPixels[i] & 0x00ffffff;  
	            }  
	        }  
	          
	        //生成结果图片  
	        resultImage = createImage( new MemoryImageSource( sw, sh, rPixels, 0, sw) );  
	          
	        BufferedImage rImage = new BufferedImage( 100, 100, BufferedImage.TYPE_4BYTE_ABGR );  
	        Graphics g = rImage.getGraphics();  
	        g.drawImage( resultImage, 0, 0, null );  
	          
	        try   
	        {  
	            //保存图片  
	            ImageIO.write( rImage, "png", new File( "d:/jewel.png" ) );  
	        }   
	        catch (IOException e)   
	        {  
	            e.printStackTrace();  
	        }  
	    }  
	      
	    private class BackgroundPanel extends JPanel  
	    {  
	        private static final long serialVersionUID = 4503016479516384644L;  
	  
	        @Override  
	        protected void paintComponent(Graphics g)   
	        {  
	            super.paintComponent(g);  
	              
	            if ( backgroundImage != null )   
	            {  
	                int w = BackgroundPanel.this.getWidth();  
	                int h = BackgroundPanel.this.getHeight();  
	                  
	                g.drawImage( backgroundImage, 0, 0, w, h, null );  
	            }  
	        }     
	    }  
	      
	    private class RocketPanel extends JPanel  
	    {  
	        private static final long serialVersionUID = 1204792957097831219L;  
	          
	        public RocketPanel()  
	        {  
	            super();  
	            setOpaque( false );  
	        }  
	  
	        @Override  
	        protected void paintComponent(Graphics g)   
	        {  
	            super.paintComponent(g);  
	              
	            if ( resultImage != null )   
	            {  
	                int w = RocketPanel.this.getWidth();  
	                int h = RocketPanel.this.getHeight();  
	                  
	                g.drawImage( resultImage, 0, 0, w, h, null );  
	            }  
	        }  
	    }  
}

