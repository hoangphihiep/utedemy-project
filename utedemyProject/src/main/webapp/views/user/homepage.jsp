<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Unica - Học trực tuyến</title>
    <link rel="stylesheet" href="styles.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style type="text/css">
    :root {
    --primary-blue: #0066cc;
    --secondary-blue: #4a90e2;
    --light-gray: #f4f4f4;
    --text-gray: #333;
    --gold: #ffc107;
    --gradient-blue: linear-gradient(135deg, #6a11cb 0%, #2575fc 100%);
}
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Arial', sans-serif;
}

body {
    background-color: var(--light-gray);
}

header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 15px 50px;
    background-color: white;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.08);
    position: sticky;
    top: 0;
    z-index: 100;
}

.logo img {
    height: 40px;
    max-width: 150px;
    object-fit: contain;
}

header nav {
    flex-grow: 1;
    margin: 0 30px;
}

header nav ul {
    display: flex;
    list-style: none;
    gap: 20px;
    justify-content: center;
    color: var(--text-gray);
    flex-wrap: wrap;
}

header nav ul li {
    font-size: 0.95rem;
    color: var(--text-gray);
    cursor: pointer;
    position: relative;
    transition: color 0.3s ease;
    padding-bottom: 5px;
}

header nav ul li a {
    text-decoration: none;
    color: var(--text-gray);
}

header nav ul li:hover,
header nav ul li a:hover {
    color: var(--primary-blue);
}

header nav ul li::after {
    content: '';
    position: absolute;
    width: 0;
    height: 2px;
    bottom: 0;
    left: 50%;
    background-color: var(--primary-blue);
    transition: all 0.3s ease;
}

header nav ul li:hover::after {
    width: 100%;
    left: 0;
}

.mobile-menu-toggle {
    display: none;
    background: none;
    border: none;
    font-size: 1.5rem;
    cursor: pointer;
}

.user-actions {
    display: flex;
    align-items: center;
    gap: 10px;
}

.user-actions button {
    padding: 8px 16px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 0.9rem;
    transition: all 0.3s ease;
}

.user-actions button:first-child,
.user-actions button:nth-child(2),
.user-actions .login {
    background-color: var(--light-gray);
    color: var(--text-gray);
}

.user-actions .signup {
    background-color: var(--primary-blue);
    color: white;
}

.user-actions button:hover {
    opacity: 0.9;
    transform: translateY(-2px);
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}
.login {
    background-color: var(--light-gray);
}

.signup {
    background-color: var(--primary-blue);
    color: white;
}

.banner-content {
    flex: 1;
}

.banner-content h1 {
    font-size: 2.5rem;
}

.banner-content h1 span {
    color: gold;
}

.zoom-btn {
    background-color: red;
    color: white;
    border: none;
    padding: 10px 20px;
    margin: 15px 0;
}

.banner-image {
    flex: 1;
    text-align: right;
}

.banner-image img {
    max-width: 300px;
    border-radius: 10px;
}

.upcoming-classes, .top-courses {
    background-color: white;
    margin: 20px;
    padding: 20px;
    border-radius: 10px;
}

.class-slider, .course-grid {
    display: flex;
    gap: 15px;
    overflow-x: auto;
    padding: 15px 0;
}

.class-card, .course-card {
    flex: 0 0 250px;
    border: 1px solid var(--light-gray);
    border-radius: 10px;
    padding: 15px;
    text-align: center;
}

.class-slider, .course-grid {
    display: flex;
    gap: 20px;
    overflow-x: auto;
    padding: 20px 0;
    scroll-snap-type: x mandatory;
    scroll-behavior: smooth;
}

.class-card {
    flex: 0 0 280px;
    border-radius: 15px;
    background: white;
    box-shadow: 0 10px 25px rgba(0,0,0,0.1);
    overflow: hidden;
    transition: transform 0.3s ease, box-shadow 0.3s ease;
    scroll-snap-align: start;
    position: relative;
    border: 1px solid rgba(0,0,0,0.05);
}

.class-card:hover {
    transform: translateY(-10px);
    box-shadow: 0 15px 35px rgba(0,0,0,0.15);
}

.class-card::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 8px;
    background: var(--gradient-blue);
}

