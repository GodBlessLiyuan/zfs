<!DOCTYPE html>
<html lang="en">

<head>
    <base href="${basePath}"/>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="icon" type="image/x-icon" href="./images/logo.png"/>
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
                                    data-target="#iModal" data-whatever="@getbootstrap">新增产品
                            </button>

                            <hr>
                            <div class="basic-form">
                                <form>
                                    <div class="form-row">
                                        <div class="form-group col-md-2">
                                            <label>操作人</label>
                                            <input id="username" type="text" class="form-control">
                                        </div>
                                    </div>
                                    <button type="button" class="btn btn-primary m-r-5 m-b-5"
                                            id="reset" onclick="resetClick()">重置
                                    </button>
                                    <button type="button" class="btn btn-primary m-r-5 m-b-5"
                                            onclick="queryClick();"
                                            id="query">查询
                                    </button>
                                </form>
                            </div>

                            <hr>
                            <div class="table-responsive">
                                <table id="datatab" class="table table-striped table-bordered zero-configuration"
                                       style="table-layout: fixed; text-align: center; vertical-align: middle">
                                    <thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>产品类型</th>
                                        <th>产品天数</th>
                                        <th>配置时间</th>
                                        <th>备注</th>
                                        <th>操作人</th>
                                    </tr>
                                    </thead>
                                </table>
                            </div>

                            <div class="modal fade" id="iModal" tabindex="-1" role="dialog"
                                 aria-labelledby="iModalLabel" style="display: none;" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="iModalLabel">新增产品</h5>
                                            <button id="iModalX" type="button" class="close" data-dismiss="modal"
                                                    aria-label="Close" onclick="clearInsModal()">
                                                <span aria-hidden="true">×</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form>
                                                <div class="form-group">
                                                    <span for="recipient-name" class="col-form-label">
                                                        产品类型<span style="color: red">*</span>:
                                                    </span>
                                                    <input type="text" class="form-control" id="iName">
                                                </div>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">
                                                        产品天数<span style="color: red">*</span>:
                                                    </span>
                                                    <input type="number" class="form-control" id="iDays">
                                                </div>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">
                                                        备注信息<span style="color: red">*</span>:
                                                    </span>
                                                    <input type="text" class="form-control" id="iExtra">
                                                </div>
                                            </form>
                                        </div>
                                        <div class="modal-footer">
                                            <button id="confirm" type="button" class="btn btn-primary"
                                                    onclick="insertClick() ">确认上架
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
            "searching": false, // 禁用全文搜索
            "ordering": false, // 禁用排序
            "ajax": "comtype/query?username=" + $('#username').val(),
            "fnDrawCallback": function () {
                this.api().column(0).nodes().each(function (cell, i) {
                    cell.innerHTML = i + 1;
                });
            },
            "columns": [
                {"data": null, "targets": 0},
                {"data": "name"},
                {"data": "days"},
                {"data": "createTime"},
                {"data": "extra"},
                {"data": "username"}
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
     * 确认上架点击事件
     */
    function insertClick() {
        let name = $('#iName').val();
        let days = $('#iDays').val();
        let extra = $('#iExtra').val();
        if (!name) {
            alert("产品类型不能为空！");
        } else if (!days) {
            alert("产品天数不能为空！");
        } else if (!extra) {
            alert("备注信息不能为空！");
        } else {
            $.ajax({
                type: 'GET',
                url: "comtype/insert?name=" + name + "&days=" + days + "&extra=" + extra,
                dataType: 'JSON',
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
     * 重置
     */
    function resetClick() {
        $('#username').val(null);
    }

    /**
     * 清空插入模板数据
     */
    function clearInsModal() {
        $('#iName').val(null);
        $('#iDays').val(null);
        $('#iExtra').val(null);
    }
</script>

</body>

</html>