//删除第一个与输入参数data相等的值的结点
void DelValue_Link(struct Node *head,int data){
	struct Node *p=head->next;
	struct Node *beforeP=head;
	while(p!=null){
		if(p->data == data){
			beforeP->next=p->next;
			free(p);
			break;
		}
		else {
			beforeP=p;
			p=p->next;
		}
	} 
} 
