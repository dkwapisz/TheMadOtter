package model;

import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;


public abstract class StaticObjects {

    private double x, y;
    private javafx.geometry.Dimension2D dimension;
    private Pane layer;
    private ImageView imageView;
    private Image imageStatic;

    public StaticObjects(double x, double y, String pathStatic, Pane layer) {
        setLocation(x, y);
        this.layer = layer;
        loadImage(pathStatic);
        imageStatic = new Image(pathStatic);
        dimension = new javafx.geometry.Dimension2D(imageStatic.getWidth(), imageStatic.getHeight());
    }

    public void setLocation(double x, double y) {
        setX(x);
        setY(y);
    }


    private void loadImage(String path) {
        imageView = new ImageView(new Image(path));
        this.imageView.relocate(this.getX(), this.getY());
        this.addToLayer();
    }


    public void addToLayer() {
        this.layer.getChildren().add(this.imageView);
    }
    public void removeFromLayer() {
        this.layer.getChildren().remove(this.imageView);
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

    public javafx.geometry.Dimension2D getDimension() {
        return dimension;
    }
    public void setDimension(Dimension2D dimension) {
        this.dimension = dimension;
    }

    public Rectangle getBounds() { return new Rectangle((int) x, (int) y, dimension.getWidth(), dimension.getHeight()); }

    public Rectangle getUpBounds() { return new Rectangle((int) x, (int) y, dimension.getWidth(), 0); }
    public Rectangle getDownBounds() { return new Rectangle((int) x, (int) y+dimension.getHeight(), dimension.getWidth(), 0); }
    public Rectangle getLeftBounds() { return new Rectangle((int) x, (int) y, 0, dimension.getHeight()); }
    public Rectangle getRightBounds() { return new Rectangle((int) x+dimension.getHeight(), (int) y, 0, dimension.getHeight()); }

    public Image getImageStatic() {
        return imageStatic;
    }
    public void setImageStatic(Image imageStatic) {
        this.imageStatic = imageStatic;
    }


}
