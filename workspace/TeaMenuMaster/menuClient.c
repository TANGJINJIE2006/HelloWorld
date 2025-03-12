#include<stdio.h>
#include"menu.h"
#include"menuPrint.h"
void menu_client(){
	int choice;
	do{
		//打印标题
		printHeading("欢迎使用茶饮点餐系统");
		//打印子标题
		printSubheading("请选择操作：");
		//打印选项
		printOption(1,"查看菜单");
		printOption(2,"点餐");
		printOption(3,"查看订单");
		printOption(4,"取消订单");
		printOption(5,"保存订单");
		printOption(6,"加载菜单");
		printOption(0,"退    出");
		//打印分割线
		printSeparator();
		printf("请输入你的选择：");
		scanf("%d",&choice);
		switch(choice){
			case 1:
				printf("查看菜单\n");
				break;
			case 2:
				printf("点餐\n");
				break;
			case 3:
				printf("查看订单\n");
				break;
			case 4:
				printf("取消订单\n");
				break;
			case 5:
				printf("保存订单\n");
				break;
			case 6:	
				printf("加载菜单\n");
				break;
			case 0:
				printf("退    出\n");
				return -1;
		}
		system("pause");
		system("cls");
	}while(choice!=0);
}
