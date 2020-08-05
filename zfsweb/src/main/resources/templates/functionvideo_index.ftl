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
                                新增
                            </button>

                            <hr>
                            <div class="basic-form">
                                <form>
                                    <div class="form-row">
                                        <div class="form-group col-md-2">
                                            <label>功能名称</label>
                                            <input id="fun_name" type="text" class="form-control">
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
                                        <th>功能名称</th>
                                        <th>视频</th>
                                        <th>备注</th>
                                        <th width="170px">操作</th>
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
                            <span for="message-text" class="col-form-label">功能名称：<span style="color: red"> *</span></span>
                            <input id="insert_name" class="form-control" type="text"/>
                        </div>
                        <div class="form-group">
                            <span for="message-text" class="col-form-label">视频：<span style="color: red"> *</span></span>
                            <input id="insert_video" class="form-control" type="file" accept="video/*"/>
                        </div>
                        <div class="form-group">
                            <span for="recipient-name" class="col-form-label">备注信息：</span>
                            <input id="insert_extra" class="form-control" type="text"/>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="insertClick()">确定上架</button>
                </div>
            </div>
        </div>
    </div>



    <!--弹框：播放视频-->
    <div class="modal fade" id="videoModal" style="display: none;" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-header">
                <h5 class="modal-title">播放视频</h5>
                <button type="button" class="close" data-dismiss="modal"><span>×</span></button>
            </div>
            <div class="modal-content">
                <video src="" id="video"></video>
                <button id="play" type="button" class="btn btn-primary" onclick="playClick()">play</button>
                <button id="pause" type="button" class="btn btn-primary" onclick="pauseClick()">pause</button>
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
                            <span for="message-text" class="col-form-label">功能名称：<span style="color: red"> *</span></span>
                            <input id="up_name" class="form-control" type="text"/>
                        </div>
                        <div class="form-group">
                            <span for="recipient-name" class="col-form-label">视频：<span style="color: red"> *</span></span>
                            <input id="up_video" class="form-control" type="file" accept="video/*"/>
                        </div>
                        <div class="form-group">
                            <span for="recipient-name" class="col-form-label">备注信息:</span>
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
     * 页面加载事件：一进入页面就进行一次查询
     */
    window.onload = function () {
        queryClick();
    }


    /**
     * 新增：在弹框中点击确定按钮后，将数据发送给后台
     */
    var isclick = true;
    function insertClick() {
        if (isclick) {
            isclick = false;

            var reqData = new FormData();
            reqData.append("funName", $('#insert_name').val());
            reqData.append("url", $('#insert_video')[0].files[0]);
            reqData.append("extra", $('#insert_extra').val());

            var funName = $('#insert_name').val();
            var url = $('#insert_video')[0].files[0];

            if (funName == null || funName.trim() == "") {
                alert("功能名称不能为空！")
                isclick = true;
            }else if (url == null) {
                alert("视频不能为空！")
                isclick = true;
            }else {

                $.ajax({
                    type: 'post',
                    url: 'functionvideo/insert',
                    dataType: 'json',
                    data: reqData,
                    contentType: false,
                    processData: false,
                    success: function (result) {
                        if (result.status === 1008) {
                            alert("登录超时，请重新登录！");
                            window.location.href = 'login';
                        }else if (result.status == 1000) {
                            alert("新增成功！");
                            $('#datatab').DataTable().draw(false);
                        } else {
                            alert("新增失败！");
                        }
                        document.getElementById("insert_xModal").click();
                    }
                });
                isclick = true;
            }
        }
    }


    /**
     * 重置
     */
    function resetClick() {
        $('#fun_name').val(null);
    }

    /**
     * 查询
     */
    function queryClick() {

        if ($.fn.dataTable.isDataTable('#datatab')) {
            $('#datatab').DataTable().destroy();
        }

        $('#datatab').DataTable({
            "processing": false,
            "serverSide": true,
            "searching": false, // 禁用全文搜索
            "ajax": "functionvideo/query?name=" + $('#fun_name').val(),
            "fnDrawCallback": function () {
                this.api().column(0).nodes().each(function (cell, i) {
                    cell.innerHTML = i + 1;
                });
            },
            "columns": [
                {"data": null, "targets": 0},
                {"data": "funName"},
                {
                    "data": "url",
                    "render": function (data, type, full) {

                        return  "<button type='button' data-toggle='modal' data-target='#videoModal' data-whatever='@getbootstrap' " +
                            "class='btn btn-primary' onclick='javascript:videoModal(\"" + data + "\")'>查看</button>";
                    }
                },
                {"data": "extra"},
                {
                    "data": "functionId",
                    "render": function (data, type, full) {

                        var update_button = "<button type='button' data-toggle='modal' data-target='#updateModal' data-whatever='@getbootstrap' " +
                            "class='btn btn-primary' onclick='javascript:updateModal(" + data + ")'>修改</button> ";
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
     * 播放：把视频播放地址传递给弹框
     */
    function videoModal(url) {
        document.getElementById("video").src = url;
    }

    /**
     * 播放
     */
    function playClick() {
        document.getElementById('video').play();
    }

    /**
     * 暂停
     */
    function pauseClick() {
        document.getElementById('video').pause();
    }


    /**
     * 修改：在表格中点击修改按钮后，到后台查询数据，给弹出框填满值
     */
    function updateModal(functionId) {

        //不是取值，而是把要修改数据的ID存放到弹框
        $('#update').val(functionId);

        $.ajax({
            type: 'GET',
            url: 'functionvideo/queryById?functionId=' + functionId,
            dataType: 'JSON',
            success: function (result) {
                if (result.status === 1008) {
                    alert("登录超时，请重新登录！");
                    window.location.href = 'login';
                } else {
                    $('#up_name').val(result.data.funName);
                    $('#up_extra').val(result.data.extra);
                }
            }
        })
    }

    /**
     * 修改：在弹出框中点击确认修改后，即通过此发送到后台
     */
    function updateClick() {

        var reqData = new FormData();
        reqData.append("functionId", $('#update').val());
        reqData.append("funName", $('#up_name').val());
        reqData.append("url", $('#up_video')[0].files[0]);
        reqData.append("extra", $('#up_extra').val());

        var funName = $('#up_name').val();
        var url = $('#up_video')[0].files[0];

        if (funName == null || funName.trim() == "") {
            alert("功能名称不能为空！")
        }else if (url == null) {
            alert("视频不能为空！")
        }else {

            $.ajax({
                type: 'post',
                url: 'functionvideo/update',
                dataType: 'json',
                data: reqData,
                contentType: false,
                processData: false,
                success: function (result) {
                    if (result.status === 1008) {
                        alert("登录超时，请重新登录！");
                        window.location.href = 'login';
                    }else if (result.status == 1000) {
                        alert("更新成功！");
                        $('#datatab').DataTable().draw(false);
                    } else {
                        alert("更新失败！");
                    }
                    document.getElementById("update_xModal").click();
                }
            });
        }
    }

    /**
     * 删除：表格中的删除按钮，作用是将要删除数据的ID传递给提示框
     */
    function deleteModal(functionId) {
        //不是取值，而是给弹框赋值
        $('#delete').val(functionId);
    }

    /**
     * 删除：真正的删除操作，从弹框中获取要删除数据的ID
     */
    function deleteClick() {
        var functionId = $('#delete').val();
        $.post("functionvideo/delete", {functionId:functionId}, function (result) {
            if (result.status === 1008) {
                alert("登录超时，请重新登录！");
                window.location.href = 'login';
            }else if (result.status === 1000) {
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