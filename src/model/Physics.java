package model;

import dev.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.awt.geom.Dimension2D;

public abstract class Physics {

    private double x, y;
    private double velX, velY;
    private Dimension2D dimension;
    private Pane layer;
    private ImageView imageView;

    public Physics(double x, double y, String path, Pane mainLayer) {
        setLocation(x, y);
        layer = mainLayer;
        loadImage(path);
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

    public Pane getLayer() {
        return layer;
    }

    public void setLayer(Pane layer) {
        this.layer = layer;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
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

    //adding imageView to the class
    public void addToLayer() {
        this.layer.getChildren().add(this.imageView);
    }

    //removing imageView to the class
    public void removeFromLayer() {
        this.layer.getChildren().remove(this.imageView);
    }

    private void loadImage(String path) {
        imageView = new ImageView(path);
        this.imageView.relocate(this.getX(), this.getY());
        this.addToLayer();
    }



}




