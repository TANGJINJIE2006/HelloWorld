const runningMan = document.querySelector('.running-man');
const startBtn = document.getElementById('start-btn');
const pauseBtn = document.getElementById('pause-btn');

startBtn.addEventListener('click', () => {
    runningMan.style.animationPlayState = 'running'; // 启动动画
});

pauseBtn.addEventListener('click', () => {
    runningMan.style.animationPlayState = 'paused'; // 暂停动画
});