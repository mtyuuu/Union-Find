/*
Social network connectivity. Given a social network containing n members and a log file containing m timestamps at which times pairs of 
members formed friendships, design an algorithm to determine the earliest time at which all members are connected 
(i.e., every member is a friend of a friend of a friend ... of a friend). 
Assume that the log file is sorted by timestamp and that friendship is an equivalence relation. 
The running time of your algorithm should be mlogn or better and use extra space proportional to n.
*/

/*solution
We can add a count for number of connected component. 
The n members can be n objects. The m timestamps can be m unions. 
So every time we union two objects until the number of the unions is 1. 
This is the earliest time at which all members are connected.
*/

public class WeightedQuickUnionFind {
      private int parent[]; //parent of node
      private int size[];   //size of component with the root node i
      private int count;    //number of components
      
      public WeightedQuickUnionFind(int n){
            count = n;
            parent = new int[n];
            size = new int[n];
            for(int i=0; i<n; ++i){
                  parent[i] = i;
                  size[i] = 1;
            }
      }
      
      public int count(){
            return count;
      }
      
      private int root(int p){
            while(parent[p] != p){
                  parent[p] = parent[parent[p]]; //path compression
                  p = parent[p];
            }
            return p;
      }
      
      publid void union(int p, int q){
            int i = root(p);
            int j = root(q);
            if (i == j) return;
            if (size[i] < size[j]){
                  parent[i] = j; 
                  size[j] += size[i]; 
            }
            else{
                  parent[j] = i; 
                  size[i] += size[j]; 
            }
            count--;
      }
      
      public boolean connected(int p, int q) {
		       return root(p) == root(q);
      }
      
      public static void main(String[] args){
            WeightedQuickUnionFind wquf = new WeightedQuickUnionFind(5);
            System.println("wquf.count() = " + wquf.count());
            wquf.union(0,1);
            System.println("wquf.count() = " + wquf.count());
            wquf.union(2,4);
            System.println("wquf.count() = " + wquf.count());
      }
}
