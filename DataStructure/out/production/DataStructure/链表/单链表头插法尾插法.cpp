int InsertPost_link(LinkList list,PNode p,DataType x)//��list�����е�pλ��֮�����ֵΪx�Ľ�� 
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

//ǰ�巨 ����Ҫ��ǰ����㣬����O��n�� 
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
