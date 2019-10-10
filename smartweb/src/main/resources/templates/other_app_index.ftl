<!DOCTYPE html>
<html lang="en">

<head>
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

                            <button type="button" class="btn btn-primary" data-toggle="modal"
                                    data-target="#insertModal" data-whatever="@getbootstrap">上架新产品
                            </button>
                            <div class="modal fade" id="insertModal" tabindex="-1" role="dialog"
                                 aria-labelledby="exampleModalLabel" style="display: none;" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">新增产品</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">×</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">应用名称:</span>
                                                    <input type="text" class="form-control" id="iOName">
                                                </div>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">应用简介:</span>
                                                    <input type="text" class="form-control" id="iExtra">
                                                </div>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">应用图标:</span>
                                                    <input id="iIconUrl" type="file"/>
                                                </div>
                                                <div class="form-group">
                                                    <span for="recipient-name" class="col-form-label">下载方式:</span>
                                                    <select id="iDownloadType" class="form-control">
                                                        <option value='1' selected='selected'>直接下载应用文件</option>
                                                        <option value='2'>跳转至应用市场</option>
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">地址:</span>
                                                    <input type="text" class="form-control" id="iAppUrl">
                                                </div>
                                            </form>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-primary" onclick="insertClick()"
                                                    data-dismiss="modal">确认上架
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal fade" id="deleteModal" style="display: none;" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="form-group">
                                            <button type="hidden" id="dOid" style="display:none;"/>
                                        </div>
                                        <div class="modal-header">
                                            <h5 class="modal-title">提示</h5>
                                            <button type="button" class="close" data-dismiss="modal"><span>×</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">是否删除此信息</div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">取消
                                            </button>
                                            <button type="button" class="btn btn-primary" data-dismiss="modal"
                                                    onclick="javascript:deleteClick()">确认删除
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>

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

                            <button type="button" class="btn btn-primary " id="reset">重置</button>
                            <button type="button" class="btn btn-primary " id="query"
                                    onclick="javascript:queryClick();">查询
                            </button>

                            <hr>
                            <div class="table-responsive">
                                <table id="datatab" class="display" style="width:100%">
                                    <thead>
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

        $.ajax({
            type: 'post',
            url: '/otherapp/insert',
            dataType: 'json',
            data: reqData,
            contentType: false,
            processData: false,
            success: function (res) {
                $('#datatab').DataTable().draw(false);
            }
        });
    }

    function deleteClick() {
        let oId = $('#dOid').val();

        $.ajax({
            type: 'GET',
            url: '/otherapp/delete?oId=' + oId,
            dataType: 'json',
            success: function (data) {
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
            "ajax": "/otherapp/query?name=" + $('#name').val(),
            "fnDrawCallback": function () {
                this.api().column(0).nodes().each(function (cell, i) {
                    cell.innerHTML = i + 1;
                });
            },
            "columns": [
                {"data": null, "targets": 0},
                {"data": "oName"},
                {"data": "extra"},
                {"data": "iconUrl"},
                {
                    "data": "downloadType",
                    "render": function (data, type, full) {
                        return data === 1 ? "直接下载应用文件" : "跳转至应用市场";
                    }
                },
                {"data": "appUrl"},
                {
                    "data": "oId",
                    "render": function (data, type, full) {
                        return "<a data-toggle='modal' data-target='#deleteModal' data-whatever='@getbootstrap' " +
                            "class='text-primary' onclick='javascript:deleteModal(" + data + ")'>删除</a>";
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
        $('#dOid').val(oId);
    }

    function uploadFile() {

    }
</script>

</body>

</html>