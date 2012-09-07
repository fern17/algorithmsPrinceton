import java.util.Arrays;
public class Brute {
    public static void main(String[] args) {
        In in = new In(args[0]);      // input file
        int N = in.readInt();

        Point [] puntos = new Point[N];
        int it = 0;
        while (!in.isEmpty()) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            puntos[it] = p;
            //p.draw();

            it++;
        }
        
        Arrays.sort(puntos, new Point(0,0).SLOPE_ORDER);

        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenRadius(0.01);
        for (int i = 0; i < puntos.length; i++) {
            for (int j = i+1; j < puntos.length; j++) {
                double slope_ij = puntos[i].slopeTo(puntos[j]);
                
                for (int k = j+1; k < puntos.length; k++) {
                    double slope_ik = puntos[i].slopeTo(puntos[k]);
                    
                    for (int l = k+1; l < puntos.length; l++) {
                        double slope_il = puntos[i].slopeTo(puntos[l]);
                        
                        //ordenar los puntos

                        if ((slope_ij == slope_ik) && (slope_ij == slope_il)) {
                            StdOut.println( puntos[i].toString() + " -> " + 
                                            puntos[j].toString() + " -> " +
                                            puntos[k].toString() + " -> " + 
                                            puntos[l].toString());
                           
                            
                            //puntos[i].drawTo(puntos[j]);
                            //puntos[j].drawTo(puntos[k]);
                            //puntos[k].drawTo(puntos[l]);

                            //StdIn.readLine();
                        }
                    }
                }
            }
        }
    }
}
