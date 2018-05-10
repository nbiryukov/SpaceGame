/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacegame.game.model;

import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Nikita
 */
public interface GameAPI {
    
    public void updatePosition(float xDelta, float yDelta);
    public Rectangle getRectangle();
    public void setBot(BotAPI bot);
    public void shoot();
    public ArrayList<GameObject> getGameObjects();
}
