package model.item.guns;

import javafx.scene.layout.Pane;
import model.item.Item;
import model.item.guns.Uzi;

public abstract class Gun extends Item {

    private int bulletVel;
    private long cooldownShot;
    private int dmg;
    private int ammo;
    private String gunName;

    public Gun(double x, double y, String pathStatic, Pane layer) {
        super(x, y, pathStatic, layer);
    }


    public void addAmmo() {
        if (this instanceof Uzi) {
            ammo = ammo + 30;
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
}
