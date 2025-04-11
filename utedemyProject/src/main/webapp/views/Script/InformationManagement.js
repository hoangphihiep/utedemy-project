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
	    fileInput.name = 'profileImage'; // Thêm name để có thể lấy được bằng req.getPart
	    fileInput.click();
	
	    fileInput.addEventListener('change', function() {
	        if (fileInput.files && fileInput.files[0]) {
	            // Lưu file vào form để có thể submit
	            const hiddenFileInput = document.getElementById('profileImageInput');
	            if (!hiddenFileInput) {
	                // Tạo input ẩn nếu chưa có
	                const newInput = document.createElement('input');
	                newInput.type = 'file';
	                newInput.id = 'profileImageInput';
	                newInput.name = 'profileImage';
	                newInput.style.display = 'none';
	                document.querySelector('form').appendChild(newInput);
	                
	                // Chuyển file đã chọn sang input ẩn (DataTransfer API)
	                const dataTransfer = new DataTransfer();
	                dataTransfer.items.add(fileInput.files[0]);
	                document.getElementById('profileImageInput').files = dataTransfer.files;
	            } else {
	                // Chuyển file đã chọn sang input ẩn đã tồn tại
	                const dataTransfer = new DataTransfer();
	                dataTransfer.items.add(fileInput.files[0]);
	                hiddenFileInput.files = dataTransfer.files;
	            }
	
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
	                
	                // Thêm lại sự kiện click cho camera icon mới
	                cameraIcon.addEventListener('click', function() {
	                    document.querySelector('input[type="file"][accept="image/*"]').click();
	                });
	            };
	            reader.readAsDataURL(fileInput.files[0]);
	        }
	    });
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