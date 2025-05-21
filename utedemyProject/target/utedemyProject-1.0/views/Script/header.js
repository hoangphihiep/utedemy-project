document.addEventListener('DOMContentLoaded', function () {
  // Select necessary DOM elements
  const categoryBtn = document.querySelector('.category-btn');
  const menuContainer = document.querySelector('.menu-container');
  const menuItems = document.querySelectorAll('.primary-menu > .menu-item');
  const avatarDropdown = document.getElementById('avatarDropdown');
  const userDropdown = document.getElementById('userDropdown');
  const notificationBell = document.getElementById('notificationBell');
  const notificationDropdown = document.getElementById('notificationDropdown');
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
      
      // Close notification dropdown if open
      if (notificationDropdown && notificationDropdown.classList.contains('show')) {
        notificationDropdown.classList.remove('show');
      }
    });
  }

	// 5. Toggle notification dropdown
  if (notificationBell && notificationDropdown) {
    notificationBell.addEventListener('click', function (e) {
      e.stopPropagation();
      notificationDropdown.classList.toggle('show');

      // Close user dropdown if open
      if (userDropdown && userDropdown.classList.contains('show')) {
        userDropdown.classList.remove('show');
      }
    });
  }

  // 6. Handle notification item clicks to mark as read
  document.querySelectorAll('.notification-item').forEach(function(item) {
    item.addEventListener('click', function() {
      const notificationId = this.getAttribute('data-notification-id');
      const unreadDot = this.querySelector('.unread-dot');

      if (this.classList.contains('unread')) {
        // Mark notification as read via AJAX
        fetch('/utedemyProject/user/markNotificationRead?id=' + notificationId, {
          method: 'POST'
        }).then(function(response) {
          if (response.ok) {
            // Update UI to show notification as read
            item.classList.remove('unread');
            if (unreadDot) {
              unreadDot.remove();
            }

            // Update the unread count badge
            const badge = document.querySelector('.notification-badge');
            if (badge) {
              const count = parseInt(badge.textContent) - 1;
              if (count > 0) {
                badge.textContent = count;
              } else {
                badge.remove();
              }
            }
          }
        }).catch(function(error) {
          console.error('Error marking notification as read:', error);
        });
      }
    });
  });
  
  // 7. Close all menus when clicking outside
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
    
    // Close notification dropdown
    if (notificationDropdown && notificationDropdown.classList.contains('show')) {
      notificationDropdown.classList.remove('show');
    }
  });

  // 8. Prevent menu container from closing when clicked
  if (menuContainer) {
    menuContainer.addEventListener('click', function (e) {
      e.stopPropagation();
    });
  }

  // 9. Prevent user dropdown from closing when clicked
  if (userDropdown) {
    userDropdown.addEventListener('click', function (e) {
      e.stopPropagation();
    });
  }
  
  // 10. Prevent notification dropdown from closing when clicked
  if (notificationDropdown) {
    notificationDropdown.addEventListener('click', function (e) {
      e.stopPropagation();
    });
  }
});