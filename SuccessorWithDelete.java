/*
Successor with delete. Given a set of N integers S={0,1,...,N−1} and a sequence of requests of the following form:
  Remove x from S
  Find the successor of x : the smallest y in S such that y≥x.
design a data type so that all operations (except construction) should take logarithmic time or better.
*/

/*solution
We can use the data structure of FindLargestUF. 
When a number is removed, union the immediate neighbor if it is also removed. 
Then the largest successor is equal to the largest number in that component plus 1.
*/

public class SuccessorWithDelete {
      private boolean rm[]; // rm[i] == true if removed
      private FindLargestUF uf;
      private int n; //number of integers
      
      public SuccessorWithDelete(int n) {
            this.n = n;
            rm = new boolean[n];
            for(int i = 0; i < n; ++i)
                  rm[i] = false;
            uf = new FindLargestUF(n);
      }
      
      public void remove(int x) {
            rm[x] = true;
            if(x > 0 && rm[x-1])
                  uf.union(x, x-1);
            if(x < n-1 && rm[x+1])
                  uf.union(x, x+1);
      }
      
      public int successor(int x) {
            if(!rm[x]) return x;
            else{
                  int scs = uf.find(x) + 1;
                  if(scs >= n) {
                        System.out.println("no successor!");
                        return -1;
                  }
                  else return scs;
            }
      }
      
      public static void main(String[] args) {
            SuccessorWithDelete swd = new SuccessorWithDelete(10);
            swd.remove(3);
            swd.remove(4);
            System.out.println("the successor of element 3 is : " + swd.successor(3));
      }
}

