<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <!-- Table -->
    <div style="background-color: #ffffff; padding: 20px; border: 1px solid #ddd; border-radius: 8px; margin-left: 0px; margin-top: 0px">
        <a href="<c:url value='/teacher/addCoupon'/>" style="background-color: #0071c2; margin-top: 20px; margin-right: 20px; 
        color: white; padding: 10px 20px; border: none; border-radius: 5px; font-size: 14px; cursor: pointer; float: right; 
        display: inline-block; text-decoration: none;">Thêm giảm giá mới</a>
		<section style="background-color: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); margin-top: 70px;">
			<table id="reservationTable" style="width: 100%; border-collapse: collapse;">
	            <thead>
	                <tr style="background-color: #f8f9fa; border-bottom: 1px solid #ddd;">
	                    <th style="padding: 10px; text-align: left;">STT</th>
	                    <th style="padding: 10px; text-align: left;">Mã</th>
	                    <th style="padding: 10px; text-align: left;">Giảm giá</th>
	                    <th style="padding: 10px; text-align: left;">Loại</th>
	                    <th style="padding: 10px; text-align: left;">Ngày bắt đầu</th>
	                    <th style="padding: 10px; text-align: left;">Ngày kết thúc</th>
	                    <th style="padding: 10px; text-align: left;">Số lượng</th>
	                    <th style="padding: 10px; text-align: left;">Áp dụng</th>
	                    <th style="padding: 10px; text-align: left;">Thao tác</th>
	                </tr>
	            </thead>
	            <tbody>
				    <c:forEach items="${listDiscount}" var="discount" varStatus="STT">
				        <tr style="border-bottom: 1px solid #ddd;">
				            <td style="padding: 10px;">${STT.index+1}</td>
				            <td style="padding: 10px;">${discount.disCode}</td>
				            <td style="padding: 10px;">${discount.decreasedFee}</td>
				            <td style="padding: 10px;">${discount.type}</td>
				            <td style="padding: 10px;">${discount.startTime}</td>
				            <td style="padding: 10px;">${discount.endTime}</td>
				            <td style="padding: 10px;">${discount.codeAmount}</td>
				            <td style="padding: 10px;">
				                <c:forEach var="course" items="${discount.appliedCourses}" varStatus="courseStatus">
				                    ${course.courseName}${!courseStatus.last ? ', ' : ''}
				                </c:forEach>
				            </td>
				            <td style="padding: 10px;">
				                <a href="<c:url value='/teacher/editCoupon?id=${discount.id}'/>" style="background-color: #007bff; color: white; border: none; padding: 5px 10px; border-radius: 4px; cursor: pointer; display: inline-block; text-decoration: none; margin-right: 5px;">✏️ Sửa</a>
				            </td>
				        </tr>
				    </c:forEach>
				</tbody>
	        </table>
		</section>
        
    </div>

</body>
</html>
