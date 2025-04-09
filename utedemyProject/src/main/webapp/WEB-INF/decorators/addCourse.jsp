<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/utedemyProject/views/Css/addCourse.css">
</head>
<body>
	<div class="header">
		<div class="header-left">
			<a href="#" class="back-button">&#9664;</a>
			<div class="header-title">Quay lại danh sách khóa học
				&nbsp;|&nbsp; Thành Thạo Excel Từ Cơ Bản Đến Nâng Cao</div>
		</div>
		<div class="header-status">
			<div class="status-tag new-tag">Mới</div>
			<div class="status-tag updating-tag">Đang cập nhật</div>
			<button class="save-button">Lưu</button>
		</div>
	</div>
	<div class="container">
		<div class="sidebar">
			<div class="sidebar-menu">
				<a href="/utedemyProject/teacher/addTarget" class="sidebar-item"
					style="display: inline-block; text-decoration: none;"> <span>Mục
						tiêu khóa học</span>
				</a> <a href="/utedemyProject/teacher/adBasicInformation"
					class="sidebar-item"
					style="display: inline-block; text-decoration: none;"> <span>Thông
						tin cơ bản</span>
				</a> <a href="/utedemyProject/teacher/add" class="sidebar-item"
					style="display: inline-block; text-decoration: none;"> <span>Danh
						sách bài học</span>
				</a>
			</div>
			<button class="submit-button">Gửi duyệt</button>
		</div>
		<div class="main-content">
			<sitemesh:write property="body" />
		</div>
	</div>
</body>
</html>