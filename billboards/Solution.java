import java.util.*;

class Solution
{
	public static void main(String s[])
	{
		Solution sol=new Solution();
		try{
		Scanner scn=new Scanner(System.in);
		int n=scn.nextInt();
		int k=scn.nextInt();
		long profit[]=new long[n];
			for(int i=0;i<n;i++) profit[i]=scn.nextLong();
		solve(profit,k);
		}catch(Exception e){}
	}

	void solve(long[] profit,int k)
	{
		
	}
}

