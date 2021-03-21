package model;

import dev.InputManager;
import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public abstract class MovingObjects {

    private double x, y;
    private double velX, velY;
    private javafx.geometry.Dimension2D dimension;
    private Pane layer;
    private ImageView imageView;
    private Image imageStatic;
    private Image imageMoving;
    private Image imageStaticShot;
    private Image imageMovingShot;
    private boolean shootingStatus = false;
    private boolean noAmmo = false;
    private final ArrayList<Rectangle2D> frame = new ArrayList<>();

    public MovingObjects(double x, double y, String pathStatic, String pathMoving, String pathStaticShot, String pathMovingShot, Pane layer) {
        setLocation(x, y);
        this.layer = layer;
        loadImage(pathStatic);
        imageStatic = new Image(pathStatic);
        imageMoving = new Image(pathMoving);
        if(pathStaticShot != null && pathMovingShot != null) {
            imageStaticShot = new Image(pathStaticShot);
            imageMovingShot = new Image(pathMovingShot);
        }
        dimension = new javafx.geometry.Dimension2D(imageStatic.getWidth(), imageStatic.getHeight()/4);
    }

    public void setLocation(double x, double y) {
        setX(x);
        setY(y);
    }

    private void loadFrames() {
        int width = (int) imageView.getImage().getWidth();
        for(int i=0; i<4; i++) {
            frame.add(new Rectangle2D(0, width*i, width, width));
        }
    }

    public void updateLocation() {
        if(!(x + velX < 30 || x + velX > 770 - imageStatic.getHeight()/4 || y + velY < 30 || y + velY > 770 - imageStatic.getHeight()/4)) {
            x = x + velX;
            y = y + velY;
        }

        imageView.relocate(x, y);

        if (velX != 0 || velY != 0){
            if(shootingStatus && !noAmmo) {
                imageView.setImage(imageMovingShot);
            } else {
                imageView.setImage(imageMoving);

            }
            directions(shootingStatus);
        } else {
            if (shootingStatus && !noAmmo) {
                imageView.setImage(imageStaticShot);
                directions(shootingStatus);
            } else {
                imageView.setImage(imageStatic);
            }
        }
    }

    private void loadImage(String path) {
        imageView = new ImageView(new Image(path));
        loadFrames();
        imageView.setViewport(frame.get(0));
        this.imageView.relocate(this.getX(), this.getY());
        this.addToLayer();
    }

    public void directions(boolean shooting){
        if(!shooting) {
            if(velX > 0 && Math.abs(velX) > Math.abs(velY)){
                imageView.setViewport(frame.get(2)); // PRAWO
            }else if (velX < 0 && Math.abs(velX) > Math.abs(velY)){
                imageView.setViewport(frame.get(3)); // LEWO
            }else if (velY < 0 && Math.abs(velY) > Math.abs(velX)){
                imageView.setViewport(frame.get(1)); // GÓRA
            }else if (velY > 0 && Math.abs(velY) > Math.abs(velX)){
                imageView.setViewport(frame.get(0)); // DÓŁ
            }
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

    public Rectangle getSmallerBounds(){
        return new Rectangle((int) x+velX+16,(int) y+velY+32, dimension.getWidth()/2, dimension.getHeight()/2);
    }

    public Rectangle getUpBounds() { return new Rectangle((int) x+velX, (int) y+velY, dimension.getWidth(), 2); }
    public Rectangle getDownBounds() { return new Rectangle((int) x+velX, (int) y+velY+dimension.getHeight(), dimension.getWidth(), 2); }
    public Rectangle getLeftBounds() { return new Rectangle((int) x+velX, (int) y+velY, 2, dimension.getHeight()); }
    public Rectangle getRightBounds() { return new Rectangle((int) x+velX+dimension.getHeight(), (int) y+velY, 2, dimension.getHeight()); }

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

    public ArrayList<Rectangle2D> getFrame() {
        return frame;
    }

    public boolean isShootingStatus() {
        return shootingStatus;
    }
    public void setShootingStatus(boolean shootingStatus) {
        this.shootingStatus = shootingStatus;
    }

    public boolean isNoAmmo() {
        return noAmmo;
    }
    public void setNoAmmo(boolean noAmmo) {
        this.noAmmo = noAmmo;
    }

    public Image getImageStaticShot() {
        return imageStaticShot;
    }
    public void setImageStaticShot(Image imageStaticShot) {
        this.imageStaticShot = imageStaticShot;
    }

    public Image getImageMovingShot() {
        return imageMovingShot;
    }
    public void setImageMovingShot(Image imageMovingShot) {
        this.imageMovingShot = imageMovingShot;
    }
}



