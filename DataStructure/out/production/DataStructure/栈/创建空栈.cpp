typedef int DataType;
struct Stack 
{
	int MAX;
	int top;//ջ��ָ�� 
	DataType *elem;//���Ԫ�ص�������ʼָ�� 
};

typedef struct Stack *SeqStack //����˳��ջ����

//������ջ
SeqStack SetNullStack_Seq(int m) //mΪ��������ռ�
{
	SeqStack sstack = (SeqStack)malloc(sizeof(struct SeqStack));
	if(sstack!=NULL)
	{
		sstack->elem=(int*)malloc(sizeof(int)*m);
		if(sstack->elem!=NULL)
		{
			sstack->MAX=M;//˳��ջ�������
			sstack->top=-1;//����ջ����ֵΪ-1
			return(sstack); 
		}
		else
		{
			free(sstack);
			return NULL;
		}
	}
	else
	{
		printf("out of space");
		return NULLL;
	}
}
  
 
