document.addEventListener('DOMContentLoaded', function() {
    // Xử lý hiển thị khung tiến độ
    const progressBtn = document.getElementById('progressBtn');
    const progressPopup = document.getElementById('progressPopup');
    
    if (progressBtn && progressPopup) {
        // Hiển thị/ẩn khung tiến độ khi click vào nút
        progressBtn.addEventListener('click', function(e) {
            e.stopPropagation(); // Ngăn sự kiện click lan tỏa
            
            // Toggle hiển thị popup
            if (progressPopup.style.display === 'block') {
                progressPopup.style.display = 'none';
            } else {
                progressPopup.style.display = 'block';
            }
        });
        
        // Ẩn popup khi click ra ngoài
        document.addEventListener('click', function(e) {
            if (progressPopup.style.display === 'block' &&
                !progressPopup.contains(e.target) &&
                e.target !== progressBtn &&
                !progressBtn.contains(e.target)) {
                progressPopup.style.display = 'none';
            }
        });
    }
    
    // Mở rộng/thu gọn các phần của khóa học
    const sectionHeaders = document.querySelectorAll('.section-header');
    
    sectionHeaders.forEach(header => {
        header.addEventListener('click', function() {
            // Toggle lớp collapsed
            this.classList.toggle('collapsed');
            
            // Thay đổi biểu tượng mũi tên
            const icon = this.querySelector('i');
            if (this.classList.contains('collapsed')) {
                icon.className = 'fas fa-chevron-down';
            } else {
                icon.className = 'fas fa-chevron-up';
            }
            
            // Hiển thị/Ẩn danh sách bài học
            const lessonList = this.nextElementSibling;
            if (lessonList && lessonList.classList.contains('lesson-list')) {
                if (this.classList.contains('collapsed')) {
                    lessonList.style.display = 'none';
                } else {
                    lessonList.style.display = 'block';
                }
            }
        });
    });
});


function toggleLesson(header) {
    const content = header.nextElementSibling;
    const isVisible = content.style.display !== "none";

    content.style.display = isVisible ? "none" : "block";
    header.classList.toggle("open");

    // Đổi icon mũi tên
    const arrow = header.querySelector(".arrow");
    arrow.innerHTML = isVisible ? "&#9654;" : "&#9660;";
}

document.addEventListener("DOMContentLoaded", function () {
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
});