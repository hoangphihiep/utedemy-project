<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Học đệm hát Guitar cùng Haketu</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="/utedemyProject/views/Css/TeacherRegister.css">
</head>
<body>
<header>
<div class="logo">
<img src="unica_logo.png" alt="Unica Logo">
<span class="danh-muc">DANH MỤC</span>
</div>
<div class="search-bar">
<input type="text" placeholder="Tìm khóa học, giảng viên...">
<button type="submit">
    <img src=''>
</button>
</div>
<div class="right-menu">
<a href="#">Doanh nghiệp</a>
<a href="#">Hội viên</a>
<a href="#" class="main-button">Vào học</a>
<a href="#"><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOEAAADhCAMAAAAJbSJIAAAAh1BMVEX///8AAADf39/b29uCgoLk5OQtLS3m5uYnJycwMDDh4eGKiorZ2dkqKio2NjYfHx97e3uVlZW/v7/v7+9YWFiioqI4ODiOjo6Hh4f29vZ1dXUYGBgjIyPKysq0tLRGRkZpaWliYmKrq6ucnJxKSkrQ0NBHR0fDw8MTExNaWlo+Pj6hoaFubm4+yERkAAAI9UlEQVR4nO2d6XqiMBSGLRRRW+uCy1B3q3Vp7//6ppaanIMYErLik/fXPFOI+eAkZ0mARsPj8Xg8Ho/H4/F4PB6Px+PxeDwez+OTJkkcR3GcJGkt22cSLcb906TbOz49PR17rfbuezAM1fUjDYeD71279dd+d3LqjxeRsubLCJbf7aciZpvxXl5luv86zQrbb78vAwX9LyH8WBX++pXu80JGZLp47jLbX32EyrQUkGxHzJ/POE+rdiKcnjnaH20Tpaoo8bTH8fu/rPcV2t+veZvvTWPl6n6ub5/39385HQTbP5yE2u+rNtZ0IPT7F9YdgfY73PePMFDqQg484yPP7Iu7/a/iyZPNWdRM7pN83/mN1ttqNxqtJs1j8d9XfMNxf2d6PjYnq9Fot3pr3fn9b0VTzuLttu3uerDcx3+xRprG4fCrX3SfxxztjwvOm7x/DcM4/Ws/iffLwbp5e9jbQoXA2xE4Gu+LLl4abje3l7ls1otvDWSzLQyP0v341lkNpPUl+Smu9fLK6u84b3FdtqXu8w5+NWZdk9eXvMWeJC01znV4tSw9ZZG/kSxL2udvX7nZLXNdOkvFqx08tnZDrhk677rvX5UlPpAvUEiHO3RWW8I1hiiImW25PdABX+d78w2eY1bcs3+6Rd5lVlligNp5FjL4MXIgxZ7xAx5y5Jl2CckzPLcnElwAOnASmA0Fzw7QvFfUfSRwJJoXDeHln1SKUxM4BnfiiVmKAtlbiUhgXzwEC+Bo/Kwwo6awgfdKMeCWJRG52W2V5lGotRPv4Qs4vapbRVMlHotQ4LFqgPkPNPIievKQaWG87OFkDCVCgb0qyWQGtHTBiSLqFvdMlABK/CD/jQTK1F6Av+mKef4NPfOfRAfuSJyqEoiu1UbkPDBH9KU68ONzYCD5ke/VU6uiKyMAxygwXyW0VyPpTLqTv4tQ4ExWYCOlfrfF7zKoJ2sqKPkEcyQRjUEF1ZaY5o3c9ragXVBSKAigoW7Av+dKykkH2iBvQkzvu3x++UtQXIZQIxCa/YjvBJqzvakqZyFDVWmiv6S0zMLnWqkdiUbb9wlvJc5YtQIxaHjC5TFeyeHfyrrwcxfzdaSWypIujVB5Ltu70NHc5MZiT2nj9K68lx9MfeFaZR9+DLWlTWCjQeomHD6R5gNKapGAkMa6c8UCgYMrL5URk+aceQUIrgurLdUCgYcrnTziHv/FEOZvLKo20QvE9HplURiZeGc6liFfLxIl8sH7JKRsU+biSKgum1MUEzZ1mOgFEks/lxxIyk+q55k/Xs96BNK55sw+LhaYdauhrV0SNbEHIhmGQgmzE5AlJPZAJPWFD+ZhLkIyjCnzsM31MHUryKY48JkfmWjMbbFSRXTt+op1VHL199omGn2QgLrH6ntwvQ4ViuS2ocsQrPokSe/XhrqlEpJfsGIm4jY50iznIIktK1gh7rAs9HEREnCyHCKJ0Nk+xU2IL2dlRaSaXz+HDxaiWNV9olBRodQoAx6FxEqF1xsdgKzpsqyURD61nmlYEefjewtSd6xf8gSyBlaOTaPXGkZtZB8WK2sgifK8hpE3X9/JdZBemzUOsb9P5mEkelW37GQKEnGumYdxlgJchARt7GiFOET1NX3dkLo+uwBDqokzHU+l6CQmRe+SnpNEuW4DkQxD9kQDrHltoFcqIXNk2R4uuiReL4+YPPEaH10CrrTn0xok7yuvEpI1nDJ7dovPa7fL18wO3LfbJejgKi/W0+03dXKJxBnybHKaClwOV6CGxxOLEadfn8I32HXPFajQLUPVN3ibhe6F5qtN0A1Gx3r4xIQ+n8O5hE4fzFK5s00fdFfbmvMMehNr4fbBpnTuXRB0Oj1q2jmhkFdqo/xJbTohJ51dn09T+njWRKCv4ImZk+MSN7SrQkEYeGhq7bRE6toEp8WE2qmm/V9qAM+TTARdG3wE2V2J8BFH4f2A8LEwV9dp4KOyFVY8185LhALXFc5PR45LhAKrrSSh52TdkwjHYNUsKIav/JB7DFE98A5We5T7QtRzViIU2JPYhBfB15a5ZKjQRNtyL8aYOCkR3sE3yYXADryLrhgqFNiUXumMWs5JhAKlnyP+IXLNUOEYlDXRDDQW7W8lQmNQ0XsUkaHalqjaRDNid6YbaKJdhdsp0Fi0KRGNQaWv+uzAAM6eoUITbSt+l2k8d0AiFCj/NoY8KAy34zRQqKZhS5P1sajDTWBQGG7eUKHArqb3CcfwmXrTd1GPH8wT2wvg0BjU+EboyFYAp9NNYGI7ARzK6DVvDLVSu9EVqhVjwWmYGoNXItMBHHITWt/IfsXwWEShmoE7eMFoAAdNtGlIoNGsH7kJIyaaETUNSYQC58bu4AVDAZw9gYYqcKbdBMZA1o8KvwbH4BXtWb8NN4HRHMChUM2KwJzTUC0RjUELJprR0Zf1o4TXmkCN1XCULlky0YxITwBnJ1QrRksAZ9tNYOBboxU904dM1LpADTEqCtUcEKjcaaAx6ITAXAAna6goo3dmhzLadyMnEQo8Wp5FIZGqAA6Fao6YaIYiQ0VuwhkTzeg0FUiEX3NQ/hJXaVAAV02ia34wj3RKbL4uKkogNxahibrjJjCduYREKLD6B5x0IzHduBeqFVPZabjsJjABzPr5XxjmtpvAoC+S8EqEAo8Om2gG2rHBZ6iu1GR4EXYadXATGPRFunJDhR8H0/G2dh10RAI4eAddDNWKEXAabia85XA7jZeaCuR2GvDzZO67CQxXAAfHYB3cBAY5jeK7CE3U7VCtGPR1oCKJ0EQ1fRJCMyVjEQp0N11i02EFcCiSqalAptOAd7COY/AK+t4alIi+YlnbO3gBBXBUIhTockbPQ2GmgUy05gILxyL8imW9QrViOrOcRPSxY4cWX6qTC+CgidraJ6MaZKibhxqDVwKY9VPqF2zfp/g7pA9iohnIUB/NRDPCvMRHE5j7BPmjmWgG+tRq6wEForFobzulXsinVuubD5YRZAGcnS3NZvit3djeTqmXH6fxqGPwSnh+cIGNhtNvYfR4PB6Px+PxeDwej8fj8Xg8Hk+d+Q/WAGiEIa++5wAAAABJRU5ErkJggg==" alt="Yêu thích"></a>
<a href="#"><img src="data:image/png;base64,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" alt="Giỏ hàng"></a>
<img src="user_avatar.png" alt="Avatar" class="avatar">
</div>
</header>
<div class="banner">
    <div class="banner-content">
      <h1>Hợp tác giảng dạy cùng Unica</h1>
      <p>"MỖI CHÚNG TA ĐỀU CÓ MỘT SỨ MỆNH CHIA SẺ LẠI GIÁ TRỊ CHO THẾ HỆ SAU"</p>
      <p class="author">- Nguyễn Trọng Thơ - CEO INET -</p>
        <a href="#" class="button">TRỞ THÀNH GIẢNG VIÊN</a>
    </div>
   
  </div>


