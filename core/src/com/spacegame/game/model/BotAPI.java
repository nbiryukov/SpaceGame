/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacegame.game.model;

/**
 *
 * @author Nikita
 */
public interface BotAPI {
    
    public void update(float delta);
    public void run(GameAPI api);
    
}
