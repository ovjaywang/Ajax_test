<%@page import="java.util.*,java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=gbk"%>
<html>
<head>
<title>666</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/debug.js"></script>
<script type="text/javascript">
	var xmlHttpRequest;
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
	
	function checkExist(){
		//1 ����xmlHttpRequest����
		xmlHttpRequest =createXMLHttpRequest();
		//2 ���ûҵ�����
		xmlHttpRequest.onreadystatechange =haoLeJiaoWo;
		var url="http://localhost:8087/<%=request.getContextPath()%>/UserExistServlet?userName="
		+document.getElementById("userName").value;
		//3.��ʼ��xmlhttprequest���
		xmlHttpRequest.open("GET",url,true);//get�л��� post�޻���
		xmlHttpRequest.setRequestHeader("If-Modified-Since","0");
		xmlHttpRequest.send(null);
	}
	/**
	0-δ��ʼ��
	1-��ʼ��
	2-��������
	3-��ʼ���ܽ��
	4-���ܽ�����
	200-ok
	404-��Դδ�ҵ�
	500 �������˳���
	*/
	function haoLeJiaoWo(){
		debug("xmlHttpRequest.readyState : "+xmlHttpRequest.readyState);
		if(xmlHttpRequest.readyState ==4 && xmlHttpRequest.status ==200){
			var msg = xmlHttpRequest.responseText;
			if(msg=="true"){
				alert("���û��Ѿ�����");
			}else{
				alert("���û�������");
			}
		}
	}
</script>
</head>
<body>
	<form name="registerUserForm" action="${pageContext.request.contextPath}/RegisterUserServlet">
	<table width="50%" border="0" align="center">
	<caption>Register User date:<%=new SimpleDateFormat("HH:mm:ss").format(new Date()) %></caption>
	<tr>
		<td align="right">UserName:</td>
		<td>
			<input name="userName" id="userName" value="${param.userName}"> 
			<input type="button" value="Exist" onclick="checkExist()">
		</td>
	</tr>
	<tr>
		<td align="right">Address:</td>
		<td><input name="address" value="${params.address }"></td>
	</tr>	
		<tr>
		<td align="right">tel:</td>
		<td><input name="address" value="${params.tel }"></td>
	</tr>
		<tr>
		<td align="right">Age:</td>
		<td><input name="address" value="${params.age }"></td>
	</tr>
		<tr>
		<td align="right"><input type="reset" value="reset"></td>
		<td><input type="submit" value="submit"></td>
	</tr>
</table>
</form>
</body>
</html>