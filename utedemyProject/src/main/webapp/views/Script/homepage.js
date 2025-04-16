 document.addEventListener('DOMContentLoaded', () => {
    const featuredInstructors = [
        {
            name: 'Trần Đăng Khoa',
            specialty: 'Chuyên gia Digital Marketing',
            image: '/api/placeholder/200/200'
        },
        {
            name: 'Phạm Văn Minh',
            specialty: 'Chuyên gia Thiết kế',
            image: '/api/placeholder/200/200'
        },
        {
            name: 'Nguyễn Thị Hương',
            specialty: 'Chuyên gia Ngoại ngữ',
            image: '/api/placeholder/200/200'
        },
        {
            name: 'Lê Văn Tuấn',
            specialty: 'Chuyên gia Tài chính',
            image: '/api/placeholder/200/200'
        }
    ];

    const bestsellerCourses = [
        <c:forEach var="course" items="${bestSellerCourses}" varStatus="status">
        <c:out value="{" escapeXml="false"/>
            id: <c:out value="${status.index}" />,
            title: "<c:out value='${course[0]}'/>",
            instructor: "<c:out value='${course[1]}'/>",
            rating: ${course[2] != null ? course[2] : 0},
            reviews: 0,
            price: ${course[3] != null ? course[3] : 0},
            originalPrice: ${course[3] != null ? course[3] * 1.5 : 0},
            image: "<c:out value='${course[4] != null ? course[4] : "/api/placeholder/300/200"}'/>",
            badge: "Bán chạy"
        <c:out value="}" escapeXml="false"/><c:if test="${!status.last}">,</c:if>
        </c:forEach>
    ];

    const todaySaleCourses = [
        <c:forEach var="course" items="${todaySaleCourses}" varStatus="status">
        <c:out value="{" escapeXml="false"/>
            id: ${status.index},
            title: "${course[0]}",
            instructor: "${course[1]}",
            rating: ${course[2] != null ? course[2] : 0},
            reviews: 0,
            price: ${course[3] != null ? course[3] * 1 : 0}, // ép thành số
            originalPrice: ${course[3] != null ? course[3] * 1.5 : 0},
            image: "${course[4] != null ? course[4] : '/api/placeholder/300/200'}",
            badge: "Giảm ${course[5]}%"
        <c:out value="}" escapeXml="false"/><c:if test="${!status.last}">,</c:if>
        </c:forEach>
    ];

    const bannerSlides = [
        {
            title: 'Khóa Học Mới',
            subtitle: 'Ưu đãi giảm 50% tất cả các khóa học',
            image: '/api/placeholder/1200/400'
        },
        {
            title: 'Học Trực Tuyến',
            subtitle: 'Học mọi lúc, mọi nơi với Unica',
            image: '/api/placeholder/1200/400'
        }
    ];

    function renderBannerSlides() {
        const sliderContent = document.querySelector('.slider-content');
        if (!sliderContent) return;

        sliderContent.innerHTML = bannerSlides.map((slide, index) => `
            <div class="slider-item" data-index="${index}">
                <img src="${slide.image}" alt="${slide.title}">
                <div class="slider-text">
                    <h2>${slide.title}</h2>
                    <p>${slide.subtitle}</p>
                    <button class="view-course-btn">Xem khóa học</button>
                </div>
            </div>
        `).join('');
    }

    function renderBannerSlides() {
        const sliderContent = document.querySelector('.slider-content');
        if (!sliderContent) return;
        
        sliderContent.innerHTML = '';
        
        bannerSlides.forEach((slide, index) => {
            let slideItem = document.createElement('div');
            slideItem.classList.add('slider-item');
            slideItem.setAttribute('data-index', index);
            
            let img = document.createElement('img');
            img.src = slide.image;
            img.alt = slide.title;
            
            let textContainer = document.createElement('div');
            textContainer.classList.add('slider-text');
            
            let title = document.createElement('h2');
            title.textContent = slide.title;
            
            let subtitle = document.createElement('p');
            subtitle.textContent = slide.subtitle;
            
            let button = document.createElement('button');
            button.classList.add('view-course-btn');
            button.textContent = 'Xem khóa học';
            
            textContainer.append(title, subtitle, button);
            slideItem.append(img, textContainer);
            sliderContent.appendChild(slideItem);
        });
    }
    
    function renderTopCourses() {
        const container = document.getElementById('today-sale-courses');
        if (!container) return;
        container.innerHTML = '';
        todaySaleCourses.forEach(course => {
            let card = document.createElement('div');
            card.classList.add('course-card');
            let img = document.createElement('img');
            img.src = course.image;
            img.alt = course.title;
            let content = document.createElement('div');
            content.classList.add('course-card-content');
            let title = document.createElement('h4');
            title.textContent = course.title;
            let instructor = document.createElement('p');
            instructor.textContent = course.instructor;
            let ratingContainer = document.createElement('div');
            ratingContainer.classList.add('course-rating');
            let stars = document.createElement('div');
            stars.classList.add('stars');
            stars.style.setProperty('--rating', course.rating);
            let reviews = document.createElement('span');
            let ratingValue = Number(course.rating);
            reviews.textContent = '(' + ratingValue.toFixed(1) + ')';
            let pricing = document.createElement('div');
            pricing.classList.add('course-pricing');
            
            let currentPrice = document.createElement('span');
            currentPrice.classList.add('real-price');
            currentPrice.textContent = Number(course.price).toLocaleString('vi-VN') + 'đ';
            let originalPrice = document.createElement('span');
            originalPrice.classList.add('product-price');
            originalPrice.textContent = Number(course.originalPrice).toLocaleString('vi-VN') + 'đ';
            let badge = document.createElement('div');
            badge.classList.add('course-badge');
            badge.textContent = course.badge;
            
            let detailsBtn = document.createElement('button');
            detailsBtn.classList.add('details-btn');
            detailsBtn.textContent = 'Xem chi tiết';
            ratingContainer.append(stars, reviews);
            pricing.append(currentPrice, originalPrice, badge);
            content.append(title, instructor, ratingContainer, pricing, detailsBtn);
            card.append(img, content);
            container.appendChild(card);
        });
    }

    function renderBestsellerCourses() {
        const container = document.getElementById('bestseller-courses');
        if (!container) return;
        
        container.innerHTML = '';
        
        bestsellerCourses.forEach(course => {
            let card = document.createElement('div');
            card.classList.add('course-card');
            
            let img = document.createElement('img');
            img.src = course.image;
            img.alt = course.title;
            
            let content = document.createElement('div');
            content.classList.add('course-card-content');
            
            let title = document.createElement('h4');
            title.textContent = course.title;
            
            let instructor = document.createElement('p');
            instructor.textContent = course.instructor;
            
            let ratingContainer = document.createElement('div');
            ratingContainer.classList.add('course-rating');
            
            let stars = document.createElement('div');
            stars.classList.add('stars');
            stars.style.setProperty('--rating', course.rating);
            
            let reviews = document.createElement('span');
            let ratingValue = Number(course.rating);
            reviews.textContent = '(' + ratingValue.toFixed(1) + ')';
            
            let pricing = document.createElement('div');
            pricing.classList.add('course-pricing');
            
            let currentPrice = document.createElement('span');
            currentPrice.classList.add('real-price');
            currentPrice.textContent = Number(course.price).toLocaleString('vi-VN') + 'đ';
            let originalPrice = document.createElement('span');
            originalPrice.classList.add('product-price');
            originalPrice.textContent = Number(course.originalPrice).toLocaleString('vi-VN') + 'đ';
            let badge = document.createElement('div');
            badge.classList.add('course-badge');
            badge.textContent = course.badge;
            
            let detailsBtn = document.createElement('button');
            detailsBtn.classList.add('details-btn');
            detailsBtn.textContent = 'Xem chi tiết';
            
            ratingContainer.append(stars, reviews);
            pricing.append(currentPrice, originalPrice, badge);
            content.append(title, instructor, ratingContainer, pricing, detailsBtn);
            card.append(img, content);
            container.appendChild(card);
        });
    }

    function formatPrices(className) {
        var elements = document.querySelectorAll('.' + className);
        elements.forEach(function(element) {
            var value = element.innerText;
            value = value.replace('₫', '').trim();
            if (!isNaN(value)) {
                value = Number(value).toLocaleString('vi-VN') + '₫';
                element.innerText = value;
            }
        });
    }
    
    // Gọi các hàm render khi DOM được load
    window.addEventListener('DOMContentLoaded', () => {
        renderBannerSlides();
        renderTodaySaleCourses();
        renderBestsellerCourses();
        renderTodaySaleCourses();
        formatPrices('real-price');
        formatPrices('product-price');
    });

    window.onload = function() {
        renderBestsellerCourses();
        renderTodaySaleCourses();
    };

    function initializeSlider() {
        const sliderContent = document.querySelector('.slider-content');
        const sliderItems = document.querySelectorAll('.slider-item');
        const sliderDots = document.querySelector('.slider-dots');
        const prevBtn = document.querySelector('.slider-prev');
        const nextBtn = document.querySelector('.slider-next');

        let currentSlide = 0;
        const totalSlides = sliderItems.length;

        // Create dots
        if (sliderDots) {
            sliderItems.forEach((_, index) => {
                const dot = document.createElement('button');
                dot.classList.add('slider-dot');
                dot.setAttribute('aria-label', `Go to slide ${index + 1}`);
                if (index === 0) dot.classList.add('active');
                dot.addEventListener('click', () => goToSlide(index));
                sliderDots.appendChild(dot);
            });
        }

        const dots = document.querySelectorAll('.slider-dot');

        function goToSlide(slideIndex) {
            currentSlide = (slideIndex + totalSlides) % totalSlides;
            
            if (sliderContent) {
                sliderContent.style.transform = `translateX(-${currentSlide * 100}%)`;
            }
            
            dots.forEach((dot, index) => {
                dot.classList.toggle('active', index === currentSlide);
            });
        }

        function nextSlide() {
            goToSlide(currentSlide + 1);
        }

        function prevSlide() {
            goToSlide(currentSlide - 1);
        }

        // Auto slide
        let autoSlideInterval = setInterval(nextSlide, 5000);

        // Event listeners for manual navigation
        if (nextBtn) {
            nextBtn.addEventListener('click', () => {
                nextSlide();
                resetAutoSlide();
            });
        }

        if (prevBtn) {
            prevBtn.addEventListener('click', () => {
                prevSlide();
                resetAutoSlide();
            });
        }

        // Reset auto slide timer when user interacts
        function resetAutoSlide() {
            clearInterval(autoSlideInterval);
            autoSlideInterval = setInterval(nextSlide, 5000);
        }
    }

    // Render components
    renderBannerSlides();
    renderTodaySaleCourses();
    renderFeaturedInstructors();
    renderBestsellerCourses();
    initializeSlider();
});