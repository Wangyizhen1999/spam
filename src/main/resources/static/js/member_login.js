function showAccount(){
    var a = document.getElementById("account").value;
    if(! /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[^]{8,16}$/.test(a)){
    document.getElementById("aaa").innerText="请输入正确的账号";
    }
    else{
        document.getElementById("aaa").innerText="";
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
$(function(){
new Vue({
    el: "#app",
    data() {
        return {
            input_clear: '',
            input_password: '',
        }
    }
})
})
$(function(){
    var time=new Date().getTime();
    $(".codeimg").attr("src","imgGetCode?t="+time);
})
function imgChange(){
    //获得验证码图片对象
    var time=new Date().getTime();
    $(".codeimg").attr("src","imgGetCode?t="+time);
}
$(function(){
	$(".login").click(function(){
	    var account = $("#account").val();
	    var password = $("#password").val();
	    var inputCode = $("#inputCode").val();
	    console.log(account, password, inputCode);
	    $.ajax({
	        type: "post",
	        url: "/member/memberLogin",
	        data: {
	            account: account,
	            password: password,
	            inputCode: inputCode,
	        },
	        dataType:"json",
	        success:function(data){
	            console.log("返回成功的数据：", data);
	            if(data.gain == 1){
	                location.href="redirect?page=member_mail";
	                sessionStorage.setItem("memberId",data.memberId);
					sessionStorage.setItem("memberAccount",data.memberAccount);
	            }else{
	                alert(data.prompt); 
	                    
	            }
	        }
	    })
	})
	});