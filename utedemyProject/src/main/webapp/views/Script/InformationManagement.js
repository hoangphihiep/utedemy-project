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

    
    // Xử lý nút TRUY CẬP
	document.addEventListener('DOMContentLoaded', function() {
	    // Thêm input ẩn cho hình ảnh vào form (nếu chưa có)
	    if (!document.getElementById('profileImageInput')) {
	        const profileImageInput = document.createElement('input');
	        profileImageInput.type = 'file';
	        profileImageInput.id = 'profileImageInput';
	        profileImageInput.name = 'profileImage';
	        profileImageInput.style.display = 'none';
	        document.querySelector('form').appendChild(profileImageInput);
	    }
	    
	    // Thêm sự kiện cho nút TRUY CẬP
	    const accessBtn = document.querySelector('button.btn.btn-primary:last-child');
	    accessBtn.addEventListener('click', function(e) {
	        e.preventDefault();
	        
	        // Thêm hidden input để xác định action
	        let actionInput = document.querySelector('input[name="action"]');
	        if (!actionInput) {
	            actionInput = document.createElement('input');
	            actionInput.type = 'hidden';
	            actionInput.name = 'action';
	            document.querySelector('form').appendChild(actionInput);
	        }
	        actionInput.value = 'access';
	        
	        // Trigger sự kiện submit để kích hoạt validation và gửi form
	        const submitEvent = new Event('submit', {
	            'bubbles': true,
	            'cancelable': true
	        });
	        document.querySelector('form').dispatchEvent(submitEvent);
	    });
	});
});

document.addEventListener('DOMContentLoaded', function() {
    // Lấy form và nút submit
    const form = document.querySelector('form');
    const updateBtn = form.querySelector('button[type="submit"]');
    
    // Thêm sự kiện click cho nút Cập nhật
    updateBtn.addEventListener('click', function(e) {
        // Ngăn form submit ngay lập tức
        e.preventDefault();
        
        // Lấy tất cả giá trị từ form
        const formData = {
            fullname: document.getElementById('fullname').value,
            email: document.getElementById('email').value,
            phone: document.getElementById('phone').value,
            address: document.getElementById('address').value,
            birthdate: document.getElementById('birthdate').value,
            gender: document.getElementById('gender').value,
            profileImage: document.getElementById('images1').files[0] ? 
                         document.getElementById('images1').files[0].name : 'Không có thay đổi'
        };
        
        // Log dữ liệu ra console
        console.log('Dữ liệu chuẩn bị gửi:', formData);
        
        // Kiểm tra validation trước khi submit
        if (validateForm()) {
            console.log('Validation thành công, tiến hành submit form');
            form.submit();
        } else {
            console.log('Validation thất bại, không submit form');
        }
    });
    
    // Hàm validate form
    function validateForm() {
        // Thêm logic validate của bạn ở đây
        // Trả về true nếu hợp lệ, false nếu không
        return true;
    }
});
function previewFile() {
    const preview = document.getElementById('previewImage');
    const fileInput = document.getElementById('images1');
    const file = fileInput.files[0];
    const reader = new FileReader();

    reader.onloadend = function() {
        preview.src = reader.result;
        // Log thông tin file ảnh
        if (file) {
            console.log('Ảnh đã chọn:', {
                name: file.name,
                size: (file.size / 1024).toFixed(2) + ' KB',
                type: file.type
            });
        }
    }

    if (file) {
        reader.readAsDataURL(file);
    } else {
        preview.src = "${imgUrl}";
    }
}
// Theo dõi thay đổi trên các trường input
const inputs = document.querySelectorAll('input, select');
inputs.forEach(input => {
    input.addEventListener('input', function() {
        console.log(`Trường ${this.name} thay đổi:`, this.value);
    });
});
// Log trạng thái hiện tại của user
console.log('Thông tin user hiện tại:', {
    fullname: "${user.fullname}",
    email: "${user.email}",
    phone: "${user.phoneNumber}",
    address: "${user.address}",
    birthdate: "${birthdate}",
    gender: "${user.gender}",
    avatar: "${user.avatarUrl}"
});

document.addEventListener('DOMContentLoaded', function() {
    // Lấy nút TRUY CẬP
    const accessBtn = document.querySelector('.btn.btn-primary:last-child');
    
    // Thêm sự kiện click
    accessBtn.addEventListener('click', function() {
        // Chuyển hướng sang trang homepage.jsp
        window.location.href = '${pageContext.request.contextPath}/homepage.jsp';
    });
});