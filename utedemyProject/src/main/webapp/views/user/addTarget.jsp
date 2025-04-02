<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<body>
	<div class="content-box">
		<div class="content-header">Mục tiêu khóa học</div>
		<div class="content-body">
			<div class="objective-intro">Học viên sẽ học được gì trong khóa
				học của bạn?</div>


			<div id="objectiveItemsContainer" class="objective-items-container">
				<div class="objective-item">
					<div class="objective-text">Ví dụ: Xác định mục tiêu và quản
						lý công việc</div>
					<div class="counter">120</div>
					<div class="delete-button" onclick="deleteItem(this)">🗑️</div>
				</div>

				<div class="objective-item">
					<div class="objective-text">Ví dụ: Dự tính ngân sách và tiền
						đồ</div>
					<div class="counter">120</div>
					<div class="delete-button" onclick="deleteItem(this)">🗑️</div>
				</div>

				<div class="objective-item">
					<div class="objective-text">Ví dụ: Quản lý chất lượng dự án</div>
					<div class="counter">120</div>
					<div class="delete-button" onclick="deleteItem(this)">🗑️</div>
				</div>

				<div class="objective-item">
					<div class="objective-text">Ví dụ: Nâng cao hiệu quả công
						việc với phương pháp quản lý đội nhóm</div>
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
				<div class="editor-content">Ví dụ: Những người đang kinh doanh
					online,...</div>
			</div>

			<div class="section-title">Chào mừng học viên tham gia khóa học</div>


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
				<div class="editor-content">Ví dụ: Chào mừng các bạn đến với
					khóa học...</div>
			</div>
		</div>
	</div>
	<script>
        // Lấy phần tử container và nút thêm mới
        const container = document.getElementById('objectiveItemsContainer');
        const addButton = document.getElementById('addButton');
        
        // Thêm sự kiện click cho nút thêm mới
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
                }
            });
            
            objectiveInput.addEventListener('blur', function() {
                convertInputToText(this);
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
            };
            
            // Thêm các phần tử con vào item mới
            newItem.appendChild(objectiveInput);
            newItem.appendChild(counter);
            newItem.appendChild(deleteButton);
            
            // Thêm item mới vào container
            container.appendChild(newItem);
            
            // Focus vào input để người dùng có thể bắt đầu gõ ngay
            objectiveInput.focus();
        });
        
        // Hàm chuyển đổi input thành text
        function convertInputToText(inputElement) {
            if (inputElement.value.trim() === '') {
                // Nếu input trống, xóa item
                inputElement.parentElement.remove();
                return;
            }
            
            const objectiveText = document.createElement('div');
            objectiveText.className = 'objective-text';
            objectiveText.textContent = inputElement.value;
            
            // Thay thế input bằng text
            inputElement.parentElement.replaceChild(objectiveText, inputElement);
        }
        
        // Hàm xóa item
        function deleteItem(button) {
            button.parentElement.remove();
        }
        
        // Thêm chức năng xóa cho các item đã có sẵn
        document.querySelectorAll('.delete-button').forEach(button => {
            button.addEventListener('click', function() {
                this.parentElement.remove();
            });
        });
        
        // Thêm chức năng đếm ký tự thực tế
        document.querySelectorAll('.objective-text').forEach(text => {
            const counter = text.nextElementSibling;
            counter.textContent = text.textContent.length;
        });
        document.querySelectorAll('.objective-text').forEach(textElement => {
            textElement.addEventListener('click', function() {
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
                    }
                });
                
                objectiveInput.addEventListener('blur', function() {
                    convertEditInputToText(this, counter);
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
        });

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
                    }
                });
                
                objectiveInput.addEventListener('blur', function() {
                    convertEditInputToText(this, counter);
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