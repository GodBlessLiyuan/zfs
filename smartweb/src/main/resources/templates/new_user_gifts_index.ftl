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
                                    data-target="#insertModal" data-whatever="@getbootstrap">新建
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
                                            <select id="comType" class="form-control">
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
                                    onclick="javascript:queryClick()">查询
                            </button>

                            <hr>
                            <div class="table-responsive">
                                <table id="datatab" class="display" style="width:100%">
                                    <thead>
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
                                                    <span for="message-text" class="col-form-label">产品类型:</span>
                                                    <select id="iComType" class="form-control">
                                                    </select>
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
                            <div class="modal fade" id="deleteModal" style="display: none;" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="form-group">
                                            <button type="hidden" id="dNugId" style="display:none;"/>
                                        </div>
                                        <div class="modal-header">
                                            <h5 class="modal-title">提示</h5>
                                            <button type="button" class="close" data-dismiss="modal"><span>×</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">是否删除此商品</div>
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
        // 下拉框请求后端并赋值
        $.ajax({
            type: 'GET',
            url: '/comtype/queryAll',
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

        $.get("/newusergifts/insert?comTypeId=" + comTypeId);
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
            "ajax": "/newusergifts/query?username=" + $('#username').val() + "&comTypeId=" + $('#comType').val(),
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
                        if (data == 1) {
                            return "<button class='badge badge-dark' type='button' " +
                                "onclick='javascript:statusClick(" + full.nugId + "," + data + ")'>未开启</button>";
                        } else {
                            return "<button class='badge badge-primary' type='button' " +
                                "onclick='javascript:statusClick(" + full.nugId + "," + data + ")'>已开启</button>";
                        }
                    }
                },
                {"data": "createTime"},
                {
                    "data": "nugId",
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
     * 是否上架点击事件
     * @param status 状态
     */
    function statusClick(nugId, status) {
        status = status === 1 ? 2 : 1;
        $.ajax({
            type: 'GET',
            url: '/newusergifts/updateStatus?nugId=' + nugId + '&status=' + status,
            dataType: 'JSON',
            success: function (data) {
                $('#datatab').DataTable().draw(false);
            }
        })
    }

    function deleteClick() {
        let nugId = $('#dNugId').val();

        $.ajax({
            type: 'GET',
            url: '/newusergifts/delete?nugId=' + nugId,
            dataType: 'json',
            success: function (data) {
                $('#datatab').DataTable().draw(false);
            }
        })

    }


    /**
     * 删除弹框界面设值
     * @param data cmdyId
     */
    function deleteModal(nugId) {
        $('#dNugId').val(nugId);
    }

    /**
     * 重置
     */
    function resetClick() {
        $('#username').val(null);
        $('#comType option:first').prop('selected', 'selected');
    }
</script>

</body>

</html>