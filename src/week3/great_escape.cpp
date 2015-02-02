#include <vector>
#include <assert.h>
#include <list>
#include <map>
#include <set>
#include <queue>
#include <deque>
#include <stack>
#include <bitset>
#include <algorithm>
#include <functional>
#include <numeric>
#include <utility>
#include <sstream>
#include <iostream>
#include <iomanip>
#include <cstdio>
#include <cmath>
#include <cstdlib>
#include <ctime>
#include <string>
#define ll long long
using namespace std;


void printVector(vector<int> v)
{
    for(int i=0;i<v.size();i++)
    {
        cout<<v[i]<<" ";
    }
}
void printGraph(vector< vector<int> > adj)
{
    for(int i=0;i<adj.size();i++)
    {
        cout<<i<<"->";
        printVector(adj[i]);
        cout<<endl;
    }
}


void addEdge(vector< vector<int> > & adj,int start,int end)
{
  /*cout<<"before push adj"<<start<<" =";
  printVector(adj[start]); cout<<endl;
  cout<<"before push adj"<<end<<" =";
  printVector(adj[end]); cout<<endl;*/

  adj[start].push_back(end);
  adj[end].push_back(start);

  /*cout<<"before push adj"<<start<<" =";
  printVector(adj[start]); cout<<endl;
  cout<<"before push adj"<<end<<" =";
  printVector(adj[end]); cout<<endl;*/
  //printGraph(adj);

}


main()
{
    int V,E,start,end,src,dest;
    cin>>V>>E;
    vector<vector<int> > adj(V);
    for(int edge=0;edge<E;++edge)
    {
        cin>>start;
        cin>>end;
        --start;--end;
        addEdge(adj,start, end);
    }
    cin>>src; --src;
    cin>>dest; --dest;

    int distTo[V];
    bool visited[V];
    for(int i=0;i<V;i++)
    {
        visited[i]=false;
        distTo[i]=0;
    }

    queue<int> q;
    q.push(src);
    distTo[src]=0;
    visited[src]=true;

    while(!q.empty())
    {
        int v=q.front();
        q.pop();
        if(v==dest) break;
        for(int i=0;i<adj[v].size();i++)
        {
            int w=adj[v][i];
            if(visited[w]) continue;
            q.push(w);
            visited[w]=true;
            distTo[w]=distTo[v]+1;
        }
    }

    cout<<distTo[dest];
}

