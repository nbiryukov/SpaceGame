/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacegame.game.model;

/**
 * Класс ракеты
 * @author Никита
 */
public class Missile extends GameObject {

    
    public Missile(TypeObject type, int speed) {
        super(type, speed);
    }

    /**
     * Обновление ракеты
     * @param delta промежуток времени
     */
    @Override
    public void update(float delta) {
        getTrajectory().calcNewPosition(delta, getRectangle(), getSpeed());
    }
}
