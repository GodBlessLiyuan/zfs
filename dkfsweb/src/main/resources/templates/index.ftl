
<!DOCTYPE html>
<html class="h-100" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="icon" type="image/x-icon" href="./images/logo.png"  />
    <title>智能移动社交营销平台管理系统</title>
    <!-- Favicon icon -->

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
                            <a class="text-center" href="index.html"> <h4>智能移动社交营销平台管理系统</h4></a>
                            <br>
                            <form class="mt-8 mb-8 login-input" method="post" action="entry">
                                <div class="form-group">
                                    <div>
                                        <input type="text" class="form-control" placeholder="Username" name="username" required="required" onfocus="clearUsernameMsg()">
                                    </div>
                                    <div>
                                        <p id="msg_username" style="color: red">${msg_username!""}</p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div>
                                    <input type="password" class="form-control" placeholder="Password" name="password" required="required" onfocus="clearPasswordMsg()">
                                    </div>
                                    <div>
                                        <p id="msg_password" style="color: red">${msg_password!""}</p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div style="float: left">
                                        <input type="text" class="form-control" id="checkcode" placeholder="Checkcode" name="checkcode" required="required" style="float: left" onfocus="clearCheckcodeMsg()">
                                    </div>
                                    <div style="float: right">
                                        <img id="img" src="login/get/checkcode"/>
                                        <a onclick="javascript:changeImg()" style="color:blue">看不清？</a>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <p id="msg_checkcode" class="form-control" style="color: red">${msg_checkcode!""}</p>
                                </div>

                                <button class="btn login-form__btn submit w-100">Login</button>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>




<!--**********************************
    Scripts
***********************************-->
<script>
    /**
     * 更换验证码内容
     */
    function changeImg(){
        var img = document.getElementById("img");
        img.src = "login/get/checkcode?date=" + new Date();
    }

    /**
     * 清除提示语：username
     */
    function clearUsernameMsg() {
        document.getElementById("msg_username").innerText = ""
    }

    /**
     * 清除提示语：password
     */
    function clearPasswordMsg() {
        document.getElementById("msg_password").innerText = ""
    }

    /**
     * 清除提示语：checkcode
     */
    function clearCheckcodeMsg() {
        document.getElementById("msg_checkcode").innerText = ""
    }
</script>


<script src="plugins/common/common.min.js"></script>
<script src="js/custom.min.js"></script>
<script src="js/settings.js"></script>
<script src="js/gleek.js"></script>
<script src="js/styleSwitcher.js"></script>

</body>
</html>





