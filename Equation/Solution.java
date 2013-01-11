import java.util.*;
class Solution
{
	public static void main(String s[])
	{
		Solution sol=new Solution();
		long MAX=1000007;					
		long sum=1l;
		try
		{
			Scanner scn=new Scanner(System.in);
			int n=scn.nextInt();					
			int primes[]=sol.primes(n);					
			int mul[]=sol.multiplicit(primes,n,n);
			for(int i=2;i<=n;i++)
		 	{
				if(mul[i]!=0)
				 { 
				  sum=((mul[i]*sum)%MAX);				   				   
				}
			}
 
		System.out.print(sum+" ");
		}
		catch(Exception e){System.out.print("Error:"+e+"\n");}
	}

	int[] primes(int N)
	{
	 int primes[]=new int[N+1];
	 Arrays.fill(primes,1);
	   for(int i = 2;i * i <= N;i++)
	    {		
		  if(primes[i]==1)               	
		  {
			for(int j = i; j*i <= N;j++)                     	
			 primes[j*i]=0; 
		  }                               	     	
	    }
	 return primes;
	}

 
	int[] multiplicit(int primes[],int N,int n)
	{
	 int multiplicit[]=new int[N+1];
	 Arrays.fill(multiplicit,0);
		for(int i = 2;i <=N;i++)
	        {
			   if(primes[i]==1)
	           {
	                 int temp = n,e = 0;
			         while(temp!= 0)
	                 {	
			       	  e += temp / i;		
			          temp = temp / i;
			         }					
			         multiplicit[i] = 2 * e + 1;
			   }
		   }
	 return multiplicit;	
	}
}

