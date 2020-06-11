//注意：选项卡 依赖 element 模块，否则无法进行功能性操作
layui.use('element', function() {
	var element = layui.element;

	//…
});
layui.use('upload', function() {
	var $ = layui.jquery,
		upload = layui.upload;

	//普通图片上传
	var uploadInst = upload.render({
		elem: '#test1',
		url: 'https://httpbin.org/post' //改成您自己的上传接口
			,
		before: function(obj) {
			//预读本地文件示例，不支持ie8
			obj.preview(function(index, file, result) {
				$('#demo1').attr('src', result); //图片链接（base64）
			});
		},
		done: function(res) {
			//如果上传失败
			if (res.code > 0) {
				return layer.msg('上传失败');
			}
			//上传成功
		},
		error: function() {
			//演示失败状态，并实现重传
			var demoText = $('#demoText');
			demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
			demoText.find('.demo-reload').on('click', function() {
				uploadInst.upload();
			});
		}
	});
});
layui.use('form', function() {
	var form = layui.form;

	//监听提交
	form.on('submit(formDemo)', function(data) {
		layer.msg(JSON.stringify(data.field));
		return false;
	});
});
$(".layui-btn-normal").click(function(){
	var managerId = sessionStorage.getItem("managerId");
    var oldPassword = $("#oldPassword").val();
    var newPassword = $("#newPassword").val();
    var querenPassword = $("#querenPassword").val();
   
    if(oldPassword.length == 0){
        layer.open({
            title: '警告',
            content: '原密码不能为空'
          });  
    }
    else if(newPassword.length == 0){
        layer.open({
            title: '警告',
            content: '新密码不能为空'
          });  
    }
    else if(querenPassword.length == 0){
        layer.open({
            title: '警告',
            content: '确认新密码不能为空'
          });  
    }
    else if(newPassword != querenPassword){
        layer.open({
            title: '警告',
            content: '两次输入的密码不一致'
          });  
    }
    else{
        console.log(oldPassword);
        console.log(newPassword);
        console.log(querenPassword);
    }
    $.ajax({
        type: "post",
        url: "/manager/managerupdatePassword",
        data: {
        	managerId:managerId,
        	oldPassword:oldPassword,
        	newPassword:newPassword,
            querenPassword:querenPassword,
        },
        dataType:"json",
        success:function(data){
        	console.log("成功返回的数据",data);	
			if(data.prompt == "修改密码成功"){
				alert("修改成功")
			}else{
				alert(data.prompt);
			}	
        }
    })
    
})
