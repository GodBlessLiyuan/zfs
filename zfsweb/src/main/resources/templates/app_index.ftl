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
                                    data-target="#insertModal" data-whatever="@getbootstrap">发布新版本
                            </button>

                            <hr>
                            <div class="basic-form">
                                <form>
                                    <div class="form-row">
                                        <div class="form-group col-md-2">
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
                                    onclick="resetClick()">重置
                            </button>
                            <button type="button" class="btn btn-primary " id="query"
                                    onclick="queryClick()">查询
                            </button>

                            <hr>
                            <div class="table-responsive">
                                <table id="datatab" class="table table-striped table-bordered zero-configuration"
                                       style="table-layout: fixed; text-align: center; vertical-align: middle">
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
                                        <th width="180px">操作</th>
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
                                            <button id="iModalX" type="button" class="close" data-dismiss="modal"
                                                    aria-label="Close" onclick="clearInsModal()">
                                                <span aria-hidden="true">×</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">应用程序<span
                                                                style="color: red">*</span>:</span>
                                                    <input id="iFile" type="file"/>
                                                </div>
                                                <div class="form-group">
                                                    <span for="recipient-name" class="col-form-label">更新方式<span
                                                                style="color: red">*</span>:</span>
                                                    <select id="iUpdateType" class="form-control">
                                                        <option value='1' selected='selected'>普通更新</option>
                                                        <option value='2'>强制更新</option>
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <span for="recipient-name" class="col-form-label">更新渠道<span
                                                                style="color: red">*</span>:</span>
                                                    <button id='insert-select-all'>全选</button>
                                                    <button id='insert-deselect-all'>全不选</button>
                                                    <select id="iSoftChannel" multiple="multiple">
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">更新内容<span
                                                                style="color: red">*</span>:</span>
                                                    <textarea class="form-control" id="iContext"
                                                              maxlength="100"></textarea>
                                                </div>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">备注:</span>
                                                    <textarea class="form-control" id="iExtra"></textarea>
                                                </div>
                                            </form>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-primary" onclick="insertClick()">
                                                确认发布
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
                                            <button id="uModalX" type="button" class="close" data-dismiss="modal"
                                                    aria-label="Close">
                                                <span aria-hidden="true">×</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form>
                                                <div class="form-group">
                                                    <button type="hidden" id="uAppId" style="display:none;"/>
                                                </div>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">应用程序:</span>
                                                    <input id="uFile" type="file"/>
                                                </div>
                                                <div class="form-group">
                                                    <span for="recipient-name" class="col-form-label">更新方式<span
                                                                style="color: red">*</span>:</span>
                                                    <select id="uUpdateType" class="form-control">
                                                        <option value='1'>普通更新</option>
                                                        <option value='2'>强制更新</option>
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <span for="recipient-name" class="col-form-label">更新渠道<span
                                                                style="color: red">*</span>:</span>
                                                    <button id='update-select-all'>全选</button>
                                                    <button id='update-deselect-all'>全不选</button>
                                                    <select id="uSoftChannel" multiple="multiple">
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">更新内容<span
                                                                style="color: red">*</span>:</span>
                                                    <textarea class="form-control" id="uContext"></textarea>
                                                </div>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">备注:</span>
                                                    <textarea class="form-control" id="uExtra"></textarea>
                                                </div>
                                            </form>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-primary" onclick="updateClick()">
                                                确认修改
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <#assign modalId = "publishModal">
                            <#assign moduleTitle = "是否发布新版本？">
                            <#assign moduleClick = "statusClick()">
                            <#include "/freemarker/base/dialog.ftl"/>

                            <#assign modalId = "unPublishModal">
                            <#assign moduleTitle = "是否取消发布此版本？">
                            <#assign moduleClick = "statusClick()">
                            <#include "/freemarker/base/dialog.ftl"/>

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
<script src="./plugins/jquery-multi-select/jquery.multi-select.js"></script>
<script>
    let moduleData = new Map();

    $('#insert-select-all').click(function () {
        $('#iSoftChannel').multiSelect('select_all');
        return false;
    });
    $('#insert-deselect-all').click(function () {
        $('#iSoftChannel').multiSelect('deselect_all');
        return false;
    });
    $('#update-select-all').click(function () {
        $('#uSoftChannel').multiSelect('select_all');
        return false;
    });
    $('#update-deselect-all').click(function () {
        $('#uSoftChannel').multiSelect('deselect_all');
        return false;
    });

    $(document).ready(function () {
        // 自动查询
        queryClick();

        // 下拉框请求后端并赋值
        $.ajax({
            type: 'GET',
            url: 'softchannel/queryAll',
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
            "processing": false,
            "serverSide": true,
            "searching": false, // 禁用全文搜索
            "ordering": false, // 禁用排序
            "ajax": "appversion/query?updateType=" + $('#updateType').val(),
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

                        let dA = full.status === 1 ? "<button data-toggle='modal' data-target='#deleteModal' " +
                            "data-whatever='@getbootstrap' class='btn btn-primary' onclick='deleteModal(" +
                            data + ")'>删除</button>   " : "";
                        let pA = "<button data-toggle='modal' " + modal + " data-whatever='@getbootstrap' " +
                            "class='btn btn-primary' onclick='publishModal(" + data + "," + full.status + ")" +
                            "'>" + title + "</button>  ";
                        let uA = "<button data-toggle='modal' data-target='#updateModal' data-whatever='@getbootstrap' " +
                            "class='btn btn-primary' onclick='updateModal(" + data + ")'>修改</button>";
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

        if ($('#iFile')[0].files[0] === undefined) {
            alert("请选择应用程序！");
        } else if ($('#iSoftChannel').val().length === 0) {
            alert("更新渠道不能为空！");
        } else if (!$('#iContext').val()) {
            alert("更新内容不能为空！");
        } else {
            $('#preloader').fadeTo('fast', 0.4);
            $.ajax({
                type: 'post',
                url: 'appversion/insert',
                dataType: 'json',
                data: reqData,
                contentType: false,
                processData: false,
                success: function (res) {
                    $('#preloader').hide();
                    if (res.status === 1000) {
                        document.getElementById("iModalX").click();
                        $('#datatab').DataTable().draw(false);
                    } else if (res.status === 1103) {
                        alert("上传的文件不是官方渠道包！");
                    } else {
                        alert("服务器内部错误！")
                    }
                }
            });
        }
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

        if ($('#uSoftChannel').val().length === 0) {
            alert("更新渠道不能为空！");
        } else if (!$('#uContext').val()) {
            alert("更新内容不能为空！");
        } else {
            $.ajax({
                type: 'post',
                url: 'appversion/update',
                dataType: 'json',
                data: reqData,
                contentType: false,
                processData: false,
                success: function (res) {
                    document.getElementById("uModalX").click();
                    $('#datatab').DataTable().draw(false);
                }
            });
        }
    }

    function deleteClick() {
        let appId = moduleData.get("id");

        $.ajax({
            type: 'GET',
            url: 'appversion/delete?appId=' + appId,
            dataType: 'json',
            success: function (data) {
                $('#datatab').DataTable().draw(false);
            }
        })
    }

    function statusClick() {
        let appId = moduleData.get("id");
        let status = moduleData.get("status");

        status = status === 1 ? 2 : 1;

        $.ajax({
            type: 'GET',
            url: 'appversion/updateStatus?appId=' + appId + "&status=" + status,
            dataType: 'json',
            success: function (data) {
                $('#datatab').DataTable().draw(false);
            }
        })
    }

    function updateModal(appId) {
        $.ajax({
            type: 'GET',
            url: 'softchannel/queryAll',
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
                    url: 'appversion/queryById?appId=' + appId,
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
        moduleData.set("id", appId);
    }

    /**
     * 发布 & 取消发布界面设置
     * @param appId
     * @param status
     */
    function publishModal(appId, status) {
        moduleData.set("id", appId);
        moduleData.set("status", status);
    }

    /**
     * 重置
     */
    function resetClick() {
        $('#updateType option:first').prop('selected', 'selected');
    }

    /**
     * 清空插入框数据
     */
    function clearInsModal() {
        $('#iFile').val(null);
        $('#iUpdateType option:first').prop('selected', 'selected');
        $('#iSoftChannel').multiSelect('deselect_all');
        $('#iContext').val(null);
        $('#iExtra').val(null);
    }
</script>

</body>

</html>