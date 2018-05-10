/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacegame.game.model;

/**
 * Класс бонуса игры
 * @author Nikita
 */
public class HealthBonus extends GameObject {

    public HealthBonus(TypeObject type, int speed) {
        super(type, speed);
    }

    /**
     * Обновление бонуса
     * @param delta промежуток времени
     */
    @Override
    public void update(float delta) {
        getTrajectory().calcNewPosition(delta, getRectangle(), getSpeed());
    }
    
}
