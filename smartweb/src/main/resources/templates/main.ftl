<!DOCTYPE html>
<html lang="en">

<head>
    <base href="${basePath}"/>
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



        <!--**********************************
                    弹框
        ***********************************-->
        <!--弹框：修改密码-->
        <div class="modal fade" id="updateModal" tabindex="-1" role="dialog"
             aria-labelledby="exampleModalLabel" style="display: none;" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">修改密码</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="form-group">
                                <span for="message-text" class="col-form-label">旧密码:</span>
                                <input type="text" class="form-control" id="oldPassword">
                            </div>
                            <div class="form-group">
                                <span for="message-text" class="col-form-label">新密码:</span>
                                <input type="text" class="form-control" id="newPassword">
                            </div>
                            <div class="form-group">
                                <span for="message-text" class="col-form-label">确认新密码:</span>
                                <input type="text" class="form-control" id="confirm">
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" onclick="updatePassword()" data-dismiss="modal">
                            确认修改
                        </button>
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


<!--发送新旧密码到后台-->
<script>
    function updatePassword() {

        var oldPassword = $('#oldPassword').val();
        var newPassword = $('#newPassword').val();
        var confirm = $('#confirm').val();

        if (newPassword != confirm) {
            alert("输入密码不一致，请重新输入！");
        } else {
            $.post("/updatePassword", {oldPassword:oldPassword, newPassword:newPassword}, function (result) {
                if (result.code === 0) {
                    alert("密码修改成功！")
                } else {
                    alert("密码修改失败！")
                }
            }, "json");
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