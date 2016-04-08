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

import com.wjy.dao.DataBaseDao;

public class CountryCityResort extends HttpServlet {

	public static Map<String,List<String>> map = new HashMap<String, List<String>>();
	public CountryCityResort() {
		super();
	}
	public void destroy() {
		super.destroy(); 
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String countryId =request.getParameter("countryId");
			String countryName =request.getParameter("countryName");
			DataBaseDao db= new DataBaseDao();
			response.setContentType("text/html;charset=utf-8");
			response.addHeader("pragma", "no-cache");//服务器端无cache操作
			response.addHeader("cache-control", "no-cache");
			response.addHeader("expires", "0");
			PrintWriter out = response.getWriter();
			out.print(db.findAllByCountryId(Integer.parseInt(countryId), countryName));
			out.close();
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doPost(request, response);
	}
	public void init() throws ServletException {

	}
}
