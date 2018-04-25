import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.box.framework.utils.FileUtil;

public class ImageController {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String uploadPath="D:/Program Files/apache-tomcat-6.0.48/webapps/Box-management/images/BoxType/1524404892678000";
		String fileName="1524404892678000-2018-04-22-21-48-12.jpg";
		//文件存储路径
		String filePath=uploadPath+File.separator+"plan"+File.separator+fileName;
        //目标路径
		String outFilePath=uploadPath+File.separator+"pla"+File.separator+fileName;
        //将平面展开图透明化处理
		//获取有效的尺寸
        int[] r=FileUtil.getSize(filePath);
		//裁剪图片
		FileUtil.cutImage(new File(filePath),outFilePath,new Rectangle(r[0],r[1],r[2],r[3]));
		//缩放图片
		FileUtil.thumbnailImage(new File(outFilePath), outFilePath,150, 100);		
		//图片透明化处理
		FileUtil.transferAlpha(outFilePath); 
	}

}
