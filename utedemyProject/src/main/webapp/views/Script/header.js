document.addEventListener('DOMContentLoaded', function () {
  // Select necessary DOM elements
  const categoryBtn = document.querySelector('.category-btn');
  const menuContainer = document.querySelector('.menu-container');
  const menuItems = document.querySelectorAll('.primary-menu > .menu-item');
  const avatarDropdown = document.getElementById('avatarDropdown');
  const userDropdown = document.getElementById('userDropdown');

  // 1. Toggle category menu on button click
  if (categoryBtn && menuContainer) {
    categoryBtn.addEventListener('click', function (e) {
      e.stopPropagation();
      menuContainer.classList.toggle('show');
    });
  }

  // 2. Handle menu item clicks for submenus
  menuItems.forEach(function (item) {
    item.addEventListener('click', function (e) {
      e.stopPropagation(); // Prevent closing the menu

      const submenu = this.querySelector('.submenu');
      if (submenu) {
        // Close all other submenus
        document.querySelectorAll('.submenu.show').forEach(function (menu) {
          if (menu !== submenu) {
            menu.classList.remove('show');
          }
        });

        // Toggle current submenu
        submenu.classList.toggle('show');
      }
    });
  });

  // 3. Prevent submenu items from closing the submenu
  document.querySelectorAll('.submenu .menu-item').forEach(function (item) {
    item.addEventListener('click', function (e) {
      e.stopPropagation(); // Prevent closing the submenu
      // Add custom behavior for submenu item clicks if needed
    });
  });

  // 4. Toggle avatar dropdown
  if (avatarDropdown && userDropdown) {
    avatarDropdown.addEventListener('click', function (e) {
      e.stopPropagation();
      userDropdown.classList.toggle('show');
    });
  }

  // 5. Close all menus when clicking outside
  document.addEventListener('click', function () {
    // Close category menu
    if (menuContainer && menuContainer.classList.contains('show')) {
      menuContainer.classList.remove('show');
    }

    // Close all submenus
    document.querySelectorAll('.submenu.show').forEach(function (submenu) {
      submenu.classList.remove('show');
    });

    // Close avatar dropdown
    if (userDropdown && userDropdown.classList.contains('show')) {
      userDropdown.classList.remove('show');
    }
  });

  // 6. Prevent menu container from closing when clicked
  if (menuContainer) {
    menuContainer.addEventListener('click', function (e) {
      e.stopPropagation();
    });
  }

  // 7. Prevent user dropdown from closing when clicked
  if (userDropdown) {
    userDropdown.addEventListener('click', function (e) {
      e.stopPropagation();
    });
  }
});