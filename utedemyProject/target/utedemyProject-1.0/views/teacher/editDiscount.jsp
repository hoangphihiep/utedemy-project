<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<div style="background-color: #ffffff; padding: 20px; border: 1px solid #ddd; border-radius: 8px; margin: 20px; margin: 20px auto; max-width: 600px;">
    <h2 style="margin-bottom: 20px;">Chỉnh sửa mã giảm giá</h2>

    <form action="<c:url value='/teacher/editCoupon'/>" method="post">
        <!-- ID ẩn -->
        <input type="hidden" name="id" value="${discount.id}">

        <!-- Mã giảm giá -->
        <div style="margin-bottom: 15px;">
            <label for="disCode" style="display: block; margin-bottom: 5px; font-weight: bold;">Mã giảm giá:</label>
            <input type="text" id="disCode" name="disCode" required value="${discount.disCode}" style="width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px;">
        </div>

        <!-- Số tiền/phần trăm giảm -->
        <div style="margin-bottom: 15px;">
        	<c:set var="rawFee" value="${discount.decreasedFee}" />
			<c:set var="numericFee" value="${fn:replace(fn:replace(fn:replace(rawFee, 'VNĐ', ''), ',', ''), 'NEW_USER', '')}" />
			<c:set var="numericFee" value="${fn:replace(numericFee, 'LOYAL_CUSTOMER', '')}" />
			<c:set var="numericFee" value="${fn:trim(numericFee)}" />
            <label for="decreasedFee" style="display: block; margin-bottom: 5px; font-weight: bold;">Số tiền/phần trăm giảm:</label>
            <input type="text" id="decreasedFee" name="decreasedFee" required value="${numericFee}" style="width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px;">
        </div>

        <!-- Loại giảm giá -->
        <div style="margin-bottom: 15px;">
            <label for="type" style="display: block; margin-bottom: 5px; font-weight: bold;">Loại giảm giá:</label>
            <select id="type" name="type" required style="width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px;">
                <option value="PERCENTAGE" ${discount.type == 'PERCENTAGE' ? 'selected' : ''}>Phần trăm (%)</option>
                <option value="AMOUNTMONEY" ${discount.type == 'AMOUNTMONEY' ? 'selected' : ''}>Số tiền cố định (VNĐ)</option>
            </select>
        </div>

        <!-- Ngày bắt đầu -->
        <div style="margin-bottom: 15px;">
            <label for="startTime" style="display: block; margin-bottom: 5px; font-weight: bold;">Ngày bắt đầu:</label>
            <input type="date" id="startTime" name="startTime" required value="${discount.startTime}" style="width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px;">
        </div>

        <!-- Ngày kết thúc -->
        <div style="margin-bottom: 15px;">
            <label for="endTime" style="display: block; margin-bottom: 5px; font-weight: bold;">Ngày kết thúc:</label>
            <input type="date" id="endTime" name="endTime" required value="${discount.endTime}" style="width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px;">
        </div>

        <!-- Số lượng mã -->
        <div style="margin-bottom: 15px;">
            <label for="codeAmount" style="display: block; margin-bottom: 5px; font-weight: bold;">Số lượng mã:</label>
            <input type="number" id="codeAmount" name="codeAmount" required min="1" value="${discount.codeAmount}" style="width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px;">
        </div>

        <!-- Áp dụng cho khóa học -->
        <div style="margin-bottom: 15px;">
            <label style="display: block; margin-bottom: 5px; font-weight: bold;">Áp dụng cho khóa học:</label>
            <div style="max-height: 200px; overflow-y: auto; border: 1px solid #ddd; padding: 10px; border-radius: 4px;">
                <c:forEach items="${teacherCourses}" var="course">
                    <div style="margin-bottom: 5px;">
                        <c:set var="isSelected" value="false" />
                        <c:forEach items="${discount.appliedCourses}" var="selectedCourse">
                            <c:if test="${selectedCourse.id == course.id}">
                                <c:set var="isSelected" value="true" />
                            </c:if>
                        </c:forEach>
                        <input type="checkbox" id="course_${course.id}" name="appliedCourses" value="${course.id}" ${isSelected ? 'checked' : ''}>
                        <label for="course_${course.id}">${course.courseName} - ${course.coursePrice} VNĐ</label>
                    </div>
                </c:forEach>
            </div>
        </div>
		
		<!-- Điều kiện sử dụng mã giảm giá -->
		<div style="margin-bottom: 15px;">
		    <label style="display: block; margin-bottom: 5px; font-weight: bold;">Điều kiện sử dụng:</label>
		    <div>
		        <input type="checkbox" id="newUser" name="conditions" value="NEW_USER" ${fn:contains(discount.decreasedFee, 'NEW_USER') ? 'checked' : ''}>
		        <label for="newUser">Chỉ áp dụng cho người dùng mới</label>
		    </div>
		    <div>
		        <input type="checkbox" id="loyalCustomer" name="conditions" value="LOYAL_CUSTOMER" ${fn:contains(discount.decreasedFee, 'LOYAL_CUSTOMER') ? 'checked' : ''}>
		        <label for="loyalCustomer">Chỉ áp dụng cho khách hàng thân thiết</label>
		    </div>
		    <div>
		        <input type="checkbox" id="minOrder" name="conditions" value="MIN_ORDER_5" ${fn:contains(discount.decreasedFee, 'MIN_ORDER_5') ? 'checked' : ''}>
		        <label for="minOrder">Áp dụng cho đơn hàng từ 5.000.000đ trở lên</label>
		    </div>
		    <div>
		        <input type="checkbox" id="firstCourse" name="conditions" value="FIRST_COURSE" ${fn:contains(discount.decreasedFee, 'FIRST_COURSE') ? 'checked' : ''}>
		        <label for="firstCourse">Chỉ áp dụng cho người đăng ký khóa học đầu tiên</label>
		    </div>    
		</div>
        <!-- Nút gửi -->
        <div style="text-align: center; margin-top: 20px;">
            <button type="submit" style="background-color: #0071c2; color: white; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer;">Cập nhật mã giảm giá</button>
            <a href="<c:url value='/teacher/coupon'/>" style="background-color: #6c757d; color: white; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer; text-decoration: none; margin-left: 10px;">Hủy</a>
        </div>
    </form>
</div>