<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <title> 会员免费领 </title>
    <link rel="stylesheet" href="${basePath}/css/style.css">
    <script src="${basePath}/js/generalize.js" type="text/javascript"></script>
    <link rel="stylesheet" href="${basePath}/css/bootstrap.min.css">
    <script src="${basePath}/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${basePath}/js/jquery-3.3.1.min.js" type="text/javascript"></script>

</head>
    <body >
        <div style="margin-top: 50px;text-align: center">
            <h2>应用市场五星好评</h2>
            <h2>会员免费领</h2>
        </div>
        <div style="margin-top: 40%">
            <div class="betweenLR">
                <#if null == status || status == 20>
                <button type="button" class="drawSubmit" id="participate" onclick="freememberClick()">立即参与</button>
                </#if>
            </div>
        </div>
        <div style="margin-left: 15%;margin-right: 15%">
            <p style="text-align: center;vertical-align: middle; border:2px solid darkgray;border-radius: 5px;">${msg}</p>
        </div>
        <div style="margin-top: 10%">
            <P style="margin-left: 10%;margin-right: 5%">
                <b>领取提示：</b><br>
                1.每位用户只能领取一次奖励<br>
                2.将您在应用市场5星好评截图上传即可，审核通过后即可获得会员奖励<br>
                3.奖励为7天VIP
            </P>
        </div>
    </body>

<!--*****************************
           script
*****************************-->
<script>
    /**
     * 页面加载事件：一进入该页面，即判断是否该显示“立即参与”按钮
     */
    /*var status =
    window.onload = function () {
        if (null == status || status == 20) {
            document.getElementById("participate").style.visibility = "visible";
        } else {
            document.getElementById("participate").style.visibility = "hidden";
        }
    }*/


    /**
     * 立即参与
     */
    function freememberClick() {
        window.User.freemember();
    }
</script>

</html>
