void Push_seq(SeqStack sstack,int x)
{
	if(sstack->top>=(sstack->MAX-1))//���ջ�Ƿ���
		printf("overflow!\n");
	else
	{
		sstack->top++;//�����������޸�ջ������
		sstack->elem[sstack->top]=x; 
	 } 
 } 
