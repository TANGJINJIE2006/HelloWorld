#ifndef UTIL_H
#define UTIL_H
void menu();			//打印菜单
void init_test_data();	//初始化测试数据的函数
void input_score();		//录入成绩
void search_score();    //查询成绩
void modify_score();	//修改成绩
void delete_score();	//删除成绩
void student_list();	//打印成绩
void save_file();       //保存文件
void read_file();       //读取文件
// 定义一个名为 Student 的结构体类型，用于存储每个学生的具体信息
typedef struct Student{//定义结构体 , 用于存放学生的信息
	int stuNum;//学号，整型变量，用于唯一标识每个学生
	char name[10];//姓名，字符数组，最多可存储 9 个字符加上结束符'\0'，用于存储学生的姓名
	char sex;//性别，单个字符，用于存储学生的性别信息，通常'M'代表男性，'F'代表女性
	float score;//成绩，浮点型变量，用于存储学生的成绩信息，可以精确到小数点后若干位
	struct Student *pNext;
	// pNext 是指针类型，指向下一个 Student 类型的结构体，用于构建链表
	// 通过这个指针，可以链接多个 Student 结构体形成一个链式存储结构
}Student;
#endif
