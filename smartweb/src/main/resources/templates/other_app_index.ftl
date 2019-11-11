<!DOCTYPE html>
<html lang="en">

<head>
    <base href="${basePath}"/>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
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
                                    data-target="#insertModal" data-whatever="@getbootstrap">上架新产品
                            </button>

                            <hr>
                            <div class="basic-form">
                                <form>
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label>应用名称</label>
                                            <input id="name" type="text" class="form-control">
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
                                        <th>应用名称</th>
                                        <th>应用简介</th>
                                        <th>应用图标</th>
                                        <th>下载方式</th>
                                        <th>下载地址</th>
                                        <th>操作</th>
                                        <th>操作人</th>
                                    </tr>
                                    </thead>
                                </table>
                            </div>

                            <div class="modal fade" id="insertModal" tabindex="-1" role="dialog"
                                 aria-labelledby="exampleModalLabel" style="display: none;" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">新增产品</h5>
                                            <button id="iModalX" type="button" class="close" data-dismiss="modal"
                                                    aria-label="Close" onclick="clearInsModal()">
                                                <span aria-hidden="true">×</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">应用名称<span
                                                                style="color: red">*</span>:</span>
                                                    <input type="text" class="form-control" id="iOName">
                                                </div>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">应用简介<span
                                                                style="color: red">*</span>:</span>
                                                    <input type="text" class="form-control" id="iExtra">
                                                </div>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">应用图标<span
                                                                style="color: red">*</span>:</span>
                                                    <input id="iIconUrl" type="file"/>
                                                </div>
                                                <div class="form-group">
                                                    <span for="recipient-name" class="col-form-label">下载方式:</span>
                                                    <select id="iDownloadType" class="form-control"
                                                            onchange="downloadTypeClick()">
                                                        <option value='1' selected='selected'>直接下载应用文件</option>
                                                        <option value='2'>跳转至应用市场</option>
                                                    </select>
                                                </div>
                                                <div id="iAppDiv" class="form-group" style="display: none;">
                                                    <span for="message-text" class="col-form-label">应用地址:</span>
                                                    <input type="text" class="form-control" id="iAppUrl">
                                                </div>
                                                <div id="iApkDiv" class="form-group">
                                                    <span for="message-text" class="col-form-label">应用程序<span
                                                                style="color: red">*</span>:</span>
                                                    <input id="iApkUrl" type="file"/>
                                                </div>
                                            </form>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-primary" onclick="insertClick()">
                                                确认上架
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <#assign modalId = "deleteModal">
                            <#assign moduleTitle = "是否删除此信息？">
                            <#assign moduleClick = "deleteClick()">
                            <#include "/freemarker/base/dialog.ftl"/>
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
    let moduleData = new Map();

    $(document).ready(function () {
        // 自动查询
        queryClick();
    });

    /**
     * 确认上架点击事件
     */
    function insertClick() {
        let reqData = new FormData();
        reqData.append("oName", $('#iOName').val());
        reqData.append("extra", $('#iExtra').val());
        reqData.append("iconUrl", $('#iIconUrl')[0].files[0]);
        reqData.append("downloadType", $('#iDownloadType').val());
        reqData.append("appUrl", $('#iAppUrl').val());
        reqData.append("apkUrl", $('#iApkUrl')[0].files[0]);

        if (!$('#iOName').val()) {
            alert("应用名称不能为空！");
        } else if (!$('#iExtra').val()) {
            alert("应用简介不能为空！");
        } else if (reqData.get("iconUrl") === "undefined") {
            alert("请上传应用图标！");
        } else if ($('#iDownloadType').val() === "1" && reqData.get("apkUrl") === "undefined") {
            alert("请上传应用程序！");
        } else {
            $.ajax({
                type: 'post',
                url: 'otherapp/insert',
                dataType: 'json',
                data: reqData,
                contentType: false,
                processData: false,
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

    function deleteClick() {
        let oId = moduleData.get("id");

        $.ajax({
            type: 'GET',
            url: 'otherapp/delete?oId=' + oId,
            dataType: 'json',
            success: function (res) {
                $('#datatab').DataTable().draw(false);
            }
        })

    }

    /**
     * 导出点击事件
     */
    function exportClick() {

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
            "ordering": false, // 禁用排序
            "ajax": "otherapp/query?name=" + $('#name').val(),
            "fnDrawCallback": function () {
                this.api().column(0).nodes().each(function (cell, i) {
                    cell.innerHTML = i + 1;
                });
            },
            "columns": [
                {"data": null, "targets": 0},
                {"data": "name"},
                {"data": "extra"},
                {
                    "data": "iconUrl",
                    "render": function (data, type, full) {
                        return "<img src='" + data + "' height='50px' width='50px'/>";
                    }
                },
                {
                    "data": "downloadType",
                    "render": function (data, type, full) {
                        return data === 1 ? "直接下载应用文件" : "跳转至应用市场";
                    }
                },
                {
                    "data": "appUrl",
                    "render": function (data, type, full) {
                        return "<a class='text-primary' href='" + data + "' download='50px'>" + data+ "</a>";
                    }
                },
                {
                    "data": "id",
                    "render": function (data, type, full) {
                        return "<button data-toggle='modal' data-target='#deleteModal' data-whatever='@getbootstrap' " +
                            "class='btn btn-primary' onclick='deleteModal(" + data + ")'>删除</button>";
                    }
                },
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
     * 删除弹框界面设值
     * @param data cmdyId
     */
    function deleteModal(oId) {
        moduleData.set("id", oId);
    }

    /**
     * 重置
     */
    function resetClick() {
        $('#name').val(null);
    }

    /**
     * 清空插入框数据
     */
    function clearInsModal() {
        $('#iOName').val(null);
        $('#iExtra').val(null);
        $('#iIconUrl').val(null);
        $('#iDownloadType option:first').prop('selected', 'selected');
        $('#iAppUrl').val(null);
        $('#iApkUrl').val(null);
        downloadTypeClick();
    }

    function downloadTypeClick() {
        let type = $('#iDownloadType').val();
        if (type === "1") {
            $('#iAppDiv').hide();
            $('#iApkDiv').show();
        } else {
            $('#iAppDiv').show();
            $('#iApkDiv').hide();
        }
    }
</script>

</body>

</html>