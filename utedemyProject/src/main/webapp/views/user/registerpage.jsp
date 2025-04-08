<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
            
            <form id="registrationForm" action="register.jsp" method="post">
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
                        <div class="country-code">
                            <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAALCAMAAABBPP0LAAAASFBMVEXUZADUZgDUYwCsKwDTXwD9/f38/Pz7+/v6+vrVZwDTXQD5+fn39/f29vb19fX09PTz8/Py8vLx8fHw8PDv7+/u7u7t7e3s7Oz3S7LIAAAAGXRFWHRTZW5zZmVyUHJlc3NlIFZpZXRuYW0gRmxhZzkBzliMAAAAT0lEQVR4XlXLRw6AIBBF0WfBCoqKvW3uf5eCP5jMmbyTRVJplCKbEzQhsJXIiMIuYRhj5vqbwiNEtt3YhDM9RBDq8nzDFtWTNYWsGsrqB/YtBnUlyg0xAAAAAElFTkSuQmCC" alt="Vietnam flag">
                            <div class="dropdown">▼</div>
                        </div>
                        <input type="tel" id="phone" name="phone" placeholder="091 234 56 78" value="091 234 56 78" required>
                    </div>
                </div>
                
                <div class="password-fields">
                    <div class="form-group">
                        <label for="password">MẬT KHẨU</label>
                        <div class="password-container">
                            <input type="password" id="password" name="password" placeholder="Mật khẩu" value="•••••••••••••" required>
                        </div>
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
        // Form validation
        document.getElementById('registrationForm').addEventListener('submit', function(event) {
            event.preventDefault();
            
            const fullname = document.getElementById('fullname').value.trim();
            const email = document.getElementById('email').value.trim();
            const phone = document.getElementById('phone').value.trim();
            const password = document.getElementById('password').value;
            const confirmPassword = document.getElementById('confirmPassword').value;
            
            if (fullname === '' || email === '' || phone === '' || password === '' || confirmPassword === '') {
                alert('Vui lòng điền đầy đủ thông tin đăng ký');
                return;
            }
            
            if (!validateEmail(email)) {
                alert('Email không hợp lệ');
                return;
            }
            
            if (password !== confirmPassword) {
                alert('Mật khẩu và nhập lại mật khẩu không khớp');
                return;
            }
            
            // In a real application, this would be an AJAX request or form submission
            console.log('Form submitted:', { fullname, email, phone, password });
            
            // Submit the form to the server
            this.submit();
        });
        
        // Email validation
        function validateEmail(email) {
            const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            return re.test(email);
        }
        
        // Country code dropdown 
        document.querySelector('.country-code').addEventListener('click', function() {
            // In a real application, this would open a dropdown to select country code
            console.log('Country code clicked');
        });
    </script>
</body>
</html>