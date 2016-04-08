//var CONSOLE_ENABlE = true;

var console =null;
var consoleID = 'console_id';

function debug(s){
//	if(!CONSOLE_ENABLE)  return;
	if(console==null) initConsole();
	console.innerHTML += s + '<BR>';
	console.scrollTop = console.offsetHeight;
}

function initConsole(){
	
	c = document.createElement('div');
	document.documentElement.lastChild.appendChild(c);
	c.style.border = "solid 1px black";
	c.style.fontSize = '12px';
	c.style.color = 'green';
	c.style.width='90%';
	c.style.height = '200px';
	c.style.overflow = 'auto';
	c.style.margin = '20px';
	c.style.padding = '2px';
	console = c;
}