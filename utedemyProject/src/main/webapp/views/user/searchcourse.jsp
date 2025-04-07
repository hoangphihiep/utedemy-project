<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
  <head>
    <meta charset="utf-8" />
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <title>Search Results</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
      rel="stylesheet"
    />
	<link
      href="webapp/views/Css/searchCourse.css"
      rel="stylesheet"
    />
    
    
  </head>
  <body class="bg-gray-100">
    <header class="bg-white shadow">
      <div
        class="container mx-auto px-4 py-4 flex items-center justify-between"
      >
        <div class="flex items-center">
          <img
            alt="Unica logo"
            class="h-10"
            height="40"
            src=""
            width="100"
          />
          <span class="ml-2 text-lg font-semibold"> DANH MỤC </span>
        </div>
        <div class="flex items-center">
          <input
            class="border rounded-full px-4 py-2 w-64"
            placeholder="it"
            type="text"
          />
          <button class="ml-2 px-4 py-2 bg-blue-500 text-white rounded-full">
            Tìm kiếm
          </button>
        </div>
        <div class="flex items-center space-x-4">
          <a class="text-gray-700" href="#"> Doanh nghiệp </a>
          <a class="text-gray-700" href="#"> Hội viên </a>
          <button class="px-4 py-2 bg-blue-500 text-white rounded-full">
            Vào học
          </button>
          <i class="fas fa-heart text-gray-700"> </i>
          <i class="fas fa-shopping-cart text-gray-700"> </i>
          <i class="fas fa-user-circle text-gray-700"> </i>
        </div>
      </div>
    </header>
    <main class="container mx-auto px-4 py-8">
      <h1 class="text-2xl font-semibold mb-4">104 kết quả cho "it"</h1>
      <div class="flex">
        <aside class="w-1/4">
          <div class="mb-8">
            <h2 class="text-lg font-semibold mb-2">Đánh giá</h2>
            <ul>
              <li class="mb-2">
                <input
                  class="mr-2"
                  id="rating-4.5"
                  name="rating"
                  type="radio"
                />
                <label class="flex items-center" for="rating-4.5">
                  <span class="text-orange-500">
                    <i class="fas fa-star"> </i>
                    <i class="fas fa-star"> </i>
                    <i class="fas fa-star"> </i>
                    <i class="fas fa-star"> </i>
                    <i class="fas fa-star-half-alt"> </i>
                  </span>
                  <span class="ml-2"> Từ 4.5 trở lên </span>
                </label>
              </li>
              <li class="mb-2">
                <input
                  class="mr-2"
                  id="rating-4.0"
                  name="rating"
                  type="radio"
                />
                <label class="flex items-center" for="rating-4.0">
                  <span class="text-orange-500">
                    <i class="fas fa-star"> </i>
                    <i class="fas fa-star"> </i>
                    <i class="fas fa-star"> </i>
                    <i class="fas fa-star"> </i>
                  </span>
                  <span class="ml-2"> Từ 4.0 trở lên </span>
                </label>
              </li>
              <li class="mb-2">
                <input
                  class="mr-2"
                  id="rating-3.5"
                  name="rating"
                  type="radio"
                />
                <label class="flex items-center" for="rating-3.5">
                  <span class="text-orange-500">
                    <i class="fas fa-star"> </i>
                    <i class="fas fa-star"> </i>
                    <i class="fas fa-star"> </i>
                    <i class="fas fa-star-half-alt"> </i>
                  </span>
                  <span class="ml-2"> Từ 3.5 trở lên </span>
                </label>
              </li>
              <li class="mb-2">
                <input
                  class="mr-2"
                  id="rating-3.0"
                  name="rating"
                  type="radio"
                />
                <label class="flex items-center" for="rating-3.0">
                  <span class="text-orange-500">
                    <i class="fas fa-star"> </i>
                    <i class="fas fa-star"> </i>
                    <i class="fas fa-star"> </i>
                  </span>
                  <span class="ml-2"> Từ 3.0 trở lên </span>
                </label>
              </li>
            </ul>
          </div>
          <div>
            <h2 class="text-lg font-semibold mb-2">Thời lượng video</h2>
            <ul>
              <li class="mb-2">
                <input class="mr-2" id="duration-0-1" type="checkbox" />
                <label for="duration-0-1"> 0 đến 1 giờ (1) </label>
              </li>
              <li class="mb-2">
                <input class="mr-2" id="duration-1-3" type="checkbox" />
                <label for="duration-1-3"> 1 đến 3 giờ (26) </label>
              </li>
              <li class="mb-2">
                <input class="mr-2" id="duration-3-6" type="checkbox" />
                <label for="duration-3-6"> 3 đến 6 giờ (38) </label>
              </li>
              <li class="mb-2">
                <input class="mr-2" id="duration-6-17" type="checkbox" />
                <label for="duration-6-17"> 6 đến 17 giờ (37) </label>
              </li>
            </ul>
          </div>
        </aside>
        <section class="w-3/4">
          <div class="flex justify-between items-center mb-4">
            <button class="px-4 py-2 bg-gray-200 rounded">Bộ lọc</button>

            <div class="relative">
              <button
                class="px-4 py-2 bg-gray-200 rounded flex items-center"
                onclick="toggleDropdown()"
              >
                Sắp xếp theo
                <i class="fas fa-chevron-down ml-2"></i>
              </button>
              <!-- Dropdown menu -->
              <div
                id="dropdown-menu"
                class="absolute right-0 mt-2 w-48 bg-white shadow-lg rounded hidden"
              >
                <a
                  class="block px-4 py-2 text-gray-700 hover:bg-gray-100"
                  href="#"
                >
                  Học nhiều nhất
                </a>
                <a
                  class="block px-4 py-2 text-gray-700 hover:bg-gray-100"
                  href="#"
                >
                  Mới nhất
                </a>
                <a
                  class="block px-4 py-2 text-gray-700 hover:bg-gray-100"
                  href="#"
                >
                  Giá thấp nhất
                </a>
                <a
                  class="block px-4 py-2 text-gray-700 hover:bg-gray-100"
                  href="#"
                >
                  Giá cao nhất
                </a>
              </div>
            </div>
          </div>

          <div class="space-y-4">
            <div class="flex items-center bg-white p-4 rounded shadow">
              <img
                alt="Guitar course image"
                class="w-32 h-20 object-cover rounded"
                height="100"
                src="https://storage.googleapis.com/a1aa/image/kW-kJgDosonbyM9f56LwGiIpVDEVd5iAvw1cQoF_gxc.jpg"
                width="150"
              />
              <div class="ml-4 flex-1">
                <h3 class="text-lg font-semibold">
                  Kỹ năng học guitar hiệu quả cho người mới bắt đầu
                </h3>
                <p class="text-gray-600">Lê Việt Dũng</p>
                <div class="flex items-center">
                  <span class="text-orange-500">
                    <i class="fas fa-star"> </i>
                    <i class="fas fa-star"> </i>
                    <i class="fas fa-star"> </i>
                    <i class="fas fa-star"> </i>
                    <i class="fas fa-star-half-alt"> </i>
                  </span>
                  <span class="ml-2 text-gray-600"> 4.3 (39) </span>
                </div>
                <p class="text-gray-600">Thời lượng: 2.9 giờ, 33 bài giảng</p>
              </div>
              <div class="text-right">
                <p class="text-lg font-semibold text-red-500">299.000đ</p>
                <p class="text-gray-500 line-through">800.000đ</p>
              </div>
            </div>
            <div class="flex items-center bg-white p-4 rounded shadow">
              <img
                alt="Guitar course image"
                class="w-32 h-20 object-cover rounded"
                height="100"
                src="https://storage.googleapis.com/a1aa/image/kW-kJgDosonbyM9f56LwGiIpVDEVd5iAvw1cQoF_gxc.jpg"
                width="150"
              />
              <div class="ml-4 flex-1">
                <h3 class="text-lg font-semibold">
                  Học Độc tấu Guitar trong 36 ngày
                </h3>
                <p class="text-gray-600">Trịnh Minh Cường</p>
                <div class="flex items-center">
                  <span class="text-orange-500">
                    <i class="fas fa-star"> </i>
                    <i class="fas fa-star"> </i>
                    <i class="fas fa-star"> </i>
                    <i class="fas fa-star"> </i>
                    <i class="fas fa-star-half-alt"> </i>
                  </span>
                  <span class="ml-2 text-gray-600"> 4.5 (67) </span>
                </div>
                <p class="text-gray-600">Thời lượng: 1.5 giờ, 42 bài giảng</p>
              </div>
              <div class="text-right">
                <p class="text-lg font-semibold text-red-500">199.000đ</p>
                <p class="text-gray-500 line-through">500.000đ</p>
              </div>
            </div>
            <div class="flex items-center bg-white p-4 rounded shadow">
              <img
                alt="Guitar course image"
                class="w-32 h-20 object-cover rounded"
                height="100"
                src="https://storage.googleapis.com/a1aa/image/kW-kJgDosonbyM9f56LwGiIpVDEVd5iAvw1cQoF_gxc.jpg"
                width="150"
              />
              <div class="ml-4 flex-1">
                <h3 class="text-lg font-semibold">
                  Học đệm hát Guitar cùng Haketu
                </h3>
                <p class="text-gray-600">Hà Kế Tú</p>
                <div class="flex items-center">
                  <span class="text-orange-500">
                    <i class="fas fa-star"> </i>
                    <i class="fas fa-star"> </i>
                    <i class="fas fa-star"> </i>
                    <i class="fas fa-star"> </i>
                  </span>
                  <span class="ml-2 text-gray-600"> 4.3 (213) </span>
                </div>
                <p class="text-gray-600">Thời lượng: 4.3 giờ, 53 bài giảng</p>
              </div>
              <div class="text-right">
                <p class="text-lg font-semibold text-red-500">199.000đ</p>
                <p class="text-gray-500 line-through">700.000đ</p>
              </div>
            </div>
            <div class="flex items-center bg-white p-4 rounded shadow">
              <img
                alt="Guitar course image"
                class="w-32 h-20 object-cover rounded"
                height="100"
                src="https://storage.googleapis.com/a1aa/image/kW-kJgDosonbyM9f56LwGiIpVDEVd5iAvw1cQoF_gxc.jpg"
                width="150"
              />
              <div class="ml-4 flex-1">
                <h3 class="text-lg font-semibold">
                  Fingerstyle guitar cho người mới bắt đầu
                </h3>
                <p class="text-gray-600">Hoàng Phúc</p>
                <div class="flex items-center">
                  <span class="text-orange-500">
                    <i class="fas fa-star"> </i>
                    <i class="fas fa-star"> </i>
                    <i class="fas fa-star"> </i>
                    <i class="fas fa-star"> </i>
                  </span>
                  <span class="ml-2 text-gray-600"> 4.1 (107) </span>
                </div>
                <p class="text-gray-600">Thời lượng: 4.6 giờ, 61 bài giảng</p>
              </div>
              <div class="text-right">
                <p class="text-lg font-semibold text-red-500">299.000đ</p>
                <p class="text-gray-500 line-through">800.000đ</p>
              </div>
            </div>
            <div class="flex items-center bg-white p-4 rounded shadow">
              <img
                alt="Guitar course image"
                class="w-32 h-20 object-cover rounded"
                height="100"
                src="https://storage.googleapis.com/a1aa/image/kW-kJgDosonbyM9f56LwGiIpVDEVd5iAvw1cQoF_gxc.jpg"
                width="150"
              />
              <div class="ml-4 flex-1">
                <h3 class="text-lg font-semibold">
                  Fingerstyle guitar nâng cao
                </h3>
                <p class="text-gray-600">Hoàng Phúc</p>
                <div class="flex items-center">
                  <span class="text-orange-500">
                    <i class="fas fa-star"> </i>
                    <i class="fas fa-star"> </i>
                    <i class="fas fa-star"> </i>
                    <i class="fas fa-star"> </i>
                  </span>
                  <span class="ml-2 text-gray-600"> 4.1 (107) </span>
                </div>
                <p class="text-gray-600">Thời lượng: 4.6 giờ, 61 bài giảng</p>
              </div>
              <div class="text-right">
                <p class="text-lg font-semibold text-red-500">299.000đ</p>
                <p class="text-gray-500 line-through">800.000đ</p>
              </div>
            </div>
          </div>
        </section>
      </div>
    </main>
    <script>
      // JavaScript to toggle dropdown visibility
      function toggleDropdown() {
        const dropdown = document.getElementById("dropdown-menu");
        dropdown.classList.toggle("hidden");
      }
    </script>
  </body>
</html>
