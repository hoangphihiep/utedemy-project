<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
<link rel="stylesheet" href="/utedemyProject/views/Css/homepage.css">

 <header class="header-container">
        <div class="top-bar">
            <div class="logo-section">
                <span class="unica-logo">unica</span>
                <button class="category-btn">
                    <i class="fas fa-th-large"></i> DANH MỤC
                </button>
                <div class="menu-container">
                    <div class="primary-menu">
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-rocket"></i> Kinh Doanh
                            </div>
                            <i class="fas fa-chevron-right"></i>
                            <div class="submenu">
                                <div class="menu-item">Quản Trị Kinh Doanh</div>
                                <div class="menu-item">Kinh Doanh Online</div>
                                <div class="menu-item">Khởi Nghiệp</div>
                                <div class="menu-item">Quản Trị Nhân Sự</div>
                                <div class="menu-item">Thương mại điện tử</div>
                                <div class="menu-item">Bán hàng</div>
                            </div>
                        </div>
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-language"></i> Ngoại Ngữ
                            </div>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-paint-brush"></i> Thiết Kế
                            </div>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-lightbulb"></i> Kỹ Năng
                            </div>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-code"></i> Lập Trình & CNTT
                            </div>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-heartbeat"></i> Sức Khoẻ Và Làm Đẹp
                            </div>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-bullhorn"></i> Marketing
                            </div>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-desktop"></i> Tin Học Văn Phòng
                            </div>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-coffee"></i> Phong Cách Sống
                            </div>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-heart"></i> Hôn Nhân Và Gia Đình
                            </div>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-chart-line"></i> Tài Chính Kế Toán
                            </div>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-music"></i> Âm Nhạc
                            </div>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                    </div>
                </div>
            </div>
            <form action="${pageContext.request.contextPath}/user/search" method="post">
                <div class="search-box-inner">
                    <input type="text" name="keyword" placeholder="Tìm khóa học, giảng viên">
                    <button class="search-btn">
                        <i class="fas fa-search"></i>
                    </button>
                </div>
             </form>
            <div class="user-actions">
                <span>Doanh nghiệp</span>
                <span>Hội viên</span>
                <i class="fas fa-shopping-cart cart-icon"></i>
                <i class="fas fa-heart heart-icon"></i>
                <button class="login-btn">Đăng nhập</button>
                <button class="signup-btn">Đăng ký</button>
            </div>
        </div>

    </header>