typedef int DataType;
struct Stack 
{
	int MAX;
	int top;//栈顶指针 
	DataType *elem;//存放元素的数组起始指针 
};

typedef struct Stack *SeqStack //定义顺序栈类型

//创建空栈
SeqStack SetNullStack_Seq(int m) //m为分配的最大空间
{
	SeqStack sstack = (SeqStack)malloc(sizeof(struct SeqStack));
	if(sstack!=NULL)
	{
		sstack->elem=(int*)malloc(sizeof(int)*m);
		if(sstack->elem!=NULL)
		{
			sstack->MAX=M;//顺序栈最大容量
			sstack->top=-1;//设置栈顶初值为-1
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
  
 
