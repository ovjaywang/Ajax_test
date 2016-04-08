package com.wjy.hehe;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaiduServlet extends HttpServlet {
	List<String> users = new Vector<String>();
	public BaiduServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			System.out.println("doPost");
			String tt =request.getParameter("txt");
			String msg = new String(tt.getBytes("ISO-8859-1"),"UTF-8");
			System.out.println(msg);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			for(String user :users){
					if(user.startsWith(msg)){
						out.print(user+",");
					}
			}
			out.close();
	}
	public void init() throws ServletException {
		users.add("ºÇºÇ");
		users.add("ºÇºÇßÕ");
		users.add("ºÇºÇÈ¥Ï´Ôè");
		users.add("Ð¡°×");
		users.add("Ð¡°×ÍÃ");
		users.add("Ð¡»¨Ã¨");
		users.add("Ð¡°×ÍÃ°®³ÔÓã");
	}

}
