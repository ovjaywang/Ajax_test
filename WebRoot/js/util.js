var xhr;
//������Դ��ַ�ص��������� XMLHttpRequest����
function sendRequest(path,methodName,xhr){
	if(xhr==null){
		xhr =  new XMLHttpRequest();
	}
	xhr.onreadystatechange=methodName;//����.������
	xhr.open("GET", "dwr/"+path,true);
	xhr.send(null);
}