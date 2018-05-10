/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacegame.game.model;

import com.spacegame.game.Game;
import java.util.ArrayList;

/**
 * КОрабль игрока
 * @author Никита
 */
public class PlayerShip extends Ship {
    
    private int score; // счет
    private WeaponBonus weaponBonus; // текущий бонус
    private ArrayList<Weapon> nativeWeapons; // "родные оружия"(для бонуса)
    
    public PlayerShip(TypeObject type, int speed) {
        super(type, speed);
        this.score = 0;
    }

    /**
     * Обновление корабля игрока
     * @param delta промежуток времени
     */
    @Override
    public void update(float delta) {
        super.update(delta);
        
        // если есть бонус, то обновляем его время действия
        if (weaponBonus != null) {
            weaponBonus.setTimeAction(weaponBonus.getTimeAction() - delta);
            if (weaponBonus.getTimeAction() <= 0) {
                this.setWeapons(this.nativeWeapons);
                weaponBonus = null;
            }
        }
    }
    
    /**
     * Переместить позицию корабля игрока
     * @param xDelta смещение по координате x
     * @param yDelta смещение по координате y
     */
    public void move(float xDelta, float yDelta) {
        this.getRectangle().x += this.getSpeed() * xDelta;
        this.getRectangle().y += this.getSpeed() * yDelta;
        
        if (this.getRectangle().x + 102 > Game.WIDTH / 2) {
            this.getRectangle().x = Game.WIDTH / 2 - 102;
        } else if (this.getRectangle().x < 0) {
            this.getRectangle().x = 0;
        } else if (this.getRectangle().y + 34 > Game.HEIGHT) {
            this.getRectangle().y = Game.HEIGHT - 34;
        } else if (this.getRectangle().y < 0) {
            this.getRectangle().y = 0;
        }
    }
    
    /**
     * Прибавить очки к текущему счету
     * @param score счет
     */
    public void addScore(int score) {
        this.score += score;
    }
    
    /**
     * Получить счет 
     * @return счет
     */
    public int getScore() {
        return this.score;
    }
    
    /**
     * Активировать бонус
     * @param bonus бонус
     */
    public void activateBonus(GameObject bonus) {
        if (bonus instanceof HealthBonus) {
            setHealth(getHealth() + 1);
        } else if (bonus instanceof WeaponBonus) {
            if (this.weaponBonus == null) {
                nativeWeapons = this.getWeapons();
            }
            this.weaponBonus = (WeaponBonus) bonus;
            ((WeaponBonus) bonus).activateBonus(this);
            setWeapons(((WeaponBonus) bonus).getWeapons());
        }
    }

}
