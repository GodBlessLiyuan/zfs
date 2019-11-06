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
    <link href="./css/bootstrap.css"/>


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
                                新增广告
                            </button>
                            <button type="button" class="btn btn-primary" data-toggle='modal' data-target='#setAdModal'
                                    onclick="javascript:setAdQueryClick();">广告策略设置
                            </button>
                            <button type="button" class="btn btn-primary" id="export"
                                    onclick="javascript:exportClick();">导出
                            </button>

                            <hr>
                            <div class="basic-form">
                                <form>
                                    <div class="form-row">
                                        <div class="form-group col-md-2">
                                            <label>广告商</label>
                                            <input id="name" type="text" class="form-control">
                                        </div>
                                        <div class="form-group col-md-2">
                                            <label>广告ID</label>
                                            <input id="adNumber" type="text" class="form-control">
                                        </div>
                                        <div class="form-group col-md-2">
                                            <label>状态</label>
                                            <select id="status" class="form-control">
                                                <option value='0' selected='selected'>全选</option>
                                                <option value='1'>关闭</option>
                                                <option value='2'>开启</option>
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
                                        <th>广告ID</th>
                                        <th>广告商名称</th>
                                        <th>上线时间</th>
                                        <th>接入人</th>
                                        <th>联系方式</th>
                                        <th>展示优先级</th>
                                        <th>展示次数</th>
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
                            <span for="message-text" class="col-form-label">广告ID：<span style="color: red"> *</span></span>
                            <input id="insert_adNumber" class="form-control" type="text"/>
                        </div>
                        <div class="form-group">
                            <span for="recipient-name" class="col-form-label">广告商名称：<span style="color: red"> *</span></span>
                            <input id="insert_name" class="form-control" type="text"/>
                        </div>
                        <div class="form-group">
                            <span for="recipient-name" class="col-form-label">接入人：<span style="color: red"> *</span></span>
                            <input id="insert_contacts" class="form-control" type="text"/>
                        </div>
                        <div class="form-group">
                            <span for="message-text" class="col-form-label">联系方式：<span style="color: red"> *</span></span>
                            <input id="insert_phone" class="form-control" type="text"
                                   onblur="insert_checkPhone()"/>
                        </div>
                        <div class="form-group">
                            <span for="message-text" class="col-form-label">展示优先级：<span style="color: red"> *</span></span>
                            <input id="insert_priority" class="form-control" type="number"
                                   min="1" max="100"/>
                        </div>
                        <div class="form-group">
                            <span for="message-text" class="col-form-label">展示次数：<span style="color: red"> *</span></span>
                            <input id="insert_total" class="form-control" type="number"
                                   min="1" max="10"/>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="insertClick()">确定上架</button>
                </div>
            </div>
        </div>
    </div>


    <!--弹框：广告策略设置-->
    <div class="modal fade" id="setAdModal" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalLabel" style="display: none;" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">广告策略设置</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <button type="hidden" id="setAd" style="display:none;"/>
                        </div>
                        <div class="form-group">
                            <span for="message-text" class="col-form-label">展现间隔:</span>
                            <input id="show_interval" class="form-control" type="text"/>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="setAdClick()">确定设置</button>
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


    <!--弹框：修改-->
    <div class="modal fade" id="updateModal" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalLabel" style="display: none;" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">修改</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close" id="update_xModal">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <button type="hidden" id="update" style="display:none;"/>
                        </div>
                        <div class="form-group">
                            <span for="message-text" class="col-form-label">广告ID：<span style="color: red"> *</span></span>
                            <input id="up_adNumber" class="form-control" type="text"/>
                        </div>
                        <div class="form-group">
                            <span for="recipient-name" class="col-form-label">广告商名称：<span style="color: red"> *</span></span>
                            <input id="up_name" class="form-control" type="text"/>
                        </div>
                        <div class="form-group">
                            <span for="recipient-name" class="col-form-label">接入人：<span style="color: red"> *</span></span>
                            <input id="up_contacts" class="form-control" type="text"/>
                        </div>
                        <div class="form-group">
                            <span for="message-text" class="col-form-label">联系方式：<span style="color: red"> *</span></span>
                            <input id="up_phone" class="form-control" type="text"
                                   onblur="up_checkPhone()">
                        </div>
                        <div class="form-group">
                            <span for="message-text" class="col-form-label">展示优先级：<span style="color: red"> *</span></span>
                            <input id="up_priority" class="form-control" type="number"
                                   min="1" max="10">
                        </div>
                        <div class="form-group">
                            <span for="message-text" class="col-form-label">展示次数：<span style="color: red"> *</span></span>
                            <input id="up_total" class="form-control" type="number"
                                   min="1" max="10">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="updateClick()">确认修改</button>
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
     * 新增广告：在弹框中点击确定按钮后，将数据发送给后台
     */
    function insertClick() {

        var adNumber = $('#insert_adNumber').val();
        var name = $('#insert_name').val();
        var contacts = $('#insert_contacts').val();
        var phone = $('#insert_phone').val();
        var priority = $('#insert_priority').val();
        var total = $('#insert_total').val();

        if (adNumber == null || adNumber.trim() == "") {
            alert("广告ID不能为空！")
        }else if (name == null || name.trim() == "") {
            alert("广告商名称不能为空！")
        }else if (contacts == null || contacts.trim() == "") {
            alert("接入人不能为空！")
        }else if (phone == null || phone.trim() == "") {
            alert("联系人不能为空！")
        }else if (priority == null || priority.trim() == "") {
            alert("展示优先级不能为空！")
        }else if (total == null || total.trim() == "") {
            alert("展示次数不能为空！");
        } else {
            $.post("/adconfig/insert", {adNumber:adNumber, name:name, contacts:contacts, phone:phone,
                priority:priority, total:total}, function (result) {
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


    /**
     * 每个必填输入框后，加上 * 号
     */
    $("form :input.required").each(function(){
        var $required = $("<strong class='high'> *</strong>");
        $(this).parent().append($required);
    })


    /**
     * 导出
     */
    function exportClick() {
    }

    /**
     * 广告策略设置：点击按钮，出来弹框，进行查询
     */
    function setAdQueryClick() {

        $.ajax({
            type: 'GET',
            url: '/adconfig/query/strategy',
            dataType: 'JSON',
            success: function (result) {
                if (result.code === 1008) {
                    alert("登录超时，请重新登录！");
                    window.location.href = '/login';
                } else {
                    $('#show_interval').val(result.data.value);
                }
            }
        })
    }

    /**
     * 广告策略设置：确定设置
     */
    function setAdClick() {
        var show_interval = $('#show_interval').val();
        $.post("/adconfig/update/strategy", {show_interval:show_interval}, function (result) {
            if (result.code === 1008) {
                alert("登录超时，请重新登录！");
                window.location.href = '/login';
            }else if (result.code === 0) {
                alert("设置成功！")
            } else {
                alert("设置失败！")
            }
        }, "json");
    }

    /**
     * 重置
     */
    function resetClick() {
        $('#name').val(null);
        $('#adNumber').val(null);
        $('#status option:first').prop('selected', 'selected');
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
            "ajax": "/adconfig/query?name=" + $('#name').val() + "&adNumber=" + $('#adNumber').val()
                + "&status=" + $('#status').val(),
            "fnDrawCallback": function () {
                this.api().column(0).nodes().each(function (cell, i) {
                    cell.innerHTML = i + 1;
                });
            },
            "columns": [
                {"data": null, "targets": 0},
                {"data": "adNumber"},
                {"data": "name"},
                {"data": "createTime"},
                {"data": "contacts"},
                {"data": "phone"},
                {"data": "priority"},
                {"data": "total"},
                {
                    "data": "status",
                    "render": function (data, type, full) {
                        var str = data == 1 ? "关闭" : "开启";
                        return "<button type='button'  data-toggle='modal' data-target='#statusModal' data-whatever='@getbootstrap' " +
                            "class='btn btn-primary' onclick='javascript:statusModal(" + full.adId + ")'>"+ str +"</button>";
                    }
                },
                {
                    "data": "adId",
                    "render": function (data, type, full) {

                        var channel_button = "<button type='button'  data-whatever='@getbootstrap'" +
                            "class='btn btn-primary' onclick='javascript:channelModal(" + data + ")'>设置开放渠道</button> ";
                        var update_button = "<button type='button' data-toggle='modal' data-target='#updateModal' data-whatever='@getbootstrap' " +
                            "class='btn btn-primary' onclick='javascript:updateModal(" + data + ")'>修改</button>";
                        var delete_button = "<button type='button' data-toggle='modal' data-target='#deleteModal' data-whatever='@getbootstrap' " +
                            "class='btn btn-primary' onclick='javascript:deleteModal(" + data + ")'>删除</button>";

                        return channel_button + update_button + delete_button;
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
    function statusModal(adId) {
        $('#statusExchange').val(adId);
    }

    /**
     * 修改状态：从提示框里取出ID，发送给后台
     */
    function statusClick() {
        var adId = $('#statusExchange').val();
        $.post("/adconfig/update/status", {adId:adId}, function (result) {
            if (result.code === 1008) {
                alert("登录超时，请重新登录！");
                window.location.href = '/login';
            }else if (result.code === 0) {
                alert("状态修改成功！");
                $('#datatab').DataTable().draw(false);
            } else {
                alert("状态修改失败！");
            }
        }, "json");
    }

    /**
     * 设置开放渠道：页面跳转
     */
    function channelModal(adId) {
        if (null === adId) {
            alert("出了些问题，无法跳转，请联系工程师！");
        } else {
            window.location.href = '/adchannel?adId='+ adId;
        }
    }

    /**
     * 修改：在表格中点击修改按钮后，到后台查询数据，给弹出框填满值
     */
    function updateModal(adId) {

        //不是取值，而是把要修改数据的ID存放到弹框
        $('#update').val(adId);

        $.ajax({
            type: 'GET',
            url: '/adconfig/queryById?adId=' + adId,
            dataType: 'JSON',
            success: function (result) {
                if (result.code === 1008) {
                    alert("登录超时，请重新登录！");
                    window.location.href = '/login';
                } else {
                    $('#up_adNumber').val(result.data.adNumber);
                    $('#up_name').val(result.data.name);
                    $('#up_contacts').val(result.data.contacts);
                    $('#up_phone').val(result.data.phone);
                    $('#up_priority').val(result.data.priority);
                    $('#up_total').val(result.data.total);
                }
            }
        })
    }

    /**
     * 修改：在弹出框中点击确认修改后，即通过此发送到后台
     */
    function updateClick() {

        var adId = $('#update').val();
        var adNumber = $('#up_adNumber').val();
        var name = $('#up_name').val();
        var contacts = $('#up_contacts').val();
        var phone = $('#up_phone').val();
        var priority = $('#up_priority').val();
        var total = $('#up_total').val();

        if (adNumber == null || adNumber.trim() == "") {
            alert("广告ID不能为空！")
        }else if (name == null || name.trim() == "") {
            alert("广告商名称不能为空！")
        }else if (contacts == null || contacts.trim() == "") {
            alert("接入人不能为空！")
        }else if (phone == null || phone.trim() == "") {
            alert("联系人不能为空！")
        }else if (priority == null || priority.trim() == "") {
            alert("展示优先级不能为空！")
        }else if (total == null || total.trim() == "") {
            alert("展示次数不能为空！");
        } else {
            $.post("/adconfig/update", {adId:adId, adNumber:adNumber, name:name, contacts:contacts, phone:phone,
                priority:priority, total:total}, function (result) {
                if (result.code === 1008) {
                    alert("登录超时，请重新登录！");
                    window.location.href = '/login';
                }else if (result.code === 0) {
                    alert("更新成功！")
                    $('#datatab').DataTable().draw(false);
                } else {
                    alert("更新失败！")
                }
                document.getElementById("update_xModal").click();
            }, "json");
        }
    }

    /**
     * 删除：表格中的删除按钮，作用是将要删除数据的ID传递给提示框
     */
    function deleteModal(adId) {
        //不是取值，而是给弹框赋值
        $('#delete').val(adId);
    }

    /**
     * 删除：真正的删除操作，从弹框中获取要删除数据的ID
     */
    function deleteClick() {
        var adId = $('#delete').val();
        $.post("/adconfig/delete", {adId:adId}, function (result) {
            if (result.code === 1008) {
                alert("登录超时，请重新登录！");
                window.location.href = '/login';
            }else if (result.code === 0) {
                alert("删除成功！")
                $('#datatab').DataTable().draw(false);
            } else {
                alert("删除失败！")
            }
        }, "json");
    }


    /**
     * 输入框限制：手机号码
     */
    function insert_checkPhone() {
        var phone = document.getElementById('insert_phone').value;
        checkPhone(phone);
    }

    function up_checkPhone() {
        var phone = document.getElementById('up_phone').value;
        checkPhone(phone);
    }

    function checkPhone(phone){
        if(!(/^1[3456789]\d{9}$/.test(phone))){
            alert("手机号码有误，请重填！");
        }
    }
</script>

</body>

</html>