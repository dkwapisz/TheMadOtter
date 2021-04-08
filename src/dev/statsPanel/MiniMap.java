package dev.statsPanel;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import map.Room;
import model.hero.Hero;

import java.util.HashMap;

public class MiniMap{
    private final Hero hero;
    private final HashMap<Integer,ImageView> map = new HashMap<>();

    public MiniMap(Hero hero){
        this.hero = hero;
        generateMiniMap();
    }

    private ImageView createImageView(int x, int y){
        ImageView miniPart = new ImageView(new Image("graphics/statsPanel/notCleaned.png"));
        miniPart.relocate(x, y);
        this.hero.getLayer().getChildren().add(miniPart);
        return miniPart;
    }

    private void generateMiniMap(){
        int x = 781;
        int y = 15;
        for (Room room:hero.getFloor().getRoomList()) {
            if (room.getRoomId() % 5 == 0){
                x = x + 34;
                y = 15 ;
            }
            this.map.put(room.getRoomId(), createImageView(x, y));
            y = y + 34;
        }
    }

    public void updateMiniMap(){
        for (Room room:hero.getFloor().getRoomList()){
            if (room.getRoomId() == hero.getActualRoom().getRoomId()) {
                map.get(room.getRoomId()).setImage(new Image("graphics/statsPanel/actual.png"));
            }
            else if (room.isClean() && !room.isShop()) {
                map.get(room.getRoomId()).setImage(new Image("graphics/statsPanel/cleaned.png"));
            } else if (room.isShop()) {
                map.get(room.getRoomId()).setImage(new Image("graphics/statsPanel/shop.png"));
            } else {
                map.get(room.getRoomId()).setImage(new Image("graphics/statsPanel/notCleaned.png"));
            }
        }
    }




}
