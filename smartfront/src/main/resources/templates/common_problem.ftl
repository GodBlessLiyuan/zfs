<!DOCTYPE html>
<html lang="en">
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <title> 常见问题 </title>
    <script src="js/generalize.js" type="text/javascript"></script>

    <link rel="stylesheet" href="${basePath}/css/style.css">
    <script src="${basePath}/js/generalize.js" type="text/javascript"></script>
    <link rel="stylesheet" href="${basePath}/css/bootstrap.min.css">
    <script src="${basePath}/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${basePath}/js/jquery-3.3.1.min.js" type="text/javascript"></script>

</head>
<style>
    a{
        text-decoration: none;
        display: block;
        font-size: 16px;
        color: #fff;
        line-height: 20px;
        background: lightslategrey;
        border-top: 1px solid black;
        border-bottom: 1px solid black;
    }
    .box_div{
        position: relative;
    }
    span{
        position: absolute;
        top: 3px;
        right: 3px;
        width: 0;
        height: 0;
        display: block;
        border-top: 6px solid #fff;
        border-bottom: 6px solid transparent;
        border-right: 6px solid transparent;
        border-left: 6px solid transparent;
    }
    p{
        height: 0px;
        overflow: hidden;
        font-size: 15px;
        color: #000;
        line-height: 20px;
        padding-left: 5px;
    }
    .box_div:target p{
        height: auto;
    }
    .box_div:target span{
        border-top: 6px solid transparent;
        border-bottom: 6px solid #fff;
        border-right: 6px solid transparent;
        border-left: 6px solid transparent;
    }
</style>

<body>
<div class="box">
    <div class="box_div" id="box_one">
        <a href="#box_one" style="padding-left: 5px">悬浮窗如何开启？</a>
        <span></span>
        <p>
            <br>
            OPPO手机：<br>
            安卓系统6.0以上：手机管家->隐私权限->悬浮窗权限管理->开启微商工具箱<br>
            安卓系统5.0以下：手机管家->隐私权限->悬浮窗权限管理->开启微商工具箱<br>
            VIVO手机：<br>
            方式一：设置->更多设置->权限管理->微商工具箱->开启悬浮窗;<br>
            方式二：i管家->权限管理->权限->悬浮窗->微商工具箱->开启<br>
            华为手机：设置->权限管理->悬浮窗->开启微商工具箱<br>
            魅族手机：手机管家->应用权限管理->微商工具箱->开启桌面悬浮窗<br>
            小米手机：安全中心->应用权限管理->微商工具箱->允许显示悬浮窗<br>
            三星手机：设置->应用程序->应用程序管理器->右上角更多->可出现在顶部的应用程序->开启微商工具箱<br>
            联想手机：安全中心->权限管理->显示悬浮窗->允许微商工具箱显示悬浮窗<br>
        </p>
    </div>
    <div class="box_div" id="box_two">
        <a href="#box_two" style="padding-left: 5px">辅助服务如何开启？</a>
        <span></span>
        <p>
            <br>
            OPPO手机：设置->其他设置->辅助功能->开启微商工具箱辅助服务<br>
            VIVO手机：设置->更多设置->辅助功能->开启微商工具箱辅助服务<br>
            华为手机：设置->高级设置->辅助功能->开启微商工具箱辅助服务<br>
            魅族手机：设置->更多设置->无障碍->开启微商工具箱辅助服务<br>
            小米手机：设置->更多设置->无障碍->开启微商工具箱辅助服务<br>
            三星手机：设置->辅助功能->开启微商工具箱辅助服务<br>
            联想手机：设置->高级设置->辅助功能->开启微商工具箱辅助服务<br>
        </p>
    </div>
    <div class="box_div" id="box_three">
        <a href="#box_three" style="padding-left: 5px">微信支持版本</a>
        <span></span>
        <p>
            <br>
            砖助智能助手支持微信：v7.0.7，v7.0.8<br>
            如检测微信版本不支持，可使用以下方法解决：<br>
            1.微信发布新版本后暂不升级，待砖助智能助手发新版本支持后在升级<br>
            2.已安装新版本的可以卸载微信安装老版本。<br>
            3.无法安装老版本可以等待砖助智能助手发布新版本，新版本一般在24小时内更新。<br>
        </p>
    </div>
</div>
</body>
</html>
