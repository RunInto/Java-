//�����Ա�slist��pλ��֮ǰ����x,�ɹ�����1�����򷵻�0 
int InsertPre_seq(SeqList slist, int p, DataType x)
{
	int q;
	if (slist->n >= slist->Max) 
	{
		printf("overflow");
		return (0);
	}
	if (p<0) || p>slist->n)//�������±�Ϊp��Ԫ��
	{
		printf("not exist!\n");
		return (0); 
	} 
	for (q = slist->n-1;q>=p;q--)//����λ���Լ�֮���Ԫ�غ��� 
	{
		slist->elem[q+1] = slist->elem[q]; 
	}
	slist->elem[p] = x;//����Ԫ��x
	slist->n = slist->n+1;//˳����ȼ�1
	return (1); 
}
