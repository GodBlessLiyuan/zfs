<!DOCTYPE html>
<html lang="en">
<head>
    <base href="${basePath}">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <title> 爱收益 </title>

    <link rel="stylesheet" href="./share/css/style.css">
    <script src="./share/js/generalize.js" type="text/javascript"></script>
    <link rel="stylesheet" href="./share/css/bootstrap.min.css">
    <script src="./share/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="./share/js/jquery-3.3.1.min.js" type="text/javascript"></script>

</head>
    <body onload="window.Share.hideBackView()">

        <!--顶部背景图片-->
        <div class="backimag" >
        </div>

        <!--余额及提现 -->
        <div class="my_state1 bg_color" >
            <div class="">
                <div class="total" style="font-size: 16px;color: #999999">
                    <b>我的余额</b>
                </div>
            </div>
            <div>
                <div style="width: 60%; float: left">
                    <div class="input-group" style="margin-left: 10px">
                        <p id="" class="balanceNum left font_money">¥${res.data.balance}</p>
                    </div>
                </div>
                <div style="width: 30%; height: 20px; float:right;margin-right: 15px">
                    <a class="btn btn-primary btn_color" onclick="withdrawmoney()" style="border-radius: 20px; width:100%;background-image: linear-gradient(90deg, #5AAEF5 0%, #3496E8 100%);
                    color: #ffffff;vertical-align: center">提现</a>
                </div>
            </div>
        </div>

        <!--帮你一步做推广，快速赚钱 -->
        <div class="my_state2" style="box-shadow: 1px 1px 4px 4px #eeeeee;">
            <div class="rows">
                <img class="bg_tuiguang" src="./share/images/banner_extension.png" onclick="promotePage()">
            </div>
        </div>

        <!--我的邀请 -->
       <div class="my_state" style="box-shadow: 1px 1px 4px 4px #eeeeee;">
            <div class="invation_top">
                <span class="font_inva">我的邀请</span>
                <span class="span_right "><a  class="font_inva" style="text-decoration:none" onclick="details()">详情></a></span>
            </div>
            <div class="rows state_bottom ">
                <div class="flex1" catchtap="my_pickGoods">
                    <p class="peopleNum">${res.data.invitenum}</p>
                    <p class="peopleTitle font_inva">成功邀请</p>
                </div>
                <div class="flex1" catchtap="my_inventory">
                    <p class="peopleNum">${res.data.paynum}</p>
                    <p class="peopleTitle font_inva">付款人数</p>
                </div>
                <div class="flex1" catchtap="no_payment">
                    <p class="peopleNum" style="color:red">${res.data.totalmny}</p>
                    <p class="peopleTitle font_inva">累计奖励</p>
                </div>
            </div>
        </div>

        <!--奖励相关  -->
        <div class="my_state" style="box-shadow: 1px 1px 4px 4px #eeeeee;">
                <div class="div_spacing">
                    <p class="font_reward padding_left" style="font-size: 15px;font-weight:bolder;margin-top:10px">如何获取奖励？</p>
                    <p class="font_reward padding_left" style="font-size: 13px">
                        1.好友通过你的邀请加入会员即可获得邀请奖励！
                    </p>
                    <p class="font_reward padding_left" style="font-size: 13px">
                        2.邀请奖励 = 好友支付金额 x 会员奖金比例。
                    </p>
                    <p class="drawTip padding_left" style="font-size: 12px">注：好友必须为新用户：从未注册过砖助智能助手的用户</p>
                </div>

                <hr class="width_dotline" style="border-top:1px #000000 dashed">

                <div><span class="font_reward padding_left"  style="font-size: 15px;font-weight: bolder">会员奖金比例是多少？</span></div>
                <div class="div_spacing">
                    <table class="table table-bordered table-hover table-condensed reward_details" style="width:90%" >
                        <thead>
                        <tr>
                            <td></td>
                            <td >非会员</td>
                            <td >VIP普通会员</td>
                            <td >VIP年会员</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr >
                            <td >奖金比例</td>
                            <td >5%</td>
                            <td >15%</td>
                            <td >30%</td>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <div class="div_spacing">
                    <p class="padding_left" style="font-weight: bolder">小砖温馨提示：</p>
                    <p class="drawTip padding_left">
                        1.好友支付时会根据您当前的会员状态分配奖励。<br>
                        2.VIP会员邀请后，如好友支付时你已不是会员，这时将无法获得邀请奖励哦。
                    </p>
                </div>
        </div>


        <script>
           /* 调用Share.startShareActivity 跳转到立即推广详情页*/
          /*  var isLogin =  window.Share.isLogin();*/
            function promotePage(){
                if(window.Share.isLogin()){
                    window.Share.startShareActivity();
                }else{
                    window.Share.showDialog();
                }
            }

            function withdrawmoney(){
                if(window.Share.isLogin()){
                    window.location.href="./share/v1.0/withdraw";
                }else {
                    window.Share.showDialog();
                }
            }

            /* 详情标签跳转函数*/
            function details() {
                if(window.Share.isLogin()){
                    window.location.href="./share/v1.0/details";
                }else{
                    window.Share.showDialog();
                }
            }


        </script>

    </body>
</html>