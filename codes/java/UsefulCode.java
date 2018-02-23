/*
 * 文件名：UsefulCode.java
 * 版权：Copyright by www.jacky.com
 * 描述：
 * 修改人：Administrator
 * 修改时间：2018年2月23日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;


public class UsefullCode {

	public static void fileCopy( File in, File out )  
            throws IOException  
    {  
        FileChannel inChannel = new FileInputStream( in ).getChannel();  
        FileChannel outChannel = new FileOutputStream( out ).getChannel();  
        try 
        {  
//          inChannel.transferTo(0, inChannel.size(), outChannel);      // original -- apparently has trouble copying large files on Windows  

            // magic number for Windows, 64Mb - 32Kb)  
            int maxCount = (64 * 1024 * 1024) - (32 * 1024);  
            long size = inChannel.size();  
            long position = 0;  
            while ( position < size )  
            {  
               position += inChannel.transferTo( position, maxCount, outChannel );  
            }  
        }  
        finally 
        {  
            if ( inChannel != null )  
            {  
               inChannel.close();  
            }  
            if ( outChannel != null )  
            {  
                outChannel.close();  
            }  
        }  
    }
	
	static void createThumbnail(String filename, int thumbWidth, int thumbHeight, int quality, String outFilename)  
	        throws InterruptedException, FileNotFoundException, IOException  
	    {  
	        // load image from filename  
	        Image image = Toolkit.getDefaultToolkit().getImage(filename);  
	        MediaTracker mediaTracker = new MediaTracker(new Container());  
	        mediaTracker.addImage(image, 0);  
	        mediaTracker.waitForID(0);  
	        // use this to test for errors at this point: System.out.println(mediaTracker.isErrorAny());  

	        // determine thumbnail size from WIDTH and HEIGHT  
	        double thumbRatio = (double)thumbWidth / (double)thumbHeight;  
	        int imageWidth = image.getWidth(null);  
	        int imageHeight = image.getHeight(null);  
	        double imageRatio = (double)imageWidth / (double)imageHeight;  
	        if (thumbRatio < imageRatio) {  
	            thumbHeight = (int)(thumbWidth / imageRatio);  
	        } else {  
	            thumbWidth = (int)(thumbHeight * imageRatio);  
	        }  

	        // draw original image to thumbnail image object and  
	        // scale it to the new size on-the-fly  
	        BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);  
	        Graphics2D graphics2D = thumbImage.createGraphics();  
	        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
	        graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);  

	        // save thumbnail image to outFilename  
	        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFilename));  
	        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
	        JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);  
	        quality = Math.max(0, Math.min(quality, 100));  
	        param.setQuality((float)quality / 100.0f, false);  
	        encoder.setJPEGEncodeParam(param);  
	        encoder.encode(thumbImage);  
	        out.close();  
	    }
}
