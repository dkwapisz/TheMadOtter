package model.enemy;

import javafx.scene.layout.Pane;

public class EnemyTest extends Enemy{

    public EnemyTest(double x, double y, String pathStatic, String pathMoving, Pane mainLayer) {
        super(x, y, pathStatic, pathMoving, mainLayer);
        setVelX(3);
        setRemainingHealth(10);
    }

}
