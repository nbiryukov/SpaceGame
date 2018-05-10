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
import com.spacegame.game.model.PlayerShip;
import com.spacegame.game.model.Ship;
import com.spacegame.game.model.Trajectory;
import com.spacegame.game.model.TypeObject;
import com.spacegame.game.model.Weapon;
import java.util.ArrayList;

/**
 * Класс построения корабля игрока
 * @author Никита
 */
public class BuilderPlayerShip implements BuilderGameObject {

    private final int WIDTH = 102;
    private final int HEIGHT = 34;
    private Ship result;
    
    /**
     * Построить объект
     * @param type тип объекта
     */
    @Override
    public void buildObject(TypeObject type) {
        result = new PlayerShip(TypeObject.PLAYER, 250);
        
        result.setHealth(3);
        result.setRectangle(new Rectangle(0, Game.HEIGHT/2, WIDTH, HEIGHT));
        
        
        ArrayList<Weapon> weapons = new ArrayList<Weapon>();
        Weapon w = new Weapon((float)0.25, result);
        w.setAngle(0);
        w.setDirection(Direction.RIGHT);
        weapons.add(w);
        
        result.setWeapons(weapons);
        
    }

    /**
     * Задать определенную траекторию
     * @param trajectory траектория
     */
    @Override
    public void setTrajectory(Trajectory trajectory) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Задать стартовую позицию
     * @param x координата по x
     * @param y координата по y
     */
    @Override
    public void setStartPosition(float x, float y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
