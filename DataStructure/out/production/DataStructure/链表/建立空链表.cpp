LinkList SetNullList_Link()//创建带有头结点的空链表 
{
	LinkList head = (LinkList)malloc(sizeof(struct Node));//申请头结点空间
	if (head!=null)
	{
		head->next=NULL;	
	}
	else 
	{
		printf("alloc failure");
	}
	return head;//返回头指针 
} 


int IsNull_Link(LinkList head)//判断链表是否为空
{
	return (head->next == null);
} 
