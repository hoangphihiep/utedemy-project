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
    <style>
        .error-message {
            color: red;
            font-size: 0.9em;
            margin-top: 5px;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <div class="header-actions">
            <button class="delete-btn">🗑️</button>
            <button class="history-btn">↺</button>
        </div>

        <form action="${pageContext.request.contextPath}/user/InformationManagement" method="post" enctype="multipart/form-data">
            <div class="profile-container">
                <div class="profile-image-container">
                    <div class="profile-image" id="profileImage">
                        <c:if test="${not empty user.avatarUrl}">
                            <c:url value="/image?fname=${user.avatarUrl}" var="imgUrl"></c:url>
                            <img id="previewImage" src="${imgUrl}" alt="Ảnh hồ sơ">
                        </c:if>
                        <c:if test="${empty user.avatarUrl}">
                            <div class="profile-placeholder">
                                <i class="profile-icon">👤</i>
                            </div>
                        </c:if>
                    </div>
                    <div class="upload-overlay">
                        <i class="upload-icon">📷</i>
                    </div>
                </div>

                <div class="upload-controls">
                    <label for="images1" class="custom-file-upload">
                        <i class="upload-button-icon">📂</i> Chọn ảnh
                    </label>
                    <input type="file" id="images1" name="profileImage" accept="image/*" onchange="previewFile()">
                </div>
            </div>

            <!-- Hiển thị thông báo thành công nếu có -->
            <c:if test="${not empty message}">
                <p style="color: green;">${message}</p>
            </c:if>

            <!-- Hiển thị thông báo lỗi chung nếu có -->
            <c:if test="${not empty error}">
                <p style="color: red;">${error}</p>
            </c:if>

            <div class="form-group">
                <label class="required" for="fullname">HỌ TÊN</label>
                <input type="text" id="fullname" name="fullname" value="${user.fullname}" required />
                <c:if test="${not empty errors}">
                    <c:forEach var="err" items="${errors}">
                        <c:if test="${err == 'Họ tên không được để trống.'}">
                            <p class="error-message">${err}</p>
                        </c:if>
                    </c:forEach>
                </c:if>
            </div>

            <div class="form-group">
                <label class="required" for="email">EMAIL</label>
                <div class="input-group">
                    <input type="email" id="email" name="email" value="${user.email}" required />
                </div>
                <c:if test="${not empty errors}">
                    <c:forEach var="err" items="${errors}">
                        <c:if test="${err == 'Email không được để trống.' || err == 'Email không đúng định dạng (ví dụ: example@domain.com).'}">
                            <p class="error-message">${err}</p>
                        </c:if>
                    </c:forEach>
                </c:if>
            </div>

            <div class="form-group">
                <label class="required" for="phone">SỐ ĐIỆN THOẠI</label>
                <div class="input-group">
                    <input type="tel" id="phone" name="phone"  value="${user.phoneNumber}" required />
                </div>
                <c:if test="${not empty errors}">
                    <c:forEach var="err" items="${errors}">
                        <c:if test="${err == 'Số điện thoại không được để trống.' || err == 'Số điện thoại phải đúng 10 số.'}">
                            <p class="error-message">${err}</p>
                        </c:if>
                    </c:forEach>
                </c:if>
            </div>

            <div class="form-group">
                <label class="required" for="address">ĐỊA CHỈ</label>
                <div class="input-group">
                    <input type="text" id="address" name="address" value="${user.address}" required />
                </div>
                <c:if test="${not empty errors}">
                    <c:forEach var="err" items="${errors}">
                        <c:if test="${err == 'Địa chỉ không được để trống.'}">
                            <p class="error-message">${err}</p>
                        </c:if>
                    </c:forEach>
                </c:if>
            </div>

            <div class="dropdown-container">
                <div class="form-group">
                    <label class="required" for="birthdate">NGÀY SINH</label>
                    <input type="date" id="birthdate" name="birthdate" value="${birthdate}" required />
                    <c:if test="${not empty errors}">
                        <c:forEach var="err" items="${errors}">
                            <c:if test="${err == 'Ngày sinh không được để trống.'}">
                                <p class="error-message">${err}</p>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </div>

                <div class="form-group">
                    <label class="required" for="gender">GIỚI TÍNH</label>
                    <select id="gender" name="gender" class="gender-select" required>
                        <option value="Nam" ${user.gender == 'Nam' ? 'selected' : ''}>Nam</option>
                        <option value="Nữ" ${user.gender == 'Nữ' ? 'selected' : ''}>Nữ</option>
                    </select>
                    <c:if test="${not empty errors}">
                        <c:forEach var="err" items="${errors}">
                            <c:if test="${err == 'Giới tính không được để trống.'}">
                                <p class="error-message">${err}</p>
                            </c:if>
                        </c:forEach>
                    </c:if>
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
				<button type="button" class="btn btn-primary" onclick="redirectToHomepage()">TRUY CẬP</button>


            </div>
        </form>
        
    </div>
    
    <script>
    document.addEventListener('DOMContentLoaded', function() {
        // Lấy nút TRUY CẬP
        const accessBtn = document.querySelector('.btn.btn-primary:last-child');

        // Thêm sự kiện click
        accessBtn.addEventListener('click', function() {
            // Lấy contextPath từ JSP
            const contextPath = "${pageContext.request.contextPath}"; // Lấy giá trị chính xác từ JSP
            
            // Chuyển hướng sang trang homepage.jsp
            window.location.href = contextPath + "/user/homepage";
        });
    });
</script>
    
</body>
</html>