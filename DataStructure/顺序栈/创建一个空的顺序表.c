#include <stdio.h>

typedef struct List *SeqList;
struct List
{
	int Max;//���Ԫ�ظ��� 
	int n;//ʵ��Ԫ�ظ���
	DataType *elem;//�����׵�ַ 
};
void main() 
{
	
}




SeqList SetNullList_Seq(int m)//������˳��� 
{
	SeqList slist = (SeqList)malloc(sizeof(struct List));//����ṹ��List�ռ�
	if (slist != NULL) 
	{
		slist->elem = (DataType*)malloc(sizeof(DataType)*m);//����˳���ռ䣬��СΪm��DataType�ռ�
		if(slist->elem)
		{
			slist->Max = m;//˳�������ֵ
			slist->n = 0;//˳����ȸ�ֵΪ0
			return(slist);	
		} 
		else free(slist);
	}
	printf("out of space!!\n");
	return NULL;
}
  

