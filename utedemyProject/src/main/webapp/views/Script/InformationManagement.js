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