<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<body>
	<div class="content-box">
		<div class="content-header">Danh sách bài học</div>
		<div class="lesson-list-container">
			<button class="add-lesson-button" id="openModal">+ Phần học
				mới</button>
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
    
    <div class="modal" id="lesson"> 
	    <div class="modal-content"> 
	        <div class="modal-header"> 
	            <h3 class="modal-title">Cập nhật bài học</h3> 
	            <button class="close-button" id="closeModal">×</button> 
	        </div> 
	        <div class="modal-body"> 
	            <div class="form-group"> 
	                <label class="form-label">Tiêu đề <span class="required">*</span></label> 
	                <input type="text" class="form-input" placeholder="Nhập tiêu đề" value="Giới Thiệu Giao Diện"> 
	            </div> 
	             
	            <div class="form-group"> 
	                <label class="form-label">Mô tả ngắn</label> 
	                <textarea class="form-input" rows="4" placeholder="Nhập mô tả"></textarea> 
	            </div>
	
	            <div class="form-group">
	                <label class="checkbox-label">
	                    <input type="checkbox" id="freeTrialCheckbox"> Học thử miễn phí
	                </label>
	            </div>
	
	            <div class="form-group">
	                <label class="form-label">Nhập link Youtube/vimeo</label>
	                <div class="video-input-container">
	                    <input type="text" class="form-input video-input" placeholder="vd: https://www.youtube.com/embed?v=7fq7rQpOHuM" value="https://www.youtube.com/embed?v=7fq7rQpOHuM">
	                </div>
	            </div>
	        </div> 
	        <div class="modal-footer"> 
	            <button class="btn-cancel" id="cancelModal">Hủy</button> 
	            <button class="btn-save">Lưu bài học</button> 
	        </div> 
	    </div> 
	</div>
	
	<div class="modal" id="quiz">
	  <div class="modal-content">
	    <div class="modal-header">
	      <h3 class="modal-title">Bài trắc nghiệm</h3>
	      <button class="close-button" id="closeModal">×</button>
	    </div>
	    <div class="modal-body">
	      <div class="form-group">
	        <label class="form-label">Tiêu đề <span class="required">*</span></label>
	        <input type="text" class="form-input" placeholder="Nhập tiêu đề" value="Bài Kiểm Tra">
	        <div class="character-count">67</div>
	      </div>
	
	      <div class="form-group">
	        <label class="form-label">Mô tả ngắn</label>
	        <input type="text" class="form-input" placeholder="Nhập mô tả">
	        <div class="character-count">200</div>
	      </div>
	      <button class="quiz-actions">
	           + Câu hỏi mới
	      </button>
	    </div>
	    <div class="modal-footer">
	      <button class="btn-cancel">Hủy</button>
	      <button class="btn-save">Lưu trắc nghiệm</button>
	    </div>
	  </div>
	</div>
    
    <script>
    document.addEventListener('DOMContentLoaded', function() {
        // Tìm các phần tử modal và nút điều khiển
        const lessonModal = document.getElementById('lessonModal');
        const lessonDetailModal = document.getElementById('lesson');
        const quizModal = document.getElementById('quiz');
        const openModalBtn = document.getElementById('openModal');
        
        // Tìm tất cả các nút "Bài học mới"
        const lessonButtons = document.querySelectorAll('.lesson-button');
        
        // Tìm các nút đóng cho modal lesson (cập nhật bài học)
        const closeDetailModalBtn = document.querySelector('#lesson .close-button');
        const cancelDetailModalBtn = document.querySelector('#lesson .btn-cancel');
        const saveDetailBtn = document.querySelector('#lesson .btn-save');
        
        // Tìm các nút đóng cho modal lessonModal (phần học)
        const closeModalBtn = document.querySelector('#lessonModal .close-button');
        const cancelModalBtn = document.querySelector('#lessonModal .btn-cancel');
        const saveBtn = document.querySelector('#lessonModal .btn-save');
        
     	// Tìm các nút đóng cho modal quiz
        const closeQuizModalBtn = document.querySelector('#quiz .close-button');
        const cancelQuizModalBtn = document.querySelector('#quiz .btn-cancel');
        const saveQuizBtn = document.querySelector('#quiz .btn-save');
        
     	// Thêm code xử lý nút "Add new question" vào đây:
        const addQuestionBtn = document.querySelector('.quiz-actions');
        const modalBody = document.querySelector('.modal-body');
        
        const lessonListContainer = document.querySelector('.lesson-list-container');
        const emptyState = document.querySelector('.empty-state');
        
        // Biến để lưu phần học hiện tại đang mở modal bài học
        let currentSection = null;
        
     	// Biến để lưu phần học hiện tại đang mở modal bài trắc nghiệm
        let currentQuizSection = null;
        
        // Các hàm mở/đóng modal
        function openLessonModal() {
            lessonModal.classList.add('show');
        }
        
        function openLessonDetailModal(section) {
            currentSection = section;
            lessonDetailModal.classList.add('show');
        }
        
     	// Hàm mở/đóng modal quiz
        function openQuizModal(section) {
            currentQuizSection = section;
            quizModal.classList.add('show');
        }
        
        function closeLessonModal() {
            if (lessonModal) lessonModal.classList.remove('show');
        }
        
        function closeLessonDetailModal() {
            if (lessonDetailModal) lessonDetailModal.classList.remove('show');
        }
        
        function closeQuizModal() {
            if (quizModal) quizModal.classList.remove('show');
        }
        
        // Thêm event listener cho modal phần học (lessonModal)
        if (openModalBtn) {
            openModalBtn.addEventListener('click', openLessonModal);
        }
        
        if (closeModalBtn) {
            closeModalBtn.addEventListener('click', closeLessonModal);
        }
        
        if (cancelModalBtn) {
            cancelModalBtn.addEventListener('click', closeLessonModal);
        }
        
        if (lessonModal) {
            lessonModal.addEventListener('click', function(e) {
                if (e.target === lessonModal) {
                    closeLessonModal();
                }
            });
        }
        
        // Thêm event listener cho modal bài học (lesson)
        lessonButtons.forEach(button => {
            if (button.textContent === 'Bài học mới') {
                button.addEventListener('click', function() {
                    // Tìm phần học (course-section) chứa nút này
                    const section = this.closest('.course-section');
                    openLessonDetailModal(section);
                });
            }
        });
        
     	// Thêm event listener cho các nút "Bài trắc nghiệm"
        const quizButtons = document.querySelectorAll('.lesson-button');
        quizButtons.forEach(button => {
            if (button.textContent === 'Bài trắc nghiệm') {
                button.addEventListener('click', function() {
                    const section = this.closest('.course-section');
                    openQuizModal(section);
                });
            }
        });
        
     	// Thêm event listeners cho đóng modal quiz
        if (closeQuizModalBtn) {
            closeQuizModalBtn.addEventListener('click', closeQuizModal);
        }
        
        if (cancelQuizModalBtn) {
            cancelQuizModalBtn.addEventListener('click', closeQuizModal);
        }
        
        if (quizModal) {
            quizModal.addEventListener('click', function(e) {
                if (e.target === quizModal) {
                    closeQuizModal();
                }
            });
        }
        
        if (closeDetailModalBtn) {
            closeDetailModalBtn.addEventListener('click', closeLessonDetailModal);
        }
        
        if (cancelDetailModalBtn) {
            cancelDetailModalBtn.addEventListener('click', closeLessonDetailModal);
        }
        
        if (lessonDetailModal) {
            lessonDetailModal.addEventListener('click', function(e) {
                if (e.target === lessonDetailModal) {
                    closeLessonDetailModal();
                }
            });
        }
        if (addQuestionBtn && modalBody) {
            addQuestionBtn.addEventListener('click', function() {
                // Tạo phần tử câu hỏi mới
                const newQuestion = document.createElement('div');
                newQuestion.className = 'question-container';
                newQuestion.innerHTML = `
                    <div class="question-header">
                        <input type="text" class="question-input" placeholder="Nhập câu hỏi">
                        <button class="delete-question">🗑️</button>
                    </div>
                    <div class="answers-container">
                        <div class="answer-option">
                            <input type="checkbox" class="answer-checkbox">
                            <input type="text" class="answer-input" placeholder="Nhập câu trả lời">
                            <button class="delete-answer">🗑️</button>
                        </div>
                        <div class="answer-option">
                            <input type="checkbox" class="answer-checkbox">
                            <input type="text" class="answer-input" placeholder="Nhập câu trả lời">
                            <button class="delete-answer">🗑️</button>
                        </div>
                        <div class="answer-option">
                            <input type="checkbox" class="answer-checkbox">
                            <input type="text" class="answer-input" placeholder="Nhập câu trả lời">
                            <button class="delete-answer">🗑️</button>
                        </div>
                        <div class="answer-option">
                            <input type="checkbox" class="answer-checkbox">
                            <input type="text" class="answer-input" placeholder="Nhập câu trả lời">
                            <button class="delete-answer">🗑️</button>
                        </div>
                    </div>
                    <button class="add-answer-btn">+ Thêm trả lời</button>
                `;
                
                const quizActions = document.querySelector('.quiz-actions');
				if (quizActions && quizActions.parentNode) {
				    quizActions.parentNode.insertBefore(newQuestion, quizActions);
				} else {
				    // Nếu không tìm thấy .quiz-actions hoặc không có parent node
				    modalBody.appendChild(newQuestion);
				}
                
                // Thêm event listener cho nút "Add answer"
                const addAnswerBtn = newQuestion.querySelector('.add-answer-btn');
                addAnswerBtn.addEventListener('click', function() {
                    const answersContainer = this.previousElementSibling;
                    const newAnswer = document.createElement('div');
                    newAnswer.className = 'answer-option';
                    newAnswer.innerHTML = `
                        <input type="checkbox" class="answer-checkbox">
                        <input type="text" class="answer-input" placeholder="Nhập câu trả lời">
                        <button class="delete-answer">🗑️</button>
                    `;
                    answersContainer.appendChild(newAnswer);
                    
                    // Thêm event listener cho nút xóa câu trả lời
                    const deleteBtn = newAnswer.querySelector('.delete-answer');
                    deleteBtn.addEventListener('click', function() {
                        this.parentElement.remove();
                    });
                });
                
                // Thêm event listeners cho các nút xóa
                const deleteQuestionBtn = newQuestion.querySelector('.delete-question');
                deleteQuestionBtn.addEventListener('click', function() {
                    this.closest('.question-container').remove();
                });
                
                const deleteAnswerBtns = newQuestion.querySelectorAll('.delete-answer');
                deleteAnswerBtns.forEach(btn => {
                    btn.addEventListener('click', function() {
                        this.parentElement.remove();
                    });
                });
            });
        }
        
        // Xử lý nút lưu cho modal phần học
        if (saveBtn && lessonModal) {
            saveBtn.addEventListener('click', function() {
                const sectionTitle = document.querySelector('#lessonModal .form-input[type="text"]').value.trim();
                const sectionDescription = document.querySelector('#lessonModal .form-input[rows="4"]').value.trim();
                
                if (sectionTitle) {
                    if (emptyState && emptyState.parentNode) {
                        emptyState.remove();
                    }
                    
                    const sectionNumber = document.querySelectorAll('.course-section').length + 1;
                    const newSection = document.createElement('div');
                    newSection.className = 'course-section';
                    newSection.innerHTML = `
                        <div class="section-header">
                            <div class="section-title">
                                Phần 1: Tổng quan về excel
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
                           'Mỗi bài học nên có một bài trắc nghiệm để củng cố kiến thức'
                        </div>
                    `;
                    
                    // Thêm event listeners cho nút "Bài học mới" vừa tạo
                    const newLessonButtons = newSection.querySelectorAll('.lesson-button');
                    newLessonButtons.forEach(button => {
                        if (button.textContent === 'Bài học mới') {
                            button.addEventListener('click', function() {
                                openLessonDetailModal(newSection);
                            });
                        }
                    });
                    
                    if (lessonListContainer && openModalBtn) {
                        lessonListContainer.insertBefore(newSection, openModalBtn);
                    }
                    
                    closeLessonModal();
                    
                    const deleteIcon = newSection.querySelector('.section-actions .action-icon:nth-child(2)');
                    if (deleteIcon) {
                        deleteIcon.addEventListener('click', function() {
                        	newSection.remove();
                        });
                    }
                    
                    // Reset form
                    const titleInput = document.querySelector('#lessonModal .form-input[type="text"]');
                    const descInput = document.querySelector('#lessonModal .form-input[rows="4"]');
                    if (titleInput) titleInput.value = '';
                    if (descInput) descInput.value = '';
                }
            });
        }
        
        // Xử lý nút lưu cho modal bài học
        if (saveDetailBtn && lessonDetailModal) {
            saveDetailBtn.addEventListener('click', function() {
                const title = document.querySelector('#lesson .form-input[placeholder="Nhập tiêu đề"]').value.trim();
                const description = document.querySelector('#lesson textarea.form-input').value.trim();
                const isFreeLesson = document.getElementById('freeTrialCheckbox').checked;
                const videoUrl = document.querySelector('#lesson .video-input').value.trim();
                
                if (title && currentSection) {
                    // Đếm số bài học hiện tại trong phần này
                    const existingLessons = currentSection.querySelectorAll('.lesson-item').length;
                    const lessonNumber = existingLessons + 1;
                    
                    // Tạo phần tử bài học mới
                    const newLesson = document.createElement('div');
                    newLesson.className = 'lesson-item';
                    newLesson.innerHTML = `
                        <div class="section-header">
                            <div class="section-title">
                                <span>Bài ${lessonNumber + 1}: ${title}</span>
                            </div>
                            <div class="section-columns">
		                        <div class="column-label">
		                            <input type="checkbox" class="free-lesson-checkbox" ${isFreeLesson ? 'checked' : ''}>
		                        </div>
                                <div class="column-label">-</div>
                                <div class="column-label">12</div>
                            </div>
                            <div class="section-actions">
                                <div class="action-icon">✎</div>
                                <div class="action-icon">🗑</div>
                            </div>
                        </div>
                    `;
                    
                    const deleteButton = newLesson.querySelector('.action-icon:nth-child(2)');
		            if (deleteButton) {
		                deleteButton.addEventListener('click', function() {
		                    newLesson.remove();
		                });
		            }
                    // Thêm bài học mới vào phần học hiện tại
                    // Thêm bài học trước phần lesson-actions
                    const lessonActions = currentSection.querySelector('.lesson-actions');
                    if (lessonActions) {
                        currentSection.insertBefore(newLesson, lessonActions);
                    } else {
                        // Nếu không tìm thấy lesson-actions, thêm vào cuối của phần học
                        currentSection.appendChild(newLesson);
                    }
                    
                    closeLessonDetailModal();
                    
                    // Reset form
                    const titleInput = document.querySelector('#lesson .form-input[placeholder="Nhập tiêu đề"]');
                    const descInput = document.querySelector('#lesson textarea.form-input');
                    const videoInput = document.querySelector('#lesson .video-input');
                    const checkbox = document.getElementById('freeTrialCheckbox');
                    
                    if (titleInput) titleInput.value = '';
                    if (descInput) descInput.value = '';
                    if (videoInput) videoInput.value = '';
                    if (checkbox) checkbox.checked = false;
                }
            });
        }
        
        // Bắt sự kiện cho các nút được thêm vào DOM sau này
        document.addEventListener('click', function(e) {
            if (e.target.classList.contains('lesson-button') && e.target.textContent === 'Bài học mới') {
                const section = e.target.closest('.course-section');
                if (section) {
                    openLessonDetailModal(section);
                }
            }
        });
        
     	// Xử lý nút lưu cho modal quiz
        if (saveQuizBtn && quizModal) {
            saveQuizBtn.addEventListener('click', function() {
                const title = document.querySelector('#quiz .form-input[placeholder="Nhập tiêu đề"]').value.trim();
                const description = document.querySelector('#quiz .form-input[placeholder="Nhập mô tả"]').value.trim();
                
                if (title && currentQuizSection) {
                    // Đếm số bài trắc nghiệm hiện tại trong phần này
                    const existingQuizzes = currentQuizSection.querySelectorAll('.quiz-item').length;
                    const quizNumber = existingQuizzes + 1;
                    
                    // Tạo phần tử bài trắc nghiệm mới
                    const newQuiz = document.createElement('div');
                    newQuiz.className = 'quiz-item lesson-item';
                    newQuiz.innerHTML = `
                        <div class="section-header">
                            <div class="section-title">
                                <span>Bài kiểm tra</span>
                            </div>
                            <div class="section-columns">
                                <div class="column-label">-</div>
                                <div class="column-label">-</div>
                                <div class="column-label">10</div>
                            </div>
                            <div class="section-actions">
                                <div class="action-icon">✎</div>
                                <div class="action-icon">🗑</div>
                            </div>
                        </div>
                    `;
                    
                    const deleteButton = newQuiz.querySelector('.action-icon:nth-child(2)');
		            if (deleteButton) {
		                deleteButton.addEventListener('click', function() {
		                	newQuiz.remove();
		                });
		            }
                    
                    // Thêm bài trắc nghiệm mới vào phần học hiện tại
                    const lessonActions = currentQuizSection.querySelector('.lesson-actions');
                    if (lessonActions) {
                        currentQuizSection.insertBefore(newQuiz, lessonActions);
                    } else {
                        currentQuizSection.appendChild(newQuiz);
                    }
                    
                    closeQuizModal();
                    
                   		// Reset form
                    	const titleInput = document.querySelector('#quiz .form-input[placeholder="Nhập tiêu đề"]');
                    	const descInput = document.querySelector('#quiz .form-input[placeholder="Nhập mô tả"]');
                    
                    	if (titleInput) titleInput.value = 'Bài Kiểm Tra'; // Giữ giá trị mặc định
                    	if (descInput) descInput.value = '';
                	}
            	});
        	}
     // Bắt sự kiện cho các nút "Bài trắc nghiệm" được thêm vào DOM sau này
        document.addEventListener('click', function(e) {
            if (e.target.classList.contains('lesson-button') && e.target.textContent === 'Bài trắc nghiệm') {
                const section = e.target.closest('.course-section');
                if (section) {
                    openQuizModal(section);
                }
            }
        });
        
        // Cập nhật hàm saveBtn để thêm event listener cho nút "Bài trắc nghiệm" mới
        if (saveBtn && lessonModal) {
            // Giữ nguyên code hiện tại và thêm đoạn sau vào cuối hàm xử lý sự kiện click
            const originalClickHandler = saveBtn.onclick;
            saveBtn.onclick = function(e) {
                if (originalClickHandler) {
                    originalClickHandler.call(this, e);
                }
                
                // Thêm event listener cho nút "Bài trắc nghiệm" mới tạo
                const newQuizButtons = document.querySelectorAll('.lesson-button');
                newQuizButtons.forEach(button => {
                    if (button.textContent === 'Bài trắc nghiệm' && !button.hasEventListener) {
                        button.hasEventListener = true;
                        button.addEventListener('click', function() {
                            const section = this.closest('.course-section');
                            openQuizModal(section);
                        });
                    }
                });
            };
        }
    });
    </script>
</body>
</html>