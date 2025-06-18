let index = 0
const imgList = document.querySelectorAll(".banner > img");
console.log(imgList);
function renderImg() {
    for (let i = 0; i < imgList.length; i++) {
        const element = imgList[i];
        element.classList = []
        if (index == i) {
            imgList[i].classList.add("img_active")
        }
    }
}
setInterval(() => {
    index++
    if (index == imgList.length) {
        index = 0
    }
    console.log(index);
    renderImg()
}, 1000 * 3)
