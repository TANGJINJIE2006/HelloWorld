#include <stdlib.h>
#include<stdio.h>
// 引入布尔_Bool 类型和宏 true 和 false
#include<stdbool.h>
// 引入字符串处理库，用于字符串比较
#include<string.h>
// 登录系统函数
// 返回值：true 登录成功，false 登录失败！
_Bool login()
{
	printf("********************\n");
	printf("*请登录成绩管理系统*\n");
	printf("********************\n");
//定义预设的用户名
const char *username="admin";
// 定义预设的密码
const char *password="abc123";
// 用户输入的用户名缓冲区
char inputUsername[10];
// 用户输入的密码缓冲区
char inputPassword[10];
// 初始化尝试次数为 0，最多 3 次
int attempts=0;
// 循环最多执行 3 次，给用户三次机会输入正确的用户名和密码
while(attempts<3){
	// 提示用户输入用户名
	printf("请输入用户名：");
	scanf("%s",&inputUsername);
	// 提示用户输入密码
	printf("请输入密码：");
	scanf("%s",&inputPassword);
	// 比较输入的用户名和密码是否与预设的匹配
	// strcmp 函数，如果两个字符串完全相同，strcmp()函数会返回 0
	if(strcmp(inputUsername,username)==0&&strcmp(inputPassword,password)==0){
		// 如果匹配成功，打印成功信息
		printf("登录成功!\n");
		// 暂停程序，等待用户按键后继续执行
	system("pause");
	system("cls");
	// 返回 true 表示登录成功	
	return true;
	}else{
		// 增加尝试次数
		attempts++;
		// 如果不匹配，提示用户重新输入
		printf("用户名或密码错误，请再次尝试，剩余%d次！\n",3-attempts);
		}
	}
// 如果尝试次数达到最大值 3 次仍未成功登录
printf("登录尝试次数超过上限。登录失败！\n");
// 返回 false 表示登录失败
 return false;
}