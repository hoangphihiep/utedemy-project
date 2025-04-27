// lấy API iframe youtube
var tag = document.createElement('script');
tag.src = "https://www.youtube.com/iframe_api";
var firstScriptTag = document.getElementsByTagName('script')[0];
firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
let player;

// Extract YouTube video ID from URL
	  function extractYouTubeVideoId(url) {
	    if (!url) return null;
	    
	    // Match various YouTube URL formats
	    const regExp = /^.*(youtu.be\/|v\/|u\/\w\/|embed\/|watch\?v=|\&v=)([^#\&\?]*).*/;
	    const match = url.match(regExp);
	    
	    if (match && match[2].length == 11) {
	      return match[2];
	    }
	    return null;
	  }
	  
	  function updateNavigationButtons(index, total) {
	    const btnPrev = document.querySelector(".btn-prv");
	    const btnNext = document.querySelector(".btn-next");
	    if (btnPrev) btnPrev.disabled = index <= 0;
	    if (btnNext) btnNext.disabled = index >= total - 1;
	  }

document.addEventListener("DOMContentLoaded", function () {
	const autoplayCheckbox = document.querySelector(".autoplay-toggle input[type='checkbox']");
	  
	  // Function to initialize YouTube player API
	  window.onYouTubeIframeAPIReady = function() {
	    initializeYouTubePlayer();
	  };
	  
	  // Initialize player when switching lessons
	  function initializeYouTubePlayer() {
	    const iframe = document.getElementById("lesson-video");
	    if (!iframe) return;
	    
	    // Get the video ID from src attribute
	    const videoSrc = iframe.src;
	    const videoId = extractYouTubeVideoId(videoSrc);
	    
	    if (!videoId) return;
	    
	    // Replace the iframe with a div that YouTube API can use
	    const videoContainer = iframe.parentNode;
	    const videoPlaceholder = document.createElement('div');
	    videoPlaceholder.id = 'youtube-player';
	    videoContainer.replaceChild(videoPlaceholder, iframe);
	    
	    // Create new player
	    player = new YT.Player('youtube-player', {
	      height: iframe.height || '390',
	      width: iframe.width || '640',
	      videoId: videoId,
	      events: {
	        'onStateChange': onPlayerStateChange
	      }
	    });
	  }
	  
	  // Handle player state changes
	  function onPlayerStateChange(event) {
	    // State 0 means the video has ended
	    if (event.data === 0) {
	      // Check if autoplay is enabled
	      if (autoplayCheckbox && autoplayCheckbox.checked) {
	        // If autoplay is checked, click the next button
	        const nextButton = document.querySelector(".btn-next");
	        if (nextButton && !nextButton.disabled) {
	          nextButton.click();
	        }
	      }
	    }
	  }
	  
	
	//Kích hoạt CKEditor
	CKEDITOR.replace('comment', {
	  toolbar: [
		{ name: 'styles', items: ['Styles', 'Format']},
	    { name: 'basicstyles', items: [ 'Bold', 'Italic' ] },
	    { name: 'paragraph', items: [ 'NumberedList', 'BulletedList' ] },
	    { name: 'links', items: [ 'Link', 'Unlink' ] },
	    { name: 'insert', items: [ 'Image' ] },
	    { name: 'clipboard', items: [ 'Undo', 'Redo' ] }
	  ],
	  removePlugins: 'elementspath',
	  resize_enabled: false
	});
	
  // Tab switching
  const tabButtons = document.querySelectorAll(".tab-menu div");
  const tabContents = document.querySelectorAll(".tab-content");

  tabButtons.forEach(button => {
    button.addEventListener("click", function () {
      const tabName = this.getAttribute("data-tab");

      tabButtons.forEach(btn => btn.classList.remove("active"));
      this.classList.add("active");

      tabContents.forEach(content => {
        if (content.getAttribute("data-content") === tabName) {
          content.classList.add("active");
        } else {
          content.classList.remove("active");
        }
      });
    });
  });

  // Hiển thị popup tiến độ
  const progressBtn = document.getElementById('progressBtn');
  const progressPopup = document.getElementById('progressPopup');

  if (progressBtn && progressPopup) {
    progressBtn.addEventListener('click', function (e) {
      e.stopPropagation();
      progressPopup.style.display = progressPopup.style.display === 'block' ? 'none' : 'block';
    });

    document.addEventListener('click', function (e) {
      if (progressPopup.style.display === 'block' &&
        !progressPopup.contains(e.target) &&
        e.target !== progressBtn &&
        !progressBtn.contains(e.target)) {
        progressPopup.style.display = 'none';
      }
    });
  }

  // Gọi render bài học khi DOM đã sẵn sàng
  renderLessons(lessonData);
  setTimeout(() => {
    initializeYouTubePlayer();
  }, 1000); // đảm bảo DOM đã có iframe
  renderComments(commentData); 
  renderOverviewIntro();
  renderInstructorInfo(instructorData);
  renderRatingSummary(ratingData);
  renderReviews();
    
    const loadMoreBtn = document.getElementById("load-more-btn");
    if (loadMoreBtn) {
      loadMoreBtn.addEventListener("click", function () {
        currentPage++;
        renderReviews();
      });
    }
  
  const sortSelect = document.getElementById("comment-sort");
  if (sortSelect) {
    sortSelect.addEventListener("change", function () {
      const selectedFilter = this.value;
      renderComments(commentData, selectedFilter);
    });
  }
  
  const firstItem = document.querySelector("#lesson-container .lesson-item");
    if (firstItem) {
      firstItem.classList.add("active");
      const url = firstItem.getAttribute("data-video-url");
      document.getElementById("lesson-video").src = url;
	  document.getElementById("lesson-title").innerText = firstItem.querySelector("span").innerText;
	  document.getElementById("lesson-description").innerText = firstItem.getAttribute("data-description") || "";
    }
	
	// Xử lý chuyển bài học tiếp theo hoặc trước đó
	const btnNext = document.querySelector(".btn-next");
	const btnPrev = document.querySelector(".btn-prv");

	function updateNavigationButtons(index, total) {
	  if (btnPrev) btnPrev.disabled = index <= 0;
	  if (btnNext) btnNext.disabled = index >= total - 1;
	}

	function switchLesson(direction) {
	  const activeItem = document.querySelector(".lesson-item.active");
	  if (!activeItem) return;

	  const allItems = Array.from(document.querySelectorAll(".lesson-item"));
	  const currentIndex = allItems.indexOf(activeItem);
	  let newIndex = direction === "next" ? currentIndex + 1 : currentIndex - 1;

	  if (newIndex >= 0 && newIndex < allItems.length) {
	    allItems[currentIndex].classList.remove("active");
	    const newItem = allItems[newIndex];
	    newItem.classList.add("active");

	    const url = newItem.getAttribute("data-video-url");
	    
	    // Cập nhật tiêu đề và mô tả trước
	    document.getElementById("lesson-title").innerText = newItem.querySelector("span").innerText;
	    document.getElementById("lesson-description").innerText = newItem.getAttribute("data-description") || "";
	    
	    // Kiểm tra xem đang dùng player API hay iframe gốc
	    const ytPlayer = document.getElementById('youtube-player');
	    if (ytPlayer && window.YT && window.YT.Player && player) {
	      // Nếu đang dùng player API, load video mới
	      const videoId = extractYouTubeVideoId(url);
	      if (videoId) {
	        player.loadVideoById(videoId);
	      }
	    } else {
	      // Nếu vẫn dùng iframe gốc
	      const iframe = document.getElementById("lesson-video");
	      if (iframe) {
	        iframe.src = url;
	      }
	    }

	    // Mở phần chứa bài học nếu bị đóng
	    const section = newItem.closest(".lesson-section");
	    const lessonList = section.querySelector(".lesson-list");
	    const arrow = section.querySelector(".arrow");

	    if (lessonList.style.display === "none") {
	      lessonList.style.display = "block";
	      if (arrow) arrow.innerHTML = "&#9660;"; // đổi sang ▼
	    }

	    // Cuộn đến bài học trong khung
	    newItem.scrollIntoView({ behavior: "smooth", block: "center" });

	    // Cập nhật trạng thái nút
	    updateNavigationButtons(newIndex, allItems.length);
	  }
	}

	
	btnNext?.addEventListener("click", () => switchLesson("next"));
	btnPrev?.addEventListener("click", () => switchLesson("prev"));

	// Cập nhật trạng thái nút sau khi DOM sẵn sàng
	const allLessons = document.querySelectorAll(".lesson-item");
	const initialIndex = Array.from(allLessons).findIndex(item => item.classList.contains("active"));
	updateNavigationButtons(initialIndex, allLessons.length);

	const reportBtn = document.getElementById("btn-report");
	  const reportModal = document.getElementById("reportModal");
	  const closeModal = document.querySelector("#reportModal .close");

	  if (reportBtn && reportModal && closeModal) {
	    reportBtn.addEventListener("click", function () {
	      reportModal.style.display = "block";
	    });

	    closeModal.addEventListener("click", function () {
	      reportModal.style.display = "none";
	    });

	    window.addEventListener("click", function (event) {
	      if (event.target === reportModal) {
	        reportModal.style.display = "none";
	      }
	    });
	  }
	
	  const submitReportBtn = document.getElementById("submitReport");

	    if (submitReportBtn) {
	      submitReportBtn.addEventListener("click", function () {
	        // 1. Lấy tên bài học đang active
	        const activeLesson = document.querySelector(".lesson-item.active");
	        const lessonTitle = activeLesson ? activeLesson.querySelector("span")?.innerText.trim() : "Không xác định";

	        // 2. Lấy loại lỗi đã chọn (radio button)
	        const selectedErrorType = document.querySelector('input[name="errorType"]:checked');
	        const errorType = selectedErrorType ? selectedErrorType.value : "";

	        // 3. Lấy nội dung textarea
	        const errorContent = document.getElementById("errorContent").value.trim();

	        // Thêm điều kiện kiểm tra: nếu cả hai đều trống
	        if (!errorType) {
				const reportModal = document.getElementById("reportModal");
			    if (reportModal) {
					 reportModal.style.display = "none";
			    }

				showToast("Hãy chọn loại lỗi trước khi gửi!");
	          return; // Không gửi nữa
	        }

	        // Nếu hợp lệ thì gửi dữ liệu
	        console.log("=== Thông tin báo lỗi ===");
	        console.log("Bài học:", lessonTitle);
	        console.log("Loại lỗi:", errorType || "Không chọn");
	        console.log("Nội dung:", errorContent || "Không nhập");

	        // (Option) Đóng modal sau khi báo lỗi xong
	        const reportModal = document.getElementById("reportModal");
	        if (reportModal) {
	          reportModal.style.display = "none";

				showToast("Đã gửi báo lỗi thành công!");
	        }

	        // (Option) Reset form sau khi gửi
	        document.getElementById("errorContent").value = "";
	        if (selectedErrorType) {
	          selectedErrorType.checked = false;
	        }
	      });
	    }
});


function showToast(message) {
  const toast = document.createElement("div");
  toast.innerText = message;
  toast.style.position = "fixed";
  toast.style.bottom = "20px";
  toast.style.right = "20px";
  toast.style.background = "#4caf50";
  toast.style.color = "white";
  toast.style.padding = "10px 20px";
  toast.style.borderRadius = "5px";
  toast.style.boxShadow = "0 2px 6px rgba(0,0,0,0.2)";
  toast.style.zIndex = "9999";

  document.body.appendChild(toast);

  setTimeout(() => {
    toast.remove();
  }, 3000);
}

//Xử lý click like
document.addEventListener("click", function(e) {
  if (e.target.closest(".fa-thumbs-up")) {
    const icon = e.target.closest(".reply-editor");
    icon.classList.toggle("liked");
  }
});

//render CKEditor comment
function attachCommentListeners() {
  document.querySelectorAll('.fa-comment').forEach(icon => {
    icon.addEventListener('click', function () {
      const commentDiv = this.closest('.comment');
      const replyDiv = commentDiv.querySelector('.comment-reply-editor');

      if (replyDiv.style.display === 'block') {
        replyDiv.style.display = 'none';
        replyDiv.innerHTML = '';
      } else {
        // Ẩn mọi CKEditor khác
        document.querySelectorAll('.comment-reply-editor').forEach(div => {
          div.style.display = 'none';
          div.innerHTML = '';
        });

        // Hiển thị khung reply
        replyDiv.style.display = 'block';

        // Tạo layout với avatar, textarea, và nút gửi
		replyDiv.innerHTML = `
		  <div class="editor-wrapper">6
		    <div class="editor-top">
		      <img class="avatar" src="${userAvatar}" alt="Avatar">
		      <div class="editor-content">
		        <textarea name="reply-editor"></textarea>
		      </div>
		    </div>
		    <div class="editor-bottom">
		      <button class="submit-reply-btn">Gửi</button>
		    </div>
		  </div>
		`;

        // Khởi tạo CKEditor
        CKEDITOR.replace(replyDiv.querySelector('textarea'), {
          toolbar: [
            { name: 'styles', items: ['Styles', 'Format'] },
            { name: 'basicstyles', items: ['Bold', 'Italic'] },
            { name: 'paragraph', items: ['NumberedList', 'BulletedList'] },
            { name: 'links', items: ['Link', 'Unlink'] },
            { name: 'insert', items: ['Image'] },
            { name: 'clipboard', items: ['Undo', 'Redo'] }
          ],
          removePlugins: 'elementspath',
          resize_enabled: false
        });
      }
    });
  });
}


// Toggle phần học (mở/đóng)
function toggleLesson(header) {
  const lessonList = header.nextElementSibling;
  const arrow = header.querySelector(".arrow");

  const isExpanded = lessonList.style.display !== "none";
  lessonList.style.display = isExpanded ? "none" : "block";
  arrow.innerHTML = isExpanded ? "&#9654;" : "&#9660;";
}

// Render các phần học
function renderLessons(data) {
  const container = document.getElementById("lesson-container");
  if (!container) return;
  container.innerHTML = "";

  data.forEach((section, index) => {
    const sectionDiv = document.createElement("div");
    sectionDiv.classList.add("lesson-section");

    const titleDiv = document.createElement("div");
    titleDiv.classList.add("lesson-group-title");
    titleDiv.setAttribute("onclick", "toggleLesson(this)");
    titleDiv.innerHTML = `
      ${section.title}
      <span class="arrow">${index === 0 ? "&#9660;" : "&#9654;"}</span>
    `;

    const lessonList = document.createElement("div");
    lessonList.classList.add("lesson-list");
    if (index !== 0) {
      lessonList.style.display = "none";
    }

    section.lessons.forEach((lesson, i) => {
      const item = document.createElement("div");
      item.classList.add("lesson-item");

      if (index === 0 && i === 0) item.classList.add("active");

      item.setAttribute("data-video-url", lesson.videoUrl);
      item.setAttribute("data-description", lesson.description || "");

      item.innerHTML = `
        <span>${lesson.title}</span>
        <span>${lesson.duration || ''}</span>
      `;

      lessonList.appendChild(item);
	  
	  // Gắn sự kiện click vào mỗi bài học
	  item.addEventListener("click", function () {
	    // Xóa class active khỏi tất cả bài học
	    document.querySelectorAll(".lesson-item").forEach(el => el.classList.remove("active"));
	    this.classList.add("active");

	    const url = this.getAttribute("data-video-url");
	    const title = this.querySelector("span").innerText;
	    const description = this.getAttribute("data-description") || "";

	    // Cập nhật tiêu đề và mô tả bài học
	    document.getElementById("lesson-title").innerText = title;
	    document.getElementById("lesson-description").innerText = description;

	    // Đổi video theo player API hoặc iframe
	    const ytPlayerDiv = document.getElementById('youtube-player');
	    if (ytPlayerDiv && window.YT && window.YT.Player && player) {
	      const videoId = extractYouTubeVideoId(url);
	      if (videoId) {
	        player.loadVideoById(videoId);
	      }
	    } else {
	      const iframe = document.getElementById("lesson-video");
	      if (iframe) {
	        iframe.src = url;
	      }
	    }

	    // Scroll bài học vào giữa màn hình cho đẹp
	    this.scrollIntoView({ behavior: "smooth", block: "center" });

	    const allLessons = document.querySelectorAll(".lesson-item");
	    const currentIndex = Array.from(allLessons).indexOf(this);
	    updateNavigationButtons(currentIndex, allLessons.length);
	  });



    });

    sectionDiv.appendChild(titleDiv);
    sectionDiv.appendChild(lessonList);
    container.appendChild(sectionDiv);
  });
}


//render reply comment
function renderRepliesForAuthor(commentDiv, authorName) {
  const replies = replyData[authorName];

  const replyList = document.createElement("div");
  replyList.classList.add("reply-list");

  if (replies && replies.length > 0) {
    replies.forEach(reply => {
      const replyDiv = document.createElement("div");
      replyDiv.classList.add("reply");

      replyDiv.innerHTML = `
        <div class="reply-body">
          <img class="avatar" src="${reply.avatar || 'https://cellphones.com.vn/sforum/wp-content/uploads/2023/10/avatar-trang-4.jpg'}" alt="avatar">
          <div class="reply-content-box">
            <strong>${reply.author}</strong> <span class="reply-time">${formatTimeAgo(reply.time) || ""}</span>
            <div class="reply-content">${reply.content}</div>
          </div>
        </div>
      `;

      replyList.appendChild(replyDiv);
    });

    commentDiv.appendChild(replyList);
    limitReplyDisplay(commentDiv);
  } else {
    // Nếu không có reply, vẫn cần gắn div.reply-list để đảm bảo layout
    commentDiv.appendChild(replyList);
  }

  // Luôn tạo comment-reply-editor
  const replyEditorDiv = document.createElement("div");
  replyEditorDiv.className = "comment-reply-editor";
  replyEditorDiv.style.display = "none";
  commentDiv.appendChild(replyEditorDiv);
}


//render chỉ hiển thị 2 reply
function limitReplyDisplay(commentDiv) {
  const replies = commentDiv.querySelectorAll('.reply');
  const replyList = commentDiv.querySelector('.reply-list');

  if (replies.length > 2) {
    // Ẩn các reply từ thứ 3 trở đi
    replies.forEach((reply, index) => {
      if (index >= 2) reply.style.display = 'none';
    });

    // Tạo nút "Hiển thị thêm"
    const showMoreBtn = document.createElement('button');
    showMoreBtn.className = 'show-more-btn';
    showMoreBtn.textContent = 'Xem thêm phản hồi';

    // Xử lý khi nhấn "Hiển thị thêm"
    showMoreBtn.addEventListener('click', () => {
      replies.forEach(reply => reply.style.display = 'block');
      showMoreBtn.style.display = 'none';
    });

    // Thêm nút vào sau danh sách reply
    commentDiv.appendChild(showMoreBtn);
  }
}


//render comment chính
function renderComments(data, filterType = "recent") {
  const container = document.getElementById("comment-container");
  if (!container) return;

  // Clone mảng để không ảnh hưởng dữ liệu gốc
  let sortedData = [...data];

  if (filterType === "likes") {
    sortedData.sort((a, b) => (b.likes || 0) - (a.likes || 0));
  } else if (filterType === "recent") {
    sortedData.sort((a, b) => new Date(b.time) - new Date(a.time));
  }

  container.innerHTML = "";

  sortedData.forEach(comment => {
    const commentDiv = document.createElement("div");
    commentDiv.classList.add("comment");

    commentDiv.innerHTML = `
      <div class="comment-body">
        <img class="avatar" src="${comment.avatar || 'https://cellphones.com.vn/sforum/wp-content/uploads/2023/10/avatar-trang-4.jpg'}" alt="avatar">
        <div class="comment-content">
          <div class="author-name"><strong>${comment.author}</strong></div>
          <div class="comment-text">${comment.content}</div>
          <div class="comment-actions">
		  <div class="left-actions">${formatTimeAgo(comment.time)}</div>
            <div class="right-actions">
              <span class="reply-editor"><i class="fa-regular fa-thumbs-up"></i> ${comment.likes || 0}</span>
              <span class="reply-editor"><i class="fa-regular fa-comment"></i> ${comment.replies || 0}</span>
            </div>
          </div>
        </div>
      </div>
    `;

    container.appendChild(commentDiv);
    renderRepliesForAuthor(commentDiv, comment.author);
  });

  attachCommentListeners();
}

function formatTimeAgo(dateString) {
  const now = new Date();
  const time = new Date(dateString);
  const diff = Math.floor((now - time) / 1000); // tính bằng giây

  if (isNaN(diff)) return "Thời gian không xác định";

  const minute = 60;
  const hour = 60 * minute;
  const day = 24 * hour;
  const month = 30 * day;
  const year = 365 * day;

  if (diff < minute) return "Vừa xong";
  if (diff < hour) return `${Math.floor(diff / minute)} phút trước`;
  if (diff < day) return `${Math.floor(diff / hour)} giờ trước`;
  if (diff < 2 * day) return "Hôm qua";
  if (diff < month) return `${Math.floor(diff / day)} ngày trước`;
  if (diff < year) return `${Math.floor(diff / month)} tháng trước`;
  return `${Math.floor(diff / year)} năm trước`;
}

//Hàm toggle dùng hiển thị thông tin
function toggleContent(id, button) {
  const content = document.getElementById(id);
  if (!content) return;

  content.classList.toggle("expanded");

  if (content.classList.contains("expanded")) {
    button.textContent = "Thu gọn";
  } else {
    button.textContent = "Hiển thị thêm";
  }
}


//render giới thiệu khóa học
function renderOverviewIntro() {
  const container = document.getElementById("course-intro");
  if (!container) return;

  container.innerHTML = `
    <div class="course-description">
  <h3>Giới thiệu khóa học</h3>
      <div class="collapsible-container" id="course-description-content">
        ${courseDescription}
      </div>
      <span class="toggle-button" onclick="toggleContent('course-description-content', this)">Hiển thị thêm</span>
    </div>
  `;
}


//render thông tin giảng viên
function renderInstructorInfo(instructor) {
  if (!instructor) return;
  const { rating, reviews, students, courses } = instructor.stats;

  document.getElementById("instructor-info").innerHTML = `
    <div class="instructor-wrapper">
	<h3>Giảng viên</h3>
      <p class="instructor-name">
        <a href="${instructor.url}" target="_blank" rel="noopener">
          ${instructor.name}
        </a>
      </p>
      <p class="instructor-org">${instructor.organization}</p>

      <div class="instructor-top">
        <img class="instructor-avatar" src="${instructor.avatar}" alt="Avatar">
        <div class="instructor-stats">
          <div class="instructor-stat">
            <i class="fas fa-star"></i>
            <span>${rating.toFixed(1)} xếp hạng</span>
          </div>
          <div class="instructor-stat">
            <i class="far fa-star"></i>
            <span>${reviews.toLocaleString()} đánh giá</span>
          </div>
          <div class="instructor-stat">
            <i class="fas fa-user"></i>
            <span>${students.toLocaleString()} học viên</span>
          </div>
          <div class="instructor-stat">
            <i class="fas fa-play-circle"></i>
            <span>${courses} khóa học</span>
          </div>
        </div>
      </div>

      <div class="instructor-bio-section">
        <div class="collapsible-container" id="instructor-bio-content">
          ${instructor.bio}
        </div>
        <span class="toggle-button"
              onclick="toggleContent('instructor-bio-content', this)">
          Hiển thị thêm
        </span>
      </div>
    </div>
  `;
}

//Render bảng đánh giá
function renderRatingSummary(data) {
  const container = document.getElementById("rating-summary");
  if (!container) return;

  let ratingRows = "";
  for (let i = 5; i >= 1; i--) {
    ratingRows += `
      <div class="rating-row">
        <div class="progress-bar-background">
          <div class="progress-bar-fill" style="width: ${data.distribution[i]}%;"></div>
        </div>
        <div class="star-text">${"★".repeat(i)}</div>
        <div class="percentage-text">${data.distribution[i]}%</div>
      </div>
    `;
  }

  container.innerHTML = `
    <h2>Phản hồi của học viên</h2>
    <div class="average-score">${data.average.toFixed(1)}</div>
    <div class="stars">
      ${"★".repeat(Math.round(data.average))}
    </div>
    <div class="average-text">Điểm đánh giá trung bình</div>
    ${ratingRows}
  `;
}

//render reviews
let reviewsPerPage = 10;
let currentPage = 1;
let selectedRatingFilter = "All"; // Mặc định

document.addEventListener("DOMContentLoaded", function () {
  renderReviews();

  const loadMoreBtn = document.getElementById("load-more-btn");
  if (loadMoreBtn) {
    loadMoreBtn.addEventListener("click", function () {
      currentPage++;
      renderReviews();
    });
  }

  // Sự kiện thay đổi bộ lọc sao
  const ratingSort = document.getElementById("rating-sort");
  if (ratingSort) {
    ratingSort.addEventListener("change", function () {
      selectedRatingFilter = this.value; // "All", "5-stars", ...
      currentPage = 1; // Reset lại trang
      renderReviews();
    });
  }
});

function renderReviews() {
  const container = document.getElementById("reviews-container");
  if (!container) return;

  container.innerHTML = "";

  // Lọc dữ liệu theo số sao nếu cần
  let filteredReviews = [...reviewData];
  if (selectedRatingFilter !== "All") {
    const selectedStars = parseInt(selectedRatingFilter.charAt(0)); // "5-stars" -> 5
    filteredReviews = reviewData.filter(r => r.rating === selectedStars);
  }

  const start = 0;
  const end = reviewsPerPage * currentPage;
  const currentReviews = filteredReviews.slice(start, end);

  currentReviews.forEach(review => {
    const reviewDiv = document.createElement("div");
    reviewDiv.classList.add("review-item");

    const stars = Array(5).fill(0).map((_, index) =>
      index < review.rating ? "★" : "☆"
    ).join("");

    reviewDiv.innerHTML = `
      <div class="review-body">
        <img class="avatar" src="${review.avatar || 'https://cellphones.com.vn/sforum/wp-content/uploads/2023/10/avatar-trang-4.jpg'}" alt="avatar">
        <div class="review-content">
          <div class="author-name">${review.author}</div>
          <div class="rating-stars">${stars}</div>
          <div class="review-text">${review.content}</div>
        </div>
      </div>
    `;

    container.appendChild(reviewDiv);
  });

  // Ẩn nút nếu đã load hết dữ liệu
  const loadMoreBtn = document.getElementById("load-more-btn");
  if (filteredReviews.length <= end) {
    loadMoreBtn.style.display = "none";
  } else {
    loadMoreBtn.style.display = "block";
  }
}


const reviewData = [
  {
    author: "Phạm Bá Hổ",
    avatar: "https://cdn-icons-png.flaticon.com/512/194/194935.png",
    rating: 4,
    content: "Bài giảng của thầy rất hay, tôi tiếp thu được thêm nhiều kiến thức. Cảm ơn Giảng viên Nguyễn Lê Hoàng và Unica"
  },
  {
    author: "Nguyễn Thị Lan",
    avatar: "https://cdn-icons-png.flaticon.com/512/194/194938.png",
    rating: 5,
    content: "Nội dung bài học dễ hiểu, giảng viên truyền đạt rõ ràng."
  },
  {
    author: "Trần Minh Tuấn",
    avatar: "https://cdn-icons-png.flaticon.com/512/194/194931.png",
    rating: 4,
    content: "Khóa học rất hữu ích cho công việc của tôi."
  },
  {
    author: "Lê Thị Bích",
    avatar: "https://cdn-icons-png.flaticon.com/512/194/194937.png",
    rating: 5,
    content: "Giảng viên dạy rất tâm huyết, tài liệu chi tiết."
  },
  {
    author: "Hoàng Quốc Việt",
    avatar: "https://cdn-icons-png.flaticon.com/512/194/194936.png",
    rating: 3,
    content: "Một số phần kiến thức còn hơi khó tiếp cận với người mới."
  },
  {
    author: "Đỗ Hồng Sơn",
    avatar: "https://cdn-icons-png.flaticon.com/512/194/194939.png",
    rating: 4,
    content: "Bài tập thực hành rất thực tế, giúp tôi hiểu bài sâu hơn."
  },
  {
    author: "Phan Thị Ngọc",
    avatar: "https://cdn-icons-png.flaticon.com/512/194/194933.png",
    rating: 5,
    content: "Khóa học tuyệt vời! Tôi sẽ giới thiệu cho bạn bè."
  },
  {
    author: "Vũ Văn An",
    avatar: "https://cdn-icons-png.flaticon.com/512/194/194930.png",
    rating: 2,
    content: "Một số video có âm thanh nhỏ, cần cải thiện."
  },
  {
    author: "Lâm Quốc Bảo",
    avatar: "https://cdn-icons-png.flaticon.com/512/194/194940.png",
    rating: 5,
    content: "Giảng viên giải thích rất dễ hiểu, phù hợp cho người mới bắt đầu."
  },
  {
    author: "Ngô Thị Hương",
    avatar: "https://cdn-icons-png.flaticon.com/512/194/194932.png",
    rating: 4,
    content: "Khóa học cung cấp nhiều ví dụ thực tế hữu ích."
  },
  {
    author: "Bùi Thanh Hằng",
    avatar: "https://cdn-icons-png.flaticon.com/512/194/194934.png",
    rating: 5,
    content: "Cách trình bày bài học rất logic và dễ theo dõi."
  },
  {
    author: "Đặng Minh Khoa",
    avatar: "https://cdn-icons-png.flaticon.com/512/194/194935.png",
    rating: 3,
    content: "Tôi mong muốn có thêm bài tập thực hành nhiều hơn nữa."
  },
  {
    author: "Nguyễn Tấn Phát",
    avatar: "https://cdn-icons-png.flaticon.com/512/194/194938.png",
    rating: 5,
    content: "Khóa học đáng đồng tiền bát gạo. Cảm ơn Unica và giảng viên!"
  },
  {
    author: "Trịnh Bảo Trâm",
    avatar: "https://cdn-icons-png.flaticon.com/512/194/194931.png",
    rating: 4,
    content: "Tôi đã áp dụng được kiến thức vào công việc thực tế ngay sau khóa học."
  },
  {
    author: "Lưu Gia Hân",
    avatar: "https://cdn-icons-png.flaticon.com/512/194/194937.png",
    rating: 1,
    content: "Khóa học rất hay, dễ tiếp thu, phù hợp cho người đi làm."
  },
  {
    author: "Mai Hữu Nghĩa",
    avatar: "https://cdn-icons-png.flaticon.com/512/194/194936.png",
    rating: 3,
    content: "Một vài phần bài giảng hơi ngắn, cần chi tiết hơn."
  },
  {
    author: "Võ Quốc Hưng",
    avatar: "https://cdn-icons-png.flaticon.com/512/194/194939.png",
    rating: 5,
    content: "Bài giảng sinh động, giảng viên nhiệt tình giải đáp thắc mắc."
  },
  {
    author: "Huỳnh Thị Mỹ Duyên",
    avatar: "https://cdn-icons-png.flaticon.com/512/194/194933.png",
    rating: 4,
    content: "Tôi cảm thấy tự tin hơn sau khi hoàn thành khóa học."
  },
  {
    author: "Tạ Minh Nhật",
    avatar: "https://cdn-icons-png.flaticon.com/512/194/194930.png",
    rating: 5,
    content: "Khóa học thiết kế bài bản, dễ hiểu từ cơ bản đến nâng cao."
  },
  {
    author: "Châu Hồng Ngọc",
    avatar: "https://cdn-icons-png.flaticon.com/512/194/194940.png",
    rating: 4,
    content: "Cảm ơn thầy cô và đội ngũ hỗ trợ rất nhiệt tình."
  },
];


//Thông tin đánh giá
const ratingData = {
  average: 4.9,
  distribution: {
    5: 90.5,
    4: 8.8,
    3: 0.7,
    2: 0,
    1: 0
  }
};

//Thông tin giới thiệu khóa học
const courseDescription = `
Bạn có biết:

Hầu hết công dân nào cũng biết mình có quyền và nghĩa vụ phải đóng thuế cho nhà nước, nhưng thuế là gì, vì sao phải đóng thuế, đối tượng phải đóng thuế là ai cũng như cách đóng thuế như thế nào lại rất ít người biết. Điều này đã vô tình làm ảnh hưởng đến quyền và lợi ích của công dân khi chưa ý thức được tầm quan trọng của Thuế như điều chỉnh và ổn định kinh tế, xã hội, phúc lợi xã hội của từng công dân, đảm bảo công bằng xã hội giữa các chủ thể kinh doanh, giữa các tầng lớp xã hội...

Với nhu cầu tìm hiểu về Thuế một cách bài bản, cụ thể và dễ hiểu nhất đến tất cả mọi người, khóa học trực tuyến "Tổng quan về thuế" của giảng viên Nguyễn Hoàng sẽ cung cấp cho bạn các kiến thức tổng quan về thuế một cách chi tiết và dễ hiểu nhất, bao gồm:

Tổng quan về quản lý thuế, giới thiệu hệ thống cơ quan quản lý thuế
Nguyên tắc khai thuế, tính thuế, thời hạn nộp hồ sơ khai thuế, thời hạn nộp tiền thuế
Quy trình gửi, nộp hồ sơ thuế, trả kết quả từ cơ quan thuế
Như thế nào là ấn định thuế, cưỡng chễ thuế
Tìm hiểu chi tiết quy định về Kiểm tra thuế, Thanh tra thuế
Quy định về thời hiệu xử phạt vi phạm hành chính thuế
Quy định về khai bổ sung hồ sơ thuế, cách tính tiền chậm nộp tiền thuế
Hướng dẫn cài đặt các phần mềm hỗ trợ cho công tác kế toán thuế tại doanh nghiệp
Tìm hiểu quy định của pháp luật về Lệ phí môn bài
Hướng dẫn thực hành kê khai và nộp lệ phí môn bài
Hướng dẫn đăng ký tờ khai thuế qua mạng, cách nộp thuế qua mạng và lập thư rà soát nộp thuế.
Không chỉ là kiến thức lý thuyết chi tiết đầy đủ, mỗi một bài giảng trong khóa học còn là những TRẢI NGHIỆM THÚ VỊ do chính giảng viên đã tự trải nghiệm và đúc kết thành những kinh nghiệm quý báu trong suốt hơn 15 năm làm việc trong nghề. Với phương pháp giảng dạy hướng dẫn hiện đại, khoa học, chi tiết và đơn giản dễ hiểu nhất bạn sẽ được lắng nghe và thực hành theo sự hướng dẫn TẬN TÌNH của thầy.

Chắc chắn với khóa học "Tổng quan về thuế" bạn rằng đây sẽ là một chương trình huấn luyện Kế toán online TUYỆT VỜI dành cho bạn!

Hãy nhanh chóng sở hữu khóa học ngay hôm nay!
`;

//thông tin giảng viên
const instructorData = {
  name: "Đăng Nhựt",
  url: "https://www.facebook.com/NhutDang2908",
  organization: "Viện Đào Tạo Kế Toán Online",
  avatar: "https://th.bing.com/th/id/R.76b548009cb114a2dfa5c9a2bfdaa5c4?rik=oUsgRDeehBEWwA&pid=ImgRaw&r=0", // hoặc đường dẫn phù hợp
  bio: `
    <ul>
      <li>Hơn 15 năm kinh nghiệm làm kế toán và huấn luyện kế toán thực tế</li>
      <li>Trực tiếp huấn luyện hàng nghìn học viên học kế toán mỗi năm, cả trực tiếp và online</li>
      <li>Tư vấn thuế, kế toán cho hàng trăm doanh nghiệp</li>
      <li>Tác giả phần mềm kế toán Excel VAA – cung cấp miễn phí cho doanh nghiệp</li>
    </ul>
  `,
	stats: {
	  rating: 4.8,
	  reviews: 8755,
	  students: 14712,
	  courses: 15
	}
};


//avt user
const userAvatar = "https://th.bing.com/th/id/R.76b548009cb114a2dfa5c9a2bfdaa5c4?rik=oUsgRDeehBEWwA&pid=ImgRaw&r=0";

//thông tin comment
const commentData = [
  {
    author: "Đăng Nhựt",
    avatar: "https://th.bing.com/th/id/R.76b548009cb114a2dfa5c9a2bfdaa5c4?rik=oUsgRDeehBEWwA&pid=ImgRaw&r=0",
    content: "Em đã hoàn thành khóa học này. Cảm ơn Thầy nhiều!",
    time: "2025-02-21T20:30:00",
    likes: 5,
    replies: 1
  },
  {
      author: "Phi Hiệp",
      avatar: "",
      content: "Em đã hoàn thành khóa học này. Cảm ơn Thầy nhiều!",
      time: "2025-04-25T18:29:59",
      likes: 3,
      replies: 3
    },
	{
	    author: "Hưng",
	    avatar: "",
	    content: "Em đã hoàn thành khóa học này. Cảm ơn Thầy nhiều!",
	    time: "2025-04-23T10:30:00",
	    likes: 2,
	    replies: 0
	  },
	  {
	      author: "Khang",
	      avatar: "",
	      content: "Em đã hoàn thành khóa học này. Cảm ơn Thầy nhiều!",
	      time: "2025-04-23T10:30:00",
	      likes: 3,
	      replies: 0
	    },
		{
		    author: "Phúc",
		    avatar: "",
		    content: "Em đã hoàn thành khóa học này. Cảm ơn Thầy nhiều!",
		    time: "2025-04-23T10:30:00",
		    likes: 3,
		    replies: 0
		  }
];

const replyData = {
  "Đăng Nhựt": [
    {
      author: "Thầy Tuấn",
      avatar: "https://cdn-icons-png.flaticon.com/512/194/194935.png",
      content: "Cảm ơn em nhiều, chúc em áp dụng thành công!",
      time: "2025-04-23T10:30:00"
    }
  ],
  "Phi Hiệp": [
    {
      author: "Giảng viên",
      avatar: "",
      content: "Chúc mừng em đã học xong!",
      time: "2025-04-23T10:30:00"
    },
    {
      author: "Học viên khác",
      avatar: "",
      content: "Bạn học nhanh ghê!",
      time: "2025-04-23T10:30:00"
    },
	{
	  author: "Học viên khác",
	  avatar: "",
	  content: "Bạn học lẹ ghê!",
	  time: "2025-04-23T10:30:00"
	}
  ]
};

// Dữ liệu bài học
const lessonData = [
  {
    title: "Phần 1: Tổng quan và cài đặt ứng dụng khai thuế",
    lessons: [
      { 
        title: "Bài 1: Giới thiệu khóa học", 
        duration: "04:47",
        videoUrl: "https://www.youtube.com/embed/jtFsZRHUyPE",
        description: "Giới thiệu tổng quan về khóa học và mục tiêu bạn sẽ đạt được."
      },
      { 
        title: "Bài 2: Cài đặt phần mềm hỗ trợ kê khai thuế", 
        duration: "11:48",
        videoUrl: "https://www.youtube.com/embed/Xnabk4pjrHA",
        description: "Hướng dẫn chi tiết cách tải và cài đặt phần mềm HTKK."
      }
    ]
  },
  {
    title: "Phần 2: Thiết lập hệ thống và sửa lỗi",
    lessons: [
      { 
        title: "Bài 1: Sửa lỗi phần mềm",
        duration: "05:26",
        videoUrl: "https://www.youtube.com/embed/mnQMvKnvEKs",
        description: "Khắc phục một số lỗi thường gặp khi cài đặt và sử dụng phần mềm."
      },
      { 
        title: "Bài 2: Khai báo thông tin cho phần mềm HTKK", 
        duration: "16:00",
        videoUrl: "https://www.youtube.com/embed/6NQaShPUD5M",
        description: "Thiết lập và khai báo thông tin doanh nghiệp trên phần mềm."
      }
    ]
  },
  {
    title: "Phần 3: Công cụ hỗ trợ và quản lý dữ liệu",
    lessons: [
      { 
        title: "Bài 1: Tìm hiểu về năm tài chính", 
        duration: "11:51",
        videoUrl: "https://www.youtube.com/embed/WJ1Y9pZrNjA",
        description: "Tổng quan về khái niệm và cách thiết lập năm tài chính trong phần mềm."
      },
      { 
        title: "Bài 2: Cài đặt các công cụ hỗ trợ", 
        duration: "07:07",
        videoUrl: "https://www.youtube.com/embed/cPbp2iFaZRo",
        description: "Các công cụ giúp bạn khai thuế hiệu quả hơn."
      },
      { 
        title: "Bài 3: Định dạng lại ngày, tháng", 
        duration: "10:15",
        videoUrl: "https://www.youtube.com/embed/zDrji1Uwnh8",
        description: "Chỉnh định dạng ngày tháng theo yêu cầu phần mềm."
      },
      { 
        title: "Bài 4: Sao lưu phục hồi dữ liệu", 
        duration: "04:58",
        videoUrl: "https://www.youtube.com/embed/USvnDk1hi5I",
        description: "Hướng dẫn sao lưu và phục hồi dữ liệu HTKK an toàn."
      }
    ]
  }
];
