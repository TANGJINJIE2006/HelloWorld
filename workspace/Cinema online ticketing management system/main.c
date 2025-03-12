#include <stdio.h>
#include "menu.h"
int main() {
	loadMoviesFromFile();
    while (1) {
        printf("\n===== 在线影院系统 =====\n");
        printf("1. 管理员菜单\n");
        printf("2. 客户菜单\n");
        printf("3. 退出\n");
        printf("请输入您的选择: ");
        int choice;
        if (scanf("%d", &choice) != 1) {
            printf("输入无效！请输入数字。\n");
            while (getchar() != '\n'); // 清空输入缓冲区
            continue;
        }
        switch (choice) {
            case 1:
                adminMenu();
                break;
            case 2:
                clientMenu();
                break;
            case 3:
                printf("退出系统...\n");
                return 0;
            default:
                printf("无效选择！请重试。\n");
        }
    }
    return 0;
}
