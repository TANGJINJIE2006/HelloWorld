
//     }
//     else if (score >= 70 ) {   
//         alert("C");
//     }
//     else if (score >= 60 ) {
//         alert("及格");
//     }
//     else {
//         alert("不及格");
//     }    
// }
//switch 语句
// let score = prompt("请输入周几")
// score = Number(score)
// let x= null
// switch (score) {
//    case 1:
//       x ="周一"
//       break;
//    case 2:
//       x ="周二"
//       break;
//    case 3:
//       x ="周三"
//       break;
//    case 4:
//       x ="周四"
//       break;
//    case 5:
//       x ="周五"
//       break;
//    case 6:
//       x ="周六"
//       break;       
//    default:
//   x="周日"
//       break;
// }
// alert(x)
//三目运算符/三元运算符 语句
// let score = prompt("请输入成绩：");
// let dengji = null
// dengji = score >= 90 ? "A" :
//     score >= 80 ? "B" :
//     score >= 70 ? "C" :
//     score >= 60 ? "及格" : "不及格"
// alert(dengji)
// let score = prompt("请输入科目一成绩：");
// if (score < 0 || score > 100) {
//     alert("成绩不在范围内！");
// }
// else {let dengji = null
//     dengji = score >= 90 ? "通过" : "挂科"
//     alert(dengji)
// }
//循环
// let strAll = ""
// for (let i = 0; i < 9; i++) {
//    let str = ""
//    for (let j = 0; j < 9; j++) {
//       if(j < i) {
//          str += " "
//       }
//    }
//    strAll += str
// }
// console.log(strAll);
// document.write(strAll)
// function ad() {
//     let strAll = ""
//     for (let i = 1; i <= 9; i++) {
//         let str = ""
//         for (let j = 1; j <= 9; j++) {
//             if (j <= i) {
//                 // str += j + "x" + i + "=" + i * j + '\t'
//                 str += `${j}x${i}=${j*i}\t`
//             }
//         }
//         strAll += str + "\n"
//     }
//     console.log(strAll);
// }
// ad()
// function add(jxy,wx,...nums) {
//     console.log(`${jxy} ${wx} = ${jxy + wx}`)
// }
// add(1,2,3,4,5,6)
// add(2,3)
// add(5,6)

// const arr = [19,20,21,33]
// console.log(arr);
// console.log(arr[2]);
// console.log(arr[arr.length - 1]);
// arr[4] = 44
// console.log(arr);
// arr.push(50)
// console.log(arr);   
// console.log(arr.pop());
// console.log(arr);
// console.log(arr.unshift(1,2,3));
// console.log(arr);
// console.log(arr.shift());
// console.log(arr);

// //字符串 api 
// let str="今，晚，打，老，虎" 
// const arr = str.split(",")
// console.log(arr)
// const arr1 = arr.reverse()
// console.log(arr1)
// const msg1 = arr1.join("+") 
// console.log(`这是新的值${msg1}`)

// //数组
// const arr = [1,2,3,4,5,6,7,8,9] //定义一个数组
// arr.push(10) //在数组末尾添加一个元素10
// arr.push(11,12,13) //在数组末尾添加多个元素11、12和13
// arr.push([14,15]) //在数组末尾添加一个数组[14,15]
// arr.unshift(0) //在数组开头添加一个元素0
// arr.unshift(-1,-2,-3) //在数组开头添加多个元素-1、-2和-3
// console.log(arr);
// //arr.forEach((item,index,thisarr) => { console.log(item,index,thisarr) }) //遍历数组

