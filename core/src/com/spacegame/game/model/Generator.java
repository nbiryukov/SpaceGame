/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacegame.game.model;

import com.badlogic.gdx.math.MathUtils;
import com.spacegame.game.Game;
import com.spacegame.game.model.builder.BuilderBonus;
import com.spacegame.game.model.builder.BuilderEnemyShip;
import java.util.ArrayList;

/**
 * Класс генерации игровых объектов
 * @author Nikita
 */
public class Generator {
    
    private float respawnTime; // время респауна врагов(меняется в зависимости от уровня)
    private float timeLastGeneration; // время, прошедшее от последней генерации врага
    private Level currentLevel; // текущий уровень игры
    private final BuilderBonus bb; // билдер бонусов
    private final BuilderEnemyShip bes; // билер вражеских кораблей
    
    private final float BONUS_HEALTH_RESPAWN_TIME = (float) 8; // время респавна бонуса хп
    private final float BONUS_WEAPON_RESPAWN_TIME = (float) 20; // время респавна бонуса оружия
    private final float GROUP_TIME =(float) 17; // время генерации группы кораблей
    private float healthTimeLast; // время, прошедшее от последней генерации хп бонуса
    private float weaponTimeLast; // время, прошедшее от последней генерации оружейного бонуса
    
    private int countShipStraightTrajectory; // кол-во кораблей, которые вылетали по прямой траектории
    private float groupTimeLast; // время, прошедшее от последней генерации группы врагов
    
    /**
     * Уровень игры
     */
    private enum Level {
        EASY, //  легкий(враги с одиночной прямой ракетой)
        MEDIUM, // средний(легкий с добавлением ХП-шных кораблей + вылет кораблей по диагонали(1 мод))
        HARD, // тяжелый(средний + корабли с 3мя орудиями + вылет врагов пачками по 3 штуки(2 мод))
        VERYHARD // очень тяжелый(тяжелый + уменьшается время респавна)
    }
    
    /**
     * Инициализация объекта
     */
    public Generator() {
        this.currentLevel = Level.EASY;
        this.timeLastGeneration = 0;
        this.respawnTime = (float) 2;
        this.healthTimeLast = 0;
        this.weaponTimeLast = 0;
        this.bb = new BuilderBonus();
        this.bes = new BuilderEnemyShip();
        this.countShipStraightTrajectory = 0;
        this.groupTimeLast = 0;
    }
    
    /**
     * Обновление объекта
     * @param delta промежуток времени
     * @param healthPlayer кол0во жизней у игрока
     * @param countDownedShips кол-во убитых врагов
     * @return 
     */
    public ArrayList<GameObject> update(float delta, int healthPlayer, int countDownedShips) {
        ArrayList<GameObject> result = new ArrayList<GameObject>();
        
        timeLastGeneration += delta;
        healthTimeLast += delta;
        weaponTimeLast += delta;
        groupTimeLast += delta;
        
        // смотрим какой сейчас уровень
        howLevel(countDownedShips);
        
        // генерируем вражеские корабли
        if (timeLastGeneration >= respawnTime) {
            timeLastGeneration = 0;
            int index = MathUtils.random(1, currentLevel.ordinal() + 1);
            if (index == 1) {
                // генерируем корабль с одной ракетой
                if (groupTimeLast >= GROUP_TIME && currentLevel.ordinal() >= 2) {
                    groupTimeLast = 0;
                    generateGroupShip(result, index);
                } else {
                    generateShipOneMissile(result);
                }
            } else if (index == 2) {
                // генерируем корабль хп
                if (groupTimeLast >= GROUP_TIME && currentLevel.ordinal() >= 2) {
                    groupTimeLast = 0;
                    generateGroupShip(result, index);
                } else {
                    generateShipHealth(result);
                }
            } else if (index >= 3) {
                // генерируем корабль с 3мя ракетами
                if (groupTimeLast >= GROUP_TIME && currentLevel.ordinal() >= 2) {
                    groupTimeLast = 0;
                    generateGroupShip(result, index);
                } else {
                    generateShipMoreMissile(result);
                }
            }
        }
        
        // генерируем бонусы
        if (healthPlayer < 3 && healthTimeLast >= BONUS_HEALTH_RESPAWN_TIME) {
            healthTimeLast = 0;
            bb.buildObject(TypeObject.HEALTHBONUS);
            bb.setStartPosition(Game.WIDTH, MathUtils.random(0, Game.HEIGHT - 30));
            result.add(bb.getObject());
        }
        
        if (weaponTimeLast >= BONUS_WEAPON_RESPAWN_TIME) {
            weaponTimeLast = 0;
            bb.buildObject(TypeObject.WEAPONBONUS);
            bb.setStartPosition(Game.WIDTH, MathUtils.random(0, Game.HEIGHT - 30));
            result.add(bb.getObject());
        }
        
        return result;
    }
    
