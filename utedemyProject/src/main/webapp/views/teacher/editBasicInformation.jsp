<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
<body>
<script src="https://cdn.ckeditor.com/4.21.0/standard/ckeditor.js"></script>
	<div class="content-box">
		<div class="content-header">Thông tin cơ bản</div>
		<div class="content-body">
			<div class="objective-intro">Trang tổng quan khóa học của bạn rất quan trọng đối với thành công của bạn trên Unica.</div>
			<div class="objective-intro">Nếu được thực hiện đúng, trang này cũng có thể giúp bạn hiển thị trong các công cụ tìm kiếm như Google.</div>
			<div class="objective-intro">Khi bạn hoàn thành phần này, hãy nghĩ đến việc tạo Trang tổng quan khóa học hấp dẫn để người muốn đăng ký học.</div>
			<div class="objective-intro">Tìm hiểu thêm về <a href="#">cách tạo trang tổng quan khóa học của bạn</a> và <a href="#">các tiêu chuẩn tối đề khóa học</a>.</div>

			<div class="form-group">
			    <label class="form-label">Tiêu đề khóa học <span class="required">*</span></label>
			    <input type="text" class="form-input" name="courseTitle" value="${course.courseName}">
			    <div class="form-hint">Tiêu đề của bạn không những phải thu hút sự chú ý, chứa nhiều thông tin mà còn được tối ưu hóa để dễ tìm kiếm</div>
			</div>

			<div class="form-group">
			    <label class="form-label">Mô tả ngắn <span class="required">*</span></label>
			    <textarea class="form-input" rows="5"><c:out value="${course.courseDetail.description}" /></textarea>
			</div>
			
			<div class="form-row">
			    <div class="form-column">
			        <label class="form-label">Thể loại <span class="required">*</span></label>
			        <select class="dropdown" name="courseTypeId">
			            <c:forEach items="${listCourseType}" var="type">
			                <option value="${type.id}" ${courseType.id == type.id ? 'selected' : ''}>${type.courseTypeName}</option>
			            </c:forEach>
			        </select>
			    </div>
			    <div class="form-column">
				  <label class="form-label">Giá <span class="required">*</span></label>
				  <input class="dropdown" type="text" name="courseName" value="${course.coursePrice}" />
				</div>
			</div>
			
			
			<!-- Phần giới thiệu khóa học mới được thêm vào -->
			<div class="form-group">
				<label class="form-label">Giới thiệu khóa học <span
					class="required">*</span></label>
				<form action="saveCourse.jsp" method="post">
				    <textarea name="courseIntroduction" id="courseIntroduction"></textarea>
				</form>
			</div>
			<!-- Add this inside the main-content div, after the existing form-group for course introduction -->
			<div class="form-group">
				<c:if test="${cate.images.substring(0,5) != 'https' }">
					<c:url value="/image?fname=${course.courseDetail.courseImage}" var="imgUrl"></c:url>
				</c:if>
				<c:if test="${cate.images.substring(0,5) == 'https' }">
					<c:url value="${course.courseDetail.courseImage}" var="imgUrl"></c:url>
				</c:if>
				
				<label class="form-label">Ảnh khóa học <span class="required">*</span></label>
				<div class="image-upload-container">
					<div class="image-preview">
						<img id="previewImage" src="${imgUrl}" alt="placeholder" />
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
							<input type="file" id="imageUpload" name="image" style="display: none;" accept="image/*" onchange="previewImage(event, 'previewImage', 'placeholderText')">
							<input type="hidden" name="image1Url" id="image1Url" value="${imgUrl}">
							<button class="upload-button" onclick="document.getElementById('imageUpload').click()">Chọn tệp</button>
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
			      <p>Video quảng cáo của bạn là một cách nhanh chóng và hấp dẫn để</p>
			      <p>học viên xem trước những gì họ sẽ học trong khóa học của bạn.</p>
			      <p>Học viên quan tâm đến khóa học của bạn có nhiều khả năng</p>
			      <p>ghi danh hơn nếu video quảng cáo của bạn được thực hiện tốt.</p>
			      <div class="form-input-container">
			        <input type="text" class="form-input" placeholder="Gắn link youtube/vimeo" value="${course.courseDetail.courseVideo}">
			      </div>
			    </div>
			  </div>
			</div>
		</div>
		
	</div>
	<script>
    CKEDITOR.replace('courseIntroduction');
    
