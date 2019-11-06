<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">

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


    <link href="./plugins/datatables/jquery.dataTables.min.css">



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

        <div class="row page-titles mx-0">
            <div class="col p-md-0">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="javascript:void(0)">Dashboard</a></li>
                    <li class="breadcrumb-item active"><a href="javascript:void(0)">Home</a></li>
                </ol>
            </div>
        </div>
        <!-- row -->

        <div class="container-fluid">
            <div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="card-body">

                            <button type="button" class="btn btn-primary" data-toggle='modal'
                                    data-target='#insertModal' onclick="insertModal()">新增账号
                            </button>

                            <hr>
                            <div class="basic-form">
                                <form>
                                    <div class="form-row">
                                        <div class="form-group col-md-2">
                                            <label>手机号：</label>
                                            <input id="phone" type="text" class="form-control">
                                        </div>
                                        <div class="form-group col-md-4">
                                            <label>备注信息：</label>
                                            <input id="extra" type="text" class="form-control">
                                        </div>
                                    </div>
                                </form>
                            </div>

                            <button type="button" class="btn btn-primary " id="reset"
                                    onclick="javascript:resetClick()">重置
                            </button>
                            <button type="button" class="btn btn-primary " id="query"
                                    onclick="javascript:queryClick();">查询
                            </button>

                            <hr>
                            <div class="table-responsive">
                                <table id="datatab" class="table table-striped table-bordered zero-configuration">
                                    <thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>账号</th>
                                        <th>姓名</th>
                                        <th>手机号</th>
                                        <th>邮箱</th>
                                        <th>角色</th>
                                        <th>备注信息</th>
                                        <th>操作</th>
                                        <th>操作人</th>
                                    </tr>
                                    </thead>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <!--**********************************
        弹框
    ***********************************-->
    <!--弹框：新增-->
    <div class="modal fade" id="insertModal" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalLabel" style="display: none;" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">新增</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close" id="insert_xModal">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <button type="hidden" id="insert" style="display:none;"/>
                        </div>
                        <div class="form-group">
                            <span for="message-text" class="col-form-label">角色：<span style="color: red"> *</span></span>
                            <select id="insert_roleId" class="form-control">
                            </select>
                        </div>
                        <div class="form-group">
                            <span for="recipient-name" class="col-form-label">账号：<span style="color: red"> *</span></span>
                            <input id="insert_username" class="form-control" type="text"/>
                        </div>
                        <div class="form-group">
                            <span for="recipient-name" class="col-form-label">密码：<span style="color: red"> *</span></span>
                            <input id="insert_password" class="form-control" type="text"/>
                        </div>
                        <div class="form-group">
                            <span for="message-text" class="col-form-label">姓名：<span style="color: red"> *</span></span>
                            <input id="insert_name" class="form-control" type="text">
                        </div>
                        <div class="form-group">
                            <span for="message-text" class="col-form-label">手机号：<span style="color: red"> *</span></span>
                            <input id="insert_phone" class="form-control" type="text"
                                   onblur="insert_checkPhone()">
                        </div>
                        <div class="form-group">
                            <span for="message-text" class="col-form-label">邮箱：<span style="color: red"> *</span></span>
                            <input id="insert_email" class="form-control" type="email">
                        </div>
                        <div class="form-group">
                            <span for="message-text" class="col-form-label">备注:</span>
                            <input id="insert_extra" class="form-control" type="text">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="insertClick()">确定创建</button>
                </div>
            </div>
        </div>
    </div>


    <!--弹框：修改-->
    <div class="modal fade" id="updateModal" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalLabel" style="display: none;" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">修改</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close" id="update_xModal">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <button type="hidden" id="update" style="display:none;"/>
                        </div>
                        <div class="form-group">
                            <span for="message-text" class="col-form-label">角色：<span style="color: red"> *</span></span>
                            <select id="up_roleId" class="form-control">
                            </select>
                        </div>
                        <div class="form-group">
                            <span for="recipient-name" class="col-form-label">账号：<span style="color: red"> *</span></span>
                            <input id="up_username" class="form-control" type="text"/>
                        </div>
                        <div class="form-group">
                            <span for="recipient-name" class="col-form-label">密码：<span style="color: red"> *</span></span>
                            <input id="up_password" class="form-control" type="text"/>
                        </div>
                        <div class="form-group">
                            <span for="recipient-name" class="col-form-label">姓名：<span style="color: red"> *</span></span>
                            <input id="up_name" class="form-control" type="text"/>
                        </div>
                        <div class="form-group">
                            <span for="message-text" class="col-form-label">手机号：<span style="color: red"> *</span></span>
                            <input id="up_phone" class="form-control" type="text"
                                   onblur="up_checkPhone()">
                        </div>
                        <div class="form-group">
                            <span for="message-text" class="col-form-label">邮箱：<span style="color: red"> *</span></span>
                            <input id="up_email" class="form-control" type="email">
                        </div>
                        <div class="form-group">
                            <span for="message-text" class="col-form-label">备注：</span>
                            <input id="up_extra" class="form-control" type="text">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="updateClick()">确认修改</button>
                </div>
            </div>
        </div>
    </div>


    <!--弹框：删除，提示是否真的要删除-->
    <div class="modal fade" id="deleteModal" style="display: none;" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="form-group">
                    <button type="hidden" id="delete" style="display:none;"/>
                </div>
                <div class="modal-header">
                    <h5 class="modal-title">提示</h5>
                    <button type="button" class="close" data-dismiss="modal"><span>×</span> </button>
                </div>
                <div class="modal-body">是否删除此信息？
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="javascript:deleteClick()">确认</button>
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
<script src="plugins/common/common.min.js"></script>
<script src="js/custom.min.js"></script>
<script src="js/settings.js"></script>
<script src="js/gleek.js"></script>
<script src="js/styleSwitcher.js"></script>


