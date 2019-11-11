<!DOCTYPE html>
<html lang="en">

<head>
    <base href="${basePath}"/>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>首页</title>
    <!-- Favicon icon -->

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
    <#include "freemarker/base/nav_header.ftl"/>
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

        <div class="div_big" style="margin-top: 100px;margin-bottom: 50px">
            <div style="text-align: center">
                <span style="text-align: center; font-size: 30px; font-weight: bolder"><label id="time"></label></span>
            </div>
        </div>


        <div class="div_big">
            <div class="container-fluid mt-3">
                <div class="row" style="-webkit-display: flex; display: flex; -webkit-justify-content: center;
                    justify-content: center; -webkit-align-items: center; align-items: center;">

                    <div class="col-3 col-sm-3">
                        <div class="card gradient-1">
                            <div class="card-body">
                                <div class="div_main">
                                    <p class="" style="font-size: 20px">今日新增注册数：<span id="register"></span></p>
                                    <p class="" style="font-size: 20px">今日新增用户数：<span id="user"></span></p>
                                    <p class="" style="font-size: 20px">注册转化率：<span id="ratio"></span></p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-3 col-sm-3">
                        <div class="card gradient-1">
                            <div class="card-body">
                                <div class="div_main">
                                    <p class="" style="font-size: 20px">今日活跃用户：<span></span></p>
                                    <p class="" style="font-size: 20px">本月活跃用户：<span></span></p>
                                    <p class="" style="font-size: 20px">&nbsp;<span></span></p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-3 col-sm-3">
                        <div class="card gradient-1">
                            <div class="card-body">
                                <div class="div_main">
                                    <p class="" style="font-size: 20px">今日收入：<span id="dayRevenue"></span></p>
                                    <p class="" style="font-size: 20px">今日支付笔数：<span id="payCount"></span></p>
                                    <p class="" style="font-size: 20px">本月收入：<span id="monthRevenue"></span></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
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
    /**
     * 页面加载事件：向后台发起请求
     */
    window.onload = function () {

        $.get("/homepage/query", function (result) {
            if (result.code === 1008) {
                alert("登录超时，请重新登录！");
                window.location.href = '/login';
            } else if (result.code === 0) {
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


    /**
     * 显示当前时间
     */
    function showtime() {
        var time = document.getElementById("time")

        var date = new Date();
        var year = date.getFullYear();
        var month = extra(date.getMonth() + 1);
        var day = extra(date.getDate());
        var hours = extra(date.getHours());
        var minutes = extra(date.getMinutes());
        var seconds = extra(date.getSeconds());

        var show = year + '-' + month + '-' + day + '&ensp;' + hours + ':' + minutes + ':' + seconds;
        time.innerHTML = show;
    }

    showtime();


    //如果传入数字小于10，数字前补一位0
    function extra(x) {
        if (x < 10) {
            return "0" + x;
        }
        else {
            return x;
        }
    }

</script>
<script src="plugins/common/common.min.js"></script>
<script src="js/custom.min.js"></script>
<script src="js/settings.js"></script>
<script src="js/gleek.js"></script>
<script src="js/styleSwitcher.js"></script>


<script src="./js/dashboard/dashboard-1.js"></script>

</body>

</html>