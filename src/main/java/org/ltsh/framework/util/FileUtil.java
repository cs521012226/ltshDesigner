package org.ltsh.framework.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 文件操作工具类
 * @author Charles
 *
 */
public class FileUtil {
	/**
	 * log4j日志
	 */
	private static final Logger logger = Logger.getLogger(FileUtil.class);
	
	public static final long SIZE_KB = 1024L; 
	public static final long SIZE_MB = 1024L * SIZE_KB;
	public static final long SIZE_GB = 1024L * SIZE_MB;
	public static final long SIZE_TB = 1024L * SIZE_GB;
	
	private static Map<String,String> options = new HashMap<String,String>();
	private static final String FILE_PATH = "filePath";
	private static final String FILE_NAME = "fileName";
	
	/** 
	 * @Author: Charles
	 * @Description: 获取指定文件的大小
	 * @param fileFullName	目录+文件名
	 * @return String: 返回大小字符串
	 */
	public static String getFileSize(String fileFullName){
		String resultSize = null;
		
		File file = new File(fileFullName);
		if(file.exists() && file.isFile()){
			long fileSize = file.length();
			if(fileSize >= SIZE_TB){
				resultSize = fileSize / 1.0 / SIZE_TB + " TB";
			}else if(fileSize >= SIZE_GB){
				resultSize = fileSize / 1.0 / SIZE_GB + " GB";
			}if(fileSize >= SIZE_MB){
				resultSize = fileSize / 1.0 / SIZE_MB + " MB";
			}if(fileSize >= SIZE_KB){
				resultSize = fileSize / 1.0 / SIZE_KB + " KB";
			}else{
				resultSize = fileSize + " B";
			}
		}
		return resultSize;
	}
	
	
	/** 
	 * @Author: Charles
	 * @Description: 过滤文件路径，替换为系统指定的File.separator
	 * @param path		文件路径 例： D:/aaa/bbb/ccc.txt 则返回 [ D:/aaa/bbb, ccc.txt] 数组
	 * @return String: 返回 [ 路径, 文件名 ] 的数组
	 */
	public static Map<String,String> filterPathSeparator(String path){
		String fullPath = null;
		
		if(path != null){
			if("\\".equals(File.separator)){
				fullPath = path.replaceAll("\\/", "\\\\");
			}else if("/".equals(File.separator)){
				fullPath = path.replaceAll("\\/", "/");
			}
		}
		String fileName = null;
		String pathName = null;
		
		//如果路径里没有路径分割而有文件名后缀，则路径就是文件名
		if((fullPath.lastIndexOf(File.separator) == -1) && (fullPath.lastIndexOf(".") != -1)){
			fileName = fullPath;
		}else if((fullPath.lastIndexOf(File.separator) == -1) && (fullPath.lastIndexOf(".") == -1)){
			pathName = fullPath;
		}else{
			
			fileName = fullPath.substring(fullPath.lastIndexOf(File.separator) + 1);
			pathName = fullPath.substring(0, fullPath.lastIndexOf(File.separator));
			
			if(fileName.lastIndexOf(".") == -1){	//没有文件后缀就当目录处理
				pathName = pathName + File.separator + fileName;
				fileName = null;
			}
			if("".equals(pathName.trim())){
				pathName = null;
			}
		}
		options.put(FILE_PATH, pathName);
		options.put(FILE_NAME, fileName);
		return options;
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 创建文件（文件不存在就创建文件）
	 * @param fileFullName	目录+文件名称
	 * @return File: 如果文件存在，则返回该文件对象，如果不存在，则新建一个文件并返回该对象
	 */
	public static File createFile(String fileFullName){
		Map<String,String> pathAndFile = filterPathSeparator(fileFullName);
		String path = pathAndFile.get(FILE_PATH);	//路径
		String fileName = pathAndFile.get(FILE_NAME);	//文件名称
		
		File dir = createFloder(path);	//先创建目录
		File file = null;
		
		try{
			if(fileName != null){
				file = new File(dir, fileName);
				if(!file.exists()){
					file.createNewFile();
				}
			}
		}catch(IOException e){
			logger.error("创建文件异常:", e);
		}
		return file;
	}
	/** 
	 * @Author: Charles
	 * @Description: 删除指定文件
	 * @param fileFullName	文件名称
	 * @return boolean: 返回是否删除成功
	 */
	public static boolean delFile(String fileFullName){
		File file = null;
		if(fileFullName != null && !"".equals(fileFullName)){
			file = new File(fileFullName);
			if(file.exists() && file.isFile()){
				return file.delete();
			}
		}
		return false;
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 删除当前目录及目录内所有目录文件，或只删除该目录下的所有目录及文件
	 * @param dirPath	目录路径
	 * @param isDelThisFloder	是否删除当前文件夹
	 * @return boolean: 
	 */
	public static boolean delFloders(String dirPath, boolean isDelThisFloder){
		File dir = null;
		
		try{
			if(dirPath != null && !"".equals(dirPath)){
				dir = new File(dirPath);
				if(dir.exists() && dir.isDirectory()){
					File[] listFile = dir.listFiles();	//当前目录下的所有文件
					for(File subFileOrDir : listFile){
						if(subFileOrDir.isFile()){
							delFile(subFileOrDir.getCanonicalPath());	//删除文件
						}
						if(subFileOrDir.isDirectory()){
							delFloders(subFileOrDir.getCanonicalPath(), true);	//递归删除子目录下的文件或者目录
						}
					}
					if(isDelThisFloder){	//是否删除当前文件夹
						dir.delete();
					}
				}
			}
		}catch(IOException e){
			logger.error("删除目录时发生异常", e);
		}
		return false;
	}
	
	
	/** 
	 * @Author: Charles
	 * @Description: 创建目录
	 * @param path	路径
	 * @return File: 如果目录存在，则返回目录File对象，如果不存在，则新建目录并返回这个目录File对象
	 */
	public static File createFloder(String path){
		File dir = null;
		if(path != null){
			dir = new File(path);	//目录文件
			if(!dir.exists()){
				dir.mkdirs();
			}
		}
		return dir;
	}
	
	
	/** 
	 * @Author: Charles
	 * @Description: 复制文件到指定目录
	 * @param sourcesFilePath	待复制的文件路径名称
	 * @param targetFilePath	目标目录文件名称
	 * @param isOverOldFile	是否覆盖目标文件（如果覆盖的话，目标原来的文件会消失，如果不覆盖，则复制文件失败，谨慎使用）
	 * @return boolean: 
	 */
	public static boolean copyFile(String sourcesFilePath, String targetFilePath, boolean isOverOldFile) {  
		
		InputStream bufIn = null;
		OutputStream bufOut = null;
        try {  
            File sourcesFile = new File(sourcesFilePath);  
            File targetFile = createFile(targetFilePath);	//创建一个目标文件
            
            if (sourcesFile.exists() && sourcesFile.isFile() && isOverOldFile) { 		// 源文件存在且指定覆盖目标文件时，才执行复制操作
            	bufIn = new BufferedInputStream(new FileInputStream(sourcesFile)); // 读入原文件  
            	bufOut = new BufferedOutputStream(new FileOutputStream(targetFile)); // 写入目标文件  
            	
            	byte[] b = new byte[(int)SIZE_KB * 10];		//一次复制10KB的数据
                int readInt;
                while((readInt = bufIn.read(b)) != -1){
                	bufOut.write(b, 0, readInt);
                }
                bufOut.flush();
                return true;
            }  
        } catch (Exception e) {  
        	logger.error("复制文件时发生异常：", e);
        } finally{
        	try {
        		if(bufIn != null){
        			bufIn.close();
        		}
        		if(bufOut != null){
        			bufOut.close();
        		}
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        return false;
    }  
	
	/** 
	 * @Author: Charles
	 * @Description: 复制指定目录及所有文件 到目标目录
	 * @param sourcesDirPath		待复制的目录路径
	 * @param targetDirPath			目标目录路径
	 * @return boolean: 返回是否复制成功
	 */
	public static boolean copyFloder(String sourcesDirPath, String targetDirPath){
		File sourcesDir = new File(sourcesDirPath);  
        File targetDir = createFloder(targetDirPath);	//创建一个目标文件
        try{
	        if(sourcesDir.exists() && sourcesDir.isDirectory()){
	        	File[] subFiles = sourcesDir.listFiles();
	        	for(File sub : subFiles){
	        		if(sub.isFile()){
	        			//复制目录下的文件
	        			copyFile(sub.getCanonicalPath(), targetDir.getCanonicalPath() + File.separator + sub.getName(), true);
	        		}
	        		if(sub.isDirectory()){
	        			//递归复制目录
	        			copyFloder(sub.getCanonicalPath(), targetDir.getCanonicalPath() + File.separator + sub.getName());
	        		}
	        	}
	        	return true;
	        }
        }catch(IOException e){
        	logger.error("复制目录时发生异常：", e);
        }
		return false;
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 移动文件到指定文件
	 * @param sourcesFilePath	待移动的文件路径+文件名，如：/aaa/test.txt
	 * @param targetFilePath	指定的文件路径+文件名，如：/bbb/test.txt
	 * @param isOverOldFile		是否覆盖目标文件（如果覆盖的话，目标原来的文件会消失，如果不覆盖，则复制文件失败，谨慎使用）
	 * @return boolean: 	是否移动成功
	 */
	public static boolean moveFile(String sourcesFilePath, String targetFilePath, boolean isOverOldFile){
		boolean isCopySuccess = copyFile(sourcesFilePath, targetFilePath, isOverOldFile);
		boolean isDelSuccess = delFile(sourcesFilePath);
		return isCopySuccess && isDelSuccess;
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 移动目录到指定路径
	 * @param sourcesDirPath	待移动的目录路径，如：D:/aaa
	 * @param targetDirPath		指定的目录路径，如：D:/bbb
	 * @return boolean: 	是否移动成功
	 */
	public static boolean moveFloder(String sourcesDirPath, String targetDirPath){
		boolean isCopySuccess = copyFloder(sourcesDirPath, targetDirPath);
		boolean isDelSuccess = delFloders(sourcesDirPath, true);
		return isCopySuccess && isDelSuccess;
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 对象序列化
	 * @param fileFullPath	文件路径
	 * @param obj		待序列化的对象
	 * @return boolean: 	是否序列化成功
	 */
	public static boolean objectToFile(String fileFullPath, Object obj){
		File file = createFile(fileFullPath);
		return objectToFile(file, obj);
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 对象序列化
	 * @param targetFile	目标文件对象
	 * @param obj		待序列化的对象
	 * @return boolean: 	是否序列化成功
	 */
	public static boolean objectToFile(File targetFile, Object obj){
		ObjectOutputStream objOut = null;
		try {
			if(targetFile != null && targetFile.isFile()){
				objOut = new ObjectOutputStream(new FileOutputStream(targetFile));
				objOut.writeObject(obj);
				return true;
			}
		} catch (FileNotFoundException e) {
			logger.error("对象序列化文件找不到：", e);
		} catch (IOException e) {
			logger.error("对象序列化文件产生错误", e);
		} finally{
			try {
				if(objOut != null){
					objOut.close();
				}
			} catch (IOException e) {
				logger.error("关闭文件错误", e);
			}
		}
		return false;
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 对象反序列化
	 * @param fileFullPath	文件路径
	 * @param clazz		对象类型
	 * @return T: 	返回反序列化的对象
	 */
	public static <T> T fileToObject(String fileFullPath, Class<T> clazz){
		File file = createFile(fileFullPath);
		return fileToObject(file, clazz);
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 对象反序列化
	 * @param targetFile	目标文件对象
	 * @param clazz	对象类型
	 * @return T: 	返回反序列化的对象
	 */
	public static <T> T fileToObject(File targetFile, Class<T> clazz){
		ObjectInputStream objIn = null;
		Object obj = null;
		try{
			if(targetFile != null && targetFile.isFile()){
				objIn = new ObjectInputStream(new FileInputStream(targetFile));
				obj = objIn.readObject();
			}
		} catch (IOException e) {
			logger.error("对象反序列化产生错误:", e);
		} catch (ClassNotFoundException e) {
			logger.error("对象反序列化文件找不到类:", e);
		} finally{
			try {
				if(objIn != null){
					objIn.close();
				}
			} catch (IOException e) {
				logger.error("IO关闭错误:", e);
			}
		}
		return (T)obj;
	}
	
	
	/** 
	 * @Author: Charles
	 * @Description: 读取文件里的文本
	 * @param tagetFile	
	 * @return List<String>: 返回每行的LIST数据
	 */
	public static List<String> readText(File tagetFile){
		List<String> textList = new ArrayList<String>();
		BufferedReader bufferedReader = null;
		try {
			if(tagetFile.exists() && tagetFile.isFile()){
				bufferedReader = new BufferedReader(new FileReader(tagetFile));
				String str = null;
				while((str = bufferedReader.readLine()) != null){
					textList.add(str);
				}
			}
		} catch (FileNotFoundException e) {
			logger.error("读取文件找不到:", e);
		} catch (IOException e){
			logger.error("读取文件产生错误:", e);
		} finally{
			try {
				if(bufferedReader != null){
					bufferedReader.close();
				}
			} catch (IOException e) {
				logger.error("IO关闭错误:", e);
			}
		}
		return textList;
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 写入文本到文件
	 * @param tagetFile
	 * @param text
	 * @return boolean: 是否写入成功
	 */
	public static boolean writeText(File tagetFile, List<String> text){
		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter(tagetFile);
			for(String s : text){
				printWriter.println(text);
			}
			return true;
		} catch (IOException e) {
			logger.error("写入文本产生错误", e);
		} finally{
			if(printWriter != null){
				printWriter.close();
			}
		}
		return false;
	}

}
