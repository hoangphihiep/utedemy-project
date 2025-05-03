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
<body>
	<div class="Pannel">
		<p>The member: ${Users}</p>
		<div>
			<a href="#" class="border-link">Khoa học</a> <a href="Profile.jsp">Ho
				so ca nhan</a> <a href="LikePage.jsp">Yeu thich</a> <a
				href="Explore.jsp">Kham pha</a> <a href="Experience.jsp">Trainghiem</a>
		</div>
	</div>
	<div>
		<select>
			<option>--Sắp xếp theo--</option>
			<option>Moi nhat</option>
			<option>Cu nhat</option>
			<option>Gia tang dan</option>
			<option>Gia giam dan</option>
		</select> <select>
			<option>--Sắp xếp theo--</option>
			<option>Moi nhat</option>
			<option>Cu nhat</option>
			<option>Gia tang dan</option>
			<option>Gia giam dan</option>
		</select> <select>
			<option>--Sắp xếp theo--</option>
			<option>Moi nhat</option>
			<option>Cu nhat</option>
			<option>Gia tang dan</option>
			<option>Gia giam dan</option>
		</select> <select>
			<option>--Sắp xếp theo--</option>
			<option>Moi nhat</option>
			<option>Cu nhat</option>
			<option>Gia tang dan</option>
			<option>Gia giam dan</option>
		</select> <input type="text" placeholder="Tim kiem khoa hoc...">
		<button>Tim</button>
	</div>
	<c:forEach var="course" items="${myCourseList}">
		<div class="myCourseList">
			<div class="CourseSet">
				<div>
					<h2>${course.courseName}</h2>
					<c:set var="a" value="images/${course.courseDetail.courseImage}" />
					<img src="${pageContext.request.contextPath}/${a}" alt="Picture">
				</div>
				<div>
					<button>->Vao hoc ngay</button>
					<span>10%</span>
				</div>
			</div>
	</c:forEach>

	<%-- <c:forEach var="c" items="${CourseDetails}">
		<c:set var="a" value="images/${c.courseImage}" />
		<img src="${pageContext.request.contextPath}/${a}" alt="Picture"
			width="200" />
	</c:forEach> --%>
</body>
</body>
</html>
