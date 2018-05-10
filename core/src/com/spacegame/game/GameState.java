/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacegame.game;

/**
 * Класс состояния игры
 * @author Nikita
 */
public class GameState {
    
    private static GameState instance = new GameState();
    
    public enum State {GameOver, PlayGame, PauseGame, Menu}
    private State currentState = State.Menu; // текущее состояние
    
    private GameState() {
        
    }

    /**
     * Получить инстанс класса
     * @return объект класса состояния игры
     */
    public static GameState getInstance() {
        return instance;
    }
    
    /**
     * Получить текущее состояние игры
     * @return состояние
     */
    public State getCurrentState() {
        return currentState;
    }
    
    /**
     * Установить текущее состояние игры
     * @param state состояние
     */
    public void setCurrentState(State state) {
        this.currentState = state;
    }
    
}