.class-card-content {
    padding: 20px;
}

.class-card h4 {
    font-size: 1.2rem;
    color: var(--primary-blue);
    margin-bottom: 10px;
    font-weight: 700;
}

.class-card p {
    color: var(--text-gray);
    margin-bottom: 15px;
    opacity: 0.8;
}

.class-details {
    display: flex;
    justify-content: space-between;
    margin-bottom: 15px;
    font-size: 0.9rem;
    color: var(--text-gray);
}

.class-card .register-btn {
    width: 100%;
    padding: 12px;
    background: var(--secondary-blue);
    color: white;
    border: none;
    font-weight: bold;
    transition: background 0.3s ease;
}

.class-card .register-btn:hover {
    background: var(--primary-blue);
}

/*.course-card {
    flex: 0 0 280px;
    border-radius: 15px;
    background: white;
    box-shadow: 0 10px 25px rgba(0,0,0,0.1);
    overflow: hidden;
    transition: transform 0.3s ease, box-shadow 0.3s ease;
    scroll-snap-align: start;
    border: 1px solid rgba(0,0,0,0.05);
    display: flex;
    flex-direction: column;
}

.course-card img {
    width: 100%;
    height: 200px;
    object-fit: cover;
}

.course-card-content {
    padding: 20px;
    flex-grow: 1;
    display: flex;
    flex-direction: column;
}*/
.course-card {
    flex: 0 0 350px; /* Tăng từ 280px lên 350px để tăng chiều rộng */
    max-height: 450px; /* Thêm giới hạn chiều cao */
    border-radius: 15px;
    background: white;
    box-shadow: 0 10px 25px rgba(0,0,0,0.1);
    overflow: hidden;
    transition: transform 0.3s ease, box-shadow 0.3s ease;
    scroll-snap-align: start;
    border: 1px solid rgba(0,0,0,0.05);
    display: flex;
    flex-direction: column;
}

/* Điều chỉnh chiều cao của ảnh để giảm chiều dài tổng thể */
.course-card img {
    width: 100%;
    height: 150px; /* Giảm từ 200px xuống 150px */
    object-fit: cover;
}

/* Điều chỉnh padding để tối ưu không gian */
.course-card-content {
    padding: 15px; /* Giảm từ 20px xuống 15px */
    flex-grow: 1;
    display: flex;
    flex-direction: column;
}

.course-card:hover {
    transform: translateY(-10px);
    box-shadow: 0 15px 35px rgba(0,0,0,0.15);
}

.course-card h4 {
    font-size: 1.1rem;
    color: var(--text-gray);
    margin-bottom: 10px;
    flex-grow: 1;
}

.course-card p {
    color: var(--primary-blue);
    margin-bottom: 10px;
    font-weight: 600;
}


.course-pricing {
    display: flex;
    align-items: center;
    margin-bottom: 15px;
}

.current-price {
    font-size: 1.2rem;
    color: var(--primary-blue);
    font-weight: bold;
    margin-right: 10px;
}

.original-price {
    text-decoration: line-through;
    color: #888;
    font-size: 0.9rem;
}

.course-card .details-btn {
    width: 100%;
    padding: 12px;
    background: var(--secondary-blue);
    color: white;
    border: none;
    font-weight: bold;
    transition: background 0.3s ease;
}

.course-card .details-btn:hover {
    background: var(--primary-blue);
}

.topics-of-interest {
    background-color: white;
    margin: 20px;
    padding: 20px;
    border-radius: 10px;
}

.topic-tags {
    display: flex;
    flex-wrap: wrap;
    gap: 10px;
}

.topic-tag {
    background-color: var(--light-gray);
    padding: 8px 15px;
    border-radius: 20px;
    font-size: 0.9rem;
    cursor: pointer;
    transition: background-color 0.3s ease;
    text-decoration: none;
    color: var(--text-gray);
}

.topic-tag:hover {
    background-color: var(--primary-blue);
    color: white;
}

.current-price {
    font-size: 1.2rem;
    color: var(--primary-blue);
    font-weight: bold;
    margin-right: 10px;
}

.original-price {
    text-decoration: line-through;
    color: #888;
    font-size: 0.9rem;
}

