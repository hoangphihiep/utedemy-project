<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Unica - Khoa hoc cua minh</title>
</head>
<link rel="stylesheet" href="/utedemyProject/views/Css/mycourse.css">
</head>
<body>
	<header
		style="display: flex; align-items: center; padding: 10px; border-bottom: 1px solid #ddd;">
		<!-- Logo và Danh Mục -->
		<div style="display: flex; align-items: center; margin-right: 20px;">
			<img src="logo.png" alt="Unica"
				style="height: 30px; margin-right: 10px;"> <span
				style="font-weight: bold;">DANH MỤC</span>
		</div>

		<!-- Thanh Tìm kiếm -->
		<div style="flex: 1; position: relative;">
			<input type="text" placeholder="Tìm khóa học, giảng viên"
				style="width: 100%; padding: 8px 35px 8px 15px; border: 1px solid #ccc; border-radius: 20px;">
			<span
				style="position: absolute; right: 10px; top: 50%; transform: translateY(-50%);">
				🔍 </span>
		</div>

		<!-- Menu bên phải -->
		<div
			style="display: flex; align-items: center; gap: 15px; margin-left: 20px;">
			<a href="#">Doanh nghiệp</a> <a href="#">Hội viên</a>

			<form action="${pageContext.request.contextPath}/user/mycourse"
				method="post" style="margin: 0;">
				<button
					style="background-color: #2d8cf0; color: white; border: none; padding: 5px 15px; border-radius: 5px; cursor: pointer;">
					➔ Vào học</button>
			</form>

			<span>🤍</span>
			<spann>🛒</span> <img src="avatar.jpg" alt="Avatar"
				style="width: 30px; height: 30px; border-radius: 50%;">
		</div>
	</header>
	<!-- phuc -->
<body>
	<div class="header">
		<div class="search-box">
			<input type="text" placeholder="Tìm kiếm khóa học...">
			<button>Tìm kiếm</button>
		</div>
	</div>

	<div class="filter-bar">
		<button>Tất cả</button>
		<button>Excel</button>
		<button>Bán hàng</button>
		<button>Thuyết trình</button>
		<button>+ Danh mục</button>
	</div>
	<div class="course-container">
		<c:forEach var="c" items="${myCourseList}">
			<div class="course-card">
				<img src="${c.course.courseDetail.courseImage}" alt="Picture"
					style="width: 200px; height: auto;">
				<div class="content">
					<h3>c.course.teacher.fullName</h3>
					<c:if test="${not empty percentage}">
						<div class="progress-circle">${percent}%</div>
					</c:if>
					<div class="footer">
						<button>Vào học ngay</button>
						<div class="progress-circle">${percent}%</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>

	<div class="chatbox">Em có thể giúp gì cho anh chị ạ?</div>
</body>
</body>
</html>