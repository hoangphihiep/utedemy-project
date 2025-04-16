<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Unica - Đăng Ký</title>

    <link rel="stylesheet" href="/utedemyProject/views/Css/registerpage.css">

    
</head>
<body>
    <!-- Network background effect -->
    <div class="network-bg"></div>
    <div class="network-lines"></div>
    
    <div class="content-wrapper">
        <!-- Logo -->
        <div class="logo">
            unica
            <i class="fas fa-cloud"></i>
        </div>
        
        <!-- Registration Container -->
        <div class="registration-container">
            <div class="registration-header">
                <h2>ĐĂNG KÝ</h2>
            </div>
           <c:if test="${not empty alert}">
                <div class="alert-error">
                    <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" fill="currentColor" viewBox="0 0 16 16">
                        <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                        <path d="M7.002 11a1 1 0 1 1 2 0 1 1 0 0 1-2 0zM7.1 4.995a.905.905 0 1 1 1.8 0l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 4.995z"/>
                    </svg>
                    ${alert}
                </div>
                <c:remove var="alert" scope="session" />
            </c:if>
         
            <form id="registrationForm" action="/utedemyProject/user/register" method="post">
                <div class="form-group">
                    <label for="fullname">HỌ TÊN</label>
                    <input type="text" id="fullname" name="fullname" placeholder="Họ tên" required>
                </div>
                
                <div class="form-group">
                    <label for="email">EMAIL</label>
                    <input type="email" id="email" name="email" placeholder="Email" value="caylaco1981@gmail.com" required>
                </div>
                
                <div class="form-group">
                    <label for="phone">SỐ ĐIỆN THOẠI</label>
                    <div class="phone-input">
                      
                        <input type="tel" id="phone" name="phone" placeholder="091 234 56 78" value="091 234 56 78" required>
                    </div>
                </div>
                
                <div class="password-fields">
                    <div class="form-group">
                        <label for="password">MẬT KHẨU</label>
                        <div class="password-container">
                            <input type="password" id="password" name="password" placeholder="Mật khẩu" required>
                        </div>
                            <div id="passwordError" style="color: red; display: none; font-size: 0.9em; margin-top: 5px;"></div>
                    </div>
                    
                    <div class="form-group">
                        <label for="confirmPassword">NHẬP LẠI MẬT KHẨU</label>
                        <div class="password-container">
                            <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Mật khẩu" required>
                        </div>
                    </div>
                </div>
                
                <button type="submit" class="register-btn">ĐĂNG KÝ</button>
                
                <div class="form-footer">
                    <a href="/utedemyProject/views/user/forgotPassword.jsp">Quên mật khẩu?</a>
                    <a href="/utedemyProject/login">Đăng nhập</a>
                </div>
            </form>
        </div>
    </div>

    <script>

    </script>
</body>
</html>