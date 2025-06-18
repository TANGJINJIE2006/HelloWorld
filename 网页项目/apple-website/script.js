// 导航栏滚动效果
const navbar = document.querySelector('.navbar');

window.addEventListener('scroll', () => {
  if (window.scrollY > 50) {
    navbar.style.backgroundColor = 'rgba(0, 0, 0, 0.9)';
  } else {
    navbar.style.backgroundColor = 'rgba(0, 0, 0, 0.8)';
  }
});

// 背景图片切换功能
const images = [
  'apple_flies/1.png',
  'apple_flies/2.png',
  'apple_flies/3.png',
  'apple_flies/4.png'
];

let currentIndex = 0;
const bgButtons = document.querySelectorAll('.bg-btn');
const heroImage = document.querySelector('.hero-image');

// 初始化背景按钮状态
bgButtons[currentIndex].classList.add('active');

// 背景按钮点击事件
bgButtons.forEach((button, index) => {
  button.addEventListener('click', () => {
    if (index === currentIndex) return;
    
    // 判断切换方向
    const direction = index > currentIndex ? 'right' : 'left';
    
    // 添加切换动画
    heroImage.classList.add(direction === 'right' ? 'slide-right' : 'slide-left');
    
    setTimeout(() => {
      // 更新背景图片
      heroImage.style.backgroundImage = `url(${images[index]})`;
      heroImage.classList.remove('slide-left', 'slide-right');
      
      // 更新按钮状态
      bgButtons[currentIndex].classList.remove('active');
      currentIndex = index;
      bgButtons[currentIndex].classList.add('active');
    }, 500);
  });
});

function changeBackground(direction = 'right') {
  // 添加切换动画
  heroImage.classList.add(direction === 'right' ? 'slide-right' : 'slide-left');
  
  setTimeout(() => {
    // 计算新索引
    let newIndex;
    if (direction === 'right') {
      newIndex = (currentIndex + 1) % images.length;
    } else {
      newIndex = (currentIndex - 1 + images.length) % images.length;
    }
    
    // 更新背景图片
    heroImage.style.backgroundImage = `url(${images[newIndex]})`;
    heroImage.classList.remove('slide-left', 'slide-right');
    
    // 更新按钮状态
    bgButtons[currentIndex].classList.remove('active');
    currentIndex = newIndex;
    bgButtons[currentIndex].classList.add('active');
  }, 500);
}

// 启动自动轮播
let autoSlide = setInterval(() => changeBackground('right'), 5000);

// 鼠标悬停时暂停轮播
heroImage.addEventListener('mouseenter', () => {
  clearInterval(autoSlide);
});

// 鼠标离开时恢复轮播
heroImage.addEventListener('mouseleave', () => {
  autoSlide = setInterval(() => changeBackground('right'), 5000);
});

// 左右切换按钮
const prevBtn = document.createElement('div');
prevBtn.className = 'bg-btn prev-btn';
prevBtn.innerHTML = '<';
prevBtn.addEventListener('click', () => changeBackground('left'));

const nextBtn = document.createElement('div');
nextBtn.className = 'bg-btn next-btn';
nextBtn.innerHTML = '>';
nextBtn.addEventListener('click', () => changeBackground('right'));

document.querySelector('.bg-navigation').prepend(prevBtn);
document.querySelector('.bg-navigation').appendChild(nextBtn);

// 响应式菜单切换
const menuToggle = document.createElement('div');
menuToggle.classList.add('menu-toggle');
menuToggle.innerHTML = '<i class="iconfont icon-menu"></i>';
document.querySelector('.nav-content').appendChild(menuToggle);

menuToggle.addEventListener('click', () => {
  const navLinks = document.querySelector('.nav-links');
  navLinks.classList.toggle('active');
});

// 平滑滚动
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
  anchor.addEventListener('click', function (e) {
    e.preventDefault();
    document.querySelector(this.getAttribute('href')).scrollIntoView({
      behavior: 'smooth'
    });
  });
});
