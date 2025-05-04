// Toggle module content
document.addEventListener('DOMContentLoaded', function () {
    const moduleHeaders = document.querySelectorAll('.module-header');

    moduleHeaders.forEach(header => {
        header.addEventListener('click', function () {
            const content = this.nextElementSibling;
            const dropdownIcon = this.querySelector('.dropdown-icon');

            // Kiểm tra trạng thái hiện tại của module content
            if (content.classList.contains('collapsed')) {
                // Mở rộng nội dung
                content.classList.remove('collapsed');
                content.classList.add('expanded');
                this.classList.add('expanded'); // Thêm class expanded cho module-header
            } else {
                // Thu gọn nội dung
                content.classList.remove('expanded');
                content.classList.add('collapsed');
                this.classList.remove('expanded'); // Xóa class expanded khi thu gọn
            }
        });
    });
});





// Simulate wishlist
document.querySelector('.wishlist-button').addEventListener('click', function() {
    this.style.color = '#f44336';
    alert('Đã thêm vào danh sách yêu thích');
});

// Simulate enrollment
document.querySelector('.primary-button').addEventListener('click', function() {
    alert('Bạn đã vào học khóa "Tổng quan về thuế"');
});

document.querySelector('.secondary-button').addEventListener('click', function() {
    alert('Bạn đã mua khóa học "Tổng quan về thuế"');
});

// Simulate coupon application
document.querySelector('.coupon-button').addEventListener('click', function() {
    const couponInput = document.querySelector('.coupon-input');
    if (couponInput.value.trim() !== '') {
        alert('Đã áp dụng mã giảm giá: ' + couponInput.value);
        couponInput.value = '';
    } else {
        alert('Vui lòng nhập mã giảm giá');
    }
});