// Function to preview images
function previewImage(event, previewId, placeholderId) {
  const placeholderText = document.getElementById(placeholderId);
  const imagePreview = document.getElementById(previewId);
  const file = event.target.files[0];

  if (file) {
    const reader = new FileReader();
    reader.onload = function(e) {
      imagePreview.src = e.target.result; // Gán đường dẫn ảnh cho img
      imagePreview.style.display = 'block'; // Hiển thị ảnh xem trước
    };
    reader.readAsDataURL(file);
  }
}

document.addEventListener('DOMContentLoaded', function() {
	  const videoInput = document.querySelector('.video-upload-info .form-input');
	  const videoPlaceholder = document.querySelector('.video-placeholder');
	  
	  if (!videoInput || !videoPlaceholder) {
	    console.error('Không tìm thấy các phần tử cần thiết');
	    return;
	  }
	  
	  function processVideoUrl(url) {
	    console.log('Processing URL:', url);
	    
	    if (url) {
	      // Xử lý URL YouTube
	      const youtubeRegex = /(?:youtube\.com\/(?:[^\/\n\s]+\/\S+\/|(?:v|e(?:mbed)?)\/|\S*?[?&]v=)|youtu\.be\/)([a-zA-Z0-9_-]{11})/;
	      const youtubeMatch = url.match(youtubeRegex);
	      
	      // Xử lý URL Vimeo
	      const vimeoRegex = /(?:vimeo\.com\/(?:channels\/(?:\w+\/)?|groups\/(?:[^\/]*)\/videos\/|album\/(?:\d+)\/video\/|)(\d+)(?:$|\/|\?))/;
	      const vimeoMatch = url.match(vimeoRegex);
	      
	      if (youtubeMatch && youtubeMatch[1]) {
	    	  const youtubeId = youtubeMatch[1];
	    	  videoPlaceholder.innerHTML = '<iframe width="100%" height="100%" ' +
	    	    'src="https://www.youtube-nocookie.com/embed/' + youtubeId + '?origin=' + window.location.origin + '&enablejsapi=1" ' +
	    	    'frameborder="0" ' +
	    	    'allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" ' +
	    	    'allowfullscreen></iframe>';
	    	} else if (vimeoMatch && vimeoMatch[1]) {
	        // Vimeo với tham số do-not-track và origin
	        const vimeoId = vimeoMatch[1];
	        videoPlaceholder.innerHTML = `
	          <iframe width="100%" height="100%" 
	          src="https://player.vimeo.com/video/${vimeoId}?dnt=1&origin=${window.location.origin}" 
	          frameborder="0" 
	          allow="autoplay; fullscreen; picture-in-picture" 
	          allowfullscreen></iframe>
	        `;
	      }
	    } else {
	      // Xóa video nếu input trống
	      videoPlaceholder.innerHTML = '';
	    }
	  }

	  // Lắng nghe sự kiện input
	  videoInput.addEventListener('input', function() {
	    processVideoUrl(this.value.trim());
	  });
	  
	  // Lắng nghe sự kiện paste
	  videoInput.addEventListener('paste', function(e) {
	    setTimeout(() => {
	      processVideoUrl(this.value.trim());
	    }, 100);
	  });
	  
	  // Kiểm tra nếu đã có URL
	  if (videoInput.value.trim()) {
	    processVideoUrl(videoInput.value.trim());
	  }
	});
</script>
</body>
</html>