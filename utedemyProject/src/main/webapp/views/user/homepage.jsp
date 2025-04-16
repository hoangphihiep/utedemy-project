<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Unica - Học trực tuyến</title>
    <link rel="stylesheet" href="styles.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="/utedemyProject/views/Css/homepage.css">
    <script src="/utedemyProject/views/Script/homepage.js"></script>  
</head>
<body>
    <header class="header-container">
        <div class="top-bar">
            <div class="logo-section">
                <a href="#" class="unica-logo">
					<img src="https://unica.vn/media/img/logo-unica.svg" alt="Unica Logo" class="logo-circle" id="logoImg">
				</a>
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
            
            <div class="search-box">
                <div class="search-box-inner">
                    <input type="text" placeholder="Tìm khóa học, giảng viên">
                    <button class="search-btn">
                        <i class="fas fa-search"></i>
                    </button>
                </div>
            </div>
            
            <div class="user-actions">
                <span>Doanh nghiệp</span>
                <span>Hội viên</span>
                <i class="fas fa-shopping-cart cart-icon"></i>
                <button class="login-btn">Đăng nhập</button>
                <button class="signup-btn">Đăng ký</button>
            </div>
        </div>

    </header>

    <main>
    
        <section class="hero-banner-slider" aria-label="Course Promotions">
            <div class="slider-container">
                <div class="slider-content">
                    <!-- Existing slider items with added accessibility -->
                    <div class="slider-item" role="group" aria-roledescription="slide">
                        <!-- Existing content with semantic improvements -->
                    </div>
                </div>
                <div class="slider-controls">
                    <button class="slider-prev" aria-label="Previous Slide">&#10094;</button>
                    <button class="slider-next" aria-label="Next Slide">&#10095;</button>
                </div>
                <div class="slider-dots" role="tablist"></div>
            </div>
        </section>
        <nav class="category-menu">
            <div class="category-menu-inner">
                <ul>
                    <li><a href="#office-it">Tin học văn phòng</a></li>
                    <li><a href="#design">Thiết kế</a></li>
                    <li><a href="#languages">Ngoại ngữ</a></li>
                    <li><a href="#marketing">Marketing</a></li>
                    <li><a href="#finance">Tài chính kế toán</a></li>
                    <li><a href="#technology">Công nghệ</a></li>
                    <li><a href="#health-beauty">Sức khỏe - Làm đẹp</a></li>
                    <li><a href="#family">Hôn nhân gia đình</a></li>
                    <li><a href="#books">Sách hay nên đọc</a></li>
                    <li><a href="#inhouse">Đào tạo inhouse</a></li>
                </ul>
            </div>
        </nav>

        <section class="top-courses" aria-labelledby="bestseller-title"> 
            <h3 id="bestseller-title">TOP BÁN CHẠY</h3>
            <div id="bestseller-courses" class="course-grid" aria-live="polite"></div>
          </section>

          <section class="top-courses" aria-labelledby="today-sale-title">
            <h3 id="today-sale-title">SIÊU ƯU ĐÃI HÔM NAY</h3>
            <div id="today-sale-courses" class="course-grid" aria-live="polite"></div>
          </section>

       <section class="topics-of-interest" aria-labelledby="topics-title">
    <h3 id="topics-title">CHỦ ĐỀ CÓ THỂ BẠN QUAN TÂM</h3>
    <div class="topic-tags" role="list">
        <a href="#office-it" role="listitem" class="topic-tag">Tin học văn phòng</a>
        <a href="#design" role="listitem" class="topic-tag">Thiết kế</a>
        <a href="#languages" role="listitem" class="topic-tag">Ngoại ngữ</a>
        <a href="#marketing" role="listitem" class="topic-tag">Marketing</a>
        <a href="#finance" role="listitem" class="topic-tag">Tài chính kế toán</a>
        <a href="#technology" role="listitem" class="topic-tag">Công nghệ</a>
        <a href="#health-beauty" role="listitem" class="topic-tag">Sức khỏe - Làm đẹp</a>
        <a href="#family" role="listitem" class="topic-tag">Hôn nhân gia đình</a>
        <a href="#books" role="listitem" class="topic-tag">Sách hay nên đọc</a>
        <a href="#inhouse" role="listitem" class="topic-tag">Đào tạo inhouse</a>
    </div>
</section>

        <section class="featured-instructors" aria-labelledby="instructors-title">
            <h3 id="instructors-title">GIẢNG VIÊN TIÊU BIỂU</h3>
            <div class="instructors-container" aria-live="polite"></div>
        </section>

        <section class="instructor-recruitment hero" aria-labelledby="recruitment-title">
            <div class="hero-content">
                <h2 id="recruitment-title">Trở thành Giảng viên Unica</h2>
                <p>Giúp mọi người trở nên tốt hơn - bao gồm cả chính bạn</p>
                <button class="btn btn-register" aria-label="Register as Instructor">Đăng ký ngay</button>
            </div>
            <div class="hero-image">
                <img src="/api/placeholder/500/400" alt="Instructor Illustration">
            </div>
        </section>
    </main>

    <footer class="footer" role="contentinfo">
        <div class="footer-content">
            <div class="footer-section">
                <h4>Về Unica</h4>
                <nav>
                    <ul>
                        <li><a href="#">Giới thiệu về Unica</a></li>
                        <li><a href="#">Hướng dẫn sử dụng</a></li>
                        <li><a href="#">Kích hoạt khóa học</a></li>
                        <li><a href="#">Góc chia sẻ</a></li>
                    </ul>
                </nav>
            </div>
            <div class="footer-section">
                <h4>Hợp tác</h4>
                <nav>
                    <ul>
                        <li><a href="#">Đăng ký giảng viên</a></li>
                        <li><a href="#">Giải pháp e-learning</a></li>
                        <li><a href="#">Đào tạo doanh nghiệp</a></li>
                        <li><a href="#">Đào tạo Inhouse</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </footer>
</body>
</html>