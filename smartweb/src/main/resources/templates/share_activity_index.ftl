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

                            <button type="button" class="btn btn-primary" data-toggle='modal'
                                    data-target='#insertModal' onclick="insert_button()">
                                新增
                            </button>

                            <hr>
                            <div class="basic-form">
                                <form>
                                    <div class="form-row">
                                        <div class="form-group col-md-2">
                                            <label>素材类型</label>
                                            <select id="type" class="form-control">
                                                <option value='0' selected='selected'>全部</option>
                                                <option value='1'>文字</option>
                                                <option value='2'>图片</option>
                                            </select>
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
                                        <th>素材类型</th>
                                        <th>内容</th>
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
                            <span for="message-text" class="col-form-label">素材类型：</span>
                            <label><input type="radio" name="insert_type" value="1" checked
                                          onclick="insertTypeClick()"/>文字</label>
                            <label><input type="radio" name="insert_type" value="2"
                                          onclick="insertTypeClick()"/>图片</label>
                        </div>
                        <div class="form-group">
                            <span for="message-text" class="col-form-label">内容：<span style="color: red"> *</span></span>
                            <div id="insert_content_type">
                            </div>
                        </div>
                        <div class="form-group">
                            <span for="recipient-name" class="col-form-label">备注：</span>
                            <input id="insert_extra" class="form-control" type="text"/>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="insertClick()">确定上架
                    </button>
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
                            <span for="message-text" class="col-form-label">素材类型：</span>
                            <label><input type="radio" name="update_type" value="1"
                                          onclick="updateTextClick()"/>文字</label>
                            <label><input type="radio" name="update_type" value="2"
                                          onclick="updateImageClick()"/>图片</label>
                        </div>
                        <div class="form-group">
                            <span for="message-text" class="col-form-label">内容：<span style="color: red"> *</span></span>
                            <div id="update_content_type">
                            </div>
                        </div>
                        <div class="form-group">
                            <span for="recipient-name" class="col-form-label">备注：</span>
                            <input id="update_extra" class="form-control" type="text"/>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="updateClick()">确定上架
                    </button>
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
                    <button type="button" class="close" data-dismiss="modal"><span>×</span></button>
                </div>
                <div class="modal-body">是否删除此信息？
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal"
                            onclick="javascript:deleteClick()">确认
                    </button>
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
     * 新增：点击新增按钮时
     */
    function insert_button() {
        insertTypeClick();
    }

    /**
     * 新增：选好素材类型时，弹出相对应的内容框
     */
    function insertTypeClick() {
        var type = $('input[type="radio"][name="insert_type"]:checked').val();
        var contentType = type == 1 ? "text" : "file";
        document.getElementById("insert_content_type").innerHTML =
            "<input id='insert_content' class='form-control' type='"+ contentType +"'/>"
    }


    /**
     * 新增：在弹框中点击确定按钮后，将数据发送给后台
     */
    function insertClick() {

        var type = $('input[type="radio"][name="insert_type"]:checked').val();
        var reqData = new FormData();
        reqData.append("type", type);
        if (type == 1) {
            reqData.append("contentText", $('#insert_content').val());
            reqData.append("contentImage", null);
        } else {
            reqData.append("contentText", null);
            reqData.append("contentImage", $('#insert_content')[0].files[0]);
        }
        reqData.append("extra", $('#insert_extra').val());

        if (type == 1) {
            var contentText = $('#insert_content').val();
            if (contentText == null || contentText.trim() == "") {
                alert("内容不能为空！");
                return;
            }
        } else if (type == 2) {
            var contentImage = $('#insert_content')[0].files[0];
            if (contentImage == null) {
                alert("内容不能为空！");
                return;
            }
        }
        $.ajax({
            type: 'post',
            url: '/shareactivity/insert',
            dataType: 'json',
            data: reqData,
            contentType: false,
            processData: false,
            success: function (result) {
                if (result.code === 1008) {
                    alert("登录超时，请重新登录！");
                    window.location.href = '/login';
                }else if (result.code == 0) {
                    alert("新增成功！");
                    $('#datatab').DataTable().draw(false);
                } else {
                    alert("新增失败！");
                }
                document.getElementById("insert_xModal").click();
            }
        });
    }


    /**
     * 重置
     */
    function resetClick() {
        $('#type option:first').prop('selected', 'selected');
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
            "ajax": "/shareactivity/query?type=" + $('#type').val(),
            "fnDrawCallback": function () {
                this.api().column(0).nodes().each(function (cell, i) {
                    cell.innerHTML = i + 1;
                });
            },
            "columns": [
                {"data": null, "targets": 0},
                {"data": "type"},
                {
                    "data": "content",
                    "render": function (data, type, full) {
                        if (full.type === 1) {
                            return data;
                        }
                        return "<img src='" + data + "' height='50px' width='50px'/>";
                    }
                },
                {"data": "extra"},
                {
                    "data": "materialId",
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
     * 定义一个全局变量，用于存储修改操作时，从后台查回的内容
     */
    var TYPE = "";
    var CONTENT = "";

    function updateModal(materialId) {

        //不是取值，而是把要修改数据的ID存放到弹框
        $('#update').val(materialId);

        $.ajax({
            type: 'post',
            url: '/shareactivity/queryById?materialId=' + materialId,
            dataType: 'JSON',
            success: function (result) {

                if (result.code === 1008) {
                    alert("登录超时，请重新登录！");
                    window.location.href = '/login';
                } else {

                    TYPE = result.data.type;
                    CONTENT = result.data.content;

                    $('input[type="radio"][name="update_type"][value="' + TYPE + '"]').attr("checked", true)
                    if (TYPE === 1) {
                        document.getElementById("update_content_type").innerHTML = "<input id='update_content' class='form-control' " +
                            "type='text' value='" + CONTENT + "'/>";
                    } else {
                        document.getElementById("update_content_type").innerHTML = "<input id='update_content' class='form-control' type='file'/>" +
                            "<img src='" + CONTENT + "' height='50px' width='50px'/>";
                    }
                    $('#update_extra').val(result.data.extra);

                }
            }
        })
    }


    /**
     * 修改：在修改弹框中选定文字素材类型后，内容栏更换为文本框
     * 并且根据该条数据原本是否为文本类型，来决定是否显示内容
     */
    function updateTextClick() {
        if (TYPE === 1) {
            document.getElementById("update_content_type").innerHTML = "<input id='update_content' class='form-control' " +
                "type='text' value='" + CONTENT + "'/>";
        } else {
            document.getElementById("update_content_type").innerHTML = "<input id='update_content' class='form-control' " +
                "type='text' value=''/>";
        }
    }

    /**
     * 修改：在修改弹框中选定图片素材类型后，内容栏更换为图片框
     * 并且根据该条数据原本是否为图片类型，来决定是否显示内容
     */
    function updateImageClick() {
        var valueData = TYPE === 2 ? "<img src='" + CONTENT + "' height='50px' width='50px'/>" : "";
        document.getElementById("update_content_type").innerHTML = "<input id='update_content' class='form-control' type='file'/>" +
            valueData;
    }


    /**
     * 修改：真正的修改操作，从弹框中获取要修改数据的ID
     */
    function updateClick() {

        var type = $('input[type="radio"][name="update_type"]:checked').val();
        var reqData = new FormData();

        reqData.append("materialId", $('#update').val());
        reqData.append("type", type);
        if (type == 1) {
            reqData.append("contentText", $('#update_content').val());
            reqData.append("contentImage", null);
        } else {
            reqData.append("contentText", null);
            reqData.append("contentImage", $('#update_content')[0].files[0]);
        }
        reqData.append("extra", $('#update_extra').val());

        if (type == 1) {
            var contentText = $('#update_content').val();
            if (contentText == null || contentText.trim() == "") {
                alert("内容不能为空！");
                return;
            }
        } else {
            var contentImage = $('#update_content')[0].files[0];
            if (contentImage == null) {
                alert("内容不能为空！");
                return;
            }
        }
        $.ajax({
            type: 'post',
            url: '/shareactivity/update',
            dataType: 'json',
            data: reqData,
            contentType: false,
            processData: false,
            success: function (result) {
                if (result.code === 1008) {
                    alert("登录超时，请重新登录！");
                    window.location.href = '/login';
                }else if (result.code == 0) {
                    alert("修改成功！");
                    $('#datatab').DataTable().draw(false);
                } else {
                    alert("修改失败！");
                }
                document.getElementById("update_xModal").click();
            }
        });
    }


    /**
     * 删除：表格中的删除按钮，作用是将要删除数据的ID传递给提示框
     */
    function deleteModal(materialId) {
        //不是取值，而是给弹框赋值
        $('#delete').val(materialId);
    }

    /**
     * 删除：真正的删除操作，从弹框中获取要删除数据的ID
     */
    function deleteClick() {
        var materialId = $('#delete').val();
        $.post("/shareactivity/delete", {materialId: materialId}, function (result) {
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
</script>

</body>

</html>