.view-more {
    display: block;
    text-align: center;
    color: var(--primary-blue);
    text-decoration: none;
    margin-top: 15px;
    font-weight: bold;
}

.featured-instructors {
    background-color: white;
    margin: 20px;
    padding: 20px;
    border-radius: 10px;
}

.instructors-container {
    display: flex;
    gap: 20px;
    overflow-x: auto;
    padding: 20px 0;
    scroll-snap-type: x mandatory;
    scroll-behavior: smooth;
}

.instructor-card {
    flex: 0 0 250px;
    border-radius: 15px;
    background: white;
    box-shadow: 0 10px 25px rgba(0,0,0,0.1);
    overflow: hidden;
    transition: transform 0.3s ease, box-shadow 0.3s ease;
    scroll-snap-align: start;
    border: 1px solid rgba(0,0,0,0.05);
    text-align: center;
}

.instructor-card:hover {
    transform: translateY(-10px);
    box-shadow: 0 15px 35px rgba(0,0,0,0.15);
}

.instructor-card img {
    width: 100%;
    height: 250px;
    object-fit: cover;
    object-position: center top;
}

.instructor-info {
    padding: 15px;
}

.instructor-info h4 {
    color: var(--text-gray);
    margin-bottom: 5px;
    font-size: 1rem;
    font-weight: 600;
}

.instructor-info p {
    color: var(--primary-blue);
    font-size: 0.9rem;
    font-weight: 400;
}

.hero-banner-slider {
    position: relative;
    width: 100%;
    overflow: hidden;
}

.slider-container {
    position: relative;
    width: 100%;
}

.slider-content {
    display: flex;
    transition: transform 0.5s ease-in-out;
}