<div class="video-container">
    <video controls>
    <source src="https://www.youtube.com/watch?v=w59RndMYVFg">
    </video>
    <div class="video-info">
    <div class="video-title">
    <img src="unica_logo.png" alt="Unica Logo" class="logo">
    <span>HỌC TỪ CHUYÊN GIA - Introduction</span>
    </div>
    <div class="video-actions">
    <a href="#">Xem sau</a>
    <a href="#">Chia sẻ</a>
    </div>
    </div>
      <div class="video-overlay">
       
      </div>
    </div>


    <div class="banner2">
        <div class="partner-student">
          <div class="partner-box">
            <img src="unica_logo.png" alt="Unica Logo">
            <h2>UNICA PARTNER</h2>
            <ul>
              <li>- Chuyên gia</li>
              <li>- Giảng viên</li>
              <li>- Doanh nhân</li>
            </ul>
          </div>
          <div class="mission-box">
            <img src="unica_logo.png" alt="Unica Logo">
            <h2>SỨ MỆNH</h2>
            <p>Mang tri thức thực tiễn tới 10 triệu người dân Việt Nam</p>
          </div>
          <div class="student-box">
            <img src="student_icon.png" alt="Student Icon">
            <h2>UNICA STUDENT</h2>
            <ul>
              <li>- Lãnh đạo</li>
              <li>- Người đi làm</li>
              <li>- Học sinh, sinh viên</li>
            </ul>
          </div>
        </div>
        <div class="banner2-content">
            <span>
                <p><b>Unica là e-learning platform, là cổng kết nối các CHUYÊN GIA tới hàng triệu người dân Việt Nam.</b></p>
                <span>
            <p>Các bài giảng được dưới dạng video giúp học viên có thể xem được bất kỳ khi nào, bất kỳ đâu.</p>
          </div>
      </div>
      <div class="title-center">5 lí do giảng dạy trên UNICA</div>
      <div class="features-step">
        <div class="step">
          <img src="https://img.freepik.com/premium-vector/outline-growing-bar-chart-icon-isolated-flat-design-vector-illustration_8559-586.jpg" alt="chart">
          <span>Làm 1 lần</span>
          <span>bán n lần</span>
        </div>
        <div class="step">
          <img src="https://img.freepik.com/premium-vector/outline-growing-bar-chart-icon-isolated-flat-design-vector-illustration_8559-586.jpg" alt="chart">
          <span>Quảng bá tới</span>
          <span>Hàng triệu học viên</span>
        </div>
        <div class="step">
          <img src="https://img.freepik.com/premium-vector/outline-growing-bar-chart-icon-isolated-flat-design-vector-illustration_8559-586.jpg" alt="chart">
          <span>Chia lợi nhuân</span>
          <span>30%-100%</span>
        </div>
        <div class="step">
          <img src="https://img.freepik.com/premium-vector/outline-growing-bar-chart-icon-isolated-flat-design-vector-illustration_8559-586.jpg" alt="chart">
          <span>Thanh toán trước ngày</span>
          <span>15 hằng tháng</span>
        </div>
        <div class="step">
            <img src="https://img.freepik.com/premium-vector/outline-growing-bar-chart-icon-isolated-flat-design-vector-illustration_8559-586.jpg" alt="chart">
            <span>Học qua VOD, Live và</span>
            <span>Offline event</span>
          </div>
      </div>
     
      <div class="title-center1">Ai nên đăng kí giảng dạy ở UNICA</div>
      <div class="features-step">
        <div class="step">
          <span>Đối tượng 1</span>
          <img src="https://img.freepik.com/premium-vector/outline-growing-bar-chart-icon-isolated-flat-design-vector-illustration_8559-586.jpg" alt="chart">
          <span>Chuyên gia</span>
        </div>
        <div class="step">
          <span>Đối tượng 2</span>
          <img src="https://img.freepik.com/premium-vector/outline-growing-bar-chart-icon-isolated-flat-design-vector-illustration_8559-586.jpg" alt="chart">
          <span>Giảng viên</span>
        </div>
        <div class="step">
          <span>Bước 3</span>
          <img src="https://img.freepik.com/premium-vector/outline-growing-bar-chart-icon-isolated-flat-design-vector-illustration_8559-586.jpg" alt="chart">
          <span>Doanh nhân</span>
        </div>
        <div class="step">
          <span>Bước 4</span>
          <img src="https://img.freepik.com/premium-vector/outline-growing-bar-chart-icon-isolated-flat-design-vector-illustration_8559-586.jpg" alt="chart">
          <span>Doanh nghiệp</span>
        </div>
      </div>




     


      <div class="title-center2">4 bước để trở thành Giảng viên ở UNICA</div>
      <div class="features-step">
        <div class="step">
          <span>Bước 1</span>
          <img src="https://img.freepik.com/premium-vector/outline-growing-bar-chart-icon-isolated-flat-design-vector-illustration_8559-586.jpg" alt="chart">
          <span>Đăng ký giảng viên</span>
        </div>
        <div class="step">
          <span>Bước 2</span>
          <img src="https://img.freepik.com/premium-vector/outline-growing-bar-chart-icon-isolated-flat-design-vector-illustration_8559-586.jpg" alt="chart">
          <span>Chuẩn bị nội dung</span>
        </div>
        <div class="step">
          <span>Bước 3</span>
          <img src="https://img.freepik.com/premium-vector/outline-growing-bar-chart-icon-isolated-flat-design-vector-illustration_8559-586.jpg" alt="chart">
          <span>Quay video khóa học</span>
        </div>
        <div class="step">
          <span>Bước 4</span>
          <img src="https://img.freepik.com/premium-vector/outline-growing-bar-chart-icon-isolated-flat-design-vector-illustration_8559-586.jpg" alt="chart">
          <span>Xuất bản & bán khóa học</span>
        </div>
      </div>


    <div class="instructors-banner">
        <div class="banner-title">HƠN 500 GIẢNG VIÊN ĐÃ CÓ KHÓA HỌC TRÊN UNICA</div>
        <div class="instructors-container">
            <div class="instructor-card">
                <img class="instructor-avatar" src="data:image/jpeg;base64,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" alt="Avatar">
                <div class="instructor-name">Hồ Ngọc Cương</div>
                <div class="instructor-title">Giảng viên, Chuyên gia Facebook Marketing</div>
              </div>
             
           
              <div class="instructor-card">
                <img class="instructor-avatar" src="data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxISEhUSEhAVFhUVFRUVFhYVFRcVGBcVFRUXGBYVFxUYHSgiGBolHRUWITEhJSkrLi4uFx8zODMtNygtLisBCgoKDg0OGxAQGi0lHyUtMS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0vLS4tLS4tLS0tLS0tLS0vLS0tLS0tLS0tLf/AABEIAOEA4QMBEQACEQEDEQH/xAAcAAACAgMBAQAAAAAAAAAAAAAAAQIGBAUHAwj/xABFEAACAQIBCQQFCQUHBQAAAAABAgADEQQFBhIhMUFRYXEigZGhBxMyUrEUI0JicoKSwfGistHh8BUkMzRDY3NTdLPC0v/EABoBAQACAwEAAAAAAAAAAAAAAAABBAIDBQb/xAAzEQEAAgECBAIJBAEFAQAAAAAAAQIDBBEFEiExQVETIjJhcYGhsdFCkcHh8BQjM1LxNP/aAAwDAQACEQMRAD8A7gTAQECUAgEAgK0BwCAQIkwGBAcAgECJgMCA4BARMBQJCAQCAjAQECUAgEBWgOAQCArwHAIBAICMAAgDMBrJA6wmImekMOrlfDrtr0/xAnwEwnJWPFvrpM9u1J/ZjtnHhR/rDuVj8BMfTU822OHamf0fWPyQzkwp/wBYfhcfER6anmmeG6mP0fWPy96WWcO2yvT72A+MyjJSfFqto9RXvSf2ZtOoGF1II5G8yid1eazXpMJSUEYCtAkBAIBAIBAIBAIBAIBAIESYABAlAIBAhVqKoLMwAG0k2A7zImdu6a1m07VjeWgx+d1FNVMGoePsr4nWe4TRbUVjt1dTBwnLfreeWP3lX8ZnPiX2OEHBBb9o3PhaaLZ7y6uLhenp3jeff+GprVmc3dmY8WJPxmqZme69SlaRtWIj4POQyOSx7lIZCBKnUKm6kg8QSD4iTE7dkWrFo2tG7aYTOPE0/wDU0hwcaXnt85srmvHipZeG6fJ+nb4dP6+jf4DPCm2qqhQ+8O0vhtHnN9dRE93LzcHyV6453+k/hYsPiEqLpIwYcVN5YiYnrDlXx2xzy3jaXrJYCAiYADAcAgEAgEAgECJMAAgSgEAgV7LOc6UrrTs7jV9UHmRtPITRkzxXpDp6Xhl8vrX6R9ZU7H5Qq1jeo5PAbFHQSpa827vQ4NPjwxtSNvuxZg3CAQHJR3KQkQCAQGZKIKQl74TF1KTaVNyp5b+o2HvmVbTWd4a8uGmWvLeN4W7I+ditZK4Cn3x7J6+78OktY9RE9LOFquE2p62LrHl4/wBrLpbxLLjT0AECUAgEBXgOAQCAjAQECUAgQrVVRSzEADWSdgkTO3WWVazaeWsbyo+X85Gq3SldaewnYz/wHLx4SnkzTbpHZ6LRcNri2tk62+kf2r0rutECAQCA5LHuUhkCYGqxOceEpmzYhbjct3/dBmyMV58FS+v09Ok3j5dfsjh858I5sK6g/WDJ5sBJnDePBjXiOmt2v++8fdtqdQEAqQQdhFiPEbZr7LUTFusHIZiAQHBMtxkTLz4chTdqe9d681O7psm7HlmnTwc7V8PpqPWjpbz8/j+V8weKSqgem11P9WPAy9W0WjeHmsuK+K01vG0veS1iBEmAAQJQCAQCAQCBCtVVFLMQFAuSdwEiZ26yyrWbTFax1lz7ODLbYhrC4pg9leP1m58t0o5cvPPuen0Wirp67z1tPf3e6Goml0YgoBAIBApWcWeRDGnhratRqnXr+oDq7z4b5bx4PGzg6zisxM0w/v8Aj8quct4rS0vlNW/2zb8Ozym/0dPJy/8AV599+ef3/jsnlLLuIrqEqVLqBawGiG5sBtPlyiuOtZ3iE5tZmzRFb26ftv8AFrlEzVkybdYGXkjLFbDNpU21XuyH2W6jceY1zC9IvHVY0+pyYLb0n5eE/wCebp+RsqJiaYqJ0ZTtVt4MoXpNJ2l6vTammenPX5x5Szpg3iA7yUbFIS2GRsrPh30l1qfaXcR+R5zZjyTSVXV6SmoptPfwn/PB0XBYtKqCohup8uIPAy/W0WjeHk8uK2K80vHWHqTMmsAQJQCAQEIDgEAgUTOvLXrW9Uh+bU6yPpsN/QbvHhKWbLzTtHZ6Xhui9FX0l49afpH5V6V3VEAgEB2giVVz7ywaVMUUNnqDtEbVp7D3nZ0vLGnx7zzT4OTxXVTjp6Oveft/f5c8l15sQMvJWTauJqrRooXqNsA1WA2sxOoKN5kTMRG8piJmdoWTLXo8x+FpGqVp1VUXb1LMzIN5KsoJHMX6Wmuuatp2bLYbVjdULza1CBuc1cqGhXGvsvZWG7X7J7ifMzVlpzVXdBqJw5Y8p6T/AA6hSqhhcfpKExs9ZW0SnISIBAIG2zeyucO+v/DbUw4fXHMeY7ptxZOSfco6/RxqKdPajt+HQ6ZBAINwRcHiNxnQeUmJidpThAgEAgEAgECvZ3ZW9VT9Wh7dQd6pvPU7B38Joz5OWNodThml9Lk57do+sqJKL0wgEAgOSx7lIZdnIcu48169SpfUWIX7C6l8hfvM6WOvLWIeM1Wb02a1/wBvh4MGZtDdZm5GGMxlKg19AktUINjoIpYi+69gv3phe3LXdnSvNbZ170c5n/IEqNUsa1R2XS4UkYhAOGl7Z6gbpVy5Obst4cfJ3XKam58/eknIIweNYILUqo9bTG5bkh0HIMCRwDAbpexX5qqGWnLZVpsajC3gdMyPji1Km4OsqNLqNTed5QvXaZh6nTZZvjrfzhvKNUMLj9JqmNnQraJhOQkQCAQLhmXlW4+TudYuafTevdtHK/CW9Pk/TLgcW0m0+mr8/wArZLTiCBEmAoE4BA8q1UKCzGwAJJ4AC5kTO3VNazaYrHeXMsp401qrVDvOocFGweE517c07vZabBGHHFI8PuxZg3iAQHJY9ykMmuzixXqsNWcbQhA+03ZHmRNmON7xCtrMno8F7R5OSCdF44wLwOrehfIZHrcYw23o0+YuDUbpcKvVWlbUW/Ss6en6nU5WWxA5j6ccKDSw1a2taj078nTS+NOWNPPWYVdTHSJckVby0qpE21CBcszq16BX3HI7jZvzMqZ49Z3uGX3w7eU/2sFGqVNx+s0ulW0xLaUaoYXH6TCY2Wa2iYeggmRCYKQPTD1mRldTZlII6iTE7TvDHJSL1mtu0uoYDFrVprUXYwv0O8dxuJ0q25o3h4zNinFkmk+D2JmTUYEBwCBEmBXs9MboURTB11Dr+ytifOw8ZX1Ftq7ebq8Jwc+XnntX7yo0pPSiAQGJKJKQmIECs+kKvo4YL79RR3KC3xUSxp49fdyuMX2wRXzmPz/DnSi8uvNN7mlm3Ux1cUkJVRZqr7kT/wCjrAH5AzC94pG7OlJvOz6EwGDSjTSlTXRRFCqOAHxPOUJmZneXQiIiNoZEJECmelvC6eTXIFzTqUnH4whPhUM24J9do1Eeo4YzW2S6pPOBZsya3aqJxCsO4kH94SvnjpEurwq3rWr8JWyVnZelCoVNx4cY2TFtp6NnSqhhcTCVmsxPVOQyEAgW7MfG+3RJ+uvkGHwPeZb01u9XB4xh61yx8J/hbQJacM4BAi0BgQOfZ3YrTxLDcgCDu1t5kjulDPbe71XC8XJp4nxnr+Glml0BAIBAIBAruWMjPlDF08KjhFp02q1XI0goZgqjRuLsbGwuNRJljFaMdZtLhcV3yZa448I3n5sKr6L8YK4pIyNTIv689lQL6wyXJ0+AGo8Ru3RnrMbuTOntE7Or5s5v0cDRFGkL73c+1UfezHyA3CVr3m07ytUpFY2htpizEAga7OPAfKMLXo76lKoo+0VOifG0mk7WiWN43rMPmVTOi5pwNpmzX0MQnBroe8avMCa8sb0lb0N+XPHv6L+BKT0UyCZJEJUapU3H6yGdbTE9G0o1QwuP0mExss1tEwnISIGfkTF+rr03vq0gD9luyfjfumzHbltEq2rw+kwWr7vrHV02dF44QCAQE7WBJ3C8JiN52cor1S7M52sxY95vOXM7zu9vSkUrFY8I2echkclBSEiAQEzAAkmwAuTwA3wTMRG8tL6KsqCtjsYxOuqiMgO3Qpsy28HSWc9OWlYeVrn9Nmvfz7fB1anslevZtnunMkCAiYAIDgfOOe+S/k2Or0rWXTNRPsVO2Lchcr92X8dt6xLnZK8tpho5mwSRypDDaCCOoNxCYmYnePB0zDVw6K42MoYdCLznzG07PVUtF6xaPHq9JDMQJ0apU3H6wyraYndtKVQMLiYTGyxW0W7JyGQgdTyZX9ZSpv7yKT1tr8506TvWJeL1GP0eW1fKZZMyaRAIGFlupo4eqf8AbbxIsPjMMk7VlY0lebPSPfDmE5r2RyWPcpDKIEAgECuZ95R9Vh/Vg9qtdfuD2z8B96WMFN7b+Tl8U1HJh5I726fLx/CkZuZYfB4iniEF9A2ZdmkjamXw8wJbvSL12l5ulprbeHec3c7MJjCEoVbuULmmVZWUAqDe4tqLDYTKE47U7uhGWt+zfyGREwEIQlCRA5n6Z8g6dOnjEXXS+bq2/wCmx7DH7LG33zLGC3XlVtRTpzORBTtlpUKBdMz8XpUTTJ10z+y2sedx3Spnrtbd3eG5ebHyeX2lvppdEQGBJRunSrFTceHGRPVlWZr1bOjVDC4/SYTGy1W0TCchLoeaNW+FTkWXwY28iJfwT6kPK8Try6m3v2+zczc54gEDVZ0tbC1eijxdRNWb2JXeHRvqafP7S5zKD1fcpDKIEAgEB3ksdnLc8cf63FPY9mn82v3faP4r+Al/DXlo8rxHN6XUT5R0/P1aSbVFuczss/I8ZSrn2AStT/jcaLHuuG+7ML15qzDPHbltEvo8MCLg3B1gjZY7DKDobiBKEiAQPHFYdaiNTdQyOpVlOwqwsQe4yY6dUT16Pm/OfJy4bFVsOlQOtJtEMNtiAwVvrC9jzBl+luaN3OvXlnZq5kxbPN3G+qrqSey3Ybv2HuNvOa8teaq1os3os0TPaekr/KT0hgSUblISIE6NUqbj9YZVtMS2lGqGFx+kwmFmLRMbr7mSf7uRwqMPJT+cu6f2HmuL/wDPHwj+VhE3uWcAganOof3Wp0U+DrNWb2JXeHf/AE0+f2lzmc960QCAQTJyWPdF2sCeAJhMztDihct2jtOs9TrM6nZ4beZ6z4iAQOu+iLO1aiDAVmtUpL8yT9OkPofaQfs9DKuam3rQt4Mm/qy6bK6yIBAiTCFaz9zpTJ+GL3BrPdaKHe9vaI91dp7hvmzHTmlryX5IfOQrsXLsxLMSWJ1kljck87m8vKDKgEDoGbmO9dRVie0vZbmRsPeLGUsleWz0ejzelxRM946S2RM1rUQISIBA9KVQqbjw4iNiLTEunZhOGwxYb6jfuqJa08eq4nFrROaNvKPvKyTe5ggIGBg5dp6WHqj/AG2PgL/lMMkb0lZ0duXUUn3w5lOa9iIBAd5KNikJEDj2VcCaFZ6RHsnVzU+yfC06dLc0bvFZ8M4ck458Pt4MSZNQgQNRqbLVpsVdCCrKbFSDcEGJjcidn0bmLll8ZgaGIqgCo4YNo6gSjsl7br6N7c5z8kRW0w6OOZtSJlYJizIwMPK2OFChVrlSwpU3qaINi2gpa192yTHWYhjPSJl8z5yZfrY6u1esdZ1Ko9lE3Io4c951y/WsVjaHPtabTvLWILkTJizoBAs2ZFQ6VVd1lbvBI/OV88dIl1eFWnmtHwWyVnZEAgO1pKCJkJdUzBpaOCQ+8zt+2QPhLmGPUee4hO+efl9lim1SEBAQFVQMCp2EEeMT1TWeWYmHJ6lMqSp2qSD1BtOXMbdHuK2i0RaPFGQkQCAQCBoc68gDEppJqqoOyfeHuH8uB6mbsOTknr2c7iGjjPXevtR29/uc1qauyRrGogixBG0G++8vvLPOAiLwO6+iiqpyZRVWBNNqqsBtVjVdgCNx0WU9CJztRH+5Lo6ef9uFxDmauaW7YFzJ5jlafO7/ACOL/wC2r/8AiaTj9uPixyexPwfMQnUcpkYZN/hAy1XjAhAtWZNA2qVNxIUd2s/EStnntDscLp0tf5LRK7rCAQCAjCXaMg4b1WGopvWmt+trnzJl+kbViHltRfny2t72cTMmktKBKAQOc504X1eJfVqezj7239oNOfmrteXrOHZefT193T9v62aqa1zuUhkIBAcG4ko2c+9IGTAlRa6jVU1N9tRqPeP3Zc09945fJ53i+nil4yR+rv8AH+4+ypyw5AgW70ZZabD4xUJ+bxBFJwTq0zqpsOekQvRjNOenNTfybsF+W+3m7pOc6RwMDL+Farha9JfaqUaqL1ZGA8zMqTtaJY3jesw+XqVMk2ItbaN4ttHWdVyWegt+UAZrwPbAYJ6zhEGs7TuUcTMbWisby24cNstuWv8A46FgcItKmtNdijbxO0k8yZStabTvL0uHFGKkUr4MiYtgIhG4hIgZuQ8H67EUqVtTOL/ZGtv2QZlSN7RDVnyejx2t7v8Ax2gy+8sUA0YEoCJgVnPfB6VNao2obN9lth8beMr6ivTd2OEZtsk458fvH9KVKT0QgEBwTIMljHUpDJXs/KYODY+69Nh1LBfgxm/Tz67m8WrvppnymPvt/LmkvPLpKN8Cw5h5OOJyhh01hEqLWqMNyUiH82Cr96RaN42TWdp3fRXyemdlYd6/zlT/AEvvW41fuP1NIbat+g/WTGljxlE6qfCDGIor7KFjxb+vym2uGlfBqtmvbxfP/pPak2UqxpUlp2Ces0RYPVI0mcjiQ4Bta5UnfebmlUma8CWHoNUZUUXZjYf1wkTO0byypSb2ite8uhZLyclBNBdv0m3sePTlKV7zad5em0+CuGnLX5z5syYNxiSgiZCRAIF19GuT7vUxBGpR6tftGxY9wt+KWMFeu7lcTy7RGOPiv8suMYEBwEYEbQI4mgtRGRhqYEHvkTG8bSzx3nHaL17w5fjcM1J2pttU268D3ixnNtWaztL2eHLXLSL17S8Ji2CAQJ0qZYhVFydwkxEzO0Mb5K4681p2huMLkQbajdy/xlmun/7OHn4xPbFHzn8ND6UqKU8muFUDSqUV5mzhtu36MsVpWvaHKy6jLl9u0z9v2cZRJm0B3gdm9FWQPk+F9e4+cxFm5rSH+GvfcsftDhIlK7yAQCBwX0k4ZqeUa+lscpUXmrIBf8SsPuzKEKzAuPopwKVscQ6hlShUax46SID4OZFoiY2llS9qW5qztLp2OzWQ66TFTwbWvjtHnNFsMeDpYuJ3jpkjf7q1i8I9JtGotju4HmDvmiazXpLq48tcsc1Z6MeYtogECVKmWYKouzEAAbyTYCSiZiI3l2PIeTRh6CUhtUdo8WOtj4y9SvLGzzGfLOXJN5bACZNJwCAQCAQKxnnkrSX16DWgs/NOPd8DylbUY945odnhOq5Leit2nt8f7UqU3oZkQQYEEzt1lZ8m4IUl+sfaP5DlL+LHyR73k9dq51F+nsx2/LMmxSUz0t0C2T2I+hVpMehJT/3EmBxMtukoSw9MM6qdjMqnoSAfjA+nFQKAoFgBYDgBqAmKTgEAgVT0hZq/LqOlTHz9IE0/rj6VInnbUdx5EyRwtgQSCCCCQQRYgjUQQdhB3SUOgehenfFV24UNH8VRSP3DIkdekJYmU8nrWQo236Lb1PHpymN6xaNm7BnthvzR8/eoFeiUYowsVJB6iU5jadnpaXi9YtHaUJDIQLp6PMiaTfKnHZW4p33tsZu7Z1vwljDT9UuXxLUbR6Kvj3dCllxRAIBAwMZlL1dWnS0b+s1aWkotw7J23sf564GfAIEX2Wg32c9zjyQcO91Hzbnsngd6H8uXQyhlx8k9Oz1Wg1f+optb2o7+/wB/5aeaXQZ2RqWlVH1QW8NnmRNuGN7qHE8vJp528en+fJZpeeVEDHyhg0rUno1BdKilGHJhbVzgfPmcuQK2BrGlVBtc+rqW7NRdxB48V3HlYnJDVGB23MfPijiqaUq1RaeIUBSGIUVSNjUydpO9doN9o1yBcpCQIDgEDivpayYtHGiogsK9PTI/3FJVj3jRPUmTCFq9DuSTTw9TEMLGuwC/8dO4B72Z+oAiR0CQkQKfnjh9Gqrj6a6+q6r+BHhK2aOu7t8Myb45r5T92hml0m1zdyM2KqimNSjXUb3Vv8Taw/kZnSnNOytqdRGGnN4+DrmGoLTRUQWVQAANwEuxG3R5y1ptM2nvL1ksRAIBA0OWv83hObONri9lLWIC2IuAdo2DdsDfQETAQgeWMwqVUKOLqf6uOci1YtG0tmLLbFeL1nrDnWWckvh30W1qfZbcR+R5Tn5Mc0l6zSaumopvHfxj/PB75tUy1RgASdHd1E2af2pUuM/8Vfj/ABKzDJ9T3fMfxlx5wmwFQfQ8CDCXgwI1EWMgY2PwNKuhp1qa1EO1XAI669h5wKrW9GWTibhKq8lqtb9q5kja5EzSwWEOlRoDT99yXcdGa+j3WgbyQHAIEajEC4UtyFrnxIHnAo2OzQrY/F/KMaRTpKAlOgjaTlFJPbcalJJudG/AHVeSLxRpBVCKoCqAqqBYADUABwkDLTA1D9A99h8ZIkcn1Pd8xAqefNFlFLSUjW+77Mr5/B1eFz1t8v5aHJOTKmIqClTGs6ydyjezcvjNNazadodLNmrirzWdZyLkqnhqQpp1ZjtZt5P8N0u1rFY2h53PmtlvzWZ8yaRARMAWA4Ghy0f7zh1FtbXN2INlYHUNIXudotuHSBvoEbQJQCB4Y3CJVQpUW6ny5g7jMbVi0bS2Yst8VovSdpVfDZHqYWqxDEoy2DDUdo1NwP8AXKUsmO2PrHZ2cmsx6rHETHrRPb8MosTvM0TMy0xEQktQjYxHQmItMdpRNYnvDzZySSTc3+GqdHFMzSJlzssbXmIKZtZGAQHAIBAUAgPStr4a5Fp2iU1jeYh6vVY7WJ6mc2bWnvLqRSsdoRDEbCZETKZiGuy1kmtjDSRT2VLaTtrCghfE8vhtm/HFr9E0zY8ETM958I8VnyNkmlhqehTHNmPtMeJP5bpdrWKxtDm5s98tuazPmTSICJgICBKAQNNlWogxFG5T1msUwWqBu1qPZXURq+lwMDcwCAQCAmgLRvtga/FZN3p4fwMqZNN41W8ep8LNa6EGxFjzlSYmJ2lciYmN4YuGe+lydh53/OX8HsQo6mNsnyh7TariAQCAQFAcAgeOMeyMeVvE2/OY5PZltwRvkrHvZQFzYTmRG/Z0d9o6s/C5NJ1vq5b++WsemnvZVyamO1WzRABYCwlyIiI2hTmZmd5SkoEBEwEBAlAIBA0GWq/95wyfWuehZQL8RcdAdHkCG/gEAgEAgEAgQq0lYWYXmNqRbpMMq3ms7xLT/wBh6Jco57badm3GwBAI3ahIpSKxtDPLlnJtvHaNnhVwrrtU9do8RMmt4yAQCAQCAQPWlh3b2VJ+HjJHtVyF6xdF30RdSdHb2WDWudmyY2rzRtLZiyzjtzRDcUKCr7It8fGKY617QxvktbvL1mbAQCAQFaA4BAICvA02WcS618MqlgrOdIhlAbYNEjado8bb9QbqAQIkwGIDgEAgRJgMCA4HnUoK21Qe6B4Pk6n7tuhMbDz/ALLp8W8R/CRsD+yk4t4j+EbCa5NpjcT1Jk7D3p4ZBsQeED0MCMCQEBwCArwHAIBAIESYCtA1mU8A716FRQNFD2jpENbX9HZa9te2xI6htoESYABAlAIBAiTAYEBwCAQIwGBAcAgImAoEhAIBARMBAQJQCAQETAQECUAgBgRECUAgEBGBFf684E4BAICMBLAlAIBAiYDEBwCAQImAxAcAgBgR/nAlAIH/2Q==" alt="Instructor Avatar">
                <div class="instructor-name">Nguyễn Trọng Thơ</div>
                <div class="instructor-title">CEO MET, Marketing Online Master</div>
            </div>
           
            <div class="instructor-card">
                <img class="instructor-avatar" src="" alt="Instructor Avatar">
                <div class="instructor-name">Richard Loc (Đinh Văn Lộc)</div>
                <div class="instructor-title">Nhà đào tạo Internet Marketing - Chủ tịch OINET</div>
            </div>
           
            <div class="instructor-card">
                <img class="instructor-avatar" src="" alt="Instructor Avatar">
                <div class="instructor-name">Hà Kế Tú</div>
                <div class="instructor-title">Giảng viên Guitar - Youtuber nổi tiếng cộng đồng guitar Việt Nam</div>
            </div>
           
            <div class="instructor-card">
                <img class="instructor-avatar" src="" alt="Instructor Avatar">
                <div class="instructor-name">Phạm Thành Long</div>
                <div class="instructor-title">Luật sư - Diễn giả</div>
            </div>
           
            <div class="instructor-card">
                <img class="instructor-avatar" src="" alt="Instructor Avatar">
                <div class="instructor-name">Phan Quốc Việt</div>
                <div class="instructor-title">Diễn giả - CEO Tâm Việt Group</div>
            </div>
           
            <div class="instructor-card">
                <img class="instructor-avatar" src="" alt="Instructor Avatar">
                <div class="instructor-name">Tuấn Hà</div>
                <div class="instructor-title">Diễn giả - CEO Vinatrix, Đồng sáng lập và Chủ tịch VMCC</div>
            </div>
           
            <div class="instructor-card">
                <img class="instructor-avatar" src="" alt="Instructor Avatar">
                <div class="instructor-name">Bùi Đức Tiến</div>
                <div class="instructor-title">Diễn giả - CEO VinTech, Đồng sáng lập và Chủ tịch VLC Group</div>
            </div>
           
            <div class="instructor-card">
                <img class="instructor-avatar" src="" alt="Instructor Avatar">
                <div class="instructor-name">Hồ Ngọc Cương</div>
                <div class="instructor-title">Giảng viên, Chuyên gia Facebook Marketing</div>
            </div>
            <div class="instructor-card">
                <div class="instructor-name">Hồ Ngọc Cương</div>
                <div class="instructor-title">Giảng viên, Chuyên gia Facebook Marketing</div>
            </div>
           
            <div class="instructor-card">
                <div class="instructor-name">Hán Quang Dự</div>
                <div class="instructor-title">Giám đốc đào tạo công ty GCC Việt Nam</div>
            </div>
           
            <div class="instructor-card">
                <div class="instructor-name">Nguyễn Trọng Thơ</div>
                <div class="instructor-title">CEO MET, Marketing Online Master</div>
            </div>
           
            <div class="instructor-card">
                <div class="instructor-name">Richard Loc (Đinh Văn Lộc)</div>
                <div class="instructor-title">Nhà đào tạo Internet Marketing - Chủ tịch OINET</div>
            </div>
           
            <div class="instructor-card">
                <div class="instructor-name">Hà Kế Tú</div>
                <div class="instructor-title">Giảng viên Guitar - Youtuber nổi tiếng cộng đồng guitar Việt Nam</div>
            </div>
           
            <div class="instructor-card">
                <div class="instructor-name">Phạm Thành Long</div>
                <div class="instructor-title">Luật sư - Diễn giả</div>
            </div>
           
            <div class="instructor-card">
                <div class="instructor-name">Phan Quốc Việt</div>
                <div class="instructor-title">Diễn giả - CEO Tâm Việt Group</div>
            </div>
           
            <div class="instructor-card">
                <div class="instructor-name">Tuấn Hà</div>
                <div class="instructor-title">Diễn giả - CEO Vinatrix, Đồng sáng lập và Chủ tịch VMCC</div>
            </div>
           
            <div class="instructor-card">
                <div class="instructor-name">Bùi Đức Tiến</div>
                <div class="instructor-title">Diễn giả - CEO VinTech, Đồng sáng lập và Chủ tịch VLC Group</div>
            </div>
           
            <div class="instructor-card">
                <div class="instructor-name">Lê Thẩm Dương</div>
                <div class="instructor-title">Tiến sĩ Tài chính - Chuyên gia đào tạo</div>
            </div>
           
            <div class="instructor-card">
                <div class="instructor-name">Nguyễn Hoàng Khắc Hiếu</div>
                <div class="instructor-title">Tiến sĩ Tâm lý học - Diễn giả nổi tiếng</div>
            </div>
           
            <div class="instructor-card">
                <div class="instructor-name">Trần Thị Minh Hà</div>
                <div class="instructor-title">Chuyên gia Digital Marketing - CEO AdsViet</div>
            </div>
           
            <div class="instructor-card">
                <div class="instructor-name">Vũ Ngọc Anh</div>
                <div class="instructor-title">Chuyên gia Content Marketing - Tác giả bestseller</div>
            </div>
           
            <div class="instructor-card">
                <div class="instructor-name">Đặng Trần Trung</div>
                <div class="instructor-title">Chuyên gia Khởi nghiệp - Nhà đầu tư Shark Tank</div>
            </div>
           
            <div class="instructor-card">
                <div class="instructor-name">Nguyễn Đình Công</div>
                <div class="instructor-title">Chuyên gia Bất động sản - CEO LandGroup</div>
            </div>
               
            <div class="instructor-card">
                <div class="instructor-name">Vũ Ngọc Anh</div>
                <div class="instructor-title">Chuyên gia Content Marketing - Tác giả bestseller</div>
            </div>
           
            <div class="instructor-card">
                <div class="instructor-name">Đặng Trần Trung</div>
                <div class="instructor-title">Chuyên gia Khởi nghiệp - Nhà đầu tư Shark Tank</div>
            </div>
           
            <div class="instructor-card">
                <div class="instructor-name">Nguyễn Đình Công</div>
                <div class="instructor-title">Chuyên gia Bất động sản - CEO LandGroup</div>
            </div>
            <div class="instructor-card">
                <div class="instructor-name">Vũ Ngọc Anh</div>
                <div class="instructor-title">Chuyên gia Content Marketing - Tác giả bestseller</div>
            </div>
           
            <div class="instructor-card">
                <div class="instructor-name">Đặng Trần Trung</div>
                <div class="instructor-title">Chuyên gia Khởi nghiệp - Nhà đầu tư Shark Tank</div>
            </div>
           
            <div class="instructor-card">
                <div class="instructor-name">Nguyễn Đình Công</div>
                <div class="instructor-title">Chuyên gia Bất động sản - CEO LandGroup</div>
            </div>
            <div class="instructor-card">
                <div class="instructor-name">Vũ Ngọc Anh</div>
                <div class="instructor-title">Chuyên gia Content Marketing - Tác giả bestseller</div>
            </div>
           
            <div class="instructor-card">
                <div class="instructor-name">Đặng Trần Trung</div>
                <div class="instructor-title">Chuyên gia Khởi nghiệp - Nhà đầu tư Shark Tank</div>
            </div>
           
            <div class="instructor-card">
                <div class="instructor-name">Nguyễn Đình Công</div>
                <div class="instructor-title">Chuyên gia Bất động sản - CEO LandGroup</div>
            </div>
            <div class="instructor-card">
                <div class="instructor-name">Vũ Ngọc Anh</div>
                <div class="instructor-title">Chuyên gia Content Marketing - Tác giả bestseller</div>
            </div>
            <div class="instructor-card">
                <div class="instructor-name">Vũ Ngọc Anh</div>
                <div class="instructor-title">Chuyên gia Content Marketing - Tác giả bestseller</div>
            </div>
       </div>
    </div>


    <div class="registration-banner">
        <h1 class="title">ĐĂNG KÝ ĐỂ TRỞ THÀNH GIẢNG VIÊN UNICA NGAY HÔM NAY</h1>
       
        <div class="registration-step">ĐĂNG KÝ GIẢNG VIÊN</div>
        <div class="registration-detail">Bạn cần đăng ký tài khoản Unica</div>
       
        <div class="registration-hotline">HOTLINE: 0320902819</div>
    </div>
