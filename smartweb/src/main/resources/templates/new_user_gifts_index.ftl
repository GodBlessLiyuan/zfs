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
                                    data-target="#iModal" data-whatever="@getbootstrap">新建
                            </button>

                            <hr>
                            <div class="basic-form">
                                <form>
                                    <div class="form-row">
                                        <div class="form-group col-md-2">
                                            <label>操作人</label>
                                            <input id="username" type="text" class="form-control">
                                        </div>
                                        <div class="form-group col-md-2">
                                            <label>产品类型</label>
                                            <select id="comType" class="form-control">
                                                <option value='0' selected='selected'>全选</option>
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
                                       style="table-layout: fixed; text-align: center; vertical-align: middle"><thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>产品类型</th>
                                        <th>产品天数</th>
                                        <th>状态</th>
                                        <th>创建时间</th>
                                        <th>操作</th>
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
                                            <h5 class="modal-title" id="iModalLabel">新增商品</h5>
                                            <button id="iModalX" type="button" class="close" data-dismiss="modal"
                                                    aria-label="Close" onclick="clearInsModal()">
                                                <span aria-hidden="true">×</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">产品类型:</span>
                                                    <select id="iComType" class="form-control">
                                                    </select>
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

                            <#assign modalId = "openModal">
                            <#assign moduleTitle = "是否开启该商品？">
                            <#assign moduleClick = "statusClick()">
                            <#include "/freemarker/base/dialog.ftl"/>

                            <#assign modalId = "closeModal">
                            <#assign moduleTitle = "是否关闭该商品？">
                            <#assign moduleClick = "statusClick()">
                            <#include "/freemarker/base/dialog.ftl"/>

                            <#assign modalId = "deleteModal">
                            <#assign moduleTitle = "是否删除该商品？">
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

        // 下拉框请求后端并赋值
        $.ajax({
            type: 'GET',
            url: 'comtype/queryAll',
            dataType: 'JSON',
            success: function (data) {
                for (let i = 0; i < data.length; i++) {
                    $('#iComType').append("<option value='" + data[i].comTypeId + "'>" + data[i].name +
                        "</option>");
                    $('#comType').append("<option value='" + data[i].comTypeId + "'>" + data[i].name +
                        "</option>");
                }
            }
        })
    });

    /**
     * 确认上架点击事件
     */
    function insertClick() {
        let comTypeId = $('#iComType').val();

        $.ajax({
            type: 'GET',
            url: "newusergifts/insert?comTypeId=" + comTypeId,
            dataType: 'json',
            success: function (res) {
                if (res.code !== 0) {
                    alert(res.msg);
                } else {
                    document.getElementById("iModalX").click();
                    $('#datatab').DataTable().draw(false);
                }
            }
        });
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
            "ajax": "newusergifts/query?username=" + $('#username').val() + "&comTypeId=" + $('#comType').val(),
            "fnDrawCallback": function () {
                this.api().column(0).nodes().each(function (cell, i) {
                    cell.innerHTML = i + 1;
                });
            },
            "columns": [
                {"data": null, "targets": 0},
                {"data": "comTypeName"},
                {"data": "days"},
                {
                    "data": "status",
                    "render": function (data, type, full) {
                        let title = "未开启";
                        let style = "class='badge badge-dark'";
                        let modalId = "data-target='#openModal'";
                        if (data === 2) {
                            title = "已开启";
                            style = "class='badge badge-primary'";
                            modalId = "data-target='#closeModal'";
                        }
                        return "<button type='button' data-toggle='modal' data-whatever='@getbootstrap' " + modalId +
                            style + "onclick='statusModal(" + full.nugId + "," + data + ")'>" + title +
                            "</button>";
                    }
                },
                {"data": "createTime"},
                {
                    "data": "nugId",
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
     * 是否上架点击事件
     * @param status 状态
     */
    function statusClick() {
        let nugId = moduleData.get("id");
        let status = moduleData.get("status");

        status = status === 1 ? 2 : 1;
        $.ajax({
            type: 'GET',
            url: 'newusergifts/updateStatus?nugId=' + nugId + '&status=' + status,
            dataType: 'JSON',
            success: function (res) {
                if (res.code === 0) {
                    $('#datatab').DataTable().draw(false);
                } else {
                    alert(res.msg);
                }
            }
        })
    }

    function deleteClick() {
        let nugId = moduleData.get("id");

        $.ajax({
            type: 'GET',
            url: 'newusergifts/delete?nugId=' + nugId,
            dataType: 'json',
            success: function (data) {
                $('#datatab').DataTable().draw(false);
            }
        })

    }

    /**
     * 开启/关闭弹框设置
     */
    function statusModal(nugId, status) {
        moduleData.set("id", nugId);
        moduleData.set("status", status);
    }

    /**
     * 删除弹框界面设值
     * @param data cmdyId
     */
    function deleteModal(nugId) {
        moduleData.set("id", nugId);
    }

    /**
     * 重置
     */
    function resetClick() {
        $('#username').val(null);
        $('#comType option:first').prop('selected', 'selected');
    }

    /**
     * 清空插入框数据
     */
    function clearInsModal() {
        $('#iComType option:first').prop('selected', 'selected');
    }
</script>

</body>

</html>