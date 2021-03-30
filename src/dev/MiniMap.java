package dev;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import map.Room;
import model.hero.Hero;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MiniMap{
    private Hero hero;
    private HashMap<Integer,ImageView> map = new HashMap<Integer, ImageView>();

    MiniMap(Hero hero){
        this.hero = hero;
        generateMiniMap();
    }

    private ImageView createImageView(int x, int y, String path){
        ImageView miniPart = new ImageView(new Image(path));
        miniPart.relocate(x, y);
        this.hero.getLayer().getChildren().add(miniPart);
        return miniPart;
    }

    private void generateMiniMap(){
        int x = 760;
        int y = 0;
        for (Room room:hero.getFloor().getRoomList()) {
            if(room.getRoomId()%5 == 0){
                x = x + 40;
                y = 0 ;
            }
            this.map.put(room.getRoomId(),createImageView(x,y,"graphics/miniMapa/notCleaned.png"));

            y = y + 40;
        }
    }

    public void updateMiniMap(){
        for (Room room:hero.getFloor().getRoomList()){
            if(room.getRoomId() == hero.getActualRoom().getRoomId()){
                map.get(room.getRoomId()).setImage(new Image("graphics/miniMapa/actual.png"));
            }
            else if(room.isClean()){
                map.get(room.getRoomId()).setImage(new Image("graphics/miniMapa/cleaned.png"));
            }else {
                map.get(room.getRoomId()).setImage(new Image("graphics/miniMapa/notCleaned.png"));
            }
        }
    }




}
