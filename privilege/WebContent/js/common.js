function submitForm(formId){
	document.getElementById(formId).submit();
}
function del(id,url){
	if(confirm("你确认删除吗？")){
		location.href=url+"&id="+id
	}
}
function back2(url){
	location.href=url;
}