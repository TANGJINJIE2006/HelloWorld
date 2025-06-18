#include<stdio.h>
#include<stdbool.h>
#include <string.h>
#include <stdlib.h>
//登陆系统函数
//返回值：true 成功；false 失败
_Bool login()
{
	printf("**************************\n");
	printf("* 请登录电影售票管理系统 *\n");
	printf("**************************\n");
	const char *username = "admin";
	//定义预设密码
	const char *password = "abc123";
	//用户输入用户名缓冲区
	char inputUsername[10];
	//密码缓冲区域
	char inputPassword[10];
	//初始化尝试次数为零，最多为三
	int attempts = 0;
	//最多执行三次循环，给用户三次机会
	while(attempts < 3)
	{
		printf("请输入用户名：");
		scanf("%s",&inputUsername);
		//提示输入密码
		printf("请输入密码：");
		scanf("%s",&inputPassword);
		if(strcmp(inputUsername,username)==0 && strcmp(inputPassword,password)==0)
		{
			printf("登录成功!\n");
			system("pause");
			system("cls");
			return true;
		}
		else
		{
			attempts++;
			printf("用户名或密码错误，请再次尝试，剩余%d次！\n",3-attempts);
		}
	}
	printf("登录尝试次数超过上限。登录失败!\n");
	return false;
}
  
  