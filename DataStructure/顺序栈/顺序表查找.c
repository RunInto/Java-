int LocateIndex_seq(SeqList slist, int x)
{
	int q;
	for (q=0;q<slist->n;q++)
	{
		if (slist->elem[q] == x)//查找成功，返回对应的下标
		{
			return q; 
		}	
		return -1;
	}	
} 