    // Генерация группы объектов
    private void generateGroupShip(ArrayList<GameObject> result, int index) {
        if (index == 1) {
           bes.buildObject(TypeObject.ENEMYSHIPONEMISSILE);
           bes.setTrajectory(new Trajectory(0, Direction.LEFT));
           bes.setStartPosition(Game.WIDTH, 235);
           result.add(bes.getObject());
           
           bes.buildObject(TypeObject.ENEMYSHIPONEMISSILE);
           bes.setTrajectory(new Trajectory(0, Direction.LEFT));
           bes.setStartPosition(Game.WIDTH, 260);
           result.add(bes.getObject());
           
           bes.buildObject(TypeObject.ENEMYSHIPONEMISSILE);
           bes.setTrajectory(new Trajectory(0, Direction.LEFT));
           bes.setStartPosition(Game.WIDTH, 285);
           result.add(bes.getObject());
           
        } else if (index == 2) {
           bes.buildObject(TypeObject.ENEMYHEALTH);
           bes.setTrajectory(new Trajectory(0, Direction.LEFT));
           bes.setStartPosition(Game.WIDTH, 175);
           result.add(bes.getObject());
           
           bes.buildObject(TypeObject.ENEMYHEALTH);
           bes.setTrajectory(new Trajectory(0, Direction.LEFT));
           bes.setStartPosition(Game.WIDTH, 240);
           result.add(bes.getObject());
           
           bes.buildObject(TypeObject.ENEMYHEALTH);
           bes.setTrajectory(new Trajectory(0, Direction.LEFT));
           bes.setStartPosition(Game.WIDTH, 305);
           result.add(bes.getObject());
            
        } else if (index >= 3) {
           bes.buildObject(TypeObject.ENEMYSHIPMOREMISSILE);
           bes.setTrajectory(new Trajectory(0, Direction.LEFT));
           bes.setStartPosition(Game.WIDTH, 210);
           result.add(bes.getObject());
           
           bes.buildObject(TypeObject.ENEMYSHIPMOREMISSILE);
           bes.setTrajectory(new Trajectory(0, Direction.LEFT));
           bes.setStartPosition(Game.WIDTH, 265);
           result.add(bes.getObject());
           
           bes.buildObject(TypeObject.ENEMYSHIPMOREMISSILE);
           bes.setTrajectory(new Trajectory(0, Direction.LEFT));
           bes.setStartPosition(Game.WIDTH, 320);
           result.add(bes.getObject());
        }
    }
    
    // генерация корабля с одной ракетой
    private void generateShipOneMissile(ArrayList<GameObject> result) {
        bes.buildObject(TypeObject.ENEMYSHIPONEMISSILE);
        if (countShipStraightTrajectory >= 4 && currentLevel != Level.EASY) {
            setDiagonallyTrajectory();
        } else {
            bes.setTrajectory(new Trajectory(0, Direction.LEFT));
            bes.setStartPosition(Game.WIDTH, MathUtils.random(0, Game.HEIGHT - 45));
            countShipStraightTrajectory++;
        }
        result.add(bes.getObject());
    }
    
    // генерация корабля с большим хп
    private void generateShipHealth(ArrayList<GameObject> result) {
        bes.buildObject(TypeObject.ENEMYHEALTH);
        if (countShipStraightTrajectory >= 4 && currentLevel != Level.EASY) {
            setDiagonallyTrajectory();
        } else {
            bes.setTrajectory(new Trajectory(0, Direction.LEFT));
            bes.setStartPosition(Game.WIDTH, MathUtils.random(0, Game.HEIGHT - 65));
            countShipStraightTrajectory++;
        }
        result.add(bes.getObject());
    }
    
    // генерация корабля с 3мя ракетами
    private void generateShipMoreMissile(ArrayList<GameObject> result) {
        bes.buildObject(TypeObject.ENEMYSHIPMOREMISSILE);
        if (countShipStraightTrajectory >= 4 && currentLevel != Level.EASY) {
            setDiagonallyTrajectory();
        } else {
            bes.setTrajectory(new Trajectory(0, Direction.LEFT));
            bes.setStartPosition(Game.WIDTH, MathUtils.random(0, Game.HEIGHT - 55));
            countShipStraightTrajectory++;
        }
        result.add(bes.getObject());
    }
    
    // сделать траекторию корабля по диагонали
    private void setDiagonallyTrajectory() {
        if(MathUtils.randomBoolean()) {
            bes.setTrajectory(new Trajectory(15, Direction.LEFT_DOWN));
            bes.setStartPosition(Game.WIDTH - 75, Game.HEIGHT);
        } else {
            bes.setTrajectory(new Trajectory(15, Direction.LEFT_TOP));
            bes.setStartPosition(Game.WIDTH - 75, -65);
        }
        countShipStraightTrajectory = 0;
    }
    
    // какой сейчас уровень игры
    private void howLevel(int countDownedShips) {
        // легкий
        if (countDownedShips <= 15) {
            currentLevel = Level.EASY;
            respawnTime = (float) 2;
        }
        // средний
        else if (countDownedShips >= 16 && countDownedShips <= 60) {
            currentLevel = Level.MEDIUM;
            respawnTime = (float) 1.9;
        }
        // тяжелый
        else if (countDownedShips >= 61 && countDownedShips <= 100) {
            currentLevel = Level.HARD;
            respawnTime = (float) 1.8;
        }
        // очень тяжелый
        else if (countDownedShips >= 101) {
            currentLevel = Level.VERYHARD;
            respawnTime = (float) 1.4;
        }
    }
    
}
