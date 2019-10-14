<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Quixlab - Bootstrap Admin Dashboard Template by Themefisher.com</title>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="images/favicon.png">
    <!-- Pignose Calender -->
    <link href="./plugins/pg-calendar/css/pignose.calendar.min.css" rel="stylesheet">
    <!-- Chartist -->
    <link rel="stylesheet" href="./plugins/chartist/css/chartist.min.css">
    <link rel="stylesheet" href="./plugins/chartist-plugin-tooltips/css/chartist-plugin-tooltip.css">
    <!-- Custom Stylesheet -->
    <link href="css/style.css" rel="stylesheet">


</head>

<body>

<!--*******************
    Preloader start
********************-->
<#include "freemarker/base/loader.ftl"/>
<!--*******************
    Preloader end
********************-->


<!--**********************************
    Main wrapper start
***********************************-->
<div id="main-wrapper">

    <!--**********************************
        Nav header start
    ***********************************-->
    <div class="nav-header">
        <div class="brand-logo">
            <a href="index.html">
                <b class="logo-abbr"><img src="images/logo.png" alt=""> </b>
                <span class="logo-compact"><img src="./images/logo-compact.png" alt=""></span>
                <span class="brand-title">
                        <img src="images/logo-text.png" alt="">
                    </span>
            </a>
        </div>
    </div>
    <!--**********************************
        Nav header end
    ***********************************-->

    <!--**********************************
        Header start
    ***********************************-->
    <#include "/freemarker/base/header.ftl"/>
    <!--**********************************
        Header end ti-comment-alt
    ***********************************-->
    <#include "/freemarker/base/dashboard.ftl"/>

    <!--**********************************
        Content body start
    ***********************************-->
    <div class="content-body">

        <div>
            <p><span><label id="time"></span></p>
        </div>

        <br/>

        <div>
            <p><span>今日新增注册数：<label id="register"/></span></p>
        </div>
        <div>
            <p><span>今日新增用户数：<label id="user"/></span></p>
        </div>
        <div>
            <p><span>注册转化率：<label id="ratio"></span></p>
        </div>

        <br/>

        <div>
            <p><span>今日活跃用户：</span></p>
        </div>
        <div>
            <p><span>本月活跃用户：</span></p>
        </div>

        <br/>

        <div>
            <p><span>今日收入：<label id="dayRevenue"></span></p>
        </div>
        <div>
            <p><span>今日支付笔数：<label id="payCount"></span></p>
        </div>
        <div>
            <p><span>本月收入：<label id="monthRevenue"></span></p>
        </div>

        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <a href="#" style="color:blue">修改密码</a>

    </div>
    <!--**********************************
        Content body end
    ***********************************-->


    <!--**********************************
        Footer start
    ***********************************-->
    <#include "/freemarker/base/footer.ftl"/>
    <!--**********************************
        Footer end
    ***********************************-->
</div>
<!--**********************************
    Main wrapper end
***********************************-->

<!--**********************************
    Scripts
***********************************-->

<script>
    // 页面加载事件：向后台发起请求
    window.onload = function () {

        $.get("/homepage/query", function (result) {
            if (result.code === 0) {
                if (result.data) {
                    $('#register').text(result.data.newRegister);
                    $('#user').text(result.data.newUser);
                    if (result.data.newUser != 0) {
                        $('#ratio').text(result.data.newRegister / result.data.newUser);
                    }
                    $('#dayRevenue').text(result.data.dayRevenue);
                    $('#payCount').text(result.data.payCount);
                    $('#monthRevenue').text(result.data.monthRevenue);
                }
            } else {
                alert("服务器异常，请联系管理员！")
            }

        }, "json");
    }
</script>

<--!显示当前时间-->
<script>
    function showtime() {
        var time = document.getElementById("time")

        var date = new Date();
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var day = date.getDate();
        var hours = date.getHours();
        var minutes = date.getMinutes();
        var seconds = date.getSeconds();

        var show = year + '-' + month + '-' + day + '&ensp;' + hours + ':' + minutes + ':' + seconds;
        time.innerHTML = show;
    }

    showtime();
</script>


<script src="plugins/common/common.min.js"></script>
<script src="js/custom.min.js"></script>
<script src="js/settings.js"></script>
<script src="js/gleek.js"></script>
<script src="js/styleSwitcher.js"></script>


<script src="./js/dashboard/dashboard-1.js"></script>

</body>

</html>