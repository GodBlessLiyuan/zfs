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
        <button type="button" class="btn btn-primary m-r-5 m-b-5"
                id="add">新增产品
        </button>
        <hr>
        <div class="form-group">
            <div class="col-md-4 ">
                <div class="input-group">
                    <span class="input-group-addon">操作人:</span>
                    <input id="operator" type="text" class="form-control">
                    <button type="button" class="btn btn-primary m-r-5 m-b-5"
                            id="reset">重置
                    </button>
                    <button type="button" class="btn btn-primary m-r-5 m-b-5" onclick="javascript:queryClick();"
                            id="query">查询
                    </button>
                </div>
            </div>
        </div>
        <hr>
        <div class="table-responsive">
            <table id="comtypetab" class="display" style="width:100%">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>产品类型</th>
                    <th>产品天数</th>
                    <th>配置时间</th>
                    <th>备注</th>
                    <th>操作人</th>
                </tr>
                </thead>
            </table>
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
    // $(document).ready(function() {
    //     $('#comtypetab').DataTable( {
    //         "processing": true,
    //         "serverSide": true,
    //         "ajax": "/comtype/query?operator=2",
    //         "columns": [
    //             { "data": "name" },
    //             { "data": "name" },
    //             { "data": "days" },
    //             { "data": "createTime" },
    //             { "data": "extra" },
    //             { "data": "aId" }
    //         ]
    //     } );
    // } );

    function queryClick() {

        if ( $.fn.dataTable.isDataTable( '#comtypetab' ) ) {
            $('#comtypetab').DataTable().destroy();
        }

        $('#comtypetab').DataTable({
            "processing": true,
            "serverSide": true,
            "ajax": "/comtype/query?operator=" + $('#operator').val(),
            "columns": [
                {"data": "name"},
                {"data": "name"},
                {"data": "days"},
                {"data": "createTime"},
                {"data": "extra"},
                {"data": "aId"}
            ]
        });
    }
</script>

</body>

</html>