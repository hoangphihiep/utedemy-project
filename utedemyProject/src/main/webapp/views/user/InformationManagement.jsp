<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Registration Form</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/Css/InformationManagement.css">
    <script src="${pageContext.request.contextPath}/views/Script/InformationManagement.js"></script>
</head>
<body>
    <div class="form-container">
        <div class="header-actions">
            <button class="delete-btn">🗑️</button>
            <button class="history-btn">↺</button>
        </div>
        
        <form action="${pageContext.request.contextPath}/user/InformationManagement" method="post" enctype="multipart/form-data">
        <input type="file" id="profileImageInput" name="profileImage" style="display: none;" accept="image/*">
        <div class="profile-container">
        	<c:if test="${user.avatarUrl.substring(0,5) != 'https' }">
			      <c:url value="/image?fname=${user.avatarUrl}" var="imgUrl"></c:url>
			  </c:if> 
			  <c:if test="${user.avatarUrl.substring(0,5) == 'https' }">
			      <c:url value="${user.avatarUrl }" var="imgUrl"></c:url>
			  </c:if>
			  <img id="previewImage" height="150" width="200" src="${imgUrl}" />
    		<div class="profile-image">
        		Unica
        		<div class="camera-icon">📷</div>
    		</div>
		</div>
        
        <!-- Hiển thị thông báo nếu có -->
        <c:if test="${not empty message}">
            <p style="color: green;">${message}</p>
        </c:if>
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>


            <div class="form-group">
                <label class="required" for="fullname">HỌ TÊN</label>
                <input type="text" id="fullname" name="fullname" value="${user.fullname}" required />
            </div>
            
            <div class="form-group">
                <label class="required" for="email">EMAIL</label>
                <div class="input-group">
                    <input type="email" id="email" name="email" value="${user.email}" required />
                </div>
            </div>
            
            <div class="form-group">
                <label class="required" for="phone">SỐ ĐIỆN THOẠI</label>
                <div class="input-group">
                    <input type="tel" id="phone" name="phone" class="phone-input" value="${user.phoneNumber}" required />
                </div>
            </div>
            
            <div class="form-group">
                <label class="required" for="address">ĐỊA CHỈ</label>
                <div class="input-group">
                    <input type="text" id="address" name="address" value="${user.address}" required />
                </div>
            </div>
            
            <div class="dropdown-container">
                <div class="form-group">
                    <label class="required" for="birthdate">NGÀY SINH</label>
                    <input type="date" id="birthdate" name="birthdate" value="${user.dateOfBirth}" required />
                </div>
                
                <div class="form-group">
                    <label class="required" for="gender">GIỚI TÍNH</label>
                    <select id="gender" name="gender" class="gender-select" required>
                        <option value="Nam" ${user.gender == 'Nam' ? 'selected' : ''}>Nam</option>
                        <option value="Nữ" ${user.gender == 'Nữ' ? 'selected' : ''}>Nữ</option>
                    </select>
                </div>
            </div>
            
            <div class="checkbox-container">
                <input type="checkbox" id="skip" name="skip">
                <label for="skip">Bỏ qua, tôi sẽ cập nhật sau</label>
            </div>
            
            <div class="action-buttons">
                <div class="left-buttons">
                    <button type="submit" class="btn btn-primary">CẬP NHẬT</button>
                    <button type="button" class="btn btn-secondary">Đổi mật khẩu</button>
                </div>
                <button type="button" class="btn btn-primary">TRUY CẬP</button>
            </div>
        </form>
    </div>
 
</body>
</html>