package com.hckj.privilege.utils;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CodeUtils {
	//8.创建随机对象
	private static Random radnom = new Random();
	//9.创建字符数组存取字母和数字 20 19
	private static char[] chars = "abcdefghjikmnpqrstuvwxyz23456789ABCDEFGHJKMNPQRSTUVWXYZ".toCharArray();
	//10.从数组中随机指定位验证码
	private static String getCode(int len){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<len;i++){
			//拼接  chars数组的其中一个字符。 通过随机下标的方式、 下标正好是0到数组长度，而且不包括长度（既最小下标到最大下标）
			sb.append(chars[radnom.nextInt(chars.length)]);
			sb.append(" ");
		}
		return sb.toString();
	}
	//11.去掉验证码的空格
		private static String getStr(String code){
			return code.replace(" ", "");
		}
		
	public static void getWriteCode(HttpServletRequest req,HttpServletResponse resp){
		try {
			//1.创建画布
			BufferedImage bufferedImage = new BufferedImage(90, 22, BufferedImage.TYPE_INT_RGB);
			//2.创建画笔 
			Graphics2D graphics2d = bufferedImage.createGraphics();
			//6.设置背景颜色 、设置画笔颜色 ，并填充整个画布。
			graphics2d.setColor(new Color(173, 223, 248));
			graphics2d.fillRect(0, 0, 90, 22);
			//7.跟新画笔颜色并写字
			graphics2d.setColor(new Color(255, 255, 255));
			//8.生成四位验证码
			String code = getCode(4);
			String str = getStr(code);
			//把验证码存到session会话中
			req.getSession().setAttribute("code", str);
			//System.out.println(str);
			//9.设置字体往画布中写入验证码
			graphics2d.setFont(new Font("Cheers ttnorm", Font.BOLD, 20));
			graphics2d.drawString(code, 16, 17);
			//4.保存
			ImageIO.write(bufferedImage, "jpg", resp.getOutputStream());
			//5.关闭资源
			graphics2d.dispose();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {	
			e.printStackTrace();
		}
				
	}
}
