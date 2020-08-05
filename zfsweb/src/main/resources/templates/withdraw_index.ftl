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

                            <button type="button" class="btn btn-primary" id="export"
                                    onclick="javascript:exportClick();">导出
                            </button>

                            <hr>
                            <div class="basic-form">
                                <form>
                                    <div class="form-row">
                                        <div class="form-group col-md-2">
                                            <label>申请账号：</label>
                                            <input id="phone" type="text" class="form-control">
                                        </div>
                                        <div class="form-group col-md-2">
                                            <label>状态：</label>
                                            <select id="status" class="form-control">
                                                <option value='0' selected='selected'>全部</option>
                                                <option value='1'>待审核</option>
                                                <option value='2'>运营驳回</option>
                                                <option value='3'>打款中</option>
                                                <option value='4'>支付宝驳回</option>
                                                <option value='5'>已完成</option>
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
                                <table id="datatab" class="display" style="width:100%">
                                    <thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>申请时间</th>
                                        <th>申请账号</th>
                                        <th>提款金额</th>
                                        <th>账户余额</th>
                                        <th>支付宝账号</th>
                                        <th>支付宝姓名</th>
                                        <th>提款次数</th>
                                        <th>审核时间</th>
                                        <th>结束状态</th>
                                        <th>状态</th>
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
    <!--弹框：修改状态，提示是否真的要切换状态-->
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
                    <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="javascript:statusClick()">确认</button>
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
     * 导出
     */
    function exportClick() {
    }



    /**
     * 重置
     */
    function resetClick() {
        $('#phone').val(null);
        $('#status option:first').prop('selected', 'selected');
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
            "ajax": "withdraw/query?phone=" + $('#phone').val() + "&status=" + $('#status').val(),
            "fnDrawCallback": function () {
                this.api().column(0).nodes().each(function (cell, i) {
                    cell.innerHTML = i + 1;
                });
            },
            "columns": [
                {"data": null, "targets": 0},
                {"data": "createTime"},
                {"data": "phone"},
                {"data": "withdraw"},
                {"data": "remaining"},
                {"data": "aliAccount"},
                {"data": "aliName"},
                {"data": "withdrawTime"},
                {"data": "auditTime"},
                {"data": "endTime"},
                {
                    "data": "status",
                    "render": function (data, type, full) {
                        var sta;
                        if (data === 1) {
                            sta = "<button type='button'  data-toggle='modal' data-whatever='@getbootstrap' " +
                                "class='btn btn-primary'>待审核</button>";
                        }else if(data === 2){
                            sta = "<button type='button'  data-toggle='modal' data-whatever='@getbootstrap' " +
                                "class='btn btn-primary'>运营驳回</button>";
                        }else if(data === 3) {
                            sta = "<button type='button'  data-toggle='modal' data-whatever='@getbootstrap' " +
                                "class='btn btn-primary'>打款中</button>";
                        }else if(data === 4){
                            sta = "<button type='button'  data-toggle='modal' data-whatever='@getbootstrap' " +
                                "class='btn btn-primary'>支付宝驳回</button>";
                        }else if(data === 5){
                            sta = "<button type='button'  data-toggle='modal' data-whatever='@getbootstrap' " +
                                "class='btn btn-primary'>已完成</button>";
                        }
                        return sta;
                    }
                },
                {
                    "data": "withdrawId",
                    "render": function (data, type, full) {

                        var reject_button = "<button type='button' data-toggle='modal' data-target='#statusModal' data-whatever='@getbootstrap' " +
                            "class='btn btn-primary' onclick='javascript:statusModal(" + data +","+ 2 +")'>驳回</button>";
                        var pass_button = "<button type='button' data-toggle='modal' data-target='#statusModal' data-whatever='@getbootstrap' " +
                            "class='btn btn-primary' onclick='javascript:statusModal(" + data +","+ 5 +")'>通过</button>";

                        if (full.status === 1) {
                            return reject_button + pass_button;
                        }return "";
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
     * 修改状态：将要修改数据的ID、状态码存到提示框里
     */
    function statusModal(withdrawId, status) {
        $('#statusChange_1').val(withdrawId);
        $('#statusChange_2').val(status);

        if (status == 2) {
            document.getElementById("statusChange_3").innerText = "是否驳回此提款请求？"
        }else if (status == 5) {
            document.getElementById("statusChange_3").innerText = "是否同意此提款请求？"
        }
    }

    /**
     * 修改状态：从提示框里取出ID和状态码，发送给后台
     */
    function statusClick() {
        var withdrawId = $('#statusChange_1').val();
        var status = $('#statusChange_2').val();

        $.post("withdraw/update", {withdrawId:withdrawId, status:status}, function (result) {
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