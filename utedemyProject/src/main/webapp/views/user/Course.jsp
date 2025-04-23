<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Unica - Học online mọi kỹ nằng từ chuyện gia hàng đầu</title>
    <link rel="stylesheet" href="/utedemyProject/views/Css/Course.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <script src="/utedemyProject/views/Script/Course.js"></script>
</head>

<header class="header-bar">
    <div class="logo-area">
        <a href="homepage.jsp" class="logo-link">
			<img src="https://photo.salekit.com/uploads/salekit_c196362f511b1291152d3dec9805e706/logo-unica-2.png" alt="Unica Logo" class="logo-circle" id="logoImg">
		</a>
        <span class="header-title">Tổng quan về thuế</span>
    </div>
    <div class="user-controls">
            <div class="user-progress" id="progressBtn">
                <i class="fas fa-chart-line"></i>
                <span>Tiến độ của bạn </span>
                <i class="fas fa-chevron-down"></i>
            </div>
        </div>
        
        <!-- Popup tiến độ -->
        <div class="progress-popup" id="progressPopup">
            <div class="progress-content">
                <div class="progress-header">Đã học ??/?? bài học</div>
                <div class="progress-text">Hoàn thành khóa học để nhận giấy chứng nhận.</div>
            </div>
        </div>
</header>

<body>
<div class="course-container">
    <!-- Phần video -->
    <div class="course-video-area">
        <iframe src="https://www.youtube.com/embed/jtFsZRHUyPE"
                frameborder="0"
                allowfullscreen>
        </iframe>

		<!-- Controller Video -->
		<div class="control-selection">
                <div class="right-controls">
                    <button class="btn-report"><i class="fas fa-exclamation-circle"></i> Báo lỗi</button>
                    <div class="autoplay-toggle">
                        <span>Autoplay</span>
                        <label class="switch">
                            <input type="checkbox" checked>
                            <span class="slider round"></span>
                        </label>
                    </div>
                    <button class="btn-prv"><i class="fas fa-chevron-left"> Bài Trước</i></button>
                    <button class="btn-next">Bài sau <i class="fas fa-chevron-right"></i></button>
				</div>
		</div>
		
		<!-- Controller Video -->
		<h2>Bài 1: bla bla bla blu blu blu ble ble ble....</h2>

        <!-- Tabs -->
        <div class="course-tabs">
		  <div class="tab-menu">
		    <div class="active" data-tab="overview">Tổng quan</div>
		    <div data-tab="discussion">Thảo luận</div>
		    <div data-tab="reviews">Đánh giá</div>
		  </div>
		
		  <div class="tab-content active" data-content="overview">
		    <h3>Giới thiệu khóa học</h3>
		    <p>...</p>
		    <h3>Giảng viên</h3>
		    <p>...</p>
		  </div>
		
		  <div class="tab-content" data-content="discussion">
		    <h3>Thảo luận</h3>
		    <p>Chưa có nội dung thảo luận</p>
		  </div>
		
		  <div class="tab-content" data-content="reviews">
		    <h3>Đánh giá</h3>
		    <p>...</p>
		  </div>
		</div>

    </div>

    <!-- Phần sidebar -->
<div class="course-sidebar">
    <h3>Nội dung khóa học</h3>

    <div class="lesson-group">
        <div class="lesson-section">
            <div class="lesson-group-title" onclick="toggleLesson(this)">
                Phần 1: Tổng quan và cài đặt ứng dụng khai thuế
                <span class="arrow">&#9660;</span>
            </div>
            <div class="lesson-list">
                <div class="lesson-item active">
                    	<span>Bài 1: Giới thiệu khóa học</span><span>04:47</span>
                </div>
                <div class="lesson-item">
                    <span>Bài 2: Cài đặt phần mềm hỗ trợ kê khai thuế</span><span>11:48</span>
                </div>
            </div>
        </div>

        <div class="lesson-section">
            <div class="lesson-group-title" onclick="toggleLesson(this)">
                Phần 2: Thiết lập hệ thống và sửa lỗi
                <span class="arrow">&#9654;</span>
            </div>
            <div class="lesson-list" style="display: none;">
                <div class="lesson-item">
                    <span>Bài 3: Sửa lỗi phần mềm</span><span>05:26</span>
                </div>
                <div class="lesson-item">
                    <span>Bài 4: Khai báo thông tin cho phần mềm HTKK</span><span>16:00</span>
                </div>
            </div>
        </div>

        <div class="lesson-section">
            <div class="lesson-group-title" onclick="toggleLesson(this)">
                Phần 3: Công cụ hỗ trợ và quản lý dữ liệu
                <span class="arrow">&#9654;</span>
            </div>
            <div class="lesson-list" style="display: none;">
                <div class="lesson-item">
                    <span>Bài 5: Tìm hiểu về năm tài chính</span><span>11:51</span>
                </div>
                <div class="lesson-item">
                    <span>Bài 6: Cài đặt các công cụ hỗ trợ</span><span>07:07</span>
                </div>
                <div class="lesson-item">
                    <span>Bài 7: Định dạng lại ngày, tháng</span><span>10:15</span>
                </div>
                <div class="lesson-item">
                    <span>Bài 8: Sao lưu phục hồi dữ liệu</span><span>04:58</span>
                </div>
            </div>
        </div>
    </div>
</div>

</div>

</body>

</html>
