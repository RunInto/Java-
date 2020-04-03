#include <stdio.h>

typedef struct List *SeqList;
struct List
{
	int Max;//最大元素个数 
	int n;//实际元素个数
	DataType *elem;//数组首地址 
};
void main() 
{
	
}




SeqList SetNullList_Seq(int m)//创建空顺序表 
{
	SeqList slist = (SeqList)malloc(sizeof(struct List));//申请结构体List空间
	if (slist != NULL) 
	{
		slist->elem = (DataType*)malloc(sizeof(DataType)*m);//申请顺序表空间，大小为m个DataType空间
		if(slist->elem)
		{
			slist->Max = m;//顺序表的最大值
			slist->n = 0;//顺序表长度赋值为0
			return(slist);	
		} 
		else free(slist);
	}
	printf("out of space!!\n");
	return NULL;
}
  

