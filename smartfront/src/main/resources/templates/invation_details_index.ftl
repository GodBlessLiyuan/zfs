<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>我的邀请详情</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="css/style.css">
    <script src="js/generalize.js" type="text/javascript"></script>

    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

    <!--总体情况-->
<!--    <div class="well well-sm">
        <b>总体情况</b>
    </div>-->


    <div class="well well-sm" style="margin-top: 20px;border:none">
        <span class="span_thead"> 总体情况 </span>
    </div>


    <div class="my_state ">

        <div class="rows state_bottom ">
            <div class="flex1" catchtap="my_pickGoods">
                <p class="peopleNum"><b>${res.invitenum}</b></p>
                <p class="peopleTitle">成功邀请</p>
            </div>
            <div class="flex1" catchtap="my_inventory">
                <p class="peopleNum"><b>${res.paynum}</b></p>
                <p class="peopleTitle">付款人数</p>
            </div>
            <div class="flex1" catchtap="no_payment">
                <p class="peopleNum" style="color:red"><b>${res.totalmny}</b></p>
                <p class="peopleTitle">累计奖励</p>
            </div>
        </div>
    </div>

<!-- 邀请详情情况 -->

    <div class="well well-sm" style="margin-top: 20px">
        <span class="span_thead"> 邀请详情 </span>
    </div>

    <div class="container">

        <table class="table table-hover" >
            <!--<caption>邀请详情</caption>-->
            <thead>
            <tr>
                <td>手机号</td>
                <td>注册时间</td>
                <td>现金收益</td>
            </tr>
            </thead>
            <tbody>
            <#list res.details as detail>
                <tr>
                    <td id="ph">${detail.ph}</td>

                    <#if (detail.ctime)??>
                        <td >${detail.ctime?string("yyyy-MM-dd")}</td>
                    <#else>
                        <td >暂未注册</td>
                    </#if>

                    <#if (detail.earnings)?? >
                        <td style="color: red">${detail.earnings}</td>
                    <#else>
                        <td >暂无收益</td>
                    </#if>

                </tr>
            </#list>
            </tbody>
        </table>
    </div>


</body>
</html>