class ImageSlider {
  constructor() {
    this.slider = document.querySelector('.slider');
    this.progress = document.querySelector('.progress');
    this.prevBtn = document.querySelector('.prev');
    this.nextBtn = document.querySelector('.next');
    this.effectSelector = document.querySelector('.effect-selector');
    this.randomBtn = document.querySelector('.random-btn');
    this.currentIndex = 0;
    this.interval = null;
    this.autoPlayDelay = 5000;
    this.imageFiles = [
      '../statics/images/s1.jpg',
      '../statics/images/s2.jpg',
      '../statics/images/s3.jpg',
      '../statics/images/s4.jpg',
      '../statics/images/t1.jpg',
      '../statics/images/t2.jpg',
      '../statics/images/t3.jpg',
      '../statics/images/t4.jpg',
      '../statics/images/t5.jpg',
      '../statics/images/news1.jpg',
      '../statics/images/news2.jpg'
    ];

    this.loadImages();
    this.init();
  }

  loadImages() {
    // 随机选择4张不同的图片
    const shuffled = [...this.imageFiles].sort(() => 0.5 - Math.random());
    const selectedImages = shuffled.slice(0, 4);
    
    const effects = ['fade', 'slide', 'zoom', 'flip'];
    
    selectedImages.forEach((img, index) => {
      const slide = document.createElement('div');
      slide.className = `slide ${index === 0 ? 'active' : ''}`;
      slide.dataset.effect = effects[index];
      
      const image = document.createElement('img');
      image.src = img;
      image.alt = `图片${index + 1}`;
      
      slide.appendChild(image);
      this.slider.appendChild(slide);
    });

    this.slides = document.querySelectorAll('.slide');
  }

  init() {
    this.setupEventListeners();
    this.startAutoPlay();
    this.updateProgress();
  }

  setupEventListeners() {
    this.prevBtn.addEventListener('click', () => this.prevSlide());
    this.nextBtn.addEventListener('click', () => this.nextSlide());
    this.randomBtn.addEventListener('click', () => this.randomSlide());
    this.effectSelector.addEventListener('change', (e) => {
      if (e.target.value === 'random') {
        this.setRandomEffect();
      } else {
        this.changeEffect(e.target.value);
      }
    });
    
    // 点击进度条跳转
    document.querySelector('.progress-bar').addEventListener('click', (e) => {
      const percent = e.offsetX / e.target.offsetWidth;
      const slideIndex = Math.floor(percent * this.slides.length);
      this.goToSlide(slideIndex);
    });
  }

  startAutoPlay() {
    this.interval = setInterval(() => this.nextSlide(), this.autoPlayDelay);
  }

  resetAutoPlay() {
    clearInterval(this.interval);
    this.startAutoPlay();
  }

  updateProgress() {
    const progress = ((this.currentIndex + 1) / this.slides.length) * 100;
    this.progress.style.width = `${progress}%`;
  }

  changeSlide(index) {
    this.slides.forEach(slide => slide.classList.remove('active'));
    this.slides[index].classList.add('active');
    this.currentIndex = index;
    this.updateProgress();
    this.resetAutoPlay();
  }

  prevSlide() {
    const newIndex = (this.currentIndex - 1 + this.slides.length) % this.slides.length;
    this.changeSlide(newIndex);
  }

  nextSlide() {
    const newIndex = (this.currentIndex + 1) % this.slides.length;
    this.changeSlide(newIndex);
  }

  goToSlide(index) {
    if (index >= 0 && index < this.slides.length) {
      this.changeSlide(index);
    }
  }

  changeEffect(effect) {
    this.slides.forEach(slide => {
      slide.dataset.effect = effect;
    });
  }

  setRandomEffect() {
    const effects = ['fade', 'slide', 'zoom', 'flip', 'rotate', 'bounce', 'fade-slide'];
    this.slides.forEach(slide => {
      const randomEffect = effects[Math.floor(Math.random() * effects.length)];
      slide.dataset.effect = randomEffect;
    });
  }

  randomSlide() {
    const randomIndex = Math.floor(Math.random() * this.slides.length);
    this.changeSlide(randomIndex);
    this.setRandomEffect();
  }
}

// 初始化滑块
document.addEventListener('DOMContentLoaded', () => {
  new ImageSlider();
});
