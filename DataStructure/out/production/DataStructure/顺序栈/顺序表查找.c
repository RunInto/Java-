int LocateIndex_seq(SeqList slist, int x)
{
	int q;
	for (q=0;q<slist->n;q++)
	{
		if (slist->elem[q] == x)//���ҳɹ������ض�Ӧ���±�
		{
			return q; 
		}	
		return -1;
	}	
} 
