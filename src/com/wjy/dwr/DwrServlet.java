package com.wjy.dwr;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class DwrServlet extends HttpServlet {

	public DwrServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String uri = request.getRequestURI();//请求地址
		uri = uri.substring(uri.lastIndexOf("/")+1);
		String[] ss = uri.split("\\.");
		String className =ss[0];
		String methodName = ss[1];
		try {
			String classFuName ="";
			if("Test".equals(className)){
				classFuName="com.wjy.dwr.Test";
			}else if("Hello".equals(className)){
				
				classFuName="com.wjy.dwr.Hello";
			}
			else if("DataBaseDao".equals(className)){
				
				classFuName="com.wjy.dao.DataBaseDao";
			}
			//加载类 反射
			Class clazz = Class.forName(classFuName);
			//构造方法对象
			Method method = clazz.getMethod(methodName);
			Object obj = method.invoke(clazz.newInstance());			
			if(obj instanceof String ){
				System.out.println(obj);
				out.print(obj);
			}else {
				Gson g = new Gson();
				String msg=g.toJson(obj);
				System.out.println(msg);
				out.print(msg);
			}
		} catch (Exception e) {
			out.print("500");
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
