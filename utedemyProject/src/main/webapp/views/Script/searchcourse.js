// Hàm tạo và hiển thị dropdown sắp xếp
function createSortDropdown() {
    const sortOptions = [
        { text: "Học nhiều nhất", value: "most-popular" },
        { text: "Đánh giá cao", value: "highest-rated" },
        { text: "Mới nhất", value: "newest" },
        { text: "Giá thấp đến cao", value: "price-low-to-high" },
        { text: "Giá cao đến thấp", value: "price-high-to-low" }
    ];


    // Tạo container chính cho dropdown
    const dropdownContainer = document.createElement('div');
    dropdownContainer.className = 'sort-dropdown-container';
    dropdownContainer.style.position = 'relative';
    dropdownContainer.style.width = '240px';


    // Tạo nút dropdown
    const dropdownButton = document.createElement('button');
    dropdownButton.className = 'sort-dropdown-button';
    dropdownButton.innerHTML = `
        <div style="display: flex; justify-content: space-between; align-items: center; width: 100%;">
            <span>Sắp xếp theo</span>
            <span class="selected-option" style="font-weight: bold; color: #007bff;">Học nhiều nhất</span>
            <span class="dropdown-icon">▼</span>
        </div>
    `;
    dropdownButton.style.width = '100%';
    dropdownButton.style.padding = '12px 15px';
    dropdownButton.style.border = '1px solid #ddd';
    dropdownButton.style.borderRadius = '4px';
    dropdownButton.style.backgroundColor = 'white';
    dropdownButton.style.cursor = 'pointer';
    dropdownButton.style.textAlign = 'left';
    dropdownButton.style.fontSize = '14px';


    // Tạo menu dropdown
    const dropdownMenu = document.createElement('div');
    dropdownMenu.className = 'sort-dropdown-menu';
    dropdownMenu.style.display = 'none';
    dropdownMenu.style.position = 'absolute';
    dropdownMenu.style.top = 'calc(100% + 5px)';
    dropdownMenu.style.left = '0';
    dropdownMenu.style.width = '100%';
    dropdownMenu.style.backgroundColor = 'white';
    dropdownMenu.style.border = '1px solid #ddd';
    dropdownMenu.style.borderRadius = '4px';
    dropdownMenu.style.boxShadow = '0 2px 5px rgba(0,0,0,0.1)';
    dropdownMenu.style.zIndex = '100';


    // Thêm các tùy chọn vào menu
    sortOptions.forEach(option => {
        const optionElement = document.createElement('div');
        optionElement.className = 'sort-option';
        optionElement.textContent = option.text;
        optionElement.dataset.value = option.value;
        optionElement.style.padding = '12px 15px';
        optionElement.style.cursor = 'pointer';


        // Đánh dấu tùy chọn đang được chọn
        if (option.text === 'Học nhiều nhất') {
            optionElement.style.fontWeight = 'bold';
            optionElement.style.backgroundColor = '#f5f5f5';
        }


        // Thêm hiệu ứng hover
        optionElement.addEventListener('mouseover', () => {
            optionElement.style.backgroundColor = '#f5f5f5';
        });


        optionElement.addEventListener('mouseout', () => {
            if (option.text !== document.querySelector('.selected-option').textContent) {
                optionElement.style.backgroundColor = 'white';
            }
        });


        // Xử lý khi click vào tùy chọn
        optionElement.addEventListener('click', () => {
            // Cập nhật nút dropdown với tùy chọn đã chọn
            document.querySelector('.selected-option').textContent = option.text;


            // Đánh dấu tùy chọn đã chọn và bỏ đánh dấu các tùy chọn khác
            document.querySelectorAll('.sort-option').forEach(opt => {
                opt.style.fontWeight = 'normal';
                opt.style.backgroundColor = 'white';
            });
            optionElement.style.fontWeight = 'bold';
            optionElement.style.backgroundColor = '#f5f5f5';


            // Đóng dropdown
            dropdownMenu.style.display = 'none';


            // Gọi hàm sắp xếp thực tế (cần triển khai)
            sortCourses(option.value);
        });


        dropdownMenu.appendChild(optionElement);
    });


    // Thêm các phần tử vào container
    dropdownContainer.appendChild(dropdownButton);
    dropdownContainer.appendChild(dropdownMenu);


    // Xử lý sự kiện click vào nút dropdown
    dropdownButton.addEventListener('click', () => {
        if (dropdownMenu.style.display === 'none') {
            dropdownMenu.style.display = 'block';
        } else {
            dropdownMenu.style.display = 'none';
        }
    });


    // Đóng dropdown khi click ra ngoài
    document.addEventListener('click', (event) => {
        if (!dropdownContainer.contains(event.target)) {
            dropdownMenu.style.display = 'none';
        }
    });


    return dropdownContainer;
}


