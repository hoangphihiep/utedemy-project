<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<body>
	<div class="content-box">
		<div class="content-header">Thông tin cơ bản</div>
		<div class="content-body">
			<div class="objective-intro">Trang tổng quan khóa học của bạn rất quan trọng đối với thành công của bạn trên Unica.</div>
			<div class="objective-intro">Nếu được thực hiện đúng, trang này cũng có thể giúp bạn hiển thị trong các công cụ tìm kiếm như Google.</div>
			<div class="objective-intro">Khi bạn hoàn thành phần này, hãy nghĩ đến việc tạo Trang tổng quan khóa học hấp dẫn để người muốn đăng ký học.</div>
			<div class="objective-intro">Tìm hiểu thêm về <a href="#">cách tạo trang tổng quan khóa học của bạn</a> và <a href="#">các tiêu chuẩn tối đề khóa học</a>.</div>

			<div class="form-group">
				<label class="form-label">Tiêu đề khóa học <span
					class="required">*</span></label> <input type="text" class="form-input"
					value="Thành Thạo Excel Từ Cơ Bản Đến Nâng Cao">
				<div class="char-count">21</div>
				<div class="form-hint">Tiêu đề của bạn không những phải thu
					hút sự chú ý, chứa nhiều thông tin mà còn được tối ưu hóa để dễ tìm
					kiếm</div>
			</div>

			<div class="form-group">
				<label class="form-label">Tên khóa học (tiếng Anh, để cấp
					chứng chỉ)</label> <input type="text" class="form-input">
				<div class="char-count">60</div>
			</div>

			<div class="form-group">
				<label class="form-label">Mô tả ngắn <span class="required">*</span></label>
				<textarea class="form-input" rows="5"></textarea>
				<div class="char-count">120</div>
			</div>
			
			<div class="form-row">
				<div class="form-column">
					<label class="form-label">Thể loại <span class="required">*</span></label>
					<select class="dropdown">
						<option>Tin Học Văn Phòng</option>
					</select>
				</div>
		
				<div class="form-column">
					<label class="form-label">Danh mục con <span class="required">*</span></label>
					<select class="dropdown">
						<option></option>
					</select>
				</div>
		
				<div class="form-column">
					<label class="form-label">Tags <span class="required">*</span></label>
					<select class="dropdown">
						<option></option>
					</select>
				</div>
			</div>
			<!-- Phần giới thiệu khóa học mới được thêm vào -->
			<div class="form-group">
				<label class="form-label">Giới thiệu khóa học <span
					class="required">*</span></label>
				<div class="editor-toolbar">
					<button class="toolbar-button" title="In đậm">B</button>
					<button class="toolbar-button" title="In nghiêng">
						<i>I</i>
					</button>
					<button class="toolbar-button" title="Danh sách">≡</button>
				</div>
				<div class="editor-content" contenteditable="true">
					Giới thiệu chi tiết về khóa học, trên 300 từ<br> -WHAT: học gì?,
					giải quyết vấn đề gì?<br> -WHY: Tại sao bạn nên mua khóa học này
					của tôi?<br> -Kêu gọi hành động: Hãy đăng ký khóa học ngay hôm
					nay!
				</div>
			</div>
			<!-- Add this inside the main-content div, after the existing form-group for course introduction -->
			<div class="form-group">
				<label class="form-label">Ảnh khóa học <span class="required">*</span></label>
				<div class="image-upload-container">
					<div class="image-preview">
						<img src="/api/placeholder/800/450" alt="placeholder" />
					</div>
					<div class="image-upload-info">
						<p>Tải hình ảnh khóa học lên tại đây.</p>
						<p>Hình
							ảnh phải đáp ứng tiêu chuẩn chất lượng hình ảnh khóa học.</p>
						<p>Hướng dẫn
							quan trọng: 800x450 pixel; .jpg, .jpeg, .gif, hoặc .png. </p>
						<p>và không
							có chữ trên hình ảnh.</p>
						<div class="upload-controls">
							<button class="upload-button">Chọn tệp</button>
							<span class="file-status">Không có tệp nào được chọn</span>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="form-label">Video quảng cáo</label>
				<div class="video-upload-container">
					<div class="video-preview">
						<div class="video-placeholder"></div>
					</div>
					<div class="video-upload-info">
						<p>Video quảng cáo của bạn là một cách nhanh chóng và hấp dẫn để
							</p>
						<p>
							học viên xem trước những gì họ sẽ học trong khóa học của bạn.</p>
						<p>Học
							viên quan tâm đến khóa học của bạn có nhiều khả năng</p>
						<p>ghi danh hơn
							nếu video quảng cáo của bạn được thực hiện tốt.</p>
						<div class="form-input-container">
							<input type="text" class="form-input"
								placeholder="Gắn link youtube/vimeo">
						</div>
					</div>
				</div>
			</div>
			<!-- Add this inside the main-content div, after the existing form-group for course introduction -->
			<div class="form-group">
				<label class="form-label">Ảnh TMĐT <span class="required">*</span></label>
				<div class="image-upload-container">
					<div class="image-preview">
						<img src="/api/placeholder/800/450" alt="placeholder" />
					</div>
					<div class="image-upload-info">
						<p>Tải hình ảnh khóa học lên tại đây.</p>
						<p>Hình
							ảnh phải đáp ứng tiêu chuẩn chất lượng hình ảnh khóa học.</p>
						<p>Hướng dẫn
							quan trọng: 800x450 pixel; .jpg, .jpeg, .gif, hoặc .png. </p>
						<p>và không
							có chữ trên hình ảnh.</p>
						<div class="upload-controls">
							<button class="upload-button">Chọn tệp</button>
							<span class="file-status">Không có tệp nào được chọn</span>
						</div>
					</div>
				</div>
			</div>
			

		</div>
		
	</div>
	
</body>
</html>