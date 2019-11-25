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

                            <button type="button" class="btn btn-primary" data-toggle="modal"
                                    data-target="#insertModal" data-whatever="@getbootstrap">新增渠道
                            </button>

                            <hr>
                            <div class="basic-form">
                                <form>
                                    <div class="form-row">
                                        <div class="form-group col-md-2">
                                            <label>渠道名称</label>
                                            <input id="channelName" type="text" class="form-control">
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
                                        <th>渠道名称</th>
                                        <th>添加时间</th>
                                        <th>备注</th>
                                    </tr>
                                    </thead>
                                </table>
                            </div>

                            <div class="modal fade" id="insertModal" tabindex="-1" role="dialog"
                                 aria-labelledby="exampleModalLabel" style="display: none;" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">新增渠道</h5>
                                            <button id="iModalX" type="button" class="close" data-dismiss="modal"
                                                    aria-label="Close" onclick="clearInsModal()">
                                                <span aria-hidden="true">×</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">渠道名称<span
                                                                style="color: red">*</span>:</span>
                                                    <input type="text" class="form-control" id="iChannelName">
                                                </div>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">备注<span
                                                                style="color: red">*</span>:</span>
                                                    <input type="text" class="form-control" id="iExtra">
                                                </div>
                                            </form>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-primary" onclick="insertClick()">
                                                确认添加
                                            </button>
                                        </div>
                                    </div>
                                </div>
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
<script>
    $(document).ready(function () {
        // 自动查询
        queryClick();
    });

    /**
     * 确认上架点击事件
     */
    function insertClick() {
        let channelName = $('#iChannelName').val();
        let extra = $('#iExtra').val();

        if (!channelName) {
            alert("渠道名称不能为空!");
        } else if (!extra) {
            alert("备注不能为空!");
        } else {
            $.ajax({
                type: 'GET',
                url: "softchannel/insert?channelName=" + channelName + "&extra=" + extra,
                dataType: 'json',
                success: function (res) {
                    if (res.code === 0) {
                        document.getElementById("iModalX").click();
                        $('#datatab').DataTable().draw(false);
                    } else {
                        alert(res.msg);
                    }
                }
            });
        }
    }

    /**
     * 查询点击事件
     */
    function queryClick() {

        if ($.fn.dataTable.isDataTable('#datatab')) {
            $('#datatab').DataTable().destroy();
        }

        $('#datatab').DataTable({
            "processing": true,
            "serverSide": true,
            "searching": false, // 禁用全文搜索
            "ordering": false, // 禁用排序
            "ajax": "softchannel/query?channelName=" + $('#channelName').val(),
            "fnDrawCallback": function () {
                this.api().column(0).nodes().each(function (cell, i) {
                    cell.innerHTML = i + 1;
                });
            },
            "columns": [
                {"data": null, "targets": 0},
                {"data": "name"},
                {"data": "createTime"},
                {"data": "extra"}
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
     * 重置
     */
    function resetClick() {
        $('#channelName').val(null);
    }

    /**
     * 清空插入框数据
     */
    function clearInsModal() {
        $('#iChannelName').val(null);
        $('#iExtra').val(null);
    }
</script>

</body>

</html>