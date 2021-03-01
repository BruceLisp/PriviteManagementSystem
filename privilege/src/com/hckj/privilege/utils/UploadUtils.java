package com.hckj.privilege.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

/*上传文件工具类*/
public class UploadUtils {
	/*
	 * 长传方法 
	 * 返回上传文件的访问路径 
	 * 目录调用者创建
	 * */
	public static String uploadFile(HttpServletRequest req,String tempPath,String uploadPath){
		try {
			//1.创建磁盘文件工厂
			String path_temp = req.getServletContext().getRealPath(tempPath);
			DiskFileItemFactory factory = new DiskFileItemFactory(1024*1024, new File(path_temp));
			//2.创建文件上传核心类 
			ServletFileUpload upload = new ServletFileUpload(factory);
			//3.设置上传文件名称编码 
			upload.setHeaderEncoding("UTF-8");
			//4.判断是否是上传表单
			boolean isMultipartContent = upload.isMultipartContent(req);  
			String fileName = "";
			if(isMultipartContent){
				//5.是上传表单  解析文件集合 
				List<FileItem> list = upload.parseRequest(req);
				if(list!=null){
					//6.遍历集合
					for(FileItem item : list){
						//判断item是文本类型 还是 字节类型 
						boolean isFormField = item.isFormField();
						if(isFormField){
							//文本类型
							String key = item.getFieldName();
							String value = item.getString("UTF-8");
							System.out.println(key+":"+value);
						} else {
							//字节文件
							fileName = item.getName();
							//获得当前日期的毫秒值
							Long time = new Date().getTime();
							fileName = time+fileName;
							//获得上传内容
							InputStream in = item.getInputStream();
							//获得部署路径
							String path = req.getServletContext().getRealPath("/"+uploadPath);
							OutputStream out= new FileOutputStream(path+"/"+fileName);
							IOUtils.copy(in, out);
							out.close();
							in.close();
							//删除临时文件
							item.delete();
						}
					}
				}
			}
			String fangWenPath = req.getContextPath()+"/"+uploadPath+"/"+fileName;
			return  fangWenPath;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
		
	}
}
