<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<body>
	<div class="content-box">
		<div class="content-header">Danh sách bài học</div>

		<button class="add-lesson-button" id="openModal">+ Phần học
			mới</button>

		<div class="lesson-list-container">
			<div class="empty-state">Chưa có bài học nào được thêm vào khóa
				học.</div>
		</div>
	</div>

	<!-- Modal Dialog -->
    <div class="modal" id="lessonModal">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">Tạo phần học</h3>
                <button class="close-button" id="closeModal">×</button>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label class="form-label">Tiêu đề<span class="required">*</span></label>
                    <input type="text" class="form-input" placeholder="Tổng quan" value="Tổng quan">
                    <div class="char-count">10</div>
                </div>
                
                <div class="form-group">
                    <label class="form-label">Mô tả ngắn</label>
                    <textarea class="form-input" rows="4" placeholder="Nhập mô tả"></textarea>
                    <div class="char-count">200</div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn-cancel" id="cancelModal">Hủy</button>
                <button class="btn-save">Lưu</button>
            </div>
        </div>
    </div>
    
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const modal = document.getElementById('lessonModal');
            const openModalBtn = document.getElementById('openModal');
            const closeModalBtn = document.getElementById('closeModal');
            const cancelModalBtn = document.getElementById('cancelModal');
            
            // Open modal
            openModalBtn.addEventListener('click', function() {
                modal.classList.add('show');
            });
            
            // Close modal functions
            function closeModal() {
                modal.classList.remove('show');
            }
            
            closeModalBtn.addEventListener('click', closeModal);
            cancelModalBtn.addEventListener('click', closeModal);
            
            // Close modal when clicking outside
            modal.addEventListener('click', function(e) {
                if (e.target === modal) {
                    closeModal();
                }
            });
        });
        document.addEventListener('DOMContentLoaded', function() {
            const modal = document.getElementById('lessonModal');
            const openModalBtn = document.getElementById('openModal');
            const closeModalBtn = document.getElementById('closeModal');
            const cancelModalBtn = document.getElementById('cancelModal');
            const saveBtn = document.querySelector('.btn-save');
            const lessonListContainer = document.querySelector('.lesson-list-container');
            const emptyState = document.querySelector('.empty-state');
            
            // Open modal
            openModalBtn.addEventListener('click', function() {
                modal.classList.add('show');
            });
            
            // Close modal functions
            function closeModal() {
                modal.classList.remove('show');
            }
            
            closeModalBtn.addEventListener('click', closeModal);
            cancelModalBtn.addEventListener('click', closeModal);
            
            // Close modal when clicking outside
            modal.addEventListener('click', function(e) {
                if (e.target === modal) {
                    closeModal();
                }
            });
            
            // Save button functionality
            saveBtn.addEventListener('click', function() {
                // Get the section title from input
                const sectionTitle = document.querySelector('.form-input[type="text"]').value.trim();
                const sectionDescription = document.querySelector('.form-input[rows="4"]').value.trim();
                
                if (sectionTitle) {
                    // Remove empty state if it exists
                    if (emptyState) {
                        emptyState.remove();
                    }
                    
                    // Create new section
                    const sectionNumber = document.querySelectorAll('.course-section').length + 1;
                    const newSection = document.createElement('div');
                    newSection.className = 'course-section';
                    newSection.innerHTML = `
                        <div class="section-header">
                            <div class="section-title">
                                <span>Phần 1: Tổng quan về khóa học</span>
                            </div>
                            <div class="section-columns">
	                            <div class="column-label">Học thử</div>
	                            <div class="column-label">Trạng thái</div>
	                            <div class="column-label">Thời lượng</div>
	                        </div>
                            <div class="section-actions">
                                <div class="action-icon">✎</div>
                                <div class="action-icon">🗑</div>
                            </div>
                        </div>
                        
                        <div class="lesson-actions">
                            <button class="lesson-button">Bài học mới</button>
                            <button class="lesson-button">Bài trắc nghiệm</button>
                        </div>
                        
                        <div class="lesson-info">
                        		Mỗi bài học nên có một bài trắc nghiệm để củng cố kiến thức
                        </div>
                    `;
                    
                    // Add the new section before the "add section" button
                    lessonListContainer.appendChild(newSection);
                    
                    // Add "add section" button if it doesn't exist yet
                    if (!document.querySelector('.add-section-button')) {
                        const addSectionBtn = document.createElement('button');
                        addSectionBtn.className = 'add-lesson-button';
                        addSectionBtn.innerHTML = '<i>+</i> Phần học mới';
                        addSectionBtn.addEventListener('click', function() {
                            modal.classList.add('show');
                        });
                        lessonListContainer.after(addSectionBtn);
                    }
                    
                    // Close the modal
                    closeModal();
                    
                    // Optional: Reset the form
                    document.querySelector('.form-input[type="text"]').value = '';
                    document.querySelector('.form-input[rows="4"]').value = '';
                }
            });
        });
    </script>
    
</body>
</html>