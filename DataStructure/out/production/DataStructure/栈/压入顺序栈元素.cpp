void Push_seq(SeqStack sstack,int x)
{
	if(sstack->top>=(sstack->MAX-1))//检查栈是否满
		printf("overflow!\n");
	else
	{
		sstack->top++;//若不满，先修改栈顶变量
		sstack->elem[sstack->top]=x; 
	 } 
 } 
