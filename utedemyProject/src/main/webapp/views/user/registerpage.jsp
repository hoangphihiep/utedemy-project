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
           <c:if test="${param.error == 'password_mismatch'}">
       <div style="color: red; margin-bottom: 10px;">
        Mật khẩu và nhập lại mật khẩu không khớp.
         </div>
          </c:if>
          <c:if test="${param.error == 'invalid_password_format'}">
               <div style="color: red; margin-bottom: 10px;">
                  Mật khẩu không đúng định dạng. Vui lòng nhập ít nhất 8 ký tự gồm chữ hoa, chữ thường, số và ký tự đặc biệt.
              </div>
          </c:if>
          <c:if test="${param.error == 'invalid_email_format'}">
              <div style="color: red; margin-bottom: 10px;">
                      Định dạng email không hợp lệ.
              </div>
          </c:if>
          <c:if test="${param.error == 'email_exist'}">
            <div style="color: red; margin-bottom: 10px;">
              Email đã tồn tại. Vui lòng sử dụng một email khác.
           </div>
        </c:if>
        <c:if test="${param.error == 'phone_exist'}">
          <div style="color: red; margin-bottom: 10px;">
             Số điện thoại đã được sử dụng. Vui lòng nhập số khác.
         </div>
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
                    <a href="forgot-password.jsp">Quên mật khẩu?</a>
                    <a href="login.jsp">Đăng nhập</a>
                </div>
            </form>
        </div>
    </div>

    <script>
    function validateEmail(email) {
        // Đây là một regex kiểm tra định dạng email đơn giản
        const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return re.test(email);
    }
        // Form validation
       document.getElementById('registrationForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const fullname = document.getElementById('fullname').value.trim();
    const email = document.getElementById('email').value.trim();
    const phone = document.getElementById('phone').value.trim();
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirmPassword').value;
    const passwordError = document.getElementById('passwordError');
    const phonePattern = /^0\d{9}$/;

    // Reset previous error
    passwordError.style.display = "none";
    passwordError.textContent = "";

    if (fullname === '' || email === '' || phone === '' || password === '' || confirmPassword === '') {
        alert('Vui lòng điền đầy đủ thông tin đăng ký');
        return;
    }

    if (!validateEmail(email)) {
        alert('Email không hợp lệ');
        return;
    }

    if (!phonePattern.test(phone)) {
        alert('Số điện thoại không đúng định dạng. Vui lòng nhập theo định dạng: 091 234 56 78');
        return;
    }


    // Password format check
    if (
        password.length < 8 || 
        !/[A-Z]/.test(password) || 
        !/[a-z]/.test(password) || 
        !/[0-9]/.test(password) || 
        !/[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/.test(password)
    ) {
        passwordError.textContent = "Mật khẩu phải có ít nhất 8 ký tự, gồm chữ hoa, chữ thường, số và ký tự đặc biệt.";
        passwordError.style.display = "block";
        return;
    }

    if (password !== confirmPassword) {
        alert('Mật khẩu và nhập lại mật khẩu không khớp');
        return;
    }

    console.log('Form submitted:', { fullname, email, phone, password });

    this.submit();
});

    </script>
</body>
</html>