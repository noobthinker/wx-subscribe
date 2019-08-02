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
</head>
<body class="hold-transition sidebar-mini layout-fixed layout-navbar-fixed layout-footer-fixed">
<div class="wrapper">

    <#include "sub/header.ftl"/>

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
        <section class="content">
            <div class="container-fluid">
                <!-- Info boxes -->
                <div class="row">
                    <div class="col-12 col-sm-6 col-md-3">
                        <div class="info-box">
                            <span class="info-box-icon bg-info elevation-1"><i class="fas fa-cog"></i></span>

                            <div class="info-box-content">
                                <span class="info-box-text">参加活动数</span>
                                <span class="info-box-number">
                  10
                </span>
                            </div>
                            <!-- /.info-box-content -->
                        </div>
                        <!-- /.info-box -->
                    </div>
                    <!-- /.col -->
                    <div class="col-12 col-sm-6 col-md-3">
                        <div class="info-box mb-3">
                            <span class="info-box-icon bg-danger elevation-1"><i class="fas fa-thumbs-up"></i></span>

                            <div class="info-box-content">
                                <span class="info-box-text">进行中的活动</span>
                                <span class="info-box-number">41,410</span>
                            </div>
                            <!-- /.info-box-content -->
                        </div>
                        <!-- /.info-box -->
                    </div>
                    <!-- /.col -->

                    <!-- fix for small devices only -->
                    <div class="clearfix hidden-md-up"></div>

                    <div class="col-12 col-sm-6 col-md-3">
                        <div class="info-box mb-3">
                            <span class="info-box-icon bg-success elevation-1"><i class="fas fa-shopping-cart"></i></span>

                            <div class="info-box-content">
                                <span class="info-box-text">总订阅人数</span>
                                <span class="info-box-number">760</span>
                            </div>
                            <!-- /.info-box-content -->
                        </div>
                        <!-- /.info-box -->
                    </div>
                    <!-- /.col -->
                    <div class="col-12 col-sm-6 col-md-3">
                        <div class="info-box mb-3">
                            <span class="info-box-icon bg-warning elevation-1"><i class="fas fa-users"></i></span>

                            <div class="info-box-content">
                                <span class="info-box-text">新用户</span>
                                <span class="info-box-number">2,000</span>
                            </div>
                            <!-- /.info-box-content -->
                        </div>
                        <!-- /.info-box -->
                    </div>
                    <!-- /.col -->
                </div>
                <!-- /.row -->

                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                <h5 class="card-title">最近订阅用户</h5>

                                <div class="card-tools">
                                    <span class="badge badge-danger">300新用户</span>
                                    <button type="button" class="btn btn-tool" data-widget="collapse">
                                        <i class="fas fa-minus"></i>
                                    </button>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-tool dropdown-toggle" data-toggle="dropdown">
                                            <i class="fas fa-wrench"></i>
                                        </button>
                                        <div class="dropdown-menu dropdown-menu-right" role="menu">
                                            <a href="#" class="dropdown-item">查看所有用户</a>
                                            <a href="#" class="dropdown-item">刷新</a>
                                        </div>
                                    </div>
                                    <button type="button" class="btn btn-tool" data-widget="remove">
                                        <i class="fas fa-times"></i>
                                    </button>
                                </div>
                            </div>
                            <!-- /.card-header -->
                            <div class="card-body">
                                <div class="row">
                                    <!-- USERS LIST -->
                                    <div class="col-md-12">
                                        <!-- /.card-header -->
                                        <div class="card-body p-0">
                                            <ul class="users-list clearfix">
                                                <li>
                                                    <img src="dist/img/user1-128x128.jpg" alt="User Image">
                                                    <a class="users-list-name" href="#">Alexander Pierce</a>
                                                    <span class="users-list-date">Today</span>
                                                </li>
                                                <li>
                                                    <img src="dist/img/user8-128x128.jpg" alt="User Image">
                                                    <a class="users-list-name" href="#">Norman</a>
                                                    <span class="users-list-date">Yesterday</span>
                                                </li>
                                                <li>
                                                    <img src="dist/img/user7-128x128.jpg" alt="User Image">
                                                    <a class="users-list-name" href="#">Jane</a>
                                                    <span class="users-list-date">12 Jan</span>
                                                </li>
                                                <li>
                                                    <img src="dist/img/user6-128x128.jpg" alt="User Image">
                                                    <a class="users-list-name" href="#">John</a>
                                                    <span class="users-list-date">12 Jan</span>
                                                </li>
                                                <li>
                                                    <img src="dist/img/user2-160x160.jpg" alt="User Image">
                                                    <a class="users-list-name" href="#">Alexander</a>
                                                    <span class="users-list-date">13 Jan</span>
                                                </li>
                                                <li>
                                                    <img src="dist/img/user5-128x128.jpg" alt="User Image">
                                                    <a class="users-list-name" href="#">Sarah</a>
                                                    <span class="users-list-date">14 Jan</span>
                                                </li>
                                                <li>
                                                    <img src="dist/img/user4-128x128.jpg" alt="User Image">
                                                    <a class="users-list-name" href="#">Nora</a>
                                                    <span class="users-list-date">15 Jan</span>
                                                </li>
                                                <li>
                                                    <img src="dist/img/user3-128x128.jpg" alt="User Image">
                                                    <a class="users-list-name" href="#">Nadia</a>
                                                    <span class="users-list-date">15 Jan</span>
                                                </li>
                                            </ul>
                                            <!-- /.users-list -->
                                        </div>

                                    </div>
                                    <!--/.card -->
                                </div>
                                <!-- /.row -->
                            </div>
                            <!-- ./card-body -->
                            <div class="card-footer">
                                <div class="row">
                                    <div class="col-sm-3 col-6">
                                        <div class="description-block border-right">
                                            <span class="description-percentage text-success"><i class="fas fa-caret-up"></i> 17%</span>
                                            <h5 class="description-header">$35,210.43</h5>
                                            <span class="description-text">TOTAL REVENUE</span>
                                        </div>
                                        <!-- /.description-block -->
                                    </div>
                                    <!-- /.col -->
                                    <div class="col-sm-3 col-6">
                                        <div class="description-block border-right">
                                            <span class="description-percentage text-warning"><i class="fas fa-caret-left"></i> 0%</span>
                                            <h5 class="description-header">$10,390.90</h5>
                                            <span class="description-text">TOTAL COST</span>
                                        </div>
                                        <!-- /.description-block -->
                                    </div>
                                    <!-- /.col -->
                                    <div class="col-sm-3 col-6">
                                        <div class="description-block border-right">
                                            <span class="description-percentage text-success"><i class="fas fa-caret-up"></i> 20%</span>
                                            <h5 class="description-header">$24,813.53</h5>
                                            <span class="description-text">TOTAL PROFIT</span>
                                        </div>
                                        <!-- /.description-block -->
                                    </div>
                                    <!-- /.col -->
                                    <div class="col-sm-3 col-6">
                                        <div class="description-block">
                                            <span class="description-percentage text-danger"><i class="fas fa-caret-down"></i> 18%</span>
                                            <h5 class="description-header">1200</h5>
                                            <span class="description-text">GOAL COMPLETIONS</span>
                                        </div>
                                        <!-- /.description-block -->
                                    </div>
                                </div>
                                <!-- /.row -->
                            </div>
                            <!-- /.card-footer -->
                        </div>
                        <!-- /.card -->
                    </div>
                    <!-- /.col -->
                </div>
                <!-- /.row -->

                <!-- Main row -->
                <div class="row">
                    <!-- Left col -->
                    <div class="col-md-8">
                        <!-- MAP & BOX PANE -->
                        <div class="card">
                            <div class="card-header">
                                <h3 class="card-title">用户参加活动列表</h3>

                                <div class="card-tools">
                                    <button type="button" class="btn btn-tool" data-widget="collapse">
                                        <i class="fas fa-minus"></i>
                                    </button>
                                    <button type="button" class="btn btn-tool" data-widget="remove">
                                        <i class="fas fa-times"></i>
                                    </button>
                                </div>
                            </div>
                            <!-- /.card-header -->
                            <div class="card-body p-0">
                                <div class="d-md-flex">
                                    <div class="p-1 flex-fill" style="overflow: hidden">
                                        <!-- Map will be created here -->
                                        <div style="height: 325px; overflow: hidden">
                                            <div class="table-responsive">
                                                <table class="table m-0">
                                                    <thead>
                                                    <tr>
                                                        <th>活动编号</th>
                                                        <th>用户</th>
                                                        <th>状态</th>
                                                        <th>描述</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <tr>
                                                        <td><a href="pages/examples/invoice.html">OR9842</a></td>
                                                        <td>Call of Duty IV</td>
                                                        <td><span class="badge badge-success">Shipped</span></td>
                                                        <td>
                                                            <div class="sparkbar" data-color="#00a65a" data-height="20">90,80,90,-70,61,-83,63</div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td><a href="pages/examples/invoice.html">OR1848</a></td>
                                                        <td>Samsung Smart TV</td>
                                                        <td><span class="badge badge-warning">Pending</span></td>
                                                        <td>
                                                            <div class="sparkbar" data-color="#f39c12" data-height="20">90,80,-90,70,61,-83,68</div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td><a href="pages/examples/invoice.html">OR7429</a></td>
                                                        <td>iPhone 6 Plus</td>
                                                        <td><span class="badge badge-danger">Delivered</span></td>
                                                        <td>
                                                            <div class="sparkbar" data-color="#f56954" data-height="20">90,-80,90,70,-61,83,63</div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td><a href="pages/examples/invoice.html">OR7429</a></td>
                                                        <td>Samsung Smart TV</td>
                                                        <td><span class="badge badge-info">Processing</span></td>
                                                        <td>
                                                            <div class="sparkbar" data-color="#00c0ef" data-height="20">90,80,-90,70,-61,83,63</div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td><a href="pages/examples/invoice.html">OR1848</a></td>
                                                        <td>Samsung Smart TV</td>
                                                        <td><span class="badge badge-warning">Pending</span></td>
                                                        <td>
                                                            <div class="sparkbar" data-color="#f39c12" data-height="20">90,80,-90,70,61,-83,68</div>
                                                        </td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>


                                        </div>
                                        <div class="card-footer clearfix">
                                            <a href="javascript:void(0)" class="btn btn-sm btn-info float-left">发布新活动</a>
                                            <a href="javascript:void(0)" class="btn btn-sm btn-secondary float-right">查看所有参加活动用户</a>
                                        </div>
                                    </div>
                                    <div class="card-pane-right bg-success pt-2 pb-2 pl-4 pr-4">
                                        <div class="description-block mb-4">
                                            <h5 class="description-header">8390</h5>
                                            <span class="description-text">总阅读量</span>
                                        </div>
                                        <!-- /.description-block -->
                                        <div class="description-block mb-4">
                                            <h5 class="description-header">500</h5>
                                            <span class="description-text">参加人数</span>
                                        </div>
                                        <!-- /.description-block -->
                                        <div class="description-block">
                                            <h5 class="description-header">8000</h5>
                                            <span class="description-text">总订阅人数</span>
                                        </div>
                                        <!-- /.description-block -->
                                    </div><!-- /.card-pane-right -->
                                </div><!-- /.d-md-flex -->
                            </div>
                            <!-- /.card-body -->
                        </div>
                        <!-- /.card -->
                    </div>
                    <!-- /.col -->

                    <div class="col-md-4">
                        <div class="card direct-chat direct-chat-warning">
                            <div class="card-header">
                                <h3 class="card-title">最近用户消息</h3>

                                <div class="card-tools">
                                    <span data-toggle="tooltip" title="3 New Messages" class="badge badge-warning">3</span>
                                    <button type="button" class="btn btn-tool" data-widget="collapse"><i class="fas fa-minus"></i>
                                    </button>
                                    <button type="button" class="btn btn-tool" data-toggle="tooltip" title="查看所有">
                                        <i class="fas fa-comments"></i></button>
                                    <button type="button" class="btn btn-tool" data-widget="remove"><i class="fas fa-times"></i>
                                    </button>
                                </div>
                            </div>
                            <!-- /.card-header -->
                            <div class="card-body">
                                <!-- Conversations are loaded here -->
                                <div class="direct-chat-messages">
                                    <!-- Message. Default to the left -->
                                    <div class="direct-chat-msg">
                                        <div class="direct-chat-infos clearfix">
                                            <span class="direct-chat-name float-left">Alexander Pierce</span>
                                            <span class="direct-chat-timestamp float-right">23 Jan 2:00 pm</span>
                                        </div>
                                        <!-- /.direct-chat-infos -->
                                        <img class="direct-chat-img" src="dist/img/user1-128x128.jpg" alt="message user image">
                                        <!-- /.direct-chat-img -->
                                        <div class="direct-chat-text">
                                            Is this template really for free? That's unbelievable!
                                        </div>
                                        <!-- /.direct-chat-text -->
                                    </div>

                                </div>

                            </div>
                            <!-- /.card-footer-->
                        </div>
                    </div>
                    <!-- /.col -->
                </div>
                <!-- /.row -->
            </div><!--/. container-fluid -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
        <!-- Control sidebar content goes here -->
    </aside>
    <!-- /.control-sidebar -->

    <!-- Main Footer -->
    <#include "sub/footer.ftl">

</div>
<!-- ./wrapper -->

<!-- REQUIRED SCRIPTS -->
<!-- jQuery -->
<script src="/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- overlayScrollbars -->
<script src="/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
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
</body>
</html>
