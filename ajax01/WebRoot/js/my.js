//获得ajax
function getXhr(){
		var xhr=null;
		if(window.XMLHttpRequest){
		//非ie浏览器
		xhr=new XMLHttpRequest();
		}else{
		xhr=new ActiveXObject("Microsoft.XMLH");
		}
		return xhr;
	}
//依据id返回dom节点
function $(id){
	return document.getElementById(id);
}
//返回id对应dom节点的value属性
function $F(id){
	return $(id).value;
}
