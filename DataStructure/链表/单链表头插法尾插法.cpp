int InsertPost_link(LinkList list,PNode p,DataType x)//在list链表中的p位置之后插入值为x的结点 
{
	PNode q;
	if (p==NULL) {
		printf("para failure!\n");
		return 0;
	} 
	q = (PNode)malloc(sizeof(struct Node));
	if (q == NULL){
		printf("out of space!\n");
		return 0;
	}
	else {
		q->data=x;
		q->next=p->next;
		p->next=q;
		return 1;
	}
} 

//前插法 ，需要找前驱结点，所以O（n） 
int InsertPre_link(LinkList list,PNode p,DataType x){
	PNode pre = list;
	PNode q = NULL;
	 if (p==NULL) {
		printf("para failure!\n");
		return 0;
	} 
	while (pre->next!=p){
		pre=pre->next;
	}
	q=(PNode)malloc(sizeof(struct Node));
	q->data=x;
	q->next=p;
	pre->next=q;
	return 1;
}
