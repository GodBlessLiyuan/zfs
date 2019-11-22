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
                                    data-target='#insertModal' onclick="insertModal()">创建会员卡
                            </button>
                            <button type="button" class="btn btn-primary" id="export"
                                    onclick="javascript:exportClick();">导出
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
                                            <label>产品类型：</label>
                                            <select id="comTypeId" class="form-control">
                                                <option value='0' selected='selected'>全部</option>
                                            </select>
                                        </div>
                                        <div class="form-group col-md-2">
                                            <label>状态：</label>
                                            <select id="status" class="form-control">
                                                <option value='0' selected='selected'>全部</option>
                                                <option value='1'>正常</option>
                                                <option value='3'>已冻结</option>
                                                <option value='4'>已失效</option>
                                                <option value='5'>已结束</option>
                                            </select>
                                        </div>
                                        <div class="form-group col-md-2">
                                            <label>操作人：</label>
                                            <input id="operator" type="text" class="form-control">
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
                                       style="table-layout: fixed; text-align: center; vertical-align: middle">
                                    <thead>
                                        <tr>
                                            <th>序号</th>
                                            <th>渠道标识</th>
                                            <th>渠道名称</th>
                                            <th>创建时间</th>
                                            <th>创建人</th>
                                            <th>产品类型</th>
                                            <th>创建数量</th>
                                            <th>已激活</th>
                                            <th>未激活</th>
                                            <th>备注</th>
                                            <th>状态</th>
                                            <th>操作时间</th>
                                            <th width="190px">操作</th>
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
                            <span for="recipient-name" class="col-form-label">渠道标识：<span style="color: red"> *</span></span>
                            <select id="insert_chanId" class="form-control">
                            </select>
                        </div>
                        <div class="form-group">
                            <span for="recipient-name" class="col-form-label">创建数量：<span style="color: red"> *</span></span>
                            <input id="insert_num" class="form-control" type="number"
                                   min="1"/>
                        </div>
                        <div class="form-group">
                            <span for="recipient-name" class="col-form-label">产品类型：<span style="color: red"> *</span></span>
                            <select id="insert_comTypeId" class="form-control">
                            </select>
                        </div>
                        <div class="form-group">
                            <span for="message-text" class="col-form-label">备注:</span>
                            <input id="insert_extra" class="form-control" type="text">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="insertClick()">确定创建</button>
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
     * 页面加载事件：往t_com_type表中查询所有的产品类型
     */
    $(document).ready(function () {
        $.ajax({
            type: 'GET',
            url: 'chbatch/queryComTypes',
            dataType: 'JSON',
            success: function (result) {
                if (result.code === 1008) {
                    alert("登录超时，请重新登录！");
                    window.location.href = 'login';
                } else {
                    for (var i = 0; i < result.data.length; i++) {
                        $('#comTypeId').append("<option value='" + result.data[i].comTypeId + "'>" + result.data[i].name + "</option>");
                    }
                }
            }
        });
    })


    /**
     * 新增：点击新增会员卡时，往t_channel表中查询所有的渠道标识，往t_com_type表中查询所有的产品类型
     */
    function insertModal() {

        // 先对上次查询的结果进行清空
        $('#insert_chanId').empty();

        $.ajax({
            type: 'GET',
            url: 'chbatch/queryChanNicknames',
            dataType: 'JSON',
            success: function (result) {
                if (result.code === 1008) {
                    alert("登录超时，请重新登录！");
                    window.location.href = 'login';
                } else {
                    for (var i = 0; i < result.data.length; i++) {
                        $('#insert_chanId').append("<option value='" + result.data[i].chanId + "'>" + result.data[i].chanNickname + "</option>");
                    }
                }
            }
        });

        // 先对上次查询的结果进行清空
        $('#insert_comTypeId').empty();

        $.ajax({
            type: 'GET',
            url: 'chbatch/queryComTypes',
            dataType: 'JSON',
            success: function (result) {
                if (result.code === 1008) {
                    alert("登录超时，请重新登录！");
                    window.location.href = 'login';
                } else {
                    for (var i = 0; i < result.data.length; i++) {
                        $('#insert_comTypeId').append("<option value='" + result.data[i].comTypeId + "'>" + result.data[i].name + "</option>");
                    }
                }
            }
        });
    }


    /**
     * 新增：在弹框中点击确定按钮后，将数据发送给后台
     */
    function insertClick() {

        var chanId = $('#insert_chanId').val();
        var num = $('#insert_num').val();
        var comTypeId = $('#insert_comTypeId').val();
        var extra = $('#insert_extra').val();

        if (num == null) {
            alert("创建数量不能为空！");
        } else {

            $.post("chbatch/insert", {chanId:chanId, num:num, comTypeId:comTypeId, extra:extra}, function (result) {
                if (result.code === 1008) {
                    alert("登录超时，请重新登录！");
                    window.location.href = 'login';
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

    /**
     * 导出
     */
    function exportClick() {
        var chanNickname = $('#chanNickname').val()
        var comTypeId = $('#comTypeId').val()
        var status= $('#status').val()
        var operator= $('#operator').val()
        var json = {
            chanNickname:chanNickname,
            comTypeId:comTypeId,
            status:status,
            operator:operator
        }
        window.location.href = encodeURI('chbatch/export?parameterMap=' + JSON.stringify(json));
    }



    /**
     * 重置
     */
    function resetClick() {
        $('#chanNickname').val(null);
        $('#comTypeId option:first').prop('selected', 'selected');
        $('#status option:first').prop('selected', 'selected');
        $('#operator').val(null);
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
            "ajax": "chbatch/query?chanNickname=" + $('#chanNickname').val() + "&comTypeId=" + $('#comTypeId').val()
                + "&status=" + $('#status').val() + "&operator=" + $('#operator').val(),
            "fnDrawCallback": function () {
                this.api().column(0).nodes().each(function (cell, i) {
                    cell.innerHTML = i + 1;
                });
            },
            "columns": [
                {"data": null, "targets": 0},
                {"data": "chanNickname"},
                {"data": "chanName"},
                {"data": "createTime"},
                {"data": "creater"},
                {"data": "comTypeName"},
                {"data": "num"},
                {"data": "activity"},
                {"data": "nonActivity"},
                {"data": "extra"},
                {
                    "data": "status",
                    "render": function (data, type, full) {
                        var sta;
                        if (data === 1) {
                            sta = "正常";
                        }else if(data === 3){
                            sta = "已冻结";
                        }else if(data === 4){
                            sta = "已失效";
                        }else if(data === 5){
                            sta = "已结束";
                        }
                        return sta;
                    }
                },
                {"data": "updateTime"},
                {
                    "data": "batchId",
                    "render": function (data, type, full) {

                        var detail_link = "<button type='button'  data-whatever='@getbootstrap'" +
                            "class='btn btn-primary' onclick='javascript:detailModal(" + data + ")'>详情</button> ";
                        var freeze_button = "<button type='button' data-toggle='modal' data-target='#statusModal' data-whatever='@getbootstrap' " +
                            "class='btn btn-primary' onclick='javascript:statusModal(" + data +","+ 3 +")'>冻结</button> ";
                        var unfreeze_button = "<button type='button' data-toggle='modal' data-target='#statusModal' data-whatever='@getbootstrap' " +
                            "class='btn btn-primary' onclick='javascript:statusModal(" + data +","+ 1 +")'>解冻</button> ";
                        var invalid_button = "<button type='button' data-toggle='modal' data-target='#statusModal' data-whatever='@getbootstrap' " +
                            "class='btn btn-primary' onclick='javascript:statusModal(" + data +","+ 4 +")'>失效</button> ";

                        if (full.status === 1) {
                            return detail_link + freeze_button + invalid_button;
                        }else if (full.status === 3) {
                            return detail_link + unfreeze_button + invalid_button;
                        } else {
                            return detail_link;
                        }
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
     * 修改状态：将要修改数据的ID、状态码存到提示框里
     */
    function statusModal(batchId, status) {
        $('#statusChange_1').val(batchId);
        $('#statusChange_2').val(status);

        if (status == 1) {
            document.getElementById("statusChange_3").innerText = "是否解冻此渠道下的会员卡？"
        }else if (status == 3) {
            document.getElementById("statusChange_3").innerText = "是否冻结此渠道下会员卡？冻结后会员卡无法使用，解冻后可重新使用"
        }else if (status == 4) {
            document.getElementById("statusChange_3").innerText = "是否失效此渠道下未激活的会员卡？"
        }
    }

    /**
     * 修改状态：从提示框里取出ID和状态码，发送给后台
     */
    function statusClick() {
        var batchId = $('#statusChange_1').val();
        var status = $('#statusChange_2').val();

        $.post("chbatch/update/status", {batchId:batchId, status:status}, function (result) {
            if (result.code === 1008) {
                alert("登录超时，请重新登录！");
                window.location.href = 'login';
            }else if (result.code === 0) {
                alert("状态修改成功！")
                $('#datatab').DataTable().draw(false);
            } else {
                alert("状态修改失败！")
            }
        }, "json");
    }

    /**
     * 查看详情：页面跳转
     */
    function detailModal(batchId) {
        if (null === batchId) {
            alert("出了些问题，无法跳转，请联系工程师！");
        } else {

            window.location.href = 'batchinfo/detail?batchId='+ batchId;
        }
    }

</script>

</body>

</html>