document.addEventListener('DOMContentLoaded', function() {
    // Add date picker functionality
    const birthdateInput = document.getElementById('birthdate');
    birthdateInput.addEventListener('focus', function() {
        this.type = 'date';
    });
    
    birthdateInput.addEventListener('blur', function() {
        if (!this.value) {
            this.type = 'text';
        }
    });
    
    // Profile image upload
    const cameraIcon = document.querySelector('.camera-icon');
    cameraIcon.addEventListener('click', function() {
        const fileInput = document.createElement('input');
        fileInput.type = 'file';
        fileInput.accept = 'image/*';
        fileInput.click();
        
        fileInput.addEventListener('change', function() {
            if (fileInput.files && fileInput.files[0]) {
                const reader = new FileReader();
                reader.onload = function(e) {
                    const profileImage = document.querySelector('.profile-image');
                    profileImage.innerHTML = '';
                    profileImage.style.backgroundImage = `url(${e.target.result})`;
                    profileImage.style.backgroundSize = 'cover';
                    profileImage.style.backgroundPosition = 'center';
                    
                    // Re-add camera icon
                    const cameraIcon = document.createElement('div');
                    cameraIcon.className = 'camera-icon';
                    cameraIcon.innerHTML = '📷';
                    profileImage.appendChild(cameraIcon);
                };
                reader.readAsDataURL(fileInput.files[0]);
            }
        });
    });

    // Form submission with logging
    document.querySelector('form').addEventListener('submit', function(event) {
        event.preventDefault(); // Ngăn form gửi ngay lập tức để kiểm tra

        // Ghi log dữ liệu gửi đi
        const formData = new FormData(this);
        console.log('Dữ liệu gửi đi:');
        for (let [key, value] of formData.entries()) {
            console.log(`${key}: ${value}`);
        }

        // Thêm validation cơ bản
        let isValid = true;
        const fullname = formData.get('fullname');
        const email = formData.get('email');
        const phone = formData.get('phone');
        const address = formData.get('address');
        const birthdate = formData.get('birthdate');
        const gender = formData.get('gender');

        if (!fullname || !email || !phone || !address || !birthdate || !gender) {
            console.error('Lỗi: Một hoặc nhiều trường bắt buộc bị trống.');
            isValid = false;
        }

        if (!email.match(/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/)) {
            console.error('Lỗi: Email không hợp lệ.');
            isValid = false;
        }

        if (!phone.match(/^\d{10,11}$/)) {
            console.error('Lỗi: Số điện thoại không hợp lệ (phải có 10-11 chữ số).');
            isValid = false;
        }

        if (isValid) {
            console.log('Dữ liệu hợp lệ, gửi form...');
            this.submit(); // Gửi form nếu dữ liệu hợp lệ
        } else {
            alert('Vui lòng kiểm tra lại thông tin trước khi gửi.');
        }
    });
});