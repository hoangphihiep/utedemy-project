<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/utedemyProject/views/Css/searchcourse.css">
    <script src="/utedemyProject/views/Script/searchcourse.js"></script>
    
    <title>Unica - Kết quả tìm kiếm</title>
    
</head>
<body>
    <!-- Navbar -->
    <div class="navbar">
        <div class="logo">
            <img src="/api/placeholder/100/30" alt="Unica Logo" style="width: 100px; height: 30px;">
            <div class="category-btn">DANH MỤC</div>
        </div>
        
        <div class="search-box">
            <input type="text" placeholder="it" value="it">
            <button>🔍</button>
        </div>
        
        <div class="nav-links">
            <a href="#" class="nav-btn login-btn">Doanh nghiệp</a>
            <a href="#" class="nav-btn login-btn">Hội viên</a>
            <button class="nav-btn cta-btn">→ Vào học</button>
            <button class="icon-btn">❤️</button>
            <button class="icon-btn">🛒</button>
            <button class="icon-btn">👤</button>
        </div>
    </div>
    
    <!-- Main Content -->
    <div class="container">
        <div class="search-header">
            <h1>104 kết quả cho "it"</h1>
            
            <div class="filter-container">
                <button class="filter-btn">
                    <span>Bộ lọc</span>
                </button>
                
                <div class="sort-dropdown">
                    <button class="sort-btn">
                        <span>Sắp xếp theo</span>
                        <span>Học nhiều nhất</span>
                        <span>▼</span>
                    </button>
                </div>
            </div>
        </div>

        <!-- Nút hiển thị lại bộ lọc -->
        <button class="show-filter-btn">Hiển thị bộ lọc</button>

        <div class="content-wrapper">
            <!-- Sidebar Filters -->
            <div class="sidebar">
                <div class="filter-section">
                    <h3 class="filter-title">Đánh giá</h3>
                    <div class="rating-filter">
                        <label class="rating-item">
                            <input type="radio" name="rating">
                            <span class="star-rating">★ ★ ★ ★ ★</span>
                            <span>Từ 4.5 trở lên</span>
                        </label>
                        <label class="rating-item">
                            <input type="radio" name="rating">
                            <span class="star-rating">★ ★ ★ ★ ☆</span>
                            <span>Từ 4.0 trở lên</span>
                        </label>
                        <label class="rating-item">
                            <input type="radio" name="rating">
                            <span class="star-rating">★ ★ ★ ☆ ☆</span>
                            <span>Từ 3.5 trở lên</span>
                        </label>
                        <label class="rating-item">
                            <input type="radio" name="rating">
                            <span class="star-rating">★ ★ ★ ☆ ☆</span>
                            <span>Từ 3.0 trở lên</span>
                        </label>
                    </div>
                </div>
                
                <div class="filter-section">
                    <h3 class="filter-title">Thời lượng video</h3>
                    <div class="duration-filter">
                        <label class="duration-item">
                            <input type="checkbox" name="duration">
                            <span>0 đến 1 giờ (1)</span>
                        </label>
                        <label class="duration-item">
                            <input type="checkbox" name="duration">
                            <span>1 đến 3 giờ (26)</span>
                        </label>
                        <label class="duration-item">
                            <input type="checkbox" name="duration">
                            <span>3 đến 6 giờ (38)</span>
                        </label>
                        <label class="duration-item">
                            <input type="checkbox" name="duration">
                            <span>6 đến 17 giờ (37)</span>
                        </label>
                    </div>
                </div>
            </div>
            
            <!-- Course List -->
            <div class="course-list">
                <!-- Course 1 -->
                <div class="course-card">
                    <img src="/api/placeholder/240/150" alt="Guitar Course" class="course-image">
                    <div class="course-details">
                        <div>
                            <h3 class="course-title">Kỹ năng học guitar hiệu quả cho người mới bắt đầu</h3>
                            <p class="instructor">Lê Việt Dũng</p>
                            <div class="rating">
                                <span>4.3</span>
                                <div class="rating-stars">★★★★☆</div>
                                <span class="rating-count">(39)</span>
                            </div>
                            <p class="course-meta">Thời lượng: 2.9 giờ, 33 bài giảng</p>
                        </div>
                    </div>
                    <div class="price-container">
                        <span class="price">299.000đ</span>
                        <span class="original-price">600.000đ</span>
                    </div>
                </div>
                
                <!-- Course 2 -->
                <div class="course-card">
                    <img src="/api/placeholder/240/150" alt="Guitar Course" class="course-image">
                    <div class="course-details">
                        <div>
                            <h3 class="course-title">Học Độc tấu Guitar trong 36 ngày</h3>
                            <p class="instructor">Trịnh Minh Cường</p>
                            <div class="rating">
                                <span>4.5</span>
                                <div class="rating-stars">★★★★★</div>
                                <span class="rating-count">(67)</span>
                            </div>
                            <p class="course-meta">Thời lượng: 1.5 giờ, 42 bài giảng</p>
                        </div>
                    </div>
                    <div class="price-container">
                        <span class="price">199.000đ</span>
                        <span class="original-price">500.000đ</span>
                    </div>
                </div>
                
                <!-- Course 3 -->
                <div class="course-card">
                    <img src="/api/placeholder/240/150" alt="Guitar Course" class="course-image">
                    <div class="course-details">
                        <div>
                            <h3 class="course-title">Học đệm hát Guitar cùng Haketu</h3>
                            <p class="instructor">Hà Kế Tú</p>
                            <div class="rating">
                                <span>4.3</span>
                                <div class="rating-stars">★★★★☆</div>
                                <span class="rating-count">(217)</span>
                            </div>
                            <p class="course-meta">Thời lượng: 4.3 giờ, 53 bài giảng</p>
                        </div>
                    </div>
                    <div class="price-container">
                        <span class="price">199.000đ</span>
                        <span class="original-price">700.000đ</span>
                    </div>
                </div>
                
                <!-- Course 4 -->
                <div class="course-card">
                    <img src="/api/placeholder/240/150" alt="Guitar Course" class="course-image">
                    <div class="course-details">
                        <div>
                            <h3 class="course-title">Fingerstyle guitar cho người mới bắt đầu</h3>
                            <p class="instructor">Hoàng Phúc</p>
                            <div class="rating">
                                <span>4.1</span>
                                <div class="rating-stars">★★★★☆</div>
                                <span class="rating-count">(107)</span>
                            </div>
                            <p class="course-meta">Thời lượng: 3.5 giờ, 45 bài giảng</p>
                        </div>
                    </div>
                    <div class="price-container">
                        <span class="price">299.000đ</span>
                        <span class="original-price">800.000đ</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Chat Bubble -->
    <div class="chat-bubble">
        💬
        <span class="notification">1</span>
    </div>

    
    
</body>
</html>