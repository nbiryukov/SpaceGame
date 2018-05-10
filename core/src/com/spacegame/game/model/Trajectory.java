/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacegame.game.model;

import com.badlogic.gdx.math.Rectangle;

/**
 * Класс траектории объекта
 * @author Nikita
 */
public class Trajectory {
    
   private float angle; // угол
   private Direction direction; // направление
   
   public Trajectory(float angle, Direction direction) {
       this.angle = angle;
       this.direction = direction;
   }
   
   /**
    * Рассчитать новую позицию по траектори
    * @param delta промежуток времени
    * @param rectangle прямоугольник физического отображения объекта
    * @param speed скорость объекта
    */
   public void calcNewPosition(float delta, Rectangle rectangle, int speed) {
       
       // Рассчитываем дельту по углу(коэф на который умножается сама дельта при апдейте)
       double dX = Math.sin(Math.toRadians((double)(90 - angle)));
       double dY = Math.sin(Math.toRadians((double)(angle)));
       
       // Высчитываем новые координаты для прямоугольника игрового объекта
       if (direction == Direction.LEFT || direction == Direction.LEFT_TOP) {
           rectangle.x -= delta * dX * speed;
           rectangle.y += delta * dY * speed;
       } else if (direction == Direction.RIGHT || direction == Direction.RIGHT_TOP) {
           rectangle.x += delta * dX * speed;
           rectangle.y += delta * dY * speed;
       } else if(direction == Direction.RIGHT_DOWN) {
           rectangle.x += delta * dX * speed;
           rectangle.y -= delta * dY * speed;
       } else if (direction == Direction.LEFT_DOWN) {
           rectangle.x -= delta * dX * speed;
           rectangle.y -= delta * dY * speed;
       }
   }
    
}
