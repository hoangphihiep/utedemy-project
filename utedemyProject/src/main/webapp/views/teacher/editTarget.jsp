<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<body>
	<div class="content-box">
		<div class="content-header">Mục tiêu khóa học</div>
		<div class="content-body">
			<div class="objective-intro">Học viên sẽ học được gì trong khóa
				học của bạn?</div>

			<input type="hidden" id="learnerAchievementsInput" name="learnerAchievements" value="${target}" />
			<div id="objectiveItemsContainer" class="objective-items-container">
				<div class="objective-item">
					<div class="objective-text">Ví dụ: Xác định mục tiêu và quản lý công việc</div>
					<div class="counter">120</div>
					<div class="delete-button" onclick="deleteItem(this)">🗑️</div>
				</div>

				<div class="objective-item">
					<div class="objective-text">Ví dụ: Dự tính ngân sách và tiền đồ</div>
					<div class="counter">120</div>
					<div class="delete-button" onclick="deleteItem(this)">🗑️</div>
				</div>

				<div class="objective-item">
					<div class="objective-text">Ví dụ: Quản lý chất lượng dự án</div>
					<div class="counter">120</div>
					<div class="delete-button" onclick="deleteItem(this)">🗑️</div>
				</div>

				<div class="objective-item">
					<div class="objective-text">Ví dụ: Nâng cao hiệu quả công việc với phương pháp quản lý đội nhóm</div>
					<div class="counter">120</div>
					<div class="delete-button" onclick="deleteItem(this)">🗑️</div>
				</div>
			</div>

			<div class="add-button" id="addButton">
				<span class="add-icon">⊕</span> Thêm nội dung
			</div>

			<div class="section-title">Khóa học này dành cho đối tượng nào?</div>



			<div class="text-editor">
				<div class="editor-toolbar">
					<button class="toolbar-button">
						<strong>B</strong>
					</button>
					<button class="toolbar-button">
						<em>I</em>
					</button>
					<button class="toolbar-button">≡</button>
				</div>
				<div class="editor-content">${learner}</div>
			</div>

		
		</div>
	</div>
	<script>
	// Hàm để khởi tạo các objective-item từ một chuỗi được phân tách bằng dấu phẩy
	function initializeObjectivesFromString() {
	    // Lấy chuỗi mục tiêu từ input field ẩn
	    const achievementsString = document.getElementById('learnerAchievementsInput').value;
	    
	    if (!achievementsString || achievementsString.trim() === '') {
	        return; // Không có dữ liệu để xử lý
	    }
	    
	    // Xóa tất cả mục tiêu mẫu hiện có
	    const container = document.getElementById('objectiveItemsContainer');
	    container.innerHTML = '';
	    
	    // Tách chuỗi thành mảng các mục tiêu dựa trên dấu phẩy
	    const objectives = achievementsString.split(',').map(item => item.trim()).filter(item => item);
	    
	    // Tạo một objective-item cho mỗi mục tiêu
	    objectives.forEach(objective => {
	        addObjectiveItem(objective);
	    });
	}
	
	// Hàm thêm một objective-item mới với nội dung cụ thể
	function addObjectiveItem(text) {
	    const container = document.getElementById('objectiveItemsContainer');
	    
	    // Tạo div mới cho objective-item
	    const newItem = document.createElement('div');
	    newItem.className = 'objective-item';
	    
	    // Tạo div cho nội dung mục tiêu
	    const objectiveText = document.createElement('div');
	    objectiveText.className = 'objective-text';
	    objectiveText.textContent = text;
	    
	    // Thêm event listener cho việc chỉnh sửa khi click
	    objectiveText.addEventListener('click', function() {
	        // Lấy giá trị hiện tại của text
	        const currentText = this.textContent;
	        
	        // Tạo input mới
	        const objectiveInput = document.createElement('input');
	        objectiveInput.className = 'new-objective-input';
	        objectiveInput.type = 'text';
	        objectiveInput.value = currentText;
	        objectiveInput.style.width = '100%';
	        objectiveInput.style.padding = '5px';
	        objectiveInput.style.border = '1px solid #ccc';
	        objectiveInput.style.borderRadius = '3px';
	        
	        // Lấy phần tử counter
	        const counter = this.nextElementSibling;
	        
	        // Khi người dùng nhấn Enter hoặc rời khỏi input, chuyển input thành text
	        objectiveInput.addEventListener('keydown', function(e) {
	            if (e.key === 'Enter') {
	                convertEditInputToText(this, counter);
	                updateHiddenField(); // Cập nhật input field ẩn
	            }
	        });
	        
	        objectiveInput.addEventListener('blur', function() {
	            convertEditInputToText(this, counter);
	            updateHiddenField(); // Cập nhật input field ẩn
	        });
	        
	        // Cập nhật counter khi gõ
	        objectiveInput.addEventListener('input', function() {
	            counter.textContent = this.value.length;
	        });
	        
	        // Thay thế text bằng input
	        this.parentElement.replaceChild(objectiveInput, this);
	        
	        // Focus vào input để người dùng có thể bắt đầu gõ ngay
	        objectiveInput.focus();
	        
	        // Đặt con trỏ ở cuối text
	        objectiveInput.setSelectionRange(currentText.length, currentText.length);
	    });
	    
	    // Tạo div cho counter
	    const counter = document.createElement('div');
	    counter.className = 'counter';
	    counter.textContent = text.length;
	    
	    // Tạo nút xóa
	    const deleteButton = document.createElement('div');
	    deleteButton.className = 'delete-button';
	    deleteButton.textContent = '🗑️';
	    deleteButton.onclick = function() {
	        deleteItem(this);
	        updateHiddenField(); // Cập nhật input field ẩn khi xóa một mục
	    };
	    
	    // Thêm các phần tử con vào item mới
	    newItem.appendChild(objectiveText);
	    newItem.appendChild(counter);
	    newItem.appendChild(deleteButton);
	    
	    // Thêm item mới vào container
	    container.appendChild(newItem);
	}
	
	// Hàm để cập nhật input field ẩn khi có thay đổi
	function updateHiddenField() {
	    const objectiveTexts = Array.from(document.querySelectorAll('.objective-text')).map(item => item.textContent.trim());
	    const hiddenInput = document.getElementById('learnerAchievementsInput');
	    hiddenInput.value = objectiveTexts.join(', ');
	}
	
	// Hàm chuyển đổi input thành text
	function convertInputToText(inputElement) {
	    if (inputElement.value.trim() === '') {
	        // Nếu input trống, xóa item
	        inputElement.parentElement.remove();
	        updateHiddenField(); // Cập nhật input field ẩn
	        return;
	    }
	    
	    const objectiveText = document.createElement('div');
	    objectiveText.className = 'objective-text';
	    objectiveText.textContent = inputElement.value;
	    
	    // Thêm event listener cho text mới
	    objectiveText.addEventListener('click', function() {
	        // Lấy giá trị hiện tại của text
	        const currentText = this.textContent;
	        
	        // Tạo input mới
	        const objectiveInput = document.createElement('input');
	        objectiveInput.className = 'new-objective-input';
	        objectiveInput.type = 'text';
	        objectiveInput.value = currentText;
	        objectiveInput.style.width = '100%';
	        objectiveInput.style.padding = '5px';
	        objectiveInput.style.border = '1px solid #ccc';
	        objectiveInput.style.borderRadius = '3px';
	        
	        // Lấy phần tử counter
	        const counter = this.nextElementSibling;
	        
	        // Khi người dùng nhấn Enter hoặc rời khỏi input, chuyển input thành text
	        objectiveInput.addEventListener('keydown', function(e) {
	            if (e.key === 'Enter') {
	                convertEditInputToText(this, counter);
	                updateHiddenField(); // Cập nhật input field ẩn
	            }
	        });
	        
	        objectiveInput.addEventListener('blur', function() {
	            convertEditInputToText(this, counter);
	            updateHiddenField(); // Cập nhật input field ẩn
	        });
	        
	        // Cập nhật counter khi gõ
	        objectiveInput.addEventListener('input', function() {
	            counter.textContent = this.value.length;
	        });
	        
	        // Thay thế text bằng input
	        this.parentElement.replaceChild(objectiveInput, this);
	        
	        // Focus vào input để người dùng có thể bắt đầu gõ ngay
	        objectiveInput.focus();
	        
	        // Đặt con trỏ ở cuối text
	        objectiveInput.setSelectionRange(currentText.length, currentText.length);
	    });
	    
	    // Thay thế input bằng text
	    inputElement.parentElement.replaceChild(objectiveText, inputElement);
	}
	
	// Hàm chuyển đổi input thành text khi đang chỉnh sửa
	function convertEditInputToText(inputElement, counter) {
	    const objectiveText = document.createElement('div');
	    objectiveText.className = 'objective-text';
	    objectiveText.textContent = inputElement.value;
	    
	    // Thêm lại event listener cho text mới
	    objectiveText.addEventListener('click', function() {
	        // Lấy giá trị hiện tại của text
	        const currentText = this.textContent;
	        
	        // Tạo input mới
	        const objectiveInput = document.createElement('input');
	        objectiveInput.className = 'new-objective-input';
	        objectiveInput.type = 'text';
	        objectiveInput.value = currentText;
	        objectiveInput.style.width = '100%';
	        objectiveInput.style.padding = '5px';
	        objectiveInput.style.border = '1px solid #ccc';
	        objectiveInput.style.borderRadius = '3px';
	        
	        // Lấy phần tử counter
	        const counter = this.nextElementSibling;
	        
	        // Khi người dùng nhấn Enter hoặc rời khỏi input, chuyển input thành text
	        objectiveInput.addEventListener('keydown', function(e) {
	            if (e.key === 'Enter') {
	                convertEditInputToText(this, counter);
	                updateHiddenField(); // Cập nhật input field ẩn
	            }
	        });
	        
	        objectiveInput.addEventListener('blur', function() {
	            convertEditInputToText(this, counter);
	            updateHiddenField(); // Cập nhật input field ẩn
	        });
	        
	        // Cập nhật counter khi gõ
	        objectiveInput.addEventListener('input', function() {
	            counter.textContent = this.value.length;
	        });
	        
	        // Thay thế text bằng input
	        this.parentElement.replaceChild(objectiveInput, this);
	        
	        // Focus vào input để người dùng có thể bắt đầu gõ ngay
	        objectiveInput.focus();
	        
	        // Đặt con trỏ ở cuối text
	        objectiveInput.setSelectionRange(currentText.length, currentText.length);
	    });
	    
	    // Thay thế input bằng text
	    inputElement.parentElement.replaceChild(objectiveText, inputElement);
	    
	    // Cập nhật counter
	    counter.textContent = objectiveText.textContent.length;
	}
	
	// Hàm xóa item
	function deleteItem(button) {
	    button.parentElement.remove();
	    updateHiddenField(); // Cập nhật input field ẩn khi xóa
	}
	
	// Gọi hàm khởi tạo khi trang tải xong
	document.addEventListener('DOMContentLoaded', function() {
	    // Khởi tạo các objective-item từ chuỗi ban đầu
	    initializeObjectivesFromString();
	    
	    // Sửa đổi sự kiện click của nút "Thêm nội dung" để cập nhật input field ẩn
	    const addButton = document.getElementById('addButton');
	    const oldClickListeners = addButton.getEventListeners ? addButton.getEventListeners('click') : [];
	    
	    // Xóa tất cả event listener cũ nếu có
	    if (oldClickListeners && oldClickListeners.length > 0) {
	        oldClickListeners.forEach(listener => {
	            addButton.removeEventListener('click', listener);
	        });
	    } else {
	        // Nếu không thể lấy event listeners cũ, chỉ cần thêm mới
	        // Nút vẫn sẽ hoạt động như trước, nhưng có thể có duplicate listeners
	    }
	    
	    // Thêm event listener mới
	    addButton.addEventListener('click', function() {
	        // Tạo div mới cho objective-item
	        const newItem = document.createElement('div');
	        newItem.className = 'objective-item';
	        
	        // Tạo input để nhập mục tiêu mới
	        const objectiveInput = document.createElement('input');
	        objectiveInput.className = 'new-objective-input';
	        objectiveInput.type = 'text';
	        objectiveInput.placeholder = 'Nhập mục tiêu mới...';
	        objectiveInput.style.width = '100%';
	        objectiveInput.style.padding = '5px';
	        objectiveInput.style.border = '1px solid #ccc';
	        objectiveInput.style.borderRadius = '3px';
	        
	        // Khi người dùng nhấn Enter hoặc rời khỏi input, chuyển input thành text
	        objectiveInput.addEventListener('keydown', function(e) {
	            if (e.key === 'Enter') {
	                convertInputToText(this);
	                updateHiddenField(); // Cập nhật input field ẩn
	            }
	        });
	        
	        objectiveInput.addEventListener('blur', function() {
	            convertInputToText(this);
	            updateHiddenField(); // Cập nhật input field ẩn
	        });
	        
	        // Tạo div cho counter
	        const counter = document.createElement('div');
	        counter.className = 'counter';
	        counter.textContent = '0';
	        
	        // Cập nhật counter khi gõ
	        objectiveInput.addEventListener('input', function() {
	            counter.textContent = this.value.length;
	        });
	        
	        // Tạo nút xóa
	        const deleteButton = document.createElement('div');
	        deleteButton.className = 'delete-button';
	        deleteButton.textContent = '🗑️';
	        deleteButton.onclick = function() {
	            deleteItem(this);
	            updateHiddenField(); // Cập nhật input field ẩn
	        };
	        
	        // Thêm các phần tử con vào item mới
	        newItem.appendChild(objectiveInput);
	        newItem.appendChild(counter);
	        newItem.appendChild(deleteButton);
	        
	        // Thêm item mới vào container
	        const container = document.getElementById('objectiveItemsContainer');
	        container.appendChild(newItem);
	        
	        // Focus vào input để người dùng có thể bắt đầu gõ ngay
	        objectiveInput.focus();
	    });
	});
	// Thêm event listener cho tất cả các editor-content
    document.querySelectorAll('.editor-content').forEach(contentElement => {
        contentElement.addEventListener('click', function() {
            // Lấy giá trị hiện tại của content
            const currentContent = this.textContent.trim();
            
            // Tạo textarea mới
            const editorTextarea = document.createElement('textarea');
            editorTextarea.className = 'editor-textarea';
            editorTextarea.value = currentContent;
            
            // Thiết lập style cho textarea
            editorTextarea.style.width = '100%';
            editorTextarea.style.minHeight = '100px';
            editorTextarea.style.padding = '15px';
            editorTextarea.style.border = 'none';
            editorTextarea.style.resize = 'vertical';
            editorTextarea.style.fontFamily = 'Arial, sans-serif';
            editorTextarea.style.fontSize = 'inherit';
            editorTextarea.style.color = '#333';
            editorTextarea.style.outline = 'none';
            
            // Khi người dùng rời khỏi textarea hoặc nhấn Ctrl+Enter, chuyển textarea thành text
            editorTextarea.addEventListener('blur', function() {
                convertTextareaToContent(this);
            });
            
            editorTextarea.addEventListener('keydown', function(e) {
                if (e.ctrlKey && e.key === 'Enter') {
                    convertTextareaToContent(this);
                    e.preventDefault();
                }
            });
            
            // Thay thế content bằng textarea
            this.parentElement.replaceChild(editorTextarea, this);
            
            // Focus vào textarea để người dùng có thể bắt đầu gõ ngay
            editorTextarea.focus();
            
            // Đặt con trỏ ở cuối text
            editorTextarea.setSelectionRange(currentContent.length, currentContent.length);
        });
    });

    // Hàm chuyển đổi textarea thành content
    function convertTextareaToContent(textareaElement) {
        const editorContent = document.createElement('div');
        editorContent.className = 'editor-content';
        editorContent.textContent = textareaElement.value || 'Nhập nội dung của bạn ở đây...';
        
        // Thiết lập style như ban đầu
        editorContent.style.padding = '15px';
        editorContent.style.minHeight = '100px';
        editorContent.style.color = textareaElement.value ? '#333' : '#999';
        
        // Thêm lại event listener cho content mới
        editorContent.addEventListener('click', function() {
            const currentContent = this.textContent.trim();
            
            const editorTextarea = document.createElement('textarea');
            editorTextarea.className = 'editor-textarea';
            editorTextarea.value = currentContent;
            
            // Thiết lập style cho textarea
            editorTextarea.style.width = '100%';
            editorTextarea.style.minHeight = '100px';
            editorTextarea.style.padding = '15px';
            editorTextarea.style.border = 'none';
            editorTextarea.style.resize = 'vertical';
            editorTextarea.style.fontFamily = 'Arial, sans-serif';
            editorTextarea.style.fontSize = 'inherit';
            editorTextarea.style.color = '#333';
            editorTextarea.style.outline = 'none';
            
            editorTextarea.addEventListener('blur', function() {
                convertTextareaToContent(this);
            });
            
            editorTextarea.addEventListener('keydown', function(e) {
                if (e.ctrlKey && e.key === 'Enter') {
                    convertTextareaToContent(this);
                    e.preventDefault();
                }
            });
            
            this.parentElement.replaceChild(editorTextarea, this);
            editorTextarea.focus();
            editorTextarea.setSelectionRange(currentContent.length, currentContent.length);
        });
        
        // Thay thế textarea bằng content
        textareaElement.parentElement.replaceChild(editorContent, textareaElement);
    }
	</script>
</body>
</html>