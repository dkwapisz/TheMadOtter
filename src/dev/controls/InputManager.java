package dev.controls;

import javafx.scene.input.KeyCode;

import java.util.List;

public class InputManager {


    public static void handlePlayerActions() {
        List keyboardInput = EventHandling.getInputList();

        if(keyboardInput.contains(KeyCode.W)){
            // Poruszanie gracza - góra
        }
        if(keyboardInput.contains(KeyCode.S)){
            // Poruszanie gracza - dół
        }
        if(keyboardInput.contains(KeyCode.A)){
            // Poruszanie gracza - lewo
        }
        if(keyboardInput.contains(KeyCode.D)){
            // Poruszanie gracza - prawo
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
            // Ustaw prędkość gracza na 0
        }


    }


}
