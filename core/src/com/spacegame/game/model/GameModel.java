/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacegame.game.model;

import com.badlogic.gdx.math.Rectangle;
import com.spacegame.game.Game;
import com.spacegame.game.GameState;
import com.spacegame.game.loader.ModuleEngine;
import com.spacegame.game.model.builder.BuilderPlayerShip;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Игровая модель
 *
 * @author Никита
 */
public class GameModel implements GameAPI {

    private ArrayList<GameObject> gameObjects; // список игровых объектов на поле
    private BuilderPlayerShip builderPlayerShip; // строитель объекта игрока
    private PlayerShip player; // игрок
    private Generator generator; // генератор игровых объектов
    private int countDownedShips; // кол-во убитых врагов

    private ModuleEngine loader;
    private BotAPI bot = null;

    public GameModel() {
        start();
    }

    /**
     * Инициализация игры
     */
    public void start() {
        this.gameObjects = new ArrayList<GameObject>();
        this.builderPlayerShip = new BuilderPlayerShip();
        this.builderPlayerShip.buildObject(TypeObject.PLAYER);
        this.player = (PlayerShip) this.builderPlayerShip.getObject();
        this.gameObjects.add(this.player);
        this.countDownedShips = 0;
        this.generator = new Generator();
        loader = new ModuleEngine();
        bot = null;
    }
    
    public void loadBot(String modulePath, String moduleName) {
        loader.loadBot(modulePath, moduleName, this);
    }

    /**
     * Обновление модели игры
     *
     * @param delta промежуток времени
     */
    public void update(float delta) {

        // Обновляем все игровые оъекты и получаем новые
        ArrayList<GameObject> tmp = new ArrayList<GameObject>();
        for (GameObject o : gameObjects) {
            o.update(delta);
            if (o instanceof EnemyShip) {
                ArrayList<Missile> missiles = ((EnemyShip) o).fire();
                if (!missiles.isEmpty()) {
                    tmp.addAll(missiles);
                }
            }
        }
        gameObjects.addAll(tmp);

        // Генерируем новые объекты
        tmp = generator.update(delta, player.getHealth(), countDownedShips);
        gameObjects.addAll(tmp);

        // Просчитать коллизии
        collision();
        // Удалить объекты, которые покинули поле
        leftField();

        if (bot != null) {
            bot.update(delta);
        }
    }

    /**
     * Обновление позиции игрока
     *
     * @param xDelta смещение по x
     * @param yDelta смещение по y
     */
    public void updatePlayerShipPosition(float xDelta, float yDelta) {
        player.move(xDelta, yDelta);
    }

    /**
     * Выстрел игрока
     */
    public void shootPlayer() {
        ArrayList<Missile> missiles = player.fire();
        if (!missiles.isEmpty()) {
            gameObjects.addAll(missiles);
        }
    }

    // обработка коллизий
    private void collision() {
        GameObject o = null;
        GameObject tmp = null;
        for (int i = 0; i < gameObjects.size(); i++) {
            o = gameObjects.get(i);
            if (o.getType() == TypeObject.PLAYER) {
                // с вражескими снарядом или кораблем
                for (int j = 0; j < gameObjects.size(); j++) {
                    tmp = gameObjects.get(j);
                    if (!o.getRectangle().equals(tmp.getRectangle()) && o.getRectangle().overlaps(tmp.getRectangle())) {

                        if (tmp instanceof HealthBonus || tmp instanceof WeaponBonus) {
                            ((PlayerShip) o).activateBonus(tmp);
                        } else {
                            ((PlayerShip) o).hitting();
                        }

                        if (((PlayerShip) o).getHealth() == 0 || tmp instanceof EnemyShip) {
                            gameObjects.remove(o);
                            --i;
                            GameState.getInstance().setCurrentState(GameState.State.GameOver);
                        }
                        gameObjects.remove(tmp);
                        --i;
                        if (i < 0) {
                            i = 0;
                        }
                        break;
                    }
                }

            } else if (o.getType() == TypeObject.MISSILEPLAYER) {
                // с вражескими снарядами или кораблем
                for (int j = 0; j < gameObjects.size(); j++) {
                    tmp = gameObjects.get(j);
                    if (!o.getRectangle().equals(tmp.getRectangle()) && o.getRectangle().overlaps(tmp.getRectangle())) {

                        if (tmp instanceof Ship) {
                            gameObjects.remove(o);
                            --i;
                            ((Ship) tmp).hitting();
                            if (((Ship) tmp).getHealth() == 0) {

                                // Посчитать кол-во очков за сбитый корабль
                                if (tmp.getType() == TypeObject.ENEMYHEALTH) {
                                    this.player.addScore(3);
                                } else {
                                    this.player.addScore(1);
                                }
                                // Общее кол-во сбитых кораблей
                                this.countDownedShips++;

                                // Удалить корабль врага
                                gameObjects.remove(tmp);
                                --i;
                            }
                        } else if (tmp.getType() == TypeObject.MISSILEENEMY) {
                            gameObjects.remove(o);
                            --i;
                            gameObjects.remove(tmp);
                            --i;
                        }

                        if (i < 0) {
                            i = 0;
                        }
                        break;
                    }
                }
            }
        }
    }

    // удаление объектов, покинувших поле
    private void leftField() {

        Iterator<GameObject> iter = gameObjects.iterator();
        while (iter.hasNext()) {
            GameObject o = iter.next();
            if (!Game.AREA.contains(o.getRectangle())) {
                iter.remove();
            }
        }
    }

    /**
     * Получить счет
     *
     * @return счет
     */
    public int getScore() {
        return player.getScore();
    }

    /**
     * Получить здоровье
     *
     * @return количество здоровья
     */
    public int getHealth() {
        return player.getHealth();
    }

    /**
     * Получить количество убитых врагов
     *
     * @return количество убитых врагов
     */
    public int countDownedShips() {
        return countDownedShips;
    }

    
    /**
     * Получение игровых объектов
     *
     * @return список объектов
     */
    @Override
    public ArrayList<GameObject> getGameObjects() {
        return this.gameObjects;
    }
    
    
    @Override
    public void setBot(BotAPI bot) {
        this.bot = bot;
    }

    @Override
    public void shoot() {
        shootPlayer();
    }

    @Override
    public void updatePosition(float xDelta, float yDelta) {
        updatePlayerShipPosition(xDelta, yDelta);
    }

    @Override
    public Rectangle getRectangle() {
        return player.getRectangle();
    }
    
    

}
