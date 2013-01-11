import java.util.*;
class Solution
{
	public static void main(String s[])
	{
		Solution sol=new Solution();
		try
		{
			Scanner scn=new Scanner(System.in);
			int n=scn.nextInt();
			long k=scn.nextLong();
			long uf[]=new long[n];
			ArrayList<Integer> primes=sol.gen_primes(k);
			TreeSet<Long> fact=sol.getPrimeFactors(primes,k);					
			for(int i=0;i<n;i++) uf[i]=scn.nextLong();
			sol.findUnfriendlyNumbers(fact,uf,k);
		}
		catch(Exception e){System.out.print("Error:"+e);}
	}
	
	void findUnfriendlyNumbers(TreeSet<Long> fact,long[] uf,long k)
	{
	int count=0;
	fact.remove((long)1);
	  for(int i=0;i<uf.length;i++)
	  {
		boolean flag=false;
		long sqrt=(long)Math.sqrt(uf[i]);
		long num=0;
		for(long item:fact)
		{						  
		   if(uf[i]%item==0)
		    {				
			flag=true; 
			num=item;
			break;
		    }
		}
		if(!flag) count++;
		else fact.remove(num);
	  }
	System.out.print(fact.size());
	}

	void solve(ArrayList<Long> factors,ArrayList<Integer> powers,int i,long prod,TreeSet<Long> fact)
	{
		if(i<factors.size())
		{
			long factor=factors.get(i);
			long pow=powers.get(i);
			for(int j=0;j<=pow;j++)
			{
			    long p=(long)Math.pow(factor,j);
			    fact.add(p*prod);
			    solve(factors,powers,i+1,prod*p,fact);
			}
		}
	}


	ArrayList<Integer> gen_primes(long num)
	{
		int N=(int)Math.sqrt(num);
		int primes[]=new int[1000000];
		ArrayList<Integer> primelist=new ArrayList<Integer>();
		Arrays.fill(primes,1);
		for(int i=2;i<=N;i++)
		{						
		   if(primes[i]!=0)
		   {
		     primelist.add(i);				
		     for(int j=i*2;j<=N;j+=i)
		        primes[j]=0;
		   }
		}				
	return primelist;
	}

	TreeSet<Long> getPrimeFactors(ArrayList<Integer> primelist,long num)
	{
		ArrayList<Long> factorlist=new ArrayList<Long>();
		ArrayList<Integer>  power=new ArrayList<Integer>();
		for(int item : primelist)
		{
			if(item>num) break;
			int pow=1;
			if(num%item==0)
			{							
			 factorlist.add((long)item);
				num/=item;	
				while(num%item==0)
				{
				 pow++;
				 num/=item;
				}
			   power.add(pow);
			}
		}

	if(num>1){factorlist.add(num);power.add(1);}
	TreeSet<Long> f=new TreeSet<Long>();	
	solve(factorlist,power,0,1,f);
	return f;
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
	

