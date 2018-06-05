package ua.regi.rovno.Task;

public class PVector {

    private float x;
    private float y;

    public PVector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ";" + y + ")";
    }

    public PVector multiply(float a){
        return new PVector(x * a, y * a);
    }
}
