function showAccount(){
    var a = document.getElementById("account").value;
    if(! /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[^]{8,16}$/.test(a)){
    document.getElementById("aaa").innerText="请输入正确的账号";
    }
    else{
        document.getElementById("aaa").innerText="";
    }
}
function showPhone(){
    var a = document.getElementById("phone").value;
    if(!/^1[0-9]{10}$/.test(a)){
    document.getElementById("ddd").innerText="请输入正确的手机号";
    }
    else{
        document.getElementById("ddd").innerText="";
    }
}
$(function(){
new Vue({
    el: "#app",
    data() {
        return {
            input_clear: '',
            input_phone: '',
        }
    }
})
})
$(function(){
$(".login").click(function(){
	var account = $("#account").val();
    var phone = $("#phone").val();
    console.log(account, phone);
    $.ajax({
        type: "post",
        url: "/member/memberFind",
        data: {
            account: account,
            phone: phone,
        },
        dataType:"json",
        success:function(data){
            console.log("返回成功的数据：", data);
            if(data.gain == 1){
                location.href="redirect?page=member_findpassword2";
                sessionStorage.setItem("memberId",data.memberId);
            }else{
                alert(data.prompt); 
                    
            }
        }
    })
})
})