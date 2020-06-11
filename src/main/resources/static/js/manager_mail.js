function format(time){
	var date = new Date(time);
	return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
}

$(function(){
	$.ajax({
		type:"post",
		url:"member/managerShow",
		dataType:"json",
		success:function(data){
			console.log("成功返回的数据",data);	
			var showAllList =  data.showAllList;
			$("#member-info").html("");
			var txt = "";
			for(var i = 0; i < showAllList.length; i ++){
			txt += `<tr>
			<td>${showAllList[i].account}</td>
			<td>${showAllList[i].phone}</td>
			<td>${showAllList[i].problem}</td>
			<td>${showAllList[i].answer}</td>
			<td>${format(showAllList[i].ct)}</td>
			<td>${showAllList[i].mailNum}</td>
			<tr>`
			}
	        console.log(txt);
			$("#member-info").html(txt);
		}
	})
})	