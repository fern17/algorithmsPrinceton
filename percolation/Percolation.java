import algs4.WeightedQuickUnionUF;
public class Percolation {
    private WeightedQuickUnionUF sites; //quickunion
    private int N;                      //number of sites
    public int cC(int i, int j){ //converts a coordinate (i,j) into a array index
        return 1+this.N*(i-1)+j-1; //calculates the position in the quickunion
    }
    
    public Percolation(int N){              // create N-by-N grid, with all sites blocked
        this.sites = new WeightedQuickUnionUF(N*N+2);
        this.N = N;
        for (int i = 1; i <= N; i++){
            this.sites.union(0,i); //unites all sites with the source
        }
        for (int i = N*N-N+1; i <= N*N; i++){
            this.sites.union(N*N+1,i); //unites all sites with the destiny
        }
    }

    public void open(int i, int j){         // open site (row i, column j) if it is not already
        if(isOpen(i,j))
            return;
        int pos = cC(i,j); 
        if(i != 1){ //if this is not at the upper bound
            this.sites.union(pos, pos-N);
        }
        if(i != N){ //if this is not at the lower bound
            this.sites.union(pos, pos+N);
        }
        if(j != 1){ //if not left bound
            this.sites.union(pos,pos-1);
        }
        if(j != N){ //if not right bound
            this.sites.union(pos,pos+1);
        }
    }

    public boolean isOpen(int i, int j){    // is site (row i, column j) open?
        int pos = cC(i,j); //gets array position
        if ((i != 1) && (this.sites.connected(pos,pos-N))) 
            return true;
        if ((i != N) && (this.sites.connected(pos,pos+N))) 
            return true;
        if ((j != 1) && (this.sites.connected(pos,pos-1))) 
            return true;
        if ((j != N) && (this.sites.connected(pos,pos+1))) 
            return true;
        return false;
    }

    public boolean isFull(int i, int j){    // is site (row i, column j) full?
        int pos = cC(i,j);
        return this.sites.connected(0, pos);
    }
    
    public boolean percolates(){            // does the system percolate?
        return this.sites.connected(0, N*N+1);
    }
}
