<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">

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

                            <button type="button" class="btn btn-primary" data-toggle='modal' data-target='#insertModal'>
                                新增
                            </button>

                            <hr>
                            <div class="basic-form">
                                <form>
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label>时间</label>
                                            <input id="startTime" type="date" class="form-control"> 至
                                            <input id="endTime" type="date" class="form-control">
                                        </div>
                                        <div class="form-group col-md-4">
                                            <label>状态</label>
                                            <select id="status" class="form-control">
                                                <option value='0' selected='selected'>全选</option>
                                                <option value='1'>关闭</option>
                                                <option value='2'>开启</option>
                                            </select>
                                        </div>
                                        <div class="form-group col-md-4">
                                            <label>通知类型</label>
                                            <select id="notice_type" class="form-control">
                                                <option value='0' selected='selected'>全选</option>
                                                <option value='1'>文本</option>
                                                <option value='2'>图片</option>
                                                <option value='3'>图文</option>
                                            </select>
                                        </div>
                                        <div class="form-group col-md-6">
                                            <label>通知名称</label>
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
                                <table id="datatab" class="table table-striped table-bordered zero-configuration">
                                    <thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>通知类型</th>
                                        <th>通知名称</th>
                                        <th>创建时间</th>
                                        <th>提示时间</th>
                                        <th>开始时间</th>
                                        <th>结束时间</th>
                                        <th>跳转地址</th>
                                        <th>通知详情</th>
                                        <th>状态</th>
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
        弹框
    ***********************************-->
    <!--弹框：新增-->
    <div class="modal fade" id="insertModal" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalLabel" style="display: none;" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">新增</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <button type="hidden" id="insert" style="display:none;"/>
                        </div>
                        <div class="form-group">
                            <span for="message-text" class="col-form-label">通知类型:</span>
                            <label><input name="insert_notice_type" class="form-control" type="radio" value="1"/>文本</label>
                            <label><input name="insert_notice_type" class="form-control" type="radio" value="2"/>图片</label>
                            <label><input name="insert_notice_type" class="form-control" type="radio" value="3"/>图文</label>
                        </div>
                        <div class="form-group">
                            <span for="message-text" class="col-form-label">图片:</span>
                            <input id="insert_pic" class="form-control" type="file"/>
                        </div>
                        <div class="form-group">
                            <span for="recipient-name" class="col-form-label">文字:</span>
                            <input id="insert_text" class="form-control" type="text"/>
                        </div>
                        <div class="form-group">
                            <span for="recipient-name" class="col-form-label">通知名称:</span>
                            <input id="insert_name" class="form-control" type="text"/>
                        </div><div class="form-group">
                            <span for="recipient-name" class="col-form-label">跳转地址:</span>
                            <input id="insert_url" class="form-control" type="text"/>
                        </div><div class="form-group">
                            <span for="recipient-name" class="col-form-label">提示时间:</span>
                            <input id="insert_showtime" class="form-control" type="date"/>
                        </div><div class="form-group">
                            <span for="recipient-name" class="col-form-label">有效时间:</span>
                            <input id="insert_startDate" type="date" class="form-control"> 至
                            <input id="insert_endDate" type="date" class="form-control">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="insertClick()" data-dismiss="modal">确定上架</button>
                </div>
            </div>
        </div>
    </div>


    <!--弹框：修改状态，提示是否真的要切换状态-->
    <div class="modal fade" id="statusModal" style="display: none;" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="form-group">
                    <button type="hidden" id="statusExchange" style="display:none;"/>
                </div>
                <div class="modal-header">
                    <h5 class="modal-title">提示</h5>
                    <button type="button" class="close" data-dismiss="modal"><span>×</span> </button>
                </div>
                <div class="modal-body">是否切换状态？
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="javascript:statusClick()">确认</button>
                </div>
            </div>
        </div>
    </div>


    <!--弹框：删除，提示是否真的要删除-->
    <div class="modal fade" id="deleteModal" style="display: none;" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="form-group">
                    <button type="hidden" id="delete" style="display:none;"/>
                </div>
                <div class="modal-header">
                    <h5 class="modal-title">提示</h5>
                    <button type="button" class="close" data-dismiss="modal"><span>×</span> </button>
                </div>
                <div class="modal-body">是否删除此信息？
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="javascript:deleteClick()">确认</button>
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
     * 新增：在弹框中点击确定按钮后，将数据发送给后台
     */
    function insertClick() {

        var type = $('input[type="radio"][name="notice_type]');
        var picPath = $('#insert_pic').val();
        var picPath = $('#insert_text').val();
        var url = $('#insert_url').val();

        $.post("/bannerconfig/insert", {name:name, picPath:picPath, url:url}, function (result) {
            if (result.code === 0) {
                alert("新增成功！")
                $('#datatab').DataTable().draw(false);
            } else {
                alert("新增失败！")
            }
        }, "json");
    }


    /**
     * 重置
     */
    function resetClick() {
        $('#startTime').val(null);
        $('#endTime').val(null);
        $('#status option:first').prop('selected', 'selected');
        $('#notice_type option:first').prop('selected', 'selected');
        $('#name').val(null);
    }

    /**
     * 查询
     */
    function queryClick() {

        if ($.fn.dataTable.isDataTable('#datatab')) {
            $('#datatab').DataTable().destroy();
        }

        $('#datatab').DataTable({
            "processing": true,
            "serverSide": true,
            "ajax": "/bannerconfig/query?name=" + $('#name').val() + "&status=" + $('#status').val(),
            "fnDrawCallback": function () {
                this.api().column(0).nodes().each(function (cell, i) {
                    cell.innerHTML = i + 1;
                });
            },
            "columns": [
                {"data": null, "targets": 0},
                {"data": "name"},
                {"data": "startTime"},
                {
                    "data": "picPath",
                    "render": function (data, type, full) {
                        return "<img src='" + data + "' height='50px' width='50px'/>";
                    }
                },
                {
                    "data": "url",
                    "render": function (data, type, full) {
                        return  "<a href='"+ data +"'>"+ data +"</a>";
                    }
                },
                {
                    "data": "status",
                    "render": function (data, type, full) {
                        var sta;
                        if (data === 1) {
                            sta = "<button type='button'  data-toggle='modal' data-target='#statusModal' data-whatever='@getbootstrap' " +
                                "class='btn btn-primary' onclick='javascript:statusModal(" + full.bannerId + ")'>关闭</button>";
                        }else{
                            sta = "<button type='button'  data-toggle='modal' data-target='#statusModal' data-whatever='@getbootstrap' " +
                                "class='btn btn-primary' onclick='javascript:statusModal(" + full.bannerId + ")'>开启</button>";
                        }
                        return sta;
                    }
                },
                {
                    "data": "bannerId",
                    "render": function (data, type, full) {

                        var delete_button = "<button type='button' data-toggle='modal' data-target='#deleteModal' data-whatever='@getbootstrap' " +
                            "class='btn btn-primary' onclick='javascript:deleteModal(" + data + ")'>删除</button>";

                        return delete_button;
                    }
                },
                {"data": "operator"}
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
     * 修改状态：将要修改数据的ID存到提示框里
     */
    function statusModal(bannerId) {
        $('#statusExchange').val(bannerId);
    }

    /**
     * 修改状态：从提示框里取出ID，发送给后台
     */
    function statusClick() {
        var bannerId = $('#statusExchange').val();
        $.post("/bannerconfig/update/status", {bannerId:bannerId}, function (result) {
            if (result.code === 0) {
                alert("状态修改成功！")
                $('#datatab').DataTable().draw(false);
            } else {
                alert("状态修改失败！")
            }
        }, "json");
    }

    /**
     * 删除：表格中的删除按钮，作用是将要删除数据的ID传递给提示框
     */
    function deleteModal(bannerId) {
        //不是取值，而是给弹框赋值
        $('#delete').val(bannerId);
    }

    /**
     * 删除：真正的删除操作，从弹框中获取要删除数据的ID
     */
    function deleteClick() {
        var bannerId = $('#delete').val();
        $.post("/bannerconfig/delete", {bannerId:bannerId}, function (result) {
            if (result.code === 0) {
                alert("删除成功！")
                $('#datatab').DataTable().draw(false);
            } else {
                alert("删除失败！")
            }
        }, "json");
    }
</script>

</body>

</html>