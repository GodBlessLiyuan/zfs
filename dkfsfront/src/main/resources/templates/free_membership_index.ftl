<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <title>免费领会员</title>
    <link rel="stylesheet" href="${basePath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/css/style.css">
</head>


<body style="background:  url(${basePath}/images/bg_evaluate.png) center center;background-size: cover;background-repeat: no-repeat;">

    <div class="container">
        <div class="row">
            <div class="div_participate" style="margin-top: 400px;" onclick="freememberClick()">
                立即参与
            </div>
        </div>

        <div class="row">
            <div class="div_pickuptips" style="margin-top: 40px;" >
                <p style="margin-left: 10%;margin-right: 10%">
                    <b>领取提示：</b><br>
                    1.每位用户只能领取一次奖励<br>
                    2.将您在应用市场5星好评截图上传即可，审核通过后即可获得会员奖励<br>
                    3.奖励为7天VIP
                </p>
            </div>
        </div>
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