/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacegame.game.model;

import com.badlogic.gdx.math.Rectangle;

/**
 * Класс вражеского корабля
 *
 * @author Никита
 */
public class EnemyShip extends Ship {

    private boolean teleport;// Нужно ли телепортироваться
    private boolean doneTeleport; // стелепортировался ли
    private float timeLast;
    private float timeTeleport;

    public EnemyShip(TypeObject type, int speed) {
        super(type, speed);
        this.teleport = false;
        this.doneTeleport = false;
        this.timeLast = 0;
        this.timeTeleport = 0;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        timeLast += delta;
        // и если корабль движется близко к верхнему или нижнему краю
        if (getTrajectory().getDirection() == Direction.LEFT && closeTheEdge() && !teleport) {
            teleport = true;
            timeTeleport = (float) (Math.random() + 1.0);
        }

        // пора ли переместиться
        if (timeLast > timeTeleport && !doneTeleport && teleport) {
            // перемещаем
            doneTeleport = true;
            Rectangle rec = getRectangle();
            float y = rec.y;
            rec.setY(540 - y - rec.height);
        }
    }

    private boolean closeTheEdge() {
        float y = getRectangle().y;

        if (y <= 110 || y >= 430) {
            return true;
        }
        return false;
    }

}
