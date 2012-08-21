public class PercolationStats {
    private double[] allruns;

    public PercolationStats(int N, int T) {    
        // perform T independent computational experiments on an N-by-N grid
        if ((N <= 0) || (T <= 0)) throw 
            new IllegalArgumentException("T or N are illegal arguments");
        allruns = new double[T];
        
        for (int i = 0; i < T; i++) {
            Percolation per = new Percolation(N);
            int steps = 0;
            //Stopwatch clock = new Stopwatch();
            while ((!per.percolates()) && (steps < N*N)) { 
                //prevents infinite loop
                int x = 1 + StdRandom.uniform(N);
                int y = 1 + StdRandom.uniform(N);
                while (per.isOpen(x, y)) { 
                    //if not is open, regenerates random until it does
                    x = 1 + StdRandom.uniform(N);
                    y = 1 + StdRandom.uniform(N);
                }
                per.open(x, y); //open site
                steps++; //increases the number of runs    
            }

            //double elapsed = clock.elapsedTime();
            double threshold = (double) steps/(N*N);
            //StdOut.println(steps + "/" + N*N + "=" + threshold );
            this.allruns[i] = threshold; 
        }
    }

    public double mean() {
        // sample mean of percolation threshold
        return StdStats.mean(allruns);
    }

    public double stddev() {                   
        // sample standard deviation of percolation threshold
        if (allruns.length <= 1)
            return Double.NaN;
        else 
            return StdStats.stddev(allruns);
    }

    public static void main(String[] args) {   // test client
        if (args.length > 1) {
            int N = Integer.parseInt(args[0]);
            int T = Integer.parseInt(args[1]);
            
            PercolationStats ps = new PercolationStats(N, T);
            double media    = ps.mean();
            double devest   = ps.stddev();
            double inicio   = media - 1.96*devest/Math.sqrt(T);
            double fin      = media + 1.96*devest/Math.sqrt(T);
            StdOut.println(media);
            StdOut.println(devest);
            StdOut.println(inicio + ", " + fin);
            
        }
    }
}

