package com.wjy.hehe;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserExistServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserExistServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			String userName = request.getParameter("userName");
			System.out.println("userName = "+userName);
			boolean exist =users.contains(userName);
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			System.out.println(exist);
			response.addHeader("pragma", "no-cache");//服务器端无cache操作
			response.addHeader("cache-control", "no-cache");
			response.addHeader("expires", "0");
			request.setAttribute("exist", exist);
			response.getWriter().print(exist);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			this.doPost(request, response);
	}
	
	private List<String> users;
	
	public void init() throws ServletException {
		users= new Vector<String>();
		users.add("zhangsan");
		users.add("dooby");
		users.add("hehe");
		users.add("niubi");
		users.add("666");
		users.add("lisi");
	}

}
