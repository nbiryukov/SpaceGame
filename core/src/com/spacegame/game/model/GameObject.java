/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacegame.game.model;

import com.badlogic.gdx.math.Rectangle;

/**
 * Класс игрового объекта
 * @author Никита
 */
public abstract class GameObject {
    
    private Rectangle rectangle; // прямоугольник, физически отображающий объект
    private int speed; // скорость перемещения
    private TypeObject type; // тип
    private Trajectory trajectory; // траектория
    
    public GameObject(TypeObject type, int speed) {
        this.type = type;
        this.speed = speed;
    }
    
    /**
     * Обновление игрового объекта
     * @param delta промежуток времени
     */
    public abstract void update(float delta);

    
    //----------Геттеры и Сеттеры----------
    
    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public TypeObject getType() {
        return type;
    }

    public void setType(TypeObject type) {
        this.type = type;
    }
    
    public void setTrajectory(Trajectory trajectory) {
        this.trajectory = trajectory;
    }
    
    public Trajectory getTrajectory() {
        return this.trajectory;
    }
    
}
