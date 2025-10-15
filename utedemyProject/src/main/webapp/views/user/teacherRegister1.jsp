<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <style nonce="${requestScope.nonce}">
        /* Thêm style cho nút bị vô hiệu hóa */
        .save-btn:disabled {
            opacity: 0.6;
            cursor: not-allowed;
        }
    </style>
</head>
<body>
    <div class="container">
        <p style="margin-bottom: 20px; color: #e74c3c;">Lưu ý: Cập nhật đầy đủ thông tin hợp đồng và tích chọn "Tôi đồng ý với những điều khoản dịch vụ dưới đây của Unica." để trở thành giảng viên của UNICA.</p>

        <h2 class="form-title">HỢP ĐỒNG ĐIỆN TỬ</h2>

        <form action="${pageContext.request.contextPath}/user/contract" method="get" style="display:inline;">
		    <button type="submit" class="btn btn-secondary">Xem trước hợp đồng</button>
		</form>

        <form action="${pageContext.request.contextPath}/user/registerTeacher" method="POST" style="display:inline;" enctype="multipart/form-data">
        <input type="hidden" name="csrfToken" value="${csrfToken}" />
        <!-- Thêm phần mô tả về giảng viên -->
        <div class="form-group description-section" style="margin-top: 20px;">
		    <div class="form-control description-control">
		        <label class="description-label">Mô tả về bản thân <span class="required">*</span></label>
		        <div class="description-container">
		            <textarea 
		                name="description" 
		                id="teacher-description" 
		                rows="5" 
		                placeholder="Nhập mô tả về kinh nghiệm, chuyên môn và thành tựu của bạn"
		                class="enhanced-textarea"></textarea>
		            <div class="character-counter">
		                <span id="char-count">0</span>/1000 ký tự
		            </div>
		        </div>
		        <div class="help-text-container">
		            <p class="help-text"><i class="info-icon">ℹ️</i> Mô tả này sẽ hiển thị trên trang giới thiệu giảng viên</p>
		            <p class="suggestion-text">Gợi ý: Nêu rõ bằng cấp, kinh nghiệm giảng dạy và thành tựu nổi bật</p>
		        </div>
		    </div>
		</div>

        <div class="form-group">
            <div class="form-control">
                <label>Mã số thuế <span class="required">*</span></label>
                <input type="text" name="taxCode" placeholder="Nhập mã số thuế cá nhân của bạn">
                <p class="help-text">Bạn chưa có mã số thuế cá nhân? Unico sẽ đăng ký giúp bạn</p>
                <p class="help-text">Đăng ký làm mã số thuế cá nhân tại: <a href="http://mstcn.unica.vn" target="_blank">http://mstcn.unica.vn</a></p>
            </div>

            <div class="form-control">
                <label>Số căn cước công dân <span class="required">*</span></label>
                <input type="text" name="idCardNumber" placeholder="Nhập số căn cước công dân của bạn">
            </div>
        </div>

        <div class="form-group">
            <div class="form-control">
                <label>Thông tin liên hệ <span class="required">*</span></label>
                <input type="text" name="socialUrl" placeholder="Nhập địa chỉ mạng xã hội">
            </div>

            <div class="form-control">
                <label>Ngày cấp <span class="required">*</span></label>
                <input type="date" name="issueDate" placeholder="Ngày cấp">
            </div>
        </div>

        <div class="form-group">
            <div class="form-control">
                <label>Nơi cấp <span class="required">*</span></label>
                <input type="text" name="issuePlace" placeholder="Nơi cấp">
            </div>
        </div>

        <div class="form-group">
            <div class="form-control">
                <c:if test="${cate.images.substring(0,5) != 'https' }">
                    <c:url value="/image?fname=${cate.images }" var="imgUrl"></c:url>
                </c:if>
                <c:if test="${cate.images.substring(0,5) == 'https' }">
                    <c:url value="${cate.images }" var="imgUrl"></c:url>
                </c:if>

                <label>Ảnh CCCD mặt trước <span class="required">*</span></label>
                <div class="upload-container">
                    <div class="upload-preview-area">
                        <div class="image-preview">
                            <img id="previewImage" height="500" width="2000" src="${imgUrl}" alt="Preview" />
                            <div id="placeholderText" class="placeholder-text" style="${not empty imgUrl ? 'display:none' : ''}">
                                <i class="upload-icon">📷</i>
                                <p>Chưa có ảnh</p>
                            </div>
                        </div>
                    </div>
                    <div class="upload-controls">
                        <input type="file" id="imageUpload" name="image" 
                            value="${imgUrl}" style="display: none;" accept="image/*"
                            onchange="previewImage(event, 'previewImage', 'placeholderText')">
                        <input type="hidden" name="image1Url" value="${imgUrl}">
                        <button type="button" class="upload-button" onclick="document.getElementById('imageUpload').click()">
                            <i class="upload-btn-icon"></i> Chọn tệp
                        </button>
                    </div>
                </div>
            </div>

            <div class="form-control">
                <c:if test="${cate.backImages.substring(0,5) != 'https' }">
                    <c:url value="/image?fname=${cate.backImages }" var="backImgUrl"></c:url>
                </c:if>
                <c:if test="${cate.backImages.substring(0,5) == 'https' }">
                    <c:url value="${cate.backImages }" var="backImgUrl"></c:url>
                </c:if>

                <label>Ảnh CCCD mặt sau <span class="required">*</span></label>
                <div class="upload-container">
                    <div class="upload-preview-area">
                        <div class="image-preview">
                            <img id="previewBackImage" height="500" width="2000" src="${backImgUrl}" alt="Preview" />
                            <div id="placeholderBackText" class="placeholder-text" style="${not empty backImgUrl ? 'display:none' : ''}">
                                <i class="upload-icon">📷</i>
                                <p>Chưa có ảnh</p>
                            </div>
                        </div>
                    </div>
                    <div class="upload-controls">
                        <input type="file" id="backImageUpload" name="backImage" 
                            value="${backImgUrl}" style="display: none;" accept="image/*"
                            onchange="previewImage(event, 'previewBackImage', 'placeholderBackText')">
                        <input type="hidden" name="image2Url" value="${backImgUrl}">
                        <button type="button" class="upload-button" onclick="document.getElementById('backImageUpload').click()">
                            <i class="upload-btn-icon"></i> Chọn tệp
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Phần 2: Tài khoản nhận tiền -->
        <h2 class="form-title" style="margin-top: 40px;">Tài khoản nhận tiền</h2>

        <div class="form-group">
            <div class="form-control">
                <label>Ngân hàng <span class="required">*</span></label>
                <input type="text" name="bankName" placeholder="Tên ngân hàng">
            </div>

            <div class="form-control">
                <label>Số tài khoản <span class="required">*</span></label>
                <input type="text" name="bankAccount" placeholder="Số tài khoản">
            </div>
        </div>

        <!-- Thêm ô checkbox đồng ý điều khoản -->
        <div class="form-group" style="margin-top: 20px; margin-left: 330px;">
            <div class="form-control checkbox-control" style="display: flex; align-items: center;">
                <input type="checkbox" id="terms-agreement" name="termsAgreement" required style="margin-right: 10px; flex-shrink: 0;">
                <label for="terms-agreement" style="margin: 0;">Tôi đồng ý với những điều khoản dịch vụ dưới đây của Unica.</label>
            </div>
        </div>

        <p class="warning">Lưu ý: Tên người dùng phải khớp với tên tài khoản ngân hàng và tên trong CCCD</p>

        <button type="submit" id="saveButton" class="save-btn" disabled>
            <i>💾</i> Lưu
        </button>
    </form>

    </div>

    <script> 
    document.addEventListener('DOMContentLoaded', function() {
        // Lấy phần tử checkbox và nút Lưu
        const termsCheckbox = document.getElementById('terms-agreement');
        const saveButton = document.getElementById('saveButton');
        
        // Thêm sự kiện lắng nghe khi checkbox thay đổi trạng thái
        termsCheckbox.addEventListener('change', function() {
            // Nếu checkbox được tích, kích hoạt nút Lưu, ngược lại vô hiệu hóa
            saveButton.disabled = !this.checked;
        });
        
        // Đếm ký tự trong phần mô tả
        const teacherDescription = document.getElementById('teacher-description');
        const charCount = document.getElementById('char-count');
        
        if (teacherDescription && charCount) {
            teacherDescription.addEventListener('input', function() {
                charCount.textContent = this.value.length;
            });
        }
        
        // Xử lý cho ảnh mặt trước
        const imageUpload = document.getElementById('imageUpload');
        if (imageUpload) {
          imageUpload.addEventListener('change', function(event) {
            const placeholderText = document.getElementById('placeholderText');
            const imagePreview = document.getElementById('previewImage');
            const file = event.target.files[0];
            
            if (file) {
              const reader = new FileReader();
              reader.onload = function(e) {
                imagePreview.src = e.target.result;
                imagePreview.style.display = 'block';
                placeholderText.style.display = 'none';
              };
              reader.readAsDataURL(file);
            }
          });
        }
        
        // Xử lý cho ảnh mặt sau
        const backImageUpload = document.getElementById('backImageUpload');
        if (backImageUpload) {
          backImageUpload.addEventListener('change', function(event) {
            const placeholderText = document.getElementById('placeholderBackText');
            const imagePreview = document.getElementById('previewBackImage');
            const file = event.target.files[0];
            
            if (file) {
              const reader = new FileReader();
              reader.onload = function(e) {
                imagePreview.src = e.target.result;
                imagePreview.style.display = 'block';
                placeholderText.style.display = 'none';
              };
              reader.readAsDataURL(file);
            }
          });
        }
      });
      
    function previewImage(event, previewId, placeholderId) {
        const preview = document.getElementById(previewId);
        const placeholder = document.getElementById(placeholderId);
        const file = event.target.files[0];
        
        if (file) {
            const reader = new FileReader();
            reader.onload = function(e) {
                preview.src = e.target.result;
                preview.style.display = 'block';
                placeholder.style.display = 'none';
            };
            reader.readAsDataURL(file);
        }
    }
	</script>
</body>
</html>