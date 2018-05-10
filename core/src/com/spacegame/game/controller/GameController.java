/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacegame.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.spacegame.game.GameState;
import com.spacegame.game.model.GameModel;
import com.spacegame.game.model.GameObject;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;

/**
 * Игровой контроллер
 * @author Никита
 */
public class GameController {
    
    private GameModel model; // модель игры
    
    public GameController(GameModel model) {
        this.model = model;
    }
    
    /**
     * Обновление игры
     * @param delta промежуток времени
     */
    public void update(float delta) {
        if (GameState.getInstance().getCurrentState() == GameState.State.PlayGame) {
            model.update(delta);
        }
        inputProcess(delta);
    }
    
    private void inputProcess(float delta) {
        if (GameState.getInstance().getCurrentState() == GameState.State.PlayGame) {
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
                model.updatePlayerShipPosition(0, (delta)*(-1));

            if (Gdx.input.isKeyPressed(Input.Keys.UP))
                model.updatePlayerShipPosition(0, (delta));

            if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
                model.updatePlayerShipPosition((delta)*(-1), 0);

            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                model.updatePlayerShipPosition((delta), 0);

            if (Gdx.input.isKeyPressed(Input.Keys.X))
                model.shootPlayer();
            
            if (Gdx.input.isKeyJustPressed(Input.Keys.P))
                GameState.getInstance().setCurrentState(GameState.State.PauseGame);
            
        } else if (GameState.getInstance().getCurrentState() == GameState.State.GameOver) {
            
            if (Gdx.input.isKeyPressed(Input.Keys.M)) {
                GameState.getInstance().setCurrentState(GameState.State.Menu);
            }
            
        } else if (GameState.getInstance().getCurrentState() == GameState.State.PauseGame) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.P))
                GameState.getInstance().setCurrentState(GameState.State.PlayGame);
        } else if (GameState.getInstance().getCurrentState() == GameState.State.Menu) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
                GameState.getInstance().setCurrentState(GameState.State.PlayGame);
                model.start();
            }
            
            if (Gdx.input.isKeyJustPressed(Input.Keys.B)) {
                GameState.getInstance().setCurrentState(GameState.State.PlayGame);
                // грузим бота
                JFileChooser fileopen = new JFileChooser(); 
                int ret = fileopen.showDialog(null, "Открыть файл"); 
                String moduleName = null; 
                String modulePath = null; 
                if (ret == JFileChooser.APPROVE_OPTION) { 
                    File file = fileopen.getSelectedFile(); 
                    System.out.println(file.getName());
                    moduleName = file.getName().split(".class")[0]; 
                    modulePath = (String)file.getPath();
                    model.start();
                    model.loadBot(modulePath, moduleName);
                }
            }
        }
    }
    
    /**
     * Получение игровых объектов
     * @return список объектов
     */
    public ArrayList<GameObject> getGameObjects() {
        return model.getGameObjects();
    }
    
    /**
     * Получить счет
     * @return счет
     */
    public int getScore() {
        return model.getScore();
    }
    
    /**
     * Получить здоровье
     * @return количество здоровья
     */
    public int getHealth() {
        return model.getHealth();
    }
    
    /**
     * Получить количество убитых врагов
     * @return количество убитых врагов
     */
    public int countDownedShips() {
        return model.countDownedShips();
    }
}
