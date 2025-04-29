<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<link rel="stylesheet" href="/utedemyProject/views/Css/header.css">
 <header class="header-container">
        <div class="top-bar">
            <div class="logo-section">
                <span class="unica-logo">unica</span>
            </div>
            <form action="${pageContext.request.contextPath}/user/search" method="post">
                <div class="search-box-inner">
                    <input type="text" name="keyword" placeholder="Tìm khóa học, giảng viên">
                    <button class="search-btn">
                        <i class="fas fa-search"></i>
                    </button>
                </div>
             </form>
           <c:if test="${not empty sessionScope.account}">
    <!-- User is logged in - show cart, favorites and avatar with dropdown -->
    <div class="user-menu-container">
      <a href="/cart"><i class="fas fa-shopping-cart cart-icon"></i></a>
      <a href="/favorites"><i class="fas fa-heart heart-icon"></i></a>
      
      <div class="dropdown">    
       <c:if test="${sessionScope.account.avatarUrl != ''}">
                                          <c:if test ="${sessionScope.account.avatarUrl.substring(0,5) != 'https' }">
                                             <c:url value="/image?fname=${sessionScope.account.avatarUrl}" var="imgUrl_avt"></c:url>
                                         </c:if>
                                  <c:if test ="${sessionScope.account.avatarUrl.substring(0,5) == 'https' }">
                                          <c:url value="${sessionScope.account.avatarUrl}" var="imgUrl_avt"></c:url>
                                 </c:if>
                         </c:if>                   
        <img src="${imgUrl_avt}" alt="User Avatar" class="avatar-img" id="avatarDropdown">
        
        <div class="dropdown-content" id="userDropdown">
          <a href="/learning">Vào học</a>
          <a href="/member">Hội viên</a>
          <a href="/activate-course">Kích hoạt khóa học</a>
          <a href="/business">Doanh nghiệp</a>
          <a href="${pageContext.request.contextPath}/user/InformationManagement">Cập nhật hồ sơ</a>
          <a href="/wallet">Ví của bạn</a>
          <a href="/utedemyProject/logout">Đăng xuất</a>
        </div>
      </div>
    </div>
  </c:if>
  
  <c:if test="${empty sessionScope.account}">
    <!-- User is not logged in - show login and signup buttons -->
    <a href="/cart"><i class="fas fa-shopping-cart cart-icon"></i></a>
    <a href="/favorites"><i class="fas fa-heart heart-icon"></i></a>
    <a href="/utedemyProject/login">
    <button class="login-btn">Đăng nhập</button>
    </a>
     <a href="/utedemyProject/user/register">
    <button class="signup-btn">Đăng ký</button>
        </a>
  </c:if>
        </div>

    </header>
<script src="/utedemyProject/views/Script/header.js"></script>