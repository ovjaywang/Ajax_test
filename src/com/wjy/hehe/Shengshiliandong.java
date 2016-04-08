package com.wjy.hehe;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Shengshiliandong extends HttpServlet {

	public static Map<String,List<String>> map = new HashMap<String, List<String>>();
	public Shengshiliandong() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String guojiaId =request.getParameter("guojiaId");
			List<String> list=map.get(guojiaId);
			StringBuilder sb = new StringBuilder();
			if(list!=null){
				for (String s : list) {
					sb.append(s).append(",");
				}
				if(!list.isEmpty()) sb.deleteCharAt(sb.length()-1);
			}
			response.setContentType("text/html;charset=utf-8");
			response.addHeader("pragma", "no-cache");//服务器端无cache操作
			response.addHeader("cache-control", "no-cache");
			response.addHeader("expires", "0");
			PrintWriter out = response.getWriter();
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			out.print(sb.toString());
			out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			this.doPost(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		List<String> lis = new ArrayList<String>();
		lis.add("北京");
		lis.add("天津");
		lis.add("石家庄");
		map.put("1", lis);
		lis= new ArrayList<String>();
		lis.add("纽约");
		lis.add("华盛顿");
		lis.add("西雅图");
		map.put("2", lis);
		lis= new ArrayList<String>();
		lis.add("马德里");
		lis.add("巴塞罗那");
		lis.add("马拉加");
		map.put("3", lis);
	}

}
