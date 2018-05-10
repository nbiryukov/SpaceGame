/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacegame.game.botpackage;

import com.badlogic.gdx.math.Rectangle;
import com.spacegame.game.model.BotAPI;
import com.spacegame.game.model.GameAPI;
import com.spacegame.game.model.GameObject;
import com.spacegame.game.model.Missile;
import com.spacegame.game.model.PlayerShip;
import java.util.ArrayList;

/**
 *
 * @author Nikita
 */
public class Bot implements BotAPI {
    
    private GameAPI api;

    @Override
    public void run(GameAPI api) {
        this.api = api;
        this.api.setBot(this);
    }
    
    @Override
    public void update(float delta) {
        ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
        Rectangle rec = api.getRectangle();
        for (GameObject obj : api.getGameObjects()) {
            if (!(obj instanceof PlayerShip) && !(obj instanceof Missile)) {
                gameObjects.add(obj);
            }
        }
        
        // получаем ближайший объект
        GameObject nearestObject = null;
        if (!gameObjects.isEmpty()) {
            nearestObject = gameObjects.get(0);
            for (GameObject o : gameObjects) {
                if (o.getRectangle().x < nearestObject.getRectangle().x) {
                    nearestObject = o;
                }
            }
            
            Direction dir = getDirection(rec, nearestObject);
            if (null != dir) switch (dir) {
                case UP:
                    api.updatePosition(0, delta);
                    break;
                case DOWN:
                    api.updatePosition(0, delta * (-1));
                    break;
                case NONE:
                    api.updatePosition(0, 0);
                    break;
                default:
                    break;
            }
            
            api.shoot();
            
        } else {
            api.updatePosition(0, 0);
        }
        
    }
    
    public Direction getDirection(Rectangle recBot, GameObject nearestObject) {
        double deltaCenter = (recBot.y + recBot.height / 2) - (nearestObject.getRectangle().y + nearestObject.getRectangle().height / 2);
        
        if (Math.abs(deltaCenter) > 3) {
               if (deltaCenter < 0) {
                   return Direction.UP;
               } else {
                   return Direction.DOWN;
               }
        } else {
            return Direction.NONE;
        }
    }
    
    
    public enum Direction {
        UP,
        DOWN,
        NONE
    }
    
}