</body>
<footer class="unica-footer">
    <div class="footer-container">
        <!-- Cột 1: Logo + Thông tin liên hệ -->
        <div class="footer-col logo-col">
            <img src="data:image/png;base64,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" alt="Unica Logo" class="footer-logo">
            <p class="footer-slogan">Học online cùng chuyên gia</p>
            <div class="contact-info">
                <p><i class="fas fa-phone"></i> Hotline 1: 9000568</p>
                <p><i class="fas fa-phone"></i> Hotline 2: 099.488.8085</p>
                <p><i class="fas fa-envelope"></i> Email: castp@unica.vn</p>
                <p><i class="fas fa-clock"></i> 08000 - 17030, thứ 2 - thứ 7</p>
            </div>
        </div>


        <!-- Cột 2: Về Unica -->
        <div class="footer-col">
            <h3 class="footer-title">Về Unica</h3>
            <ul class="footer-links">
                <li><i class="fas fa-chevron-right"></i> <a href="#">Giới thiệu về Unica</a></li>
                <li><i class="fas fa-chevron-right"></i> <a href="#">Hướng dẫn sử dụng</a></li>
                <li><i class="fas fa-chevron-right"></i> <a href="#">Kích hoạt khóa học</a></li>
                <li><i class="fas fa-chevron-right"></i> <a href="#">Đọc chia sẻ</a></li>
                <li><i class="fas fa-chevron-right"></i> <a href="#">Giấu khách đối với</a></li>
                <li><i class="fas fa-chevron-right"></i> <a href="#">Chính sách bảo mật</a></li>
               
            </ul>
        </div>


        <!-- Cột 3: Hợp tác -->
        <div class="footer-col">
            <h3 class="footer-title">Hợp tác</h3>
            <ul class="footer-links">
                <li><i class="fas fa-chevron-right"></i> <a href="#">Đăng ký giảng viên</a></li>
                <li><i class="fas fa-chevron-right"></i> <a href="#">Giải pháp e-learning</a></li>
                <li><i class="fas fa-chevron-right"></i> <a href="#">Đào tạo doanh nghiệp</a></li>
                <li><i class="fas fa-chevron-right"></i> <a href="#">Đào tạo phần mềm</a></li>
                <li><i class="fas fa-chevron-right"></i> <a href="#">Affiliate</a></li>
                <li><i class="fas fa-chevron-right"></i> <a href="#">Quay dựng video</a></li>
            </ul>
        </div>
       
        <!-- Cột 4: Mobile -->
        <div class="header-section app-section">
            <h3>Mobile</h3>
            <div class="app-download">
                <a href="#" class="app-link">
                    <img src="https://upload.wikimedia.org/wikipedia/commons/3/3c/Download_on_the_App_Store_Badge.svg" alt="Download on App Store" class="app-logo">
                </a>
                <a href="#" class="app1-link">
                    <img src="https://upload.wikimedia.org/wikipedia/commons/7/78/Google_Play_Store_badge_EN.svg" alt="Get it on Google Play" class="app-logo">
                </a>
            </div>
        </div>
    <!-- Footer bottom -->
    <div class="footer-bottom">
        <p>© Công ty cổ phần đào tạo trực tuyến Unica – 247 Cầu Giấy, P. Gòn Vọng, Q. Cầu Giấy, Tại Việt,</p>
        <p>Sự điện; Ông Nguyễn Tuyng Trời | Mã số thuế; (trongbiorth có quá trị lời existent' means put his way)</p>
    </div>
</footer>
<!-- Include Font Awesome for icons -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</html>

