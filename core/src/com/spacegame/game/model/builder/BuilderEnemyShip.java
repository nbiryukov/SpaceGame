/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacegame.game.model.builder;

import com.badlogic.gdx.math.Rectangle;
import com.spacegame.game.Game;
import com.spacegame.game.model.Direction;
import com.spacegame.game.model.EnemyShip;
import com.spacegame.game.model.GameObject;
import com.spacegame.game.model.Ship;
import com.spacegame.game.model.Trajectory;
import com.spacegame.game.model.TypeObject;
import com.spacegame.game.model.Weapon;
import java.util.ArrayList;

/**
 * Класс построения вражеских кораблей
 * @author Никита
 */
public class BuilderEnemyShip implements BuilderGameObject {
    
    private Ship result;

    /**
     * Построить объект
     * @param type тип объекта
     */
    @Override
    public void buildObject(TypeObject type) {
        if (type == TypeObject.ENEMYSHIPONEMISSILE) {
            this.result = new EnemyShip(TypeObject.ENEMYSHIPONEMISSILE, 200);
       
            // Здоровье и начальное положение(можно его менять)
            result.setHealth(1);
            result.setRectangle(new Rectangle(Game.WIDTH, Game.HEIGHT/2, 42, 40));

            // Заполняем оружием
            ArrayList<Weapon> weapons = new ArrayList<Weapon>();
            Weapon w = new Weapon((float)1.5, result);
            w.setAngle(0);
            w.setDirection(Direction.LEFT);
            weapons.add(w);
            
            result.setWeapons(weapons);
        } else if (type == TypeObject.ENEMYSHIPMOREMISSILE) {
            this.result = new EnemyShip(TypeObject.ENEMYSHIPMOREMISSILE, 200);
       
            result.setHealth(1);
            result.setRectangle(new Rectangle(Game.WIDTH, Game.HEIGHT/2, 40, 50));

            ArrayList<Weapon> weapons = new ArrayList<Weapon>();
            Weapon w = new Weapon((float)1.5, result);
            w.setAngle(7);
            w.setDirection(Direction.LEFT_TOP);
            weapons.add(w);

            w = new Weapon((float)1.5, result);
            w.setAngle(0);
            w.setDirection(Direction.LEFT);
            weapons.add(w);

            w = new Weapon((float)1.5, result);
            w.setAngle(7);
            w.setDirection(Direction.LEFT_DOWN);
            weapons.add(w);
        
            result.setWeapons(weapons);
        } else if (type == TypeObject.ENEMYHEALTH) {
            this.result = new EnemyShip(TypeObject.ENEMYHEALTH, 150);
       
            result.setHealth(5);
            result.setRectangle(new Rectangle(Game.WIDTH, Game.HEIGHT/2, 70, 62));
            
            ArrayList<Weapon> weapons = new ArrayList<Weapon>();
            result.setWeapons(weapons);
        }
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
