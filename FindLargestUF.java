/*
Union-find with specific canonical element. Add a method find() to the union-find data type so that find(i)
returns the largest element in the connected component containing i. 
The operations, union(), connected(), and find()should all take logarithmic time or better.
For example, if one of the connected components is {1,2,6,9}, then the find()method should return 9 
for each of the four elements in the connected components.
*/

/*solution
We can use an array maxEle[i] to record the largest element in the connected component with the root of i. 
Every time we union two components, we update the maxEle[i]. 
So in the method of find(i), we first find the root of element i, then return maxEle[root].
*/

public class FindLargestUF {
      private int parent[]; //parent of node
      private int size[];   //size of component with the root node i
      private int maxEle[]; //the largest element of the component with the root node i
      
      public FindLargestUF(int n) {
            parent = new int[n];
            size = new int[n];
            maxEle =new int[n];
            for(int i=0; i<n; ++i){
                  parent[i] = i;
                  size[i] = 1;
                  maxEle[i] = i;
            }
      }
      
      private int root(int p) {
            while(parent[p] != p){
                  parent[p] = parent[parent[p]]; //path compression
                  p = parent[p];
            }
            return p;
      }
      
      public void union(int p, int q) {
            int i = root(p);
            int j = root(q);
            if (i == j) return;
            int maxp = maxEle[i];
            int maxq = maxEle[j];           
            if (size[i] < size[j]){
                  parent[i] = j; 
                  size[j] += size[i]; 
                  if(maxp > maxq)
                        maxEle[j] = maxp;
            }
            else{
                  parent[j] = i; 
                  size[i] += size[j]; 
                  if(maxq > maxp)
                        maxEle[i] = maxq;
            }
      }
      
      public boolean connected(int p, int q) {
        return root(p) == root(q);
      }
      
      public int find(int p){
            return maxEle[root(p)];
      }
      
      public static void main(String[] args) {
            FindLargestUF fluf = new FindLargestUF(5);
            fluf.union(0, 1);
            fluf.union(0, 2);
            fluf.union(4, 3);
            System.out.println("the largest element of component with root 1: " + fluf.find(1));
      }
}
