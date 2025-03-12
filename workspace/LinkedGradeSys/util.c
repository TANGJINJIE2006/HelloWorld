#include<stdio.h>//引入头文件
#include"util.h"
#include <stdlib.h>
#include <string.h>
struct Student stu;// 全局变量 stu，用于存储学生成绩信息
Student *head=NULL;// 全局指针 head 头指针，用于指向链表第一个节点
// 打印菜单
void menu()
{
	printf("* * *欢迎使用学生成绩管理系统* * *\n");
	printf("*\t 1.学生成绩录入 \t *\n");
	printf("*\t 2.学生成绩查询 \t *\n");
	printf("*\t 3.学生成绩修改 \t *\n");
	printf("*\t 4.学生成绩删除 \t *\n");
	printf("*\t 5.学生成绩打印 \t *\n");
	printf("*\t 6.学生成绩保存 \t *\n");
	printf("*\t 7.学生成绩读取 \t *\n");
	printf("*\t 0.退        出 \t *\n");
	printf("* * * * * * * * ** * * * * * * * *\n");
	printf("请输入您的选择(0-7):");	
}
//初始化测试数据
void init_test_data()
{
	//初始化一个指针p，用于辅助遍历和更新列表
	Student *p=head;
	
//创建第一个节点
	//分配内存空间给第一个新的Student结构实例
	Student *newNode=(Student *)malloc(sizeof(Student));
	//设置学号
	newNode->stuNum=11;
	//复制姓名到结构体的name字段
	strcpy(newNode->name,"月半猫");
	//设置性别
	newNode->sex='F';
	//设置成绩
	newNode->score=69;
	//将新节点设置为链表的头节点
	head=newNode;
	//更新辅助指针p指向当前节点
	p=newNode;
//创建第二个节点
	//分配内存空间给一个新的Student结构体实测
	Student *newNode1=(Student *)malloc(sizeof(Student));
	//设置学号
	newNode1->stuNum=12;
	//复制姓名到结构体的name字段
	strcpy(newNode1->name,"张真源");
	newNode1->sex='M';
	newNode1->score=100;
	//将新节点链接到链表末尾，即p节点的pNext字段
	p->pNext=newNode1;
	//更新辅助指针p指向当前节点，此时p指向链表的最后一个节点
	p=newNode1;
//创建第三个节点
	Student *newNode2=(Student *)malloc(sizeof(Student));
	newNode2->stuNum=13;
	strcpy(newNode2->name,"严浩翔");
	newNode2->sex='M';
	newNode2->score=76;
	p->pNext=newNode2;
	p=newNode2;
//创建第四个节点
	Student *newNode3=(Student *)malloc(sizeof(Student));
	newNode3->stuNum=22;
	strcpy(newNode3->name,"马嘉祺");
	newNode3->sex='M';
	newNode3->score=92;
	p->pNext=newNode3;
	p=newNode3;
//创建第五个节点
	Student *newNode4=(Student *)malloc(sizeof(Student));
	newNode4->stuNum=28;
	strcpy(newNode4->name,"宋亚轩");
	newNode4->sex='F';
	newNode4->score=74;
	p->pNext=newNode4;
	p=newNode4;
//创建第六个节点
	Student *newNode5=(Student *)malloc(sizeof(Student));
	newNode5->stuNum=29;
	strcpy(newNode5->name,"丁程鑫");
	newNode5->sex='M';
	newNode5->score=82;
	p->pNext=newNode5;
	p=newNode5;
//创建第七个节点
	Student *newNode6=(Student *)malloc(sizeof(Student));
	newNode6->stuNum=30;
	strcpy(newNode6->name,"刘耀文");
	newNode6->sex='M';
	newNode6->score=86;
	p->pNext=newNode6;
	p=newNode6;
//创建第八个节点
	Student *newNode7=(Student *)malloc(sizeof(Student));
	newNode7->stuNum=32;
	strcpy(newNode7->name,"蔡徐坤");
	newNode7->sex='M';
	newNode7->score=76;
	p->pNext=newNode7;
	p=newNode7;
//创建第九个节点
	Student *newNode8=(Student *)malloc(sizeof(Student));
	newNode8->stuNum=42;
	strcpy(newNode8->name,"王俊杰");
	newNode8->sex='M';
	newNode8->score=94;
	p->pNext=newNode8;
	p=newNode8;
	//当前节点p的pNext字段设置为NULL，表示这是链表的最后一个节点
	p->pNext=NULL;
}
// 录入成绩
void input_score()// 声明一个指向 Student 结构体的指针 p，并将其初始化为链表的头节点
{
	Student *p=head;// 声明指针，用于记录遍历过程中的前一个节点
	Student *prev=NULL;// 在内存中分配一块空间放置新节点
	Student *newNode=(Student *)malloc(sizeof(Student));
	// 录入学生信息
	printf("请输入学生的学号：");
	scanf("%d",&newNode->stuNum);
	printf("请输入学生的姓名：");
	scanf("%s",&newNode->name);
	// 清除输入缓冲区中的换行符
	getchar();
	printf("请输入学生的性别(男:M,女:F):");
	scanf("%c",&newNode->sex);
	printf("请输入学生的成绩：");
	scanf("%f",&newNode->score);
	// 初始化新节点的下一个指针为
	newNode->pNext=NULL;
	// 判断链表的第一个节点是否为空
	if(p==NULL){
		printf("head is null\n");
		// 将新节点放入链表起始位置
		head=newNode;
	//如果p不为空，并且新节点的学号小于当前头节点的学号
	//这意味着新节点应当位于头节点之前，以维持按学号排序的顺序
	}else if(p!=NULL&&newNode->stuNum<p->stuNum){
		newNode->pNext=p;// 设置新节点的 pNext 为当前的头节点 p
		head=newNode;// 将新节点设为新的头节点，从而将它插入到链表的起始位置
	}
	else{
		//循环遍历整个链表，直到链表的最后一个
		while(p!=NULL&&newNode->stuNum>p->stuNum){
			//更新prev为当前p，因为我们要在prev和p之间插入新节点
			prev=p;
			//移动到下一个节点继续比较
			p=p->pNext;
		}
		//当循环结束时，prev指向了新节点应该插入位置的前一个节点
		//将prev的pNext指向新节点，即在prev之后插入新节点
		prev->pNext=newNode;
		//设置新节点的pNext为原来的p节点，保证链表连续性
		//这样新节点就被插入到了正确的位置，并且链表依然保持有序
		newNode->pNext=p;
	}
	printf("录入学生成绩成功\n");// 暂停程序，等待用户按键后继续执行
	system("pause");// 清除控制台屏幕上的所有输出
	system("cls");
}
//查询成绩
void search_score()
{
	//声明整形变量，用于存储用户输入的学号
	int num;
	//输出提示信息，要求用户输入要查找的学号
	printf("请输入要查找的学号：");
	//读取用户输入的学号，并存储到num变量中
	scanf("%d",&num);
	//开始从链表的头部开始遍历，temp用于遍历链表
	Student *temp=head;
	//遍历链表直到找到匹配的学号成绩或达到链表的末尾
	while(temp!=NULL){
	//检查当前节点的学号是否与输入的学号匹配
	if(temp->stuNum==num){
		//如果匹配，跳出循环
		break;
	}
	//移动到链表的下一个节点
	temp=temp->pNext;
	}
	//如果temp为NULL，表示没有找到匹配的学号
	if(temp==NULL){
		printf("没有匹配的学号。\n");
	}else{
		//如果找到了匹配的学号，打印学生成绩信息
		printf("找到的信息如下：\n");
		printf("学号：%d,姓名：%s,性别：%c,成绩：%.2f\n",temp->stuNum,temp->name,temp->sex,temp->score);
		}
	system("pause");
	//清除控制台屏幕上的所有输出
	system("cls");
}
//修改成绩
void modify_score()
{
    //声明整形变量，用于存储用户输入的学号
    int num;
    //输出提示信息，要求用户输入要修改成绩的学号
    printf("请输入要修改成绩的学号：");
    //读取用户输入的学号，并存储到num变量中
    scanf("%d", &num);
    //开始从链表的头部开始遍历，temp用于遍历链表
    Student *temp = head;
    //遍历链表直到找到匹配的学号或达到链表的末尾
    while (temp != NULL){
    //检查当前节点的学号是否与输入的学号匹配
    	if (temp->stuNum == num){
	        //如果匹配，跳出循环
	        break;
    	}
    //移动到链表的下一个节点
    	temp=temp->pNext;
    }
    //如果temp为NULL，表示没有找到匹配的学号
    if (temp==NULL){
    	//如果temp为NULL，表示没有找到匹配的学号
        printf("没有匹配的学号。\n");
    }else{
        //如果找到了匹配的学号，提示输入新的成绩并更新
        printf("找到的信息如下：\n");
        printf("学号：%d, 姓名：%s, 性别：%c, 成绩：%.2f\n", temp->stuNum, temp->name, temp->sex, temp->score);
        //询问是否要修改记录
		printf("是否要修改记录？(y/n)");
        //清空输入缓冲区，以正确读取用户输入的单个字符
        getchar();
        //读取用户输入的字符
        char choose=getchar();
        //根据用户输入决定是否修改成绩
        if(choose=='y'|| choose=='Y'){
        	//用户选择了修改，提示输入新的成绩
        	printf("请输入新的成绩：");
        	//读取用户输入的新成绩，并更新找到的记录
        scanf("%f", &temp->score);
		}
    }
        //更新学生成绩
        printf("成绩已成功修改为：%.2f\n", temp->score);
    
    system("pause");
    system("cls");
}
//删除成绩
void delete_score()
{  //声明整形变量，用于存储用户输入的学号
	int num;
	//声明指针p并初始化为链表头部，用于遍历链表
	Student *p=head;
	//声明指针prev并初始化为NULL，用于记录遍历过程中的前一个节点
	Student *prev=NULL;
	printf("请输入要删除成绩的学号：");
	//获取用户输入的学号
	scanf("%d",&num);
	//判断链表是否为空
	if(head==NULL){
		//如果链表为空,输出提示信息
		printf("链表为空。没有数据。\n");
		system("pause");
		return;
	}
	//如果头节点就是要删除的节点
	if(head->stuNum==num){
		//输出学生信息
		printf("学号：%d,姓名：%s，性别：%c，成绩：%.2f\n",p->stuNum,p->name,p->sex,p->score);
		printf("是否要删除记录？(y/n)");
		getchar();
		//获取用户的选择
		char choose=getchar();
		//如果用户选择删除
		if(choose=='y'||choose=='Y'){
			//释放头节点的内存
			free(p);
			//将头指针重新指向头节点的下一个节点
			head=head->pNext;
			//输出删除成功的信息
			printf("删除学号%d的学生成功。\n",num);
		}
		system("pause");
		system("cls");
		return;
	}
	while(p!=NULL&&p->stuNum!=num){
		//记录当前节点作为前一个节点
		prev=p;
		//移动到下一个节点
		p=p->pNext;
	}
		//如果找到了要删除的学生
		if(p!=NULL){
			printf("找到的信息如下：\n");
			//输出学生信息
			printf("学号：%d，姓名：%s，性别：%c，成绩：%.2f\n",p->stuNum,p->name,p->sex,p->score);
			printf("是否要删除记录？(y/n)");
			getchar();
			char choose=getchar();
			if(choose=='y'||choose=='Y'){
				//更新前一个节点的pNext指针，跳过要删除的节点
				prev->pNext=p->pNext;
				//释放要删除的节点内存
				free(p);
				//输出删除成功的信息
				printf("删除学号%d的学生成功。\n",num);
			}
		}else{
			//如果没有找到学生，打印提示信息
			printf("学号%d的学生未找到。\n",num);
		}
		system("pause");
		system("cls");
	
}
// 打印成绩
void student_list()
{
	printf("学号\t\t姓名\t性别\t成绩\n");// 打印学生信息的表头，使用制表符对齐各列
	// 声明一个指向 Student 结构体的指针 p，并将其初始化为链表的头节点
	Student *p=head;// 遍历链表，直到 p 指向空(NULL)，即链表的末尾
	while(p!=NULL){// 打印当前节点中的学生信息，使用%-16d 等指定输出字段宽度和对齐方式
		printf("%-16d%-8s%-8c%-8.2f\n",p->stuNum,p->name,p->sex,p->score);
	// 更新指针 p，使其指向下一个节点
		p=p->pNext;
	}
	system("pause");
	system("cls");
}
//保存文件
void save_file()
{ 	//打开文件，使用"w"模式表示写入，如果文件不存在则创建新文件
	FILE* fp=fopen("D:\\LinkArrayData.txt","w");
	//检查文件是否成功打开
	if(fp==NULL){
		printf("文件不存在或无法打开！\n");
		//如果文件打开失败，直接返回
		return;
	}
	//遍历链表中的每一个学生节点
	//初始化p指针，用于遍历和更新链表
	Student *p=head;
	while(p!=NULL){
		//使用fwrite函数将链表中的学生数据写入文件
		//fwrite(数据指针，单个数据大小，数据个数，文件指针)
		//这里将整个学生节点的数据作为一个整体写入
		fwrite(p,sizeof(Student),1,fp);
		//移动到下一个节点
		p=p->pNext;
	}
	//关闭文件
	fclose(fp);
	//输出保存成功的消息
	printf("保存成功！\n");//暂停程序，等待用户按键后继续执行
	system("pause");
	system("cls");
}
//读取文件
void read_file()
{	//打-开文件，使用"r"模板表示读取
	FILE* fp=fopen("D:\\LinkArrayData.txt","r");
	// 检查文件是否成功打开
	if(fp==NULL){
		printf(" 文件不存在或无法打开！\n");
		// 如果文件打开失败，直接返回
		return;
	}
// 定义一个 Student 结构体变量 stu，用于临时存储读取的数据	
Student stu;
// 初始化 p 指针，用于遍历和更新链表
Student *p=head;
// 使用 fread() 函数读取文件中的数据，直到文件结束
while(fread(&stu,sizeof(Student),1,fp)){
	// 动态分配内存创建新节点
	Student *newNode=(Student *)malloc(sizeof(Student));
	// 设置新节点的 pNext 为 NULL
	newNode->pNext=NULL;
	// 使用 memcpy() 函数将读取到的数据复制到新节点中
	memcpy(newNode,&stu,sizeof(Student));
	// 如果链表为空，设置新节点为头节点
	if(p==NULL){
		head=newNode;
	}else{
		// 如果链表非空，将新节点链接到链表的末尾
		p->pNext=newNode;
	}
	// 更新 p 指针，使其指向新节点
	p=newNode;
	}
	// 关闭文件
	fclose(fp);
	// 输出读取成功的消息
	printf("读取成功！\n");
}

