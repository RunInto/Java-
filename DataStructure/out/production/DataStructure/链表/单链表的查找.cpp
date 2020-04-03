 PNode Locate_Link(LinkList list,DataType x)//按值查找 
 {
 	PNode p;
	 if (list==NULL) return NULL;
	 p=list->next;
	 while(p!=NULL&&p-data!=x)
	 	p=p->next;
	 return p;	
 }
 
 PNode Locate_Link_Num(LinkList list,int x)//按序号查找
 {
 	PNode p;
 	if (list==NULL) return NULL;
 	p=list->next;
 	int counter = 0;
 	
 	while(p!=NULL)
 	{
 		p=p->next;
 		counter++;
 		if (counter == x)
 		{
 			return p;	
		}
	}
	 
 }
   
