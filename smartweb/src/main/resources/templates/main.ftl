<!DOCTYPE html>
<html lang="en">

<head>
    <base href="${basePath}"/>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>智能移动社交营销平台管理系统</title>
    <!-- Favicon icon -->

    <!-- Pignose Calender -->
    <link href="./plugins/pg-calendar/css/pignose.calendar.min.css" rel="stylesheet">
    <!-- Chartist -->
    <link rel="stylesheet" href="./plugins/chartist/css/chartist.min.css">
    <link rel="stylesheet" href="./plugins/chartist-plugin-tooltips/css/chartist-plugin-tooltip.css">
    <!-- Custom Stylesheet -->
    <link href="css/style.css" rel="stylesheet">


</head>

<style>
    .coo_contaier {
        display:-webkit-flex;
        display: flex;
        justify-content: space-around;

    }
</style>

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
                <span style="text-align: center;font-size:24px;font-weight: bolder"><label id="time"></label></span>
            </div>
        </div>
        
        <#--<div class="container-fluid mt-3">
            <div class="row">
                <div class="col-lg-3 col-sm-6">
                    <div class="card gradient-1">
                        <div class="card-body">
                            <h3 class="card-title text-white">今日新增注册：</h3>
                            <div class="d-inline-block">
                                <h2 class="text-white" id="newRegist"></h2>
                                <p class="text-white mb-0" id="newUsers"></p>
                                <p class="text-white mb-0" id="registTranRate"></p>
                            </div>
                            <span class="float-right display-5 opacity-5"><i class="fa fa-shopping-cart"></i></span>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-sm-6">
                    <div class="card gradient-2">
                        <div class="card-body">
                            <h3 class="card-title text-white">今日活跃用户：</h3>
                            <div class="d-inline-block">
                                <h2 class="text-white" id="activeUsers"></h2>
                                <p class="text-white mb-0" id="activeMonthUsers"></p>
                            </div>
                            <span class="float-right display-5 opacity-5"><i class="fa fa-money"></i></span>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-sm-6">
                    <div class="card gradient-3">
                        <div class="card-body">
                            <h3 class="card-title text-white">今日收入：</h3>
                            <div class="d-inline-block">
                                <h2 class="text-white" id="dailyIncome"></h2>
                                <p class="text-white mb-0" id="dailyPayNum"></p>
                                <p class="text-white mb-0" id="monthIncome"></p>
                            </div>
                            <span class="float-right display-5 opacity-5"><i class="fa fa-users"></i></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>-->


        <div class="div_big">
            <div class="container-fluid">
                <div class="row" style="-webkit-display: flex; display: flex; -webkit-justify-content: center;
                    justify-content: center; -webkit-align-items: center; align-items: center;">

                    <div class="col-3 col-sm-3">
                        <div class="card">
                            <div class="card-body">
                                <div class="div_main">
                                    <p class="p_first"><span>今日新增注册数：<label id="register"/></span></p>
                                    <p class="p_left"><span>今日新增用户数：<label id="user"/></span></p>
                                    <p class="p_left"><span>注册转化率：<label id="ratio"></span></p>
                                </div>
                            </div>
                        </div>
                    </div>
                <#--</div>
            </div>

            <div class="container-fluid">
                <div class="row">-->
                    <div class="col-3 col-sm-3">
                        <div class="card">
                            <div class="card-body">
                                <div class="div_main">
                                    <p class="p_left"><span>今日活跃用户：</span></p>
                                    <p class="p_left"><span>本月活跃用户：</span></p>
                                </div>
                            </div>
                        </div>
                    </div>
               <#-- </div>
            </div>

            <div class="container-fluid">
                <div class="row">-->
                    <div class="col-3 col-sm-3">
                        <div class="card">
                            <div class="card-body">
                                <div class="div_main">
                                    <p class="p_first"><span>今日收入：<label id="dayRevenue"></span></p>
                                    <p class="p_left"><span>今日支付笔数：<label id="payCount"></span></p>
                                    <p class="p_left"><span>本月收入：<label id="monthRevenue"></span></p>
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