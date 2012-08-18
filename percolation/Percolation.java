//import java.lang.IndexOutOfBoundsException;
public class Percolation {
    private WeightedQuickUnionUF sites; //quickunion
    private int N;                      //number of sites
    
    public Percolation(int N) {              
        // create N-by-N grid, with all sites blocked
        this.sites = new WeightedQuickUnionUF(N*N+2);
        this.N = N;
        
        for (int i = 1; i <= N; i++) {
            //unites all sites with the source
            this.sites.union(0, i); 
        }
        
        for (int i = N*N-N+1; i <= N*N; i++) {
            //unites all sites with the destiny
            this.sites.union(N*N+1, i);
        }
    }
    
    private int cC(int i, int j) { 
        //converts a coordinate (i,j) into a array index
        
        if (i <= 0 || i > this.N) throw 
            new IndexOutOfBoundsException("row index i out of bounds");
        if (j <= 0 || j > this.N) throw 
            new IndexOutOfBoundsException("row index j out of bounds");
        
        //calculates the position in the quickunion
        return 1+this.N*(i-1)+j-1; 
    }

    public void open(int i, int j) {         
        // open site (row i, column j) if it is not already
        if (i <= 0 || i > this.N) throw 
            new IndexOutOfBoundsException("row index i out of bounds");
        if (j <= 0 || j > this.N) throw 
            new IndexOutOfBoundsException("row index j out of bounds");

        if (isOpen(i, j))
            return;
        int pos = cC(i, j);
        this.sites.union(pos, pos); //unites with itself
        if ((i != 1) && (isOpen(i-1, j))) { 
            //if this is not at the upper bound
            this.sites.union(pos, pos-N);
        }
        if ((i != N) && (isOpen(i+1, j))) { 
            //if this is not at the lower bound
            this.sites.union(pos, pos+N);
        }
        if ((j != 1) && (isOpen(i, j-1))) { 
            //if not left bound
            this.sites.union(pos, pos-1);
        }
        if ((j != N) && (isOpen(i, j+1))) {
            //if not right bound
            this.sites.union(pos, pos+1);
        }
    }

    public boolean isOpen(int i, int j) {    
        // is site (row i, column j) open?
        if (i <= 0 || i > this.N) throw 
            new IndexOutOfBoundsException("row index i out of bounds");
        if (j <= 0 || j > this.N) throw 
            new IndexOutOfBoundsException("row index j out of bounds");
    
        int pos = cC(i, j); //gets array position
        return this.sites.connected(pos, pos);
    }

    public boolean isFull(int i, int j) {    
        // is site (row i, column j) full?
        if (i <= 0 || i > this.N) throw 
            new IndexOutOfBoundsException("row index i out of bounds");
        if (j <= 0 || j > this.N) throw 
            new IndexOutOfBoundsException("row index j out of bounds");
        int pos = cC(i, j);
        return this.sites.connected(0, pos);
    }
    
    public boolean percolates() {            
        // does the system percolate?
        return this.sites.connected(0, N*N+1);
    }
}
