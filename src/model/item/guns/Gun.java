package model.item.guns;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.item.Item;
import model.item.guns.Uzi;

import java.awt.*;

public abstract class Gun extends Item {

    private int bulletVel; // prędkość pocisku
    private long cooldownShot; // cooldown między strzałami
    private int dmg; // dmg per shot
    private String gunName; // nazwa broni
    private String pathBullet; // ścieżka do grafiki pocisku

    public Gun(double x, double y, String pathStatic, String pathBullet, Pane layer) {
        super(x, y, pathStatic, layer);
        this.pathBullet = pathBullet;
    }


    public int getBulletVel() {
        return bulletVel;
    }
    public void setBulletVel(int bulletVel) {
        this.bulletVel = bulletVel;
    }

    public long getCooldownShot() {
        return cooldownShot;
    }
    public void setCooldownShot(long cooldownShot) {
        this.cooldownShot = cooldownShot;
    }

    public int getDmg() {
        return dmg;
    }
    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public String getGunName() {
        return gunName;
    }
    public void setGunName(String gunName) {
        this.gunName = gunName;
    }

    public String getPathBullet() {
        return pathBullet;
    }
    public void setPathBullet(String pathBullet) {
        this.pathBullet = pathBullet;
    }
}
