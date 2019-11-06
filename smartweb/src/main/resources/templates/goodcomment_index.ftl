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


                            <div class="basic-form">
                                <form>
                                    <div class="form-row">
                                        <div class="form-group col-md-2">
                                            <label>手机号</label>
                                            <input id="phone" type="text" class="form-control">
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
                                        <th>手机号</th>
                                        <th>参与时间</th>
                                        <th>截图</th>
                                        <th>产品类型</th>
                                        <th>审核状态</th>
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
    <!--弹框：查看图片-->
    <div class="modal fade" id="imageModal" style="display: none;" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="form-group">
                    <img src="" id="image" height='50px' width='50px'/>
                </div>
            </div>
        </div>
    </div>

    <!--弹框：修改状态，提示是否真的要通过-->
    <div class="modal fade" id="passModal" style="display: none;" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="form-group">
                    <button type="hidden" id="pass" style="display:none;"/>
                </div>
                <div class="modal-header">
                    <h5 class="modal-title">提示</h5>
                    <button type="button" class="close" data-dismiss="modal"><span>×</span> </button>
                </div>
                <div class="modal-body">是否确认通过审核，赠送奖励？
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="javascript:passClick()">确认</button>
                </div>
            </div>
        </div>
    </div>


    <!--弹框：修改状态，提示是否真的要驳回-->
    <div class="modal fade" id="rejectModal" style="display: none;" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="form-group">
                    <button type="hidden" id="reject" style="display:none;"/>
                </div>
                <div class="modal-header">
                    <h5 class="modal-title">提示</h5>
                    <button type="button" class="close" data-dismiss="modal"><span>×</span> </button>
                </div>
                <div class="modal-body">是否确认驳回？
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="javascript:rejectClick()">确认</button>
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
     * 重置
     */
    function resetClick() {
        $('#phone').val(null);
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
            "ajax": "/goodcomment/query?phone=" + $('#phone').val(),
            "fnDrawCallback": function () {
                this.api().column(0).nodes().each(function (cell, i) {
                    cell.innerHTML = i + 1;
                });
            },
            "columns": [
                {"data": null, "targets": 0},
                {"data": "phone"},
                {"data": "createTime"},
                {
                    "data": "url",
                    "render": function (data, type, full) {

                        return  "<button type='button' data-toggle='modal' data-target='#imageModal' data-whatever='@getbootstrap' " +
                            "class='btn btn-primary' onclick='javascript:imageModal(" + data + ")'>查看</button>";
                    }
                },
                {"data": "comTypeName"},
                {
                    "data": "status",
                    "render": function (data, type, full) {
                        var sta;
                        if (data === 1) {
                            sta = "待审核"
                        }else if (data === 2) {
                            sta = "已通过";
                        } else {
                            sta = "已驳回"
                        }
                        return "<p  data-whatever='@getbootstrap'>" + sta + "</p>";
                    }
                },
                {
                    "data": "uAId",
                    "render": function (data, type, full) {

                        var pass_button = "<button type='button' data-toggle='modal' data-target='#passModal' data-whatever='@getbootstrap' " +
                            "class='btn btn-primary' onclick='javascript:passModal(" + data + ")'>通过</button>";
                        var reject_button = "<button type='button' data-toggle='modal' data-target='#rejectModal' data-whatever='@getbootstrap' " +
                            "class='btn btn-primary' onclick='javascript:rejectModal(" + data + ")'>驳回</button>";

                        if (full.status == 1) {
                            return pass_button + reject_button;
                        }
                        return "";
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
     * 查看图片：把图片地址传递给弹框
     */
    function imageModal(url) {
        //document.getElementById("image").src = url;
        $('#image').attr("src", url)
    }


    /**
     * 修改状态：通过，将要修改数据的ID存到提示框里
     */
    function passModal(uAId) {
        $('#pass').val(uAId);
    }

    /**
     * 修改状态：通过，从提示框里取出ID，发送给后台
     */
    function passClick() {
        var uAId = $('#pass').val();
        $.post("/goodcomment/update/status", {uAId:uAId, status:2}, function (result) {
            if (result.code === 1008) {
                alert("登录超时，请重新登录！");
                window.location.href = '/login';
            }else if (result.code === 0) {
                alert("状态修改成功！")
                $('#datatab').DataTable().draw(false);
            } else {
                alert("状态修改失败！")
            }
        }, "json");
    }

    /**
    * 修改状态：驳回，将要修改数据的ID存到提示框里
    */
    function rejectModal(uAId) {
        $('#reject').val(uAId);
    }

    /**
     * 修改状态：驳回，从提示框里取出ID，发送给后台
     */
    function rejectClick() {
        var uAId = $('#reject').val();
        $.post("/goodcomment/update/status", {uAId:uAId, status:3}, function (result) {
            if (result.code === 1008) {
                alert("登录超时，请重新登录！");
                window.location.href = '/login';
            }else if (result.code === 0) {
                alert("状态修改成功！")
                $('#datatab').DataTable().draw(false);
            } else {
                alert("状态修改失败！")
            }
        }, "json");
    }
</script>

</body>

</html>