// // 1. 创建数组
// const fruits = ['Apple', 'Banana', 'Orange'];
// const numbers = [1, 2, 3, 4, 5];
// const mixed = [1, 'Hello', true, {name: 'John'}];
// console.log('初始数组:', fruits, numbers, mixed); 
// // 2. 修改原数组的方法
// // push() - 末尾添加元素
// fruits.push('Mango');
// console.log('push后:', fruits); // ['Apple', 'Banana', 'Orange', 'Mango']
// // pop() - 移除最后一个元素
// const lastFruit = fruits.pop();
// console.log('pop后:', fruits, '被移除的元素:', lastFruit);
// // unshift() - 开头添加元素
// fruits.unshift('Strawberry');
// console.log('unshift后:', fruits);
// // shift() - 移除第一个元素
// const firstFruit = fruits.shift();
// console.log('shift后:', fruits, '被移除的元素:', firstFruit);
// // splice() - 添加/删除元素
// fruits.splice(1, 1, 'Peach', 'Pear'); // 从索引1开始删除1个元素，然后添加'Peach'和'Pear'
// console.log('splice后:', fruits);
// // 3. 不修改原数组的方法
// // concat() - 合并数组
// const moreFruits = ['Grape', 'Pineapple'];
// const allFruits = fruits.concat(moreFruits);
// console.log('concat后:', allFruits);
// // slice() - 提取部分数组
// const someFruits = allFruits.slice(1, 4);
// console.log('slice(1,4):', someFruits);
// // join() - 数组转字符串
// const fruitString = allFruits.join(', ');
// console.log('join后:', fruitString);
// // 4. 遍历方法
// // forEach() - 遍历数组
// console.log('forEach输出:');
// allFruits.forEach((fruit, index) => {
//   console.log(`${index}: ${fruit}`);
// });
// // map() - 创建新数组
// const upperFruits = allFruits.map(fruit => fruit.toUpperCase());
// console.log('map后:', upperFruits);
// // filter() - 过滤数组
// const longFruits = allFruits.filter(fruit => fruit.length > 5);
// console.log('filter(长度>5):', longFruits);
// // reduce() - 累加器
// const sum = numbers.reduce((total, num) => total + num, 0);
// console.log('reduce求和:', sum);
// // 5. 查找方法
// // includes() - 检查包含
// console.log('包含Banana:', allFruits.includes('Banana')); // false
// console.log('包含Peach:', allFruits.includes('Peach')); // true
// // find() - 查找元素
// const foundFruit = allFruits.find(fruit => fruit.startsWith('P'));
// console.log('find(以P开头):', foundFruit);
// // findIndex() - 查找索引
// const peachIndex = allFruits.findIndex(fruit => fruit === 'Peach');
// console.log('Peach的索引:', peachIndex);
// // 6. 其他实用方法
// // Array.isArray() - 检查是否为数组
// console.log('fruits是数组吗:', Array.isArray(fruits)); // true
// // Array.from() - 从类数组创建数组
// const str = 'hello';
// const strArray = Array.from(str);
// console.log('Array.from字符串:', strArray);
// // flat() - 扁平化数组
// const nestedArray = [1, [2, 3], [4, [5, 6]]];
// const flatArray = nestedArray.flat(2);
// console.log('flat(2)后:', flatArray);
// // sort() - 排序
// const randomNumbers = [3, 1, 4, 1, 5, 9, 2, 6];
// console.log('排序前:', randomNumbers);
// randomNumbers.sort((a, b) => a - b); // 数字升序
// console.log('排序后:', randomNumbers);

// arr = [1,2,3,4,5,6,7,8,9];
// // // for(let i=0;i<array.length;i++){
// // //     const element=array[i];
// // //     console.log(`下标${i}`);
// // //     console.log(`元素值${array[i]}`);
// // // }
// // for(const item of arr){
// //     console.log(`元素值${item}`);
// // }

// arr.forEach(function(value,i,array){
//     if(value % 2 == 0){
//         arr[i] = `${value}是偶数`
//     } else {
//         arr[i] = `${value}是奇数`
//     }
// });
// console.log(arr);

// arr = [1, 2, 3, 4, 5, 6, 7, 8, 9];
// const arr1 = arr.map(item => {
//     return `乘以2了${item * 2}`
// })
// const arr2 = arr.filter(item => {
//     return item % 2 == 0
// })
// const arr4 = [2, 4, 6, 8, 10]
// const flag = arr4.every(item => item % 2 == 0)
// console.log(flag);
// const flag1 = arr4.some(item => item == 3)
// console.log(flag1);
// const sum = arr.reduce((pre, item) => {
//     return pre.concat(item * 2)
// }, [])
// console.log(arr1);
// console.log(arr2);
// console.log(arr);
// console.log(sum);

arr = [1,2,3,4,5,6,7,8,9]
arr.push(10)
arr.pop()
arr.unshift()
arr.join("")
for (let i =  0; i < arr.length; i++) {
    const element = array[i];
}
for (const item of arr) {
    console.log(item);
}
arr.forEach (item => console.log(item))
//arr.map(item => item * 2)
const arr1 = arr.map(item =>{return item * 2})
arr.filter(item => item % 2 == 0)

