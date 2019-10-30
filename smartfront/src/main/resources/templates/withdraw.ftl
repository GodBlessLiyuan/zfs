<html lang="en"><head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <title>余额提现</title>

    <link rel="stylesheet" href="/css/style.css">
    <script src="/js/generalize.js" type="text/javascript"></script>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="/js/bootstrap.min.js" type="text/javascript"></script>

</head>
<body>

    <!--顶部背景图片-->
    <div class="backimag" >
    </div>

    <!--余额及提现 -->
    <div class="my_state1 bg_color" >

        <div class="">
            <div class="total" >
                <span id="balance" class="tx_time" style="font-size: 18px">可提金额:￥${res.data.balance}</span>
            </div>
        </div>
        <div>
            <div style="width: 70%; float: left">
                <div class="input-group" style="margin-left: 15px">
                    <span class="input-group-addon" style="border: none;font-size: 20px;background-color:#FFFFFF">￥</span>
                    <input id="importBalance" type="text" class="form-control" style="font-size: 20px;border: none" placeholder="请输入提现金额">
                </div>wi
            </div>
            <div style="width: 20%; float:right;margin-right: 25px">
                <button  type="button" class="btn btn-primary" style="text-align: center" onclick="drawTotal()">全部提现</button>
            </div>
        </div>
    </div>

    <!--请输入支付宝信息-->
    <div class="my_state2 bg_color" style="height: 160px" >
        <br>
        <p class="font_tishi" ><b>&nbsp;&nbsp;&nbsp;&nbsp;请输入支付宝信息</b></p>

        <div style="margin-left: 15px">
            <input id="accountNum" class="input_com font_placeholder"  style="padding-left: 10px" type="text" placeholder="请输入支付宝账号">
        </div>

        <div style="margin-left: 15px">
            <input id="Name" class="input_com font_placeholder" style="padding-left: 10px" type="text" placeholder="请输入姓名(与支付宝实名一致)">
        </div>
    </div>

    <!--提示规则-->
    <div class="my_state2 bg_color" style="height:210px" >
        <p style="margin-left: 10px;margin-top: 10px"><b>提现规则:</b></p>
        <p  style="margin-left: 10px">
            1.最低提现1元<br>
            2.每次提现只能提整数<br>
            3.每次提现金额将于48小时到账、双休日、法定节假日顺延<br>
            4.当提现信息有误时、将会进行驳回、驳回后可重新进行提现操作<br>
            5.如发现作弊情况、将对客户进行封号处理，概不结算。
        </p>
    </div>

    <form action="">
        <p class="errorMsg"></p>
        <div class="betweenLR">
            <button type="button" class="drawSubmit" onclick="showBox()">提现</button>
        </div>
    </form>


<!-- 遮罩层 -->
<div id="zhezhaoa"></div>

<!-- 提现确认弹框 -->
<div class="explain-box">
    <div class="box-top">
        <div class="box-title">提现信息确认</div>
        <p class="box-text">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：<span id="accountNum2"></span></p>
        <p class="box-text">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：<span id="Name2"></span></p>
        <p class="box-text">提现金额：<span id="tx_money"></span></p>
    </div>
    <div class="rows">
        <div class="box-btnL lineL" onclick="closeBox()"><b>取消</b></div>
        <div class="box-btnR" onclick="successBox('f70d81abc1b2df6fe22e876e43030a89')"><b>确认提现</b></div>
    </div>
</div>

<!-- 提现成功弹框 -->
<div class="successBox">
    <div class="box-top">
        <div class="box-title">提现成功</div>
        <p class="box-text">提现申请已提交，提款审核结束后48小时内到账，双休日、法定节假日顺延</p>
    </div>
    <div class="successBottom">
        <div class="successBtn" onclick="closeSuccessBox()">确定</div>
    </div>
</div>

<!-- 请勿重复提现弹框 -->
<div class="infoBox">
    <div class="box-top">
        <p class="box-text2"></p>
    </div>
    <div class="successBottom">
        <div class="successBtn" onclick="closeinfoBox()">确定</div>
    </div>
</div>

<script>
    // 打开“提现确认”弹框---并验证信息
    function showBox(){
        if($("#importBalance").val()==""||$("#importBalance").val()==undefined){
            $(".errorMsg").text("提现金额不能为空")
        }else if(parseFloat($("#importBalance").val())>parseFloat($("#balance").text())){
            $(".errorMsg").text("提现金额不能大于可用余额")
        }else if($("#importBalance").val()<1){
            $(".errorMsg").text("最低金额需达1元")
        }else if(Number.isInteger($("#importBalance").val()-0)==false){
            $(".errorMsg").text("提现金额需是整数")
        }else if($("#accountNum").val()==""||$("#accountNum").val()==undefined){
            $(".errorMsg").text("支付宝账号不能为空")
        }else if($("#Name").val()==""||$("#Name").val()==undefined){
            $(".errorMsg").text("支付宝姓名不能为空")
        }else{

            var  accountNum = $("#accountNum").val();
            var  Name = $("#Name").val();
            var money = $("#importBalance").val();

            $("#accountNum2").text(accountNum);
            $("#Name2").text(Name);
            $("#tx_money").text(money);

            $(".explain-box").show();
            $("#zhezhaoa").css({
                "display":"block"
            });
            $("html,body").css({"height":"100%","overflow":"hidden"});
        }
    }
    // 取消“提现确认”弹框
    function closeBox(){
        $(".explain-box").hide();
        $("#zhezhaoa").css({"display":"none"});
        $("html,body").css({"height":"auto","overflow":"auto"});
        // location.reload();//刷新页面
    }
    //打开提现成功弹框
    function successBox(godin_id){
        // 提现金额、账号、姓名
        var money = $("#importBalance").val();
        var zfb_account = $("#accountNum").val();
        var name = $("#Name").val();


        $.ajax({
            url: "",
            data: {
                "money": money,
                "zfb_account": zfb_account,
                "name": name,
                "godin_id": godin_id
            },
            type: "POST",
            async: false,
            success: function(res) {
                if ('0000' == res["statuscode"]) {
                    closeBox();
                    $(".successBox").show();
                    $("#zhezhaoa").css({
                        "display":"block"
                    });
                    $("html,body").css({"height":"100%","overflow":"hidden"});
                } else if ('0001' == res["statuscode"]) {

                    closeBox();
                    $(".box-text2").text("已有提现订单，请勿重复申请");
                    $(".infoBox").show();
                    $("#zhezhaoa").css({
                        "display":"block"
                    });
                    $("html,body").css({"height":"100%","overflow":"hidden"});

                } else {

                    closeBox();
                    $(".box-text2").text("提现失败,请稍后再试");
                    $(".infoBox").show();
                    $("#zhezhaoa").css({
                        "display":"block"
                    });
                    $("html,body").css({"height":"100%","overflow":"hidden"});
                }
            },
            cache: false,
            fail: function() {
            }
        })

    }
    // 关闭提现成功弹框
    function closeSuccessBox(){
        $(".successBox").hide();
        $("#zhezhaoa").css({"display":"none"});
        $("html,body").css({"height":"auto","overflow":"auto"});
        location.reload();//刷新页面
    }
    // 关闭请勿重复提现弹框
    function closeinfoBox(){
        $(".infoBox").hide();
        $("#zhezhaoa").css({"display":"none"});
        $("html,body").css({"height":"auto","overflow":"auto"});
        location.reload();//刷新页面
    }
    //全部体现按钮功能
    function drawTotal(){
        var  balance = $("#balance").text();
        $("#importBalance").val(balance);
    }
</script>

</body>

</html>