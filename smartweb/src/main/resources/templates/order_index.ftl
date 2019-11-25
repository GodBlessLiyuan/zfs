<!DOCTYPE html>
<html lang="en">

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
    <!--jedate-->
    <link rel="stylesheet" type="text/css" href="./plugins/jedate-6.5.0/skin/jedate.css"/>

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
                                            <span for="recipient-name" class="col-form-label">支付时间：</span>
                                            <input id="startDate" type="text" class="form-control" placeholder="开始时间"> 至 <input
                                                    id="endDate" type="text" class="form-control" placeholder="结束时间">
                                        </div>
                                        <div class="form-group col-md-2">
                                            <span for="recipient-name" class="col-form-label">产品类型：</span>
                                            <select id="comTypeId" class="form-control">
                                                <option value='0' selected='selected'>全选</option>
                                            </select>
                                        </div>
                                        <div class="form-group col-md-2">
                                            <span for="recipient-name" class="col-form-label">支付方式：</span>
                                            <select id="type" class="form-control">
                                                <option value='0' selected='selected'>全选</option>
                                                <option value='1'>微信</option>
                                                <option value='2'>支付宝</option>
                                            </select>
                                        </div>
                                        <div class="form-group col-md-2">
                                            <span for="recipient-name" class="col-form-label">用户渠道：</span>
                                            <select id="uChanId" class="form-control">
                                                <option value='0' selected='selected'>全选</option>
                                            </select>
                                        </div>
                                        <div class="form-group col-md-2">
                                            <span for="recipient-name" class="col-form-label">销售渠道：</span>
                                            <select id="sChanId" class="form-control">
                                                <option value='0' selected='selected'>全选</option>
                                            </select>
                                        </div>
                                        <div class="form-group col-md-2">
                                            <span>支付账号：</span>
                                            <input id="phone" type="text" class="form-control">
                                        </div>
                                        <div class="form-group col-md-2">
                                            <span>订单编号：</span>
                                            <input id="number" type="text" class="form-control">
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
                                        <th>订单编号</th>
                                        <th>用户渠道</th>
                                        <th>销售渠道</th>
                                        <th>支付方式</th>
                                        <th>购买账号</th>
                                        <th>发起时间</th>
                                        <th>支付时间</th>
                                        <th>产品类型</th>
                                        <th>会员天数</th>
                                        <th>原价</th>
                                        <th>折扣</th>
                                        <th>支付金额</th>
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
<script src="./plugins/jedate-6.5.0/dist/jedate.min.js"></script>

<script>
    $(document).ready(function () {
        // 自动查询
        queryClick();

        // 产品列表
        $.ajax({
            type: 'GET',
            url: 'comtype/queryAll',
            dataType: 'JSON',
            success: function (data) {
                for (let i = 0; i < data.length; i++) {
                    $('#comTypeId').append("<option value='" + data[i].comTypeId + "'>" + data[i].name +
                        "</option>");
                }
            }
        });

        // 渠道信息
        $.ajax({
            type: 'GET',
            url: 'softchannel/queryAll',
            dataType: 'JSON',
            success: function (data) {
                for (let i = 0; i < data.length; i++) {
                    $('#uChanId').append("<option value='" + data[i].softChannelId + "'>" + data[i].name +
                        "</option>");
                    $('#sChanId').append("<option value='" + data[i].softChannelId + "'>" + data[i].name +
                        "</option>");
                }
            }
        })
    });

    /**
     * 查询点击事件
     */
    function queryClick() {

        if ($.fn.dataTable.isDataTable('#datatab')) {
            $('#datatab').DataTable().destroy();
        }

        let startDate = $('#startDate').val();
        let endDate = $('#endDate').val();
        let comTypeId = $('#comTypeId').val();
        let type = $('#type').val();
        let uChanId = $('#uChanId').val();
        let sChanId = $('#sChanId').val();
        let phone = $('#phone').val();
        let number = $('#number').val();

        $('#datatab').DataTable({
            "processing": true,
            "serverSide": true,
            "searching": false, // 禁用全文搜索
            "ordering": false, // 禁用排序
            "ajax": "order/query?startDate=" + startDate + "&endDate=" + endDate + "&comTypeId=" + comTypeId +
                "&type=" + type + "&uChanId=" + uChanId + "&sChanId=" + sChanId + "&phone=" + phone + "&number=" +
                number,
            "fnDrawCallback": function () {
                this.api().column(0).nodes().each(function (cell, i) {
                    cell.innerHTML = i + 1;
                });
            },
            "columns": [
                {"data": null, "targets": 0},
                {"data": "orderNumber"},
                {"data": "userChanName"},
                {"data": "saleChanName"},
                {
                    "data": "type",
                    "render": function (data, type, full) {
                        return data === 1 ? "微信" : "支付宝";
                    }
                },
                {"data": "phone"},
                {"data": "createTime"},
                {"data": "payTime"},
                {"data": "comName"},
                {"data": "days"},
                {"data": "price"},
                {"data": "showDiscount"},
                {"data": "discount"}
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
     * 导出点击事件
     */
    function exportClick() {

    }

    /**
     * 重置
     */
    function resetClick() {
        $('#startDate').val(null);
        $('#endDate').val(null);
        $('#comTypeId option:first').prop('selected', 'selected');
        $('#type option:first').prop('selected', 'selected');
        $('#uChanId option:first').prop('selected', 'selected');
        $('#sChanId option:first').prop('selected', 'selected');
        $('#phone').val(null);
        $('#number').val(null);
    }


    /**
     * 日期控件
     */
    document.getElementById("startDate").addEventListener("focus",function () {
        jeDate(this, {
            theme: {bgcolor: "#f60", color: "#fff", pnColor: "#f60"},   //设置颜色
            format: "YYYY-MM-DD",                                       //设置时间格式
            minDate: "1995-01-01 00:00:00",                             //设置最小日期
            maxDate: "2099-12-31 23:59:59"                              //设置最大日期
        });
    });
    document.getElementById("endDate").addEventListener("focus",function () {
        jeDate(this, {
            theme: {bgcolor: "#f60", color: "#fff", pnColor: "#f60"},   //设置颜色
            format: "YYYY-MM-DD",                                       //设置时间格式
            minDate: "1995-01-01 00:00:00",                             //设置最小日期
            maxDate: "2099-12-31 23:59:59"                              //设置最大日期
        });
    });

</script>

</body>

</html>