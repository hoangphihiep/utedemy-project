<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Giảng viên Dashboard</title>
    
</head>
<body>
    <header class="header">
        <div class="instructor-info">
            <div class="instructor-name">Giảng viên : Thảo Chu</div>
            <div class="instructor-id">ID: 580184</div>
        </div>
        <div class="support-info">
            <p>Hỗ trợ giảng viên</p>
            <p>HOTLINE: 0329802918</p>
            <p>EMAIL: teacher@unica.vn</p>
        </div>
    </header>

    <nav class="nav-menu">
        <ul>
            <li class="active">Bài giảng</li>
            <li>Học viên</li>
            <li>Học tập</li>
            <li>Coupon</li>
            <li>Đơn hàng</li>
            <li>Thu nhập</li>
            <li>Hồ sơ</li>
        </ul>
    </nav>

    <main class="content">
        <div class="content-header">
            <h1 class="content-title">Bài giảng của tôi</h1>
            <div style="display: flex;">
                <div class="search-bar">
                    <input type="text" class="search-input" placeholder="Nhập tên khóa học">
                    <button class="search-button">
                        <i class="fa fa-search"></i> Tìm kiếm
                    </button>
                </div>
                <div class="action-buttons">
                    <button class="btn btn-primary">
                        <i class="fa fa-plus"></i> Tạo khóa học
                    </button>
                </div>
            </div>
        </div>

        <div class="course-list">
            <div class="course-card">
                <div class="course-image">
                    800 x 450
                </div>
                <div class="course-info">
                    <div>
                        <h3 class="course-title">Kiếm Tiền Online 4.0</h3>
                        <span class="course-tag">Khóa học mới</span>
                    </div>
                    <div class="course-bottom">
                        <div class="progress-section">
                            <span class="progress-label">Hoàn thành khóa học</span>
                            <div class="progress-bar">
                                <div class="progress-fill"></div>
                            </div>
                        </div>
                        <div class="course-actions">
                            <a href="#">Link</a>
                            <a href="#">Sửa / Quản lý</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
</body>
</html>