<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Đăng nhập bằng OTP</title>
<link rel="stylesheet" href="/utedemyProject/views/Css/verifyOTP.css">
</head>
<body>
<div class="otp-container">
<h1 class="otp-header">Đăng nhập bằng OTP</h1>
<p class="otp-instructions">Nhập OTP được cung cấp trong thư điện tử gửi cho ${email}.</p>

<form id="otpForm" action="/utedemyProject/user/verifyOTP" method="post">
<input type="hidden" name="email" value="${email}">
<input type="hidden" name="csrfToken" value="${csrfToken}" />
<div class="otp-input-group">
<input class="otp-input" name="otp1" type="text" maxlength="1">
<input class="otp-input" name="otp2" type="text" maxlength="1">
<input class="otp-input" name="otp3" type="text" maxlength="1">
<input class="otp-input" name="otp4" type="text" maxlength="1">
<input class="otp-input" name="otp5" type="text" maxlength="1">
<input class="otp-input" name="otp6" type="text" maxlength="1">
</div>

<button id="continueBtn" type="button" class="otp-submit-btn" disabled>Tiếp tục</button>
</form>

<!-- Thêm phần hiển thị thông báo lỗi -->
<div id="error-message" style="color: red; text-align: center; margin-top: 10px; display: none;"></div>

<div class="resend-timer">
Gửi lại email <span id="countdown" class="countdown">01:30</span>
</div>

<hr class="divider">

<div class="alt-login">Đăng nhập bằng cách khác</div>
<a href="/do_An/sheller/dangNhap" class="password-link">Sử dụng mật khẩu</a>

<div class="footer-note">
Khi đăng nhập, tôi đồng ý với các <a href="#">Điều khoản</a>.
</div>
</div>

<script nonce="${requestScope.nonce}" src="/utedemyProject/views/Script/verifyOTP.js"></script>
<script nonce="${requestScope.nonce}">
document.addEventListener('DOMContentLoaded', function() {
    const email = "${email}";
    startCountdown(email);

    // Lấy tất cả các ô input OTP
    const otpInputs = document.querySelectorAll('.otp-input');
    const continueBtn = document.getElementById('continueBtn');

    // Hàm chỉ cho phép nhập số
    function onlyNumbersAndMove(input) {
        // Chỉ giữ lại số
        input.value = input.value.replace(/[^0-9]/g, '');
        
        // Tự động chuyển sang ô tiếp theo khi nhập xong
        if (input.value.length === 1) {
            const nextInput = input.nextElementSibling;
            if (nextInput && nextInput.classList.contains('otp-input')) {
                nextInput.focus();
            }
        }
        
        // Kiểm tra xem đã nhập đủ 6 số chưa
        checkAllInputsFilled();
    }

    // Hàm xử lý phím Backspace
    function handleKeyDown(event, input) {
        if (event.key === 'Backspace' && input.value === '') {
            const prevInput = input.previousElementSibling;
            if (prevInput && prevInput.classList.contains('otp-input')) {
                prevInput.focus();
            }
        }
    }

    // Hàm kiểm tra tất cả ô đã nhập chưa
    function checkAllInputsFilled() {
        let allFilled = true;
        otpInputs.forEach(input => {
            if (input.value === '') {
                allFilled = false;
            }
        });
        
        // Enable/disable nút Tiếp tục
        continueBtn.disabled = !allFilled;
    }

    // Gắn sự kiện cho tất cả các ô OTP
    otpInputs.forEach((input, index) => {
        // Sự kiện input (khi nhập ký tự)
        input.addEventListener('input', function() {
            onlyNumbersAndMove(this);
        });
        
        // Sự kiện keydown (xử lý Backspace)
        input.addEventListener('keydown', function(event) {
            handleKeyDown(event, this);
        });
    });

    // Sự kiện cho nút Tiếp tục
    continueBtn.addEventListener('click', function() {
        submitOTPForm();
    });

    // Đảm bảo rằng liên kết "gửi lại mã OTP" cũng hoạt động nếu đã hiển thị
    const resendLink = document.getElementById('resendOTPLink');
    if (resendLink) {
        resendLink.addEventListener('click', function(e) {
            e.preventDefault();
            resendOTP();
        });
    }
});
</script>
</body>
</html>