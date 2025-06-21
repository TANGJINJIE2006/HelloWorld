class BannerSlider {
  constructor() {
    this.banner = document.querySelector('.banner');
    this.slides = [];
    this.currentIndex = 0;
    this.interval = null;
    this.autoPlayDelay = 2000;

    this.init();
  }

  init() {
    // 创建轮播图幻灯片
    this.createSlides();
    // 创建指示器
    this.createIndicators();
    // 开始自动播放
    this.startAutoPlay();
    // 添加鼠标悬停暂停
    this.addHoverEffect();
  }

  createSlides() {
    const images = [
      'images/L1.jpg',
      'images/L2.jpg',
      'images/L3.jpg',
      'images/L4.jpg',
      'images/L5.png',
      'images/L6.png'
    ];

    const texts = [
      { title: '山水画廊', desc: '诗意栖居' },
      { title: '红色记忆', desc: '革命圣地' }, 
      { title: '云海仙境', desc: '天然氧吧' },
      { title: '梯田如画', desc: '农耕文明' },
      { title: '古镇悠悠', desc: '岁月留痕' },
      { title: '美酒飘香', desc: '醉美古蔺' }
    ];

    images.forEach((img, index) => {
      const slide = document.createElement('div');
      slide.className = 'slide';
      slide.innerHTML = `
        <img src="${img}" alt="古蔺县风景 ${index + 1}">
        <div class="banner-text">
          <h2>${texts[index].title}</h2>
          <p>${texts[index].desc}</p>
        </div>
      `;
      this.banner.appendChild(slide);
      this.slides.push(slide); 
    });

    // 激活第一张幻灯片
    this.slides[0].classList.add('active');
  }

  startAutoPlay() {
    this.interval = setInterval(() => this.nextSlide(), this.autoPlayDelay);
  }

  resetAutoPlay() {
    clearInterval(this.interval);
    this.startAutoPlay();
  }

  createIndicators() {
    const indicatorsContainer = document.createElement('div');
    indicatorsContainer.className = 'banner-indicators';
    
    this.slides.forEach((_, index) => {
      const indicator = document.createElement('div');
      indicator.className = 'banner-indicator';
      if (index === 0) indicator.classList.add('active');
      
      indicator.addEventListener('click', () => {
        this.goToSlide(index);
      });
      
      indicatorsContainer.appendChild(indicator);
    });
    
    this.banner.appendChild(indicatorsContainer);
    this.indicators = document.querySelectorAll('.banner-indicator');
  }

  goToSlide(index) {
    this.slides[this.currentIndex].classList.remove('active');
    this.indicators[this.currentIndex].classList.remove('active');
    
    this.currentIndex = index;
    
    this.slides[this.currentIndex].classList.add('active');
    this.indicators[this.currentIndex].classList.add('active');
    
    this.resetAutoPlay();
  }

  nextSlide() {
    this.goToSlide((this.currentIndex + 1) % this.slides.length);
  }

  addHoverEffect() {
    this.banner.addEventListener('mouseenter', () => {
      clearInterval(this.interval);
    });

    this.banner.addEventListener('mouseleave', () => {
      this.startAutoPlay();
    });

    // 添加箭头点击事件
    document.querySelector('.prev').addEventListener('click', () => {
      this.goToSlide((this.currentIndex - 1 + this.slides.length) % this.slides.length);
    });

    document.querySelector('.next').addEventListener('click', () => {
      this.nextSlide();
    });
  }
}

// 页面加载完成后初始化
document.addEventListener('DOMContentLoaded', () => {
  new BannerSlider();
});
