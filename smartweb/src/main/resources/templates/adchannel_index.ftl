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
                                        <div class="form-group col-md-6">
                                            <label>渠道</label>
                                            <input id="channel" type="text" class="form-control">
                                        </div>
                                        <div class="form-group col-md-4">
                                            <label>版本</label>
                                            <select id="version" class="form-control">
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
                                <table id="datatab" class="display" style="width:100%">
                                    <thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>渠道</th>
                                        <th>版本</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                </table>
                            </div>


                            <button type="button" class="btn btn-primary " id="chooseAll"
                                    onclick="javascript:checkAllClick()">全选
                            </button>
                            <button type="button" class="btn btn-primary " id="submit"
                                    onclick="javascript:submitClick();">提交
                            </button>
                            <button type="button" class="btn btn-primary " id="back"
                                    onclick="javascript:backClick();">返回
                            </button>

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
    /**
     * 页面加载事件：查询所有的版本名
     */
    $(document).ready(function () {
        // 产品列表
        $.ajax({
            type: 'GET',
            url: '/adchannel/queryVersionname',
            dataType: 'JSON',
            success: function (result) {
                for (var i = 0; i < result.data.length; i++) {
                    $('#version').append("<option value='" + result.data[i].appId + "'>" + result.data[i].versionName + "</option>");
                }
            }
        });
    })


    /**
     * 页面加载事件：一进入页面，就进行一次查询
     */
    $(document).ready(function () {
        queryClick();
    })


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
            "ajax": "/adchannel/query?adId=" + ${adId} + "&name=" + $('#channel').val() + "&appId=" + $('#version').val(),
            "fnDrawCallback": function () {
                this.api().column(0).nodes().each(function (cell, i) {
                    cell.innerHTML = i + 1;
                });
            },
            "columns": [
                {"data": null, "targets": 0},
                {"data": "name"},
                {"data": "versionname"},
                {
                    "data": "type",
                    "render": function (data, type, full) {
                        var checked = data === 1 ? "checked='checked'" : "";
                        return "<input type='checkbox' id='checkbox' name='type' data-whatever='@getbootstrap' value='"+ data +"'" + checked +
                        " onclick='javascript:statusModal(" + full.adId + "," + full.appId + "," + full.softChannelId + "," + data + ")'>开启广告</input>";
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
     * 修改状态：先将要修改的数据存到map中
     */
    var map = new Map();

    function statusModal(adId, appId, softChannelId, type) {
        var key = adId + "_" + appId + "_" + softChannelId;
        if(map.has(key)) {
            map.delete(key);
        }else {
            map.set(key, type);
        }
    }


    /**
     * 全选：点击按钮，勾选所有未勾选的
     */
    var allChecked = false;
    function checkAllClick() {
        if(allChecked) {
            // 反选
            $("input[name = 'type']").each(function () {
                if (this.checked) {
                    this.click();
                }
            })
            allChecked = false;
        }else {
            // 全选
            $("input[name = 'type']").each(function () {
                if (!this.checked) {
                    this.click();
                }
            })
            allChecked = true;
        }
    }


    /**
     * 提交：将所选中的数据发送给后台，修改开启广告的设置
     */
    function submitClick() {
        // 解析map，转换成json格式，放入数组中
        var arr = [];
        map.forEach(function (value, key) {
            var splitArray = key.split("_");
            var o = {
                "adId": parseInt(splitArray[0]),
                "appId": parseInt(splitArray[1]),
                "softChannelId": parseInt(splitArray[2]),
                "type": value
            }
            arr.push(o);
        })


        $.ajax({
            type: 'post',
            url: '/adchannel/update',
            dataType: 'json',
            data: JSON.stringify(arr),
            contentType: "application/json",
            processData: false,
            success: function (result) {
                if (result.code == 0) {
                    alert("更改成功！");
                } else {
                    alert("更改失败！");
                }
                map.clear();
                $('#datatab').DataTable().draw(false);
            }
        });
    }

    /**
     * 返回：跳转回原来的页面
     */
    function backClick() {
        window.location.href = '/adconfig';
    }
</script>

</body>

</html>