package dev;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import model.hero.Hero;
import model.hero.HeroDirections;

import java.util.List;

public class InputManager {

    public Hero hero;

    public InputManager(Hero hero) {
        this.hero = hero;
    }

    public void handlePlayerActions() {
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

        if(!keyboardInput.contains(KeyCode.W) && !keyboardInput.contains(KeyCode.S) ) {
            hero.setVelY(0);
        }
        if(!keyboardInput.contains(KeyCode.A) && !keyboardInput.contains(KeyCode.D)) {
            hero.setVelX(0);
        }


    }


}
