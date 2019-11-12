<div class="header">
    <div class="header-content clearfix">

        <div class="nav-control">
            <div class="hamburger">
                <span class="toggle-icon"><i class="icon-menu"></i></span>
            </div>
        </div>
        <div class="header-right">
            <ul class="clearfix">
                <li class="icons dropdown d-none d-md-flex">
                    <button type="button" class="btn btn-primary">欢迎您，${username}！</button>
                </li>
                <li class="icons dropdown d-none d-md-flex">
                    <button type="button" class="btn btn-primary" data-toggle='modal' data-target='#logoutModal'>退出</button>
                </li>
                <li class="icons dropdown d-none d-md-flex">
                    <button type="button" class="btn btn-primary" data-toggle='modal' data-target='#updatePwdModal'>修改密码</button>
                </li>
            </ul>
        </div>
    </div>
</div>


<!--**********************************
                    弹框
 ***********************************-->
<!--弹框：修改密码-->
<div class="modal fade" id="updatePwdModal" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" style="display: none;" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">修改密码</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" id="xModal">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <span for="message-text" class="col-form-label">旧密码：<span style="color: red"> *</span></span>
                        <input type="text" class="form-control" id="oldPassword">
                    </div>
                    <div class="form-group">
                        <span for="message-text" class="col-form-label">新密码：<span style="color: red"> *</span></span>
                        <input type="password" class="form-control" id="newPassword">
                    </div>
                    <div class="form-group">
                        <span for="message-text" class="col-form-label">确认新密码：<span style="color: red"> *</span></span>
                        <input type="password" class="form-control" id="confirm">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="updatePassword()">
                    确认修改
                </button>
            </div>
        </div>
    </div>
</div>


<!--弹框：退出登录-->
<div class="modal fade" id="logoutModal" style="display: none;" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="form-group">
                <button type="hidden" id="logout" style="display:none;"/>
            </div>
            <div class="modal-header">
                <h5 class="modal-title">提示</h5>
                <button type="button" class="close" data-dismiss="modal"><span>×</span> </button>
            </div>
            <div class="modal-body">是否退出登录？
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="javascript:logoutClick()">确认</button>
            </div>
        </div>
    </div>
</div>



       <!--**********************************
                    script
        ***********************************-->
<script>
    /**
     * 发送新旧密码到后台
     */
    function updatePassword() {

        var oldPassword = $('#oldPassword').val();
        var newPassword = $('#newPassword').val();
        var confirm = $('#confirm').val();

        if (oldPassword == null || oldPassword.trim() == "") {
            alert("请输入旧密码！")
        } else if (newPassword == null || newPassword.trim() == "") {
            alert("请输入新密码！")
        } else if (newPassword != confirm) {
            alert("输入密码不一致，请重新输入！");
        }else {
            $.post("updatePassword", {oldPassword: oldPassword, newPassword: newPassword}, function (result) {
                if (result.code === 0) {
                    alert("密码修改成功！")
                } else {
                    alert("密码修改失败！")
                }
                document.getElementById("xModal").click();
            }, "json");
        }
    }


    /**
     * 退出登录
     */
    function logoutClick() {
        window.location= "logout";
    }
</script>
