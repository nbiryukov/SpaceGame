/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacegame.game.model.builder;

import com.spacegame.game.model.GameObject;
import com.spacegame.game.model.Trajectory;
import com.spacegame.game.model.TypeObject;

/**
 * Интерфейс построения игровых объектов
 * @author Nikita
 */
public interface BuilderGameObject {
    /**
     * Построить объект
     * @param type тип объекта
     */
    public void buildObject(TypeObject type);
    
    /**
     * Задать определенную траекторию
     * @param trajectory траектория
     */
    public void setTrajectory(Trajectory trajectory);
    
    /**
     * Задать стартовую позицию
     * @param x координата по x
     * @param y координата по y
     */
    public void setStartPosition(float x, float y);
    
    /**
     * Получить игровой объект
     * @return объект
     */
    public GameObject getObject();
}
