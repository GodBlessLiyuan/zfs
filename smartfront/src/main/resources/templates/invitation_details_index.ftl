<!DOCTYPE html>
<html lang="en">
<head>
    <base href="${basePath}"/>
    <meta charset="UTF-8">
    <title>我的邀请详情</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">

    <link rel="stylesheet" href="./share/css/style.css">
    <script src="./share/js/generalize.js" type="text/javascript"></script>
    <link rel="stylesheet" href="./share/css/bootstrap.min.css">
    <script src="./share/js/bootstrap.min.js" type="text/javascript"></script>

</head>
<body onload="window.Share.showBackView()">


    <div class="my_state" style="box-shadow: 1px 1px 4px 4px #eeeeee;padding-top: 20px;">
        <span class="span_thead"> 总体情况 </span>
        <div class="rows state_bottom ">
            <div class="flex1" catchtap="my_pickGoods">
                <p class="peopleNum"><b>${res.data.invitenum}</b></p>
                <p class="peopleTitle">成功邀请</p>
            </div>
            <div class="flex1" catchtap="my_inventory">
                <p class="peopleNum"><b>${res.data.paynum}</b></p>
                <p class="peopleTitle">付款人数</p>
            </div>
            <div class="flex1" catchtap="no_payment">
                <p class="peopleNum" style="color:red"><b>${res.data.totalmny}</b></p>
                <p class="peopleTitle">累计奖励</p>
            </div>
        </div>
    </div>

    <!-- 邀请详情情况 -->
    <div class="my_state" style="box-shadow: 1px 1px 4px 4px #eeeeee;padding-top: 20px;">
        <span class="span_thead"> 邀请详情 </span>

        <div style="margin: 20px 10px 20px 10px">
            <table class="table table-hover" >
                <thead>
                <tr>
                    <td style="text-align: center">手机号</td>
                    <td style="text-align: center">注册时间</td>
                    <td style="text-align: center">现金收益</td>
                </tr>
                </thead>
                <tbody>
                <#list res.data.details as detail>
                    <tr>
                        <td id="ph" style="text-align: center">${detail.ph}</td>

                        <#if (detail.ctime)??>
                            <td style="text-align: center">${detail.ctime?string("yyyy-MM-dd")}</td>
                        <#else>
                            <td style="text-align: center">暂未注册</td>
                        </#if>

                        <#if (detail.earnings)?? >
                            <td style="color: red;text-align: center">${detail.earnings}</td>
                        <#else>
                            <td style="text-align: center" >暂无收益</td>
                        </#if>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>

    </div>

</body>
</html>