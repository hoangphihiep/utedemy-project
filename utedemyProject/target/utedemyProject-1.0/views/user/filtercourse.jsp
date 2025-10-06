<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/utedemyProject/views/Css/searchcourse.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

<script nonce="2TAWjZIgxy7UGObaOZ4UyQ==" src="/utedemyProject/views/Script/searchcourse.js"></script>


<title>Unica - Kết quả tìm kiếm</title>

</head>
<body>
	<!-- Navbar -->
	<div class="navbar">
		<div class="logo">
			<img src="/api/placeholder/100/30" alt="Unica Logo"
				style="width: 100px; height: 30px;">
			<div class="category-btn">DANH MỤC</div>
		</div>

		<form action="${pageContext.request.contextPath}/user/filter"
			method="post">
			<input type="hidden" name="csrfToken" value="${csrfToken}" />
			<div class="search-box">
				<input type="text" name="keyword" placeholder="Search..."
					value="${keyWord}">
				<button type="submit">🔍</button>
			</div>
		</form>


		<div class="nav-links">
			<a href="#" class="nav-btn login-btn">Doanh nghiệp</a> <a href="#"
				class="nav-btn login-btn">Hội viên</a>
			<button class="nav-btn cta-btn">→ Vào học</button>
			<button class="icon-btn">❤️</button>
			<button class="icon-btn">🛒</button>
			<button class="icon-btn">👤</button>
		</div>
	</div>

	<!-- Main Content -->
	<div class="container">
		<div class="search-header">
			<h1>"${searchAmount}" kết quả cho "${keyWord}"</h1>

			<div class="filter-container">
				<button class="filter-btn">
					<span>Bộ lọc</span>
				</button>

				<div class="sort-dropdown">
					<!-- 					<button class="sort-btn">
						<span>Sắp xếp theo</span> <span>Học nhiều nhất</span> <span>▼</span>
					</button> -->
					<select class="sort-dropdown" name="sort" id="sort-select">
						<form action="${pageContext.request.contextPath}/user/search"
							method="post">
							<input type="hidden" name="csrfToken" value="${csrfToken}" />
							<select name="sortBy">
								<option value="highest-rated">Đánh giá cao nhất</option>
								<option value="most-popular">Học nhiều nhất</option>
								<!-- Thêm các tùy chọn khác -->
							</select>
							<button type="submit">Lọc</button>
						</form>

					</select>
				</div>
			</div>
		</div>

		<!-- Nút hiển thị lại bộ lọc -->
		<button class="show-filter-btn">Hiển thị bộ lọc</button>
		<div class="course-list">
			<c:forEach items="${CourseList}" var="i">
				<div class="course-card">
					<div class="course-details">
						<div>
							<h3 class="course-title">${i.courseName}</h3>
							<p class="instructor">${i.teacher.fullname}</p>
							<c:set var="reviewCount" value="0" scope="page" />
							<div class="rating">
								<c:set var="sum" value="0" />
								<c:set var="reviewCount" value="0" />

								<!-- Lặp qua Review để tính tổng điểm và đếm số lượng -->
								<c:forEach items="${Review}" var="r">
									<c:if test="${r.course.id == i.id}">
										<c:set var="sum" value="${sum + r.rate}" />
										<c:set var="reviewCount" value="${reviewCount + 1}" />
									</c:if>
								</c:forEach>
								<!-- Kiểm tra nếu có review -->
								<c:choose>
									<c:when test="${reviewCount > 0}">
										<!-- Tính trung bình -->
										<c:set var="average" value="${sum / reviewCount}" />

										<!-- Làm tròn: ví dụ 3.4 => 3; 3.5 => 4 -->
										<c:set var="roundedRating"
											value="${average - (average % 1) + (average % 1 ge 0.5 ? 1 : 0)}" />

										<!-- Hiển thị sao -->
										<span class="stars"> <c:forEach begin="1"
												end="${roundedRating}" var="star">
												<i class="fas fa-star" style="color: gold;"></i>
											</c:forEach>
										</span>

									</c:when>
									<c:otherwise>
										<span>No reviews yet</span>
									</c:otherwise>
								</c:choose>

								<!-- Hiển thị tổng số đánh giá -->
								<span class="rating-count">(${reviewCount})</span>
							</div>
							<img src="${i.courseDetail.courseImage}" alt="Picture"
								style="width: 200px; height: auto;">
							<!-- Cập nhật prevSectionId -->
						</div>
					</div>

					<div class="price-container">
						<!-- Display course price and original price -->
						<span class="price">${i.coursePrice}</span>
					</div>
				</div>
			</c:forEach>
		</div>
		<%-- 		<c:out value="${sessionScope.keyWord}" />
 --%>
		<form action="${pageContext.request.contextPath}/user/search"
			method="post">
			<input type="hidden" name="csrfToken" value="${csrfToken}" />
			<input type="hidden" name="keyword" value="${sessionScope.keyWord}" />
			<button type="submit"
				style="padding: 10px 20px; font-size: 16px; color: white; background-color: #007bff; border: none; border-radius: 15px; cursor: pointer; transition: background-color 0.3s, transform 0.2s;">
				Quay lại tìm kiếm</button>
		</form>
		<!-- Chat Bubble -->
		<div class="chat-bubble">
			💬 <span class="notification">1</span>
		</div>
</body>
</html>