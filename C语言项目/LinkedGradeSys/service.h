// 检查是否已经定义了 UTIL_H 这个宏
#ifndef UTIL_H
// 如果尚未定义，则定义 UTIL_H 宏，防止此头文件再次被包含
#define UTIL_H
// 登录系统函数
// 声明 login 函数，该函数用于验证用户凭据并返回登录是否成功的布尔值
_Bool login();
// 结束#ifndef 和#define 指令块，如果 UTIL_H 已经被定义，则跳过此头文件的包含
#endif
