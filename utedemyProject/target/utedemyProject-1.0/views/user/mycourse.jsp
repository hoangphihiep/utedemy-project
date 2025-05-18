<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<body>
	<div style="background: #fff; padding: 20px; display: flex; justify-content: center; align-items: center; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);">
		<div style="display: flex; align-items: center; gap: 10px; justify-content: center; margin: 20px auto;">
			<input type="text" placeholder="Tìm kiếm khóa học..." style="padding: 10px; width: 400px; border: 1px solid #ccc; border-radius: 4px;">
			<button style="padding: 10px 20px; background: #4CAF50; color: white; border: none; border-radius: 4px; cursor: pointer;">Tìm kiếm</button>
		</div>
	</div>

	<!-- Thanh lọc -->
	<div style="display: flex; justify-content: center; flex-wrap: wrap; gap: 10px; margin: 10px 0 20px;">
		<button style="padding: 8px 16px; border: 1px solid #ccc; border-radius: 20px; background: white; cursor: pointer;">Tất cả</button>
		<button style="padding: 8px 16px; border: 1px solid #ccc; border-radius: 20px; background: white; cursor: pointer;">Excel</button>
		<button style="padding: 8px 16px; border: 1px solid #ccc; border-radius: 20px; background: white; cursor: pointer;">Bán hàng</button>
		<button style="padding: 8px 16px; border: 1px solid #ccc; border-radius: 20px; background: white; cursor: pointer;">Thuyết trình</button>
		<button style="padding: 8px 16px; border: 1px solid #ccc; border-radius: 20px; background: white; cursor: pointer;">+ Danh mục</button>
	</div>

	<!-- Danh sách khóa học -->
	<div style="display: grid; grid-template-columns: repeat(3, 1fr); gap: 20px; max-width: 780px; margin: 0 auto; padding: 20px; background-color: #f0f0f0; border: 1px solid #ccc; border-radius: 10px;">
		<c:forEach var="c" items="${myCourseList}">
			<div style="background: white; border-radius: 8px; overflow: hidden; box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1); display: flex; flex-direction: column;">
				<c:if test="${c.course.courseDetail.courseImage.substring(0,5) != 'https' }">
					<c:url value="/image?fname=${c.course.courseDetail.courseImage}" var="imgUrl"></c:url>
				</c:if>
				<c:if test="${c.course.courseDetail.courseImage.substring(0,5) == 'https' }">
					<c:url value="${c.course.courseDetail.courseImage}" var="imgUrl"></c:url>
				</c:if>

				<img src="${imgUrl}" alt="Picture" style="width: 100%; height: auto; display: block; object-fit: cover; box-shadow: inset 0 0 30px lightgrey; border-top-left-radius: 8px; border-top-right-radius: 8px;">

				<div style="padding: 12px; flex: 1; display: flex; flex-direction: column; justify-content: space-between;">
					<h3 style="margin: 0 0 8px 0; font-size: 16px;">${c.course.teacher.fullname}</h3>

					<c:if test="${not empty percentage}">
						<div style="width: 42px; height: 42px; border: 2px solid #ccc; border-radius: 50%; display: flex; align-items: center; justify-content: center; font-size: 13px; font-weight: bold; color: #333;">${percent}%</div>
					</c:if>

					<div style="padding: 10px 12px; display: flex; justify-content: space-between; align-items: center; border-top: 1px solid #eee;">
						<button onclick="goToLearn('${c.course.id}')" style="padding: 6px 12px; background: #4CAF50; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 13px;">Vào học ngay</button>
						
						<div style="width: 42px; height: 42px; border: 2px solid #ccc; border-radius: 50%; display: flex; align-items: center; justify-content: center; font-size: 13px; font-weight: bold; color: #333;">${percent}%</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>

	<!-- Chatbox -->
	<div style="position: fixed; bottom: 20px; right: 20px; background: #fff; border: 1px solid #ccc; border-radius: 50px; padding: 10px 20px; display: flex; align-items: center; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);">
		Em có thể giúp gì cho anh chị ạ?
	</div>
</body>
</html>