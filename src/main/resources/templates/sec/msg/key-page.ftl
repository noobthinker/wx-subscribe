<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${name} | 控制面板</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/plugins/fontawesome-free/css/all.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Tempusdominus Bbootstrap 4 -->
    <link rel="stylesheet" href="/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
    <!-- JQVMap -->
    <link rel="stylesheet" href="/plugins/jqvmap/jqvmap.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/css/adminlte.min.css">
    <!-- overlayScrollbars -->
    <link rel="stylesheet" href="/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
    <!-- Daterange picker -->
    <link rel="stylesheet" href="/plugins/daterangepicker/daterangepicker.css">
    <!-- summernote -->
    <link rel="stylesheet" href="/plugins/summernote/summernote-bs4.css">
    <!-- Google Font: Source Sans Pro -->
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">

    <link rel="stylesheet" href="/plugins/datatables/dataTables.bootstrap4.css">
</head>
<body class="hold-transition sidebar-mini layout-fixed layout-navbar-fixed layout-footer-fixed">
<div class="wrapper">

    <#include "../../sub/header.ftl"/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <div class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1 class="m-0 text-dark">主页 v1</h1>
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">Home</a></li>
                            <li class="breadcrumb-item active">${name} v1</li>
                        </ol>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.container-fluid -->
        </div>
        <!-- /.content-header -->

        <!-- Main content -->

        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-header">
                        <h3 class="card-title">图文回复列表</h3>
                    </div>
                    <!-- /.card-header -->
                    <div class="card-body p-0">
                        <table class="table table-bordered table-striped" id="dataTab">
                            <thead>
                            <tr>
                                <th style="width: 10px">#</th>
                                <td>Id</td>
                                <td>状态</td>
                                <th>关键字</th>
                                <th>描述</th>
                                <th>类型</th>
                                <th>--</th>
                            </tr>
                            </thead>
                            <tbody>

                            <#list dataList as data>
                            <tr onclick="">
                                <td>${1+data_index }</td>
                                <td>${data.id}</td>
                                <td><#if data.status==1>
                                    线上
                                    <#else>
                                    下线
                                </#if></td>
                                <td>
                                    <#if data.targetList=="subscribe">
                                            【关注回复】
                                        <#else>
                                            ${data.targetList}
                                    </#if>

                                </td>
                                <td>${data.functionTxt}</td>
                                <td>
                                    ${data.keyType.getDesc()}</td>
                                <td><a href="/back/key-page-convert-${data.id}.html">状态转换</a></td>
                            </tr>
                            </#list>

                            </tbody>
                        </table>
                    </div>
                    <div class="card-footer clearfix">
                        <a href="/back/user-page-add.html" class="btn btn-sm btn-info float-left">添加</a>
                    </div>
                </div>
            </div>
        </div>


        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
        <!-- Control sidebar content goes here -->
    </aside>
    <!-- /.control-sidebar -->

    <!-- Main Footer -->
    <#include "../../sub/footer.ftl">

</div>
<!-- ./wrapper -->

<!-- REQUIRED SCRIPTS -->
<!-- jQuery -->
<script src="/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- overlayScrollbars -->
<script src="/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
<script src="/plugins/datatables/jquery.dataTables.js"></script>
<script src="/plugins/datatables/dataTables.bootstrap4.js"></script>
<!-- AdminLTE App -->
<script src="/js/adminlte.js"></script>

<!-- OPTIONAL SCRIPTS -->
<script src="/js/demo.js"></script>

<!-- PAGE PLUGINS -->
<!-- jQuery Mapael -->
<script src="/plugins/jquery-mousewheel/jquery.mousewheel.js"></script>
<script src="/plugins/raphael/raphael.min.js"></script>
<script src="/plugins/jquery-mapael/jquery.mapael.min.js"></script>
<script src="/plugins/jquery-mapael/maps/world_countries.min.js"></script>
<!-- ChartJS -->
<script src="/plugins/chart.js/Chart.min.js"></script>

<!-- PAGE SCRIPTS -->
<script src="/js/pages/dashboard2.js"></script>

<script>
    $(function () {
        $("#dataTab").DataTable({
            'paging'      : false,
            'lengthChange': false,
            'searching'   : true,
            'autoWidth'   : false
        });
    });

</script>
</body>
</html>
