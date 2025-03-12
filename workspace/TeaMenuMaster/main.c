#include <stdio.h>
#include <stdlib.h>
#include"menu.h"

int main() {
	int choice;
	printf("1.茶饮后台管理系统\n");
	printf("2.茶饮点餐客户端\n");
	printf("请选择系统：");
	scanf("%d",&choice);
	system("cls");
	if(choice==1){
		menu_admin();//调用管理端
	}else if (choice==2){
		menu_client();//调用客户端	
	}else{
		printf("输入错误，再见!\n");
	}	
	return 0;
}