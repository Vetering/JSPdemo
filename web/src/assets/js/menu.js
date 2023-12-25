function toggleSubMenu(menuItem, submenuId) {
    const submenu = document.getElementById(submenuId);
    const arrow = menuItem.querySelector('.arrow');

    if (submenu.style.display === 'block') {
        submenu.style.display = 'none';
        arrow.classList.remove('rotate-left');
    } else {
        submenu.style.display = 'block';
        arrow.classList.add('rotate-left');
    }
}