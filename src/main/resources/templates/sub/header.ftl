<!-- Navbar -->
    <nav class="main-header navbar navbar-expand navbar-white navbar-light">

        <!-- Right navbar links -->
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" data-widget="control-sidebar" data-slide="true" href="#"><i
                        class="fas fa-th-large"></i></a>
            </li>
        </ul>
    </nav>
    <!-- /.navbar -->

    <!-- Main Sidebar Container -->
    <aside class="main-sidebar sidebar-dark-primary elevation-4">
        <!-- Brand Logo -->
        <a href="index3.html" class="brand-link">
            <span class="brand-text font-weight-light">${name}</span>
        </a>

        <!-- Sidebar -->
        <div class="sidebar">
            <!-- Sidebar user panel (optional) -->
            <div class="user-panel mt-3 pb-3 mb-3 d-flex">
                <div class="info">
                    <a href="/back/index.html" class="d-block">${user.name}</a>
                </div>
            </div>

            <!-- Sidebar Menu -->
            <nav class="mt-2">
                <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                    <!-- Add icons to the links using the .nav-icon class
                         with font-awesome or any other icon font library -->
                    <li class="nav-item has-treeview menu-open">
                        <a href="/back/menu.html" class="nav-link">
                            <i class="nav-icon fas fa-tachometer-alt"></i>
                            <p>
                                菜单管理
                            </p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/back/user.html" class="nav-link">
                            <i class="nav-icon fas fa-th"></i>
                            <p>
                                用户列表
                            </p>
                        </a>
                    </li>
                    <li class="nav-item has-treeview">
                        <a href="#" class="nav-link">
                            <i class="nav-icon fas fa-copy"></i>
                            <p>
                                消息设置
                                <i class="fas fa-angle-left right"></i>
                                <span class="badge badge-info right">4</span>
                            </p>
                        </a>
                        <ul class="nav nav-treeview">
                            <li class="nav-item">
                                <a href="/back/msgSubscribe.html" class="nav-link">
                                    <i class="far fa-circle nav-icon"></i>
                                    <p>关注回复</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="/back/msgKey.html" class="nav-link">
                                    <i class="far fa-circle nav-icon"></i>
                                    <p>消息关键字</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="/back/msgKeyPage.html" class="nav-link">
                                    <i class="far fa-circle nav-icon"></i>
                                    <p>关键字绑定</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="/back/msgPage.html" class="nav-link">
                                    <i class="far fa-circle nav-icon"></i>
                                    <p>图文消息回复</p>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item has-treeview">
                        <a href="#" class="nav-link">
                            <i class="nav-icon fas fa-chart-pie"></i>
                            <p>
                                素材维护
                                <i class="right fas fa-angle-left"></i>
                            </p>
                        </a>
                        <ul class="nav nav-treeview">
                            <li class="nav-item">
                                <a href="/back/staff-0.html" class="nav-link">
                                    <i class="far fa-circle nav-icon"></i>
                                    <p>图文素材</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="/back/staff-i-{0}.html" class="nav-link">
                                    <i class="far fa-circle nav-icon"></i>
                                    <p>图片素材</p>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item has-treeview">
                        <a href="/back/activity.html" class="nav-link">
                            <i class="nav-icon fas fa-tree"></i>
                            <p>
                                活动维护
                            </p>
                        </a>
                    </li>
                    <li class="nav-item has-treeview">
                        <a href="/back/user.html" class="nav-link">
                            <i class="nav-icon fas fa-edit"></i>
                            <p>
                                用户
                                <i class="fas fa-angle-left right"></i>
                            </p>
                        </a>
                        <ul class="nav nav-treeview">
                            <li class="nav-item">
                                <a href="/back/wxUsers.html" class="nav-link">
                                    <i class="far fa-circle nav-icon"></i>
                                    <p>用户列表</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="/back/wxBlackUsers.html" class="nav-link">
                                    <i class="far fa-circle nav-icon"></i>
                                    <p>黑名单</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="/back/wxAdmins.html" class="nav-link">
                                    <i class="far fa-circle nav-icon"></i>
                                    <p>特殊用户</p>
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </nav>
            <!-- /.sidebar-menu -->
        </div>
        <!-- /.sidebar -->
    </aside>