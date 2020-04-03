//在线性表slist的p位置之前插入x,成功返回1，否则返回0 
int InsertPre_seq(SeqList slist, int p, DataType x)
{
	int q;
	if (slist->n >= slist->Max) 
	{
		printf("overflow");
		return (0);
	}
	if (p<0) || p>slist->n)//不存在下标为p的元素
	{
		printf("not exist!\n");
		return (0); 
	} 
	for (q = slist->n-1;q>=p;q--)//插入位置以及之后的元素后移 
	{
		slist->elem[q+1] = slist->elem[q]; 
	}
	slist->elem[p] = x;//插入元素x
	slist->n = slist->n+1;//顺序表长度加1
	return (1); 
}
