<!DOCTYPE html>
<html lang="en">

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
                                    data-target="#insertModal" data-whatever="@getbootstrap">发布新版本
                            </button>

                            <hr>
                            <div class="basic-form">
                                <form>
                                    <div class="form-row">
                                        <div class="form-group col-md-4">
                                            <label>更新方式</label>
                                            <select id="updateType" class="form-control">
                                                <option value='0' selected='selected'>全选</option>
                                                <option value='1'>普通更新</option>
                                                <option value='2'>强制更新</option>
                                            </select>
                                        </div>
                                    </div>
                                </form>
                            </div>

                            <button type="button" class="btn btn-primary " id="reset"
                                    onclick="javascript:resetClick()">重置</button>
                            <button type="button" class="btn btn-primary " id="query"
                                    onclick="javascript:queryClick()">查询
                            </button>

                            <hr>
                            <div class="table-responsive">
                                <table id="datatab" class="table table-striped table-bordered zero-configuration">
                                    <thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>发布时间</th>
                                        <th>发布人</th>
                                        <th>版本号</th>
                                        <th>文件大小</th>
                                        <th>更新方式</th>
                                        <th>更新内容</th>
                                        <th>更新渠道</th>
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
                                            <h5 class="modal-title" id="exampleModalLabel">新增版本</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">×</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">应用:</span>
                                                    <input id="iFile" type="file"/>
                                                </div>
                                                <div class="form-group">
                                                    <span for="recipient-name" class="col-form-label">更新方式:</span>
                                                    <select id="iUpdateType" class="form-control">
                                                        <option value='1' selected='selected'>普通更新</option>
                                                        <option value='2'>强制更新</option>
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
                                            <h5 class="modal-title" id="exampleModalLabel">修改版本</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">×</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form>
                                                <div class="form-group">
                                                    <button type="hidden" id="uAppId" style="display:none;"/>
                                                </div>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">应用:</span>
                                                    <input id="uFile" type="file"/>
                                                </div>
                                                <div class="form-group">
                                                    <span for="recipient-name" class="col-form-label">更新方式:</span>
                                                    <select id="uUpdateType" class="form-control">
                                                        <option value='1'>普通更新</option>
                                                        <option value='2'>强制更新</option>
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
                                            <button type="button" class="btn btn-primary" onclick="updateClick()"
                                                    data-dismiss="modal">确认修改
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal fade" id="deleteModal" style="display: none;" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="form-group">
                                            <button type="hidden" id="dAppId" style="display:none;"/>
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
                                            <button type="hidden" id="pAppId" style="display:none;"/>
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
                                            <button type="hidden" id="upAppId" style="display:none;"/>
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
            url: '/softchannel/queryAll',
            dataType: 'JSON',
            success: function (data) {
                for (let i = 0; i < data.length; i++) {
                    $('#iSoftChannel').multiSelect('addOption', {
                        value: data[i].softChannelId, text: data[i].name,
                        index: i
                    });
                    $('#uSoftChannel').multiSelect('addOption', {
                        value: data[i].softChannelId, text: data[i].name,
                        index: i
                    });
                }
                $('#iSoftChannel').multiSelect("refresh");
                $('#uSoftChannel').multiSelect("refresh");
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

        $('#datatab').DataTable({
            "processing": true,
            "serverSide": true,
            "ajax": "/appversion/query?updateType=" + $('#updateType').val(),
            "fnDrawCallback": function () {
                this.api().column(0).nodes().each(function (cell, i) {
                    cell.innerHTML = i + 1;
                });
            },
            "columns": [
                {"data": null, "targets": 0},
                {"data": "publishTime"},
                {"data": "username"},
                {"data": "versionName"},
                {"data": "size"},
                {"data": "updateType"},
                {"data": "context"},
                {"data": "chanName"},
                {"data": "status"},
                {"data": "extra"},
                {
                    "data": "appId",
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
                    "targets": [5],
                    "render": function (data, type, full) {
                        return data === 1 ? "普通更新" : "强制更新";
                    }
                },
                {
                    "targets": [8],
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
     * 确认上架点击事件
     */
    function insertClick() {
        let reqData = new FormData();
        reqData.append("file", $('#iFile')[0].files[0]);
        reqData.append("updateType", $('#iUpdateType').val());
        reqData.append("softChannel", $('#iSoftChannel').val());
        reqData.append("context", $('#iContext').val());
        reqData.append("extra", $('#iExtra').val());

        $.ajax({
            type: 'post',
            url: '/appversion/insert',
            dataType: 'json',
            data: reqData,
            contentType: false,
            processData: false,
            success: function (res) {
                $('#datatab').DataTable().draw(false);
            }
        });
    }

    /**
     * 确认上架点击事件
     */
    function updateClick() {
        let reqData = new FormData();
        reqData.append("appId", $('#uAppId').val())
        reqData.append("file", $('#uFile')[0].files[0]);
        reqData.append("updateType", $('#uUpdateType').val());
        reqData.append("softChannel", $('#uSoftChannel').val());
        reqData.append("context", $('#uContext').val());
        reqData.append("extra", $('#uExtra').val());

        $.ajax({
            type: 'post',
            url: '/appversion/update',
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
        let appId = $('#dAppId').val();

        $.ajax({
            type: 'GET',
            url: '/appversion/delete?appId=' + appId,
            dataType: 'json',
            success: function (data) {
                $('#datatab').DataTable().draw(false);
            }
        })
    }

    function publishClick() {
        let appId = $('#pAppId').val();

        $.ajax({
            type: 'GET',
            url: '/appversion/updateStatus?appId=' + appId + "&status=2",
            dataType: 'json',
            success: function (data) {
                $('#datatab').DataTable().draw(false);
            }
        })
    }

    function unPublishClick() {
        let appId = $('#upAppId').val();

        $.ajax({
            type: 'GET',
            url: '/appversion/updateStatus?appId=' + appId + "&status=1",
            dataType: 'json',
            success: function (data) {
                $('#datatab').DataTable().draw(false);
            }
        })
    }

    function updateModal(appId) {
        $.ajax({
            type: 'GET',
            url: '/softchannel/queryAll',
            dataType: 'JSON',
            success: function (data) {
                $('#uSoftChannel').empty();
                $('#uUpdateType').find("option[value='1']").attr("selected", false);
                $('#uUpdateType').find("option[value='2']").attr("selected", false);
                for (let i = 0; i < data.length; i++) {
                    $('#uSoftChannel').multiSelect('addOption', {
                        value: data[i].softChannelId, text: data[i].name,
                        index: i
                    });
                }
                $('#uSoftChannel').multiSelect("refresh");

                $.ajax({
                    type: 'GET',
                    url: '/appversion/queryById?appId=' + appId,
                    dataType: 'JSON',
                    success: function (data) {
                        $('#uAppId').val(data.appId);
                        $('#uUpdateType').find("option[value='" + data.updateType + "']").attr("selected", true);
                        $.each(data.chanId.split(','), function (i, chanId) {
                            $('#uSoftChannel').find("option[value='" + chanId + "']").attr("selected", true);
                        });
                        $('#uSoftChannel').multiSelect("refresh");
                        $('#uContext').val(data.context);
                        $('#uExtra').val(data.extra);
                    }
                })
            }
        });
    }

    /**
     * 删除弹框界面设值
     * @param data cmdyId
     */
    function deleteModal(appId) {
        $('#dAppId').val(appId);
    }

    /**
     * 发布 & 取消发布界面设置
     * @param appId
     * @param status
     */
    function publishModal(appId, status) {
        status === 1 ? $('#pAppId').val(appId) : $('#upAppId').val(appId);
    }

    /**
     * 重置
     */
    function resetClick() {
        $('#updateType option:first').prop('selected', 'selected');
    }
</script>

</body>

</html>