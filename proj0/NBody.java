public class NBody {

    public static double readRadius(String filename){
        In in = new In(filename);
        int n_planet = in.readInt();
        double radius = in.readDouble();

        return radius;
    }

    public static int readnum(String filename){
        In in = new In(filename);
        int n_planet = in.readInt();

        return n_planet;
    }

    public static Body[] readBodies(String filename){
        In in = new In(filename);
        int n_planet = in.readInt();
        double radius = in.readDouble();

        Body[] allBodys = new Body[n_planet];

        for(int i=0;i<n_planet;i++){
            allBodys[i] = new Body(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
        }

        return allBodys;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        int n_planet = readnum(filename);
        double radius = readRadius(filename);
        Body[] allBodys = readBodies(filename);

        double scale = radius;
        double scale_k = 1;
        while(scale>1000){
            scale = scale/10;
            scale_k = scale_k *10;
        }

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-scale, scale);


        for(double time=0;time<=T;time=time+dt){
            double[] xForces = new double[n_planet];
            double[] yForces = new double[n_planet];

            for(int i=0;i<n_planet;i++){
                xForces[i] = allBodys[i].calcNetForceExertedByX(allBodys);
                yForces[i] = allBodys[i].calcNetForceExertedByY(allBodys);


            }

            for(int i=0;i<n_planet;i++){
                allBodys[i].update(dt,xForces[i],yForces[i]);
            }

            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg",2*scale,2*scale);
            for(Body b : allBodys){
                b.draw(scale_k);
            }
            StdDraw.show();
            StdDraw.pause(10);

        }

        StdOut.printf("%d\n", allBodys.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allBodys.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    allBodys[i].xxPos, allBodys[i].yyPos, allBodys[i].xxVel,
                    allBodys[i].yyVel, allBodys[i].mass, allBodys[i].imgFileName);
        }

    }

}
