package model;

import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import model.hero.HeroDirections;

public abstract class ObjectsBehaviour {

    private double x, y;
    private double velX, velY;
    private javafx.geometry.Dimension2D dimension;
    private Pane layer;
    private ImageView imageView;
    private Image imageStatic;
    private Image imageMoving;

    public ObjectsBehaviour(double x, double y, String pathStatic, String pathMoving, Pane layer) {
        setLocation(x, y);
        this.layer = layer;
        loadImage(pathStatic);
        imageStatic = new Image(pathStatic);
        imageMoving = new Image(pathMoving);
        dimension = new javafx.geometry.Dimension2D(imageStatic.getWidth(), imageStatic.getHeight());
    }

    public void setLocation(double x, double y) {
        setX(x);
        setY(y);
    }

    public void updateLocation() {
        if(!(x + velX < 30 || x + velX > 770 - imageStatic.getHeight() || y + velY < 30 || y + velY > 770 - imageStatic.getHeight())) {
            x = x + velX;
            y = y + velY;
        }

        imageView.relocate(x, y);
        rotation();

        if (velX != 0 || velY != 0){
            imageView.setImage(imageMoving);

        } else {
            imageView.setImage(imageStatic);
        }
    }

    private void loadImage(String path) {
        imageView = new ImageView(new Image(path));
        this.imageView.relocate(this.getX(), this.getY());
        this.addToLayer();
    }

    public void rotation(){
        if(velX > 0){
            imageView.setRotate(90);
        }else if (velX < 0){
            imageView.setRotate(-90);
        }else if (velY < 0){
            imageView.setRotate(0);
        }else if (velY > 0){
            imageView.setRotate(180);
        }
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

    public javafx.geometry.Dimension2D getDimension() {
        return dimension;
    }
    public void setDimension(Dimension2D dimension) {
        this.dimension = dimension;
    }

    public Rectangle getBounds() { return new Rectangle((int) x, (int) y, dimension.getWidth(), dimension.getHeight()); }

    public Image getImageStatic() {
        return imageStatic;
    }
    public void setImageStatic(Image imageStatic) {
        this.imageStatic = imageStatic;
    }

    public Image getImageMoving() {
        return imageMoving;
    }
    public void setImageMoving(Image imageMoving) {
        this.imageMoving = imageMoving;
    }

}



