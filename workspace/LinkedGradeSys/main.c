#include <stdio.h>
#include "util.h"
#include"service.h"
int main(int argc, char** argv)
{
	//初始化测试数据
	//init_test_data();
	//调用登录函数
	// 检查登录函数的返回值
	// 如果登录函数返回 false（即登录失败）
	if(!login()){
		// 输出再见消息
		printf("再见!\n");
		// 结束当前函数或程序的执行
		return;
	}
	//调用读取文件函数
	read_file();
	//定义初始化选项
 	int choose = -1;
 	while(1){// 调用打印菜单函数
 		menu();// 用户输入选择菜单项
 		scanf("%d",&choose);
 		switch(choose){
 			case 1 :
 				printf("录入成绩\n");
 				input_score();// 调用录入成绩函数
				break;
			case 2 :
				printf("查询成绩\n");
				search_score();//调用查找成绩函数
				break;
			case 3 :
				printf("修改成绩\n");
				modify_score();//调用修改成绩函数
				break;
			case 4 :
				printf("删除成绩\n");
				delete_score();//调用删除成绩函数
				break;
			case 5 :
				printf("打印成绩\n");
				student_list();// 调用打印成绩函数
				break;
			case 6 :
				printf("保存成绩\n");
				save_file();//调用保存文件函数
				break;
			case 7:
				printf("读取成绩\n");
				read_file();//调用读取文件函数
			case 0:
				printf("谢谢使用，再见！\n");
				return 0;
			default:
				break;
		}
	}
}