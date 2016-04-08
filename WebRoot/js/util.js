var xhr;
//请求资源地址回调方法名称 XMLHttpRequest对象
function sendRequest(path,methodName,xhr){
	if(xhr==null){
		xhr =  new XMLHttpRequest();
	}
	xhr.onreadystatechange=methodName;//类名.方法名
	xhr.open("GET", "dwr/"+path,true);
	xhr.send(null);
}