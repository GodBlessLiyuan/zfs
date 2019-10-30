<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <title> 提现记录 </title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
</head>
<body>


<!--顶部信息-->
<div class="container">
  <span class="tx_title" >提现记录</span>
</div>

<!--提现记录详情-->
<div class="container">
    <table class="table table-striped" >
        <tbody>

        <#list res as record>
            <tr>
                <td>
                    <div> <span class="account">${record.account} </span>  <span class="tx_money"> -${record.money}  </span>   </div>

                    <div class="name"> ${record.name} </div>

                    <div> <span class="tx_time">${record.cTime?string('yyyy-MM-dd hh:mm:ss')}</span>  <span class="tx_state"> 审核中</span> </div>
                </td>
            </tr>
        </#list>

        </tbody>
    </table>
</div>

</body>
</html>