package dev;

import javafx.scene.input.KeyCode;
import model.hero.Hero;
import model.hero.HeroDirections;

import java.util.List;

public class InputManager {

    public static Hero hero = new Hero(100, 100);

    public static void handlePlayerActions() {
        List keyboardInput = EventHandling.getInputList();

        if(keyboardInput.contains(KeyCode.W)){
            hero.setCurrentDirection(HeroDirections.UP);
            hero.move();
        }
        if(keyboardInput.contains(KeyCode.S)){
            hero.setCurrentDirection(HeroDirections.DOWN);
            hero.move();
        }
        if(keyboardInput.contains(KeyCode.A)){
            hero.setCurrentDirection(HeroDirections.LEFT);
            hero.move();
        }
        if(keyboardInput.contains(KeyCode.D)){
            hero.setCurrentDirection(HeroDirections.RIGHT);
            hero.move();
        }

        if(keyboardInput.contains(KeyCode.UP)){
            // Strzelanie - góra
        }
        if(keyboardInput.contains(KeyCode.DOWN)){
            // Strzelanie - dół
        }
        if(keyboardInput.contains(KeyCode.LEFT)){
            // Strzelanie - lewo
        }
        if(keyboardInput.contains(KeyCode.RIGHT)){
            // Strzelanie - prawo
        }

        if(!keyboardInput.contains(KeyCode.W) && !keyboardInput.contains(KeyCode.A) && !keyboardInput.contains(KeyCode.S) && !keyboardInput.contains(KeyCode.D)) {
            hero.setVelX(0);
            hero.setVelY(0);
        }


    }


}
