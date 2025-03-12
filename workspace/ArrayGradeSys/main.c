#include <stdio.h> 
#include <stdlib.h>   
//最大学生人数
#define MaxNum 100
//实际学生人数
int actnum=6;
// 存放学生信息 
//int stu[MaxNum][2];
//测试数据
int stu[MaxNum][2] = {{22, 64}, {26, 69}, {25, 70}, {21, 88}, {23, 91}, {30, 75}};  
void input_score();//录入
void modify_score();//修改
void remove_score();//删除
void print_score();//打印
void search_score();//查询
void save_score();//保存
void read_score();//读取
void bubble_sort_by_score();//成绩基于冒泡排序
void insertion_sort_by_score();//成绩基于插入排序
void selection_sort_by_score();//成绩基于选择排序
int main()   
{
	int choose = -1;  
    while (1) { // 循环开始  
        printf(" 1. 学生成绩录入\n");  
        printf(" 2. 学生成绩修改\n");  
        printf(" 3. 学生成绩删除\n");  
        printf(" 4. 学生成绩查询\n");  
        printf(" 5. 学生成绩打印\n");  
		printf(" 6. 学生成绩保存\n");  
		printf(" 7. 学生成绩读取\n");  
		printf(" 8. 按成绩升序(冒泡排序)\n");
		printf(" 9. 按成绩升序(插入排序)\n");
		printf("10. 按成绩升序(选择排序)\n");
		printf("0. 退出\n");    
        printf("请输入您的选择(0-10):");  
        scanf("%d", &choose);  
        printf("您选择的是：%d\n", choose);  
        switch (choose) {  
            case 1:  
                printf("正在使用：学生成绩录入\n");  
                input_score();  
                break;  
            case 2:  
                printf("正在使用：学生成绩修改\n");  
                modify_score();
                break;                  
            case 3:                  
                printf("正在使用：学生成绩删除\n");  
                remove_score();                
                break;  
            case 4:  
                printf("正在使用：学生成绩查询\n");
                search_score();  
                break;  
            case 5:  
                printf("正在使用：学生成绩打印\n");  
                print_score();  
                break;
			case 6:
			 	printf("正在使用：学生成绩保存\n");
				save_score();
				break;
			case 7:
				printf("正在使用：学生成绩读取\n");
				read_score();
				break;
			case 8:
				printf("正在使用：成绩排序(冒泡排序)\n");
				bubble_sort_by_score();
				break;
			case 9:
				printf("正在使用：成绩排序(插入排序)\n");
				insertion_sort_by_score();
				break;
			case 10:
				printf("正在使用：成绩排序(选择排序)\n");
				selection_sort_by_score();
				break;
			case 0:  
                printf("谢谢使用，再见！\n");  
                return 0;
            default:  
                printf("对不起，没有此菜单项\n");  
                break;
        }  
    } // 循环结束  
    return 0;
}  
//录入
void input_score()  
{
    printf("准备成绩录入......\n");  
    for(int i=actnum;i<MaxNum;i++){ 
        printf("请输入第%d个学生的学号:",i+1);  
        scanf("%d", &stu[i][0]);  
        printf("请输入第%d个学生的成绩:",i+1);  
        scanf("%d", &stu[i][1]);
		actnum++;  
	    getchar();
	    printf("是否继续[Y/N]\n");
	    char flag = getchar();
	    if(flag=='n'||flag=='N'){
	    	break;
		}	
	}
}
//修改
void modify_score()
{
	printf("准备成绩修改......\n");
	int number;
	printf("请输入要修改的学生号：");
	scanf("%d",&number);
	int i=0;
	while(stu[i][0]!=number && i<actnum){i++;}
	if(stu[i][0]==number){printf("学号为%d的学生成绩是%d\n",stu[i][0],stu[i][1]);
	printf("请输入学号为%d的新成绩：",stu[i][0]);
	scanf("%d",&stu[i][1]);
	}else{
	printf("学号为%d的学生没找到！\n",number);
	}
}
//删除 
void remove_score()
{
    printf("准备成绩删除......\n");
	int number;
	printf("请输入要删除的学生号：");
	scanf("%d",&number);
	int i=0;
    while(stu[i][0]!=number && i<actnum){i++;}
    if(stu[i][0]==number){
	for(;i<actnum;i++){
		stu[i][0]=stu[i+1][0];
		stu[i][1]=stu[i+1][1];
	}
	printf("已删除成功！\n");
	actnum--;
	}else{
    printf("学号为%d的学生成绩没找到！\n", number);   
	}
}  
//查询
void search_score()  
{
    printf("准备成绩查询......\n"); 
    int number;
    printf("请输入要查询的学号：");  
    scanf("%d",&number);
    int found=0;
    for (int i=0;i<6;i++) {  
        if (stu[i][0]==number) {  
            printf("学号为%d的学生成绩是%d\n",stu[i][0],stu[i][1]);  
            found=1; 
            break;  
        }  
    }  
    if (!found) {  
    printf("学号为%d的成绩没找到！\n", number);  
    }  
}
//打印
void print_score()  
{  
    printf("准备成绩打印......\n");  
    printf("学号\t-\t成绩\n");  
    for(int i=0; i<actnum;i++){  
        printf("%d\t-\t%d\n", stu[i][0], stu[i][1]);  
    }    
}
//保存
void save_score()
{
printf("准备保存数据到文件......\n");
	FILE *fp;
	fp=fopen("D:\\score.txt","a+");
	if(fp==NULL){
		printf("文件打开失败\n");
		system("pause");
	}else{
		int i=0;
		while(stu[i][0]!=0){
			fprintf(fp,"%d,%d\n",stu[i][0],stu[i][1]);i++;
		}
		fclose(fp);
	}
}
//读取
void read_score()
{
	printf("准备读取文件数据......\n");
	FILE *fp;
	fp=fopen("d:\\score.txt","a+");
	if(fp==NULL){
		printf("文件打开失败");
		system("pause");
	}else{
	int i=0;
	actnum=0;
	while(fscanf(fp,"%d,%d\n",&stu[i][0],&stu[i][1])!=EOF)
	{i++;
	 actnum++;
	}
	printf("%d条数据\n",i+1);
	fclose(fp);
	}
}
//成绩冒泡排序
void bubble_sort_by_score()
{
	printf("准备成绩排序，基于冒泡排序......\n");
	for(int i=0;i<actnum-1;i++){
		for (int j=0;j<actnum-1;j++){
			if(stu[j][1]>stu[j+1][1]){
				int tempId=stu[j][0];
				int tempScore=stu[j][1];
				stu[j][0]=stu[j+1][0];
				stu[j][1]=stu[j+1][1];
				stu[j+1][0]=tempId;
				stu[j+1][1]=tempScore;
			}
		}	
	}
}
//成绩插入排序
void insertion_sort_by_score()
{
	printf("准备成绩排序，基于插入排序......\n");
	for (int i=1;i<actnum;i++){
		int keyId=stu[i][0];
		int keyScore=stu[i][1];
		int j=i-1;
		while(j>=0 && stu[j][1]>keyScore){
			stu[j+1][0]=stu[j][0];
			stu[j+1][1]=stu[j][1];
			j=j-1;
		}
		stu[j+1][0]=keyId;
		stu[j+1][1]=keyScore;
	}
}
//成绩选择排序
void selection_sort_by_score()
{
	printf("准备成绩排序，基于选择排序......\n");
	for(int i=0;i<actnum-1;i++){
		int minIndex=i;
		for(int j=i+1;j<actnum;j++){
			if(stu[j][1]<stu[minIndex][1]){
				minIndex=j;
			}
		}
		if(minIndex!=i){
			int tempId=stu[i][0];
			int tempScore=stu[i][1];
			stu[i][0]=stu[minIndex][0];
			stu[minIndex][0]=tempId;
			stu[minIndex][1]=tempScore;
		}
	}
}
