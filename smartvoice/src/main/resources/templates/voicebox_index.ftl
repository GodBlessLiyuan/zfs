<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>语音盒子</title>
   <#-- <link rel="stylesheet" href="./share/css/style.css">-->

</head>
<style>
    .div_main {
        width: 100%;
        background: #FFFFFF;
        box-shadow: 0 -1px 0 0 #EEEEEE;
    }

    .div_tips {

        background: #F8F8F8;
        padding:20px 10%;

        font-family: PingFang-SC-Medium;
        font-size: 12px;
        color: #999999;
        letter-spacing: 0;
        line-height: 20px;
    }

    .div_a {
        font-family: PingFangSC-Semibold;
        font-size: 12px;
        color: #3698E9;
        letter-spacing: 0;
    }

    .div_voice {
        padding:20px 10%;
    }

    .content li video {
        width: 100%;
        height: 80px;
    }
</style>
<body >
    <div class="div_main">
        <div class="div_voice">
            <ul class="content">
                <#if !res.data.voices?exists || res.data.voices?size==0>
                    没有语音！
                <#else>
                    <#list res.data.voices as voice>
                        <li>
                            <audio controls="controls" controlslist="nodownload">
                                <source src="${voice}" type="audio/mpeg">
                            </audio>
                        </li>
                    </#list>
                </#if>
            </ul>
        </div>
        <div class="div_tips">
            注：此录音只保存72小时，72小时后将删除，
            本语音由砖助智能助手提供技术支持。
             <#--    <a class="div_a" href="" >点击下载</a>-->
        </div>
    </div>
</body>
</html>
