package travel.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * 
 * @brief 	FileUploadCreateDateFolderUtil
 * @author 	용산프로젝트/어드민/유창근
 * @version 1.0
 * @date    생성: 2014. 11. 25.
 * @date    최종수정: 2014. 11. 25.
 * @remark
 */
public class FileUploadCreateDateFolderUtil {
	
	private static String DEFAULTROOT = "/data/upload/product";
	
	/**
	 * 해당년 리턴
	 * @return
	 */
	public static String getYear(){
		Date nowDate = new Date();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy");
		return simple.format(nowDate);
	}
	
	/**
	 * 해당 년에 월 리턴
	 * @return
	 */
	public static String getMonth(){
		Date nowDate = new Date();
		SimpleDateFormat simple = new SimpleDateFormat("MM");
		return simple.format(nowDate);
	}
	
	/**
	 * 해당 년에 월 일 리턴
	 * @return
	 */
	public static String getDay(){
		Date nowDate = new Date();
		SimpleDateFormat simple = new SimpleDateFormat("dd");
		return simple.format(nowDate);
	}
	
	public static String getFullDate(){
		Date nowDate = new Date();
		SimpleDateFormat simple = new SimpleDateFormat("yyyyMMddHHmmss");
		return simple.format(nowDate);
	}
	
	
	/**
	 * server default Root/년/월 리턴
	 * @return
	 */
	public static String getUploadFolder1(){
		 return DEFAULTROOT + "/" + getYear() + "/" + getMonth();
	}
	
	/**
	 * server default Root/년/월/일 리턴
	 * @return
	 */
	public static String getUploadFolder2(){
		 return DEFAULTROOT + "/" + getYear() + "/" + getMonth() + "/" + getDay();
	}	
	
	
	public static String getDate(){
		return getYear() + getMonth() + getDay();
	}
	
	//파일 업로드 경로 생성
	public static void createFileUploadFolder(String savePath) {
		// TODO Auto-generated method stub
		boolean flag = false;
		File file = new File(savePath);
		if(!file.exists()){
			flag =	file.mkdirs();
		}
		//return flag;
	}
	
	public static String[] getFileNameSplit(String fileName){
		String [] fileSplit = new String[2];	
		String ext = "";
		String originalFileName  = "";
		
		if(fileName.lastIndexOf(".")  <= 0){
			originalFileName = fileName;
			ext = ""; 
		}else{
			originalFileName = fileName.substring(0, fileName.lastIndexOf("."));
			ext = fileName.substring(fileName.lastIndexOf("."), fileName.length()); 
		}
		
		fileSplit[0] = originalFileName;
		fileSplit[1] = ext;
		return fileSplit;
	}
	
}
