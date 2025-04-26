document.addEventListener('DOMContentLoaded', function () {
  const avatarDropdown = document.getElementById('avatarDropdown');
  const userDropdown = document.getElementById('userDropdown');
  const categoryBtn = document.querySelector('.category-btn');
  const menuContainer = document.querySelector('.menu-container');

  // Avatar dropdown
  if (avatarDropdown && userDropdown) {
    avatarDropdown.addEventListener('click', function (e) {
      e.stopPropagation();
      userDropdown.style.display = userDropdown.style.display === 'block' ? 'none' : 'block';
    });
  }

  // Category dropdown
  if (categoryBtn && menuContainer) {
    categoryBtn.addEventListener('click', function (e) {
      e.stopPropagation();
      menuContainer.style.display = menuContainer.style.display === 'block' ? 'none' : 'block';
    });
  }

  // Primary menu submenus
  const menuItems = document.querySelectorAll('.primary-menu .menu-item');
  menuItems.forEach(item => {
    const menuTitle = item.querySelector('.menu-title');
    const submenu = item.querySelector('.submenu');

    if (menuTitle && submenu) {
      menuTitle.addEventListener('click', function (e) {
        e.stopPropagation();

        // Hide other submenus
        document.querySelectorAll('.primary-menu .submenu').forEach(menu => {
          if (menu !== submenu) menu.style.display = 'none';
        });

        // Toggle this submenu
        submenu.style.display = submenu.style.display === 'block' ? 'none' : 'block';
      });
    }
  });

  // Click outside to close everything
  document.addEventListener('click', function () {
    if (userDropdown) userDropdown.style.display = 'none';
    if (menuContainer) menuContainer.style.display = 'none';

    document.querySelectorAll('.primary-menu .submenu').forEach(menu => {
      menu.style.display = 'none';
    });
  });

  // Prevent closing when clicking inside dropdowns
  if (userDropdown) userDropdown.addEventListener('click', e => e.stopPropagation());
  if (menuContainer) menuContainer.addEventListener('click', e => e.stopPropagation());
});
