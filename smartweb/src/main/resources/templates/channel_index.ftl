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

                            <button type="button" class="btn btn-primary" data-toggle='modal'
                                    data-target='#insertModal' onclick="insertModal()">新增渠道
                            </button>

                            <hr>
                            <div class="basic-form">
                                <form>
                                    <div class="form-row">
                                        <div class="form-group col-md-2">
                                            <label>渠道标识：</label>
                                            <input id="chanNickname" type="text" class="form-control">
                                        </div>
                                        <div class="form-group col-md-2">
                                            <label>负责人：</label>
                                            <select id="proId" class="form-control">
                                                <option value='0' selected='selected'>全部</option>
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
                                <table id="datatab" class="table table-striped table-bordered zero-configuration">
                                    <thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>渠道标识</th>
                                        <th>渠道名称</th>
                                        <th>负责人</th>
                                        <th>联系电话</th>
                                        <th>备注</th>
                                        <th>创建时间</th>
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
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close" id="insert_xModal">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <button type="hidden" id="insert" style="display:none;"/>
                        </div>
                        <div class="form-group">
                            <span for="message-text" class="col-form-label">渠道标识：<span style="color: red"> *</span></span>
                            <input id="insert_chanName" class="form-control" type="text"/>
                        </div>
                        <div class="form-group">
                            <span for="message-text" class="col-form-label">渠道名称：<span style="color: red"> *</span></span>
                            <input id="insert_chanNickname" class="form-control" type="text"/>
                        </div>
                        <div class="form-group">
                            <span for="recipient-name" class="col-form-label">负责人：<span style="color: red"> *</span></span>
                            <select id="insert_proId" class="form-control">
                            </select>
                        </div>
                        <div class="form-group">
                            <span for="recipient-name" class="col-form-label">备注:</span>
                            <input id="insert_extra" class="form-control" type="text"/>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="insertClick()">确定创建</button>
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
     * 页面加载事件：一进入页面就进行一次查询
     */
    window.onload = function () {
        queryClick();
    }


    /**
     * 页面加载事件：往t_channel表中查询所有的负责人
     */
    $(document).ready(function () {
        $.ajax({
            type: 'GET',
            url: '/channel/queryProNames',
            dataType: 'JSON',
            success: function (result) {
                if (result.code === 1008) {
                    alert("登录超时，请重新登录！");
                    window.location.href = '/login';
                } else {
                    for (var i = 0; i < result.data.length; i++) {
                        $('#proId').append("<option value='" + result.data[i].proId + "'>" + result.data[i].proName + "</option>");
                    }
                }
            }
        });
    })



    /**
     * 重置
     */
    function resetClick() {
        $('#chanNickname').val(null);
        $('#proName').val(null);
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
            "ajax": "/channel/query?chanNickname=" + $('#chanNickname').val() + "&proId=" + $('#proId').val(),
            "fnDrawCallback": function () {
                this.api().column(0).nodes().each(function (cell, i) {
                    cell.innerHTML = i + 1;
                });
            },
            "columns": [
                {"data": null, "targets": 0},
                {"data": "chanNickname"},
                {"data": "chanName"},
                {"data": "proName"},
                {"data": "phone"},
                {"data": "extra"},
                {"data": "createTime"},
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
     * 新增：点击新增渠道时，往t_promoter表中查询所有的负责人
     */
    function insertModal() {

        // 先对上次查询的结果进行清空
        $('#insert_proId').empty();

        $.ajax({
            type: 'GET',
            url: '/channel/queryAllProNames',
            dataType: 'JSON',
            success: function (result) {
                if (result.code === 1008) {
                    alert("登录超时，请重新登录！");
                    window.location.href = '/login';
                } else {
                    for (var i = 0; i < result.data.length; i++) {
                        $('#insert_proId').append("<option value='" + result.data[i].proId + "'>" + result.data[i].proName + "</option>");
                    }
                }
            }
        });
    }

    /**
     * 新增：在弹框中点击确定按钮后，将数据发送给后台
     */
    function insertClick() {

        var chanName = $('#insert_chanName').val();
        var chanNickname = $('#insert_chanNickname').val();
        var proId = $('#insert_proId').val();
        var extra = $('#insert_extra').val();

        if (chanName == null || chanName.trim() == "") {
            alert("渠道标识不能为空！")
        }else if (chanNickname == null || chanNickname.trim() == "") {
            alert("渠道名称不能为空！");
        } else {

            $.post("/channel/insert", {chanName:chanName, chanNickname:chanNickname, proId:proId, extra:extra},
                function (result) {
                    if (result.code === 1008) {
                        alert("登录超时，请重新登录！");
                        window.location.href = '/login';
                    }else if (result.code === 0) {
                        alert("新增成功！")
                        $('#datatab').DataTable().draw(false);
                    } else {
                        alert("新增失败！")
                    }
                    document.getElementById("insert_xModal").click();
                }, "json");
        }
    }

</script>

</body>

</html>