
<!DOCTYPE html>
<html class="h-100" lang="en">

<head>
    <base href="${basePath}"/>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Quixlab - Bootstrap Admin Dashboard Template by Themefisher.com</title>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="../../assets/images/favicon.png">
    <!-- <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous"> -->
    <link href="css/style.css" rel="stylesheet">

    <script src="./plugins/jquery/jquery.min.js"></script>



</head>

<body class="h-100">

<!--*******************
    Preloader start
********************-->
<div id="preloader">
    <div class="loader">
        <svg class="circular" viewBox="25 25 50 50">
            <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="3" stroke-miterlimit="10" />
        </svg>
    </div>
</div>
<!--*******************
    Preloader end
********************-->





<div class="login-form-bg h-100">
    <div class="container h-100">
        <div class="row justify-content-center h-100">
            <div class="col-xl-6">
                <div class="form-input-content">
                    <div class="card login-form mb-0">
                        <div class="card-body pt-5">
                            <a class="text-center" href="index.html"> <h4>砖助智能助手业务后台</h4></a>

                            <form class="mt-8 mb-8 login-input" method="post" action="/entry">
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="Username" name="username">
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control" placeholder="Password" name="password">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="checkcode" placeholder="Checkcode" name="checkcode">
                                    <img id="img" src="/login/get/checkcode" />
                                    <a onclick="javascript:changeImg()" style="color:blue;">看不清？</a>
                                    <span id="msg"></span>
                                </div>

                                <button class="btn login-form__btn submit w-100">Login</button>

                            </form>
                        </div>

        <a data-toggle='modal' data-target='#updateModal' data-whatever='@getbootstrap' " +
        "class='text-primary' onclick='updateModal(" + data + ")'>修改</a>

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
                                <button type="hidden" id="uCmdyId" name="uCmdyId"
                                        style="display:none;"/>
                            </div>
                            <div class="form-group">
                                <span for="recipient-name" class="col-form-label">销售渠道:</span>
                                <label id="uChannelId">xxx</label>
                            </div>
                            <div class="form-group">
                                <span for="message-text" class="col-form-label">产品类型:</span>
                                <label id="uComTypeId">XXX</label>
                            </div>
                            <div class="form-group">
                                <span for="message-text" class="col-form-label">商品名称:</span>
                                <input type="text" class="form-control" id="uComName">
                            </div>
                            <div class="form-group">
                                <span for="message-text" class="col-form-label">商品描述:</span>
                                <input type="text" class="form-control" id="uDescription">
                            </div>
                            <div class="form-group">
                                <span for="message-text" class="col-form-label">原价:</span>
                                <input type="text" class="form-control" id="uPrice">
                            </div>
                            <div class="form-group">
                                <span for="message-text" class="col-form-label">折扣:</span>
                                <input type="text" class="form-control" id="uShowDiscount">
                            </div>
                            <div class="form-group">
                                <span for="message-text" class="col-form-label">售价:</span>
                                <input type="text" class="form-control" id="uDiscount">
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" onclick="updateClick()"
                                data-dismiss="modal"
                        >确认修改
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>




<!--**********************************
    Scripts
***********************************-->

<--!更换验证码内容-->
<script>
    function changeImg(){
        var img = document.getElementById("img");
        img.src = "/login/get/checkcode?date=" + new Date();
    }
</script>


<script src="plugins/common/common.min.js"></script>
<script src="js/custom.min.js"></script>
<script src="js/settings.js"></script>
<script src="js/gleek.js"></script>
<script src="js/styleSwitcher.js"></script>

</body>
</html>





