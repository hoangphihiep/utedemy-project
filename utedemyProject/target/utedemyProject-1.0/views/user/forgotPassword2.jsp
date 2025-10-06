<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đặt lại mật khẩu</title>
    <link rel="stylesheet" href="/utedemyProject/views/Css/forgotPassword.css">
    <style nonce="2TAWjZIgxy7UGObaOZ4UyQ==">
        .password-container {
            position: relative;
        }
        
        .toggle-password {
            position: absolute;
            right: 10px;
            top: 13px;
            cursor: pointer;
            color: #6c757d;
        }
        
        .password-requirements {
            font-size: 12px;
            color: #6c757d;
            margin-bottom: 15px;
            padding-left: 5px;
        }
        
        .requirement {
            margin-bottom: 3px;
        }
        
        .valid {
            color: #28a745;
        }
        
        .invalid {
            color: #dc3545;
        }
    </style>
</head>
<body>
    <div class="forgot-password-container">
        <h1 class="title">ĐẶT LẠI MẬT KHẨU</h1>
        
        <% if(request.getAttribute("message") != null) { %>
        <div class="success-message" style="display: block;">
            <%= request.getAttribute("message") %>
        </div>
        <% } %>
        
        <% if(request.getAttribute("error") != null) { %>
        <div class="error-message" style="display: block;">
            <%= request.getAttribute("error") %>
        </div>
        <% } %>
        
        <form action="${pageContext.request.contextPath}/user/resetPassword" method="post" id="resetPasswordForm">

            <input type="hidden" name="email" value="${email}">
            
            <div class="label">MẬT KHẨU MỚI</div>
            <div class="password-container">
                <input type="password" class="input-field" name="newPassword" id="newPassword" placeholder="Nhập mật khẩu mới" required>
                <span class="toggle-password" onclick="togglePassword('newPassword')">👁️</span>
            </div>
            <div id="passwordError" class="error-message"></div>
            
            <div class="password-requirements">
                <div id="length" class="requirement">• Tối thiểu 8 ký tự</div>
                <div id="uppercase" class="requirement">• Ít nhất 1 chữ hoa</div>
                <div id="lowercase" class="requirement">• Ít nhất 1 chữ thường</div>
                <div id="number" class="requirement">• Ít nhất 1 số</div>
                <div id="special" class="requirement">• Ít nhất 1 ký tự đặc biệt</div>
            </div>
            
            <div class="label">XÁC NHẬN MẬT KHẨU</div>
            <div class="password-container">
                <input type="password" class="input-field" name="confirmPassword" id="confirmPassword" placeholder="Xác nhận mật khẩu mới" required>
                <span class="toggle-password" onclick="togglePassword('confirmPassword')">👁️</span>
            </div>
            <div id="confirmError" class="error-message"></div>
            
            <button type="submit" class="submit-btn">XÁC NHẬN</button>
        </form>
        
        <div class="footer">
            <a href="${pageContext.request.contextPath}/login" class="login-link">Quay lại đăng nhập</a>
        </div>
    </div>

    <script nonce="2TAWjZIgxy7UGObaOZ4UyQ==">
        function togglePassword(inputId) {
            const input = document.getElementById(inputId);
            if (input.type === "password") {
                input.type = "text";
            } else {
                input.type = "password";
            }
        }
        
        // Kiểm tra mật khẩu thời gian thực
        const newPassword = document.getElementById('newPassword');
        const confirmPassword = document.getElementById('confirmPassword');
        const passwordError = document.getElementById('passwordError');
        const confirmError = document.getElementById('confirmError');
        
        // Các yêu cầu mật khẩu
        const length = document.getElementById('length');
        const uppercase = document.getElementById('uppercase');
        const lowercase = document.getElementById('lowercase');
        const number = document.getElementById('number');
        const special = document.getElementById('special');
        
        newPassword.addEventListener('input', function() {
            const value = this.value;
            
            // Kiểm tra độ dài
            if (value.length >= 8) {
                length.classList.add('valid');
                length.classList.remove('invalid');
            } else {
                length.classList.add('invalid');
                length.classList.remove('valid');
            }
            
            // Kiểm tra chữ hoa
            if (/[A-Z]/.test(value)) {
                uppercase.classList.add('valid');
                uppercase.classList.remove('invalid');
            } else {
                uppercase.classList.add('invalid');
                uppercase.classList.remove('valid');
            }
            
            // Kiểm tra chữ thường
            if (/[a-z]/.test(value)) {
                lowercase.classList.add('valid');
                lowercase.classList.remove('invalid');
            } else {
                lowercase.classList.add('invalid');
                lowercase.classList.remove('valid');
            }
            
            // Kiểm tra số
            if (/[0-9]/.test(value)) {
                number.classList.add('valid');
                number.classList.remove('invalid');
            } else {
                number.classList.add('invalid');
                number.classList.remove('valid');
            }
            
            // Kiểm tra ký tự đặc biệt
            if (/[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/.test(value)) {
                special.classList.add('valid');
                special.classList.remove('invalid');
            } else {
                special.classList.add('invalid');
                special.classList.remove('valid');
            }
            
            // Nếu xác nhận mật khẩu đã nhập, kiểm tra khớp
            if (confirmPassword.value) {
                checkPasswordsMatch();
            }
        });
        
        // Kiểm tra mật khẩu khớp
        confirmPassword.addEventListener('input', checkPasswordsMatch);
        
        function checkPasswordsMatch() {
            if (newPassword.value !== confirmPassword.value) {
                confirmError.textContent = "Mật khẩu không khớp";
                confirmError.style.display = "block";
            } else {
                confirmError.style.display = "none";
            }
        }
        
        // Xác thực form trước khi gửi
        document.getElementById('resetPasswordForm').addEventListener('submit', function(event) {
            const passwordValue = newPassword.value;
            let isValid = true;
            
            // Kiểm tra các điều kiện mật khẩu
            if (passwordValue.length < 8 || 
                !/[A-Z]/.test(passwordValue) || 
                !/[a-z]/.test(passwordValue) || 
                !/[0-9]/.test(passwordValue) || 
                !/[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/.test(passwordValue)) {
                
                passwordError.textContent = "Mật khẩu không đáp ứng các yêu cầu";
                passwordError.style.display = "block";
                isValid = false;
            } else {
                passwordError.style.display = "none";
            }
            
            // Kiểm tra mật khẩu khớp
            if (newPassword.value !== confirmPassword.value) {
                confirmError.textContent = "Mật khẩu không khớp";
                confirmError.style.display = "block";
                isValid = false;
            } else {
                confirmError.style.display = "none";
            }
            
            if (!isValid) {
                event.preventDefault(); // Ngăn form submit nếu không hợp lệ
            }
        });
    </script>
</body>
</html>