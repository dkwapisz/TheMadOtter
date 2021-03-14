package model.enemy;

import javafx.scene.layout.Pane;
import model.ObjectsBehaviour;

public class Enemy extends ObjectsBehaviour {


    public Enemy(double x, double y, String pathStatic, String pathMoving, Pane mainLayer) {
        super(x, y, pathStatic, pathMoving, mainLayer);
    }


}
