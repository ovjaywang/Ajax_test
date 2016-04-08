<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% String path = request.getContextPath(); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>下拉框联动</title>
<script type="text/javascript" src="<%=path%>/js/debug.js"></script>
<script type="text/javascript">
	var xhr = createXMLHttpRequest();
	function sendRequest(shengId){
		if(shengId=='0'){
			debug('默认选项清空');
			clearShi();
			return
		}
		xhr.open('GET', '<%=path%>/Shengshiliandong?guojiaId='+shengId+'&'+new Date().getTime(), true);
		xhr.setRequestHeader("If-Modified-Since","0");
		xhr.onreadystatechange = getResponse;
		xhr.send(null);
		showLoading(document.getElementById('ad'),'${pageContext.request.contextPath}/images/2.gif');
		debug("已发送请求，国家编号为 " +shengId);		
	}
	
	function getResponse(){
		var city = document.getElementById("city");
		if(xhr.readyState ==4&&xhr.status==200){
			var s = xhr.responseText;
			debug('获得服务器响应   '+s);
			var cities = s.split(",");
			clearShi();
			for(var i=0;i<cities.length;i++){
				city.options[city.options.length] = new Option(cities[i],cities[i]);
			}
			hideLoading();
		}
	}
	function clearShi(){
		var city = document.getElementById("city");
		city.options.length = 0;
		city.options[0] = new Option('==请选择==','0');
	}
	function createXMLHttpRequest(){
		if(window.ActiveXObject){
			try{
				xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
			}catch(e){
				xmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");
			}
			return xmlHttpRequest;
		}else if(window.XMLHttpRequest){
			return new XMLHttpRequest();
		}
	}
	function showLoading(where1,image){
		debug(image);
		var img =  document.getElementById("ss");
		if(img==null){
			var img = document.createElement('img');
			img.setAttribute("id","ss");
			img.setAttribute("src", image);
			where1.appendChild(img);
		}
		img.style.display = 'inline';
	}
	function hideLoading(){
		document.getElementById("ss").style.display = 'none';
	}
</script>
</head>
<body>
<h1>你来自哪里</h1>
<div id="ad">
省：<select id="guojia" onchange="sendRequest(this.options(this.selectedIndex).value)">
	<option value="0">==请选择==</option>
	<option value="1">河北</option>
	<option value="2">美帝</option>
	<option value="3">板鸭</option>
</select>
市：<select id="city">
	<option>==请选择==</option>
</select>
</div>
</body>
</html>