/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacegame.game.model;

import java.util.ArrayList;

/**
 * Класс бонуса оружи для игрока
 * @author Nikita
 */
public class WeaponBonus extends GameObject {
    
    private float timeAction; // время действия бонуса
    private ArrayList<Weapon> weapons; // список бонусных оружий 

    public WeaponBonus(TypeObject type, int speed) {
        super(type, speed);
        this.timeAction = 7;
    }

    /**
     * Обновить бонус
     * @param delta промежуток времени
     */
    @Override
    public void update(float delta) {
        getTrajectory().calcNewPosition(delta, getRectangle(), getSpeed());
    }

    /**
     * Установить время действия
     * @param timeAction время действия
     */
    public void setTimeAction(float timeAction) {
        this.timeAction = timeAction;
    }
    
    /**
     * Получить время действия
     * @return время действия
     */
    public float getTimeAction() {
        return this.timeAction;
    }
    
    /**
     * Получить бонусные оружия
     * @return список оружия
     */
    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    /**
     * Установить бонусные оружия
     * @param weapons список оружия
     */
    public void setWeapons(ArrayList<Weapon> weapons) {
        this.weapons = weapons;
    }
    
    /**
     * Активировать бонус
     * @param ship корабль игрока
     */
    public void activateBonus(PlayerShip ship) {
        weapons = new ArrayList<Weapon>();
        
        Weapon w = new Weapon((float)0.25, ship);
        w.setAngle(10);
        w.setDirection(Direction.RIGHT_TOP);
        weapons.add(w);

        w = new Weapon((float)0.25, ship);
        w.setAngle(0);
        w.setDirection(Direction.RIGHT);
        weapons.add(w);

        w = new Weapon((float)0.25, ship);
        w.setAngle(10);
        w.setDirection(Direction.RIGHT_DOWN);
        weapons.add(w);
    }
    
}
