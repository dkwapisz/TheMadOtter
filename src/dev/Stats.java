package dev;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import model.hero.Hero;
import model.item.guns.M16;
import model.item.guns.Shotgun;

import java.util.ArrayList;

public class Stats {

    private final Hero hero;
    private final Label infoLabel = new Label();
    private final Label moneyLabel = new Label();
    private final Label bombLabel = new Label();
    private final Label floorLabel = new Label();
    private final Label dmgLabel = new Label();
    private final Label rofLabel = new Label();
    private final Label timeLabel = new Label();
    private final Label pointLabel = new Label();
    private ArrayList<Label> labels = new ArrayList<>();

    public Stats(Hero hero) {
        this.hero = hero;
        createBasicStats();
        createAdditionalStats();
    }

    public void createBasicStats() {
        labels.add(moneyLabel);
        labels.add(bombLabel);
        labels.add(floorLabel);
        labels.add(dmgLabel);
        labels.add(rofLabel);
        labels.add(timeLabel);
        labels.add(pointLabel);
        int y = 0;
        for (Label label : labels) {
            label.setStyle("-fx-font: 25 forte");
            label.relocate(875, 295 + y);
            label.setTextAlignment(TextAlignment.LEFT);
            label.setTextFill(Color.rgb(131, 82, 73));
            hero.getLayer().getChildren().add(label);
            y += 45;
        }
    }

    public void updateBasicStats() {
        moneyLabel.setText(hero.getMoney() + " $");
        bombLabel.setText(String.valueOf(hero.getBombs()));
        floorLabel.setText(String.valueOf(hero.getFloor().getFloorId()));
        dmgLabel.setText(String.valueOf(hero.getActualGun().getDmg()));
        rofLabel.setText(String.valueOf(getRof()));
        timeLabel.setText("No Data");
        pointLabel.setText("No Data");
    }

    public void createAdditionalStats() {
        infoLabel.setStyle("-fx-font: 16 arial");
        infoLabel.setTextFill(Color.WHITE);
        infoLabel.relocate(32, 32);
        hero.getLayer().getChildren().add(infoLabel);
    }

    public void updateAdditionalStats() {
        infoLabel.setVisible(hero.isAdditionalData());
        infoLabel.setText("Gun: " + hero.getActualGun().getGunName() +
                "\nGun Dmg: " + hero.getActualGun().getDmg() +
                "\nGun coold.: " + hero.getActualGun().getCooldownShot() + " [ms]" +
                "\nBullet Vel: " + hero.getActualGun().getBulletVel() +
                "\nHP: " + hero.getRemainingLives() +
                "\nMoney: " + hero.getMoney() + " $" +
                "\nBombs: " + hero.getBombs() +
                "\nClean Room: " + hero.getActualRoom().isClean() +
                "\nFloorID: " + hero.getFloor().getFloorId() +
                "\nRoomID: " + hero.getActualRoom().getRoomId() +
                "\nShooting: " + hero.isShooting() +
                "\nTrapdoor Open: " + hero.getFloor().getTrapdoor().isOpen());
    }

    private int getRof() {
        float rof = 1;
        if (hero.getActualGun() instanceof Shotgun) {
            rof = (60/((float) hero.getActualGun().getCooldownShot()/1000))*((float) 10*hero.getActualGun().getDmg());
        } else if (hero.getActualGun() instanceof M16) {
            rof = (60/((float) hero.getActualGun().getCooldownShot()/1000))*((float) 3*hero.getActualGun().getDmg());
        } else {
            rof = (60/((float) hero.getActualGun().getCooldownShot()/1000))*((float) hero.getActualGun().getDmg());
        }
        rof = Math.round(rof);
        return (int) rof;
    }
}