// Hàm sắp xếp khóa học theo tiêu chí đã chọn
function sortCourses(criteria) {
    console.log(`Đang sắp xếp khóa học theo: ${criteria}`);
    // Mã sắp xếp thực tế sẽ được triển khai ở đây
    // Phụ thuộc vào cách bạn quản lý và hiển thị danh sách khóa học


    // Ví dụ: Lấy tất cả các khóa học
    const courseCards = document.querySelectorAll('.course-card');
    const courseList = document.querySelector('.course-list');


    if (courseCards.length > 0) {
        // Chuyển NodeList thành mảng để có thể sắp xếp
        const coursesArray = Array.from(courseCards);


        switch(criteria) {
            case 'most-popular':
                // Logic sắp xếp theo lượt học nhiều nhất
                // Đây chỉ là ví dụ, trong thực tế bạn sẽ cần dữ liệu thật về số lượt học
                break;


            case 'highest-rated':
                // Sắp xếp theo điểm đánh giá cao nhất
                coursesArray.sort((a, b) => {
                    const ratingA = parseFloat(a.querySelector('.rating span').textContent);
                    const ratingB = parseFloat(b.querySelector('.rating span').textContent);
                    return ratingB - ratingA;
                });
                break;


            case 'newest':
                // Sắp xếp theo mới nhất
                // Cần thêm thông tin về ngày tạo khóa học
                break;


            case 'price-low-to-high':
                // Sắp xếp theo giá từ thấp đến cao
                coursesArray.sort((a, b) => {
                    const priceA = parseInt(a.querySelector('.price').textContent.replace(/\D/g, ''));
                    const priceB = parseInt(b.querySelector('.price').textContent.replace(/\D/g, ''));
                    return priceA - priceB;
                });
                break;


            case 'price-high-to-low':
                // Sắp xếp theo giá từ cao đến thấp
                coursesArray.sort((a, b) => {
                    const priceA = parseInt(a.querySelector('.price').textContent.replace(/\D/g, ''));
                    const priceB = parseInt(b.querySelector('.price').textContent.replace(/\D/g, ''));
                    return priceB - priceA;
                });
                break;
        }


        // Xóa các khóa học hiện tại
        courseCards.forEach(card => card.remove());


        // Thêm lại các khóa học theo thứ tự mới
        coursesArray.forEach(card => {
            courseList.appendChild(card);
        });
    }
}


// Khởi tạo dropdown khi trang được tải
document.addEventListener('DOMContentLoaded', function() {
    const filterContainer = document.querySelector('.filter-container');
    if (filterContainer) {
        // Xóa dropdown cũ nếu có
        const oldDropdown = document.querySelector('.sort-dropdown');
        if (oldDropdown) {
            oldDropdown.remove();
        }


        // Thêm dropdown mới
        filterContainer.appendChild(createSortDropdown());
    }
});


document.addEventListener('DOMContentLoaded', function() {
            // Tham chiếu đến các phần tử
            const filterBtn = document.querySelector('.filter-btn');
            const showFilterBtn = document.querySelector('.show-filter-btn');
            const sidebar = document.querySelector('.sidebar');
            const courseList = document.querySelector('.course-list');


            // Xử lý sự kiện khi nhấn nút "Bộ lọc"
            filterBtn.addEventListener('click', function() {
                // Ẩn sidebar
                sidebar.classList.add('hidden');
                // Mở rộng course-list
                courseList.classList.add('expanded');
                // Hiển thị nút "Hiển thị bộ lọc"
                showFilterBtn.classList.add('visible');
                // Ẩn nút "Bộ lọc"
                filterBtn.style.display = 'none';
            });


            // Xử lý sự kiện khi nhấn nút "Hiển thị bộ lọc"
            showFilterBtn.addEventListener('click', function() {
                // Hiển thị lại sidebar
                sidebar.classList.remove('hidden');
                // Thu hẹp course-list về trạng thái ban đầu
                courseList.classList.remove('expanded');
                // Ẩn nút "Hiển thị bộ lọc"
                showFilterBtn.classList.remove('visible');
                // Hiển thị lại nút "Bộ lọc"
                filterBtn.style.display = 'flex';
            });


            // Xử lý sự kiện khi nhấn nút "Sắp xếp"
            document.querySelector('.sort-btn').addEventListener('click', function() {
                alert('Mở tùy chọn sắp xếp');
            });


            // Xử lý sự kiện khi nhấn chat bubble
            document.querySelector('.chat-bubble').addEventListener('click', function() {
                alert('Chào mừng bạn ghé thăm Unica. Mình có thể hỗ trợ gì cho bạn?');
            });


            // Xử lý sự kiện khi nhấn vào course card
            document.querySelectorAll('.course-card').forEach(function(card) {
                card.addEventListener('click', function() {
                    const title = this.querySelector('.course-title').textContent;
                    alert('Bạn đã chọn khóa học: ' + title);
                });
            });
        });