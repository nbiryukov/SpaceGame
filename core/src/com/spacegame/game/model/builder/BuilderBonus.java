/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacegame.game.model.builder;

import com.badlogic.gdx.math.Rectangle;
import com.spacegame.game.Game;
import com.spacegame.game.model.Direction;
import com.spacegame.game.model.GameObject;
import com.spacegame.game.model.HealthBonus;
import com.spacegame.game.model.Trajectory;
import com.spacegame.game.model.TypeObject;
import com.spacegame.game.model.WeaponBonus;

/**
 * Класс построения бонусов
 * @author Nikita
 */
public class BuilderBonus implements BuilderGameObject {
    
    private GameObject result;

    /**
     * Построить объект
     * @param type тип объекта
     */
    @Override
    public void buildObject(TypeObject type) {
        if (type == TypeObject.HEALTHBONUS) {
            this.result = new HealthBonus(type, 175);
        } else if (type == TypeObject.WEAPONBONUS) {
            this.result = new WeaponBonus(type, 175);    
        }
        result.setRectangle(new Rectangle(Game.WIDTH, Game.HEIGHT/2, 24, 24));
        result.setTrajectory(new Trajectory(0, Direction.LEFT));
    }

    /**
     * Задать определенную траекторию
     * @param trajectory траектория
     */
    @Override
    public void setTrajectory(Trajectory trajectory) {
        result.setTrajectory(trajectory);
    }

    /**
     * Задать стартовую позицию
     * @param x координата по x
     * @param y координата по y
     */
    @Override
    public void setStartPosition(float x, float y) {
        result.getRectangle().x = x;
        result.getRectangle().y = y;
    }

    /**
     * Получить игровой объект
     * @return объект
     */
    @Override
    public GameObject getObject() {
        return result;
    }
    
}
