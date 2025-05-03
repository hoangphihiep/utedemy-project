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
<link rel="stylesheet" href="/utedemyProject/views/Css/myCoursepage.css">
</head>
<body>
	<header class="main-header">
		<div class="logo">
			<img
				src="https://play-lh.googleusercontent.com/CRA6gH69WvUTBVLlBpZr1MICRayAi69w0pvi187ny5QBVFNEeCRKFqjo87pxmigxlUk=w512"
				alt="Logo Unica">
		</div>
		<div class="menu-item">Danh mục</div>
		<div class="searchBox">
			<input type="text" placeholder="Nhập từ khóa tìm kiếm...">
			<button>Tìm</button>
		</div>
		<div class="menu-item">
			<a href="#">Doanh nghiệp</a>
		</div>
		<div class="menu-item">
			<a href="#">Hội viên</a>
			<div class="avatar">
				<img src="" alt="Avatar">
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
