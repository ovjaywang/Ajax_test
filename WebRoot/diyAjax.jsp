<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/util.js"></script>
<script type="text/javascript" src="js/debug.js"></script>
<script type="text/javascript">
	var xhr = new XMLHttpRequest();
	function hello(){
		sendRequest("Hello.sayHello",haolejiaowo,xhr);
	}
	function haolejiaowo(){
		if(xhr.readyState==4){
			alert(xhr.responseText);
		}
	}
	function haolejiaowo2(){
		if(xhr.readyState==4){
			var json = xhr.responseText;
			json = eval("("+json+")");
			alert(json.Name+","+json.Age);
		}
	}
	function haolejiaowo3(){
		if(xhr.readyState==4){
			var json = xhr.responseText;
			json = eval("("+json+")");
			for(var i=0;i<json.length;i++){
				debug(json[i].countryName +" "+json[i].countryId);
			}
		}
	}
	function sendRequest666(){
		sendRequest("Test.getStudent",haolejiaowo2,xhr);
	}
	function getCities(){
		sendRequest("DataBaseDao.getCountry",haolejiaowo3,xhr);
	}
</script>
<title>Insert title here</title>
</head>
<body>
<input type="button" value="hello()" onclick="hello()">
<input type="button" value="getStudent()" onclick="sendRequest666()">
<input type="button" value="getCountry()" onclick="getCities()">
</body>
</html>