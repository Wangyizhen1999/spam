/**
 * 占比
 */ 
    //从数据库读取数据赋值给echarts
 function option1(myChart1){
		var memberId = sessionStorage.getItem("memberId");
        $.ajax({
            type : "post",
            url:"/mail/mailNum",
            dataType : "json",
            data:{
    			memberId:memberId,
    		},
            success:function(data) {
                //创建一个数组，用来装对象传给series.data，因为series.data里面不能直接写for循环
//                var servicedata=[];
// 
//                for(var i=0;i<data.length;i++){
//                    var obj=new Object();
//                    obj.value=data[i];
//                    servicedata[i]=obj;
            
            	console.log("成功返回的数据",data);
    			var normalNum = data.normalNum;
    			var spamNum = data.spamNum;
 
                myChart1.setOption({
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b}: {c} ({d}%)'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 10,
                        data:['普通邮件','垃圾邮件']
                    },
                    series: [
                        {
                            name: '访问来源',
                            type: 'pie',
                            radius: ['50%', '70%'],
                            avoidLabelOverlap: false,
                            label: {
                                show: false,
                                position: 'center'
                            },
                            emphasis: {
                                label: {
                                    show: true,
                                    fontSize: '30',
                                    fontWeight: 'bold'
                                }
                            },
                            labelLine: {
                                show: false
                            },
                            data: [
                                {value: normalNum, name: '普通邮件'},
                                {value: spamNum, name: '垃圾邮件'},
                            ],
                        }
                    ]
                })
 
            }
        });
    }
 
    //初始化echarts实例
    var myChart1 = echarts.init(document.getElementById('zhanbi'));
    option1(myChart1);    

/**
 * 域名
 */
    function option2(myChart2){
        $.ajax({
            type : "post",
            url:"/addressder/addressderShow",
            dataType : "json",
            success : function(data) {
                //创建一个数组，用来装对象传给series.data，因为series.data里面不能直接写for循环
                var servicedata=[];
 
                for(var i=0;i<data.length;i++){
                    var obj=new Object();
                    obj.name=data[i].addresser; 
                    obj.value=data[i].number;
                    servicedata[i]=obj;
                }
 
                myChart2.setOption({
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b}: {c} ({d}%)'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 10,
                        data:servicedata
                    },
                    series: [
                        {
                            name: '访问来源',
                            type: 'pie',
                            radius: ['50%', '70%'],
                            avoidLabelOverlap: false,
                            label: {
                                show: false,
                                position: 'center'
                            },
                            emphasis: {
                                label: {
                                    show: true,
                                    fontSize: '30',
                                    fontWeight: 'bold'
                                }
                            },
                            labelLine: {
                                show: false
                            },
                            data: servicedata,
                        }
                    ]
                })
 
            }
        });
    }
 
    //初始化echarts实例
    var myChart2 = echarts.init(document.getElementById('yuming'));
    option2(myChart2);
/**
 * 主题词频
 */
    function option3(myChart3){
        $.ajax({
            type : "post",
            url:"/theme/themeShow",
            dataType : "json",
            success : function(data) {
                //创建一个数组，用来装对象传给series.data，因为series.data里面不能直接写for循环
                var servicedata=[];
 
                for(var i=0;i<data.length;i++){
                    var obj=new Object();
                    obj.name=data[i].theme; 
                    obj.value=data[i].number;
                    servicedata[i]=obj;
                }
 
                myChart3.setOption({
    title: {
        text: '垃圾邮件主题词频统计',
        subtext: '纯属虚构',
        left: 'center'
    },
    tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b} : {c} ({d}%)'
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        data:servicedata
    },
    series: [
        {
            name: '访问来源',
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            data: servicedata,

            emphasis: {
                itemStyle: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
});
            }
        });
    }
    //初始化echarts实例
    var myChart3 = echarts.init(document.getElementById('zhuti'));
    option3(myChart3);


/**
 * 正文词频
 */
    function option4(myChart4){
        $.ajax({
            type : "post",
            url:"/text/textShow",
            dataType : "json",
            success : function(data) {
                //创建一个数组，用来装对象传给series.data，因为series.data里面不能直接写for循环
                var servicedata=[];
 
                for(var i=0;i<data.length;i++){
                    var obj=new Object();
                    obj.name=data[i].text; 
                    obj.value=data[i].number;
                    servicedata[i]=obj;
                }
 
                myChart4.setOption({
    title: {
        text: '垃圾邮件正文词频统计',
        subtext: '纯属虚构',
        left: 'center'
    },
    tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b} : {c} ({d}%)'
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        data:servicedata
    },
    series: [
        {
            name: '访问来源',
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            data: servicedata,

            emphasis: {
                itemStyle: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
});
            }
        });
    }
    //初始化echarts实例
    var myChart4 = echarts.init(document.getElementById('zhengwen'));
    option4(myChart4);

/**
 * 文本大小
 */
    function option5(myChart5){
        $.ajax({
            type : "post",
            url:"/size/sizeShow",
            dataType : "json",
            success : function(data) {
                //创建一个数组，用来装对象传给series.data，因为series.data里面不能直接写for循环
                var servicedata=[];
 
                for(var i=0;i<data.length;i++){
                    var obj=new Object();
                    obj.name=data[i].size; 
                    obj.value=data[i].number;
                    servicedata[i]=obj;
                }
 
                myChart5.setOption({
    title: {
        text: '垃圾邮件文本大小词频统计',
        subtext: '纯属虚构',
        left: 'center'
    },
    tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b} : {c} ({d}%)'
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        data:servicedata
    },
    series: [
        {
            name: '访问来源',
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            data: servicedata,

            emphasis: {
                itemStyle: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
});
            }
        });
    }
    //初始化echarts实例
    var myChart5 = echarts.init(document.getElementById('wenben'));
    option5(myChart5);


/**
 * 其他特征
 */
    function option6(myChart6){
        $.ajax({
            type : "post",
            url:"/color/colorShow",
            dataType : "json",
            success : function(data) {
                //创建一个数组，用来装对象传给series.data，因为series.data里面不能直接写for循环
                var servicedata=[];
 
                for(var i=0;i<data.length;i++){
                    var obj=new Object();
                    obj.name=data[i].color; 
                    obj.value=data[i].number;
                    servicedata[i]=obj;
                }
 
                myChart6.setOption({
    title: {
        text: '垃圾邮件字体颜色词频统计',
        subtext: '纯属虚构',
        left: 'center'
    },
    tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b} : {c} ({d}%)'
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        data:servicedata
    },
    series: [
        {
            name: '访问来源',
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            data: servicedata,

            emphasis: {
                itemStyle: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
});
            }
        });
    }
    //初始化echarts实例
    var myChart6 = echarts.init(document.getElementById('qita'));
    option6(myChart6);
