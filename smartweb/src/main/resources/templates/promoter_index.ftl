<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">

<head>
    <base href="${basePath}"/>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="icon" type="image/x-icon" href="./images/logo.png"  />
    <title>智能移动社交营销平台管理系统</title>
    <!-- Favicon icon -->

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

        <div class="container-fluid">
            <div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="card-body">

                            <button type="button" class="btn btn-primary" data-toggle='modal' data-target='#insertModal'>
                                新增负责人
                            </button>

                            <hr>
                            <div class="basic-form">
                                <form>
                                    <div class="form-row">
                                        <div class="form-group col-md-2">
                                            <label>负责人：</label>
                                            <input id="proName" type="text" class="form-control">
                                        </div>
                                        <div class="form-group col-md-2">
                                            <label>联系电话：</label>
                                            <input id="phone" type="text" class="form-control">
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
                                   <table id="datatab" class="table table-striped table-bordered zero-configuration"
                                       style="table-layout: fixed; text-align: center; vertical-align: middle"><thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>创建时间</th>
                                        <th>负责人</th>
                                        <th>联系电话</th>
                                        <th>备注</th>
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
                            <span for="message-text" class="col-form-label">负责人：<span style="color: red"> *</span></span>
                            <input id="insert_proName" class="form-control" type="text"/>
                        </div>
                        <div class="form-group">
                            <span for="recipient-name" class="col-form-label">联系电话：<span style="color: red"> *</span></span>
                            <input id="insert_phone" class="form-control" type="text"
                                   onblur="insert_checkPhone()"/>
                        </div>
                        <div class="form-group">
                            <span for="recipient-name" class="col-form-label">备注：<span style="color: red"> *</span></span>
                            <input id="insert_extra" class="form-control" type="text"/>
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
                            <span for="message-text" class="col-form-label">负责人：<span style="color: red"> *</span></span>
                            <input id="up_proName" class="form-control" type="text"/>
                        </div>
                        <div class="form-group">
                            <span for="recipient-name" class="col-form-label">联系电话：<span style="color: red"> *</span></span>
                            <input id="up_phone" class="form-control" type="text"
                                   onblur="up_checkPhone()/>
                        </div>
                        <div class="form-group">
                            <span for="recipient-name" class="col-form-label">备注：<span style="color: red"> *</span></span>
                            <input id="up_extra" class="form-control" type="text"/>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="updateClick()">确认修改</button>
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
     * 页面加载事件：一进入页面就进行一次查询
     */
    window.onload = function () {
        queryClick();
    }



    /**
     * 新增广告：在弹框中点击确定按钮后，将数据发送给后台
     */
    function insertClick() {

        var proName = $('#insert_proName').val();
        var phone = $('#insert_phone').val();
        var extra = $('#insert_extra').val();

        if (proName == null || proName.trim() == "") {
            alert("负责人不能为空！")
        }else if (phone == null || phone.trim() == "") {
            alert("联系电话不能为空！")
        }else if (extra == null || extra.trim() == "") {
            alert("备注不能为空！");
        } else {
            $.post("promoter/insert", {proName:proName, phone:phone, extra:extra}, function (result) {
                if (result.code === 1008) {
                    alert("登录超时，请重新登录！");
                    window.location.href = 'login';
                }else if (result.code === 0) {
                    alert("新增成功！")
                    $('#datatab').DataTable().draw(false);
                } else {
                    alert("新增失败！")
                }
                document.getElementById("insert_xModal").click();
            }, "json");
        }
    }


    /**
     * 重置
     */
    function resetClick() {
        $('#proName').val(null);
        $('#phone').val(null);
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
            "searching": false, // 禁用全文搜索
            "ajax": "promoter/query?proName=" + $('#proName').val() + "&phone=" + $('#phone').val(),
            "fnDrawCallback": function () {
                this.api().column(0).nodes().each(function (cell, i) {
                    cell.innerHTML = i + 1;
                });
            },
            "columns": [
                {"data": null, "targets": 0},
                {"data": "createTime"},
                {"data": "proName"},
                {"data": "phone"},
                {"data": "extra"},
                {
                    "data": "proId",
                    "render": function (data, type, full) {

                        return "<button type='button' data-toggle='modal' data-target='#updateModal' data-whatever='@getbootstrap' " +
                            "class='btn btn-primary' onclick='javascript:updateModal(" + data + ")'>修改</button>";
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
    function updateModal(proId) {

        //不是取值，而是把要修改数据的ID存放到弹框
        $('#update').val(proId);

        $.ajax({
            type: 'GET',
            url: '/adconfig/queryById?proId=' + proId,
            dataType: 'JSON',
            success: function (result) {
                if (result.code === 1008) {
                    alert("登录超时，请重新登录！");
                    window.location.href = 'login';
                } else {
                    $('#up_proName').val(result.data.proName);
                    $('#up_phone').val(result.data.phone);
                    $('#up_extra').val(result.data.extra);
                }
            }
        })
    }

    /**
     * 修改：在弹出框中点击确认修改后，即通过此发送到后台
     */
    function updateClick() {

        var proId = $('#update').val();
        var proName = $('#up_proName').val();
        var phone = $('#up_phone').val();
        var extra = $('#up_extra').val();

        if (proName == null || proName.trim() == "") {
            alert("负责人不能为空！")
        }else if (phone == null || phone.trim() == "") {
            alert("联系电话不能为空！")
        }else if (extra == null || extra.trim() == "") {
            alert("备注不能为空！");
        } else {

            $.post("promoter/update", {proId:proId, proName:proName, phone:phone, extra:extra}, function (result) {
                if (result.code === 1008) {
                    alert("登录超时，请重新登录！");
                    window.location.href = 'login';
                }else if (result.code === 0) {
                    alert("更新成功！")
                    $('#datatab').DataTable().draw(false);
                } else {
                    alert("更新失败！")
                }
                document.getElementById("update_xModal").click();
            }, "json");
        }
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