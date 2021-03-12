package model;

import javafx.scene.shape.Rectangle;

import java.awt.geom.Dimension2D;

public abstract class Physics {

    private double x, y;
    private double velX, velY;
    private Dimension2D dimension;

    public Physics(double x, double y) {
        setLocation(x, y);
    }

    public void setLocation(double x, double y) {
        setX(x);
        setY(y);
    }

    public void updateLocation() {
        x = x + velX;
        y = y + velY;
    }



    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getVelX() {
        return velX;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public Dimension2D getDimension() {
        return dimension;
    }

    public void setDimension(Dimension2D dimension) {
        this.dimension = dimension;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, dimension.getWidth(), dimension.getHeight());
    }



}




