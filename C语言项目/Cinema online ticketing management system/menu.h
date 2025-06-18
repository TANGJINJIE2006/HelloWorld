#ifndef MENU_H
#define MENU_H
void adminMenu();// 管理员菜单
void clientMenu();// 客户端菜单
void loadMoviesFromFile();//在主菜单调用读取文件
// 定义电影信息结构体
typedef struct Movie{
    int id;                  // 电影编号
    char name[50];           // 电影名称
    char showtime[20];       // 放映时间
    float price;             // 票价
    int hall;                // 影厅编号
    int tickets;             // 可用票数
} Movie;
#endif // MENU_H
