/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacegame.game.model;

import com.badlogic.gdx.math.Rectangle;

/**
 * Класс оружия
 * @author Никита
 */
public class Weapon {
    
    private final float frequency; // частота встрела
    private float timeLast; // время, прошедшее с прошлого выстрела
    private Ship ship; // корабль, которому принадлежит оружие
    private float angle; // угол выстрела
    private Direction direction; // направление выстрела
    
    public Weapon(float frequency, Ship ship) {
        this.frequency = frequency;
        this.timeLast = 10;
        this.ship = ship;
        this.angle = 0;
    }
    
    /**
     * Произвести выстрел
     * @return снаряд
     */
    public Missile fire() {
        
        if (frequency < timeLast) {
            timeLast = 0;
            if (ship.getType() == TypeObject.PLAYER) {
                Missile missile = new Missile(TypeObject.MISSILEPLAYER, 500);
                Rectangle rec = ship.getRectangle();
                missile.setRectangle(new Rectangle(rec.x + rec.width, rec.y + (rec.height/2 - 6), 22, 15));
                Trajectory trajectory = new Trajectory(angle, direction);
                missile.setTrajectory(trajectory);
                
                return missile;
            } else if (ship.getType() != TypeObject.PLAYER) {
                Missile missile = new Missile(TypeObject.MISSILEENEMY, 350);
                Rectangle rec = ship.getRectangle();
                missile.setRectangle(new Rectangle(rec.x - 22, rec.y + (rec.height/2 - 6), 22, 11));
                Trajectory trajectory = new Trajectory(angle, direction);
                missile.setTrajectory(trajectory);
                
                return missile;
            }
            
        }
        
        return null;
    }
    
    /**
     * Обновить прошедшее время после выстрела
     * @param delta промежуток врмени
     */
    public void updateTimeLast(float delta) {
        this.timeLast += delta;
    }

    /**
     * Установить угол выстрела
     * @param angle угол(в градусах)
     */
    public void setAngle(float angle) {
        this.angle = angle;
    }
    
    /**
     * Установить направление выстрела
     * @param direction направление
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
