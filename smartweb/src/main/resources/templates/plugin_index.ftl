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
    <link href="./plugins/jquery-multi-select/multi-select.css" rel="stylesheet" type="text/css">

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
                                    data-target="#insertModal" data-whatever="@getbootstrap">发布新插件
                            </button>

                            <hr>
                            <div class="basic-form">
                                <form>
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label>发布人：</label>
                                            <input id="aId" type="text" class="form-control">
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
                                        <th>发布时间</th>
                                        <th>发布人</th>
                                        <th>文件大小</th>
                                        <th>更新内容</th>
                                        <th>版本|渠道</th>
                                        <th>状态</th>
                                        <th>备注</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                </table>
                            </div>

                            <div class="modal fade" id="insertModal" tabindex="-1" role="dialog"
                                 aria-labelledby="exampleModalLabel" style="display: none;" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">新增插件</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">×</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">插件:</span>
                                                    <button id="iUrl" type="button" class="btn btn-primary"
                                                            onclick="javascript:uploadFile()"
                                                            data-dismiss="modal">上传文件
                                                    </button>
                                                </div>
                                                <div class="form-group">
                                                    <span for="recipient-name" class="col-form-label">支持版本:</span>
                                                    <select id="iAppId" class="form-control"
                                                            onclick="javascript:iAppIdClick()">
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <span for="recipient-name" class="col-form-label">更新渠道:</span>
                                                    <select id="iSoftChannel" multiple="multiple">
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">更新内容:</span>
                                                    <input type="text" class="form-control" id="iContext">
                                                </div>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">备注:</span>
                                                    <input type="text" class="form-control" id="iExtra">
                                                </div>
                                            </form>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-primary" onclick="insertClick()"
                                                    data-dismiss="modal">确认发布
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal fade" id="updateModal" tabindex="-1" role="dialog"
                                 aria-labelledby="exampleModalLabel" style="display: none;" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">修改插件</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">×</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form>
                                                <div class="form-group">
                                                    <button type="hidden" id="uPluginId" style="display:none;"/>
                                                </div>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">插件:</span>
                                                    <button id="uUrl" type="button" class="btn btn-primary"
                                                            onclick="javascript:uploadFile()"
                                                            data-dismiss="modal">上传文件
                                                    </button>
                                                </div>
                                                <div class="form-group">
                                                    <span for="recipient-name" class="col-form-label">支持版本:</span>
                                                    <select id="uAppId" class="form-control" onclick="uAppIdClick()">
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <span for="recipient-name" class="col-form-label">更新渠道:</span>
                                                    <select id="uSoftChannel" multiple="multiple">
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">更新内容:</span>
                                                    <input type="text" class="form-control" id="uContext">
                                                </div>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">备注:</span>
                                                    <input type="text" class="form-control" id="uExtra">
                                                </div>
                                            </form>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-primary" onclick="updateClick()">确认修改
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal fade" id="deleteModal" style="display: none;" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="form-group">
                                            <button type="hidden" id="dPluginId" style="display:none;"/>
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
                            <div class="modal fade" id="publishModal" style="display: none;" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="form-group">
                                            <button type="hidden" id="pPluginId" style="display:none;"/>
                                        </div>
                                        <div class="modal-header">
                                            <h5 class="modal-title">提示</h5>
                                            <button type="button" class="close" data-dismiss="modal"><span>×</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">是否发布新版本？</div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">取消
                                            </button>
                                            <button type="button" class="btn btn-primary" data-dismiss="modal"
                                                    onclick="javascript:publishClick()">确认发布
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal fade" id="unPublishModal" style="display: none;" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="form-group">IP
                                            <button type="hidden" id="upPluginId" style="display:none;"/>
                                        </div>
                                        <div class="modal-header">
                                            <h5 class="modal-title">提示</h5>
                                            <button type="button" class="close" data-dismiss="modal"><span>×</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">是否取消发布此版本？</div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">取消
                                            </button>
                                            <button type="button" class="btn btn-primary" data-dismiss="modal"
                                                    onclick="javascript:unPublishClick()">确认取消
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
<script src="./plugins/jquery-multi-select/jquery.multi-select.js"></script>
<script>

    $(document).ready(function () {
        // 下拉框请求后端并赋值
        $.ajax({
            type: 'GET',
            url: '/appversion/queryAll',
            dataType: 'JSON',
            success: function (data) {
                for (let i = 0; i < data.length; i++) {
                    $('#iAppId').append("<option value='" + data[i].appId + "'>" + data[i].versionName +
                        "</option>");
                    $('#uAppId').append("<option value='" + data[i].appId + "'>" + data[i].versionName +
                        "</option>");
                }

                let appId = $('#iAppId').val();
                $.ajax({
                    type: 'GET',
                    url: '/appversion/queryById?appId=' + appId,
                    dataType: 'JSON',
                    success: function (data) {
                        let chanNames = data.chanName.split(',');
                        $.each(data.chanId.split(','), function (i, chanId) {
                            $('#iSoftChannel').multiSelect('addOption', {
                                value: chanId, text: chanNames[i],
                                index: i
                            });
                            $('#uSoftChannel').multiSelect('addOption', {
                                value: chanId, text: chanNames[i],
                                index: i
                            });
                        })
                        $('#iSoftChannel').multiSelect("refresh");
                        $('#uSoftChannel').multiSelect("refresh");
                    }
                })
            }
        })
    });

    /**
     * 新增时，切换版本事件
     */
    function iAppIdClick() {
        let appId = $('#iAppId').val();
        $.ajax({
            type: 'GET',
            url: '/appversion/queryById?appId=' + appId,
            dataType: 'JSON',
            success: function (data) {
                let chanNames = data.chanName.split(',');
                $('#iSoftChannel').empty();
                $.each(data.chanId.split(','), function (i, chanId) {
                    $('#iSoftChannel').multiSelect('addOption', {
                        value: chanId, text: chanNames[i],
                        index: i
                    });
                })
                $('#iSoftChannel').multiSelect("refresh");
            }
        })
    }

    /**
     * 新增时，切换版本事件
     */
    function uAppIdClick() {
        let appId = $('#uAppId').val();
        $.ajax({
            type: 'GET',
            url: '/appversion/queryById?appId=' + appId,
            dataType: 'JSON',
            success: function (data) {
                let chanNames = data.chanName.split(',');
                $('#uSoftChannel').empty();
                $.each(data.chanId.split(','), function (i, chanId) {
                    $('#uSoftChannel').multiSelect('addOption', {
                        value: chanId, text: chanNames[i],
                        index: i
                    });
                })
                $('#uSoftChannel').multiSelect("refresh");

                $.ajax({
                    type: 'GET',
                    url: '/plugin/querySoftChannelByIds?pluginId=' + $('#uPluginId').val() + "&appId=" + appId,
                    dataType: 'JSON',
                    success: function (data) {
                        $.each(data, function(i, id){
                            $('#uSoftChannel').find("option[value='" + id + "']").attr("selected", true);
                        });
                        $('#uSoftChannel').multiSelect("refresh");
                    }
                })
            }
        })
    }

    /**
     * 确认上架点击事件
     */
    function insertClick() {
        let url = $('#iUrl').val();
        let appId = $('#iAppId').val();
        let softChannel = $('#iSoftChannel').val();
        let context = $('#iContext').val();
        let extra = $('#iExtra').val();

        $.get("/plugin/insert?appId=" + appId + "&softChannel=" + softChannel + "&context=" + context +
            "&extra=" + extra + "&url=" + url);
    }

    /**
     * 确认修改点击事件
     */
    function updateClick() {
        let pluginId = $('#uPluginId').val();
        let url = $('#uUrl').val();
        let appId = $('#uAppId').val();
        let softChannel = $('#uSoftChannel').val();
        let context = $('#uContext').val();
        let extra = $('#uExtra').val();

        $.ajax({
            type: 'GET',
            url: "/plugin/update?pluginId=" + pluginId + "&appId=" + appId + "&softChannel=" + softChannel + "&context="
                + context + "&extra=" + extra + "&url=" + url,
            dataType: 'json',
            success: function (data) {
                $('#datatab').DataTable().draw(false);
            }
        })
    }

    function deleteClick() {
        let pluginId = $('#dPluginId').val();

        $.ajax({
            type: 'GET',
            url: '/plugin/delete?pluginId=' + pluginId,
            dataType: 'json',
            success: function (data) {
                $('#datatab').DataTable().draw(false);
            }
        })
    }

    function publishClick() {
        let pluginId = $('#pPluginId').val();

        $.ajax({
            type: 'GET',
            url: '/plugin/updateStatus?pluginId=' + pluginId + "&status=2",
            dataType: 'json',
            success: function (data) {
                $('#datatab').DataTable().draw(false);
            }
        })
    }

    function unPublishClick() {
        let pluginId = $('#upPluginId').val();

        $.ajax({
            type: 'GET',
            url: '/plugin/updateStatus?pluginId=' + pluginId + "&status=1",
            dataType: 'json',
            success: function (data) {
                $('#datatab').DataTable().draw(false);
            }
        })
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
            "ajax": "/plugin/query?aId=" + $('#aId').val(),
            "fnDrawCallback": function () {
                this.api().column(0).nodes().each(function (cell, i) {
                    cell.innerHTML = i + 1;
                });
            },
            "columns": [
                {"data": null, "targets": 0},
                {"data": "publishTime"},
                {"data": "username"},
                {"data": "size"},
                {"data": "context"},
                {"data": "name"},
                {"data": "status"},
                {"data": "extra"},
                {
                    "data": "pluginId",
                    "render": function (data, type, full) {

                        let title = "发布";
                        let modal = "data-target='#publishModal'";

                        if (full.status === 2) {
                            title = "取消发布";
                            modal = "data-target='#unPublishModal'";
                        }

                        let dA = full.status === 1 ? "<a data-toggle='modal' data-target='#deleteModal' " +
                            "data-whatever='@getbootstrap' class='text-primary' onclick='javascript:deleteModal(" +
                            data + ")'>删除</a>   " : "";
                        let pA = "<a data-toggle='modal' " + modal + " data-whatever='@getbootstrap' " +
                            "class='text-primary' onclick='javascript:publishModal(" + data + "," + full.status + ")" +
                            "'>" + title + "</a>  ";
                        let uA = "<a data-toggle='modal' data-target='#updateModal' data-whatever='@getbootstrap' " +
                            "class='text-primary' onclick='javascript:updateModal(" + data + ")'>修改</a>";
                        return dA + pA + uA;

                    }
                }
            ],
            "columnDefs": [
                {
                    "targets": [1],
                    "render": function (data, type, full) {
                        if (data == null || data.trim() == "") {
                            return "";
                        } else {
                            var date = new Date(data);
                            return date.getFullYear() + "/" + date.getMonth() + "/" +
                                date.getDate() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
                        }
                    }
                },
                {
                    "targets": [6],
                    "render": function (data, type, full) {
                        return data === 1 ? "未发布" : "已发布";
                    }
                }
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
     * 更新界面设值
     */
    function updateModal(pluginId) {
        $.ajax({
            type: 'GET',
            url: '/plugin/queryById?pluginId=' + pluginId,
            dataType: 'JSON',
            success: function (data) {
                $('#uPluginId').val(data.pluginId);
                $('#uContext').val(data.context);
                $('#uExtra').val(data.extra);

                // $('#uAppId').empty();
                // $('#uSoftChannel').empty();
                $.each(data.ids.split(","), function(i, ids){
                    ids = ids.split('|');
                    $('#uAppId').find("option[value='" + ids[0] + "']").attr("selected", true);
                    $('#uSoftChannel').find("option[value='" + ids[1] + "']").attr("selected", true);
                });
                $('#uSoftChannel').multiSelect("refresh");

            }
        })
    }

    /**
     * 删除弹框界面设值
     * @param data cmdyId
     */
    function deleteModal(appId) {
        $('#dPluginId').val(appId);
    }

    /**
     * 发布 & 取消发布界面设置
     * @param appId
     * @param status
     */
    function publishModal(pluginId, status) {
        status === 1 ? $('#pPluginId').val(pluginId) : $('#upPluginId').val(pluginId);
    }

    function uploadFile() {

    }
</script>

</body>

</html>