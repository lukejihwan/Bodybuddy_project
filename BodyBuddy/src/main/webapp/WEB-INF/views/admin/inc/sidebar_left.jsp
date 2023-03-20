<%@ page contentType="text/html;charset=UTF-8"%>
  <aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="../index.jsp" class="brand-link">
      <img src="/resources/admin/dist/img/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
      <span class="brand-text font-weight-light">바디바디</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
      <!-- Sidebar user panel (optional) -->
      <div class="user-panel mt-3 pb-3 mb-3 d-flex">
        <div class="image">
          <img src="/resources/admin/dist/img/user2-160x160.jpg" class="img-circle elevation-2" alt="User Image">
        </div>
        <div class="info">
          <a href="#" class="d-block">관리자</a>
        </div>
      </div>

      <!-- SidebarSearch Form -->
      <div class="form-inline">
        <div class="input-group" data-widget="sidebar-search">
          <input class="form-control form-control-sidebar" type="search" placeholder="Search" aria-label="Search">
          <div class="input-group-append">
            <button class="btn btn-sidebar">
              <i class="fas fa-search fa-fw"></i>
            </button>
          </div>
        </div>
      </div>

      <!-- Sidebar Menu -->
      <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
          <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
          <li class="nav-header">관리메뉴</li>    
          <li class="nav-item menu">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>
                입점업체관리
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="./index.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>헬스장</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="./index2.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>피트니스</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="./index3.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>요가</p>
                </a>
              </li>
            </ul>
          </li>
           
          <!-- 운동게시판 관리 -->
          <li class="nav-item open">
            <a href="#" class="nav-link ">
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>운동게시판 관리<i class="right fas fa-angle-left"></i></p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="/admin/exr/notice" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>정보게시판</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="/admin/product/list" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>루틴게시판</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="/admin/category/main" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>팁게시판</p>
                </a>
              </li>
            </ul>
          </li>
          
          <!-- 식단게시판 관리 -->
          <li class="nav-item open">
            <a href="#" class="nav-link ">
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>
                식단게시판 관리
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="/admin/diet/info_list" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>식단정보게시판</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="/admin/product/list" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>식단공유게시판</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="/admin/category/main" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>식단팁게시판</p>
                </a>
              </li>
            </ul>
          </li>
           
           <!-- 회원 관리 -->
          <li class="nav-item open">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>
                회원관리
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="./index.html" class="nav-link ">
                  <i class="far fa-circle nav-icon"></i>
                  <p>일반회원목록</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="./index2.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>업체회원목록</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="./index3.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>관리자목록</p>
                </a>
              </li>
            </ul>
          </li>
          
          <!-- 통계관리 --> 
          <li class="nav-item open">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>
                통계관리
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="./index.html" class="nav-link ">
                  <i class="far fa-circle nav-icon"></i>
                  <p>회원수 관리</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="./index2.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>입점업체 관리</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="./index3.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>매출관리</p>
                </a>
              </li>
            </ul>
          </li>
           
          
           
          <!-- 고객지원 -->
          <li class="nav-item open">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>
                고객센터
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="./index.html" class="nav-link ">
                  <i class="far fa-circle nav-icon"></i>
                  <p>1:1 문의 목록</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="./index2.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Q&A 목록</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="./index3.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>신고 목록</p>
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
 