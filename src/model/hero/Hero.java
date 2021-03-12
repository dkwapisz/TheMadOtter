package model.hero;

import model.Physics;

public class Hero extends Physics {

    private int remainingLives;
    private HeroDirections currentDirection;


    public Hero(double x, double y) {
        super(x, y);
        currentDirection = HeroDirections.UP;
    }


    public void move() {
        int vel = 3;

        if(currentDirection == HeroDirections.UP) {
            setVelY(-vel);
        }
        if(currentDirection == HeroDirections.DOWN) {
            setVelY(vel);
        }
        if(currentDirection == HeroDirections.RIGHT) {
            setVelX(vel);
        }
        if(currentDirection == HeroDirections.LEFT) {
            setVelX(-vel);
        }
    }


    public int getRemainingLives() {
        return remainingLives;
    }

    public void setRemainingLives(int remainingLives) {
        this.remainingLives = remainingLives;
    }

    public HeroDirections getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(HeroDirections currentDirection) {
        this.currentDirection = currentDirection;
    }
}
