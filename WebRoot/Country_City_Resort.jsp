<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.wjy.dao.DataBaseDao"%>
<%@page import="com.wjy.entity.Country"%>
<%@page import="com.wjy.entity.Country_CityList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% String path = request.getContextPath(); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>下拉框联动</title>
<script type="text/javascript" src="<%=path%>/js/debug.js"></script>
<script type="text/javascript">
	var xhr = createXMLHttpRequest();
	var obj;
	function sendRequest(countryId,countryName){
		if(countryId=='0'){
			debug('默认选项清空');
			clearShi();
			return
		}
		xhr.open('GET', '<%=path%>/CountryCityResort?countryId='+countryId+'&countryName='+countryName+"&timestamp="+new Date().getTime(), true);
		xhr.setRequestHeader("If-Modified-Since","0");
		xhr.onreadystatechange = getResponse;
		xhr.send(null);
		showLoading(document.getElementById('ad'),'${pageContext.request.contextPath}/images/2.gif');
		debug("已发送请求，国家编号为 " +countryId);		
	}	
	function getResponse(){
		var city = document.getElementById("city");
		if(xhr.readyState ==4&&xhr.status==200){
			var s = xhr.responseText;
			debug('获得服务器响应   '+s);
			obj=eval('('+s+')');
			clearShi();
			for(var i=0;i<obj.cities.length;i++){
				city.options[city.options.length] = new Option(obj.cities[i].cityName,obj.cities[i].cityId);
			}
			hideLoading();
		}
	}
	function clearShi(){
		var city = document.getElementById("city");
		city.options.length = 0;
		city.options[0] = new Option('==请选择==','-1');
		clearResort();
	}
	function clearResort(){
		var resort =document.getElementById("resort");
		resort.options.length=0;
		resort.options[0] = new Option('==请选择==','-1');
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
	function selectedCity(cityId){
		clearResort();
		var rss=document.getElementById("resort");
		for(var j =0;j<obj.cities.length;j++){
			if(obj.cities[j].cityId==cityId){
				debug("城市有 "+obj.cities[j].rs.length+" 个风景");
				for(var i=0;i<obj.cities[j].rs.length;i++){
					rss.options[rss.options.length] = new Option(obj.cities[j].rs[i].ResortName,obj.cities[j].rs[i].ResortName);
				}
			}
		}
	}
	var tmp_tag;
	var tmp_tag2;
	function getCitylist(countryId,tagId,countryName){
		var isdelete =false;
		tmp_tag=tagId;
		var sons = document.getElementById(tagId).childNodes;
		for(var i =(sons.length-1);i>=0;i--){
			if(sons[i].nodeName.toUpperCase()=="UL"){
				sons[i].parentNode.removeChild(sons[i]);
				isdelete =true;
			}
		}
		debug(isdelete);
		if(isdelete) return;
		xhr.open('GET','<%=path%>/CountryCityResort?countryId='+countryId+'&countryName='+countryName+"&timestamp="+new Date().getTime(), true);
		xhr.setRequestHeader("If-Modified-Since","0");
		xhr.onreadystatechange = getSonsOK;
		xhr.send();
		debug("已经发送请求  父ID = " + ParentId);
		return false;
	}
	function getSonsOK(){
		if(xhr.readyState ==4){
			var s = xhr.responseText;
			debug("收到服务器回应 " + s);
			var obj =eval('('+s+')');
			if(obj.cities.length==0) return;
			var pli = document.getElementById(tmp_tag);
			debug(pli);
			var nul = document.createElement("ul");
			for(var i =0;i<obj.cities.length;i++){
				var nli = document.createElement("li");
				nli.id = "city"+obj.cities[i].cityId;
				nli.innerHTML="<a href=\"javascript:getResortList('"+obj.cities[i].cityId+"','city'+"+obj.cities[i].cityId+")\">"+obj.cities[i].cityName+"</a>";
				nul.appendChild(nli);
			}
			pli.appendChild(nul);
		}
	}
	function getResortList(cityId,tagId){
		debug("IN");
		var isdelete =false;	
		tmp_tag2=tagId;
		var sons = document.getElementById(tagId).childNodes;
		for(var i =(sons.length-1);i>=0;i--){
			if(sons[i].nodeName.toUpperCase()=="UL"){
				sons[i].parentNode.removeChild(sons[i]);
				isdelete =true;
			}
		}
		debug(isdelete);
		if(isdelete) return;
		xhr.open('GET','<%=path%>/Resorts?cityId='+cityId+"&timestamp="+new Date().getTime(), true);
		xhr.setRequestHeader("If-Modified-Since","0");
		xhr.onreadystatechange = getResortOK;
		xhr.send();
	}
		function getResortOK(){
		if(xhr.readyState ==4){
			var s = xhr.responseText;
			debug("收到服务器回应 " + s);
			var obj =eval('('+s+')');
			if(obj.length==0) return;
			var pli = document.getElementById(tmp_tag2);
			debug(pli);
			var nul = document.createElement("ul");
			for(var i =0;i<obj.length;i++){
				var nli = document.createElement("li");
				nli.id = "city"+obj[i].resortId;
				nli.innerHTML="<a href='javascript:'>"+obj[i].ResortName+"</a>";
				nul.appendChild(nli);
			}
			pli.appendChild(nul);
		}
	}
</script>
</head>
<body >
<%ArrayList<Country> lls = new ArrayList<Country>();  %>
<h1>你来自哪里</h1>
<div id="ad">
国家：<select id="guojia" onchange="sendRequest(this.options(this.selectedIndex).value,this.options(this.selectedIndex).text)">
	<option value="-1">==请选择==</option>
<%DataBaseDao d = new DataBaseDao(); lls=d.getCountry(); for(int i=0;i<lls.size();i++){%>
<option value="<%=lls.get(i).getCountryId()%>"><%=lls.get(i).getCountryName() %></option>
<%} %>
</select>
城市：<select id="city" onchange="selectedCity(this.options(this.selectedIndex).value)">
	<option>==请选择==</option>
</select>
景点：<select id="resort">
	<option>==请选择==</option>
</select>

</div>

<hr/>
<ul>
	<%	for(int i=0;i<lls.size();i++){ %>
		<li id="country<%=( lls.get(i)).getCountryId()%>"><a href="javascript:getCitylist('<%=( lls.get(i)).getCountryId()%>','country<%=(lls.get(i)).getCountryId()%>','<%=lls.get(i).getCountryName()%>')"><%=lls.get(i).getCountryName()%></a></li><%} %></ul>
</body>
</html>