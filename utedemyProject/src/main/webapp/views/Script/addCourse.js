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
        
        let editingSectionId = null;
		let editingSectionElement = null;
		
		let editingLessonId = null;
		let editingLessonElement = null;
        
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
            editingLessonId = null;
            editingLessonElement = null;
            lessonDetailModal.classList.add('show');
            
            document.querySelector('#lesson .form-input[placeholder="Nhập tiêu đề"]').value = '';
		    document.querySelector('#lesson textarea.form-input').value = '';
		    document.querySelector('#lesson .video-input').value = '';
		    document.getElementById('freeTrialCheckbox').checked = false;
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
                        <input type="number" class="question-score" placeholder="Điểm" min="0" style="width: 80px; margin-left: 10px;">
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
			console.log('editingSectionElement:', editingSectionElement);
            saveBtn.addEventListener('click', function () {
			  const sectionTitle = document.querySelector('#lessonModal .form-input[type="text"]').value.trim();
				
			  if (sectionTitle) {
			    if (editingSectionElement) {
			      // Cập nhật UI
			      const titleDiv = editingSectionElement.querySelector('.section-title');
			      console.log('editingSectionElement1:', editingSectionElement);
			      if (titleDiv) {
			        const sectionIndex = Array.from(document.querySelectorAll('.course-section')).indexOf(editingSectionElement) + 1;
			        titleDiv.textContent = `Phần ${sectionIndex}: ${sectionTitle}`;
			      }
			
			      // Gửi request cập nhật nếu cần
			      fetch('/utedemyProject/teacher/updateSection', {
			        method: 'POST',
			        headers: {
			          'Content-Type': 'application/x-www-form-urlencoded'
			        },
			        body: `sectionId=${editingSectionId}&sectionTitle=${encodeURIComponent(sectionTitle)}`
			      })
			      .then(response => {
			        if (response.ok) {
			          // Cập nhật lại UI nếu muốn
			          const titleDiv = editingLessonElement.querySelector('.section-title');
			          if (titleDiv) {
			            const sectionIndex = Array.from(document.querySelectorAll('.course-section')).indexOf(editingSectionElement) + 1;
			            titleDiv.textContent = `Phần ${sectionIndex}: ${sectionTitle}`;
			          }
			
			          closeLessonModal();
			          editingSectionId = null;
			          editingSectionElement = null;
			        } else {
			          alert("Lỗi khi cập nhật phần học!");
			        }
			      });
			    } else {
			      // Tạo mới nếu không trong trạng thái chỉnh sửa
			      fetch('/utedemyProject/teacher/addSection', {
			        method: 'POST',
			        headers: {
			          'Content-Type': 'application/x-www-form-urlencoded'
			        },
			        body: `sectionTitle=${encodeURIComponent(sectionTitle)}`
			      })
			      .then(response => response.text()) // Đổi từ .json() sang .text() để xem nội dung
					.then(text => {
					  console.log("Phản hồi thô từ server:", text); // Kiểm tra server trả về gì
					  try {
					    const data = JSON.parse(text); // Tự parse thủ công
					    if (data && data.sectionId) {
					      addSectionToUI(data.title, data.sectionId);
					    } else {
					      alert("Phản hồi không hợp lệ từ server!");
					    }
					  } catch (error) {
					    console.error("Không thể parse JSON:", error);
					    alert("Phản hồi không phải là JSON hợp lệ!");
					  }
					})
					.catch(error => {
					  console.error("Lỗi khi thêm phần học:", error);
					  alert("Không thể thêm phần học!");
					});
			    }
			
			    closeLessonModal();
			  }
			});
        }
        function openEditSectionModal(title) {
		  const modal = document.getElementById('lessonModal');
		  const titleInput = modal.querySelector('#sectionTitle');
		  
		  if (titleInput) {
		    titleInput.value = title;
		  }
		  lessonModal.classList.add('show');
		}
        function addSectionToUI(sectionTitle, sectionId) {
		    const sectionNumber = document.querySelectorAll('.course-section').length + 1;
		    const newSection = document.createElement('div');
		    newSection.className = 'course-section';
		    newSection.innerHTML = `
		        <div class="section-header">
		            <div class="section-title">
		                Phần ${sectionNumber}: ${sectionTitle}
		            </div>
		            <div class="section-columns">
		                <div class="column-label">Học thử</div>
		                <div class="column-label">Trạng thái</div>
		                <div class="column-label">Thời lượng</div>
		            </div>
		            <div class="section-actions">
		                <div class="action-icon edit-section" data-id="${sectionId}">✎</div>
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
		
		    // Thêm sự kiện cho nút "Bài học mới"
		    const newLessonButtons = newSection.querySelectorAll('.lesson-button');
		    newLessonButtons.forEach(button => {
		        if (button.textContent === 'Bài học mới') {
		            button.addEventListener('click', function () {
		                openLessonDetailModal(newSection);
		            });
		        }
		    });
		
		    if (lessonListContainer && openModalBtn) {
		        lessonListContainer.insertBefore(newSection, openModalBtn);
		    }
		
		    // Sự kiện xóa phần học
		    const deleteIcon = newSection.querySelector('.section-actions .action-icon:nth-child(2)');
		    if (deleteIcon) {
		        deleteIcon.addEventListener('click', function () {
					const sectionId = editIcon.getAttribute('data-id');
					fetch(`/utedemyProject/teacher/deleteSection`, {
		                method: 'POST',
		                headers: {
		                    'Content-Type': 'application/x-www-form-urlencoded'
		                },
		                body: `sectionId=${sectionId}`
		            })
		            .then(response => {
		                if (response.ok) {
		                    newSection.remove(); 
		                } else {
		                    alert("Lỗi khi xóa phần học!");
		                }
		            })
		            .catch(error => {
		                console.error("Lỗi khi gọi xóa:", error);
		                alert("Không thể kết nối đến server.");
		            });
		        });
		    }
		    
		    const editIcon = newSection.querySelector('.section-actions .action-icon.edit-section');
			if (editIcon) {
			  editIcon.addEventListener('click', function () {
					const sectionId = this.getAttribute('data-id');
					const currentSectionElement = this.closest('.course-section'); // lấy phần đang sửa
					
					fetch(`/utedemyProject/teacher/editSection?sectionId=${sectionId}`)
						.then(response => response.json())
						.then(data => {
							if (data && data.title) {
								openEditSectionModal(data.title); // Gán lại giá trị lên input
								editingSectionElement = currentSectionElement; // đúng phần tử đang được chỉnh
								console.log('editingSectionElement4:', editingSectionElement);
								editingSectionId = sectionId;
							} else {
								alert("Không lấy được dữ liệu phần học!");
							}
						})
						.catch(error => {
							console.error("Lỗi khi gọi dữ liệu:", error);
							alert("Không thể kết nối đến server.");
						});
				});
			}
		}

        // Xử lý nút lưu cho modal bài học
        if (saveDetailBtn && lessonDetailModal) {
		    saveDetailBtn.addEventListener('click', function () {
		        const title = document.querySelector('#lesson .form-input[placeholder="Nhập tiêu đề"]').value.trim();
		        const description = document.querySelector('#lesson textarea.form-input').value.trim();
		        const isFreeLesson = document.getElementById('freeTrialCheckbox').checked;
		        const videoUrl = document.querySelector('#lesson .video-input').value.trim();
				
				const existingItems = currentSection.querySelectorAll('.lesson-item').length - 1;
		        const itemNumber = existingItems + 1;
		        
		        console.log("editingLessonElement = ", editingLessonElement);
		        if (title && currentSection) {
					if (editingLessonElement){
					  const titleDiv = editingLessonElement.querySelector('.section-title');
				      if (titleDiv) {
				        titleDiv.textContent = `Bài ${itemNumber}: ${title}`;
				      }
				
				      // Gửi request cập nhật nếu cần
				      fetch('/utedemyProject/teacher/updateLesson', {
				        method: 'POST',
				        headers: {
				          'Content-Type': 'application/x-www-form-urlencoded'
				        },
				        body: new URLSearchParams({
						    lessonId: editingLessonId,
						    title: title,
						    isFreeLesson: isFreeLesson,
						    description: description,
						    videoUrl: videoUrl
						})
				      })
				      .then(response => {
				        if (response.ok) {
				          // Cập nhật lại UI nếu muốn
				          const titleDiv = editingLessonElement.querySelector('.section-title');
				          if (titleDiv) {
				            titleDiv.textContent = `Bài ${itemNumber}: ${title}`;
				          }
						  const checkbox = editingLessonElement.querySelector('.free-lesson-checkbox');
						  if (checkbox) {
						     checkbox.checked = isFreeLesson;
						  }
				          closeLessonDetailModal();
				          editingLessonId = null;
				          editingLessonElement = null;
				        } else {
				          alert("Lỗi khi cập nhật phần học!");
				        }
				      });
					} 
					else{
						// Gửi dữ liệu bài học về server bằng fetch
			            const sectionId = currentSection.getAttribute('data-section-id'); // Lấy id phần học nếu có
			            fetch('/utedemyProject/teacher/addLesson', {
			                method: 'POST',
			                headers: {
			                    'Content-Type': 'application/x-www-form-urlencoded',
			                },
			                body: new URLSearchParams({
			                    title: title,
			                    description: description,
			                    isFreeLesson: isFreeLesson,
			                    videoUrl: videoUrl,
			                    sectionId: sectionId,
			                    numerical: itemNumber + 1
			                })
			            })
			            .then(response => {
			                if (response.ok) {
						        return response.json(); // Trả về object luôn
						    }
						    throw new Error('Lỗi khi gửi dữ liệu');
			            })
			            .then(data => {
						    const lessonId = data.lessonId;
						    // Xử lý hiển thị như cũ (nếu muốn hiển thị ngay trên giao diện
			                const newLesson = document.createElement('div');
			                newLesson.className = 'lesson-item';
			                newLesson.innerHTML = `
			                    <div class="section-header">
			                        <div class="section-title">
			                            <span>Bài ${itemNumber + 1}: ${title}</span>
			                        </div>
			                        <div class="section-columns">
			                            <div class="column-label">
			                                <input type="checkbox" class="free-lesson-checkbox" ${isFreeLesson ? 'checked' : ''}>
			                            </div>
			                            <div class="column-label">-</div>
			                            <div class="column-label">12</div>
			                        </div>
			                        <div class="section-actions">
			                            <div class="action-icon" data-id="${lessonId}">✎</div>
			                            <div class="action-icon">🗑</div>
			                        </div>
			                    </div>
			                `;
			
			                const deleteButton = newLesson.querySelector('.action-icon:nth-child(2)');
			                if (deleteButton) {
			                    deleteButton.addEventListener('click', function () {
			                        const lessonId = editButton.getAttribute('data-id');
									fetch(`/utedemyProject/teacher/deleteLesson`, {
						                method: 'POST',
						                headers: {
						                    'Content-Type': 'application/x-www-form-urlencoded'
						                },
						                body: `lessonId=${lessonId}`
						            })
						            .then(response => {
						                if (response.ok) {
						                    newLesson.remove();
						                } else {
						                    alert("Lỗi khi xóa phần học!");
						                }
						            })
						            .catch(error => {
						                console.error("Lỗi khi gọi xóa:", error);
						                alert("Không thể kết nối đến server.");
						            });  
			                    });
			                }
			                
			                const editButton = newLesson.querySelector('.action-icon:nth-child(1)');
							if (editButton) {
							  editButton.addEventListener('click', function () {
									const lessonId = this.getAttribute('data-id');
									const currentLessonElement = this.closest('.lesson-item'); // lấy phần đang sửa
									
									fetch(`/utedemyProject/teacher/editLesson?lessonId=${lessonId}`)
										.then(response => response.json())
										.then(data => {
											if (data && data.description && data.title && data.videoUrl) {
												openEditLessonModal(data.description, data.title, data.videoUrl, data.isFreeLesson);  // Gán lại giá trị lên input
												editingLessonElement = currentLessonElement; // đúng phần tử đang được chỉnh
												editingLessonId = lessonId;
											} else {
												alert("Không lấy được dữ liệu phần học!");
											}
										})
										.catch(error => {
											console.error("Lỗi khi gọi dữ liệu:", error);
											alert("Không thể kết nối đến server.");
										});
								});
							}

			
			                const lessonActions = currentSection.querySelector('.lesson-actions');
			                if (lessonActions) {
			                    currentSection.insertBefore(newLesson, lessonActions);
			                } else {
			                    currentSection.appendChild(newLesson);
			                }
			
			                closeLessonDetailModal();
			
			                // Reset form
			                document.querySelector('#lesson .form-input[placeholder="Nhập tiêu đề"]').value = '';
			                document.querySelector('#lesson textarea.form-input').value = '';
			                document.querySelector('#lesson .video-input').value = '';
			                document.getElementById('freeTrialCheckbox').checked = false;
			
			                console.log('Đã gửi bài học:', data);
							
						})
			            .catch(error => {
			                console.error(error);
			                alert('Đã xảy ra lỗi khi lưu bài học.');
			            });
					}
				}
		    });
		}
		function openEditLessonModal(description, title, videoUrl, isFreeLesson ) {
		  const modal = document.getElementById('lesson');
		  const descriptionInput = modal.querySelector('textarea[name="description"]');
  		  const titleInput = modal.querySelector('input[name="sectionTitle"]');
 		  const videoInput = modal.querySelector('input[name="videoUrl"]');
 		  const freeLessonCheckbox = document.getElementById('freeTrialCheckbox');
		  
		  if (titleInput && descriptionInput && videoInput && freeLessonCheckbox) {
		    descriptionInput.value = description;
		    titleInput.value = title;
		    videoInput.value = videoUrl;
		    freeLessonCheckbox.checked = isFreeLesson === true || isFreeLesson === "true";
		  }
		  lessonDetailModal.classList.add('show');
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
				const quizId = quizModal.getAttribute('data-quiz-id');
    			const isEditMode = !!quizId;
    
                const title = document.querySelector('#quiz .form-input[placeholder="Nhập tiêu đề"]').value.trim();
                const description = document.querySelector('#quiz .form-input[placeholder="Nhập mô tả"]').value.trim();
                const durationInput = document.querySelector('#quiz .quiz-duration');
				const duration = durationInput ? parseInt(durationInput.value.trim(), 10) : 0;

				console.log("⏱ Duration gửi lên:", duration);
				
                const existingItems = currentQuizSection.querySelectorAll('.lesson-item').length - 1;
				const itemNumber = existingItems + 1;
                const rawQuestions = [];
			    document.querySelectorAll('.question-container').forEach(qEl => {
			        const questionText = qEl.querySelector('.question-input')?.value.trim();
			        const questionScore = qEl.querySelector('.question-score')?.value.trim();
			        const rawAnswers = [];
			        qEl.querySelectorAll('.answer-option').forEach(aEl => {
			            const answerText = aEl.querySelector('.answer-input')?.value.trim();
			            const isCorrect = aEl.querySelector('.answer-checkbox')?.checked;
			            if (answerText !== '') {
			                rawAnswers.push({
			                    text: answerText,
			                    correct: isCorrect,
			                    
			                });
			            }
			        });
			        if (questionText !== '') {
			            rawQuestions.push({
			                question: questionText,
			                score: questionScore,
			                answers: rawAnswers        
			            });
			        }
			    });

                if (title && currentQuizSection) {
                    // Đếm số bài trắc nghiệm hiện tại trong phần này
                    const quizData = {
						id: isEditMode ? quizId : null,
				        title: title,
				        description: description,
				        duration: duration,
				        numberItem: itemNumber + 1,
				        questions: rawQuestions.map(q => ({
				            description: q.question,
				            score: q.score,
				            answers: q.answers.map(a => ({
				                content: a.text,
				                isCorrect: a.correct
				            }))
				        }))
				    };
					
					const url = isEditMode ? '/utedemyProject/teacher/updateQuiz' : '/utedemyProject/teacher/addQuiz';
					if (!isEditMode) {
					    delete quizData.id; // Nếu thêm mới thì xoá id
					} else {
					    const quizIdValue = quizModal.getAttribute('data-quiz-id');
					    // Convert to number or null if undefined/invalid
					    console.log("Quiz ID lấy từ modal:", quizIdValue);
						console.log("QuizData gửi lên server:", quizData);
						console.log("Duration gửi đi:", durationInput.value.trim());
						console.log("QuizData:", quizData);
					    if (quizIdValue && quizIdValue !== "undefined" && !isNaN(parseInt(quizIdValue))) {
						    quizData.id = parseInt(quizIdValue, 10);
						} else {
						    console.warn("⚠️ Quiz ID không hợp lệ, không gửi ID cho server.");
						    delete quizData.id;
						}
					}
                    fetch(url, {
			            method: 'POST',
			            headers: {
			                'Content-Type': 'application/json'
			            },
			            body: JSON.stringify(quizData)
			        })
		            .then(response => {
					    console.log('Trạng thái phản hồi:', response.status); // thêm dòng này
					    if (response.ok) {
					        return response.json(); 
					    }
					    return response.text().then(text => {
					        console.error('Lỗi chi tiết từ server:', text);
					        throw new Error('Lỗi khi gửi dữ liệu');
					    });
					})
		            .then(result => {         
	                    const quizId = result.quizId;
	                    
	                    if (!isEditMode) {
							// Tạo phần tử bài trắc nghiệm mới
		                    const newQuiz = document.createElement('div');
		                    newQuiz.setAttribute('data-quiz-id', quizId);
		                    
		                    newQuiz.className = 'quiz-item lesson-item';
		                    newQuiz.innerHTML = `
		                        <div class="section-header">
		                            <div class="section-title">
		                                <span>Bài ${itemNumber + 1}: ${title}</span>
		                            </div>
		                            <div class="section-columns">
		                                <div class="column-label">-</div>
		                                <div class="column-label">-</div>
		                                <div class="column-label">-</div>
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
				                	const quizId = newQuiz.getAttribute('data-quiz-id'); 
									fetch(`/utedemyProject/teacher/deleteQuiz`, {
						                method: 'POST',
						                headers: {
						                    'Content-Type': 'application/x-www-form-urlencoded'
						                },
						                body: `quizId=${quizId}`
						            })
						            .then(response => {
						                if (response.ok) {
						                    newQuiz.remove();
						                } else {
						                    alert("Lỗi khi xóa phần học!");
						                }
						            })
						            .catch(error => {
						                console.error("Lỗi khi gọi xóa:", error);
						                alert("Không thể kết nối đến server.");
						            });	
				                });
				            }
				            
				            const editButton = newQuiz.querySelector('.action-icon:nth-child(1)');
							if (editButton) {
							    editButton.addEventListener('click', function () {
							        const quizId = newQuiz.getAttribute('data-quiz-id');
							        if (quizId) {
							            fetch(`/utedemyProject/teacher/editQuiz?quizId=${quizId}`)
							                .then(res => res.json())
							                .then(data => {
							                    // Gọi hàm điền dữ liệu vào modal
							                    populateQuizModal(data);
							                    openQuizModal(currentQuizSection);
							                })
							                .catch(err => console.error('Lỗi khi lấy dữ liệu quiz:', err));
							        }
							    });
							}
		                    
		                    // Thêm bài trắc nghiệm mới vào phần học hiện tại
		                    const lessonActions = currentQuizSection.querySelector('.lesson-actions');
		                    if (lessonActions) {
		                        currentQuizSection.insertBefore(newQuiz, lessonActions);
		                    } else {
		                        currentQuizSection.appendChild(newQuiz);
		                    }
						}
						else{
							// Tìm phần tử quiz đang hiển thị trên giao diện bằng quizId
						    const existingQuiz = document.querySelector(`.quiz-item[data-quiz-id="${quizId}"]`);
						
						    if (existingQuiz) {
						        // Cập nhật lại phần tiêu đề bài trắc nghiệm
						        const titleElement = existingQuiz.querySelector('.section-title span');
						        if (titleElement) {
						            titleElement.textContent = `Bài ${itemNumber + 1}: ${title}`;
						        }
						
						        // (Tuỳ chọn) Nếu bạn muốn cập nhật thêm mô tả, số lượng câu hỏi, v.v... bạn có thể thêm ở đây
						    }
						}
	                    closeQuizModal();
	                    quizModal.removeAttribute('data-quiz-id');
	                   	// Reset form
	                    const titleInput = document.querySelector('#quiz .form-input[placeholder="Nhập tiêu đề"]');
	                    const descInput = document.querySelector('#quiz .form-input[placeholder="Nhập mô tả"]');
	                    
	                    	if (titleInput) titleInput.value = 'Bài Kiểm Tra'; // Giữ giá trị mặc định
	                    	if (descInput) descInput.value = '';
	                	})
	                	.catch(error => {
			                console.error(error);
			                alert('Đã xảy ra lỗi khi lưu bài học.');
			            });
	                }
                	
            	});
        	}
        	
        	function populateQuizModal(data) {
			    // Điền tiêu đề, mô tả
			    const titleInput = document.querySelector('#quiz .form-input[placeholder="Nhập tiêu đề"]');
			    const descInput = document.querySelector('#quiz .form-input[placeholder="Nhập mô tả"]');
			    const durationInput = document.querySelector('#quiz .quiz-duration');
			    if (titleInput) titleInput.value = data.title;
			    if (descInput) descInput.value = data.description;
			    if (durationInput) durationInput.value = data.duration || 0;
			    
			    quizModal.setAttribute('data-quiz-id', data.id);
				console.log("QuizData gửi lên server:", data.id);
			    // Xoá toàn bộ câu hỏi cũ
			    document.querySelectorAll('.question-container').forEach(q => q.remove());
			
			    // Lặp qua các câu hỏi để tạo lại
			    data.questions.forEach(q => {
			        addQuestionBtn.click(); // Nhấn nút để tạo câu hỏi mới
			        const lastQuestion = document.querySelectorAll('.question-container');
			        const newQ = lastQuestion[lastQuestion.length - 1];
			
			        newQ.querySelector('.question-input').value = q.description;
			        newQ.querySelector('.question-score').value = q.score;
			
			        const answersContainer = newQ.querySelector('.answers-container');
			        answersContainer.innerHTML = ''; // Xoá sẵn 4 answer trống
			
			        q.answers.forEach(a => {
			            const answerEl = document.createElement('div');
			            answerEl.className = 'answer-option';
			            answerEl.innerHTML = `
			                <input type="checkbox" class="answer-checkbox" ${a.isCorrect ? 'checked' : ''}>
			                <input type="text" class="answer-input" value="${a.content}" placeholder="Nhập câu trả lời">
			                <button class="delete-answer">🗑️</button>
			            `;
			            answersContainer.appendChild(answerEl);
			
			            // Gắn sự kiện xóa
			            answerEl.querySelector('.delete-answer').addEventListener('click', function () {
			                answerEl.remove();
			            });
			        });
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