<script src="./js/dashboard/dashboard-1.js"></script>

<script src="./plugins/jquery/jquery.min.js"></script>
<script src="./plugins/datatables/js/jquery.dataTables.min.js"></script>



<script>
    /**
     * 新增：点击新增账号时，往t_role表中查询所有的角色
     */
    function insertModal() {

        // 先对上次查询的结果进行清空
        $('#insert_roleId').empty();

        $.ajax({
            type: 'GET',
            url: '/admin/queryAllRoles',
            dataType: 'JSON',
            success: function (result) {
                if (result.code === 1008) {
                    alert("登录超时，请重新登录！");
                    window.location.href = '/login';
                } else {
                    for (var i = 0; i < result.data.length; i++) {
                        $('#insert_roleId').append("<option value='" + result.data[i].roleId + "'>" + result.data[i].roleName + "</option>");
                    }
                }
            }
        });
    }


    /**
     * 新增：在弹框中点击确定按钮后，将数据发送给后台
     */
    function insertClick() {

        var roleId = $('#insert_roleId').val();
        var username = $('#insert_username').val();
        var password = $('#insert_password').val();
        var name = $('#insert_name').val();
        var phone = $('#insert_phone').val();
        var email = $('#insert_email').val();
        var extra = $('#insert_extra').val();

        if (username == null || username.trim() == "") {
            alert("账号不能为空！")
        }else if (password == null || password.trim() == "") {
            alert("密码不能为空！")
        }else if (name == null || name.trim() == "") {
            alert("姓名不能为空！")
        }else if (phone == null || phone.trim() == "") {
            alert("手机号不能为空！")
        }else if (email == null || email.trim() == "") {
            alert("邮箱不能为空！")
        }else{

            $.post("/admin/insert", {roleId:roleId, username:username, password:password, name:name, phone:phone,
                email:email, extra:extra}, function (result) {
                if (result.code === 1008) {
                    alert("登录超时，请重新登录！");
                    window.location.href = '/login';
                }else if (result.code === 0) {
                    alert("新增成功！")
                    $('#datatab').DataTable().draw(false);
                } else {
                    alert("新增失败！")
                }
            }, "json");
            document.getElementById("insert_xModal").click();
        }
    }


    /**
     * 重置
     */
    function resetClick() {
        $('#phone').val(null);
        $('#extra').val(null);
    }

    /**
     * 查询
     */
    function queryClick() {

        if ($.fn.dataTable.isDataTable('#datatab')) {
            $('#datatab').DataTable().destroy();
        }

        $('#datatab').DataTable({
            "processing": true,
            "serverSide": true,
            "ajax": "/admin/query?phone=" + $('#phone').val() + "&extra=" + $('#extra').val(),
            "fnDrawCallback": function () {
                this.api().column(0).nodes().each(function (cell, i) {
                    cell.innerHTML = i + 1;
                });
            },
            "columns": [
                {"data": null, "targets": 0},
                {"data": "username"},
                {"data": "name"},
                {"data": "phone"},
                {"data": "email"},
                {"data": "roleName"},
                {"data": "extra"},
                {
                    "data": "aId",
                    "render": function (data, type, full) {

                        var update_button = "<button type='button' data-toggle='modal' data-target='#updateModal' data-whatever='@getbootstrap' " +
                            "class='btn btn-primary' onclick='javascript:updateModal(" + data + ")'>修改</button>";
                        var delete_button = "<button type='button' data-toggle='modal' data-target='#deleteModal' data-whatever='@getbootstrap' " +
                            "class='btn btn-primary' onclick='javascript:deleteModal(" + data + ")'>删除</button>";

                        return update_button + delete_button;
                    }
                },
                {"data": "operator"}
            ],
            "oLanguage": {
                "sLengthMenu": "每页显示 _MENU_ 条记录",
                "sZeroRecords": "对不起，没有匹配的数据",
                "sInfo": "第 _START_ - _END_ 条 / 共 _TOTAL_ 条数据",
                "sInfoEmpty": "没有匹配的数据",
                "sInfoFiltered": "(数据表中共 _MAX_ 条记录)",
                "sProcessing": "正在加载中...",
                "sSearch": "全文搜索：",
                "oPaginate": {
                    "sFirst": "第一页",
                    "sPrevious": " 上一页 ",
                    "sNext": " 下一页 ",
                    "sLast": " 最后一页 "
                }
            }
        });
    }


    /**
     * 修改：在表格中点击修改按钮后，到后台查询数据，给弹出框填满值
     */
    function updateModal(aId) {

        // 不是取值，而是把要修改数据的ID存放到弹框
        $('#update').val(aId);

        // 往t_role表中查询所有的角色
        $('#up_roleId').empty();
        $.ajax({
            type: 'GET',
            url: '/admin/queryAllRoles',
            dataType: 'JSON',
            success: function (result) {
                if (result.code === 1008) {
                    alert("登录超时，请重新登录！");
                    window.location.href = '/login';
                } else {
                    for (var i = 0; i < result.data.length; i++) {
                        $('#up_roleId').append("<option value='" + result.data[i].roleId + "'>" + result.data[i].roleName + "</option>");
                    }
                }
            }
        });

        $.ajax({
            type: 'GET',
            url: '/admin/queryById?aId=' + aId,
            dataType: 'JSON',
            success: function (result) {
                if (result.code === 1008) {
                    alert("登录超时，请重新登录！");
                    window.location.href = '/login';
                } else {
                    $('#up_roleId').val(result.data.roleId);
                    $('#up_username').val(result.data.username);
                    $('#up_password').val(result.data.password);
                    $('#up_name').val(result.data.name);
                    $('#up_phone').val(result.data.phone);
                    $('#up_email').val(result.data.email);
                    $('#up_extra').val(result.data.extra);
                }
            }
        })
    }


    /**
     * 修改：在弹出框中点击确认修改后，即通过此发送到后台
     */
    function updateClick() {

        var aId = $('#update').val();
        var roleId = $('#up_roleId').val();
        var username = $('#up_username').val();
        var password = $('#up_password').val();
        var name = $('#up_name').val();
        var phone = $('#up_phone').val();
        var email = $('#up_email').val();
        var extra = $('#up_extra').val();

        if (username == null || username.trim() == "") {
            alert("账号不能为空！")
        }else if (password == null || password.trim() == "") {
            alert("密码不能为空！")
        }else if (name == null || name.trim() == "") {
            alert("姓名不能为空！")
        }else if (phone == null || phone.trim() == "") {
            alert("手机号不能为空！")
        }else if (email == null || email.trim() == "") {
            alert("邮箱不能为空！")
        }else{

            $.post("/admin/update", {aId:aId, roleId:roleId, username:username, password:password, name:name,
                phone:phone, email:email, extra:extra}, function (result) {
                if (result.code === 1008) {
                    alert("登录超时，请重新登录！");
                    window.location.href = '/login';
                }else if (result.code === 0) {
                    alert("更新成功！")
                    $('#datatab').DataTable().draw(false);
                } else {
                    alert("更新失败！")
                }
            }, "json");
            document.getElementById("update_xModal").click();
        }
    }

    /**
     * 删除：表格中的删除按钮，作用是将要删除数据的ID传递给提示框
     */
    function deleteModal(aId) {
        //不是取值，而是给弹框赋值
        $('#delete').val(aId);
    }

    /**
     * 删除：真正的删除操作，从弹框中获取要删除数据的ID
     */
    function deleteClick() {
        var aId = $('#delete').val();
        $.post("/admin/delete", {aId:aId}, function (result) {
            if (result.code === 1008) {
                alert("登录超时，请重新登录！");
                window.location.href = '/login';
            }else if (result.code === 0) {
                alert("删除成功！")
                $('#datatab').DataTable().draw(false);
            } else {
                alert("删除失败！")
            }
        }, "json");
    }


    /**
     * 输入框限制：手机号码
     */
    function insert_checkPhone() {
        var phone = document.getElementById('insert_phone').value;
        checkPhone(phone);
    }

    function up_checkPhone() {
        var phone = document.getElementById('up_phone').value;
        checkPhone(phone);
    }

    function checkPhone(phone){
        if(!(/^1[3456789]\d{9}$/.test(phone))){
            alert("手机号码有误，请重填！");
        }
    }
</script>

</body>

</html>