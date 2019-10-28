package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUtil {

	// 文件上传路径
	private static String UPLOAD_PATH = null;

	/**
	 * @author Duke 静态内部类，实例化工具类
	 */
	private static class LazyHolder {

		private static final FileUtil FILEUTIL = new FileUtil();
	}

	/**
	 * 私有构造函数
	 */
	private FileUtil() {

	}

	/**
	 * 获取工具类实例
	 * 
	 * @return FileUploadUtil 实例
	 */
	public static FileUtil getInstance(String uploadPath) {

		UPLOAD_PATH = uploadPath;
		return LazyHolder.FILEUTIL;
	}

	/**
	 * 文件批量上传
	 * 
	 * @param request
	 * @param uploadPath
	 * 
	 */
	public List<File> upload(HttpServletRequest request) {

		// 用于存放上传成功的照片
		List<File> files = new ArrayList<File>();

		try {

			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);

			List<FileItem> items = upload.parseRequest(request);

			for (FileItem fileItem : items) {
				
				// 获得文件名，包括文件路径
				String fileFullName = fileItem.getName();

				if (fileFullName != null && fileFullName != "" && fileItem.getSize() > 0) {

					// 获取文件路径
					String fileName = new File(fileFullName).getName();

					// 创建文件
					File savedFile = new File(UPLOAD_PATH, fileName);

					// 如果文件所在文件夹不存在的话，新建文件夹
					if (!savedFile.getParentFile().exists()) {
						savedFile.getParentFile().mkdirs();
					}

					// 写入文件
					fileItem.write(savedFile);
					files.add(savedFile);
				}
			}
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return files;
	}
	
	/**
	 * 文件下载
	 * 
	 * @param filePath
	 * @throws IOException 
	 * @throws Exception
	 */
	public void downLoadFile(String filePath, HttpServletResponse response) throws IOException {
		
		// 处理中文乱码问题
		filePath = new String(filePath.getBytes("ISO-8859-1"), "UTF-8");
		
		downLoadFile(new File(filePath), response);
	}
	
	/**
	 * 文件下载 
	 * 
	 * @param file
	 * @throws IOException 
	 */
	public void downLoadFile(File file, HttpServletResponse response) throws IOException {
		
		if (file.exists()) {
			
			response.reset();
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(file.getName(), "UTF-8"));
			
			// 输出文件字节流
			writeBufferByte(file, response);
		}
	}
	
	/**
	 * 输出照片字节流
	 * 
	 * @throws IOException 
	 */
	public void outputImgByte(String filePath, HttpServletResponse response) throws IOException {
		
		// 处理中文乱码问题
		filePath = new String(filePath.getBytes("ISO-8859-1"), "UTF-8");
		outputImgByte(new File(filePath), response);
	}
	
	/**
	 * 输出照片字节流
	 * 
	 * @throws IOException 
	 */
	public void outputImgByte(File file, HttpServletResponse response) throws IOException {
		
		// 文件存在的情况下输出文件
		if (file.exists()) {

			// 设置相应信息的类型
			response.setContentType("image/jpeg");

			// 输出文件字节流
			writeBufferByte(file, response);
		}
	}
	
	/**
	 * 输出文件字节流
	 * 
	 * @param file
	 * @param response
	 * @throws IOException
	 */
	private void writeBufferByte(File file, HttpServletResponse response) throws IOException {
		
		// 一次读 2048 个字节
		byte[] buffer = new byte[2048];

		// 获取照片流
		FileInputStream fos = new FileInputStream(file);

		int count;

		while ((count = fos.read(buffer)) > 0) {

			// 输出照片流
			response.getOutputStream().write(buffer, 0, count);
		}

		fos.close();
	}
}
