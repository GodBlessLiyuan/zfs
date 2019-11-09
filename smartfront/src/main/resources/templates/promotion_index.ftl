<!DOCTYPE html>
<html lang="en">
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <title> 智能推广营销工具 </title>

    <link rel="stylesheet" href="${basePath}/css/style.css">
    <script src="${basePath}/js/generalize.js" type="text/javascript"></script>
    <link rel="stylesheet" href="${basePath}/css/bootstrap.min.css">
    <script src="${basePath}/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${basePath}/js/jquery-3.3.1.min.js" type="text/javascript"></script>

</head>


    <body >
        <div class="promote_state" style="height:100%;width:100%; ">
            <div class="betweenLR" style="margin-top: 120%">
                <input id="phone" class="drawSubmit1" style="border:5px" required="true" maxlength="11" onchange="phoneCheck(this.value)" placeholder="请输入手机号码">
            </div>
            <span id="phone-err" class="span-err" style="margin-left: 15%">  </span>
            <div class="betweenLR" style="margin-top: 5%">
                <button type="button" class="drawSubmit" onclick="download()">下载APP</button>
            </div>
        </div>

        <style>
            body {
                background-image: url("${basePath}/images/bg_extension.png");
                background-repeat: no-repeat;
                background-size: cover;
                background-position: center 0px;
            }
        </style>

        <script>
            function phoneCheck(input){
                var b=true;
                if(input!=""){
                    var p1=/^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))[0-9]{8}$/;
                    if(!p1.test(input)){
                        document.getElementById("phone-err").innerHTML="号码错误，请输入正确手机号码。";
                        b = false;
                    }else {
                        document.getElementById("phone-err").innerHTML="";
                    }
                }else {
                    return false;
                }
                return b;
            }

            /*  定义一个全局变量 如果运营想从应用市场下载
                    * 判断一下， 变量为空 则从 后台数据库下载*/
            // 下载APP
            function download(){
                // 手机号
                var phone = $("#phone").val();
                if(!phoneCheck(phone)){
                    return;
                }

                $.ajax({
                    type: "post",
                    url: "/share/v1.0/download",
                    data: JSON.stringify({
                        "phone": phone,
                        "code": '${res.data}'
                    }),
                    dataType : "json",
                    contentType: "application/json; charset=utf-8",
                    success: function(res) {
                        if(res!=null){
                            window.location.href=res.data;
                        }
                    },
                    cache: false,
                    fail: function() {
                    }
                })
            }
        </script>
    </body>

</html>
