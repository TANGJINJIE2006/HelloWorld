#include <stdio.h>
#include <string.h>
#include "menu.h"
// 全局变量模拟电影信息和订票记录
#define MAX_MOVIES 100
#define MAX_TICKETS 100
#define ROWS 10
#define COLS 10
typedef struct {//定义坐位结构体
    int movieId;
    char movieName[50];
    int ticketCount;
    float totalPrice;
    int seats[ROWS][COLS];
} TicketRecord;
Movie movies[MAX_MOVIES];
TicketRecord tickets[MAX_TICKETS];
int movieCount = 0;
int ticketCount = 0;
void displayMovies();
void bookTicket();
void refundTicket();
void viewTickets();
// 打印座位图
void printSeat(int movieId);
// 客户菜单
void clientMenu() {
    while (1) {
        printf("\n===== 客户菜单 =====\n");
        printf("1. 查看电影\n");
        printf("2. 订票\n");
        printf("3. 退票\n");
        printf("4. 查看订票记录\n");
        printf("5. 返回主菜单\n");
        printf("请输入您的选择: ");
        int choice;
        if (scanf("%d", &choice) != 1) {
            printf("输入无效！请输入数字。\n");
            while (getchar() != '\n'); // 清空输入缓冲区
            continue;
        }
        switch (choice) {
            case 1:
                displayMovies();
                break;
            case 2:
                bookTicket();
                break;
            case 3:
                refundTicket();
                break;
            case 4:
                viewTickets();
                break;
            case 5:
                printf("返回主菜单...\n");
                return;
            default:
                printf("无效选择！请重试。\n");
        }
    }
}
// 显示所有电影
void displayMovies() {
    if (movieCount == 0) {
        printf("\n当前没有可用电影！\n");
        return;
    }
    printf("\n===== 当前可用电影 =====\n");
    for (int i = 0; i < movieCount; i++) {
        printf("编号:%d \t,名称:《%s》\t,时间:%s \t,票价:%.2f \t,影厅:%d \t,可用票数:%d \t \n",
               movies[i].id, movies[i].name, movies[i].showtime, movies[i].price, movies[i].hall, movies[i].tickets);
    }
}
// 订购电影票
void bookTicket() {
    int movieId, quantity;
    printf("\n请输入电影编号: ");
    scanf("%d", &movieId);
    printf("请输入订票数量: ");
    scanf("%d", &quantity);
    for (int i = 0; i < movieCount; i++) {
        if (movies[i].id == movieId) {
            if (movies[i].tickets >= quantity) {
            	//先进行作为选择
            	  printSeat(movieId);
                int row, col;
                int selectedSeats = 0;
                while (selectedSeats < quantity) {
                    printf("请输入要选择的座位行号（从0开始）: ");
                    scanf("%d", &row);
                    printf("请输入要选择的座位列号（从0开始）: ");
                    scanf("%d", &col);
                    if (row >= 0 && row < ROWS && col >= 0 && col < COLS && tickets[i].seats[row][col] == 0) {
                        tickets[i].seats[row][col] = 1;  // 标记座位已选
                        selectedSeats++;
                    } else {
                        printf("输入的座位无效，请重新选择！\n");
                    }
                }
                movies[i].tickets -= quantity;
                tickets[ticketCount].movieId = movieId;
                snprintf(tickets[ticketCount].movieName, sizeof(tickets[ticketCount].movieName), "%s", movies[i].name);
                tickets[ticketCount].ticketCount = quantity;
                tickets[ticketCount].totalPrice = quantity * movies[i].price;
                ticketCount++;
                printf("\n订票成功！您订购了 %d 张票，总金额为 %.2f 元。\n", quantity, quantity * movies[i].price);
                return;
            } else {
                printf("\n座位不足，订票失败！\n");
                return;
            }
        }
    }
    printf("\n未找到电影编号，请重试！\n");
}
// 退票
void refundTicket() {
    int movieId, quantity;
    printf("\n请输入退票的电影编号: ");
    scanf("%d", &movieId);
    printf("请输入退票数量: ");
    scanf("%d", &quantity);
    for (int i = 0; i < ticketCount; i++) {
        if (tickets[i].movieId == movieId) {
            if (tickets[i].ticketCount >= quantity) {
                tickets[i].ticketCount -= quantity;
                for (int j = 0; j < movieCount; j++) {
                    if (movies[j].id == movieId) {
                        movies[j].tickets += quantity;
                        break;
                    }
                }
                printf("\n退票成功！退还 %d 张票。\n", quantity);
                return;
            } else {
                printf("\n退票失败，退票数量超出订票数量！\n");
                return;
            }
        }
    }
    printf("\n未找到相关的订票记录！\n");
}
// 查看订票记录
void viewTickets() {
    if (ticketCount == 0) {
        printf("\n当前没有订票记录！\n");
        return;
    }
    printf("\n===== 订票记录 =====\n");
    for (int i = 0; i < ticketCount; i++) {
        printf("电影编号: %d, 名称: %s, 订票数量: %d, 总金额: %.2f\n",
               tickets[i].movieId, tickets[i].movieName, tickets[i].ticketCount, tickets[i].totalPrice);
    }
}
// 查看坐位
void printSeat(int movieId) {
    for (int i = 0; i < movieCount; i++) {
        if (movies[i].id == movieId) {
            printf("当前电影《%s》的座位图如下（0表示空闲，1表示已售出）：\n", movies[i].name);
            for (int row = 0; row < ROWS; row++) {
                for (int col = 0; col < COLS; col++) {
                    printf("%d ",tickets[i].seats[row][col]);
                }
                printf("\n");
            }
            break;
        }
    }
}