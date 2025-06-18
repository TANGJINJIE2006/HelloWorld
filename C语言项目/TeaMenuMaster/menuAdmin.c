#include<stdio.h>
#include"menu.h"
#include"menuPrint.h"
void menu_admin(){
	int choice;
	do{
		//打印标题
		printHeading("欢迎使用茶饮点餐后台管理系统");
		//打印子标题
		printSubheading("请选择操作：");
		//打印选项
		printOption(1,"所有茶饮");
		printOption(2,"设置茶饮");
		printOption(3,"删除茶饮");
		printOption(4,"存储到文件");
		printOption(5,"加载茶饮数据");
		printOption(0,"退    出");
		//打印分割线
		printSeparator();
		printf("请输入你的选择：");
		scanf("%d",&choice);
		switch(choice){
			case 1:
				printf("所有茶饮\n");
				break;
			case 2:
				printf("设置茶饮\n");
				break;
			case 3:
				printf("删除茶饮\n");
				break;
			case 4:
				printf("存储到文件\n");
				break;
			case 5:
				printf("加载茶饮数据\n");
				break;
			case 0:
				printf("谢谢使用，再见！\n");
				return -1;
		}
		system("pause");
		system("cls");
	}while(choice!=0);
}
