void CreateList_Head(struct Node *head)//头插法建立单链表
{
	PNode p = NULL;//零时使用
	int data;
	print("请输入整型数据建立链表，以-1结束\n");
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
