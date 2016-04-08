package com.wjy.hehe;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.wjy.dao.DataBaseDao;

public class Resorts extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Resorts() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String cityId =request.getParameter("cityId");
		DataBaseDao db= new DataBaseDao();
		response.setContentType("text/html;charset=utf-8");
		response.addHeader("pragma", "no-cache");//服务器端无cache操作
		response.addHeader("cache-control", "no-cache");
		response.addHeader("expires", "0");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
 		String txt = gson.toJson(db.findResortByCityId(Integer.parseInt(cityId)));
		System.out.println(txt);
		out.print(txt);
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			this.doGet(request, response);
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
