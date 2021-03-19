package dev;

import javafx.scene.input.KeyCode;
import model.hero.Hero;
import model.hero.HeroActions;

import java.util.List;

public class InputManager {
    public Hero hero;
    public InputManager(Hero hero) {
        this.hero = hero;
    }

    public void handlePlayerActions() {
        List keyboardInput = EventHandling.getInputList();

        if(keyboardInput.contains(KeyCode.W)){
            hero.setCurrentAction(HeroActions.UP);
            hero.move();
        }
        if(keyboardInput.contains(KeyCode.S)){
            hero.setCurrentAction(HeroActions.DOWN);
            hero.move();
        }
        if(keyboardInput.contains(KeyCode.A)){
            hero.setCurrentAction(HeroActions.LEFT);
            hero.move();
        }
        if(keyboardInput.contains(KeyCode.D)){
            hero.setCurrentAction(HeroActions.RIGHT);
            hero.move();
        }

        if(keyboardInput.contains(KeyCode.UP)){
            hero.setShooting(true);
            hero.setCurrentAction(HeroActions.SHOTUP);
            hero.shot(0, -1);
        }
        if(keyboardInput.contains(KeyCode.DOWN)){
            hero.setShooting(true);
            hero.setCurrentAction(HeroActions.SHOTDOWN);
            hero.shot(0, 1);
        }
        if(keyboardInput.contains(KeyCode.LEFT)){
            hero.setShooting(true);
            hero.setCurrentAction(HeroActions.SHOTLEFT);
            hero.shot(-1, 0);
        }
        if(keyboardInput.contains(KeyCode.RIGHT)){
            hero.setShooting(true);
            hero.setCurrentAction(HeroActions.SHOTRIGHT);
            hero.shot(1, 0);
        }

        if(keyboardInput.contains(KeyCode.E)){
            hero.changeWeapon(true);
        }
        if(keyboardInput.contains(KeyCode.Q)){
            hero.changeWeapon(false);
        }


        if(!keyboardInput.contains(KeyCode.A) && !keyboardInput.contains(KeyCode.D)) {
            hero.setVelX(0);
        }
        if(!keyboardInput.contains(KeyCode.W) && !keyboardInput.contains(KeyCode.S)) {
            hero.setVelY(0);
        }

        if(!keyboardInput.contains(KeyCode.UP) && !keyboardInput.contains(KeyCode.DOWN) && !keyboardInput.contains(KeyCode.LEFT) && !keyboardInput.contains(KeyCode.RIGHT)) {
            hero.setShooting(false);
        }
    }



}
