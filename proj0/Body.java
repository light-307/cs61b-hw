public class Body {

    public double xxPos;   //x位置
    public double yyPos;   //y位置
    public double xxVel;   //x方向上的当前速度
    public double yyVel;   //y方向上的当前速度
    public double mass;    //其质量
    public String imgFileName;  //图像相对应的文件名

    public Body(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b){
        return Math.sqrt(Math.pow(b.xxPos - xxPos,2) + Math.pow(b.yyPos - yyPos,2));
    }

    public double calcForceExertedBy(Body b){
        if(this.equals(b))
            return 0;
        return 6.67e-11 * mass * b.mass / Math.pow(this.calcDistance(b),2);
    }

    public double calcForceExertedByX(Body b){
        return this.calcForceExertedBy(b) * (b.xxPos-xxPos) / this.calcDistance(b);
    }

    public double calcForceExertedByY(Body b){
        return this.calcForceExertedBy(b) * (b.yyPos-yyPos) / this.calcDistance(b);
    }

    public double calcNetForceExertedByX(Body[] allBodys){
        double NetForceExertedByX = 0;
        for(Body b : allBodys){
            if(this.equals(b)){
                continue;
            }
            NetForceExertedByX = NetForceExertedByX + this.calcForceExertedByX(b);
        }
        return NetForceExertedByX;
    }

    public double calcNetForceExertedByY(Body[] allBodys){
        double NetForceExertedByY = 0;
        for(Body b : allBodys){
            if(this.equals(b)){
                continue;
            }
            NetForceExertedByY = NetForceExertedByY + this.calcForceExertedByY(b);
        }
        return NetForceExertedByY;
    }

    public void update(double dt, double fx, double fy){
        xxVel = xxVel + dt * fx/mass;
        yyVel = yyVel + dt * fy/mass;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    public void draw(double scale_k){
        StdDraw.picture(xxPos/scale_k, yyPos/scale_k, "images/"+imgFileName);
    }
}
