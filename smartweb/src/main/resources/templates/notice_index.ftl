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
                                新增通知
                            </button>

                            <hr>
                            <div class="basic-form">
                                <form>
                                    <div class="form-row">
                                        <div class="form-group col-md-2">
                                            <label>时间</label>
                                            <input id="startTime" type="date" class="form-control"> 至
                                            <input id="endTime" type="date" class="form-control">
                                        </div>
                                        <div class="form-group col-md-2">
                                            <label>状态</label>
                                            <select id="status" class="form-control">
                                                <option value='0' selected='selected'>全选</option>
                                                <option value='1'>关闭</option>
                                                <option value='2'>开启</option>
                                            </select>
                                        </div>
                                        <div class="form-group col-md-2">
                                            <label>通知类型</label>
                                            <select id="notice_type" class="form-control">
                                                <option value='0' selected='selected'>全部</option>
                                                <option value='1'>文本</option>
                                                <option value='2'>图片</option>
                                                <option value='3'>图文</option>
                                            </select>
                                        </div>
                                        <div class="form-group col-md-2">
                                            <label>通知名称</label>
                                            <input id="title" type="text" class="form-control">
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
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close" id="xModal">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <button type="hidden" id="insert" style="display:none;"/>
                        </div>
                        <div class="form-group">
                            <span for="message-text" class="col-form-label">通知类型：<span style="color: red"> *</span></span>
                            <label><input type="radio" name="insert_notice_type" value="1" onclick="insertTypeClick()"/>文本</label>
                            <label><input type="radio" name="insert_notice_type" value="2" onclick="insertTypeClick()"/>图片</label>
                            <label><input type="radio" name="insert_notice_type" value="3" onclick="insertTypeClick()"/>图文</label>
                        </div>
                        <div class="form-group" id="picModal">
                            <span for="message-text" class="col-form-label">图片：<span style="color: red"> *</span></span>
                            <input id="insert_pic" class="form-control" type="file"/>
                        </div>
                        <div class="form-group" id="textModal">
                            <span for="recipient-name" class="col-form-label">文字：<span style="color: red"> *</span></span>
                            <input id="insert_text" class="form-control" type="text"/>
                        </div>
                        <div class="form-group">
                            <span for="recipient-name" class="col-form-label">通知名称：<span style="color: red"> *</span></span>
                            <input id="insert_title" class="form-control" type="text"/>
                        </div><div class="form-group">
                            <span for="recipient-name" class="col-form-label">跳转地址：<span style="color: red"> *</span></span>
                            <input id="insert_url" class="form-control" type="text"/>
                        </div><div class="form-group">
                            <span for="recipient-name" class="col-form-label">提示时间：<span style="color: red"> *</span></span>
                            <input id="insert_showTime" class="form-control" type="time"/>
                        </div><div class="form-group">
                            <span for="recipient-name" class="col-form-label">有效时间：<span style="color: red"> *</span></span>
                            <input id="insert_startTime" type="date" class="form-control"> 至
                            <input id="insert_endTime" type="date" class="form-control">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="insertClick()" data-dismiss="modal">确定上架</button>
                </div>
            </div>
        </div>
    </div>


    <!--弹框：查看通知详情-->
    <div class="modal fade" id="noticeDetailModal" style="display: none;" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="form-group">
                    <button type="hidden" id="" style="display:none;"/>
                </div>
                <div class="modal-header">
                    <h5 class="modal-title">通知详情</h5>
                    <button type="button" class="close" data-dismiss="modal"><span>×</span> </button>
                </div>
                <div class="modal-body">
                    <img src='' id="notice_detail_pic" height='50px' width='50px'/>
                    <p id="notice_detail_text"></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
                </div>
            </div>
        </div>
    </div>


    <!--弹框：修改状态，提示是否真的要切换状态-->
    <div class="modal fade" id="statusModal" style="display: none;" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="form-group">
                    <button type="hidden" id="statusChange_1" style="display:none;"/>
                    <button type="hidden" id="statusChange_2" style="display:none;"/>
                </div>
                <div class="modal-header">
                    <h5 class="modal-title">提示</h5>
                    <button type="button" class="close" data-dismiss="modal"><span>×</span> </button>
                </div>
                <div class="modal-body" id="statusChange_3">
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

        var type = $('input[type="radio"][name="insert_notice_type"]:checked').val();
        var reqData = new FormData();
        reqData.append("type", type);
        if (type == 1) {
            reqData.append("text", $('#insert_text').val());
            reqData.append("picurl", null);
        } else if (type == 2) {
            reqData.append("text", null);
            reqData.append("picurl", $('#insert_pic')[0].files[0]);
        } else {
            reqData.append("text", $('#insert_text').val());
            reqData.append("picurl", $('#insert_pic')[0].files[0]);
        }
        reqData.append("title", $('#insert_title').val());
        reqData.append("url", $('#insert_url').val());
        reqData.append("showTime", $('#insert_showTime').val());
        reqData.append("startTime", $('#insert_startTime').val());
        reqData.append("endTime", $('#insert_endTime').val());

        var text = $('#insert_text').val();
        var picurl = $('#insert_pic')[0].files[0];
        var title = $('#insert_title').val();
        var url = $('#insert_url').val();
        var showTime = $('#insert_showTime').val();
        var startTime = $('#insert_startTime').val();
        var endTime = $('#insert_endTime').val();

        if (type == 1) {
            if (text == null || text.trim() == "") {
                alert("文字不能为空！")
            }
        }else if (type == 2) {
            if (picurl == null) {
                alert("图片不能为空！")
            }
        }else if (type == 3) {
            if (text == null || text.trim() == "") {
                alert("文字不能为空！")
            }
            if (picurl == null) {
                alert("图片不能为空！")
            }
        }else if (title == null || title.trim() == "") {
            alert("通知名称不能为空！")
        }else if (url == null || url.trim() == "") {
            alert("跳转地址不能为空！")
        }else if (showTime == null) {
            alert("提示时间不能为空！")
        }else if (startTime == null) {
            alert("有效时间不能为空！")
        }else if (endTime == null) {
            alert("有效时间不能为空！");
        } else {

            $.ajax({
                type: 'post',
                url: '/notice/insert',
                dataType: 'json',
                data: reqData,
                contentType: false,
                processData: false,
                success: function (result) {
                    if (result.code == 0) {
                        alert("新增成功！");
                    } else {
                        alert("新增失败！");
                    }
                    $('#datatab').DataTable().draw(false);
                    document.getElementById("xModal").click();
                }
            });
        }
    }


    /**
     * 新增：点击新增弹出框中不同的通知类型按钮，显现出相应的内容类型框
     */
    function insertTypeClick() {
        var type = $('input[type="radio"][name="insert_notice_type"]:checked').val();
        if (type == 1) {
            document.getElementById("picModal").style.display = "none";
            document.getElementById("textModal").style.display = "block";
        } else if (type == 2) {
            document.getElementById("picModal").style.display = "block";
            document.getElementById("textModal").style.display = "none";
        } else {
            document.getElementById("picModal").style.display = "block";
            document.getElementById("textModal").style.display = "block";
        }
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
            "ajax": "/notice/query?startTime=" + $('#startTime').val() + "&endTime=" + $('#endTime').val() +
                "&status=" + $('#status').val() + "&type=" + $('#notice_type').val() + "&title=" + $('#title').val(),
            "fnDrawCallback": function () {
                this.api().column(0).nodes().each(function (cell, i) {
                    cell.innerHTML = i + 1;
                });
            },
            "columns": [
                {"data": null, "targets": 0},
                {"data": "type"},
                {"data": "title"},
                {"data": "createTime"},
                {"data": "showTime"},
                {"data": "startTime"},
                {"data": "endTime"},
                {"data": "url"},
                {
                    "data": "type",
                    "render": function (data, type, full) {

                        return "<button type='button'  data-toggle='modal' data-target='#noticeDetailModal' data-whatever='@getbootstrap' " +
                        "class='btn btn-primary' onclick='viewClick("+ data +",\""+ full.text +"\",\""+ full.picurl +"\")'>查看</button>";
                    }
                },
                {
                    "data": "status",
                    "render": function (data, type, full) {

                        var sta_str = data == 1 ? "已关闭" : "已开启"
                        return "<button type='button' data-whatever='@getbootstrap' class='btn btn-primary'>"+ sta_str +"</button>";
                    }
                },
                {
                    "data": "noticeId",
                    "render": function (data, type, full) {

                        // 要修改成的状态值：如果当前状态值为1，就修改成2，反之亦然
                        var status = full.status == 1 ? 2 : 1;
                        var statusButton = "<button type='button' data-toggle='modal' data-target='#statusModal' data-whatever='@getbootstrap' " +
                            "class='btn btn-primary' onclick='javascript:statusModal(" + data +","+ status +")'>开/关</button>";
                        var deleteButton = "<button type='button' data-toggle='modal' data-target='#deleteModal' data-whatever='@getbootstrap' " +
                            "class='btn btn-primary' onclick='javascript:deleteModal(" + data + ")'>删除</button>";
                        return statusButton + deleteButton;
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
     * 查看详情：根据状态类型，来控制详情弹框中所展示的内容
     */
    function viewClick(type, text, picurl) {
        if (type == 1) {
            document.getElementById("notice_detail_text").style.display = "block";
            document.getElementById("notice_detail_text").innerText = text;
            document.getElementById("notice_detail_pic").style.display = "none";
        } else if (type == 2) {
            document.getElementById("notice_detail_text").style.display = "none";
            document.getElementById("notice_detail_pic").src = picurl;
            document.getElementById("notice_detail_pic").style.display = "block";
        } else {
            document.getElementById("notice_detail_text").style.display = "block";
            document.getElementById("notice_detail_text").innerText = text;
            document.getElementById("notice_detail_pic").src = picurl;
            document.getElementById("notice_detail_pic").style.display = "block";
        }
    }


    /**
     * 修改状态：将要修改数据的ID、状态码存到提示框里
     */
    function statusModal(noticeId, status) {
        $('#statusChange_1').val(noticeId);
        $('#statusChange_2').val(status);

        var tip = status == 1 ? "确认关闭？" : "确认开启？"
        document.getElementById("statusChange_3").innerText = tip;
    }

    /**
     * 修改状态：从提示框里取出ID和状态码，发送给后台
     */
    function statusClick() {
        var noticeId = $('#statusChange_1').val();
        var status = $('#statusChange_2').val();

        $.post("/notice/update/status", {noticeId:noticeId, status:status}, function (result) {
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
    function deleteModal(noticeId) {
        //不是取值，而是给弹框赋值
        $('#delete').val(noticeId);
    }

    /**
     * 删除：真正的删除操作，从弹框中获取要删除数据的ID
     */
    function deleteClick() {
        var noticeId = $('#delete').val();
        $.post("/notice/delete", {noticeId:noticeId}, function (result) {
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