package com.wjy.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.wjy.entity.City;
import com.wjy.entity.Country;
import com.wjy.entity.Country_CityList;
import com.wjy.entity.Resort;

public class DataBaseDao {
	private Connection conn; 
	private void sqlCon() {  
        try {  
            Class.forName("com.mysql.jdbc.Driver");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        try {  
              
            String url = "jdbc:mysql://localhost:3306/cityManager?user=root&password=1234&useUnicode=true&characterEncoding=UTF-8";// 链接数据库语句  
  
            conn = (Connection) DriverManager.getConnection(url); // 链接数据库  
            System.out.println("连接成功"+conn.toString());
             Statement stmt=(Statement)  
             conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);  
             String sql="select * from city";//查询user表语句  
             ResultSet rs=stmt.executeQuery(sql);//执行查询  
             while(rs.next()){  
            	 System.out.println(rs.getString(1)+" "+rs.getInt(2)+" "+rs.getInt(3));
            	
             }  
             rs.close();  
             stmt.close();  
             conn.close();  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
    }
	public ArrayList<Country> getCountry() {
		ArrayList<Country> ct = new ArrayList<Country>();
        try {  
            Class.forName("com.mysql.jdbc.Driver");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        try {  
              
            String url = "jdbc:mysql://localhost:3306/cityManager?user=root&password=1234&useUnicode=true&characterEncoding=UTF-8";// 链接数据库语句  
  
            conn = (Connection) DriverManager.getConnection(url); // 链接数据库  
            System.out.println("连接成功"+conn.toString());
             Statement stmt=(Statement)  
             conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);  
             String sql="select countryId,countryName from country";//查询user表语句  
             ResultSet rs=stmt.executeQuery(sql);//执行查询  
             while(rs.next()){  
            	 System.out.println(rs.getString(2)+" "+rs.getInt(1));
            	 ct.add(new Country(rs.getString(2), rs.getInt(1)));
             }
             rs.close();  
             stmt.close();  
             conn.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        
        return ct;
    }
	public List<Resort> findResortByCityId(int cityId){
		List<Resort> rsss = new ArrayList<Resort>();
		 try {  
	            Class.forName("com.mysql.jdbc.Driver");  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        try {  
	              
	            String url = "jdbc:mysql://localhost:3306/cityManager?user=root&password=1234&useUnicode=true&characterEncoding=UTF-8";// 链接数据库语句  
	  
	            conn = (Connection) DriverManager.getConnection(url); // 链接数据库  
	            System.out.println("连接成功"+conn.toString());
	             Statement stmt=(Statement)  
	             conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);  
	             String sql="select resortId,resortName from resort where cityId="+cityId;//查询user表语句  
	             ResultSet rs=stmt.executeQuery(sql);//执行查询
	             while(rs.next()){  
	            	 Resort rss = new Resort(rs.getString(2),cityId,rs.getInt(1));
	            	 rsss.add(rss);
	             }
	             rs.close();  
	             stmt.close();  
	             conn.close();  	  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        System.out.println(rsss);
		return rsss;
	}
	public String findAllByCountryId(int countryId,String countryName){
		 String result ="";
         Country_CityList ccl=new Country_CityList(new Country(countryName, countryId),new ArrayList<City>());
		 try {  
	            Class.forName("com.mysql.jdbc.Driver");  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        try {  
	              
	            String url = "jdbc:mysql://localhost:3306/cityManager?user=root&password=1234&useUnicode=true&characterEncoding=UTF-8";// 链接数据库语句  
	  
	            conn = (Connection) DriverManager.getConnection(url); // 链接数据库  
	            System.out.println("连接成功"+conn.toString());
	             Statement stmt=(Statement)  
	             conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);  
	             String sql="select city.cityId,city.cityName,resort.resortName,resort.resortId from city JOIN resort where city.countryId="+countryId+" and resort.cityId=city.cityId ORDER BY cityId";//查询user表语句  
	             ResultSet rs=stmt.executeQuery(sql);//执行查询
	             Set set=new HashSet();
	             City ct =null;
	             while(rs.next()){  
	            	 System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4) );
	            	 if(!set.contains(rs.getInt(1))){	            		 		            	 
		            		 ct = new City(rs.getString(2), rs.getInt(1), countryId, new ArrayList<Resort>());
		            		 ccl.insertCity(ct);
		            		 set.add(rs.getInt(1));
	            	 }
	            	 Resort rss = new Resort(rs.getString(3),rs.getInt(1),rs.getInt(4));
	            	 ct.insertRs(rss);
	             }
	     		Gson gson = new Gson();
	     		result = gson.toJson(ccl);
	    		System.out.println(result);
	             rs.close();  
	             stmt.close();  
	             conn.close();  	  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
            System.out.println(explainJson(result));
		return result;
	}	
	public Country_CityList explainJson(String jsonMsg){
		Gson g=new Gson();
		Country_CityList t= g.fromJson(jsonMsg, Country_CityList.class);  
		return t;
	}
	public static void main(String[] args) {
//		testMysql test=new testMysql();
//		test.sqlCon();
//		DataBaseDao test = new DataBaseDao();
//		test.findAllByCountryId(1, "china");
		DataBaseDao test = new DataBaseDao();
		test.findResortByCityId(1);
//		System.out.println(test.getCountry());
	}
}