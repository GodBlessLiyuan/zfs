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
                                    data-target="#insertModal" data-whatever="@getbootstrap">新增商品
                            </button>
                            <button type="button" class="btn btn-primary" id="export"
                                    onclick="javascript:exportClick();">导出
                            </button>

                            <hr>
                            <div class="basic-form">
                                <form>
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label>操作人</label>
                                            <input id="username" type="text" class="form-control">
                                        </div>
                                        <div class="form-group col-md-4">
                                            <label>产品类型</label>
                                            <select id="comTypeId" class="form-control">
                                                <option value='0' selected='selected'>全选</option>
                                            </select>
                                        </div>
                                        <div class="form-group col-md-4">
                                            <label>销售渠道</label>
                                            <select id="channelId" class="form-control">
                                                <option value='0' selected='selected'>全选</option>
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
                                        <th>销售渠道</th>
                                        <th>产品类型</th>
                                        <th>会员天数</th>
                                        <th>商品名称</th>
                                        <th>商品描述</th>
                                        <th>原价</th>
                                        <th>折扣</th>
                                        <th>售价</th>
                                        <th>是否上架</th>
                                        <th>是否置顶</th>
                                        <th>创建时间</th>
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
                                            <h5 class="modal-title" id="exampleModalLabel">新增商品</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">×</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form>
                                                <div class="form-group">
                                                    <span for="recipient-name" class="col-form-label">销售渠道:</span>
                                                    <select id="iChannelId" class="form-control">
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">产品类型:</span>
                                                    <select id="iComTypeId" class="form-control">
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">商品名称:</span>
                                                    <input type="text" class="form-control" id="comName">
                                                </div>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">商品描述:</span>
                                                    <input type="text" class="form-control" id="description">
                                                </div>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">原价:</span>
                                                    <input type="text" class="form-control" id="price">
                                                </div>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">折扣:</span>
                                                    <input type="text" class="form-control" id="showDiscount">
                                                </div>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">售价:</span>
                                                    <input type="text" class="form-control" id="discount">
                                                </div>
                                            </form>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-primary" onclick="insertClick()"
                                                    data-dismiss="modal"
                                            >确认上架
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
                                            <h5 class="modal-title" id="exampleModalLabel">修改商品</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">×</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form>
                                                <div class="form-group">
                                                    <button type="hidden" id="uCmdyId" name="uCmdyId"
                                                            style="display:none;"/>
                                                </div>
                                                <div class="form-group">
                                                    <span for="recipient-name" class="col-form-label">销售渠道:</span>
                                                    <label id="uChannelId">xxx</label>
                                                </div>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">产品类型:</span>
                                                    <label id="uComTypeId">XXX</label>
                                                </div>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">商品名称:</span>
                                                    <input type="text" class="form-control" id="uComName">
                                                </div>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">商品描述:</span>
                                                    <input type="text" class="form-control" id="uDescription">
                                                </div>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">原价:</span>
                                                    <input type="text" class="form-control" id="uPrice">
                                                </div>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">折扣:</span>
                                                    <input type="text" class="form-control" id="uShowDiscount">
                                                </div>
                                                <div class="form-group">
                                                    <span for="message-text" class="col-form-label">售价:</span>
                                                    <input type="text" class="form-control" id="uDiscount">
                                                </div>
                                            </form>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-primary" onclick="updateClick()"
                                                    data-dismiss="modal"
                                            >确认修改
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <#assign modalId = "topModal">
                            <#assign moduleTitle = "是否置顶该商品？">
                            <#assign moduleClick = "statusClick()">
                            <#include "/freemarker/base/dialog.ftl"/>

                            <#assign modalId = "downModal">
                            <#assign moduleTitle = "是否取消置顶该商品？">
                            <#assign moduleClick = "statusClick()">
                            <#include "/freemarker/base/dialog.ftl"/>

                            <#assign modalId = "shelfModal">
                            <#assign moduleTitle = "是否上架该商品？">
                            <#assign moduleClick = "statusClick()">
                            <#include "/freemarker/base/dialog.ftl"/>

                            <#assign modalId = "obtainModal">
                            <#assign moduleTitle = "是否下架该商品？">
                            <#assign moduleClick = "statusClick()">
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
        // 产品列表
        $.ajax({
            type: 'GET',
            url: '/comtype/queryAll',
            dataType: 'JSON',
            success: function (data) {
                for (let i = 0; i < data.length; i++) {
                    $('#iComTypeId').append("<option value='" + data[i].comTypeId + "'>" + data[i].name +
                        "</option>");
                    $('#comTypeId').append("<option value='" + data[i].comTypeId + "'>" + data[i].name +
                        "</option>");
                }
            }
        });

        // 渠道信息
        $.ajax({
            type: 'GET',
            url: '/softchannel/queryAll',
            dataType: 'JSON',
            success: function (data) {
                for (let i = 0; i < data.length; i++) {
                    $('#iChannelId').append("<option value='" + data[i].softChannelId + "'>" + data[i].name +
                        "</option>");
                    $('#channelId').append("<option value='" + data[i].softChannelId + "'>" + data[i].name +
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

        $('#datatab').DataTable({
            "processing": true,
            "serverSide": true,
            "ajax": "/vipcommodity/query?username=" + $('#username').val() + "&comTypeId=" + $('#comTypeId').val() +
                "&channelId=" + $('#channelId').val(),
            "fnDrawCallback": function () {
                this.api().column(0).nodes().each(function (cell, i) {
                    cell.innerHTML = i + 1;
                });
            },
            "columns": [
                {"data": null, "targets": 0},
                {"data": "name"},
                {"data": "comTypeName"},
                {"data": "days"},
                {"data": "comName"},
                {"data": "description"},
                {"data": "price"},
                {"data": "showDiscount"},
                {"data": "discount"},
                {
                    "data": "status",
                    "render": function (data, type, full) {
                        let title = "未上架";
                        let style = "class='badge badge-dark'";
                        let modalId = "data-target='#shelfModal'";
                        if (data === 2) {
                            title = "已上架";
                            style = "class='badge badge-primary'";
                            modalId = "data-target='#obtainModal'";
                        }

                        return "<button type='button' data-toggle='modal' data-whatever='@getbootstrap' " + modalId +
                            style + " onclick='statusModal(" + full.cmdyId + "," + data + ", 1)'>" +
                            title + "</button>";
                    }
                },
                {
                    "data": "istop",
                    "render": function (data, type, full) {
                        let title = "未置顶";
                        let style = "class='badge badge-dark'";
                        let modalId = "data-target='#topModal'";
                        if (data === 2) {
                            title = "已置顶";
                            style = "class='badge badge-primary'";
                            modalId = "data-target='#downModal'";
                        }
                        return "<button type='button' data-toggle='modal' data-whatever='@getbootstrap' " + modalId +
                            style + "onclick='statusModal(" + full.cmdyId + "," + data + ", 2)'>" + title +
                            "</button>";
                    }
                },
                {"data": "createTime"},
                {
                    "data": "cmdyId",
                    "render": function (data, type, full) {
                        return "<a data-toggle='modal' data-target='#updateModal' data-whatever='@getbootstrap' " +
                            "class='text-primary' onclick='updateModal(" + data + ")'>修改</a>";
                    }
                },
                {"data": "username"}
            ],
            "columnDefs": [
                {
                    "targets": [11],
                    "render": function (data, type, full) {
                        if (data == null || data.trim() == "") {
                            return "";
                        } else {
                            var date = new Date(data);
                            return date.getFullYear() + "/" + date.getMonth() + "/" +
                                date.getDate() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
                        }
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
        let channelId = $('#iChannelId').val();
        let comTypeId = $('#iComTypeId').val();
        let comName = $('#comName').val();
        let description = $('#description').val();
        let price = $('#price').val();
        let showDiscount = $('#showDiscount').val();
        let discount = $('#discount').val();

        $.get("/vipcommodity/insert?channelId=" + channelId + "&comTypeId=" + comTypeId + "&comName=" + comName +
            "&description=" + description + "&price=" + price + "&showDiscount=" + showDiscount + "&discount=" +
            discount);
    }

    function updateClick() {
        let cmdyId = $('#uCmdyId').val();
        let comName = $('#uComName').val();
        let description = $('#uDescription').val();
        let price = $('#uPrice').val();
        let showDiscount = $('#uShowDiscount').val();
        let discount = $('#uDiscount').val();

        $.ajax({
            type: 'GET',
            url: '/vipcommodity/update?cmdyId=' + cmdyId + "&comName=" + comName + "&description=" + description +
                "&price=" + price + "&showDiscount=" + showDiscount + "&discount=" + discount,
            dataType: 'json',
            success: function (data) {
                $('#datatab').DataTable().draw(false);
            }
        })

    }

    /**
     * 更新弹框界面设值
     * @param data cmdyId
     */
    function updateModal(cmdyId) {
        $.ajax({
            type: 'GET',
            url: '/vipcommodity/queryById?cmdyId=' + cmdyId,
            dataType: 'JSON',
            success: function (data) {
                $('#uCmdyId').val(data.cmdyId);
                $('#uChannelId').text(data.name);
                $('#uComTypeId').text(data.comTypeName);
                $('#uComName').val(data.comName);
                $('#uDescription').val(data.description);
                $('#uPrice').val(data.price);
                $('#uShowDiscount').val(data.showDiscount);
                $('#uDiscount').val(data.discount);
            }
        })
    }

    function statusModal(cmdyId, status, type) {
        moduleData.set("id", cmdyId);
        moduleData.set("status", status);
        moduleData.set("type", type);
    }

    /**
     * 是否上架/置顶点击事件
     * @param status 状态
     */
    function statusClick() {
        let cmdyId = moduleData.get("id");
        let status = moduleData.get("status");
        let type = moduleData.get("type");

        status = status === 1 ? 2 : 1;
        type = type === 2 ? "updateIsTop?isTop=" : "updateStatus?status=";

        $.ajax({
            type: 'GET',
            url: '/vipcommodity/' + type + status + '&cmdyId=' + cmdyId,
            dataType: 'JSON',
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
     * 重置
     */
    function resetClick() {
        $('#username').val(null);
        $('#comTypeId option:first').prop('selected', 'selected');
        $('#channelId option:first').prop('selected', 'selected');
    }
</script>

</body>

</html>