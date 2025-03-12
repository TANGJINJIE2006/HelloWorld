#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "menu.h"
#include "Service.h"
// 定义保存和读取电影数据的文件路径
#define MOVIE_FILE "D:\\movies.txt"//为了方便每次访问的管理 增加保存读取功能 
// 最大电影数量
#define MAX_MOVIES 100
extern Movie movies[MAX_MOVIES]; // 存储电影信息的数组 定义电影信息存放的位置
extern int movieCount;   // 在其他文件中只声明，在一个文件中定义
// 函数声明
void addMovie();// 声明添加电影函数
void deleteMovie();//声明删除电影函数
void updateMovieDetails();//声明更新电影函数
void displayAllMovies();//声明显示所有电影函数
// 声明读取和保存函数
void saveMoviesToFile();
void loadMoviesFromFile();
// 管理员菜单
void adminMenu() {
	if(!login())
	{
		printf("再见！\n");
		return;
	}
    while (1) {
        printf("\n===== 管理员菜单 =====\n");
        printf("1. 添加电影\n");
        printf("2. 删除电影\n");
        printf("3. 更新电影详情\n");
        printf("4. 查看所有电影\n");
   		printf("5. 保存电影到文件\n");
        printf("6. 从文件加载电影\n");
        printf("7. 返回主菜单\n");
        printf("请输入您的选择: ");
        int choice;
        if (scanf("%d", &choice) != 1) {
            printf("输入无效！请输入数字。\n");
            while (getchar() != '\n'); // 清空输入缓冲区
            continue;
        }
        switch (choice) {
            case 1:
                addMovie();//调用增加电影函数
                break;
            case 2:
                deleteMovie();//调用删除电影函数
                break;
            case 3:
                updateMovieDetails();//调用更新电影函数
                break;
            case 4:
             	displayAllMovies();//调用显示所有电影函数
                break;
            case 5:
            	saveMoviesToFile();//调用保存
            	break;
            case 6:
            	loadMoviesFromFile();//调用读取
            	break;
            case 7:
                printf("返回主菜单...\n");
                return;
            default:
                printf("无效选择！请重试。\n");
                break;
        }
    }
}
// 添加电影
void addMovie() 
{
    if (movieCount >= MAX_MOVIES) {
        printf("电影列表已满，无法添加更多电影！\n");
        return;
    }
    Movie newMovie;
    printf("请输入电影编号: ");
    scanf("%d", &newMovie.id);
    printf("请输入电影名称: ");
    scanf(" %[^\n]", newMovie.name); // 读取带空格的字符串
    printf("请输入放映时间 (例如 19:30): ");
    scanf("%s", newMovie.showtime);
    printf("请输入票价: ");
    scanf("%f", &newMovie.price);
    printf("请输入影厅编号: ");
    scanf("%d", &newMovie.hall);
    printf("请输入可用票数: ");
    scanf("%d", &newMovie.tickets);
    movies[movieCount++] = newMovie;
    printf("电影添加成功！\n");
}
//删除电影
void deleteMovie()
{
    if (movieCount == 0) {
        printf("当前没有电影可供删除！\\n");
        return;
    }
    int id;
    printf("请输入要删除的电影编号: ");
    scanf("%d", &id);
    for (int i = 0; i < movieCount; i++) {
        if (movies[i].id == id) {
            for (int j = i; j < movieCount - 1; j++) {
                movies[j] = movies[j + 1];
            }
            movieCount--;
            printf("电影删除成功！\n");
            return;
        }
    }
    printf("未找到编号为 %d 的电影！\n", id);
}
//更新电影
void updateMovieDetails() 
{
    if (movieCount == 0) {
        printf("当前没有电影可供更新！\n");
        return;
    }
    int id;
    printf("请输入要更新的电影编号: ");
    scanf("%d", &id);
    for (int i = 0; i < movieCount; i++) {
        if (movies[i].id == id) {
            printf("当前电影信息: \n");
            printf("编号: %d, 名称: %s, 时间: %s, 票价: %.2f, 影厅: %d, 可用票数: %d\n",
                   movies[i].id, movies[i].name, movies[i].showtime, movies[i].price, movies[i].hall, movies[i].tickets);
            printf("请输入新的电影名称: ");
            scanf(" %[^\n]", movies[i].name);
            printf("请输入新的放映时间: ");
            scanf("%s", movies[i].showtime);
            printf("请输入新的票价: ");
            scanf("%f", &movies[i].price);
            printf("请输入新的影厅编号: ");
            scanf("%d", &movies[i].hall);
            printf("请输入新的可用票数: ");
            scanf("%d", &movies[i].tickets);
            printf("电影详情更新成功！\n");
            return;
        }
    }
    printf("未找到编号为 %d 的电影！\n", id);
}
//显示所有电影
void displayAllMovies()
{
    if (movieCount == 0) {
        printf("当前没有电影！\n");
        return;
    }
    printf("\n===== 当前电影列表 =====\n");
    for (int i = 0; i < movieCount; i++) {
        printf("编号:%d \t,名称:《%s》\t,时间:%s \t,票价:%.2f \t,影厅:%d \t,可用票数:%d \t \n",
               movies[i].id, movies[i].name, movies[i].showtime, movies[i].price, movies[i].hall, movies[i].tickets);
    }
}
// 保存电影到文件
void saveMoviesToFile() {
    FILE *file = fopen("D:\\movies.txt", "w");
    if (file == NULL) {
        printf("无法打开文件保存电影数据！\n");
        return;
    }
    for (int i = 0; i < movieCount; i++) {
        fprintf(file, "%d|%s|%s|%.2f|%d|%d\n",
                movies[i].id,
                movies[i].name,
                movies[i].showtime,
                movies[i].price,
                movies[i].hall,
                movies[i].tickets);
    }

    fclose(file);
    printf("电影数据保存成功！\n");
}
// 从文件加载电影
void loadMoviesFromFile() {
    FILE *file = fopen("D:\\movies.txt", "r");
    if (file == NULL) {
        printf("未找到数据文件，初始化为空列表。\n");
        return;
    }
    movieCount = 0;
    while (!feof(file)) {
        Movie newMovie;
        if (fscanf(file, "%d|%49[^|]|%19[^|]|%f|%d|%d\n",
                   &newMovie.id,
                   newMovie.name,
                   newMovie.showtime,
                   &newMovie.price,
                   &newMovie.hall,
                   &newMovie.tickets) == 6) {
            movies[movieCount++] = newMovie;
        }
    }
    fclose(file);
    printf("电影数据加载成功！\n");
}