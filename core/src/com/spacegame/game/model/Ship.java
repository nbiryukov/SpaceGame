/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacegame.game.model;

import java.util.ArrayList;

/**
 * Класс корабля
 * @author Никита
 */
public class Ship extends GameObject {
    
    private int health; // кол-во здоровья
    private ArrayList<Weapon> weapons; // оружия у корабля

    public Ship(TypeObject type, int speed) {
        super(type, speed);
        this.weapons = new ArrayList<Weapon>();
    }
    
    /**
     * Обновить корабль
     * @param delta промежуток времени
     */
    public void update(float delta) {
        if(this.getType() != TypeObject.PLAYER) {
            getTrajectory().calcNewPosition(delta, getRectangle(), getSpeed());
        }
        // обновляем время последнего выстрела у оружия
        for (Weapon weapon : weapons)
            weapon.updateTimeLast(delta);
    }
    
    /**
     * Произвести выстрел
     * @return список выпущенных снарядов
     */
    public ArrayList<Missile> fire() {
        ArrayList<Missile> missiles = new ArrayList<Missile>();
        for (Weapon weapon : weapons) {
            Missile m = weapon.fire();
            if (m != null)
                missiles.add(m);
        }
        
        return missiles;
    }
    
    /**
     * Попадание в корабль
     */
    public void hitting() {
        this.health--;
    }

    /**
     * Получить здоровье
     * @return 
     */
    public int getHealth() {
        return health;
    }

    /**
     * Установить здоровье
     * @param health кол-во жизней
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Получить оружия
     * @return список оружий
     */
    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    /**
     * Установить оружия
     * @param weapons список с оружиями
     */
    public void setWeapons(ArrayList<Weapon> weapons) {
        this.weapons = weapons;
    }  
}
