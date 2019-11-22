<!DOCTYPE html>
<html lang="en">
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <title> 会员中心 </title>

    <link rel="stylesheet" href="${basePath}/css/style.css">
    <script src="${basePath}/js/generalize.js" type="text/javascript"></script>
    <link rel="stylesheet" href="${basePath}/css/bootstrap.min.css">
    <script src="${basePath}/js/bootstrap.min.js" type="text/javascript"></script>
</head>
    <body >
        <#--<img src="${basePath}/images/member_rights.png" width="100"/>-->
        <!--奖励相关  -->
        <div class="font_reward" style="font-size: 15px;font-weight: bolder;margin-left: 35px;padding-top: 20px">会员规则如下，未勾选表示无权使用：</div>
        <div class="my_state">
            <div class="div_spacing">
                <table class="table table-bordered table-hover table-condensed reward_details" style="width:90%">
                    <thead>
                    <tr>
                        <td style="vertical-align: middle;text-align: center">会员权益</td>
                        <td style="vertical-align: middle;text-align: center">非会员</td>
                        <td style="vertical-align: middle;text-align: center;background-color: #FFF4E0">VIP会员</td>
                    </tr>
                    </thead>
                    <tbody class="table_freemember">
                    <tr>
                        <td style="vertical-align: middle;text-align: center">好友验证</td>
                        <td style="vertical-align: middle; text-align: center"><img
                                    src="${basePath}/images/check_redbag_24.png"/></td>
                        <td style="vertical-align: middle; text-align: center"><img
                                    src="${basePath}/images/check_redbag_24.png"/></td>
                    </tr>
                    <tr>
                        <td style="vertical-align: middle;text-align: center">自助群发</td>
                        <td style="vertical-align: middle; text-align: center"><img
                                    src="${basePath}/images/check_redbag_24.png"/></td>
                        <td style="vertical-align: middle; text-align: center;"><img
                                    src="${basePath}/images/check_redbag_24.png"/></td>
                    </tr>
                    <tr>
                        <td style="vertical-align: middle;text-align: center">一键点赞</td>
                        <td style="vertical-align: middle; text-align: center"><img
                                    src="${basePath}/images/check_redbag_24.png"/></td>
                        <td style="vertical-align: middle; text-align: center"><img
                                    src="${basePath}/images/check_redbag_24.png"/></td>
                    </tr>
                    <tr>
                        <td style="vertical-align: middle;text-align: center">消息确认清理</td>
                        <td style="vertical-align: middle; text-align: center"><img
                                    src="${basePath}/images/check_redbag_24.png"/></td>
                        <td style="vertical-align: middle; text-align: center"><img
                                    src="${basePath}/images/check_redbag_24.png"/></td>
                    </tr>
                    <tr>
                        <td style="vertical-align: middle;text-align: center">通讯录加人</td>
                        <td style="vertical-align: middle; text-align: center"><img
                                    src="${basePath}/images/ico_delete_youshang_24.png"/></td>
                        <td style="vertical-align: middle; text-align: center"><img
                                    src="${basePath}/images/check_redbag_24.png"/></td>
                    </tr>
                    <tr>
                        <td style="vertical-align: middle;text-align: center">群聊加人</td>
                        <td style="vertical-align: middle; text-align: center"><img
                                    src="${basePath}/images/ico_delete_youshang_24.png"/></td>
                        <td style="vertical-align: middle; text-align: center"><img
                                    src="${basePath}/images/check_redbag_24.png"/></td>
                    </tr>
                    <tr>
                        <td style="vertical-align: middle;text-align: center">自动打招呼</td>
                        <td style="vertical-align: middle; text-align: center"><img
                                    src="${basePath}/images/ico_delete_youshang_24.png"/></td>
                        <td style="vertical-align: middle; text-align: center"><img
                                    src="${basePath}/images/check_redbag_24.png"/></td>
                    </tr>
                    <tr>
                        <td style="vertical-align: middle;text-align: center">群发微信群</td>
                        <td style="vertical-align: middle; text-align: center"><img
                                    src="${basePath}/images/ico_delete_youshang_24.png"/></td>
                        <td style="vertical-align: middle; text-align: center"><img
                                    src="${basePath}/images/check_redbag_24.png"/></td>
                    </tr>
                    <tr>
                        <td style="vertical-align: middle;text-align: center">好友群发</td>
                        <td style="vertical-align: middle; text-align: center"><img
                                    src="${basePath}/images/ico_delete_youshang_24.png"/></td>
                        <td style="vertical-align: middle; text-align: center"><img
                                    src="${basePath}/images/check_redbag_24.png"/></td>
                    </tr>
                    <tr>
                        <td style="vertical-align: middle;text-align: center">收藏群发</td>
                        <td style="vertical-align: middle; text-align: center"><img
                                    src="${basePath}/images/ico_delete_youshang_24.png"/></td>
                        <td style="vertical-align: middle; text-align: center"><img
                                    src="${basePath}/images/check_redbag_24.png"/></td>
                    </tr>
                    <tr>
                        <td style="vertical-align: middle;text-align: center">图文转发</td>
                        <td style="vertical-align: middle; text-align: center"><img
                                    src="${basePath}/images/ico_delete_youshang_24.png"/></td>
                        <td style="vertical-align: middle; text-align: center"><img
                                    src="${basePath}/images/check_redbag_24.png"/></td>
                    </tr>
                    <tr>
                        <td style="vertical-align: middle;text-align: center">一键评论</td>
                        <td style="vertical-align: middle; text-align: center"><img
                                    src="${basePath}/images/ico_delete_youshang_24.png"/></td>
                        <td style="vertical-align: middle; text-align: center"><img
                                    src="${basePath}/images/check_redbag_24.png"/></td>
                    </tr>
                    <tr>
                        <td style="vertical-align: middle;text-align: center">朋友圈转发</td>
                        <td style="vertical-align: middle; text-align: center"><img
                                    src="${basePath}/images/ico_delete_youshang_24.png"/></td>
                        <td style="vertical-align: middle; text-align: center"><img
                                    src="${basePath}/images/check_redbag_24.png"/></td>
                    </tr>
                    <tr>
                        <td style="vertical-align: middle;text-align: center">未读消息清理</td>
                        <td style="vertical-align: middle; text-align: center"><img
                                    src="${basePath}/images/ico_delete_youshang_24.png"/></td>
                        <td style="vertical-align: middle; text-align: center"><img
                                    src="${basePath}/images/check_redbag_24.png"/></td>
                    </tr>
                    <tr>
                        <td style="vertical-align: middle;text-align: center">好友清理</td>
                        <td style="vertical-align: middle; text-align: center"><img
                                    src="${basePath}/images/ico_delete_youshang_24.png"/></td>
                        <td style="vertical-align: middle; text-align: center"><img
                                    src="${basePath}/images/check_redbag_24.png"/></td>
                    </tr>
                    <tr>
                        <td style="vertical-align: middle;text-align: center">朋友圈清理</td>
                        <td style="vertical-align: middle; text-align: center"><img
                                    src="${basePath}/images/ico_delete_youshang_24.png"/></td>
                        <td style="vertical-align: middle; text-align: center"><img
                                    src="${basePath}/images/check_redbag_24.png"/></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
