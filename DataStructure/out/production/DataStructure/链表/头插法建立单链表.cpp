void CreateList_Head(struct Node *head)//ͷ�巨����������
{
	PNode p = NULL;//��ʱʹ��
	int data;
	print("�������������ݽ���������-1����\n");
	scanf("%d",&data);	
	while(data!=-1)
	{
		p = (struct Node*)malloc(sizeof(struct Node));
		p->data=data;
		p->next=head->next;
		head->next=p;
		scanf("%d",&data);
	}
}
