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


                            <div class="basic-form">
                                <form>
                                    <div class="form-row">
                                        <div class="form-group col-md-2">
                                            <label>手机号</label>
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
                                        <th>手机号</th>
                                        <th>参与时间</th>
                                        <th>截图</th>
                                        <th>产品类型</th>
                                        <th>审核状态</th>
                                        <th width="160px">操作</th>
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
    <!--弹框：查看图片-->
    <div class="modal fade" id="imageModal" style="display: none;" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <img src="" id="image"/>
            </div>
        </div>
    </div>

    <!--弹框：修改状态，提示是否真的要通过/驳回-->
    <div class="modal fade" id="statusModal" style="display: none;" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="form-group">
                    <button type="hidden" id="statusChange_1" style="display:none;"/>
                    <button type="hidden" id="statusChange_2" style="display:none;"/>
                </div>
                <div class="modal-header">
                    <h5 class="modal-title">提示</h5>
                    <button type="button" class="close" data-dismiss="modal"><span>×</span> </button>
                </div>
                <div class="modal-body" id="statusChange_3">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="javascript:passClick()">确认</button>
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
     * 重置
     */
    function resetClick() {
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
            "processing": false,
            "serverSide": true,
            "searching": false, // 禁用全文搜索
            "ajax": "goodcomment/query?phone=" + $('#phone').val(),
            "fnDrawCallback": function () {
                this.api().column(0).nodes().each(function (cell, i) {
                    cell.innerHTML = i + 1;
                });
            },
            "columns": [
                {"data": null, "targets": 0},
                {"data": "phone"},
                {"data": "createTime"},
                {
                    "data": "url",
                    "render": function (data, type, full) {

                        if (null == data) {
                            return "";
                        } else {
                            return  "<button type='button' data-toggle='modal' data-target='#imageModal' data-whatever='@getbootstrap' " +
                                "class='btn btn-primary' onclick='javascript:imageModal(\"" + data + "\")'>查看</button>";
                        }
                    }
                },
                {"data": "comTypeName"},
                {
                    "data": "status",
                    "render": function (data, type, full) {
                        var sta;
                        if (data === 1) {
                            sta = "待审核"
                        }else if (data === 10) {
                            sta = "已通过";
                        }else if (data === 20) {
                            sta = "已驳回";
                        } else {
                            sta = "已领取";
                        }
                        return sta;
                    }
                },
                {
                    "data": "uaid",
                    "render": function (data, type, full) {

                        var pass_button = "<button type='button' data-toggle='modal' data-target='#statusModal' data-whatever='@getbootstrap' " +
                            "class='btn btn-primary' onclick='javascript:statusModal(" + data +","+ 10 + ")'>通过</button> ";
                        var reject_button = "<button type='button' data-toggle='modal' data-target='#statusModal' data-whatever='@getbootstrap' " +
                            "class='btn btn-primary' onclick='javascript:statusModal(" + data +","+ 20 + ")'>驳回</button>";

                        if (full.status == 1) {
                            return pass_button + reject_button;
                        } else {
                            return "";
                        }
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
     * 查看图片：把图片地址传递给弹框
     */
    function imageModal(url) {
        document.getElementById("image").src = url;
    }


    /**
     * 修改状态：将要修改数据的ID存到提示框里
     */
    function statusModal(uAId, status) {
        $('#statusChange_1').val(uAId);
        $('#statusChange_2').val(status);

        var tip = status == 10 ? "确认通过？" : "确认驳回？"
        document.getElementById("statusChange_3").innerText = tip;
    }

    /**
     * 修改状态：从提示框里取出ID和状态码，发送给后台
     */
    function passClick() {
        var uAId = $('#statusChange_1').val();
        var status = $('#statusChange_2').val();

        $.post("goodcomment/update/status", {uAId:uAId, status:status}, function (result) {
            if (result.status === 1008) {
                alert("登录超时，请重新登录！");
                window.location.href = 'login';
            }else if (result.status === 1000) {
                alert("状态修改成功！")
                $('#datatab').DataTable().draw(false);
            } else {
                alert("状态修改失败！")
            }
        }, "json");
    }
</script>

</body>

</html>