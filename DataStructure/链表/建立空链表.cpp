LinkList SetNullList_Link()//��������ͷ���Ŀ����� 
{
	LinkList head = (LinkList)malloc(sizeof(struct Node));//����ͷ���ռ�
	if (head!=null)
	{
		head->next=NULL;	
	}
	else 
	{
		printf("alloc failure");
	}
	return head;//����ͷָ�� 
} 


int IsNull_Link(LinkList head)//�ж������Ƿ�Ϊ��
{
	return (head->next == null);
} 
