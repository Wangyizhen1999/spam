function showAnswer(){
    var a = document.getElementById("answer").value;
    if(a == ""){
    document.getElementById("fff").innerText="请输入密保答案";
    }
    else{
        document.getElementById("fff").innerText="";
    }
}
function showPassword(){
    var a = document.getElementById("password").value;
    if(! /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[^]{8,16}$/.test(a)){
    document.getElementById("bbb").innerText="请输入正确的密码";
    }
    else{
        document.getElementById("bbb").innerText="";
    }
}
function querenPassword(){
    var a = document.getElementById("password").value;
    var b = document.getElementById("querenPassword").value;
    if(a != b){
    document.getElementById("ccc").innerText="两次密码不一致";
    }
    else{
        document.getElementById("ccc").innerText="";
    }
}
$(function(){
    var time=new Date().getTime();
    $(".codeimg").attr("src","imgGetCode?t="+time);
    new Vue({
        el: "#app",
        data() {
            return {
            	input_answer: '',
            	input_password: '',
            	queren_password: '',
            }
        }
})
})
function imgChange(){
    //获得验证码图片对象
    var time=new Date().getTime();
    $(".codeimg").attr("src","imgGetCode?t="+time);
}
/*
 * 显示密保问题
 */
$(function(){
	var memberId = sessionStorage.getItem("memberId");
	$.ajax({
		type:"post",
		url:"member/memberProtect",
		data:{
			memberId:memberId,
		},
		dataType:"json",
		success:function(data){
			console.log("成功返回的数据",data);	
			var protectList =  data.protectList;
			$("#problem-info").html("");
			var txt = "";
			txt += `密保问题：${protectList[0].problem}？`
	        console.log(txt);
			$("#problem-info").html(txt);
		}
	})
})	
/*
 * 修改密码
 */
$(function(){
	$(".login").click(function(){
		var memberId = sessionStorage.getItem("memberId");
	    var answer = $("#answer").val();
	    var password = $("#password").val();
		var querenPassword = $("#querenPassword").val();
	    var inputCode = $("#inputCode").val();
	    console.log(answer, password, inputCode);
	    $.ajax({
	        type: "post",
	        url: "/member/memberfindPassword",
	        data: {
	        	memberId:memberId,
	        	answer: answer,
	            password: password,
	            querenPassword:querenPassword,
	            inputCode: inputCode,
	        },
	        dataType:"json",
	        success:function(data){
	        	console.log("成功返回的数据",data);	
				if(data.prompt == "找回密码成功"){
					 location.href="redirect?page=member_login";
				}else{
					alert(data.prompt);
				}	
	        }
	    })
	})
	});