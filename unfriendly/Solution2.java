
import java.util.*;

class Solution2

{

	public static void main(String s[])

	{

		Solution2 sol=new Solution2();

		try

		{

			Scanner scn=new Scanner(System.in);

			int n=scn.nextInt();

			long k=scn.nextLong();

			long uf[]=new long[n];

			TreeSet<Long> factor=new TreeSet<Long>();

			TreeSet<Long> factor2=new TreeSet<Long>();

			sol.get_factors(factor,k);			

			for(int i=0;i<n;i++)			
				factor2.add(sol.gcd(scn.nextLong(),k));

			sol.solve(factor,factor2);

		        System.out.println(factor.size()+"");

		}

		catch(Exception e){System.out.print("Error:"+e);}

	}



	void solve(TreeSet<Long> f1,TreeSet<Long> f2)
	{	

		for(long a : f2 )		

		{			
		
			long num=0;
			for(long b : f1)

			{
				if(a%b==0)
				{				
				 num=b;
				 break;
				}
				if(b>a)break;
			}		 
			if(num!=0) f1.remove(num);

		}

	}



	void get_factors(TreeSet<Long> fact,long k)

	{

		for(int i=1;i<=Math.sqrt(k);i++) 

		{

			if(k%i==0)

			{

			  fact.add((long)i);

			  fact.add(k/i);

			}

		}

	}

	long gcd(long a,long b)

	{

		long c=0;

		while(a!=0)

		{

		 c=a;

		 a=b%a;

		 b=c;

		}

	return c;	

	}

}

	
