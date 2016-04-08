<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" src= "js/debug.js"> </script>
<script type="text/javascript">
	var xhr = new XMLHttpRequest();	
	function sendRquest(txt){
		if(txt == "") return;
		var auto = document.getElementById('auto');
		if(event.keyCode == 40 && auto.style.display =='block'){
			var selectValues  = document.getElementById('se');
			selectValues.focus();
			selectValues.selectedIndex = 0;
			return;
		}
		auto.style.display = 'none';
		var url = 'BaiduServlet?txt='+txt;
		xhr.open('POST', encodeURI(url), true);
		xhr.onreadystatechange = getResponse;
		xhr.send(null);
		debug('已经发送 文字 =' +txt);
		//40向下
	}
	
	function getResponse(){
		if(xhr.readyState ==4 ){
					var auto = document.getElementById('auto');
					var txt = document.getElementById('kw');
					var selectValues = document.getElementById('se');
					selectValues.length = 0;
					 var s = xhr.responseText;
					 debug('收到服务器返回 :' + s);
					 if(s == "") {
					 	auto.style.display ='none';
					 	return;
					 } 
					 var ss = s.split(",");
					 selectValues.size = ss.length -1;
					 selectValues.onkeyup = function(){
					 	txt.value = this.value;
					 	if(event.keyCode  == 13){
					 		this.parentNode.style.display = 'none';
					 	}//回车
					 };
					 selectValues.onclick  = function(){
					 	txt.value = this.value;
					 	this.parentNode.style.display ='none';
					 };
					 for ( var i = 0; i < (ss.length-1); i++) {
						selectValues.options[i] =  new Option(ss[i],ss[i])
					}
					var left = txt.offsetLeft;
					var top = txt.offsetTop;
					auto.style.left = left;
					auto.style.top = top;
					auto.style.display='block';					 
		}
	}
	</script>
	<%@ include file = "BaiduAbove.jsp" %>
	<INPUT name="wd" class="s_ipt" id="kw" maxlength="255" autocomplete="off" onkeyup="sendRquest(this.value);"><br>

	<%@ include file = "BaiduBelow.jsp" %>
	