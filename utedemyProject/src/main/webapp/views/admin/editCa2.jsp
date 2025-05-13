<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cập nhật khóa học</title>
</head>
<body>
    <div style="margin-top: 60px; text-align: center;">
        <h3 style="color: #2c3e50; margin-bottom: 30px;">Chỉnh sửa danh mục</h3>

        <!-- Form chỉnh sửa khóa học -->
        <form action="${pageContext.request.contextPath}/admin/edit2" method="post" 
              style="background-color: #fff; padding: 20px 30px; border-radius: 12px; margin-left: 600px; width: 400px; margin-bottom: 20px; box-shadow: 0 6px 15px rgba(0, 0, 0, 0.1); text-align: left;">
            <!-- Giữ id của loại khóa học không thay đổi -->
            <input type="hidden" name="id" value="${courseType.id}" />

            <div >
                <label for="courseTypeName" style="display: block; margin-bottom: 8px; font-weight: bold;">Tên danh mục:</label>
                <!-- Hiển thị tên loại khóa học cũ, người dùng có thể chỉnh sửa -->
                <input type="text" id="courseTypeName" name="courseTypeName" 
                       placeholder="Nhập tên loại khóa học" value="${courseType.courseTypeName}" required 
                       style="width: 100%; padding: 10px; margin-bottom: 16px; border: 1px solid #ccc; border-radius: 6px;" />
            </div>
            <button type="submit" style="padding: 10px 20px; background-color: #3498db; color: white; border: none; border-radius: 6px; cursor: pointer; font-size: 14px;">Cập nhật danh mục</button>
        </form>

        <!-- Hiển thị thông báo lỗi hoặc thành công -->
        <c:if test="${not empty message}">
            <p style="color: red; font-weight: bold;">${message}</p>
        </c:if>

        <!-- Form quay lại trang danh sách -->
        <form action="${pageContext.request.contextPath}/admin/category2" method="GET" 
              style="background-color: #fff; padding: 20px 30px; border-radius: 12px; width: 400px; margin-left: 600px; margin-bottom: 20px; box-shadow: 0 6px 15px rgba(0, 0, 0, 0.1); text-align: left;">
            <button type="submit" style="padding: 10px 20px; background-color: #3498db; color: white; border: none; border-radius: 6px; cursor: pointer; font-size: 14px;">Quay về danh sách</button>
        </form>
    </div>
</body>

</html>