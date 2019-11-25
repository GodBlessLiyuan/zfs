<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">

<head>
    <base href="${basePath}"/>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="icon" type="image/x-icon" href="./images/logo.png"  />
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
    <!--jedate-->
    <link rel="stylesheet" type="text/css" href="./plugins/jedate-6.5.0/skin/jedate.css"/>


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

                            <div class="basic-form">
                                <form>
                                    <div class="form-row">
                                        <div class="form-group col-md-2">
                                            <label>时间</label>
                                            <input id="startTime" type="text" class="form-control" placeholder="开始时间"> 至
                                            <input id="endTime" type="text" class="form-control" placeholder="结束时间">
                                        </div>
                                        <div class="form-group col-md-2">
                                            <label>用户账号</label>
                                            <input id="userId" type="text" class="form-control">
                                        </div>
                                        <div class="form-group col-md-2">
                                            <label>联系方式</label>
                                            <input id="contact" type="text" class="form-control">
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
                                   <table id="datatab" class="table table-striped table-bordered zero-configuration"
                                       style="table-layout: fixed; text-align: center; vertical-align: middle"><thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>用户账号</th>
                                        <th>厂商</th>
                                        <th>型号</th>
                                        <th>系统版本</th>
                                        <th>应用版本</th>
                                        <th>反馈时间</th>
                                        <th>联系方式</th>
                                        <th>反馈内容</th>
                                        <th>图片</th>
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
    <div class="modal fade" id="url1Modal" style="display: none;" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="form-group">
                    <button type="hidden" id="" style="display:none;"/>
                </div>
                <div class="modal-header">
                    <h5 class="modal-title">查看图片</h5>
                    <button type="button" class="close" data-dismiss="modal"><span>×</span> </button>
                </div>
                <div class="container-fluid">
                    <div class="row">
                        <img src='' id="url1_pic" height='50px' width='50px'/>
                        <img src='' id="url2_pic" height='50px' width='50px'/>
                        <img src='' id="url3_pic" height='50px' width='50px'/>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
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
<script src="./plugins/jedate-6.5.0/dist/jedate.min.js"></script>

<script>
    /**
     * 页面加载事件：一进入页面就进行一次查询
     */
    window.onload = function () {
        queryClick();
    }


    /**
     * 重置
     */
    function resetClick() {
        $('#startTime').val(null);
        $('#endTime').val(null);
        $('#userId').val(null);
        $('#contact').val(null);
    }

    /**
     * 查询
     */
    function queryClick() {

        if ($.fn.dataTable.isDataTable('#datatab')) {
            $('#datatab').DataTable().destroy();
        }

        $('#datatab').DataTable({
            "processing": false,
            "serverSide": true,
            "searching": false, // 禁用全文搜索
            "ajax": "feedback/query?startTime=" + $('#startTime').val() + "&endTime=" + $('#endTime').val() +
                "&userId=" + $('#userId').val() + "&contact=" + $('#contact').val(),
            "fnDrawCallback": function () {
                this.api().column(0).nodes().each(function (cell, i) {
                    cell.innerHTML = i + 1;
                });
            },
            "columns": [
                {"data": null, "targets": 0},
                {"data": "phone"},
                {"data": "manufacturer"},
                {"data": "androidmodel"},
                {"data": "buildversion"},
                {"data": "versioncode"},
                {"data": "createTime"},
                {"data": "contact"},
                {"data": "context"},
                {
                    "data": "feedbackId",
                    "render": function (data, type, full) {
                        if (null == full.url1 && null == full.url2 && null == full.url3) {
                            return "";
                        } else {
                            return "<button type='button'  data-toggle='modal' data-target='#url1Modal' data-whatever='@getbootstrap' " +
                                "class='btn btn-primary' onclick='viewClick(\""+ full.url1 +"\",\""+ full.url2 +"\",\""+ full.url3 +"\")'>查看</button>";
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
     * 查看图片
     */
    function viewClick(url1, url2, url3) {
        if ("null" == url1) {
            document.getElementById("url1_pic").style.display = "none";
        } else {
            document.getElementById("url1_pic").style.display = "block";
            document.getElementById("url1_pic").src = url1;
        }
        if ("null" == url2) {
            document.getElementById("url2_pic").style.display = "none";
        } else {
            document.getElementById("url2_pic").style.display = "block";
            document.getElementById("url2_pic").src = url2;
        }
        if ("null" == url3) {
            document.getElementById("url3_pic").style.display = "none";
        } else {
            document.getElementById("url3_pic").style.display = "block";
            document.getElementById("url3_pic").src = url3;
        }
    }


    /**
     * 日期控件
     */
    document.getElementById("startTime").addEventListener("focus",function () {
        jeDate(this, {
            theme: {bgcolor: "#f60", color: "#fff", pnColor: "#f60"},   //设置颜色
            format: "YYYY-MM-DD",                                       //设置时间格式
            minDate: "1995-01-01 00:00:00",                             //设置最小日期
            maxDate: "2099-12-31 23:59:59"                              //设置最大日期
        });
    });
    document.getElementById("endTime").addEventListener("focus",function () {
        jeDate(this, {
            theme: {bgcolor: "#f60", color: "#fff", pnColor: "#f60"},   //设置颜色
            format: "YYYY-MM-DD",                                       //设置时间格式
            minDate: "1995-01-01 00:00:00",                             //设置最小日期
            maxDate: "2099-12-31 23:59:59"                              //设置最大日期
        });
    });
</script>

</body>

</html>