.slider-item {
    min-width: 100%;
    display: flex;
    align-items: center;
    background: linear-gradient(to right, #000428, #004e92);
    color: white;
    padding: 50px;
}

.banner-content {
    flex: 1;
    padding-right: 30px;
}

.banner-image {
    flex: 1;
    text-align: right;
}

.banner-image img {
    max-width: 300px;
    border-radius: 10px;
}

.slider-dots {
    position: absolute;
    bottom: 20px;
    left: 50%;
    transform: translateX(-50%);
    display: flex;
    gap: 10px;
}

.slider-dot {
    width: 12px;
    height: 12px;
    background-color: rgba(255,255,255,0.5);
    border-radius: 50%;
    cursor: pointer;
}

.slider-dot.active {
    background-color: white;
}

.slider-prev, .slider-next {
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
    background: rgba(0,0,0,0.3);
    color: white;
    border: none;
    padding: 10px 15px;
    cursor: pointer;
}

.slider-prev {
    left: 10px;
}

.slider-next {
    right: 10px;
}
.footer {
    background-color: #00326B;
    color: white;
    padding: 50px 20px;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
}

.footer-content {
    display: flex;
    width: 100%;
    max-width: 1200px;
    margin: 0 auto;
}

.footer-section {
    flex: 1;
    margin-right: 30px;
    min-width: 200px;
}

.footer-section:last-child {
    margin-right: 0;
}

.footer-section h4 {
    margin-bottom: 15px;
    font-size: 18px;
    font-weight: bold;
}

.footer-section ul {
    list-style: none;
    padding: 0;
    margin: 0;
}

.footer-section ul li {
    margin-bottom: 10px;
}

.footer-section a {
    color: white;
    text-decoration: none;
    transition: color 0.3s ease;
}

.footer-section a:hover {
    color: #FFD700; /* Add a highlight color on hover */
}
.hero {
    display: flex;
    align-items: center;
    background: linear-gradient(to right, #FFC107, #2196F3);
    color: white;
    padding: 50px 100px;
    position: relative;
    overflow: hidden;
}

.hero-content {
    flex: 1;
    z-index: 2;
    position: relative;
}

.hero-content h1 {
    font-size: 2.5rem;
    margin-bottom: 15px;
    color: white;
    font-weight: bold;
}

.hero-content p {
    font-size: 1.1rem;
    margin-bottom: 25px;
    color: rgba(255, 255, 255, 0.9);
}

.btn-register {
    background-color: #2196F3;
    color: white;
    border: none;
    padding: 12px 24px;
    border-radius: 5px;
    cursor: pointer;
    font-size: 1rem;
    text-transform: uppercase;
    font-weight: bold;
    transition: background-color 0.3s ease;
}

.btn-register:hover {
    background-color: #1976D2;
}

.hero-image {
    flex: 1;
    text-align: right;
    position: relative;
    z-index: 2;
}

.hero-image img {
    max-width: 500px;
    height: auto;
}
.bestseller-courses {
    background-color: white;
    margin: 20px;
    padding: 20px;
    border-radius: 10px;
}

.bestseller-courses h3 {
    font-size: 1.5rem;
    color: var(--text-gray);
    margin-bottom: 20px;
    text-align: center;
    font-weight: bold;
}

.bestseller-grid {
    display: flex;
    gap: 20px;
    overflow-x: auto;
    padding: 20px 0;
    scroll-snap-type: x mandatory;
    scroll-behavior: smooth;
}

.bestseller-card {
    position: relative;
    flex: 0 0 280px;
    border-radius: 15px;
    background: white;
    box-shadow: 0 10px 25px rgba(0,0,0,0.1);
    overflow: hidden;
    transition: transform 0.3s ease, box-shadow 0.3s ease;
    scroll-snap-align: start;
    border: 1px solid rgba(0,0,0,0.05);
    display: flex;
    flex-direction: column;
}

.bestseller-card .bestseller-badge {
    position: absolute;
    top: 10px;
    right: 10px;
    background-color: #FFC107;
    color: white;
    padding: 5px 10px;
    border-radius: 20px;
    font-size: 0.8rem;
    font-weight: bold;
}

.bestseller-card:hover {
    transform: translateY(-10px);
    box-shadow: 0 15px 35px rgba(0,0,0,0.15);
}

.bestseller-card img {
    width: 100%;
    height: 200px;
    object-fit: cover;
}

.bestseller-card .course-card-content {
    padding: 20px;
    flex-grow: 1;
    display: flex;
    flex-direction: column;
}
.bestseller-card .details-btn {
    margin-top: auto;
    width: 100%;
    padding: 12px;
    background: var(--secondary-blue);
    color: white;
    border: none;
    font-weight: bold;
    transition: background 0.3s ease;
}

.bestseller-card .details-btn:hover {
    background: var(--primary-blue);
}
.course-rating {
    display: flex;
    align-items: center;
    gap: 5px;
}


.stars {
  --rating: 0;
  --star-size: 20px;
  display: inline-block;
  font-size: var(--star-size);
  font-family: Times; 
  line-height: 1;
  position: relative;
}

.stars::before {
  content: '★★★★★';
  letter-spacing: 3px;
  background: linear-gradient(90deg, gold calc(var(--rating) / 5 * 100%), #ccc calc(var(--rating) / 5 * 100%));
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
}


.stars::after {
    content: "★★★★★";
    letter-spacing: 3px;
    position: absolute;
    left: 0;
    top: 0;
    width: calc(var(--rating) / 5 * 100%);
    overflow: hidden;
    white-space: nowrap;
    color: #FFD700; /* Màu vàng */
}
.header-container {
    background-color: var(white);
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    position: sticky;
    top: 0;
    z-index: 100;
}

/* Top Bar */
.top-bar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px 20px;
    max-width: 1200px;
    margin: 0 auto;
}

/* Logo Section */
.logo-section {
    display: flex;
    align-items: center;
}

.unica-logo {
    display: flex;
    align-items: left;
}

.logo-circle {
    height: 36px; 
    width: auto;
    margin-right: 30px;
    }

/* Category Button */
.category-btn {
    background: none;
    border: none;
    color: var(--dark-blue);
    cursor: pointer;
    display: flex;
    align-items: center;
    gap: 5px;
}

/* Search Box */
.search-box {
    flex-grow: 1;
    max-width: 500px;
    margin: 0 20px;
}

.search-box-inner {
    display: flex;
    border: 1px solid #ddd;
    border-radius: 4px;
    overflow: hidden;
}

.search-box input {
    flex-grow: 1;
    border: none;
    padding: 10px;
    font-size: 16px;
}

.search-btn {
    background-color: var(--primary-blue);
    color: white;
    border: none;
    padding: 10px 15px;
    cursor: pointer;
}

/* User Actions */
.user-actions {
    display: flex;
    align-items: center;
    gap: 15px;
}

.user-actions span,
.cart-icon {
    color: var(--dark-blue);
    cursor: pointer;
}

/* Buttons */
.login-btn,
.signup-btn {
    padding: 8px 16px;
    border-radius: 4px;
    border: none;
    cursor: pointer;
    font-size: 14px;
    transition: background-color 0.3s ease;
}

.login-btn {
    background-color: var(--light-gray);
    color: var(--dark-blue);
}

.signup-btn {
    background-color: var(--primary-blue);
    color: white;
}

.category-menu {
    background: linear-gradient(to right, #2c3e50, #3498db);
    padding: 10px 0;
    width: 100%;
}

.category-menu-inner {
    display: flex;
    justify-content: center;
    max-width: 1200px;
    margin: 0 auto;
    padding: 0 15px; /* Added padding for responsiveness */
}

.category-menu ul {
    display: flex;
    list-style: none;
    gap: 20px;
    margin: 0;
    padding: 0;
    flex-wrap: wrap; /* Allow wrapping on smaller screens */
    justify-content: center;
}

.category-menu a {
    color: white;
    text-decoration: none; /* Remove underline */
    padding: 5px 10px; /* Add some padding for better click area */
    border-radius: 4px; /* Rounded corners */
    transition: all 0.3s ease; /* More comprehensive transition */
}

.category-menu a:hover {
    color: #e74c3c; /* Bright red */
    background-color: rgba(255, 255, 255, 0.2); /* Subtle background on hover */
}
.category-btn {
    background-color: #f5f5f5;
    border: none;
    padding: 10px 15px;
    cursor: pointer;
    display: flex;
    align-items: center;
    font-weight: bold;
    position: relative;
}
.category-btn i {
    margin-right: 8px;
    color: #2e86de;
}

.search-section {
    margin-left: 20px;
    flex-grow: 1;
}

.search-box {
    padding: 10px 15px;
    width: 100%;
    max-width: 500px;
    border: 1px solid #ddd;
    border-radius: 20px;
    outline: none;
}

/* Navigation menu styles */
.menu-container {
    position: absolute;
    top: 100%;
    left: 0;
    z-index: 100;
    display: none;
}

.category-btn:hover + .menu-container,
.menu-container:hover {
    display: block;
}

.primary-menu {
    width: 250px;
    background-color: white;
    box-shadow: 0 2px 10px rgba(0,0,0,0.1);
}

.menu-item {
    padding: 12px 20px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    cursor: pointer;
    transition: background-color 0.2s;
    position: relative;
}

.menu-item:hover {
    background-color: #f5f5f5;
}

.menu-item i {
    margin-right: 10px;
    width: 20px;
    text-align: center;
}

.menu-item .fa-chevron-right {
    margin-left: 10px;
    color: #999;
}

.submenu {
    position: absolute;
    left: 100%;
    top: 0;
    width: 250px;
    background-color: white;
    box-shadow: 2px 2px 10px rgba(0,0,0,0.1);
    display: none;
}

.menu-item:hover .submenu {
    display: block;
    
    
}

.course-badge {
  background-color: red;
  color: white;
  font-weight: bold;
  padding: 2px 4px;
  border-radius: 6px;
  display: inline-block;
  margin-bottom: 4px;
  margin-left: auto;
}

.course-pricing {
	display: flex;
	align-items: center;
	gap: 8px; /* khoảng cách giữa các phần tử giá */
	flex-wrap: wrap;
	margin: 8px 0;
}

.real-price {
	font-size: 20px;
	font-weight: 700;
	color: #e53935; /* đỏ nổi bật */
}

.product-price {
	font-size: 16px;
	color: #888;
	text-decoration: line-through;
}

.product-discount {
	font-size: 14px;
	color: #027a3e;
	background-color: #e1f7e7;
	padding: 2px 6px;
	border-radius: 4px;
	font-weight: 500;
}



    </style>
</head>
<body>
    <header class="header-container">
        <div class="top-bar">
            <div class="logo-section">
                <a href="#" class="unica-logo">
					<img src="https://unica.vn/media/img/logo-unica.svg" alt="Unica Logo" class="logo-circle" id="logoImg">
				</a>
                <button class="category-btn">
                    <i class="fas fa-th-large"></i> DANH MỤC
                </button>
                <div class="menu-container">
                    <div class="primary-menu">
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-rocket"></i> Kinh Doanh
                            </div>
                            <i class="fas fa-chevron-right"></i>
                            <div class="submenu">
                                <div class="menu-item">Quản Trị Kinh Doanh</div>
                                <div class="menu-item">Kinh Doanh Online</div>
                                <div class="menu-item">Khởi Nghiệp</div>
                                <div class="menu-item">Quản Trị Nhân Sự</div>
                                <div class="menu-item">Thương mại điện tử</div>
                                <div class="menu-item">Bán hàng</div>
                            </div>
                        </div>
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-language"></i> Ngoại Ngữ
                            </div>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-paint-brush"></i> Thiết Kế
                            </div>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-lightbulb"></i> Kỹ Năng
                            </div>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-code"></i> Lập Trình & CNTT
                            </div>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-heartbeat"></i> Sức Khoẻ Và Làm Đẹp
                            </div>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-bullhorn"></i> Marketing
                            </div>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-desktop"></i> Tin Học Văn Phòng
                            </div>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-coffee"></i> Phong Cách Sống
                            </div>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-heart"></i> Hôn Nhân Và Gia Đình
                            </div>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-chart-line"></i> Tài Chính Kế Toán
                            </div>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-music"></i> Âm Nhạc
                            </div>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="search-box">
                <div class="search-box-inner">
                    <input type="text" placeholder="Tìm khóa học, giảng viên">
                    <button class="search-btn">
                        <i class="fas fa-search"></i>
                    </button>
                </div>
            </div>
            
            <div class="user-actions">
                <span>Doanh nghiệp</span>
                <span>Hội viên</span>
                <i class="fas fa-shopping-cart cart-icon"></i>
                <button class="login-btn">Đăng nhập</button>
                <button class="signup-btn">Đăng ký</button>
            </div>
        </div>

    </header>

    <main>
    
        <section class="hero-banner-slider" aria-label="Course Promotions">
            <div class="slider-container">
                <div class="slider-content">
                    <!-- Existing slider items with added accessibility -->
                    <div class="slider-item" role="group" aria-roledescription="slide">
                        <!-- Existing content with semantic improvements -->
                    </div>
                </div>
                <div class="slider-controls">
                    <button class="slider-prev" aria-label="Previous Slide">&#10094;</button>
                    <button class="slider-next" aria-label="Next Slide">&#10095;</button>
                </div>
                <div class="slider-dots" role="tablist"></div>
            </div>
        </section>
        <nav class="category-menu">
            <div class="category-menu-inner">
                <ul>
                    <li><a href="#office-it">Tin học văn phòng</a></li>
                    <li><a href="#design">Thiết kế</a></li>
                    <li><a href="#languages">Ngoại ngữ</a></li>
                    <li><a href="#marketing">Marketing</a></li>
                    <li><a href="#finance">Tài chính kế toán</a></li>
                    <li><a href="#technology">Công nghệ</a></li>
                    <li><a href="#health-beauty">Sức khỏe - Làm đẹp</a></li>
                    <li><a href="#family">Hôn nhân gia đình</a></li>
                    <li><a href="#books">Sách hay nên đọc</a></li>
                    <li><a href="#inhouse">Đào tạo inhouse</a></li>
                </ul>
            </div>
        </nav>

          <section class="top-courses" aria-labelledby="bestseller-title"> 
			  <h3 id="bestseller-title">TOP BÁN CHẠY</h3>
			  <div id="bestseller-courses" class="course-grid" aria-live="polite"></div>
			</section>

			
			<section class="top-courses" aria-labelledby="today-sale-title">
			  <h3 id="today-sale-title">SIÊU ƯU ĐÃI HÔM NAY</h3>
			  <div id="today-sale-courses" class="course-grid" aria-live="polite"></div>
			</section>


       <section class="topics-of-interest" aria-labelledby="topics-title">
    <h3 id="topics-title">CHỦ ĐỀ CÓ THỂ BẠN QUAN TÂM</h3>
    <div class="topic-tags" role="list">
        <a href="#office-it" role="listitem" class="topic-tag">Tin học văn phòng</a>
        <a href="#design" role="listitem" class="topic-tag">Thiết kế</a>
        <a href="#languages" role="listitem" class="topic-tag">Ngoại ngữ</a>
        <a href="#marketing" role="listitem" class="topic-tag">Marketing</a>
        <a href="#finance" role="listitem" class="topic-tag">Tài chính kế toán</a>
        <a href="#technology" role="listitem" class="topic-tag">Công nghệ</a>
        <a href="#health-beauty" role="listitem" class="topic-tag">Sức khỏe - Làm đẹp</a>
        <a href="#family" role="listitem" class="topic-tag">Hôn nhân gia đình</a>
        <a href="#books" role="listitem" class="topic-tag">Sách hay nên đọc</a>
        <a href="#inhouse" role="listitem" class="topic-tag">Đào tạo inhouse</a>
    </div>
</section>

        <section class="featured-instructors" aria-labelledby="instructors-title">
            <h3 id="instructors-title">GIẢNG VIÊN TIÊU BIỂU</h3>
            <div class="instructors-container" aria-live="polite"></div>
        </section>

        <section class="instructor-recruitment hero" aria-labelledby="recruitment-title">
            <div class="hero-content">
                <h2 id="recruitment-title">Trở thành Giảng viên Unica</h2>
                <p>Giúp mọi người trở nên tốt hơn - bao gồm cả chính bạn</p>
                <button class="btn btn-register" aria-label="Register as Instructor">Đăng ký ngay</button>
            </div>
            <div class="hero-image">
                <img src="/api/placeholder/500/400" alt="Instructor Illustration">
            </div>
        </section>
    </main>

    <footer class="footer" role="contentinfo">
        <div class="footer-content">
            <div class="footer-section">
                <h4>Về Unica</h4>
                <nav>
                    <ul>
                        <li><a href="#">Giới thiệu về Unica</a></li>
                        <li><a href="#">Hướng dẫn sử dụng</a></li>
                        <li><a href="#">Kích hoạt khóa học</a></li>
                        <li><a href="#">Góc chia sẻ</a></li>
                    </ul>
                </nav>
            </div>
            <div class="footer-section">
                <h4>Hợp tác</h4>
                <nav>
                    <ul>
                        <li><a href="#">Đăng ký giảng viên</a></li>
                        <li><a href="#">Giải pháp e-learning</a></li>
                        <li><a href="#">Đào tạo doanh nghiệp</a></li>
                        <li><a href="#">Đào tạo Inhouse</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </footer>

    <script>
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
            {
                id: ${status.index},
                title: "${course[0]}",
                instructor: "${course[1]}",
                rating: ${course[2] != null ? course[2] : 0},
                reviews: 0,
                price: ${course[3] != null ? course[3] * 1 : 0}, // ép thành số
                originalPrice: ${course[3] != null ? course[3] * 1.5 : 0},
                image: "${course[4] != null ? course[4] : '/api/placeholder/300/200'}",
        	    badge: "Bán chạy"
            }<c:if test="${!status.last}">,</c:if>
            </c:forEach>
        ];


        const todaySaleCourses = [
        	  <c:forEach var="course" items="${todaySaleCourses}" varStatus="status">
        	  {
        	    id: ${status.index},
        	    title: "${course[0]}",
        	    instructor: "${course[1]}",
        	    rating: ${course[2] != null ? course[2] : 0},
        	    reviews: 0,
                price: ${course[3] != null ? course[3] * 1 : 0}, // ép thành số
                originalPrice: ${course[3] != null ? course[3] * 1.5 : 0},
        	    image: "${course[4] != null ? course[4] : '/api/placeholder/300/200'}",
        	    badge: "Giảm ${course[5]}%"
        	  }<c:if test="${!status.last}">,</c:if>
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
        
        function renderTodaySaleCourses() {
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
                goToSlide(currentSlide - 1);1
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
    </script>
</body>
</html>