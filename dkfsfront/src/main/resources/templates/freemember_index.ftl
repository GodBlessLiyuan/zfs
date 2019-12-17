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
    <body style="background-image:  url(${basePath}/images/bg_evaluate.png);background-size: contain;background-repeat: no-repeat;background-color: #FA524A">
        <div class="betweenLR" style="margin-top: 400px">
            <#if null == status || status == 20>
            <button type="button" class="drawSubmit3" style="background-color: #FFE8B9;color:#D20f1C;font-size: 20px" onclick="freememberClick()">立即参与</button>
            </#if>
        </div>

        <div class="div_text" style="margin-left: 20%;margin-right: 20%;border:none;">
            <p style="text-align: center;vertical-align: middle; padding-top: 5px;">${msg}</p>
        </div>

        <div class="div_text" style="margin-top: 50px;" >
            <p style="margin-left: 10%;margin-right: 5%">
                <b>领取提示：</b><br>
                1.每位用户只能领取一次奖励<br>
                2.将您在应用市场5星好评截图上传即可，审核通过后即可获得会员奖励<br>
                3.奖励为7天VIP
            </p>
        </div>
    </body>

<!--*****************************
           script
*****************************-->
<script>
    /**
     * 立即参与
     */
    function freememberClick() {
        window.User.freemember();
    }
</script>

</html>
