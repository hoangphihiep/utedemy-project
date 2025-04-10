<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Unica - Đăng Nhập</title>
   </head>
    <link rel="stylesheet" href="/utedemyProject/views/Css/loginpage.css">
</head>
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
        
        <!-- Login Container -->
        <div class="login-container">
            <div class="login-header">
                <h2>ĐĂNG NHẬP</h2>
            </div>
            <c:if test="${not empty alert}">
               <div class="alert-error">${alert}</div>
           </c:if>
            <c:if test="${param.error == 'empty'}">
              <div class="alert-error">
                  Vui lòng điền đầy đủ email và mật khẩu.
              </div>
            </c:if>

             <c:if test="${param.error == 'invalid'}">
                 <div class="alert-error">Định dạng email hoặc số điện thoại không hợp lệ.</div>
            </c:if>

              <c:if test="${param.error == 'wrong'}">
                  <div class="alert-error">Email/số điện thoại hoặc mật khẩu không đúng.</div>
              </c:if>            
            <form id="loginForm" action="/utedemyProject/login" method="post">
                <div class="form-group">
                    <label for="email">EMAIL / SỐ ĐIỆN THOẠI</label>
                    <input type="text" id="email" name="email" placeholder="Email / Số điện thoại" required>
                </div>
                
                <div class="form-group">
                    <label for="password">MẬT KHẨU</label>
                    <div class="password-container">
                        <input type="password" id="password" name="password" placeholder="Mật khẩu" required>
                        <span class="password-toggle" onclick="togglePassword()">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                                <path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z"/>
                                <path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z"/>
                            </svg>
                        </span>
                    </div>
                </div>
                
                <div class="remember-me">
                    <input type="checkbox" id="remember" name="remember">
                    <label for="remember">Ghi nhớ tài khoản</label>
                </div>
                
                <button type="submit" class="login-btn">ĐĂNG NHẬP</button>
                
                <div class="login-footer">
                    <a href="forgot-password.jsp">Quên mật khẩu?</a>
                    <div>
                        Bạn chưa có tài khoản? <a href="register.jsp">Đăng ký ngay</a>
                    </div>
                </div>
            </form>
           
        </div>
    </div>

    <script>
        // Toggle password visibility
        function togglePassword() {
            const passwordField = document.getElementById('password');
            const passwordToggle = document.querySelector('.password-toggle');
            
            if (passwordField.type === 'password') {
                passwordField.type = 'text';
                passwordToggle.innerHTML = `
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                        <path d="M13.359 11.238C15.06 9.72 16 8 16 8s-3-5.5-8-5.5a7.028 7.028 0 0 0-2.79.588l.77.771A5.944 5.944 0 0 1 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.134 13.134 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755-.165.165-.337.328-.517.486l.708.709z"/>
                        <path d="M11.297 9.176a3.5 3.5 0 0 0-4.474-4.474l.823.823a2.5 2.5 0 0 1 2.829 2.829l.822.822zm-2.943 1.299.822.822a3.5 3.5 0 0 1-4.474-4.474l.823.823a2.5 2.5 0 0 0 2.829 2.829z"/>
                        <path d="M3.35 5.47c-.18.16-.353.322-.518.487A13.134 13.134 0 0 0 1.172 8l.195.288c.335.48.83 1.12 1.465 1.755C4.121 11.332 5.881 12.5 8 12.5c.716 0 1.39-.133 2.02-.36l.77.772A7.029 7.029 0 0 1 8 13.5C3 13.5 0 8 0 8s.939-1.721 2.641-3.238l.708.709zm10.296 8.884-12-12 .708-.708 12 12-.708.708z"/>
                    </svg>
                `;
            } else {
                passwordField.type = 'password';
                passwordToggle.innerHTML = `
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                        <path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z"/>
                        <path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z"/>
                    </svg>
                `;
            }
        }
        
        // Form validation
        document.getElementById('loginForm').addEventListener('submit', function(event) {
            event.preventDefault();
            
            const email = document.getElementById('email').value.trim();
            const password = document.getElementById('password').value.trim();
            
            if (email === '' || password === '') {
                alert('Vui lòng điền đầy đủ thông tin đăng nhập');
                return;
            }
            
            // In a real application, this would be an AJAX request or form submission
            // For now, we'll just simulate submission
            console.log('Form submitted:', { email, password });
            
            // Submit the form to the server
            this.submit();
        });
        
        }
    </script>
</body>
</html>