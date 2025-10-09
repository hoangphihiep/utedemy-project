<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<body>
	<div class="content-box">
        <div class="content-header">Danh sách bài học</div>
        <div class="lesson-list-container">
            

            <c:if test="${empty course.sections}">
                <div class="empty-state">Chưa có bài học nào được thêm vào khóa học.</div>
            </c:if>

            <c:forEach var="section" items="${course.sections}" varStatus="sectionStatus">
                <div class="course-section">
                    <div class="section-header">
                        <div class="section-title">
                            Phần ${sectionStatus.index + 1}: ${section.title}
                        </div>
                        <div class="section-columns">
                            <div class="column-label">Học thử</div>
                            <div class="column-label">Trạng thái</div>
                            <div class="column-label">Thời lượng</div>
                        </div>
                        <div class="section-actions">
                            <div class="action-icon edit-section" data-id="${section.id}">✎</div>
                            <div class="action-icon delete-section">🗑</div>
                        </div>
                    </div>

                    <!-- Hiển thị danh sách bài học trong section -->
                    <c:forEach var="lesson" items="${section.lessons}">
                        <div class="section-header">
							<div class="section-title">
								<span>Bài ${lesson.numberItem}: ${lesson.title}</span>
							</div>
							<div class="section-columns">
								<div class="column-label">
									<input type="checkbox" class="free-lesson-checkbox"
										${lesson.isFreeLesson ? 'checked' : ''}>
								</div>
								<div class="column-label">-</div>
								<div class="column-label">12</div>
							</div>
							<div class="section-actions">
								<div class="action-icon edit-lesson" data-id="${lesson.id}">✎</div>
								<div class="action-icon delete-lesson">🗑</div>
							</div>
                        </div>
                    </c:forEach>

                    <!-- Hiển thị danh sách quiz trong section -->
                    <c:forEach var="quiz" items="${section.quizs}">
						<div class="section-header">
							<div class="section-title">
								<span>Bài ${quiz.numberItem}: ${quiz.title}</span>
							</div>
							<div class="section-columns">
								<div class="column-label">-</div>
								<div class="column-label">-</div>
								<div class="column-label">-</div>
							</div>
							<div class="section-actions">
								<div class="action-icon edit-quiz " data-id="${quiz.id}">✎</div>
								<div class="action-icon delete-quiz">🗑</div>
							</div>
						</div>
                    </c:forEach>

                    <div class="lesson-actions">
                        <button class="lesson-button">Bài học mới</button>
                        <button class="lesson-button">Bài trắc nghiệm</button>
                    </div>

                    <div class="lesson-info">
                        'Mỗi bài học nên có một bài trắc nghiệm để củng cố kiến thức'
                    </div>
                </div>
            </c:forEach>
            <button class="add-lesson-button" id="openModal">+ Phần học mới</button>
        </div>
    </div>

    <!-- Modal Dialog cho phần học mới -->
    <div class="modal" id="lessonModal">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">Tạo phần học</h3>
                <button class="close-button" id="closeModal">×</button>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label class="form-label">Tiêu đề<span class="required">*</span></label>
                    <input type="text" id="sectionTitle" name="sectionTitle" class="form-input">
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn-cancel" id="cancelModal">Hủy</button>
                <button type="button" class="btn-save">Lưu</button>
            </div>
        </div>
    </div>

    <!-- Modal Dialog cho bài học mới -->
    <div class="modal" id="lesson"> 
        <div class="modal-content"> 
            <div class="modal-header"> 
                <h3 class="modal-title">Cập nhật bài học</h3> 
                <button class="close-button" id="closeModal">×</button> 
            </div> 
            <div class="modal-body"> 
                <div class="form-group"> 
                    <label class="form-label">Tiêu đề <span class="required">*</span></label> 
                    <input type="text" class="form-input" placeholder="Nhập tiêu đề" name="sectionTitle"> 
                </div> 

                <div class="form-group"> 
                    <label class="form-label">Mô tả ngắn</label> 
                    <textarea class="form-input" rows="4" placeholder="Nhập mô tả" name="description"></textarea> 
                </div>

                <div class="form-group">
                    <label class="checkbox-label">
                        <input type="checkbox" id="freeTrialCheckbox"> Học thử miễn phí
                    </label>
                </div>

                <div class="form-group">
                    <label class="form-label">Nhập link Youtube/vimeo</label>
                    <div class="video-input-container">
                        <input type="text" class="form-input video-input" placeholder="vd: https://www.youtube.com/embed?v=7fq7rQpOHuM" name="videoUrl">
                    </div>
                </div>
            </div> 
            <div class="modal-footer"> 
                <button class="btn-cancel" id="cancelModal">Hủy</button> 
                <button class="btn-save">Lưu bài học</button> 
            </div> 
        </div> 
    </div>

    <!-- Modal Dialog cho bài trắc nghiệm -->
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

          <div class="form-group">
            <label class="form-label">Thời gian làm bài</label>
            <input type="number" class="form-input quiz-duration" placeholder="Thời gian làm bài (phút)">
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

    <script nonce="${requestScope.nonce}" src="/utedemyProject/views/Script/editCourse.js"></script>
</body>
</html>