package model.item.guns;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.item.Item;
import model.item.guns.Uzi;

import java.awt.*;

public abstract class Gun extends Item {

    private int bulletVel;
    private long cooldownShot;
    private int dmg;
    private int ammo;
    private String gunName;
    private String pathBullet;

    public Gun(double x, double y, String pathStatic, String pathBullet, Pane layer) {
        super(x, y, pathStatic, layer);
        this.pathBullet = pathBullet;
    }


    public void addAmmo() {
        if (this instanceof Uzi) {
            ammo = ammo + 50;
        }
        if (this instanceof Ak47) {
            ammo = ammo + 30;
        }
        if (this instanceof SniperRifle) {
            ammo = ammo + 5;
        }
        if (this instanceof Deagle) {
            ammo = ammo + 7;
        }
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

    public int getAmmo() {
        return ammo;
    }
    public void setAmmo(int ammo) {
        this.ammo = ammo;
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
