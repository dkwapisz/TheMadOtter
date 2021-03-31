package model.enemy;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Random;

public class Slime extends Enemy{
    private boolean slimeKing = false;
    private boolean medium = false;
    private boolean small = false;
    private double parentPositionX;
    private double parentPositionY;

    public Slime(double x, double y, Pane layer, String name) {
        super(x, y, "/graphics/enemies/slime.gif", "/graphics/enemies/slime.gif", null, null, layer);
        if(name == "SlimeKing") {
            setFlying(false);
            setExplosive(false);
            setShooting(false);
            setRemainingHealth(120);
            setFollowing(true);
            setFollowingVel(2);
            setDmg(4);
            setSlimeKing(true);
        }
        if(name == "Medium") {
            setFlying(false);
            setExplosive(false);
            setShooting(false);
            setRemainingHealth(60);
            setFollowing(false);
            setDmg(4);
            setMedium(true);
            addToLayer();
            setParentPositionX(parentPositionX);
            setParentPositionY(parentPositionY);
        }
        if(name == "Small") {
            setFlying(false);
            setExplosive(false);
            setShooting(false);
            setRemainingHealth(15);
            setDmg(4);
            setSmall(true);
            addToLayer();
            setParentPositionX(parentPositionX);
            setParentPositionY(parentPositionY);
        }
    }

    public ArrayList<Slime> createMedium(double x, double y){
        ArrayList<Slime> mediumSlimes = new ArrayList<>();
        for (int i = 0; i<=3; i++){
            Slime medium = new Slime(x,y,getLayer(),"Medium");
            if(i == 0){
                medium.setVelY(0);
                medium.setVelX(4);
            }else if(i == 1){
                medium.setVelY(0);
                medium.setVelX(-4);
            }else if(i == 2){
                medium.setVelY(4);
                medium.setVelX(0);
            }else if(i == 3){
                medium.setVelY(-4);
                medium.setVelX(0);
            }
            mediumSlimes.add(medium);
        }

        return mediumSlimes;
    }

    public ArrayList<Slime> createSmall(double x, double y){
        ArrayList<Slime> mediumSlimes = new ArrayList<>();
        for (int i = 0; i<=3; i++){
            Slime medium = new Slime(x,y,getLayer(),"Small");
            if(i == 0){
                medium.setVelY(8);
                medium.setVelX(8);
            }else if(i == 1){
                medium.setVelY(-8);
                medium.setVelX(-8);
            }else if(i == 2){
                medium.setVelY(8);
                medium.setVelX(-8);
            }else if(i == 3){
                medium.setVelY(-8);
                medium.setVelX(8);
            }
            mediumSlimes.add(medium);
        }

        return mediumSlimes;
    }


    public boolean isSlimeKing() {
        return slimeKing;
    }

    public void setSlimeKing(boolean slimeKing) {
        this.slimeKing = slimeKing;
    }

    public boolean isMedium() {
        return medium;
    }

    public void setMedium(boolean medium) {
        this.medium = medium;
    }

    public boolean isSmall() {
        return small;
    }

    public void setSmall(boolean small) {
        this.small = small;
    }

    public double getParentPositionX() {
        return parentPositionX;
    }

    public void setParentPositionX(double parentPositionX) {
        this.parentPositionX = parentPositionX;
    }

    public double getParentPositionY() {
        return parentPositionY;
    }

    public void setParentPositionY(double parentPositionY) {
        this.parentPositionY = parentPositionY;
    }
}
