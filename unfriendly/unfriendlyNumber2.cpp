#include <iostream>
#include <cstdio>
#include <cstring>
#include <cmath>
#include <cstdlib>
#include <ctime>
#include <algorithm>
#include <vector>
#include <set>
#include <map>
#include <bitset>
#include <valarray>
#include <iterator>

using namespace std;

#define PRIME_N 10000000

#define REP(i, st, n) for(int (i) = st; (i) < (n); ++(i))
#define ULL unsigned long long
#define VULL vector<ULL>
#define VI vector<int>
#define ValI valarray<int>
#define P_ULL pair<ULL, ULL>

VULL allPrimes;

template<class T>
void print(T container)
{
	for(typename T::iterator it = container.begin();
		it != container.end(); ++it)
		cout << *it << " ";
	cout << endl;
}

ULL gcd(ULL a, ULL b) {
	return b ? gcd(b, a%b) : a;
}



void findPrimeFactors(ULL n, VI &primeFactors, VI &primePower)
{
	int sqrtN = ceil(sqrt(n));
	REP(i, 0, allPrimes.size()) 
	{
		if(allPrimes[i] > n || allPrimes[i] > sqrtN) break;
		if(n%allPrimes[i] == 0) 
		{
			primeFactors.push_back(allPrimes[i]);
			int factorPower = 1;
			n/=allPrimes[i];
			while(n > 1 && n%allPrimes[i] == 0) 
			{
				factorPower++;
				n/=allPrimes[i];
			}
			primePower.push_back(factorPower);
		}
	}
	if(n > 1) { primeFactors.push_back(n); primePower.push_back(1); }
}

void generatePrimes()
{ 
	bitset<PRIME_N> *bset = new bitset<PRIME_N>();
	bset->set();
	allPrimes.push_back(2);
	allPrimes.push_back(3);
	for(int i = 5; i < PRIME_N; i = i + 4)
	{
		if((*bset)[i]) {
			allPrimes.push_back(i);
			for(int j = i*2; j < PRIME_N; j+=i)
				bset->reset(j);
		}
		i = i+2;
		if(i < PRIME_N && (*bset)[i]) {
			allPrimes.push_back(i);
			for(int j = i*2; j < PRIME_N; j+=i)
				bset->reset(j);
		}
	}
}


ULL powProd(VI &primeFactors, VI &primePowers) 
{
	ULL prod = 1;
	REP(i, 0, primeFactors.size())
		prod *= (ULL)pow((double)primeFactors[i], primePowers[i]);
	return prod;
}

void constructDivisorSet(set<ULL> &divSet, VI &primeFactors,VI &primePower) 
{
	VI primeIndex(primePower.size(), 0);
	divSet.clear();
	cout<<endl;
	while(primeIndex.size() > 0) 
	{
		divSet.insert(powProd(primeFactors, primeIndex));
		cout<<primeIndex[primeIndex.size()-1]<<"<=>";
		primeIndex[primeIndex.size()-1]++;
		cout<<primeIndex[primeIndex.size()-1]<<endl;
		for(int i = primeIndex.size()-1; i>=0 && primeIndex[i] > primePower[i]; i--)
		{
			primeIndex[i] = 0;
			if((i-1) < 0) return;
			primeIndex[i-1]++;			
		}
	}	
	for(int i=0;i<primeIndex.size();i++) cout<<primeIndex[i]<<"--";
	print(divSet);
	if(divSet.size() == 0) divSet.insert(1);
}



void solveUnfriendlyNumbersFast(int N, ULL K, ULL *unfriendly) 
{
	generatePrimes();
	VI primeFactors, primePower;
	findPrimeFactors(K, primeFactors, primePower);
	set<ULL> divSet;
	constructDivisorSet(divSet, primeFactors, primePower);
	REP(i, 0, N) {
		ULL g = gcd(K, unfriendly[i]);
	
		set<ULL>::iterator it = divSet.begin();
		while(it != divSet.end() && g >= *it) {
			if(g% (*it) == 0) {
				ULL temp = *it;
				divSet.erase(it);
				it = divSet.upper_bound(temp);
			}
			else ++it;
		}
		if(divSet.size() == 0) break;
	}
	printf("%d\n", (int)divSet.size());
}



void solveProb() 
{
	int N;
	ULL K;
	ULL unfriendly[1000010];
	scanf(" %d %llu", &N, &K);
	REP(i, 0, N) scanf(" %llu", &unfriendly[i]);
	solveUnfriendlyNumbersFast(N, K, unfriendly);
}

int main(int argc, char *argv[])
{
	solveProb();
	return 0;
}


