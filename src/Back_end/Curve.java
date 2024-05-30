package Back_end;

public class Curve {
    private double R, S, E;

    public Curve(double r, double s, double e) {
        R = r;
        S = s;
        E = e;
    }

    public Curve(double r, double s) {
        R = r;
        S = s;
    }

    //todo: 怎么用？
    public void updateCurve(double R, long t, long T) {
        this.R = R;
        this.S = 0.3 + R * 0.2;
        this.E = Math.pow(R, (t-T) * 1.0 / S);
    }

    public double getR() {
        return R;
    }

    public void setR(double r) {
        R = r;
    }

    public double getS() {
        return S;
    }

    public void setS(double s) {
        S = s;
    }

    public double getE() {
        return E;
    }

    public void setE(double e) {
        E = e;
    }
}
